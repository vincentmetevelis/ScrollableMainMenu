package com.vincentmet.smm.guis;

import com.vincentmet.smm.lib.EnumButtonTexture;
import com.vincentmet.smm.lib.Ref;
import com.vincentmet.smm.lib.Utils;
import com.vincentmet.smm.lib.gui.GuiLocation;
import com.vincentmet.smm.lib.handlers.ConfigHandler;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.GuiModList;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiNewMainMenu extends GuiScreen{
	private static final ResourceLocation background = new ResourceLocation(Ref.MODID, "textures/gui/background.png");
	private static final ResourceLocation logo = new ResourceLocation(Ref.MODID, "textures/gui/logo.png");
	private static final int textureSizeX = ConfigHandler.background_width;
	private static final int textureSizeY = ConfigHandler.background_height;
	private static final int maxLogoWidth = 200;
	private static final int maxLogoHeight = 150;
	private static final int logoTextureWidth = ConfigHandler.logo_width;
	private static final int logoTextureHeight = ConfigHandler.logo_height;
	public int currentButton = 0;
	
	public GuiLocation left;
	public GuiLocation leftcenter;
	public GuiLocation center;
	public GuiLocation centerright;
	public GuiLocation right;
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks){
		GlStateManager.pushMatrix();
		float x = (float)width/textureSizeX;
		float y = (float)height/textureSizeY;
		GlStateManager.scale(x, y, 1.0);
		this.mc.renderEngine.bindTexture(background);
		drawModalRectWithCustomSizedTexture(0, 0, 0, 0, textureSizeX, textureSizeY, textureSizeX, textureSizeY);
		GlStateManager.popMatrix();
		
		if(ConfigHandler.is_logo_enabled){
			GlStateManager.pushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			this.mc.renderEngine.bindTexture(logo);
			drawModalRectWithCustomSizedTexture((width>>1)-(maxLogoWidth>>1), (height>>4), 0, 0, logoTextureWidth, logoTextureHeight, logoTextureWidth, logoTextureHeight);
			GL11.glDisable(GL11.GL_BLEND);
			GlStateManager.popMatrix();
		}
		
		List<GuiButton> buttons = new ArrayList<>();
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
		final int buttonDistanceApart = buttonSize;
		int centerWidth = width >> 1;
		int centerHeight = height >> 1;
		
		left =           new GuiLocation(centerWidth - (buttonSize >> 1) - 2 * buttonDistanceApart, centerHeight + (height / 6) - 2*(buttonSize / 3));
		leftcenter =     new GuiLocation(centerWidth - (buttonSize >> 1) - 1 * buttonDistanceApart, centerHeight + (height / 6) - 1*(buttonSize / 3));
		center =         new GuiLocation(centerWidth - (buttonSize >> 1) + 0 * buttonDistanceApart, centerHeight + (height / 6) - 0*(buttonSize / 3));
		centerright =    new GuiLocation(centerWidth - (buttonSize >> 1) + 1 * buttonDistanceApart, centerHeight + (height / 6) - 1*(buttonSize / 3));
		right =          new GuiLocation(centerWidth - (buttonSize >> 1) + 2 * buttonDistanceApart, centerHeight + (height / 6) - 2*(buttonSize / 3));
		
		switch(this.currentButton){
			case 0:
				Utils.addHexaButton(left.x, left.y, mouseX, mouseY, this, EnumButtonTexture.LANGUAGE);
				Utils.addHexaButton(leftcenter.x, leftcenter.y, mouseX, mouseY, this, EnumButtonTexture.EXIT);
				Utils.addHexaButton(center.x, center.y, mouseX, mouseY, this, EnumButtonTexture.SINGLEPLAYER);
				Utils.addHexaButton(centerright.x, centerright.y, mouseX, mouseY, this, EnumButtonTexture.MULTIPLAYER);
				Utils.addHexaButton(right.x, right.y, mouseX, mouseY, this, EnumButtonTexture.MODLIST);
				break;
			case 1:
				Utils.addHexaButton(left.x, left.y, mouseX, mouseY, this, EnumButtonTexture.EXIT);
				Utils.addHexaButton(leftcenter.x, leftcenter.y, mouseX, mouseY, this, EnumButtonTexture.SINGLEPLAYER);
				Utils.addHexaButton(center.x, center.y, mouseX, mouseY, this, EnumButtonTexture.MULTIPLAYER);
				Utils.addHexaButton(centerright.x, centerright.y, mouseX, mouseY, this, EnumButtonTexture.MODLIST);
				Utils.addHexaButton(right.x, right.y, mouseX, mouseY, this, EnumButtonTexture.OPTIONS);
				break;
			case 2:
				Utils.addHexaButton(left.x, left.y, mouseX, mouseY, this, EnumButtonTexture.SINGLEPLAYER);
				Utils.addHexaButton(leftcenter.x, leftcenter.y, mouseX, mouseY, this, EnumButtonTexture.MULTIPLAYER);
				Utils.addHexaButton(center.x, center.y, mouseX, mouseY, this, EnumButtonTexture.MODLIST);
				Utils.addHexaButton(centerright.x, centerright.y, mouseX, mouseY, this, EnumButtonTexture.OPTIONS);
				Utils.addHexaButton(right.x, right.y, mouseX, mouseY, this, EnumButtonTexture.LANGUAGE);
				break;
			case 3:
				Utils.addHexaButton(left.x, left.y, mouseX, mouseY, this, EnumButtonTexture.MULTIPLAYER);
				Utils.addHexaButton(leftcenter.x, leftcenter.y, mouseX, mouseY, this, EnumButtonTexture.MODLIST);
				Utils.addHexaButton(center.x, center.y, mouseX, mouseY, this, EnumButtonTexture.OPTIONS);
				Utils.addHexaButton(centerright.x, centerright.y, mouseX, mouseY, this, EnumButtonTexture.LANGUAGE);
				Utils.addHexaButton(right.x, right.y, mouseX, mouseY, this, EnumButtonTexture.EXIT);
				break;
			case 4:
				Utils.addHexaButton(left.x, left.y, mouseX, mouseY, this, EnumButtonTexture.MODLIST);
				Utils.addHexaButton(leftcenter.x, leftcenter.y, mouseX, mouseY, this, EnumButtonTexture.OPTIONS);
				Utils.addHexaButton(center.x, center.y, mouseX, mouseY, this, EnumButtonTexture.LANGUAGE);
				Utils.addHexaButton(centerright.x, centerright.y, mouseX, mouseY, this, EnumButtonTexture.EXIT);
				Utils.addHexaButton(right.x, right.y, mouseX, mouseY, this, EnumButtonTexture.SINGLEPLAYER);
				break;
			case 5:
				Utils.addHexaButton(left.x, left.y, mouseX, mouseY, this, EnumButtonTexture.OPTIONS);
				Utils.addHexaButton(leftcenter.x, leftcenter.y, mouseX, mouseY, this, EnumButtonTexture.LANGUAGE);
				Utils.addHexaButton(center.x, center.y, mouseX, mouseY, this, EnumButtonTexture.EXIT);
				Utils.addHexaButton(centerright.x, centerright.y, mouseX, mouseY, this, EnumButtonTexture.SINGLEPLAYER);
				Utils.addHexaButton(right.x, right.y, mouseX, mouseY, this, EnumButtonTexture.MULTIPLAYER);
				break;
		}
		
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException{
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
		
		int buttonX = center.x;
		int buttonY = center.y;
		if((buttonX + buttonSize) > mouseX && mouseX > buttonX && (buttonY + buttonSize) > mouseY && mouseY > buttonY){
			this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
			switch(this.currentButton){
				case 0: this.mc.displayGuiScreen(new GuiWorldSelection(this)); break; // Singleplayer
				case 1: this.mc.displayGuiScreen(new GuiMultiplayer(this)); break; // Multiplayer
				case 2: this.mc.displayGuiScreen(new GuiModList(this)); break; // Mods
				case 3: this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings)); break; // Settings
				case 4: this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager())); break; // Language
				case 5: this.mc.shutdown(); break; // Exit
			}
		}
		
		
		buttonX = left.x;
		buttonY = left.y;
		if((buttonX + buttonSize) > mouseX && mouseX > buttonX && (buttonY + buttonSize) > mouseY && mouseY > buttonY){
			this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
			if(currentButton >= 2){
				currentButton -= 2;
			}else{
				currentButton += 4;
			}
		}
		
		
		buttonX = leftcenter.x;
		buttonY = leftcenter.y;
		if((buttonX + buttonSize) > mouseX && mouseX > buttonX && (buttonY + buttonSize) > mouseY && mouseY > buttonY){
			this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
			if(currentButton >= 1){
				currentButton -= 1;
			}else{
				currentButton += 5;
			}
		}
		
		buttonX = centerright.x;
		buttonY = centerright.y;
		if((buttonX + buttonSize) > mouseX && mouseX > buttonX && (buttonY + buttonSize) > mouseY && mouseY > buttonY){
			this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
			if(currentButton <= 4){
				currentButton += 1;
			}else{
				currentButton -= 5;
			}
		}
		
		buttonX = right.x;
		buttonY = right.y;
		if((buttonX + buttonSize) > mouseX && mouseX > buttonX && (buttonY + buttonSize) > mouseY && mouseY > buttonY){
			this.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
			if(currentButton <= 3){
				currentButton += 2;
			}else{
				currentButton -= 4;
			}
		}
		
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}
}