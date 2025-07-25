package com.Tc_traveler.thaumcraftrecipes.mixins;

public enum TargetMod {

    Thaumcraft("Thaumcraft", "Thaumcraft");

    public final String modName;
    public final String modId;

    TargetMod(String modName, String modId) {
        this.modName = modName;
        this.modId = modId;
    }

    public String getModName() {
        return modName;
    }

    public String getModId() {
        return modId;
    }
}
