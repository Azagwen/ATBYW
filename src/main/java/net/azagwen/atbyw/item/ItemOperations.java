package net.azagwen.atbyw.item;

import com.google.common.collect.Table;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.PlaySoundIdS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ItemOperations {

    public static void replaceBlock(World world, ServerPlayerEntity player, BlockPos pos, Direction side, BlockState oldState, Table.Cell<Item, Block, ItemOperationResult> operation) {
        var newBlock = operation.getValue().result();
        var lootItem = operation.getValue().loot();
        var sound = operation.getValue().sound();

        world.setBlockState(pos, newBlock.getStateWithProperties(oldState));

        if (sound != null) {
            if (!world.isClient) {
                var vec3D = new Vec3d(pos.getX(), pos.getY(), pos.getZ());
                player.networkHandler.sendPacket(new PlaySoundIdS2CPacket(sound, SoundCategory.BLOCKS, vec3D, 1.0F, 1.0F));
            }
        }
        if (lootItem != null) {
            Block.dropStack(world, pos, side, new ItemStack(lootItem));
        }
    }
}
