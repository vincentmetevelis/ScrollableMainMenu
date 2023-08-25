package com.vincentmet.smm.lib.handlers;

import com.vincentmet.smm.guis.GuiNewMainMenu;
import com.vincentmet.smm.lib.Ref;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class EventHandler {
	@SubscribeEvent
	public static void reregisterMainMenuGui(ScreenEvent e) {
		if(e.getScreen() instanceof TitleScreen){
			Minecraft.getInstance().screen = new GuiNewMainMenu();
		}
	}
}