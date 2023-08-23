package com.vincentmet.smm.lib;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class Ref{
	public static final String MODID = "smm";
	public static final Path PATH_CONFIG = FMLPaths.CONFIGDIR.get().resolve(MODID);
	
	public static final ResourceLocation IMAGE_BUTTON_HEXAGON_UNPRESSED = new ResourceLocation(MODID, "textures/gui/button_hexagon_unpressed.png");
	public static final ResourceLocation IMAGE_BUTTON_HEXAGON_PRESSED = new ResourceLocation(MODID, "textures/gui/button_hexagon_pressed.png");
	public static final ResourceLocation IMAGE_BUTTON_ICON_ARROW_LEFT = new ResourceLocation(MODID, "textures/gui/icon_arrow_left.png");
	public static final ResourceLocation IMAGE_BUTTON_ICON_ARROW_RIGHT = new ResourceLocation(MODID, "textures/gui/icon_arrow_right.png");
}