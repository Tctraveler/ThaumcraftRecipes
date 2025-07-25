package com.Tc_traveler.thaumcraftrecipes.research;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;

import com.Tc_traveler.thaumcraftrecipes.crafting.NodeRecipe;

import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchPage;

public class TRResearchPage extends ResearchPage {

    public TRPageType trPageType = null;

    public TRResearchPage(AspectList as) {
        super(as);
    }

    public TRResearchPage(ResourceLocation image, String caption) {
        super(image, caption);
    }

    public TRResearchPage(ItemStack input) {
        super(input);
    }

    public TRResearchPage(CrucibleRecipe recipe) {
        super(recipe);
    }

    public TRResearchPage(CrucibleRecipe[] recipe) {
        super(recipe);
    }

    public TRResearchPage(IArcaneRecipe recipe) {
        super(recipe);
    }

    public TRResearchPage(IArcaneRecipe[] recipe) {
        super(recipe);
    }

    public TRResearchPage(InfusionEnchantmentRecipe recipe) {
        super(recipe);
    }

    public TRResearchPage(InfusionRecipe recipe) {
        super(recipe);
    }

    public TRResearchPage(InfusionRecipe[] recipe) {
        super(recipe);
    }

    public TRResearchPage(IRecipe recipe) {
        super(recipe);
    }

    public TRResearchPage(IRecipe[] recipe) {
        super(recipe);
    }

    public TRResearchPage(List recipe) {
        super(recipe);
    }

    public TRResearchPage(String research, String text) {
        super(research, text);
    }

    public TRResearchPage(String text) {
        super(text);
    }

    public TRResearchPage(NodeRecipe recipe) {
        super("");
        this.recipe = recipe;
        this.trPageType = TRPageType.NODE_CRAFTING;
    }

    public String getTranslatedText() {
        return super.getTranslatedText();
    }

    public static enum TRPageType {
        NODE_CRAFTING;
    }
}
