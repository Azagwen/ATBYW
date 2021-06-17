package net.azagwen.atbyw.block.statues;

import net.azagwen.atbyw.block.state.AtbywProperties;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.client.util.ParticleUtil;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class StatueBlock extends AbstractStatueBlock implements StatueBlockMethods, Waterloggable {
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final IntProperty MOSS_LEVEL;
    private final StatueBlockMobType mobType;
    private final boolean hasLoots;
    private final List<Block> waxedStates;

    public StatueBlock(List<Block> waxedStates, StatueBlockMobType mobType, Settings settings) {
        super(mobType, settings.nonOpaque());
        this.mobType = mobType;
        this.hasLoots = mobType.getLootTable() != null;
        this.waxedStates = waxedStates;
        waxedStates.add(this);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(MOSS_LEVEL, 0).with(WATERLOGGED, false));
    }

    private BlockState getWaxedState(BlockState state) {
        for (var currentBlock : this.waxedStates) {
            if (currentBlock instanceof WaxedStatueBlock) {
                if (((WaxedStatueBlock) currentBlock).getMossLevel() == this.getCurrentMossLevel(state)) {
                    return currentBlock.getDefaultState()
                            .with(WaxedStatueBlock.WATERLOGGED, state.get(WATERLOGGED))
                            .with(WaxedStatueBlock.FACING, state.get(FACING));
                }
            }
        }
        return this.waxedStates.get(0).getDefaultState();
    }

    @Override
    public BlockState getResetState(BlockState state) {
        return state.with(MOSS_LEVEL, 0).with(FACING, state.get(FACING));
    }

    @Override
    public int getMaxMossLevel() {
        return 4;
    }

    private int getCurrentMossLevel(BlockState state) {
        return state.get(MOSS_LEVEL);
    }

    private boolean isFullyCoveredInMoss(BlockState state) {
        return state.get(MOSS_LEVEL) >= this.getMaxMossLevel();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (player.getMainHandStack().getItem().equals(Items.HONEYCOMB)) {
            int waxAmountRequired = 1;

            if (player.getMainHandStack().getCount() >= waxAmountRequired) {
                if (!player.isCreative()) {
                    player.getMainHandStack().decrement(waxAmountRequired);
                }
                world.setBlockState(pos, this.getWaxedState(state));
                world.playSound(null, pos, SoundEvents.ITEM_HONEYCOMB_WAX_ON, SoundCategory.BLOCKS, 1.0F, 1.0F);
                player.sendMessage(new TranslatableText("block.atbyw." + mobType.getName() + ".waxed"), true);
                ParticleUtil.spawnParticle(world, pos, ParticleTypes.WAX_ON, UniformIntProvider.create(3, 5));
                return ActionResult.success(world.isClient);
            } else {
                player.sendMessage(new TranslatableText("block.atbyw." + mobType.getName() + ".cannot_be_waxed", waxAmountRequired), true);
                return ActionResult.CONSUME;
            }
        } else {
            int mossPercentage = (state.get(MOSS_LEVEL) * 25);

            if (this.isFullyCoveredInMoss(state)) {
                this.resetStatue(state, world, pos, player, mobType, hasLoots);
                player.sendMessage(new TranslatableText("block.atbyw." + mobType.getName() + ".ready", mossPercentage), true);
                return ActionResult.success(world.isClient);
            } else {
                player.sendMessage(new TranslatableText("block.atbyw." + mobType.getName() + ".not_ready", mossPercentage), true);
                return ActionResult.CONSUME;
            }
        }
    }

    public void resetStatue(BlockState state, World world, BlockPos pos, PlayerEntity player, StatueBlockMobType type, boolean hasLoots) {
        if (!world.isClient) {
            if (hasLoots) {
                var f = 0.7F;
                var x = (double) pos.getX() + (double) (world.random.nextFloat() * f) + 0.15000000596046448D;
                var y = (double) pos.getY() + (double) (world.random.nextFloat() * f) + 0.06000000238418579D + 0.6D;
                var z = (double) pos.getZ() + (double) (world.random.nextFloat() * f) + 0.15000000596046448D;

                var pickedStack = this.getItemFromLootTable(state, world, pos, player, type);
                var itemEntity = new ItemEntity(world, x, y, z, pickedStack);
                itemEntity.setToDefaultPickupDelay();

                if (pickedStack.getCount() > 0 || !(pickedStack.equals(ItemStack.EMPTY))) {
                    this.spawnParticles(world, pos, true);
                    world.playSound(null, pos, SoundEvents.ENTITY_CHICKEN_EGG, SoundCategory.BLOCKS, 0.5F, 1.2F);
                    world.playSound(null, pos, SoundEvents.BLOCK_BEEHIVE_SHEAR, SoundCategory.BLOCKS, 0.2F, 0.5F);
                    world.spawnEntity(itemEntity);
                } else {
                    this.spawnParticles(world, pos, false);
                    world.playSound(null, pos, SoundEvents.BLOCK_BEEHIVE_SHEAR, SoundCategory.BLOCKS, 0.5F, 0.5F);
                }
            } else {
                this.spawnParticles(world, pos, false);
                world.playSound(null, pos, SoundEvents.BLOCK_BEEHIVE_SHEAR, SoundCategory.BLOCKS, 0.5F, 0.5F);
            }
        }

        world.setBlockState(pos, getResetState(state));
    }

    protected ItemStack getItemFromLootTable(BlockState state, World world, BlockPos pos, PlayerEntity player, StatueBlockMobType type) {
        var identifier = type.getLootTable();
        var lootTable = world.getServer().getLootManager().getTable(identifier);

        LootContext.Builder builder = new LootContext.Builder((ServerWorld) world)
                .parameter(LootContextParameters.BLOCK_STATE, state)
                .parameter(LootContextParameters.ORIGIN, new Vec3d(pos.getX(), pos.getY(), pos.getZ()))
                .parameter(LootContextParameters.TOOL, player.getMainHandStack())
                .random(world.random);


        var loots = lootTable.generateLoot(builder.build(LootContextTypes.BLOCK));
        var loot = ItemStack.EMPTY;
        if (loots.size() > 0) {
            int randomInt = world.random.nextInt(loots.size());
            loot = loots.get(randomInt);
        }

        return loot;
    }

    protected void spawnParticles(World world, BlockPos pos, boolean isDropSuccessful) {
        var MOSS = new DustParticleEffect(new Vec3f(0.3F, 0.4F, 0.0F), 1.5F);

        if (isDropSuccessful) {
            ParticleUtil.spawnParticle(world, pos, ParticleTypes.HAPPY_VILLAGER, UniformIntProvider.create(5, 8));
        } else {
            ParticleUtil.spawnParticle(world, pos, MOSS, UniformIntProvider.create(5, 8));
        }
    }

    public boolean hasRandomTicks(BlockState state) {
        return !this.isFullyCoveredInMoss(state);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (this.getCurrentMossLevel(state) < this.getMaxMossLevel()) {
            if (!world.isRaining()) {
                if ((random.nextInt((int) (100.0F) + 1) == 0)) {
                    world.setBlockState(pos, state.with(MOSS_LEVEL, this.getCurrentMossLevel(state) + 1).with(FACING, state.get(FACING)), 2);
                }
            } else {
                if ((random.nextInt((int) (20.0F) + 1) == 0)) {
                    world.setBlockState(pos, state.with(MOSS_LEVEL, this.getCurrentMossLevel(state) + 1).with(FACING, state.get(FACING)), 2);
                }
            }
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(MOSS_LEVEL);
    }

    static {
        MOSS_LEVEL = AtbywProperties.MOSS_LEVEL;
    }
}
