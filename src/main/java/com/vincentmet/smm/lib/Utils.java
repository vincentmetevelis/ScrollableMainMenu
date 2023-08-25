package com.vincentmet.smm.lib;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.vincentmet.smm.Config;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.LanguageSelectScreen;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.ModListScreen;

public class Utils{
	public static void addHexaButton(PoseStack stack, int x, int y, int mouseX, int mouseY, int buttonId){
		int buttonSize = 32 * Config.ConfigValues.BUTTON_SIZE_MULTIPLIER;
		stack.pushPose();
		if((x + buttonSize) > mouseX && mouseX > x && (y + buttonSize) > mouseY && mouseY > y){
			RenderSystem.setShaderTexture(0, Ref.IMAGE_BUTTON_HEXAGON_PRESSED);
		}else{
			RenderSystem.setShaderTexture(0, Ref.IMAGE_BUTTON_HEXAGON_UNPRESSED);
		}
		Screen.blit(stack, x, y, 0, 0, buttonSize, buttonSize, buttonSize, buttonSize);
		RenderSystem.setShaderTexture(0, new ResourceLocation(Ref.MODID, Config.ConfigValues.BUTTONS.get(buttonId % Config.ConfigValues.BUTTONS.size()).getRight()));
		Screen.blit(stack, x + (buttonSize / 4), y + (buttonSize / 4), 0, 0, (buttonSize / 2), (buttonSize / 2), (buttonSize / 2), (buttonSize / 2));
		stack.popPose();
	}

	public static void addHexaButtonLeft(PoseStack stack, int x, int y, int mouseX, int mouseY){
		int buttonSize = 32 * Config.ConfigValues.BUTTON_SIZE_MULTIPLIER;
		stack.pushPose();
		if((x + buttonSize) > mouseX && mouseX > x && (y + buttonSize) > mouseY && mouseY > y){
			RenderSystem.setShaderTexture(0, Ref.IMAGE_BUTTON_HEXAGON_PRESSED);
		}else{
			RenderSystem.setShaderTexture(0, Ref.IMAGE_BUTTON_HEXAGON_UNPRESSED);
		}
		Screen.blit(stack, x, y, 0, 0, buttonSize, buttonSize, buttonSize, buttonSize);
		RenderSystem.setShaderTexture(0, Ref.IMAGE_BUTTON_ICON_ARROW_LEFT);
		Screen.blit(stack, x + (buttonSize / 4), y + (buttonSize / 4), 0, 0, (buttonSize / 2), (buttonSize / 2), (buttonSize / 2), (buttonSize / 2));
		stack.popPose();
	}

	public static void addHexaButtonRight(PoseStack stack, int x, int y, int mouseX, int mouseY){
		int buttonSize = 32 * Config.ConfigValues.BUTTON_SIZE_MULTIPLIER;
		stack.pushPose();
		if((x + buttonSize) > mouseX && mouseX > x && (y + buttonSize) > mouseY && mouseY > y){
			RenderSystem.setShaderTexture(0, Ref.IMAGE_BUTTON_HEXAGON_PRESSED);
		}else{
			RenderSystem.setShaderTexture(0, Ref.IMAGE_BUTTON_HEXAGON_UNPRESSED);
		}
		Screen.blit(stack, x, y, 0, 0, buttonSize, buttonSize, buttonSize, buttonSize);
		RenderSystem.setShaderTexture(0, Ref.IMAGE_BUTTON_ICON_ARROW_RIGHT);
		Screen.blit(stack, x + (buttonSize / 4), y + (buttonSize / 4), 0, 0, (buttonSize / 2), (buttonSize / 2), (buttonSize / 2), (buttonSize / 2));
		stack.popPose();
	}

	public static void executeActionForButtonId(int buttonId, Screen prevScreen){
		ResourceLocation type = Config.ConfigValues.BUTTONS.get(buttonId).getLeft();
		String value = Config.ConfigValues.BUTTONS.get(buttonId).getMiddle();
		if(type.equals(new ResourceLocation(Ref.MODID, "minecraft"))){
			SelectWorldScreen s = new SelectWorldScreen(prevScreen);
			s.init(Minecraft.getInstance(), prevScreen.width, prevScreen.height);
			if(value.equals("singleplayer")) Minecraft.getInstance().setScreen(s);
			if(value.equals("multiplayer")) Minecraft.getInstance().setScreen(new JoinMultiplayerScreen(prevScreen));
			if(value.equals("settings")) Minecraft.getInstance().setScreen(new OptionsScreen(prevScreen, Minecraft.getInstance().options));
			if(value.equals("localization")) Minecraft.getInstance().setScreen(new LanguageSelectScreen(prevScreen, Minecraft.getInstance().options, Minecraft.getInstance().getLanguageManager()));
			if(value.equals("quitgame")) Minecraft.getInstance().stop();
		}
		if(type.equals(new ResourceLocation(Ref.MODID, "forge"))){
			if(value.equals("modlist")) Minecraft.getInstance().setScreen(new ModListScreen(prevScreen));
		}
		if(type.equals(new ResourceLocation(Ref.MODID, "url"))){
			Minecraft.getInstance().setScreen(new ConfirmLinkScreen((bool) -> {
				if (bool) {
					Util.getPlatform().openUri(Config.ConfigValues.BUTTONS.get(buttonId).getMiddle());
				}
				Minecraft.getInstance().setScreen(prevScreen);
			}, Config.ConfigValues.BUTTONS.get(buttonId).getMiddle(), true));
		}
	}
}