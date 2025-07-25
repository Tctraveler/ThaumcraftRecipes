package com.Tc_traveler.thaumcraftrecipes.crafting;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class NodeRecipe {

    private ItemStack output;
    public Object input;
    public AspectList aspects;
    public String key;
    public int hash;

    public NodeRecipe(String researchKey, ItemStack out, Object in, AspectList tags) {
        this.output = out;
        this.aspects = tags;
        this.key = researchKey;
        this.input = in;
        if (in instanceof String) {
            this.input = OreDictionary.getOres((String) in);
        }

        StringBuilder hc = new StringBuilder(researchKey + out.toString());

        for (Aspect tag : tags.getAspects()) {
            hc.append(tag.getTag())
                .append(tags.getAmount(tag));
        }

        if (in instanceof ItemStack) {
            hc.append(in);
        } else if (in instanceof ArrayList<?> && !((ArrayList<?>) this.input).isEmpty()) {
            if (((ArrayList<?>) in).get(0) instanceof ItemStack) {
                for (ItemStack is : (ArrayList<ItemStack>) this.input) {
                    hc.append(is.toString());
                }
            }
        }

        this.hash = hc.toString()
            .hashCode();
    }

    public ItemStack getRecipeOutput() {
        return output;
    }
}
