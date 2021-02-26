package net.azagwen.atbyw.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
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
}
