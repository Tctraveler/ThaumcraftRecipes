package com.Tc_traveler.thaumcraftrecipes.helper;

import static thaumcraft.api.ThaumcraftApi.getCraftingRecipes;

import net.minecraft.item.ItemStack;

import com.Tc_traveler.thaumcraftrecipes.crafting.NodeRecipe;

import thaumcraft.api.aspects.AspectList;

public class ThaumcraftApiHelper {

    public static NodeRecipe addNodeRecipe(String key, ItemStack output, Object input, AspectList tags) {
        NodeRecipe nr = new NodeRecipe(key, output, input, tags);
        getCraftingRecipes().add(nr);
        return nr;
    }

}
