package net.azagwen.atbyw.datagen.arrp;

import net.azagwen.atbyw.main.AtbywMain;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.models.JModel;
import net.devtech.arrp.json.models.JTextures;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.registry.Registry;

import static net.devtech.arrp.json.blockstate.JState.*;

public class BlockModels {

    public static JTextures getPillarSlabTexture(RuntimeResourcePack pack, String namespace, String path, boolean horizontal) {
        var slabTextures = JModel.textures()
                .var("v2", namespace + ":block/" + path + "_top")
                .var("v1", namespace + ":block/" + path + "_top")
                .var("side", namespace + ":block/" + path);
        var horizontalPillarSlabTextures = JModel.textures()
                .var("end", namespace + ":block/" + path + "_top")
                .var("side", namespace + ":block/" + path);

        return horizontal ? horizontalPillarSlabTextures : slabTextures;
    }

    public static void createPillarSlabModels(RuntimeResourcePack pack, String namespace, String path) {
        var slabTextures = getPillarSlabTexture(pack, namespace, path, false);
        var horizontalPillarSlabTextures = getPillarSlabTexture(pack, namespace, path, true);

        //Block Model
        pack.addModel(JModel.model().parent("minecraft:block/slab").textures(slabTextures), AtbywMain.id("block/" + path + "_slab"));
        pack.addModel(JModel.model().parent("minecraft:block/slab_top").textures(slabTextures), AtbywMain.id("block/" + path + "_slab_top"));
        pack.addModel(JModel.model().parent("atbyw:block/slab/template_column_slab_horizontal").textures(horizontalPillarSlabTextures), AtbywMain.id("block/" + path + "_slab_horizontal"));
        pack.addModel(JModel.model().parent("atbyw:block/slab/template_column_slab_horizontal_top").textures(horizontalPillarSlabTextures), AtbywMain.id("block/" + path + "_slab_horizontal_top"));

        //Item model
        pack.addModel(JModel.model().parent("atbyw:block/" + path + "_slab"), AtbywMain.id("item/" + path + "_slab"));

        createPillarSlabState(pack, namespace, path);
    }

    public static void createStrippedLogSlabModels(RuntimeResourcePack pack, String namespace, String path) {
        var slabTextures = getPillarSlabTexture(pack, namespace, path, false);
        var horizontalPillarSlabTextures = getPillarSlabTexture(pack, namespace, path, true);

        //Block Model
        pack.addModel(JModel.model().parent("minecraft:block/slab").textures(slabTextures), AtbywMain.id("block/" + path + "_slab"));
        pack.addModel(JModel.model().parent("minecraft:block/slab_top").textures(slabTextures), AtbywMain.id("block/" + path + "_slab_top"));
        pack.addModel(JModel.model().parent("atbyw:block/slab/template_stripped_log_slab_horizontal").textures(horizontalPillarSlabTextures), AtbywMain.id("block/" + path + "_slab_horizontal"));
        pack.addModel(JModel.model().parent("atbyw:block/slab/template_stripped_log_slab_horizontal_top").textures(horizontalPillarSlabTextures), AtbywMain.id("block/" + path + "_slab_horizontal_top"));

        //Item model
        pack.addModel(JModel.model().parent("atbyw:block/" + path + "_slab"), AtbywMain.id("item/" + path + "_slab"));

        createPillarSlabState(pack, namespace, path);
    }

    public static void createPillarSlabState(RuntimeResourcePack pack, String namespace, String path) {
        pack.addBlockState(JState.state(
                JState.multipart(JState.model("atbyw:block/" + path + "_slab_horizontal").y(90)).when(when().add("bottom_type", "x")),
                JState.multipart(JState.model("atbyw:block/" + path + "_slab")).when(when().add("bottom_type", "y")),
                JState.multipart(JState.model("atbyw:block/" + path + "_slab_horizontal")).when(when().add("bottom_type", "z")),
                JState.multipart(JState.model("atbyw:block/" + path + "_slab_horizontal_top").y(90)).when(when().add("top_type", "x")),
                JState.multipart(JState.model("atbyw:block/" + path + "_slab_top")).when(when().add("top_type", "y")),
                JState.multipart(JState.model("atbyw:block/" + path + "_slab_horizontal_top")).when(when().add("top_type", "z"))
        ), AtbywMain.id(path + "_slab"));
    }

    //unused, not a fan of datagen models
    public static void init() {
        //Generate log slab models
        Registry.BLOCK.forEach((block -> {
            if (block instanceof PillarBlock) {
                var id = Registry.BLOCK.getId(block);
                if (id.getNamespace().equals("minecraft")) {
                    if (id.getPath().contains("log") || id.getPath().contains("stem")) {
                        if (id.getPath().contains("stripped")) {
                            BlockModels.createStrippedLogSlabModels(AtbywRRP.ATBYW_RESOURCE_PACK, id.getNamespace(), id.getPath());
                        } else {
                            BlockModels.createPillarSlabModels(AtbywRRP.ATBYW_RESOURCE_PACK, id.getNamespace(), id.getPath());
                        }
                    }
                }
            }
        }));
    }
}
