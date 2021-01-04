package net.azagwen.atbyw.blocks.statues;

import net.azagwen.atbyw.blocks.AtbywProperties;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class StatueBlock extends HorizontalFacingBlock implements BlockEntityProvider {
    public static final IntProperty MOSS_LEVEL;
    private final StatueBlockMobType mobType;
    private final boolean hasLoots;

    public StatueBlock(boolean hasLoots, StatueBlockMobType mobType, Settings settings) {
        super(settings.nonOpaque());
        this.mobType = mobType;
        this.hasLoots = hasLoots;
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(MOSS_LEVEL, 0));
    }

    private int getMaxMossLevel() {
        return 4;
    }

    private int getCurrentMossLevel(BlockState state) {
        return state.get(MOSS_LEVEL);
    }

    private boolean isFullyCoveredInMoss(BlockState state) {
        return state.get(MOSS_LEVEL) >= this.getMaxMossLevel();
    }

    private Direction getDirection(BlockState state) {
        return state.get(FACING);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        int mossPercentage = (state.get(MOSS_LEVEL) * 25);

        if (this.isFullyCoveredInMoss(state)) {
            resetStatue(state, world, pos, player, mobType, hasLoots);
            player.sendMessage(new TranslatableText("block.atbyw." + mobType.getName() + ".ready", mossPercentage), true);
            return ActionResult.success(world.isClient);
        } else {
            player.sendMessage(new TranslatableText("block.atbyw." + mobType.getName() + ".not_ready", mossPercentage), true);
            return ActionResult.CONSUME;
        }
    }

    public static void resetStatue(BlockState state, World world, BlockPos pos, PlayerEntity player, StatueBlockMobType type, boolean hasLoots) {
        if (!world.isClient) {
            if (hasLoots) {
                float f = 0.7F;
                double x = (double) pos.getX() + (double) (world.random.nextFloat() * f) + 0.15000000596046448D;
                double y = (double) pos.getY() + (double) (world.random.nextFloat() * f) + 0.06000000238418579D + 0.6D;
                double z = (double) pos.getZ() + (double) (world.random.nextFloat() * f) + 0.15000000596046448D;

                ItemStack pickedStack = getItemFromLootTable(state, world, pos, player, type);
                ItemEntity itemEntity = new ItemEntity(world, x, y, z, pickedStack);
                itemEntity.setToDefaultPickupDelay();

                if (pickedStack.getCount() > 0) {
                    spawnParticles(world, pos, true);
                    world.playSound(null, pos, SoundEvents.ENTITY_CHICKEN_EGG, SoundCategory.BLOCKS, 0.5F, 1.2F);
                    world.playSound(null, pos, SoundEvents.BLOCK_BEEHIVE_SHEAR, SoundCategory.BLOCKS, 0.2F, 0.5F);
                    world.spawnEntity(itemEntity);
                } else {
                    spawnParticles(world, pos, false);
                    world.playSound(null, pos, SoundEvents.BLOCK_BEEHIVE_SHEAR, SoundCategory.BLOCKS, 0.5F, 0.5F);
                }
            } else {
                spawnParticles(world, pos, false);
                world.playSound(null, pos, SoundEvents.BLOCK_BEEHIVE_SHEAR, SoundCategory.BLOCKS, 0.5F, 0.5F);
            }
        }

        BlockState blockState = state.with(MOSS_LEVEL, 0).with(FACING, state.get(FACING));
        world.setBlockState(pos, blockState);
    }

    private static ItemStack getItemFromLootTable(BlockState state, World world, BlockPos pos, PlayerEntity player, StatueBlockMobType type) {
        Identifier identifier = type.getLootTable();
        LootTable lootTable = world.getServer().getLootManager().getTable(identifier);
        LootContext.Builder builder = new LootContext.Builder((ServerWorld) world).parameter(LootContextParameters.BLOCK_STATE, state).parameter(LootContextParameters.ORIGIN, new Vec3d(pos.getX(), pos.getY(), pos.getZ())).parameter(LootContextParameters.TOOL, player.getMainHandStack()).random(world.random);
        List<ItemStack> loots = lootTable.generateLoot(builder.build(LootContextTypes.BLOCK));
        ItemStack loot = loots.get(world.random.nextInt(loots.size()));
        loot.setCount(loot.getCount());

        return loot;
    }

    private static void spawnParticles(World world, BlockPos pos, boolean isDropSuccessful) {
        DustParticleEffect MOSS = new DustParticleEffect(0.3F, 0.4F, 0.0F, 1.5F);

        double x = (double)pos.getX() + 0.5F;
        double y = (double)pos.getY() + 0.5F;
        double z = (double)pos.getZ() + 0.5F;

        double deltaX = 0.25D;
        double deltaY = 0.25D;
        double deltaZ = 0.25D;

        if (isDropSuccessful) {
            ((ServerWorld) world).spawnParticles(ParticleTypes.HAPPY_VILLAGER, x, y, z, 8, deltaX, deltaY, deltaZ, 0.0D);
        } else {
            ((ServerWorld) world).spawnParticles(MOSS, x, y, z, 8, deltaX, deltaY, deltaZ, 0.0D);
        }
    }

    public boolean hasRandomTicks(BlockState state) {
        return !this.isFullyCoveredInMoss(state);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (this.getCurrentMossLevel(state) < this.getMaxMossLevel()) {
            if ((random.nextInt((int)(100.0F) + 1) == 0)) {
                world.setBlockState(pos, state.with(MOSS_LEVEL, this.getCurrentMossLevel(state) + 1).with(FACING, state.get(FACING)), 2);
            }
        }
    }

    private VoxelShape setOutlineShape(Direction direction) {
        switch (direction) {
            default:
            case NORTH:
                return mobType.getOutlineShape(StatueBlockMobType.NORTH);
            case SOUTH:
                return mobType.getOutlineShape(StatueBlockMobType.SOUTH);
            case EAST:
                return mobType.getOutlineShape(StatueBlockMobType.EAST);
            case WEST:
                return mobType.getOutlineShape(StatueBlockMobType.WEST);
        }
    }

    private VoxelShape setCollisionShape(Direction direction) {
        switch (direction) {
            default:
            case NORTH:
                return mobType.getCollisionShape(StatueBlockMobType.NORTH);
            case SOUTH:
                return mobType.getCollisionShape(StatueBlockMobType.SOUTH);
            case EAST:
                return mobType.getCollisionShape(StatueBlockMobType.EAST);
            case WEST:
                return mobType.getCollisionShape(StatueBlockMobType.WEST);
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (mobType.getOutlineShapes() != null) {
            return setOutlineShape(getDirection(state));
        }
        return StatueVoxelShapes.DEFAULT_OUTLINE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {

        if (mobType.getCollisionShapes() != null) {
            return setCollisionShape(getDirection(state));
        }
        else if (mobType.getOutlineShapes() != null) {
            return setOutlineShape(getDirection(state));
        }
        return StatueVoxelShapes.DEFAULT_COLLISIONS;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, MOSS_LEVEL);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockView world) {
        return new StatueBlockEntity(mobType.getBlockEntityType());
    }

    static {
        MOSS_LEVEL = AtbywProperties.MOSS_LEVEL;

    }
}
