package net.azagwen.atbyw.dev_tools;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.azagwen.atbyw.main.AtbywMain;
import net.minecraft.client.MinecraftClient;

import java.io.*;

public class AutoJsonWriter {

    public AutoJsonWriter() {
    }

    public void write(String fileName, JsonObject jsonObject) {
        var gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        var client = MinecraftClient.getInstance();
        var file = new File(client.runDirectory, "atbyw_dev_output/" + fileName);

        //Create the File
        if (file.getParentFile().mkdirs()) {
            AtbywMain.MYS_LOGGER.info(file.getParentFile().getAbsolutePath() + " folders created.");
        }
        try {
            if (file.createNewFile()) {
                AtbywMain.MYS_LOGGER.info(file.getName() + " created.");
            }
        } catch (IOException e) {
            AtbywMain.MYS_LOGGER.info("Failed to create " + file.getName());
            e.printStackTrace();
        }

        //Write in the File
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(gson.toJson(jsonObject));
            writer.close();
            AtbywMain.MYS_LOGGER.info("Successfully wrote to " + file.getName());
        } catch (IOException e) {
            AtbywMain.MYS_LOGGER.info("An error occurred writing to " + file.getName());
            e.printStackTrace();
        }
    }

    public void writeAll() {
        AtbywMain.MYS_LOGGER.info("JSON :)");
    }
}
