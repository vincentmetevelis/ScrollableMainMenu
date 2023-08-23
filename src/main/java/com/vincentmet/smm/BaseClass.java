package com.vincentmet.smm;

import com.vincentmet.smm.lib.Ref;
import com.vincentmet.smm.lib.handlers.EventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Ref.MODID)
public class BaseClass{
    public static BaseClass INSTANCE;

    public BaseClass(){
        INSTANCE = this;
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(EventHandler.class);

    }

    private void setup(final FMLCommonSetupEvent event){
        Config.readConfigToMemory(Ref.PATH_CONFIG, "config.json");
    }
}
