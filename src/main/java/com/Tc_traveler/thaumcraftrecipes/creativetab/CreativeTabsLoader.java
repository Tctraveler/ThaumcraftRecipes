package com.Tc_traveler.thaumcraftrecipes.creativetab;

import net.minecraft.creativetab.CreativeTabs;

public class CreativeTabsLoader {

    public static CreativeTabs TRTabs;

    public static void init() {
        TRTabs = new CreativeTabsThaumcraftRecipes();
    }
}
