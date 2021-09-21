package net.azagwen.atbyw.item;

import net.azagwen.atbyw.block.registry.AtbywBlocks;
import net.azagwen.atbyw.block.ShroomStickBlock;
import net.azagwen.atbyw.item.entity.ShroomStickEntity;
import net.azagwen.atbyw.main.Tags;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class ShroomStickItem extends Item{

    public ShroomStickItem(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.random.nextFloat() * 0.4F + 0.8F));
        player.getItemCooldownManager().set(this, 6);
        if (!world.isClient) {
            ShroomStickEntity entity = new ShroomStickEntity(world, player);
            entity.setItem(itemStack);
            entity.setProperties(player, player.getPitch(), player.getYaw(), 0.0F, 1.5F, 1.0F);
            world.spawnEntity(entity);
        }

        player.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!player.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        BlockPos hitpos = context.getBlockPos();

        return land(world, player, hitpos, context);
    }

    private ActionResult land(World world, PlayerEntity player, BlockPos hitPos, ItemUsageContext context) {
        Hand hand = context.getHand();
        BlockPos hitPosUp = hitPos.up();
        BlockState state = world.getBlockState(hitPosUp);

        boolean waterLogged = world.getBlockState(hitPosUp).getFluidState().getFluid() == Fluids.WATER;

        if (context.getSide() == Direction.UP && world.getBlockState(hitPos).isSideSolidFullSquare(world, hitPos, Direction.UP)) {
            if (state.isAir() || state.isIn(Tags.BlockTags.SHROOMSTICK_REPLACEABLE_GROUND) || state.isOf(Blocks.WATER) || state.isIn(Tags.BlockTags.SHROOMSTICK_REPLACEABLE_WATER)) {
                placeBlock(world, player, hitPosUp, waterLogged, hand);

                return ActionResult.success(world.isClient());
            } else {
                super.use(world, player, hand);
                return ActionResult.PASS;
            }
        } else {
            super.use(world, player, hand);
            return ActionResult.PASS;
        }
    }

    private void placeBlock(World world, PlayerEntity player, BlockPos hitPos, boolean waterLogged, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);

        world.playSound(null, hitPos, SoundEvents.BLOCK_SHROOMLIGHT_PLACE, SoundCategory.BLOCKS, 0.5F, 2.0F);
        world.breakBlock(hitPos, true, player);
        world.setBlockState(hitPos, AtbywBlocks.SHROOMSTICK.getDefaultState().with(ShroomStickBlock.WATERLOGGED, waterLogged));

        if (!player.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
    }
}
