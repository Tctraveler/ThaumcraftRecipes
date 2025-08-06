package com.Tc_traveler.thaumcraftrecipes.helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.Tc_traveler.thaumcraftrecipes.crafting.NodeRecipe;

import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;

public class ThaumcraftCraftingManager {

    public static NodeRecipe findMatchingNodeRecipe(AspectList aspects, ItemStack input, EntityPlayer player) {
        NodeRecipe recipe = null;
        for (var r : ThaumcraftApi.getCraftingRecipes()) {
            if (r instanceof NodeRecipe && ((NodeRecipe) r).matches(aspects, input, player)) {
                recipe = (NodeRecipe) r;
                break;
            }
        }
        return recipe;
    }

}
