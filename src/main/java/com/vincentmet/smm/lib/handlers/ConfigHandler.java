package com.vincentmet.smm.lib.handlers;

import com.vincentmet.smm.network.proxy.ClientProxy;
import net.minecraftforge.common.config.Configuration;

import java.math.BigDecimal;

public class ConfigHandler {
    public static boolean is_logo_enabled = true;
    public static int button_size = 0;
    public static int background_width = 1280;
    public static int background_height = 720;
    public static int logo_width = 200;
    public static int logo_height = 150;
    
    public static void readCfg(){
        Configuration cfg = ClientProxy.cfg;
        try{
            cfg.load();
            initCfg(cfg);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cfg.hasChanged()){
                cfg.save();
            }
        }
    }
    private static void initCfg(Configuration cfg){
        is_logo_enabled = cfg.getBoolean("Logo Enabled", "enabling", is_logo_enabled, "True=Logo Enabled; False=Logo Disabled;");
        
        background_width = cfg.getInt("Background Image Width", "sizes", background_width, 1, 3840, "Max 4K");
        background_height = cfg.getInt("Background Image Height", "sizes", background_height, 1, 2160, "Max 4K");
        
        logo_width = cfg.getInt("Logo Image Width", "sizes", logo_width, 1, 200, "Max 200x150");
        logo_height = cfg.getInt("Logo Image Height", "sizes", logo_height, 1, 150, "Max 200x150");
        
        button_size = cfg.getInt("Button Size", "sizes", button_size, 0, 2, "0=Small;1=Medium;2=Big;");
    }
}