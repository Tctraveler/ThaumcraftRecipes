package Tc_traveler.thaumcraftrecipes.client;

import Tc_traveler.thaumcraftrecipes.common.CommonProxy;
import Tc_traveler.thaumcraftrecipes.helper.ClientHelper;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.entity.player.EntityPlayer;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }
    @Override
    public EntityPlayer getClientPlayer() {
        return (EntityPlayer) ClientHelper.clientPlayer();
    }
}
