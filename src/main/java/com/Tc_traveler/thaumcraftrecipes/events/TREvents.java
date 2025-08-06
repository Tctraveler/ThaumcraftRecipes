package com.Tc_traveler.thaumcraftrecipes.events;

import java.util.function.Supplier;

import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.FMLCommonHandler;

public class TREvents {

    public static void init() {
        registerEvent(NodeRightClickEvent::new, EventType.ForgeEvent);
    }

    private static void registerEvent(Supplier<?> event, EventType eventType) {
        registerEvent(event, true, eventType);
    }

    private static void registerEvent(Supplier<?> event, boolean shouldRegister, EventType eventType) {
        if (!shouldRegister) return;
        if (eventType == EventType.ForgeEvent) {
            MinecraftForge.EVENT_BUS.register(event.get());
        }
        if (eventType == EventType.FmlEvent) {
            FMLCommonHandler.instance()
                .bus()
                .register(event.get());
        }
    }

    public enum EventType {
        ForgeEvent,
        FmlEvent;
    }

}
