package Tc_traveler.thaumcraftrecipes.items;

import Tc_traveler.thaumcraftrecipes.ThaumcraftRecipes;
import Tc_traveler.thaumcraftrecipes.items.foci.ItemAlumentumSpawnerFocus;
import Tc_traveler.thaumcraftrecipes.items.foci.ItemNitorFocus;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class TRItems {
    public static Item icon;
    public static Item alumentumSpawnerFoci;
    public static Item nitorFoci;
    public static void init() {
        icon = new Item().setUnlocalizedName("TRicon").setTextureName(ThaumcraftRecipes.MODID+":TRicon");
        GameRegistry.registerItem(icon, icon.getUnlocalizedName());
        alumentumSpawnerFoci = new ItemAlumentumSpawnerFocus();
        GameRegistry.registerItem(alumentumSpawnerFoci, "focusAlumentumSpawner");
        nitorFoci = new ItemNitorFocus();
        GameRegistry.registerItem(nitorFoci, "focusNitor");
    }
}
