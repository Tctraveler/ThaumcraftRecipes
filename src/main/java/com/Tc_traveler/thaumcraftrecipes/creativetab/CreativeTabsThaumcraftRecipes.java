package com.Tc_traveler.thaumcraftrecipes.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.Tc_traveler.thaumcraftrecipes.ThaumcraftRecipes;
import com.Tc_traveler.thaumcraftrecipes.items.TRItems;

public class CreativeTabsThaumcraftRecipes extends CreativeTabs {

    public CreativeTabsThaumcraftRecipes() {
        super(ThaumcraftRecipes.MODID);
        setBackgroundImageName("TRCreativeTabs.png");
    }

    @Override
    public Item getTabIconItem() {
        return TRItems.icon;
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }

    @Override
    public int getSearchbarWidth() {
        return 89;
    }
}
