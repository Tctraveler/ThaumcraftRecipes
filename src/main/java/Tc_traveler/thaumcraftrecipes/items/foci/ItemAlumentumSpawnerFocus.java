package Tc_traveler.thaumcraftrecipes.items.foci;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.entities.projectile.EntityAlumentum;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ItemAlumentumSpawnerFocus extends ItemTRFocus{
    public ItemAlumentumSpawnerFocus(){
        super("alumentum_spawner");
        this.setUnlocalizedName("focus_alumentum_spawner");
    }
    @Override
    public AspectList getVisCost(ItemStack focus){
        return new AspectList().add(Aspect.ENTROPY,1000).add(Aspect.ORDER,500);
    }
    @Override
    public int getActivationCooldown(ItemStack stack) {
        return 500;
    }
    @Override
    public WandFocusAnimation getAnimation(ItemStack stack){
        return WandFocusAnimation.CHARGE;
    }
    @Override
    public int getFocusColor(ItemStack focus){
        return 0x8A2BE2;
    }
    @Override
    public ItemStack onFocusRightClick(ItemStack itemstack, World world, EntityPlayer player, MovingObjectPosition movingObjectPosition){
        ItemWandCasting wandCasting = (ItemWandCasting) itemstack.getItem();
        EntityAlumentum entityAlumentum = new EntityAlumentum(world,(EntityLivingBase)player);
        if((!world.isRemote)&&wandCasting.consumeAllVis(itemstack, player,this.getVisCost(itemstack),true, false)){
            world.spawnEntityInWorld(entityAlumentum);
        }
        return itemstack;
    }
}
