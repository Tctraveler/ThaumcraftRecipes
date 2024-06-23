package Tc_traveler.thaumcraftrecipes.items.foci;

import Tc_traveler.thaumcraftrecipes.ThaumcraftRecipes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ItemNitorFocus extends ItemFocusBasic {
    private final String name = "focus_nitor";
    public ItemNitorFocus(){
        this.setUnlocalizedName(name);
    }
    public void addRegenerationBuff(EntityPlayer player){
        PotionEffect potionEffect = new PotionEffect(Potion.heal.id, 100, 1);
        player.addPotionEffect(potionEffect);
    }
    @Override
    public AspectList getVisCost(ItemStack focus) {
        return new AspectList().add(Aspect.ORDER,100).add(Aspect.WATER,200).add(Aspect.EARTH,200);
    }
    @Override
    public WandFocusAnimation getAnimation(ItemStack stack){
        return WandFocusAnimation.CHARGE;
    }
    @Override
    public int getFocusColor(ItemStack focus){
        return 0x50FF57;
    }
    @Override
    public int getActivationCooldown(ItemStack stack) {
        return 60000;
    }
    @Override
    public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition movingObjectPosition) {
        ItemWandCasting wandCasting = (ItemWandCasting) itemstack.getItem();
        if(!world.isRemote&&wandCasting.consumeAllVis(itemstack, player,this.getVisCost(itemstack),true, false)){
            addRegenerationBuff(player);
        }
        return itemstack;
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister){
        this.icon = iconRegister.registerIcon(ThaumcraftRecipes.MODID+":"+name);
    }
}
