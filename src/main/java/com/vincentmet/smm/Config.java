package com.vincentmet.smm;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.nio.file.Path;

@Mod.EventBusSubscriber
public class Config {
    public static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec CLIENT_CONFIG;

    public static final String CATEGORY_GENERAL = "General";
    public static ForgeConfigSpec.BooleanValue isLogoEnabled;
    public static ForgeConfigSpec.IntValue buttonSize;
    public static ForgeConfigSpec.IntValue backgroundWidth;
    public static ForgeConfigSpec.IntValue backgroundHeight;
    public static ForgeConfigSpec.IntValue logoWidth;
    public static ForgeConfigSpec.IntValue logoHeight;

    static{
        isLogoEnabled = CLIENT_BUILDER.comment("Logo Enabled").define("isLogoEnabled", true);
        buttonSize = CLIENT_BUILDER.comment("Button Scale (0=Small;1=Medium;2=Big;)").defineInRange("buttonSize", 0, 0, 2);
        backgroundWidth = CLIENT_BUILDER.comment("Background Width").defineInRange("backgroundWidth", 1280, 1, 3840);
        backgroundHeight = CLIENT_BUILDER.comment("Background Height").defineInRange("backgroundHeight", 720, 1, 2160);
        logoWidth = CLIENT_BUILDER.comment("Logo Width").defineInRange("logoWidth", 200, 1, 200);
        logoHeight = CLIENT_BUILDER.comment("Logo Height").defineInRange("logoHeight", 150, 1, 150);

        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path){
        final CommentedFileConfig config = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();
        config.load();
        spec.setConfig(config);
    }
}