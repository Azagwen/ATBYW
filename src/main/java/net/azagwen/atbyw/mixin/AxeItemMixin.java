package net.azagwen.atbyw.mixin;

import com.google.common.collect.Maps;
import net.azagwen.atbyw.block.AtbywBlocks;
import net.azagwen.atbyw.block.slabs.PillarSlabBlock;
import net.azagwen.atbyw.client.ItemOperationDecoder;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Mixin(AxeItem.class)
public class AxeItemMixin {
    private static final Map<Block, Block> LOG_SLABS = Maps.newHashMap();

    private static InputStream getResource(Identifier id) {
        var inputStream = (InputStream) null;
        try {
            var client = MinecraftClient.getInstance();
            inputStream = client.getResourceManager().getResource(id).getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputStream;
    }

    @Inject(at = @At("HEAD"), method = "useOnBlock", cancellable = true)
    public void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        var world = context.getWorld();
        var player = context.getPlayer();
        var pos = context.getBlockPos();
        var hitBlock = world.getBlockState(pos).getBlock();

        //Pillar Slabs Stripping
        if (hitBlock instanceof PillarSlabBlock) {
            var resource = getResource(new Identifier("atbyw", "data/custom/strip_log_slabs"));
            var decoder = new ItemOperationDecoder(resource, "a").readBlockToBlockOp();

            for (var slab : decoder.entrySet()) {
                if (slab.getKey() == hitBlock) {
                    var bottomType = world.getBlockState(pos).get(PillarSlabBlock.BOTTOM_TYPE);
                    var topType = world.getBlockState(pos).get(PillarSlabBlock.TOP_TYPE);
                    var type = world.getBlockState(pos).get(PillarSlabBlock.TYPE);
                    var waterLogged = world.getBlockState(pos).get(PillarSlabBlock.WATERLOGGED);

                    world.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    world.setBlockState(pos, slab.getValue().getDefaultState()
                            .with(PillarSlabBlock.BOTTOM_TYPE, bottomType)
                            .with(PillarSlabBlock.TOP_TYPE, topType)
                            .with(PillarSlabBlock.TYPE, type)
                            .with(PillarSlabBlock.WATERLOGGED, waterLogged)
                    );
                    cir.setReturnValue(ActionResult.success(world.isClient));
                }
            }
        }
    }

    static {
        LOG_SLABS.put(AtbywBlocks.OAK_LOG_SLAB, AtbywBlocks.STRIPPED_OAK_LOG_SLAB);
        LOG_SLABS.put(AtbywBlocks.SPRUCE_LOG_SLAB, AtbywBlocks.STRIPPED_SPRUCE_LOG_SLAB);
        LOG_SLABS.put(AtbywBlocks.BIRCH_LOG_SLAB, AtbywBlocks.STRIPPED_BIRCH_LOG_SLAB);
        LOG_SLABS.put(AtbywBlocks.JUNGLE_LOG_SLAB, AtbywBlocks.STRIPPED_JUNGLE_LOG_SLAB);
        LOG_SLABS.put(AtbywBlocks.ACACIA_LOG_SLAB, AtbywBlocks.STRIPPED_ACACIA_LOG_SLAB);
        LOG_SLABS.put(AtbywBlocks.DARK_OAK_LOG_SLAB, AtbywBlocks.STRIPPED_DARK_OAK_LOG_SLAB);
        LOG_SLABS.put(AtbywBlocks.CRIMSON_STEM_SLAB, AtbywBlocks.STRIPPED_CRIMSON_STEM_SLAB);
        LOG_SLABS.put(AtbywBlocks.WARPED_STEM_SLAB, AtbywBlocks.STRIPPED_WARPED_STEM_SLAB);
    }
}
