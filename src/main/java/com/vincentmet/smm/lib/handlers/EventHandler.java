package com.vincentmet.smm.lib.handlers;

import com.vincentmet.smm.guis.GuiNewMainMenu;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class EventHandler {
	@SubscribeEvent
	public static void reregisterMainMenuGui(GuiScreenEvent e) {
		if(e.getGui() instanceof GuiMainMenu){
			e.getGui().mc.displayGuiScreen(new GuiNewMainMenu());
		}
	}
}