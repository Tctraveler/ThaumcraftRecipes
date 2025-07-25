package com.Tc_traveler.thaumcraftrecipes.research;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.Tc_traveler.thaumcraftrecipes.common.ConfigLoader;
import com.Tc_traveler.thaumcraftrecipes.crafting.NodeRecipe;
import com.Tc_traveler.thaumcraftrecipes.items.TRItems;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.config.ConfigResearch;

// col为列 row为行 同时为正时在右下
public class myResearch {

    public static void addResearch() {
        ResearchCategories.registerCategory(
            "SYNTHESIS",
            new ResourceLocation("thaumcraftrecipes", "textures/items/TRicon.png"),
            new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png"));
        (new ResearchItem(
            "DESCRIPTION",
            "SYNTHESIS",
            new AspectList().add(Aspect.EXCHANGE, 4)
                .add(Aspect.CRAFT, 4),
            0,
            0,
            7,
            new ResourceLocation("thaumcraft", "textures/aspects/_unknown.png"))).setSpecial()
                .setPages(new ResearchPage[] { new ResearchPage("tr.research.description_1") })
                .setLost()
                .setAspectTriggers(Aspect.CRAFT)
                .registerResearchItem();
        (new ResearchItem(
            "ADDGREATWOOD",
            "SYNTHESIS",
            new AspectList().add(Aspect.PLANT, 4)
                .add(Aspect.TREE, 6)
                .add(Aspect.CRAFT, 8)
                .add(Aspect.MAGIC, 4),
            2,
            -2,
            2,
            new ResourceLocation("thaumcraft", "textures/blocks/greatwoodsapling.png")))
                .setParents(new String[] { "DESCRIPTION" })
                .setPages(
                    new ResearchPage[] { new ResearchPage("tr.research.addgreatwood_1"),
                        new ResearchPage((CrucibleRecipe) ConfigResearch.recipes.get("trgreatwoodsapling")) })
                .setConcealed()
                .registerResearchItem();
        (new ResearchItem(
            "ADDSILVERWOOD",
            "SYNTHESIS",
            new AspectList().add(Aspect.PLANT, 4)
                .add(Aspect.TREE, 6)
                .add(Aspect.ORDER, 6)
                .add(Aspect.CRAFT, 8)
                .add(Aspect.MAGIC, 8),
            2,
            2,
            7,
            new ResourceLocation("thaumcraft", "textures/blocks/silverwoodsapling.png")))
                .setParents(new String[] { "ADDGREATWOOD", "INFUSION" })
                .setPages(
                    new ResearchPage[] { new ResearchPage("tr.research.addsilverwood_1"),
                        new ResearchPage((InfusionRecipe) ConfigResearch.recipes.get("trsilverwoodsapling")) })
                .setConcealed()
                .registerResearchItem();
        (new ResearchItem(
            "ADDMANABEAN",
            "SYNTHESIS",
            new AspectList().add(Aspect.PLANT, 4)
                .add(Aspect.CRAFT, 4)
                .add(Aspect.MAGIC, 4),
            4,
            0,
            1,
            new ItemStack(ConfigItems.itemManaBean))).setParents(new String[] { "ADDGREATWOOD", "ADDSILVERWOOD" })
                .setSecondary()
                .setPages(
                    new ResearchPage[] { new ResearchPage("tr.research.addmanabean_1"),
                        new ResearchPage((ShapedArcaneRecipe) ConfigResearch.recipes.get("trmanabean")) })
                .setConcealed()
                .registerResearchItem();
        (new ResearchItem(
            "ALUMENTUMSPAWNERFOCUS",
            "SYNTHESIS",
            new AspectList().add(Aspect.ENERGY, 4)
                .add(Aspect.ENTROPY, 4)
                .add(Aspect.FIRE, 4)
                .add(Aspect.CRAFT, 4)
                .add(Aspect.MAGIC, 4),
            -2,
            -2,
            2,
            new ItemStack(TRItems.alumentumSpawnerFoci)))
                .setParents(new String[] { "DESCRIPTION", "ALUMENTUM", "INFUSION" })
                .setPages(
                    new ResearchPage[] { new ResearchPage("tr.research.alumentumspawnerfocus_1"),
                        new ResearchPage((InfusionRecipe) ConfigResearch.recipes.get("tralumentumspawnerfocus")) })
                .setConcealed()
                .registerResearchItem();
        (new ResearchItem(
            "NITORFOCUS",
            "SYNTHESIS",
            new AspectList().add(Aspect.ENERGY, 4)
                .add(Aspect.FIRE, 4)
                .add(Aspect.CRAFT, 4)
                .add(Aspect.HEAL, 4),
            -2,
            2,
            2,
            new ItemStack(TRItems.nitorFoci))).setParents(new String[] { "DESCRIPTION", "NITOR", "INFUSION" })
                .setPages(
                    new ResearchPage[] { new ResearchPage("tr.research.nitorfocus_1"),
                        new ResearchPage((InfusionRecipe) ConfigResearch.recipes.get("trnitorfocus")) })
                .setConcealed()
                .registerResearchItem();
        if (ConfigLoader.ENABLE_PECHSCURSE) {
            (new ResearchItem(
                "PECH'SCURSEFOCUS",
                "SYNTHESIS",
                new AspectList().add(Aspect.CRAFT, 4)
                    .add(Aspect.GREED, 4)
                    .add(Aspect.HUNGER, 4)
                    .add(Aspect.POISON, 4),
                -4,
                2,
                9,
                new ItemStack(ConfigItems.itemFocusPech)))
                    .setParents(new String[] { "ALUMENTUMSPAWNERFOCUS", "NITORFOCUS" })
                    .setPages(
                        new ResearchPage[] { new ResearchPage("tr.research.pech'scursefocus_1"),
                            new ResearchPage((InfusionRecipe) ConfigResearch.recipes.get("trpech'scursefocus")) })
                    .setConcealed()
                    .registerResearchItem();
        }
        (new ResearchItem(
            "TRANSMUTATIONFOCUS",
            "SYNTHESIS",
            new AspectList().add(Aspect.CRAFT, 4)
                .add(Aspect.EXCHANGE, 4)
                .add(Aspect.CRYSTAL, 8),
            -4,
            -2,
            5,
            new ItemStack(TRItems.transmutationFoci)))
                .setParents(new String[] { "ALUMENTUMSPAWNERFOCUS", "NITORFOCUS" })
                .setPages(
                    new ResearchPage[] { new ResearchPage("tr.research.transmutationfocus_1"),
                        new ResearchPage((InfusionRecipe) ConfigResearch.recipes.get("trtransmutationfocus")) })
                .setConcealed()
                .registerResearchItem();
        (new ResearchItem(
            "NODECRAFTINGTEST",
            "SYNTHESIS",
            new AspectList().add(Aspect.CRAFT, 4),
            4,
            4,
            5,
            new ItemStack(ConfigItems.itemFocusPech))).setParents("DESCRIPTION")
                .setPages(
                    new ResearchPage("tr.research.nodecraftingtest_1"),
                    new TRResearchPage((NodeRecipe) ConfigResearch.recipes.get("trnodecraftingtest")))
                .registerResearchItem();
    }
}
