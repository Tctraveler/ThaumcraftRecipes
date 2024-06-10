package Tc_traveler.thaumcraftrecipes;

import Tc_traveler.thaumcraftrecipes.common.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ThaumcraftRecipes.MODID,name = ThaumcraftRecipes.MODNAME,version = ThaumcraftRecipes.VERSION,dependencies = "required-after:Thaumcraft@[4.2.3.5,)")
public class ThaumcraftRecipes {
    @Mod.Instance("thaumcraftrecipes")
    public static ThaumcraftRecipes instance;
    public static final String MODID = "thaumcraftrecipes";
    public static final String VERSION = "1.0";
    public static final String MODNAME = "ThaumcraftRecipes";
    @SidedProxy(clientSide = "Tc_traveler.thaumcraftrecipes.client.ClientProxy",serverSide = "Tc_traveler.thaumcraftrecipes.common.CommonProxy")
    public static CommonProxy proxy;
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        proxy.preInit(event);
    }
    @EventHandler
    public void init(FMLInitializationEvent event){

    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event){
        proxy.postInit(event);
    }
}
