package net.azagwen.atbyw.items;

import net.azagwen.atbyw.blocks.AtbywBlocks;
import net.azagwen.atbyw.blocks.ShroomStickBlock;
import net.azagwen.atbyw.misc.AtbywTags;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.property.Properties;
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
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
        player.getItemCooldownManager().set(this, 6);
        if (!world.isClient) {
            ShroomStickEntity entity = new ShroomStickEntity(world, player);
            entity.setItem(itemStack);
            entity.setProperties(player, player.pitch, player.yaw, 0.0F, 1.5F, 1.0F);
            world.spawnEntity(entity);
        }

        player.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!player.abilities.creativeMode) {
            itemStack.decrement(1);
        }

        return TypedActionResult.success(itemStack, world.isClient());
    }

    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos().up();

        return land(world, player, blockPos, context);
    }

    private ActionResult land(World world, PlayerEntity player, BlockPos hitPos, ItemUsageContext context) {
        boolean waterLogged = world.getBlockState(hitPos).getFluidState().getFluid() == Fluids.WATER;
        BlockState state = world.getBlockState(hitPos);

        if (context.getSide() == Direction.UP) {
            if (state.isAir() || state.isIn(AtbywTags.SHROOMSTICK_REPLACEABLE_GROUND) || state.isOf(Blocks.WATER) || state.isIn(AtbywTags.SHROOMSTICK_REPLACEABLE_WATER)) {
                placeBlock(world, player, hitPos, waterLogged);

                return ActionResult.success(world.isClient());
            } else {
                super.use(world, player, context.getHand());
                return ActionResult.PASS;
            }
        } else {
            super.use(world, player, context.getHand());
            return ActionResult.PASS;
        }
    }

    private void placeBlock(World world, PlayerEntity player, BlockPos hitPos, boolean waterLogged) {
        world.playSound(null, hitPos, SoundEvents.BLOCK_SHROOMLIGHT_PLACE, SoundCategory.BLOCKS, 0.5F, 2.0F);
        world.breakBlock(hitPos, true, player);
        world.setBlockState(hitPos, AtbywBlocks.SHROOMSTICK.getDefaultState().with(ShroomStickBlock.WATERLOGGED, waterLogged));
    }
}
