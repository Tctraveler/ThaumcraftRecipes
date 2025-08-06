package com.Tc_traveler.thaumcraftrecipes.crafting;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import thaumcraft.api.ThaumcraftApiHelper;
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

    public boolean matches(AspectList itags, ItemStack cat, EntityPlayer player) {
        if (!this.key.isEmpty() && !ThaumcraftApiHelper.isResearchComplete(player.getCommandSenderName(), this.key)) {
            return false;
        }
        if (this.input instanceof ItemStack && !ThaumcraftApiHelper.itemMatches((ItemStack) this.input, cat, false)) {
            return false;
        } else {
            if (this.input instanceof ArrayList && !((ArrayList<?>) this.input).isEmpty()) {
                ItemStack[] ores = (ItemStack[]) ((ArrayList) this.input).toArray(new ItemStack[0]);
                if (!ThaumcraftApiHelper.containsMatch(false, new ItemStack[] { cat }, ores)) {
                    return false;
                }
            }

            if (itags == null) {
                return false;
            } else {
                for (Aspect tag : this.aspects.getAspects()) {
                    if (itags.getAmount(tag) < this.aspects.getAmount(tag)) {
                        return false;
                    }
                }

                return true;
            }
        }
    }
}
