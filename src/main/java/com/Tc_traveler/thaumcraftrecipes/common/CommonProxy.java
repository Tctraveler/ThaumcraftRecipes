package com.Tc_traveler.thaumcraftrecipes.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.config.Configuration;

import com.Tc_traveler.thaumcraftrecipes.ThaumcraftRecipesRecipe;
import com.Tc_traveler.thaumcraftrecipes.creativetab.CreativeTabsLoader;
import com.Tc_traveler.thaumcraftrecipes.items.TRItems;
import com.Tc_traveler.thaumcraftrecipes.research.myResearch;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        ConfigLoader.config = new Configuration(event.getSuggestedConfigurationFile());
        ConfigLoader.initConfig();
        CreativeTabsLoader.init();
        TRItems.init();
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {
        ThaumcraftRecipesRecipe.addRecipes();
        myResearch.addResearch();
    }

    public EntityPlayer getClientPlayer() {
        return null;
    }
}
