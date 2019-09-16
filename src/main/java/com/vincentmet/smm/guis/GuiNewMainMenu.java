package com.vincentmet.smm.guis;

import com.vincentmet.smm.Config;
import com.vincentmet.smm.lib.EnumButtonTexture;
import com.vincentmet.smm.lib.Ref;
import com.vincentmet.smm.lib.Utils;
import com.vincentmet.smm.lib.gui.GuiLocation;
import net.minecraft.client.gui.screen.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.client.gui.GuiModList;
import org.lwjgl.opengl.GL11;

public class GuiNewMainMenu extends Screen {
	private static final ResourceLocation background = new ResourceLocation(Ref.MODID, "textures/gui/background.png");
	private static final ResourceLocation logo = new ResourceLocation(Ref.MODID, "textures/gui/logo.png");
	private static final int textureSizeX = Config.backgroundWidth.get();
	private static final int textureSizeY = Config.backgroundHeight.get();
	private static final int maxLogoWidth = 200;
	private static final int maxLogoHeight = 150;
	private static final int logoTextureWidth = Config.logoWidth.get();
	private static final int logoTextureHeight = Config.logoHeight.get();
	public int currentButton = 0;
	
	public GuiLocation left;
	public GuiLocation leftcenter;
	public GuiLocation center;
	public GuiLocation centerright;
	public GuiLocation right;

	public GuiNewMainMenu() {
		super(new TranslationTextComponent(""));
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks){
		GL11.glPushMatrix();
		float x = (float)width/textureSizeX;
		float y = (float)height/textureSizeY;
		GL11.glScaled(x, y, 1.0);
		this.getMinecraft().getTextureManager().bindTexture(background);
		blit(0, 0, 0, 0, textureSizeX, textureSizeY, textureSizeX, textureSizeY);
		GL11.glPopMatrix();
		
		if(Config.isLogoEnabled.get()){
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			this.getMinecraft().getTextureManager().bindTexture(logo);
			blit((width>>1)-(maxLogoWidth>>1), (height>>4), 0, 0, logoTextureWidth, logoTextureHeight, logoTextureWidth, logoTextureHeight);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glPopMatrix();
		}

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
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton){
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
		
		int buttonX = center.x;
		int buttonY = center.y;
		if((buttonX + buttonSize) > mouseX && mouseX > buttonX && (buttonY + buttonSize) > mouseY && mouseY > buttonY){
			//this.getMinecraft().getSoundHandler().play((ISound) SoundEvents.UI_BUTTON_CLICK);
			switch(this.currentButton){
				case 0: this.getMinecraft().displayGuiScreen(new WorldSelectionScreen(this)); break; // Singleplayer
				case 1: this.getMinecraft().displayGuiScreen(new MultiplayerScreen(this)); break; // Multiplayer
				case 2: this.getMinecraft().displayGuiScreen(new GuiModList(this)); break; // Mods
				case 3: this.getMinecraft().displayGuiScreen(new OptionsScreen(this, this.getMinecraft().gameSettings)); break; // Settings
				case 4: this.getMinecraft().displayGuiScreen(new LanguageScreen(this, this.getMinecraft().gameSettings, this.getMinecraft().getLanguageManager())); break; // Language
				case 5: this.getMinecraft().shutdown(); break; // Exit
			}
		}
		
		
		buttonX = left.x;
		buttonY = left.y;
		if((buttonX + buttonSize) > mouseX && mouseX > buttonX && (buttonY + buttonSize) > mouseY && mouseY > buttonY){
			//this.getMinecraft().getSoundHandler().play((ISound) SoundEvents.UI_BUTTON_CLICK);
			if(currentButton >= 2){
				currentButton -= 2;
			}else{
				currentButton += 4;
			}
		}
		
		
		buttonX = leftcenter.x;
		buttonY = leftcenter.y;
		if((buttonX + buttonSize) > mouseX && mouseX > buttonX && (buttonY + buttonSize) > mouseY && mouseY > buttonY){
			//this.getMinecraft().getSoundHandler().play((ISound) SoundEvents.UI_BUTTON_CLICK);
			if(currentButton >= 1){
				currentButton -= 1;
			}else{
				currentButton += 5;
			}
		}
		
		buttonX = centerright.x;
		buttonY = centerright.y;
		if((buttonX + buttonSize) > mouseX && mouseX > buttonX && (buttonY + buttonSize) > mouseY && mouseY > buttonY){
			//this.getMinecraft().getSoundHandler().play((ISound)SoundEvents.UI_BUTTON_CLICK);
			if(currentButton <= 4){
				currentButton += 1;
			}else{
				currentButton -= 5;
			}
		}
		
		buttonX = right.x;
		buttonY = right.y;
		if((buttonX + buttonSize) > mouseX && mouseX > buttonX && (buttonY + buttonSize) > mouseY && mouseY > buttonY){
			//this.getMinecraft().getSoundHandler().play((ISound) SoundEvents.UI_BUTTON_CLICK);
			if(currentButton <= 3){
				currentButton += 2;
			}else{
				currentButton -= 4;
			}
		}
		
		return super.mouseClicked(mouseX, mouseY, mouseButton);
	}
}