package com.vincentmet.smm.lib;

import com.vincentmet.smm.lib.handlers.ConfigHandler;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class Utils{
	public static <T extends GuiScreen> void addHexaButton(int x, int y, int mouseX, int mouseY, T gui, EnumButtonTexture texture){
		int buttonSize = 32;
		switch(ConfigHandler.button_size){
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
		if((x + buttonSize) > mouseX && mouseX > x && (y + buttonSize) > mouseY && mouseY > y){
			gui.mc.getTextureManager().bindTexture(Ref.IMAGE_BUTTON_HEXAGON_PRESSED);
		}else{
			gui.mc.getTextureManager().bindTexture(Ref.IMAGE_BUTTON_HEXAGON_UNPRESSED);
		}
		Gui.drawModalRectWithCustomSizedTexture(x, y, 0, 0, buttonSize, buttonSize, buttonSize, buttonSize);
		switch(texture){
			case SINGLEPLAYER: gui.mc.getTextureManager().bindTexture(Ref.IMAGE_BUTTON_HEXAGON_OVERLAY_SINGLEPLAYER); break;
			case MULTIPLAYER: gui.mc.getTextureManager().bindTexture(Ref.IMAGE_BUTTON_HEXAGON_OVERLAY_MULTIPLAYER); break;
			case MODLIST: gui.mc.getTextureManager().bindTexture(Ref.IMAGE_BUTTON_HEXAGON_OVERLAY_MODS); break;
			case OPTIONS: gui.mc.getTextureManager().bindTexture(Ref.IMAGE_BUTTON_HEXAGON_OVERLAY_OPTIONS); break;
			case LANGUAGE: gui.mc.getTextureManager().bindTexture(Ref.IMAGE_BUTTON_HEXAGON_OVERLAY_LANGUAGE); break;
			case EXIT: gui.mc.getTextureManager().bindTexture(Ref.IMAGE_BUTTON_HEXAGON_OVERLAY_EXIT); break;
		}
		Gui.drawModalRectWithCustomSizedTexture(x + (buttonSize / 4), y + (buttonSize / 4), 0, 0, (buttonSize / 2), (buttonSize / 2), (buttonSize / 2), (buttonSize / 2));
		GL11.glDisable(GL11.GL_BLEND);
		GlStateManager.popMatrix();
	}
}