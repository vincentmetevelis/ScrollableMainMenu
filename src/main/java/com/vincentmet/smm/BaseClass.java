package com.vincentmet.smm;

import com.vincentmet.smm.lib.Ref;
import com.vincentmet.smm.network.proxy.ClientProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Ref.MODID, name = Ref.NAME, version = Ref.VERSION, clientSideOnly = true, dependencies = "before-required-client:resourceloader@[1.5.3,);")
public class BaseClass{
    @SidedProxy(clientSide = Ref.PROXY_CLIENT)
    public static ClientProxy proxy;
    
    @Mod.Instance(Ref.MODID)
    public static BaseClass instance;
    
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent e){
        proxy.preInit(e);
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent e){
        proxy.init(e);
    }
    
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent e){
        proxy.postInit(e);
    }
}
