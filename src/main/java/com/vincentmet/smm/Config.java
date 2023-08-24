package com.vincentmet.smm;

import com.google.gson.*;
import com.vincentmet.smm.lib.Triple;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class Config {
    public static void processJson(JsonObject json){
        ConfigValues.BUTTONS.clear();
        if(json.has("is_logo_enabled")){
            JsonElement jsonElement = json.get("is_logo_enabled");
            if(jsonElement.isJsonPrimitive()){
                JsonPrimitive jsonPrimitive = jsonElement.getAsJsonPrimitive();
                if(jsonPrimitive.isBoolean()){
                    ConfigValues.IS_LOGO_ENABLED = jsonPrimitive.getAsBoolean();
                }else{
                    ConfigValues.IS_LOGO_ENABLED = true;
                }
            }else{
                ConfigValues.IS_LOGO_ENABLED = true;
            }
        }else{
            ConfigValues.IS_LOGO_ENABLED = true;
        }

        if(json.has("button_size_multiplier")){
            JsonElement jsonElement = json.get("button_size_multiplier");
            if(jsonElement.isJsonPrimitive()){
                JsonPrimitive jsonPrimitive = jsonElement.getAsJsonPrimitive();
                if(jsonPrimitive.isNumber()){
                    int value = jsonPrimitive.getAsNumber().intValue();
                    if(value < 1) value = 1;
                    if(value > 3) value = 3;
                    ConfigValues.BUTTON_SIZE_MULTIPLIER = value;
                }else{
                    ConfigValues.BUTTON_SIZE_MULTIPLIER = 1;
                }
            }else{
                ConfigValues.BUTTON_SIZE_MULTIPLIER = 1;
            }
        }else{
            ConfigValues.BUTTON_SIZE_MULTIPLIER = 1;
        }

        if(json.has("buttons")){
            JsonElement jsonElement = json.get("buttons");
            if(jsonElement.isJsonArray()){
                JsonArray jsonArray = jsonElement.getAsJsonArray();
                for (JsonElement element : jsonArray) {
                    if (element.isJsonObject()){
                        JsonObject jsonObject = element.getAsJsonObject();
                        if (jsonObject.has("type") && jsonObject.has("value") && jsonObject.has("texture")){
                            JsonElement typeElement = jsonObject.get("type");
                            JsonElement valueElement = jsonObject.get("value");
                            JsonElement textureElement = jsonObject.get("texture");
                            if(typeElement.isJsonPrimitive() && typeElement.getAsJsonPrimitive().isString() && valueElement.isJsonPrimitive() && valueElement.getAsJsonPrimitive().isString() && textureElement.isJsonPrimitive() && textureElement.getAsJsonPrimitive().isString()){
                                ResourceLocation type = ResourceLocation.tryParse(typeElement.getAsString());
                                String value = valueElement.getAsString();
                                String texture = textureElement.getAsString();
                                ConfigValues.BUTTONS.add(new Triple<>(type, value, texture));
                            }
                        }
                    }
                }
            }else{
                ConfigValues.BUTTON_SIZE_MULTIPLIER = 1;
            }
        }else{
            ConfigValues.BUTTON_SIZE_MULTIPLIER = 1;
        }
    }

    public static JsonObject getJson(){
        JsonObject json = new JsonObject();
        json.addProperty("is_logo_enabled", ConfigValues.IS_LOGO_ENABLED);
        json.addProperty("button_size_multiplier", ConfigValues.BUTTON_SIZE_MULTIPLIER);
        JsonArray array = new JsonArray();
        for(Triple<ResourceLocation, String, String> pair : ConfigValues.BUTTONS){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("type", pair.getLeft().toString());
            jsonObject.addProperty("value", pair.getMiddle());
            jsonObject.addProperty("texture", pair.getRight());
            array.add(jsonObject);
        }
        json.add("buttons", array);
        return json;
    }

    public static void readConfigToMemory(Path path, String file){
        processJson(loadConfig(path, file));
        writeConfigToDisk(path, file);
    }

    public static void writeConfigToDisk(Path path, String file){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String out = gson.toJson(getJson());
        writeTo(path, file, out);
    }

    private static JsonObject loadConfig(Path path, String filename){
        try {
            StringBuilder res = new StringBuilder();
            Files.readAllLines(path.resolve(filename), StandardCharsets.UTF_8).forEach(res::append);
            return new JsonParser().parse(res.toString()).getAsJsonObject();
        }catch (IOException e) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String out = gson.toJson(getDefaultJson());
            writeTo(path, filename, out);
            return loadConfig(path, filename);
        }
    }

    public static void writeTo(Path location, String filename, Object text){
        try{
            if(!location.toFile().exists()){
                location.toFile().mkdirs();
            }
            Files.write(location.resolve(filename), text.toString().getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static JsonObject getDefaultJson(){
        JsonObject json = new JsonObject();
        json.addProperty("is_logo_enabled", true);
        json.addProperty("button_size_multiplier", 1);
        JsonArray array = new JsonArray();

        JsonObject jsonObjectSinglePlayer = new JsonObject();
        jsonObjectSinglePlayer.addProperty("type", "smm:minecraft");
        jsonObjectSinglePlayer.addProperty("value", "singleplayer");
        jsonObjectSinglePlayer.addProperty("texture", "textures/gui/singleplayer_overlay.png");
        array.add(jsonObjectSinglePlayer);

        JsonObject jsonObjectMultiPlayer = new JsonObject();
        jsonObjectMultiPlayer.addProperty("type", "smm:minecraft");
        jsonObjectMultiPlayer.addProperty("value", "multiplayer");
        jsonObjectMultiPlayer.addProperty("texture", "textures/gui/multiplayer_overlay.png");
        array.add(jsonObjectMultiPlayer);

        JsonObject jsonObjectModlist = new JsonObject();
        jsonObjectModlist.addProperty("type", "smm:forge");
        jsonObjectModlist.addProperty("value", "modlist");
        jsonObjectModlist.addProperty("texture", "textures/gui/mods_overlay.png");
        array.add(jsonObjectModlist);

        JsonObject jsonObjectSettings = new JsonObject();
        jsonObjectSettings.addProperty("type", "smm:minecraft");
        jsonObjectSettings.addProperty("value", "settings");
        jsonObjectSettings.addProperty("texture", "textures/gui/settings_overlay.png");
        array.add(jsonObjectSettings);

        JsonObject jsonObjectLocalization = new JsonObject();
        jsonObjectLocalization.addProperty("type", "smm:minecraft");
        jsonObjectLocalization.addProperty("value", "localization");
        jsonObjectLocalization.addProperty("texture", "textures/gui/languages_overlay.png");
        array.add(jsonObjectLocalization);

        JsonObject jsonObjectQuitGame = new JsonObject();
        jsonObjectQuitGame.addProperty("type", "smm:minecraft");
        jsonObjectQuitGame.addProperty("value", "quitgame");
        jsonObjectQuitGame.addProperty("texture", "textures/gui/exit_overlay.png");
        array.add(jsonObjectQuitGame);

        JsonObject jsonObjectUrl = new JsonObject();
        jsonObjectUrl.addProperty("type", "smm:url");
        jsonObjectUrl.addProperty("value", "https://curseforge.com/minecraft/mc-mods/scrollable-main-menu");
        jsonObjectUrl.addProperty("texture", "textures/gui/cf_logo.png");
        array.add(jsonObjectUrl);

        json.add("buttons", array);
        return json;
    }

    public static class ConfigValues{
        public static boolean IS_LOGO_ENABLED = true;
        public static int BUTTON_SIZE_MULTIPLIER = 1; // 1 ~ 3

        public static List<Triple<ResourceLocation, String, String>> BUTTONS = new ArrayList<>();
    }
}