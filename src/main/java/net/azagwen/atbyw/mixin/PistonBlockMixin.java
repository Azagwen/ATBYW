package net.azagwen.atbyw.mixin;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.azagwen.atbyw.blocks.piston.PistonDuck;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.enums.PistonType;
import net.minecraft.block.piston.PistonHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static net.minecraft.block.Block.dropStacks;
import static net.minecraft.state.property.Properties.FACING;
import static net.azagwen.atbyw.blocks.piston.PistonHelper.*;

@Mixin(PistonBlock.class)
public class PistonBlockMixin implements PistonDuck {
    private int type;

    private Block getPistonHeadBlock() {
        return getWoodType(this.type, PISTON_HEAD);
    }

    private Block getMovingPistonBlock() {
//        return getWoodType(this.type, MOVING_PISTON);
        return Blocks.MOVING_PISTON;
    }

    public PistonBlockMixin(boolean sticky) {
        this.sticky = sticky;
    }

    @Inject(method = "move(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;Z)Z", at =
    @At(value = "HEAD"), cancellable = true)
    private void move(World world, BlockPos pos, Direction dir, boolean retract, CallbackInfoReturnable<Boolean> cbir) {
        BlockPos blockPos = pos.offset(dir);

        if (!retract && world.getBlockState(blockPos).isOf(getPistonHeadBlock())) {
            world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), 20);
        }

        PistonHandler pistonHandler = new PistonHandler(world, pos, dir, retract);
        if (!pistonHandler.calculatePush()) {
            cbir.setReturnValue(false);
        } else {
            Map<BlockPos, BlockState> map = Maps.newHashMap();
            List<BlockPos> list = pistonHandler.getMovedBlocks();
            List<BlockState> list2 = Lists.newArrayList();

            for(int i = 0; i < list.size(); ++i) {
                BlockPos blockPos2 = (BlockPos)list.get(i);
                BlockState blockState = world.getBlockState(blockPos2);
                list2.add(blockState);
                map.put(blockPos2, blockState);
            }

            List<BlockPos> list3 = pistonHandler.getBrokenBlocks();
            BlockState[] blockStates = new BlockState[list.size() + list3.size()];
            Direction direction = retract ? dir : dir.getOpposite();
            int j = 0;

            int l;
            BlockPos blockPos4;
            BlockState blockState8;
            for(l = list3.size() - 1; l >= 0; --l) {
                blockPos4 = (BlockPos)list3.get(l);
                blockState8 = world.getBlockState(blockPos4);
                BlockEntity blockEntity = blockState8.getBlock().hasBlockEntity() ? world.getBlockEntity(blockPos4) : null;
                dropStacks(blockState8, world, blockPos4, blockEntity);
                world.setBlockState(blockPos4, Blocks.AIR.getDefaultState(), 18);
                blockStates[j++] = blockState8;
            }

            for(l = list.size() - 1; l >= 0; --l) {
                blockPos4 = (BlockPos)list.get(l);
                blockState8 = world.getBlockState(blockPos4);
                blockPos4 = blockPos4.offset(direction);
                map.remove(blockPos4);
                world.setBlockState(blockPos4, getMovingPistonBlock().getDefaultState().with(FACING, dir), 68);
                world.setBlockEntity(blockPos4, PistonExtensionBlock.createBlockEntityPiston((BlockState)list2.get(l), dir, retract, false));
                blockStates[j++] = blockState8;
            }

            if (retract) {
                PistonType pistonType = this.sticky ? PistonType.STICKY : PistonType.DEFAULT;
                BlockState blockState4 = getPistonHeadBlock().getDefaultState().with(PistonHeadBlock.FACING, dir).with(PistonHeadBlock.TYPE, pistonType);
                blockState8 = getMovingPistonBlock().getDefaultState().with(PistonExtensionBlock.FACING, dir).with(PistonExtensionBlock.TYPE, this.sticky ? PistonType.STICKY : PistonType.DEFAULT);
                map.remove(blockPos);
                world.setBlockState(blockPos, blockState8, 68);
                world.setBlockEntity(blockPos, PistonExtensionBlock.createBlockEntityPiston(blockState4, dir, true, true));
            }

            BlockState blockState6 = Blocks.AIR.getDefaultState();
            Iterator var25 = map.keySet().iterator();

            while(var25.hasNext()) {
                BlockPos blockPos5 = (BlockPos)var25.next();
                world.setBlockState(blockPos5, blockState6, 82);
            }

            var25 = map.entrySet().iterator();

            BlockPos blockPos7;
            while(var25.hasNext()) {
                Map.Entry<BlockPos, BlockState> entry = (Map.Entry)var25.next();
                blockPos7 = (BlockPos)entry.getKey();
                BlockState blockState7 = (BlockState)entry.getValue();
                blockState7.prepare(world, blockPos7, 2);
                blockState6.updateNeighbors(world, blockPos7, 2);
                blockState6.prepare(world, blockPos7, 2);
            }

            j = 0;

            int n;
            for(n = list3.size() - 1; n >= 0; --n) {
                blockState8 = blockStates[j++];
                blockPos7 = (BlockPos)list3.get(n);
                blockState8.prepare(world, blockPos7, 2);
                world.updateNeighborsAlways(blockPos7, blockState8.getBlock());
            }

            for(n = list.size() - 1; n >= 0; --n) {
                world.updateNeighborsAlways((BlockPos)list.get(n), blockStates[j++].getBlock());
            }

            if (retract) {
                world.updateNeighborsAlways(blockPos, getPistonHeadBlock());
            }

            cbir.setReturnValue(true);
        }
    }

    @Shadow
    private final boolean sticky;

    @Override
    public void setType(int type) {
        this.type = type;
    }
}
