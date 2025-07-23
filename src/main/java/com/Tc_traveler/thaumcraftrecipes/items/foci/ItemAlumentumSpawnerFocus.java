package com.Tc_traveler.thaumcraftrecipes.items.foci;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
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
import thaumcraft.common.entities.projectile.EntityAlumentum;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ItemAlumentumSpawnerFocus extends ItemFocusBasic {

    private final String name = "focus_alumentum_spawner";

    public ItemAlumentumSpawnerFocus() {
        setMaxDamage(0);
        setNoRepair();
        setMaxStackSize(1);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabsLoader.TRTabs);
    }

    @Override
    public AspectList getVisCost(ItemStack focus) {
        return new AspectList().add(Aspect.ENTROPY, 1000)
            .add(Aspect.ORDER, 500);
    }

    @Override
    public int getActivationCooldown(ItemStack stack) {
        return 500;
    }

    @Override
    public WandFocusAnimation getAnimation(ItemStack stack) {
        return WandFocusAnimation.CHARGE;
    }

    @Override
    public int getFocusColor(ItemStack focus) {
        return 0x8A2BE2;
    }

    @Override
    public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player,
        MovingObjectPosition movingObjectPosition) {
        ItemWandCasting wandCasting = (ItemWandCasting) itemstack.getItem();
        EntityAlumentum entityAlumentum = new EntityAlumentum(world, (EntityLivingBase) player);
        if ((!world.isRemote)
            && wandCasting.consumeAllVis(itemstack, player, this.getVisCost(itemstack), true, false)) {
            world.spawnEntityInWorld(entityAlumentum);
        }
        return itemstack;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.icon = iconRegister.registerIcon(ThaumcraftRecipes.MODID + ":" + name);
    }

    @Override
    public String getSortingHelper(final ItemStack stack) {
        return "ALUMENTUM" + super.getSortingHelper(stack);
    }

    @Override
    public boolean isVisCostPerTick(ItemStack focusstack) {
        return false;
    }
}
