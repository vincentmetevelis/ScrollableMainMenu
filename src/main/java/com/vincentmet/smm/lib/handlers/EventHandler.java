package com.vincentmet.smm.lib.handlers;

import com.vincentmet.smm.guis.GuiNewMainMenu;
import com.vincentmet.smm.lib.Ref;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Ref.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class EventHandler {
	@SubscribeEvent
	public static void reregisterMainMenuGui(GuiScreenEvent e) {
		if(e.getGui() instanceof TitleScreen){
			e.getGui().getMinecraft().setScreen(new GuiNewMainMenu());
		}
	}
}