package com.vincentmet.smm.lib;

import com.mojang.blaze3d.platform.GlStateManager;
import com.vincentmet.smm.Config;
import com.vincentmet.smm.guis.GuiNewMainMenu;
import org.lwjgl.opengl.GL11;

public class Utils{
	public static <T extends GuiNewMainMenu> void addHexaButton(int x, int y, int mouseX, int mouseY, T gui, EnumButtonTexture texture){
		int buttonSize = 32;
		switch(Config.buttonSize.get()){
			case 0:
				buttonSize = 32;
				break;
			case 1:
				buttonSize = 64;
				break;
			case 2:
				buttonSize = 96;
				break;
		}
		GlStateManager.pushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		if((x + buttonSize) > mouseX && mouseX > x && (y + buttonSize) > mouseY && mouseY > y){
			gui.getMinecraft().getTextureManager().bindTexture(Ref.IMAGE_BUTTON_HEXAGON_PRESSED);
		}else{
			gui.getMinecraft().getTextureManager().bindTexture(Ref.IMAGE_BUTTON_HEXAGON_UNPRESSED);
		}
		gui.blit(x, y, 0, 0, buttonSize, buttonSize, buttonSize, buttonSize);
		switch(texture){
			case SINGLEPLAYER: gui.getMinecraft().getTextureManager().bindTexture(Ref.IMAGE_BUTTON_HEXAGON_OVERLAY_SINGLEPLAYER); break;
			case MULTIPLAYER: gui.getMinecraft().getTextureManager().bindTexture(Ref.IMAGE_BUTTON_HEXAGON_OVERLAY_MULTIPLAYER); break;
			case MODLIST: gui.getMinecraft().getTextureManager().bindTexture(Ref.IMAGE_BUTTON_HEXAGON_OVERLAY_MODS); break;
			case OPTIONS: gui.getMinecraft().getTextureManager().bindTexture(Ref.IMAGE_BUTTON_HEXAGON_OVERLAY_OPTIONS); break;
			case LANGUAGE: gui.getMinecraft().getTextureManager().bindTexture(Ref.IMAGE_BUTTON_HEXAGON_OVERLAY_LANGUAGE); break;
			case EXIT: gui.getMinecraft().getTextureManager().bindTexture(Ref.IMAGE_BUTTON_HEXAGON_OVERLAY_EXIT); break;
		}
		gui.blit(x + (buttonSize / 4), y + (buttonSize / 4), 0, 0, (buttonSize / 2), (buttonSize / 2), (buttonSize / 2), (buttonSize / 2));
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GlStateManager.popMatrix();
	}
}