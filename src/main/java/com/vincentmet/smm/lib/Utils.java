package com.vincentmet.smm.lib;

import com.mojang.blaze3d.platform.GlStateManager;
import com.vincentmet.smm.Config;
import com.vincentmet.smm.guis.GuiNewMainMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.screen.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.fml.client.gui.screen.ModListScreen;
import org.lwjgl.opengl.GL11;

public class Utils{
	public static <T extends GuiNewMainMenu> void addHexaButton(int x, int y, int mouseX, int mouseY, int buttonId){
		int buttonSize = 32 * Config.ConfigValues.BUTTON_SIZE_MULTIPLIER;
		GlStateManager.pushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		if((x + buttonSize) > mouseX && mouseX > x && (y + buttonSize) > mouseY && mouseY > y){
			Minecraft.getInstance().getTextureManager().bindTexture(Ref.IMAGE_BUTTON_HEXAGON_PRESSED);
		}else{
			Minecraft.getInstance().getTextureManager().bindTexture(Ref.IMAGE_BUTTON_HEXAGON_UNPRESSED);
		}
		AbstractGui.blit(x, y, 0, 0, buttonSize, buttonSize, buttonSize, buttonSize);
		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation(Ref.MODID, Config.ConfigValues.BUTTONS.get(buttonId % Config.ConfigValues.BUTTONS.size()).getRight()));
		AbstractGui.blit(x + (buttonSize / 4), y + (buttonSize / 4), 0, 0, (buttonSize / 2), (buttonSize / 2), (buttonSize / 2), (buttonSize / 2));
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GlStateManager.popMatrix();
	}

	public static <T extends GuiNewMainMenu> void addHexaButtonLeft(int x, int y, int mouseX, int mouseY){
		int buttonSize = 32 * Config.ConfigValues.BUTTON_SIZE_MULTIPLIER;
		GlStateManager.pushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		if((x + buttonSize) > mouseX && mouseX > x && (y + buttonSize) > mouseY && mouseY > y){
			Minecraft.getInstance().getTextureManager().bindTexture(Ref.IMAGE_BUTTON_HEXAGON_PRESSED);
		}else{
			Minecraft.getInstance().getTextureManager().bindTexture(Ref.IMAGE_BUTTON_HEXAGON_UNPRESSED);
		}
		AbstractGui.blit(x, y, 0, 0, buttonSize, buttonSize, buttonSize, buttonSize);
		Minecraft.getInstance().getTextureManager().bindTexture(Ref.IMAGE_BUTTON_ICON_ARROW_LEFT);
		AbstractGui.blit(x + (buttonSize / 4), y + (buttonSize / 4), 0, 0, (buttonSize / 2), (buttonSize / 2), (buttonSize / 2), (buttonSize / 2));
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GlStateManager.popMatrix();
	}

	public static <T extends GuiNewMainMenu> void addHexaButtonRight(int x, int y, int mouseX, int mouseY){
		int buttonSize = 32 * Config.ConfigValues.BUTTON_SIZE_MULTIPLIER;
		GlStateManager.pushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		if((x + buttonSize) > mouseX && mouseX > x && (y + buttonSize) > mouseY && mouseY > y){
			Minecraft.getInstance().getTextureManager().bindTexture(Ref.IMAGE_BUTTON_HEXAGON_PRESSED);
		}else{
			Minecraft.getInstance().getTextureManager().bindTexture(Ref.IMAGE_BUTTON_HEXAGON_UNPRESSED);
		}
		AbstractGui.blit(x, y, 0, 0, buttonSize, buttonSize, buttonSize, buttonSize);
		Minecraft.getInstance().getTextureManager().bindTexture(Ref.IMAGE_BUTTON_ICON_ARROW_RIGHT);
		AbstractGui.blit(x + (buttonSize / 4), y + (buttonSize / 4), 0, 0, (buttonSize / 2), (buttonSize / 2), (buttonSize / 2), (buttonSize / 2));
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GlStateManager.popMatrix();
	}

	public static void executeActionForButtonId(int buttonId, Screen prevScreen){
		ResourceLocation type = Config.ConfigValues.BUTTONS.get(buttonId).getLeft();
		String value = Config.ConfigValues.BUTTONS.get(buttonId).getMiddle();
		if(type.equals(new ResourceLocation(Ref.MODID, "minecraft"))){
			if(value.equals("singleplayer")) Minecraft.getInstance().displayGuiScreen(new WorldSelectionScreen(prevScreen));
			if(value.equals("multiplayer")) Minecraft.getInstance().displayGuiScreen(new MultiplayerScreen(prevScreen));
			if(value.equals("settings")) Minecraft.getInstance().displayGuiScreen(new OptionsScreen(prevScreen, Minecraft.getInstance().gameSettings));
			if(value.equals("localization")) Minecraft.getInstance().displayGuiScreen(new LanguageScreen(prevScreen, Minecraft.getInstance().gameSettings, Minecraft.getInstance().getLanguageManager()));
			if(value.equals("quitgame")) Minecraft.getInstance().shutdown();
		}
		if(type.equals(new ResourceLocation(Ref.MODID, "forge"))){
			if(value.equals("modlist")) Minecraft.getInstance().displayGuiScreen(new ModListScreen(prevScreen));
		}
		if(type.equals(new ResourceLocation(Ref.MODID, "url"))){
			Minecraft.getInstance().displayGuiScreen(new ConfirmOpenLinkScreen((bool) -> {
				if (bool) {
					Util.getOSType().openURI(Config.ConfigValues.BUTTONS.get(buttonId).getMiddle());
				}
				Minecraft.getInstance().displayGuiScreen(prevScreen);
			}, Config.ConfigValues.BUTTONS.get(buttonId).getMiddle(), true));
		}
	}
}