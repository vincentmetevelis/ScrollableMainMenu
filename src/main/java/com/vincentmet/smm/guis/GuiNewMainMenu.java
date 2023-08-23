package com.vincentmet.smm.guis;

import com.vincentmet.smm.Config;
import com.vincentmet.smm.lib.Ref;
import com.vincentmet.smm.lib.Utils;
import com.vincentmet.smm.lib.gui.GuiLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.TranslationTextComponent;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class GuiNewMainMenu extends Screen {
	private static final ResourceLocation background = new ResourceLocation(Ref.MODID, "textures/gui/background.png");
	private static final ResourceLocation logo = new ResourceLocation(Ref.MODID, "textures/gui/logo.png");
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
	public void render(int mouseX, int mouseY, float partialTicks){
		GL11.glPushMatrix();
		this.getMinecraft().getTextureManager().bindTexture(background);
		int[] bgBufW = new int[1];
		int[] bgBufH = new int[1];
		glGetTexLevelParameteriv(GL_TEXTURE_2D, 0, GL_TEXTURE_WIDTH, bgBufW);
		glGetTexLevelParameteriv(GL_TEXTURE_2D, 0, GL_TEXTURE_HEIGHT, bgBufH);
		int bgWidth = bgBufW[0];
		int bgHeight = bgBufH[0];
		float x = (float)width/bgWidth;
		float y = (float)height/bgHeight;
		GL11.glScaled(x, y, 1.0);
		blit(0, 0, 0, 0, bgWidth, bgHeight, bgWidth, bgHeight);
		GL11.glPopMatrix();
		
		if(Config.ConfigValues.IS_LOGO_ENABLED){
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			this.getMinecraft().getTextureManager().bindTexture(logo);
			int[] logoBufW = new int[1];
			int[] logoBufH = new int[1];
			glGetTexLevelParameteriv(GL_TEXTURE_2D, 0, GL_TEXTURE_WIDTH, logoBufW);
			glGetTexLevelParameteriv(GL_TEXTURE_2D, 0, GL_TEXTURE_HEIGHT, logoBufH);
			int logoTexWidth = logoBufW[0];
			int logoTexHeight = logoBufH[0];
			blit((width>>1)-(logoTexWidth>>1), height>>4, 0, 0, logoTexWidth, logoTexHeight, logoTexWidth, logoTexHeight);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_ALPHA_TEST);
			GL11.glPopMatrix();
		}

		int buttonSize = 32 * Config.ConfigValues.BUTTON_SIZE_MULTIPLIER;
		int buttonDistanceApart = buttonSize;
		int centerX = width >> 1;
		int centerY = height >> 1;
		
		left =           new GuiLocation(centerX - (buttonSize >> 1) - 2 * buttonDistanceApart, centerY + (height / 6) - 2*(buttonSize / 3));
		leftcenter =     new GuiLocation(centerX - (buttonSize >> 1) - 1 * buttonDistanceApart, centerY + (height / 6) - 1*(buttonSize / 3));
		center =         new GuiLocation(centerX - (buttonSize >> 1) + 0 * buttonDistanceApart, centerY + (height / 6) - 0*(buttonSize / 3));
		centerright =    new GuiLocation(centerX - (buttonSize >> 1) + 1 * buttonDistanceApart, centerY + (height / 6) - 1*(buttonSize / 3));
		right =          new GuiLocation(centerX - (buttonSize >> 1) + 2 * buttonDistanceApart, centerY + (height / 6) - 2*(buttonSize / 3));

		if(Config.ConfigValues.BUTTONS.size() >= 1){
			Utils.addHexaButtonLeft(left.x, left.y, mouseX, mouseY);
			Utils.addHexaButton(leftcenter.x, leftcenter.y, mouseX, mouseY, (this.currentButton-1+Config.ConfigValues.BUTTONS.size())%Config.ConfigValues.BUTTONS.size());
			Utils.addHexaButton(center.x, center.y, mouseX, mouseY, (this.currentButton+Config.ConfigValues.BUTTONS.size())%Config.ConfigValues.BUTTONS.size());
			Utils.addHexaButton(centerright.x, centerright.y, mouseX, mouseY, (this.currentButton+1+Config.ConfigValues.BUTTONS.size())%Config.ConfigValues.BUTTONS.size());
			Utils.addHexaButtonRight(right.x, right.y, mouseX, mouseY);
		}
	}
	
	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton){
		int buttonSize = 32 * Config.ConfigValues.BUTTON_SIZE_MULTIPLIER;
		if (Config.ConfigValues.BUTTONS.size() >= 1){
			if((left.x + buttonSize) > mouseX && mouseX > left.x && (left.y + buttonSize) > mouseY && mouseY > left.y){
				Minecraft.getInstance().getSoundHandler().play(SimpleSound.master(SoundEvents.UI_BUTTON_CLICK, 1));
				this.currentButton = (this.currentButton-1+Config.ConfigValues.BUTTONS.size())%Config.ConfigValues.BUTTONS.size();
			}

			if((leftcenter.x + buttonSize) > mouseX && mouseX > leftcenter.x && (leftcenter.y + buttonSize) > mouseY && mouseY > leftcenter.y){
				Minecraft.getInstance().getSoundHandler().play(SimpleSound.master(SoundEvents.UI_BUTTON_CLICK, 1));
				Utils.executeActionForButtonId((this.currentButton+Config.ConfigValues.BUTTONS.size()-1+Config.ConfigValues.BUTTONS.size())%Config.ConfigValues.BUTTONS.size(), this);
			}

			if((center.x + buttonSize) > mouseX && mouseX > center.x && (center.y + buttonSize) > mouseY && mouseY > center.y){
				Minecraft.getInstance().getSoundHandler().play(SimpleSound.master(SoundEvents.UI_BUTTON_CLICK, 1));
				Utils.executeActionForButtonId((this.currentButton+Config.ConfigValues.BUTTONS.size())%Config.ConfigValues.BUTTONS.size(), this);
			}

			if((centerright.x + buttonSize) > mouseX && mouseX > centerright.x && (centerright.y + buttonSize) > mouseY && mouseY > centerright.y){
				Minecraft.getInstance().getSoundHandler().play(SimpleSound.master(SoundEvents.UI_BUTTON_CLICK, 1));
				Utils.executeActionForButtonId((this.currentButton+Config.ConfigValues.BUTTONS.size()+1+Config.ConfigValues.BUTTONS.size())%Config.ConfigValues.BUTTONS.size(), this);
			}

			if((right.x + buttonSize) > mouseX && mouseX > right.x && (right.y + buttonSize) > mouseY && mouseY > right.y){
				Minecraft.getInstance().getSoundHandler().play(SimpleSound.master(SoundEvents.UI_BUTTON_CLICK, 1));
				this.currentButton = (this.currentButton+1+Config.ConfigValues.BUTTONS.size())%Config.ConfigValues.BUTTONS.size();
			}
		}
		return super.mouseClicked(mouseX, mouseY, mouseButton);
	}
}