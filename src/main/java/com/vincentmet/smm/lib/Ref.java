package com.vincentmet.smm.lib;

import net.minecraft.util.ResourceLocation;

public class Ref{
	public static final String MODID = "smm";
	public static final String NAME = "Scrollable Main Menu";
	public static final String VERSION = "1.12.2-0.4";
	public static final String RESOURCE_PATH = MODID + ":";
	public static final String PROXY_CLIENT = "com.vincentmet.smm.network.proxy.ClientProxy";
	
	public static final ResourceLocation IMAGE_BUTTON_HEXAGON_UNPRESSED = new ResourceLocation(MODID, "textures/gui/button_hexagon_unpressed.png");
	public static final ResourceLocation IMAGE_BUTTON_HEXAGON_PRESSED = new ResourceLocation(MODID, "textures/gui/button_hexagon_pressed.png");
	
	public static final ResourceLocation IMAGE_BUTTON_HEXAGON_OVERLAY_SINGLEPLAYER = new ResourceLocation(MODID, "textures/gui/singleplayer_overlay.png");
	public static final ResourceLocation IMAGE_BUTTON_HEXAGON_OVERLAY_MULTIPLAYER = new ResourceLocation(MODID, "textures/gui/multiplayer_overlay.png");
	public static final ResourceLocation IMAGE_BUTTON_HEXAGON_OVERLAY_MODS = new ResourceLocation(MODID, "textures/gui/mods_overlay.png");
	public static final ResourceLocation IMAGE_BUTTON_HEXAGON_OVERLAY_OPTIONS= new ResourceLocation(MODID, "textures/gui/settings_overlay.png");
	public static final ResourceLocation IMAGE_BUTTON_HEXAGON_OVERLAY_LANGUAGE = new ResourceLocation(MODID, "textures/gui/languages_overlay.png");
	public static final ResourceLocation IMAGE_BUTTON_HEXAGON_OVERLAY_EXIT = new ResourceLocation(MODID, "textures/gui/exit_overlay.png");
	
	/*public static String CONFIG_LOCATION;*/
}