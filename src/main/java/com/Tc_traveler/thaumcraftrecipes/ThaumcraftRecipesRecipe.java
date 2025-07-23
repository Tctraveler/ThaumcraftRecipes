package com.Tc_traveler.thaumcraftrecipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.Tc_traveler.thaumcraftrecipes.common.ConfigLoader;
import com.Tc_traveler.thaumcraftrecipes.items.TRItems;

import cpw.mods.fml.common.registry.GameRegistry;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.config.ConfigResearch;

public class ThaumcraftRecipesRecipe {

    public static void addRecipes() {
        ConfigResearch.recipes.put(
            "trgreatwoodsapling",
            ThaumcraftApi.addCrucibleRecipe(
                "ADDGREATWOOD",
                new ItemStack(GameRegistry.findItem("Thaumcraft", "blockCustomPlant"), 1, 0),
                new ItemStack(Blocks.sapling, 1, 1),
                (new AspectList()).add(Aspect.PLANT, 4)
                    .add(Aspect.TREE, 4)
                    .add(Aspect.CRAFT, 4)));
        ConfigResearch.recipes.put(
            "trsilverwoodsapling",
            ThaumcraftApi.addInfusionCraftingRecipe(
                "ADDSILVERWOOD",
                new ItemStack(ConfigBlocks.blockCustomPlant, 1, 1),
                12,
                (new AspectList()).add(Aspect.PLANT, 16)
                    .add(Aspect.TREE, 16)
                    .add(Aspect.ORDER, 32)
                    .add(Aspect.MAGIC, 32)
                    .add(Aspect.CRAFT, 32),
                new ItemStack(ConfigBlocks.blockCustomPlant, 1, 0),
                new ItemStack[] { new ItemStack(ConfigBlocks.blockMagicalLog, 1, 1),
                    new ItemStack(ConfigBlocks.blockMagicalLog, 1, 1),
                    new ItemStack(ConfigBlocks.blockCustomPlant, 1, 2), new ItemStack(ConfigItems.itemShard, 1, 6),
                    new ItemStack(ConfigItems.itemShard, 1, 6) }));
        ConfigResearch.recipes.put(
            "trmanabean",
            ThaumcraftApi.addArcaneCraftingRecipe(
                "ADDMANABEAN",
                new ItemStack(ConfigItems.itemManaBean, 1),
                new AspectList().add(Aspect.ORDER, 25)
                    .add(Aspect.EARTH, 25),
                new Object[] { "aaa", "bcd", "aaa", 'a', new ItemStack(ConfigItems.itemResource, 1, 14), 'b',
                    new ItemStack(ConfigItems.itemShard, 1, 3), 'c', new ItemStack(Items.dye, 1, 3), 'd',
                    new ItemStack(ConfigItems.itemShard, 1, 4) }));
        ConfigResearch.recipes.put(
            "tralumentumspawnerfocus",
            ThaumcraftApi.addInfusionCraftingRecipe(
                "ALUMENTUMSPAWNERFOCUS",
                new ItemStack(TRItems.alumentumSpawnerFoci, 1),
                5,
                (new AspectList().add(Aspect.FIRE, 25)
                    .add(Aspect.ENTROPY, 16)
                    .add(Aspect.ENERGY, 25)
                    .add(Aspect.CRAFT, 16)),
                new ItemStack(ConfigItems.itemResource, 1, 0),
                (new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 5), new ItemStack(Items.quartz, 1),
                    new ItemStack(ConfigItems.itemResource, 1, 0), new ItemStack(Items.quartz, 1),
                    new ItemStack(ConfigItems.itemShard, 1, 5), new ItemStack(Items.quartz, 1),
                    new ItemStack(ConfigItems.itemResource, 1, 0), new ItemStack(Items.quartz, 1) })));
        ConfigResearch.recipes.put(
            "trnitorfocus",
            ThaumcraftApi.addInfusionCraftingRecipe(
                "NITORFOCUS",
                new ItemStack(TRItems.nitorFoci, 1),
                5,
                (new AspectList().add(Aspect.FIRE, 25)
                    .add(Aspect.ENERGY, 16)
                    .add(Aspect.HEAL, 8)
                    .add(Aspect.CRAFT, 16)),
                new ItemStack(ConfigItems.itemResource, 1, 1),
                (new ItemStack[] { new ItemStack(ConfigItems.itemShard, 1, 3), new ItemStack(Items.quartz, 1),
                    new ItemStack(ConfigItems.itemResource, 1, 1), new ItemStack(Items.quartz, 1),
                    new ItemStack(ConfigItems.itemShard, 1, 4), new ItemStack(Items.quartz, 1),
                    new ItemStack(ConfigItems.itemResource, 1, 1), new ItemStack(Items.quartz, 1) })));
        if (ConfigLoader.ENABLE_PECHSCURSE) {
            ConfigResearch.recipes.put(
                "trpech'scursefocus",
                ThaumcraftApi.addInfusionCraftingRecipe(
                    "PECH'SCURSEFOCUS",
                    new ItemStack(ConfigItems.itemFocusPech, 1),
                    16,
                    (new AspectList().add(Aspect.CRAFT, 16)
                        .add(Aspect.GREED, 16)
                        .add(Aspect.HUNGER, 8)
                        .add(Aspect.POISON, 4)
                        .add(Aspect.MAN, 4)),
                    new ItemStack(ConfigItems.itemFocusExcavation, 1),
                    new ItemStack[] { new ItemStack(Items.bone, 1), new ItemStack(ConfigItems.itemShard, 1, 5),
                        new ItemStack(Items.quartz, 1), new ItemStack(Items.ender_pearl, 1),
                        new ItemStack(Items.spider_eye, 1), new ItemStack(ConfigItems.itemShard, 1, 5),
                        new ItemStack(Items.quartz, 1), new ItemStack(Items.ender_pearl, 1) }));
        }
        ConfigResearch.recipes.put(
            "trtransmutationfocus",
            ThaumcraftApi.addInfusionCraftingRecipe(
                "TRANSMUTATIONFOCUS",
                new ItemStack(TRItems.transmutationFoci, 1),
                9,
                (new AspectList().add(Aspect.ORDER, 64)
                    .add(Aspect.WATER, 64)
                    .add(Aspect.EARTH, 64)
                    .add(Aspect.FIRE, 64)
                    .add(Aspect.AIR, 64)
                    .add(Aspect.ENTROPY, 64)),
                new ItemStack(ConfigBlocks.blockCrystal, 1, 6),
                new ItemStack[] { new ItemStack(ConfigBlocks.blockCrystal, 1, 0),
                    new ItemStack(ConfigBlocks.blockCrystal, 1, 1), new ItemStack(ConfigBlocks.blockCrystal, 1, 2),
                    new ItemStack(ConfigBlocks.blockCrystal, 1, 3), new ItemStack(ConfigBlocks.blockCrystal, 1, 4),
                    new ItemStack(ConfigBlocks.blockCrystal, 1, 5) }));
    }
}
