package com.Tc_traveler.thaumcraftrecipes.items;

import net.minecraft.item.Item;

import com.Tc_traveler.thaumcraftrecipes.ThaumcraftRecipes;
import com.Tc_traveler.thaumcraftrecipes.items.foci.ItemAlumentumSpawnerFocus;
import com.Tc_traveler.thaumcraftrecipes.items.foci.ItemNitorFocus;
import com.Tc_traveler.thaumcraftrecipes.items.foci.ItemTransmutationFocus;

import cpw.mods.fml.common.registry.GameRegistry;

public class TRItems {

    public static Item icon;
    public static Item alumentumSpawnerFoci;
    public static Item nitorFoci;
    public static Item transmutationFoci;

    public static void init() {
        icon = new Item().setUnlocalizedName("TRicon")
            .setTextureName(ThaumcraftRecipes.MODID + ":TRicon");
        GameRegistry.registerItem(icon, icon.getUnlocalizedName());
        alumentumSpawnerFoci = new ItemAlumentumSpawnerFocus();
        GameRegistry.registerItem(alumentumSpawnerFoci, "focusAlumentumSpawner");
        nitorFoci = new ItemNitorFocus();
        GameRegistry.registerItem(nitorFoci, "focusNitor");
        transmutationFoci = new ItemTransmutationFocus();
        GameRegistry.registerItem(transmutationFoci, "focusTransmutation");
    }
}
