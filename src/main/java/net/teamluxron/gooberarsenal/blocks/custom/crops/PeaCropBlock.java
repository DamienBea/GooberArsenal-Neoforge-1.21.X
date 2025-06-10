package net.teamluxron.gooberarsenal.blocks.custom.crops;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.teamluxron.gooberarsenal.blocks.entity.ModBlockEntities;
import net.teamluxron.gooberarsenal.entity.ModEntities;
import net.teamluxron.gooberarsenal.entity.custom.PeaShooterEntity;
import net.teamluxron.gooberarsenal.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class PeaCropBlock extends CropBlock implements EntityBlock {
    public static final int MAX_AGE = 3;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 3);
    private static final VoxelShape[] SHAPE_BY_AGE = {
            Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0)
    };

    public PeaCropBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.MAGICAL_BEAN_SEED;
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(Blocks.FARMLAND);
    }

    public static final EntityDataAccessor<UUID> PLANTER = SynchedEntityData.defineId(PeaCropBlock.class, EntityDataSerializers.UUID);

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(PLANTER);
        builder.add(AGE);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[state.getValue(AGE)];
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PeaCropBlockEntity(pos, state);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        if (placer instanceof Player player && level.getBlockEntity(pos) instanceof PeaCropBlockEntity blockEntity) {
            blockEntity.setPlanter(player.getUUID());
        }
    }

    @Override
    public void growCrops(Level level, BlockPos pos, BlockState state) {
        int newAge = Math.min(this.getMaxAge(), state.getValue(this.getAgeProperty()) + 1);
        if (newAge == this.getMaxAge()) {
            PeaShooterEntity peashooter = ModEntities.PEASHOOTER.get().create(level);
            if (peashooter != null) {
                peashooter.setPos(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);

                if (level.getBlockEntity(pos) instanceof PeaCropBlockEntity blockEntity) {
                    UUID planter = blockEntity.getPlanter();
                    if (planter != null) {
                        peashooter.setOwnerUUID(planter);
                    }
                }

                level.addFreshEntity(peashooter);
            }
            level.destroyBlock(pos, false);
        } else {
            level.setBlock(pos, state.setValue(AGE, newAge), 2);
        }
    }
}

public class PeaCropBlockEntity extends BlockEntity {
    private static final String PLANTER_KEY = "Planter";
    @Nullable
    private UUID planter;

    public PeaCropBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PEA_CROP.get(), pos, state);
    }

    public void setPlanter(UUID uuid) {
        this.planter = uuid;
        setChanged();
    }

    @Nullable
    public UUID getPlanter() {
        return planter;
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (planter != null) {
            tag.putUUID(PLANTER_KEY, planter);
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.hasUUID(PLANTER_KEY)) {
            planter = tag.getUUID(PLANTER_KEY);
        }
    }
}