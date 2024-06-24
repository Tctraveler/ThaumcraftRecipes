package Tc_traveler.thaumcraftrecipes.common;

import Tc_traveler.thaumcraftrecipes.ThaumcraftRecipes;
import net.minecraftforge.common.config.Configuration;

public class ConfigLoader {
    public static Configuration config;
    public static boolean ENABLE_PECHSCURSE = true;
    public static void initConfig(){
        ThaumcraftRecipes.logger.info("Loading config...");
        config.load();
        ENABLE_PECHSCURSE = config.getBoolean("ENABLE_PECHSCURSE","recipes",true,"Setting this to false will disable [TR] adding recipe of Pech's curse.");
        config.save();
    }
}
