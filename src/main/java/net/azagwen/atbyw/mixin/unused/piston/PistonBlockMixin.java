package net.azagwen.atbyw.mixin.unused.piston;

import net.azagwen.atbyw.block.piston.PistonWoodTypes;
import net.azagwen.atbyw.block.piston.PistonDuck;
import net.minecraft.block.*;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static net.minecraft.block.Block.dropStacks;

@Mixin(PistonBlock.class)
public class PistonBlockMixin implements PistonDuck {
    @Shadow public static final BooleanProperty EXTENDED;
    @Shadow private final boolean sticky;
    private String type;

    private Block getPistonHeadBlock() {
        if (this.type == null)
            return PistonWoodTypes.valueOf("OAK").getPistonHead();
        return PistonWoodTypes.valueOf(type).getPistonHead();
    }

    private Block getMovingPistonBlock() {
//        return PistonWoodTypes.valueOf(type).getMovingPiston();
        return Blocks.MOVING_PISTON;
    }

    public PistonBlockMixin(boolean sticky) {
        this.sticky = sticky;
    }
//
//    @Inject(method = "isMovable(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;ZLnet/minecraft/util/math/Direction;)Z", at =
//    @At(value = "HEAD"), cancellable = true)
//    private static void isMovable(BlockState blockState, World world, BlockPos blockPos, Direction direction, boolean canBreak, Direction pistonDir, CallbackInfoReturnable<Boolean> cbir) {
//        if (blockPos.getY() >= 0 && blockPos.getY() <= world.getHeight() - 1 && world.getWorldBorder().contains(blockPos)) {
//            if (blockState.isAir()) {
//                cbir.setReturnValue(true);
//            } else if (!blockState.isOf(Blocks.OBSIDIAN) && !blockState.isOf(Blocks.CRYING_OBSIDIAN) && !blockState.isOf(Blocks.RESPAWN_ANCHOR)) {
//                if (direction == Direction.DOWN && blockPos.getY() == 0) {
//                    cbir.setReturnValue(false);
//                } else if (direction == Direction.UP && blockPos.getY() == world.getHeight() - 1) {
//                    cbir.setReturnValue(false);
//                } else {
//                    if (!blockState.isIn(AtbywTags.PISTONS)) {
//                        if (blockState.getHardness(world, blockPos) == -1.0F) {
//                            cbir.setReturnValue(false);
//                        }
//
//                        switch(blockState.getPistonBehavior()) {
//                            case BLOCK:
//                                cbir.setReturnValue(false);
//                            case DESTROY:
//                                cbir.setReturnValue(canBreak);
//                            case PUSH_ONLY:
//                                cbir.setReturnValue(direction == pistonDir);
//                        }
//                    } else if (blockState.get(EXTENDED)) {
//                        cbir.setReturnValue(false);
//                    }
//
//                    cbir.setReturnValue(!blockState.hasBlockEntity());
//                }
//            } else {
//                cbir.setReturnValue(false);
//            }
//        } else {
//            cbir.setReturnValue(false);
//        }
//    }
//
//    @Inject(method = "move(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;Z)Z", at =
//    @At(value = "HEAD"), cancellable = true)
//    private void move(World world, BlockPos pos, Direction dir, boolean retract, CallbackInfoReturnable<Boolean> cbir) {
//        BlockPos blockPos = pos.offset(dir);
//
//        if (!retract && world.getBlockState(blockPos).isOf(getPistonHeadBlock())) {
//            world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), 20);
//        }
//
//        PistonHandler pistonHandler = new PistonHandler(world, pos, dir, retract);
//        if (!pistonHandler.calculatePush()) {
//            cbir.setReturnValue(false);
//        } else {
//            Map<BlockPos, BlockState> map = Maps.newHashMap();
//            List<BlockPos> list = pistonHandler.getMovedBlocks();
//            List<BlockState> list2 = Lists.newArrayList();
//
//            for(int i = 0; i < list.size(); ++i) {
//                BlockPos blockPos2 = (BlockPos)list.get(i);
//                BlockState blockState = world.getBlockState(blockPos2);
//                list2.add(blockState);
//                map.put(blockPos2, blockState);
//            }
//
//            List<BlockPos> list3 = pistonHandler.getBrokenBlocks();
//            BlockState[] blockStates = new BlockState[list.size() + list3.size()];
//            Direction direction = retract ? dir : dir.getOpposite();
//            int j = 0;
//
//            int l;
//            BlockPos blockPos4;
//            BlockState blockState8;
//            for(l = list3.size() - 1; l >= 0; --l) {
//                blockPos4 = (BlockPos)list3.get(l);
//                blockState8 = world.getBlockState(blockPos4);
//                BlockEntity blockEntity = blockState8.hasBlockEntity() ? world.getBlockEntity(blockPos4) : null;
//                dropStacks(blockState8, world, blockPos4, blockEntity);
//                world.setBlockState(blockPos4, Blocks.AIR.getDefaultState(), 18);
//                blockStates[j++] = blockState8;
//            }
//
//            for(l = list.size() - 1; l >= 0; --l) {
//                blockPos4 = (BlockPos)list.get(l);
//                blockState8 = world.getBlockState(blockPos4);
//                blockPos4 = blockPos4.offset(direction);
//                map.remove(blockPos4);
//                world.setBlockState(blockPos4, getMovingPistonBlock().getDefaultState().with(FACING, dir), 68);
//                world.addBlockEntity(blockPos4, PistonExtensionBlock.createBlockEntityPiston((BlockState)list2.get(l), dir, retract, false));
//                blockStates[j++] = blockState8;
//            }
//
//            if (retract) {
//                PistonType pistonType = this.sticky ? PistonType.STICKY : PistonType.DEFAULT;
//                BlockState blockState4 = getPistonHeadBlock().getDefaultState().with(PistonHeadBlock.FACING, dir).with(PistonHeadBlock.TYPE, pistonType);
//                blockState8 = getMovingPistonBlock().getDefaultState().with(PistonExtensionBlock.FACING, dir).with(PistonExtensionBlock.TYPE, this.sticky ? PistonType.STICKY : PistonType.DEFAULT);
//                map.remove(blockPos);
//                world.setBlockState(blockPos, blockState8, 68);
//                world.addBlockEntity(blockPos, PistonExtensionBlock.createBlockEntityPiston(blockState4, dir, true, true));
//            }
//
//            BlockState blockState6 = Blocks.AIR.getDefaultState();
//            Iterator var25 = map.keySet().iterator();
//
//            while(var25.hasNext()) {
//                BlockPos blockPos5 = (BlockPos)var25.next();
//                world.setBlockState(blockPos5, blockState6, 82);
//            }
//
//            var25 = map.entrySet().iterator();
//
//            BlockPos blockPos7;
//            while(var25.hasNext()) {
//                Map.Entry<BlockPos, BlockState> entry = (Map.Entry)var25.next();
//                blockPos7 = (BlockPos)entry.getKey();
//                BlockState blockState7 = (BlockState)entry.getValue();
//                blockState7.prepare(world, blockPos7, 2);
//                blockState6.updateNeighbors(world, blockPos7, 2);
//                blockState6.prepare(world, blockPos7, 2);
//            }
//
//            j = 0;
//
//            int n;
//            for(n = list3.size() - 1; n >= 0; --n) {
//                blockState8 = blockStates[j++];
//                blockPos7 = (BlockPos)list3.get(n);
//                blockState8.prepare(world, blockPos7, 2);
//                world.updateNeighborsAlways(blockPos7, blockState8.getBlock());
//            }
//
//            for(n = list.size() - 1; n >= 0; --n) {
//                world.updateNeighborsAlways((BlockPos)list.get(n), blockStates[j++].getBlock());
//            }
//
//            if (retract) {
//                world.updateNeighborsAlways(blockPos, getPistonHeadBlock());
//            }
//
//            cbir.setReturnValue(true);
//        }
//    }
//
    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return this.type;
    }

    static {
        EXTENDED = Properties.EXTENDED;
    }
}
