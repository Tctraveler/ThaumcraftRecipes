package com.Tc_traveler.thaumcraftrecipes.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import com.Tc_traveler.thaumcraftrecipes.interfaces.ITileNode_Mixin;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.common.tiles.TileNode;

public class NodeRightClickEvent {

    @SubscribeEvent
    public void onRightClick(PlayerInteractEvent event) {
        World world = event.world;
        if (event.action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        EntityPlayer player = event.entityPlayer;
        ItemStack now = player.getHeldItem();
        if (now != null) {
            return;
        }
        TileEntity te = world.getTileEntity(event.x, event.y, event.z);
        if (te instanceof TileNode tn) {
            if (tn instanceof ITileNode_Mixin Mtn) {
                Mtn.thaumcraftRecipes$startCrafting(player);
            }
        }
    }

}
