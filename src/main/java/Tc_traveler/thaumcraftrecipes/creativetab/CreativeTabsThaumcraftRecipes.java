package Tc_traveler.thaumcraftrecipes.creativetab;

import Tc_traveler.thaumcraftrecipes.ThaumcraftRecipes;
import Tc_traveler.thaumcraftrecipes.items.TRItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabsThaumcraftRecipes extends CreativeTabs {
    public CreativeTabsThaumcraftRecipes(){
        super(ThaumcraftRecipes.MODID);
        setBackgroundImageName("TRCreativeTabs.png");
    }
    @Override
    public Item getTabIconItem() {
        return TRItems.icon;
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }

    @Override
    public int getSearchbarWidth() {
        return 89;
    }
}
