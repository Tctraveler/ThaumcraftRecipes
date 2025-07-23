package com.Tc_traveler.thaumcraftrecipes.items.foci;

import java.awt.Color;
import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.Tc_traveler.thaumcraftrecipes.ThaumcraftRecipes;
import com.Tc_traveler.thaumcraftrecipes.creativetab.CreativeTabsLoader;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ItemTransmutationFocus extends ItemFocusBasic {

    private final String name = "focus_transmutation";

    public ItemTransmutationFocus() {
        setMaxDamage(0);
        setNoRepair();
        setMaxStackSize(1);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabsLoader.TRTabs);
    }

    @Override
    public AspectList getVisCost(ItemStack focus) {
        return new AspectList().add(Aspect.ORDER, 500)
            .add(Aspect.WATER, 500)
            .add(Aspect.EARTH, 500)
            .add(Aspect.FIRE, 500)
            .add(Aspect.AIR, 500)
            .add(Aspect.ENTROPY, 500);
    }

    @Override
    public boolean isVisCostPerTick(ItemStack focusstack) {
        return false;
    }

    @Override
    public ItemStack onFocusRightClick(ItemStack wandstack, World world, EntityPlayer player,
        MovingObjectPosition movingobjectposition) {
        if (movingobjectposition == null) {
            return wandstack;
        }
        ItemWandCasting wandCasting = (ItemWandCasting) wandstack.getItem();
        int x = movingobjectposition.blockX;
        int y = movingobjectposition.blockY;
        int z = movingobjectposition.blockZ;
        if ((world.getBlock(x, y, z) == Blocks.stone || world.getBlock(x, y, z) == Blocks.cobblestone)
            && wandCasting.consumeAllVis(wandstack, player, this.getVisCost(wandstack), true, false)) {
            world.setBlock(x, y, z, ConfigBlocks.blockCustomOre, (new Random()).nextInt(6) + 1, 3);
            if (!world.isRemote) {
                world.playSoundEffect(x, y, z, "thaumcraft:wand", 1.0F, 1.0F);
            }
        }
        return wandstack;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.icon = iconRegister.registerIcon(ThaumcraftRecipes.MODID + ":" + name);
    }

    @Override
    public int getFocusColor(ItemStack focusstack) {
        EntityPlayer player = ThaumcraftRecipes.proxy.getClientPlayer();
        return player == null ? 0xFFFFFF : Color.HSBtoRGB(player.ticksExisted * 2 % 720 / 720F, 1F, 1F);
    }

    @Override
    public String getSortingHelper(final ItemStack focusstack) {
        return "Transmutation" + super.getSortingHelper(focusstack);
    }

    @Override
    public WandFocusAnimation getAnimation(ItemStack focusstack) {
        return WandFocusAnimation.CHARGE;
    }
}
