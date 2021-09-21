package net.azagwen.atbyw.block;

import net.azagwen.atbyw.block.entity.CanvasBlockEntity;
import net.azagwen.atbyw.block.registry.AtbywBlocks;
import net.azagwen.atbyw.block.registry.DecorationBlockRegistry;
import net.azagwen.atbyw.item.ColorizerItem;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CanvasBlock extends BlockWithEntity {
    public final boolean glowing;

    public CanvasBlock(boolean glowing, Settings settings) {
        super(settings);
        this.glowing = glowing;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CanvasBlockEntity(pos, state);
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        var itemStack = super.getPickStack(world, pos, state);
        var entity = (CanvasBlockEntity) world.getBlockEntity(pos);

        if (entity.getColor() != ColorizerItem.DEFAULT_COLOR) {
            var nbt = new NbtCompound();
            nbt.putInt("color", entity.getColor());
            if (!nbt.isEmpty()) {
                itemStack.putSubTag("display", nbt);
            }
        }

        return itemStack;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!glowing) {
            if (player.getStackInHand(hand).getItem() == Items.GLOW_INK_SAC) {
                var hitPos = hit.getBlockPos();
                var oldColorNbt = world.getBlockEntity(hitPos).writeNbt(new NbtCompound());

                world.setBlockState(hitPos, DecorationBlockRegistry.GLOWING_CANVAS_BLOCK.getDefaultState());
                world.getBlockEntity(hitPos).readNbt(oldColorNbt);
                world.getBlockEntity(hitPos).toUpdatePacket();
                world.playSound(player, hitPos, SoundEvents.ITEM_GLOW_INK_SAC_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);

                return ActionResult.success(world.isClient);
            }
        }
        return ActionResult.PASS;
    }
}
