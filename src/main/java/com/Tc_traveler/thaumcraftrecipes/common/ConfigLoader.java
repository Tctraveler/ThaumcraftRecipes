package com.Tc_traveler.thaumcraftrecipes.common;

import net.minecraftforge.common.config.Configuration;

import com.Tc_traveler.thaumcraftrecipes.ThaumcraftRecipes;

public class ConfigLoader {

    public static Configuration config;
    public static boolean ENABLE_PECHSCURSE = true;

    public static void initConfig() {
        ThaumcraftRecipes.logger.info("Loading config...");
        config.load();
        ENABLE_PECHSCURSE = config.getBoolean(
            "ENABLE_PECHSCURSE",
            "recipes",
            true,
            "Setting this to false will disable [TR] adding recipe of Pech's curse.");
        config.save();
    }
}
