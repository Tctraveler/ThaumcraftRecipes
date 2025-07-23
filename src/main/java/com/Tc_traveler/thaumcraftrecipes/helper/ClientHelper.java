package com.Tc_traveler.thaumcraftrecipes.helper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;

public class ClientHelper {

    public static Minecraft minecraft() {
        return Minecraft.getMinecraft();
    }

    public static EntityClientPlayerMP clientPlayer() {
        return minecraft().thePlayer;
    }
}
