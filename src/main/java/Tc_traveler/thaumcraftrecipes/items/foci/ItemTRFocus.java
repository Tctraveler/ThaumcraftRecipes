package Tc_traveler.thaumcraftrecipes.items.foci;

import Tc_traveler.thaumcraftrecipes.creativetab.CreativeTabsLoader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import thaumcraft.api.wands.ItemFocusBasic;

public class ItemTRFocus extends ItemFocusBasic {
    private String name;
    public ItemTRFocus(String name) {
        super();
        setMaxDamage(0);
        setNoRepair();
        setMaxStackSize(1);
        this.name=name;
        this.setCreativeTab(CreativeTabsLoader.TRTabs);
    }
    protected String getName(){
        return this.name;
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.icon=iconRegister.registerIcon("thaumcraftrecipes"+":focus_"+name);
    }
}
