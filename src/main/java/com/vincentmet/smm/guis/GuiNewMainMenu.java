package com.vincentmet.smm.guis;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.vincentmet.smm.Config;
import com.vincentmet.smm.lib.Ref;
import com.vincentmet.smm.lib.Utils;
import com.vincentmet.smm.lib.gui.GuiLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;

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
		super(Component.literal("Main Menu"));
	}

	@Override
	public void render(PoseStack stack, int mouseX, int mouseY, float partialTicks){
		stack.pushPose();
		RenderSystem.setShaderTexture(0, background);
		float x = (float)width/1280;
		float y = (float)height/720;
		stack.scale(x, y, 1.0f);
		Screen.blit(stack, 0, 0, 0, 0, 1280, 720, 1280, 720);
		stack.popPose();
		
		if(Config.ConfigValues.IS_LOGO_ENABLED){
			stack.pushPose();
			RenderSystem.setShaderTexture(0, logo);
			Screen.blit(stack, (width>>1)-(200>>1), height>>4, 0, 0, 200, 150, 200, 150);
			RenderSystem.disableBlend();
			stack.popPose();
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
			Utils.addHexaButtonLeft(stack, left.x, left.y, mouseX, mouseY);
			Utils.addHexaButton(stack, leftcenter.x, leftcenter.y, mouseX, mouseY, (this.currentButton-1+Config.ConfigValues.BUTTONS.size())%Config.ConfigValues.BUTTONS.size());
			Utils.addHexaButton(stack, center.x, center.y, mouseX, mouseY, (this.currentButton+Config.ConfigValues.BUTTONS.size())%Config.ConfigValues.BUTTONS.size());
			Utils.addHexaButton(stack, centerright.x, centerright.y, mouseX, mouseY, (this.currentButton+1+Config.ConfigValues.BUTTONS.size())%Config.ConfigValues.BUTTONS.size());
			Utils.addHexaButtonRight(stack, right.x, right.y, mouseX, mouseY);
		}
	}
	
	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton){
		int buttonSize = 32 * Config.ConfigValues.BUTTON_SIZE_MULTIPLIER;
		if (Config.ConfigValues.BUTTONS.size() >= 1){
			if((left.x + buttonSize) > mouseX && mouseX > left.x && (left.y + buttonSize) > mouseY && mouseY > left.y){
				Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1));
				this.currentButton = (this.currentButton-1+Config.ConfigValues.BUTTONS.size())%Config.ConfigValues.BUTTONS.size();
			}

			if((leftcenter.x + buttonSize) > mouseX && mouseX > leftcenter.x && (leftcenter.y + buttonSize) > mouseY && mouseY > leftcenter.y){
				Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1));
				Utils.executeActionForButtonId((this.currentButton+Config.ConfigValues.BUTTONS.size()-1+Config.ConfigValues.BUTTONS.size())%Config.ConfigValues.BUTTONS.size(), this);
			}

			if((center.x + buttonSize) > mouseX && mouseX > center.x && (center.y + buttonSize) > mouseY && mouseY > center.y){
				Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1));
				Utils.executeActionForButtonId((this.currentButton+Config.ConfigValues.BUTTONS.size())%Config.ConfigValues.BUTTONS.size(), this);
			}

			if((centerright.x + buttonSize) > mouseX && mouseX > centerright.x && (centerright.y + buttonSize) > mouseY && mouseY > centerright.y){
				Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1));
				Utils.executeActionForButtonId((this.currentButton+Config.ConfigValues.BUTTONS.size()+1+Config.ConfigValues.BUTTONS.size())%Config.ConfigValues.BUTTONS.size(), this);
			}

			if((right.x + buttonSize) > mouseX && mouseX > right.x && (right.y + buttonSize) > mouseY && mouseY > right.y){
				Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1));
				this.currentButton = (this.currentButton+1+Config.ConfigValues.BUTTONS.size())%Config.ConfigValues.BUTTONS.size();
			}
		}
		return super.mouseClicked(mouseX, mouseY, mouseButton);
	}
}