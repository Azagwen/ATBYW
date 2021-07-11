package net.azagwen.atbyw.block.extensions;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Set;
import java.util.function.Function;

public abstract class BlockExt extends Block {
    private final Function<UseAction, ActionResult> onUseAction;

    protected BlockExt(Settings settings) {
        this(null, null, settings);
    }

    protected BlockExt(Set<Block> set, Settings settings) {
        this(set, null, settings);
    }

    protected BlockExt(Set<Block> set, Function<UseAction, ActionResult> onUseAction, Settings settings) {
        super(settings);
        if (set != null) {
            set.add(this);
        }
        this.onUseAction = onUseAction;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (this.onUseAction != null) {
            return this.onUseAction.apply(new UseAction(state, world, pos, player, hand, hit));
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
}
