package net.azagwen.atbyw.client.render;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;

import java.util.List;

public class AtbywRenderLayer extends RenderLayer {
    private static final RenderLayer SOLID_EMISSIVE;

    public AtbywRenderLayer(String name, VertexFormat vertexFormat, VertexFormat.DrawMode drawMode, int expectedBufferSize, boolean hasCrumbling, boolean translucent, Runnable startAction, Runnable endAction) {
        super(name, vertexFormat, drawMode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
    }

    public static List<RenderLayer> getBlockLayers() {
        return Lists.newArrayList(
                getSolid(),
                getSolidEmissive(),
                getCutoutMipped(),
                getCutout(),
                getTranslucent(),
                getTripwire()
        );
    }

    public static RenderLayer getSolidEmissive() {
        return SOLID_EMISSIVE;
    }

    static {
        SOLID_EMISSIVE = of("solid_emissive", VertexFormats.POSITION_COLOR_TEXTURE_LIGHT_NORMAL, VertexFormat.DrawMode.QUADS, 2097152, true, false, RenderLayer.MultiPhaseParameters.builder().lightmap(ENABLE_LIGHTMAP).shader(BEACON_BEAM_SHADER).texture(MIPMAP_BLOCK_ATLAS_TEXTURE).build(true));
    }
}
