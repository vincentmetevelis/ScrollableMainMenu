package com.vincentmet.smm;

import com.vincentmet.smm.lib.Ref;
import com.vincentmet.smm.lib.handlers.EventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Ref.MODID)
public class BaseClass{
    public BaseClass(){
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);

    }

    private void setup(final FMLClientSetupEvent event){
        Config.readConfigToMemory(Ref.PATH_CONFIG, "config.json");
    }
}
