package com.Tc_traveler.thaumcraftrecipes.mixins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;

import cpw.mods.fml.relauncher.FMLLaunchHandler;

public enum Mixins {

    Thaumcraft_GuiResearchRecipe(Side.CLIENT, Phase.LATE, "Thaumcraft.GuiResearchRecipe_Mixin", TargetMod.Thaumcraft),;

    private final Side side;
    private final Phase phase;
    private final String mixin;

    private final TargetMod[] targetMod;

    Mixins(Side side, Phase phase, String mixin, TargetMod... targetMod) {
        this.side = side;
        this.phase = phase;
        this.mixin = mixin;
        this.targetMod = targetMod;
    }

    public static @NotNull List<String> getLateMixins(Set<String> loadedMods) {
        return filterMixins(Phase.LATE, loadedMods);
    }

    public static @NotNull List<String> getEarlyMixins(Set<String> loadedMods) {
        return filterMixins(Phase.EARLY, loadedMods);
    }

    private static @NotNull List<String> filterMixins(Phase phase, Set<String> loadedMods) {
        List<String> mixins = new ArrayList<>();
        for (Mixins value : Mixins.values()) {
            if (shouldApply(value.side) && value.phase == phase
                && loadedMods.containsAll(
                    Arrays.stream(value.targetMod)
                        .map(TargetMod::getModId)
                        .collect(Collectors.toSet()))) {
                mixins.add(value.mixin);
            }
        }
        return mixins;
    }

    private static boolean shouldApply(Side side) {
        return side == Side.BOTH || (side == Side.CLIENT && FMLLaunchHandler.side()
            .isClient())
            || (side == Side.SERVER && FMLLaunchHandler.side()
                .isServer());
    }

    private enum Phase {
        EARLY,
        LATE
    }

    private enum Side {
        BOTH,
        SERVER,
        CLIENT
    }
}
