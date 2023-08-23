package com.vincentmet.smm.lib;

import com.mojang.blaze3d.matrix.MatrixStack;
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
	public static <T extends GuiNewMainMenu> void addHexaButton(MatrixStack stack, int x, int y, int mouseX, int mouseY, int buttonId){
		int buttonSize = 32 * Config.ConfigValues.BUTTON_SIZE_MULTIPLIER;
		stack.pushPose();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		if((x + buttonSize) > mouseX && mouseX > x && (y + buttonSize) > mouseY && mouseY > y){
			Minecraft.getInstance().getTextureManager().bind(Ref.IMAGE_BUTTON_HEXAGON_PRESSED);
		}else{
			Minecraft.getInstance().getTextureManager().bind(Ref.IMAGE_BUTTON_HEXAGON_UNPRESSED);
		}
		AbstractGui.blit(stack, x, y, 0, 0, buttonSize, buttonSize, buttonSize, buttonSize);
		Minecraft.getInstance().getTextureManager().bind(new ResourceLocation(Ref.MODID, Config.ConfigValues.BUTTONS.get(buttonId % Config.ConfigValues.BUTTONS.size()).getRight()));
		AbstractGui.blit(stack, x + (buttonSize / 4), y + (buttonSize / 4), 0, 0, (buttonSize / 2), (buttonSize / 2), (buttonSize / 2), (buttonSize / 2));
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		stack.popPose();
	}

	public static <T extends GuiNewMainMenu> void addHexaButtonLeft(MatrixStack stack, int x, int y, int mouseX, int mouseY){
		int buttonSize = 32 * Config.ConfigValues.BUTTON_SIZE_MULTIPLIER;
		stack.pushPose();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		if((x + buttonSize) > mouseX && mouseX > x && (y + buttonSize) > mouseY && mouseY > y){
			Minecraft.getInstance().getTextureManager().bind(Ref.IMAGE_BUTTON_HEXAGON_PRESSED);
		}else{
			Minecraft.getInstance().getTextureManager().bind(Ref.IMAGE_BUTTON_HEXAGON_UNPRESSED);
		}
		AbstractGui.blit(stack, x, y, 0, 0, buttonSize, buttonSize, buttonSize, buttonSize);
		Minecraft.getInstance().getTextureManager().bind(Ref.IMAGE_BUTTON_ICON_ARROW_LEFT);
		AbstractGui.blit(stack, x + (buttonSize / 4), y + (buttonSize / 4), 0, 0, (buttonSize / 2), (buttonSize / 2), (buttonSize / 2), (buttonSize / 2));
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		stack.popPose();
	}

	public static <T extends GuiNewMainMenu> void addHexaButtonRight(MatrixStack stack, int x, int y, int mouseX, int mouseY){
		int buttonSize = 32 * Config.ConfigValues.BUTTON_SIZE_MULTIPLIER;
		stack.pushPose();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		if((x + buttonSize) > mouseX && mouseX > x && (y + buttonSize) > mouseY && mouseY > y){
			Minecraft.getInstance().getTextureManager().bind(Ref.IMAGE_BUTTON_HEXAGON_PRESSED);
		}else{
			Minecraft.getInstance().getTextureManager().bind(Ref.IMAGE_BUTTON_HEXAGON_UNPRESSED);
		}
		AbstractGui.blit(stack, x, y, 0, 0, buttonSize, buttonSize, buttonSize, buttonSize);
		Minecraft.getInstance().getTextureManager().bind(Ref.IMAGE_BUTTON_ICON_ARROW_RIGHT);
		AbstractGui.blit(stack, x + (buttonSize / 4), y + (buttonSize / 4), 0, 0, (buttonSize / 2), (buttonSize / 2), (buttonSize / 2), (buttonSize / 2));
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		stack.popPose();
	}

	public static void executeActionForButtonId(int buttonId, Screen prevScreen){
		ResourceLocation type = Config.ConfigValues.BUTTONS.get(buttonId).getLeft();
		String value = Config.ConfigValues.BUTTONS.get(buttonId).getMiddle();
		if(type.equals(new ResourceLocation(Ref.MODID, "minecraft"))){
			if(value.equals("singleplayer")) Minecraft.getInstance().setScreen(new WorldSelectionScreen(prevScreen));
			if(value.equals("multiplayer")) Minecraft.getInstance().setScreen(new MultiplayerScreen(prevScreen));
			if(value.equals("settings")) Minecraft.getInstance().setScreen(new OptionsScreen(prevScreen, Minecraft.getInstance().options));
			if(value.equals("localization")) Minecraft.getInstance().setScreen(new LanguageScreen(prevScreen, Minecraft.getInstance().options, Minecraft.getInstance().getLanguageManager()));
			if(value.equals("quitgame")) Minecraft.getInstance().stop();
		}
		if(type.equals(new ResourceLocation(Ref.MODID, "forge"))){
			if(value.equals("modlist")) Minecraft.getInstance().setScreen(new ModListScreen(prevScreen));
		}
		if(type.equals(new ResourceLocation(Ref.MODID, "url"))){
			Minecraft.getInstance().setScreen(new ConfirmOpenLinkScreen((bool) -> {
				if (bool) {
					Util.getPlatform().openUri(Config.ConfigValues.BUTTONS.get(buttonId).getMiddle());
				}
				Minecraft.getInstance().setScreen(prevScreen);
			}, Config.ConfigValues.BUTTONS.get(buttonId).getMiddle(), true));
		}
	}
}