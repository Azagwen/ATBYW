package net.azagwen.atbyw.mixin.bookshelf_behavior;

import net.azagwen.atbyw.main.Tags;
import net.minecraft.block.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(EnchantingTableBlock.class)
public class EnchantingTableBlockMixin extends Block {

    @Inject(method = "randomDisplayTick", at = @At("HEAD"))
    private void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random, CallbackInfo ci) {
        super.randomDisplayTick(state, world, pos, random);

        for(int i = -2; i <= 2; ++i) {
            for(int j = -2; j <= 2; ++j) {
                if (i > -2 && i < 2 && j == -1) {
                    j = 2;
                }

                if (random.nextInt(16) == 0) {
                    for(int k = 0; k <= 1; ++k) {
                        var blockState = world.getBlockState(pos.add(i, k, j));
                        if (blockState.isIn(Tags.BlockTags.BOOKSHELVES_COMMON)) {
                            if (!world.isAir(pos.add(i / 2, 0, j / 2))) {
                                break;
                            }

                            world.addParticle(ParticleTypes.ENCHANT, (double)pos.getX() + 0.5D, (double)pos.getY() + 2.0D, (double)pos.getZ() + 0.5D, (double)((float)i + random.nextFloat()) - 0.5D, ((float)k - random.nextFloat() - 1.0F), (double)((float)j + random.nextFloat()) - 0.5D);
                        }
                    }
                }
            }
        }
    }

    protected EnchantingTableBlockMixin(Settings settings) {
        super(settings);
    }
}
