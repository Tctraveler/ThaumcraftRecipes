package com.Tc_traveler.thaumcraftrecipes.mixins.late.Thaumcraft;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import com.Tc_traveler.thaumcraftrecipes.crafting.NodeRecipe;
import com.Tc_traveler.thaumcraftrecipes.research.TRResearchPage;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.client.gui.GuiResearchRecipe;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.lib.utils.InventoryUtils;

@Mixin(value = GuiResearchRecipe.class, remap = false)
public abstract class GuiResearchRecipe_Mixin extends GuiScreen {

    @Shadow
    private int cycle;

    @Shadow
    private ResearchItem research;

    @Shadow
    private long lastCycle;

    @Shadow
    private int page;

    @Shadow
    String tex2;

    @Shadow
    protected static RenderItem itemRenderer;

    @Shadow
    ArrayList<List> reference;

    @Shadow
    protected abstract void drawArcaneCraftingPage(int side, int x, int y, int mx, int my, ResearchPage pageParm);

    @Shadow
    protected abstract void drawAspectPage(int side, int x, int y, int mx, int my, AspectList aspects);

    @Shadow
    protected abstract void drawCompoundCraftingPage(int side, int x, int y, int mx, int my, ResearchPage page);

    @Shadow
    protected abstract void drawCraftingPage(int side, int x, int y, int mx, int my, ResearchPage pageParm);

    @Shadow
    protected abstract void drawCruciblePage(int side, int x, int y, int mx, int my, ResearchPage pageParm);

    @Shadow
    protected abstract void drawInfusionEnchantingPage(int side, int x, int y, int mx, int my, ResearchPage pageParm);

    @Shadow
    protected abstract void drawInfusionPage(int side, int x, int y, int mx, int my, ResearchPage pageParm);

    @Shadow
    protected abstract void drawSmeltingPage(int side, int x, int y, int mx, int my, ResearchPage pageParm);

    @Shadow
    protected abstract void drawTextPage(int side, int x, int y, String text);

    @Shadow
    public abstract void drawCustomTooltip(GuiScreen gui, RenderItem itemRenderer, FontRenderer fr, List var4, int par2,
        int par3, int subTipColor);

    @Shadow
    public abstract Object[] findRecipeReference(ItemStack item);

    @Unique
    String thaumcraftRecipes$tex3 = "textures/gui/gui_researchbook_overlay2.png";

    /**
     * @author Tc_traveler
     * @reason New idea.
     */
    @Overwrite
    private void drawPage(ResearchPage pageParm, int side, int x, int y, int mx, int my) {
        GL11.glPushAttrib(1048575);
        if (this.lastCycle < System.currentTimeMillis()) {
            ++this.cycle;
            this.lastCycle = System.currentTimeMillis() + 1000L;
        }

        if (this.page == 0 && side == 0) {
            this.drawTexturedModalRect(x + 4, y - 13, 24, 184, 96, 4);
            this.drawTexturedModalRect(x + 4, y + 4, 24, 184, 96, 4);
            int offset = this.fontRendererObj.getStringWidth(this.research.getName());
            if (offset <= 130) {
                this.fontRendererObj.drawString(this.research.getName(), x + 52 - offset / 2, y - 6, 3158064);
            } else {
                float vv = 130.0F / (float) offset;
                GL11.glPushMatrix();
                GL11.glTranslatef((float) (x + 52) - (float) (offset / 2) * vv, (float) y - 6.0F * vv, 0.0F);
                GL11.glScalef(vv, vv, vv);
                this.fontRendererObj.drawString(this.research.getName(), 0, 0, 3158064);
                GL11.glPopMatrix();
            }

            y += 25;
        }

        GL11.glAlphaFunc(516, 0.003921569F);
        if (pageParm instanceof TRResearchPage trResearchPage) {
            if (trResearchPage.trPageType == TRResearchPage.TRPageType.NODE_CRAFTING) {
                this.thaumcraftRecipes$drawNodeCraftingPage(side, x - 4, y - 8, mx, my, trResearchPage);
            }
        } else {
            if (pageParm.type != ResearchPage.PageType.TEXT && pageParm.type != ResearchPage.PageType.TEXT_CONCEALED) {
                if (pageParm.type == ResearchPage.PageType.ASPECTS) {
                    this.drawAspectPage(side, x - 8, y - 8, mx, my, pageParm.aspects);
                } else if (pageParm.type == ResearchPage.PageType.CRUCIBLE_CRAFTING) {
                    this.drawCruciblePage(side, x - 4, y - 8, mx, my, pageParm);
                } else if (pageParm.type == ResearchPage.PageType.NORMAL_CRAFTING) {
                    this.drawCraftingPage(side, x - 4, y - 8, mx, my, pageParm);
                } else if (pageParm.type == ResearchPage.PageType.ARCANE_CRAFTING) {
                    this.drawArcaneCraftingPage(side, x - 4, y - 8, mx, my, pageParm);
                } else if (pageParm.type == ResearchPage.PageType.COMPOUND_CRAFTING) {
                    this.drawCompoundCraftingPage(side, x - 4, y - 8, mx, my, pageParm);
                } else if (pageParm.type == ResearchPage.PageType.INFUSION_CRAFTING) {
                    this.drawInfusionPage(side, x - 4, y - 8, mx, my, pageParm);
                } else if (pageParm.type == ResearchPage.PageType.INFUSION_ENCHANTMENT) {
                    this.drawInfusionEnchantingPage(side, x - 4, y - 8, mx, my, pageParm);
                } else if (pageParm.type == ResearchPage.PageType.SMELTING) {
                    this.drawSmeltingPage(side, x - 4, y - 8, mx, my, pageParm);
                }
            } else {
                this.drawTextPage(side, x, y - 10, pageParm.getTranslatedText());
            }
        }

        GL11.glAlphaFunc(516, 0.1F);
        GL11.glPopAttrib();
    }

    @Unique
    private void thaumcraftRecipes$drawNodeCraftingPage(int side, int x, int y, int mx, int my, ResearchPage pageParm) {
        NodeRecipe rc = null;
        Object tr = null;
        if (pageParm.recipe instanceof Object[]) {
            try {
                tr = ((Object[]) ((Object[]) pageParm.recipe))[this.cycle];
            } catch (Exception var26) {
                this.cycle = 0;
                tr = ((Object[]) ((Object[]) pageParm.recipe))[this.cycle];
            }
        } else {
            tr = pageParm.recipe;
        }

        if (tr instanceof NodeRecipe) {
            rc = (NodeRecipe) tr;
        }

        if (rc != null) {
            GL11.glPushMatrix();
            int start = side * 152;
            String text = StatCollector.translateToLocal("recipe.type.node");
            int offset = this.fontRendererObj.getStringWidth(text);
            this.fontRendererObj.drawString(text, x + start + 56 - offset / 2, y, 5263440);
            UtilsFX.bindTexture(this.tex2);
            GL11.glPushMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable(3042);
            GL11.glTranslatef((float) (x + start), (float) (y + 28), 0.0F);
            GL11.glScalef(2.0F, 2.0F, 1.0F);
            this.drawTexturedModalRect(0, 0, 0, 3, 56, 17);
            GL11.glPopMatrix();
            UtilsFX.bindTexture(this.thaumcraftRecipes$tex3);
            GL11.glPushMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glEnable(3042);
            GL11.glTranslatef((float) (x + start), (float) (y + 28), 0.0F);
            GL11.glScalef(0.5F, 0.5F, 1.0F);
            GL11.glTranslatef(-16.0F, 70.0F, 0.0F);
            this.drawTexturedModalRect(0, 0, 0, 0, 256, 256);
            GL11.glPopMatrix();
            int mposx = mx;
            int mposy = my;
            int total = 0;
            int rows = (rc.aspects.size() - 1) / 3;
            int shift = (3 - rc.aspects.size() % 3) * 10;
            int sx = x + start + 28;
            int sy = y + 85 + 32 - 10 * rows;

            for (Aspect tag : rc.aspects.getAspectsSorted()) {
                int m = 0;
                if (total / 3 >= rows && (rows > 1 || rc.aspects.size() < 3)) {
                    m = 1;
                }

                int vx = sx + total % 3 * 20 + shift * m;
                int vy = sy + total / 3 * 20;
                UtilsFX.drawTag(vx, vy, tag, (float) rc.aspects.getAmount(tag), 0, (double) this.zLevel);
                ++total;
            }

            GL11.glPushMatrix();
            GL11.glTranslated(0.0D, 0.0D, 100.0D);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            RenderHelper.enableGUIStandardItemLighting();
            GL11.glEnable(2884);
            itemRenderer.renderItemAndEffectIntoGUI(
                this.mc.fontRenderer,
                this.mc.renderEngine,
                rc.getRecipeOutput(),
                x + 48 + start,
                y + 36);
            itemRenderer.renderItemOverlayIntoGUI(
                this.mc.fontRenderer,
                this.mc.renderEngine,
                rc.getRecipeOutput(),
                x + 48 + start,
                y + 36);
            RenderHelper.disableStandardItemLighting();
            GL11.glEnable(2896);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslated(0.0D, 0.0D, 100.0D);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            RenderHelper.enableGUIStandardItemLighting();
            GL11.glEnable(2884);
            itemRenderer.renderItemAndEffectIntoGUI(
                this.mc.fontRenderer,
                this.mc.renderEngine,
                InventoryUtils.cycleItemStack(rc.input),
                x + 16 + start,
                y + 60);
            itemRenderer.renderItemOverlayIntoGUI(
                this.mc.fontRenderer,
                this.mc.renderEngine,
                InventoryUtils.cycleItemStack(rc.input)
                    .copy()
                    .splitStack(1),
                x + 16 + start,
                y + 60);
            RenderHelper.disableStandardItemLighting();
            GL11.glEnable(2896);
            GL11.glPopMatrix();
            if (mx >= x + 48 + start && my >= y + 36 && mx < x + 48 + start + 16 && my < y + 36 + 16) {
                this.drawCustomTooltip(
                    this,
                    itemRenderer,
                    this.fontRendererObj,
                    rc.getRecipeOutput()
                        .getTooltip(this.mc.thePlayer, this.mc.gameSettings.advancedItemTooltips),
                    mx,
                    my,
                    11);
            }

            if (mx >= x + 16 + start && my >= y + 60 && mx < x + 16 + start + 16 && my < y + 60 + 16) {
                List addtext = InventoryUtils.cycleItemStack(rc.input)
                    .getTooltip(this.mc.thePlayer, this.mc.gameSettings.advancedItemTooltips);
                Object[] ref = this.findRecipeReference(InventoryUtils.cycleItemStack(rc.input));
                if (ref != null && !((String) ref[0]).equals(this.research.key)) {
                    addtext.add("§8§o" + StatCollector.translateToLocal("recipe.clickthrough"));
                    this.reference.add(
                        Arrays.asList(
                            new Serializable[] { Integer.valueOf(mx), Integer.valueOf(my), (String) ref[0],
                                (Integer) ref[1] }));
                }

                this.drawCustomTooltip(this, itemRenderer, this.fontRendererObj, addtext, mx, my, 11);
            }

            total = 0;

            for (Aspect tag : rc.aspects.getAspectsSorted()) {
                int m = 0;
                if (total / 3 >= rows && (rows > 1 || rc.aspects.size() < 3)) {
                    m = 1;
                }

                int vx = sx + total % 3 * 20 + shift * m;
                int vy = sy + total / 3 * 20;
                if (mposx >= vx && mposy >= vy && mposx < vx + 16 && mposy < vy + 16) {
                    this.drawCustomTooltip(
                        this,
                        itemRenderer,
                        this.fontRendererObj,
                        Arrays.asList(new String[] { tag.getName(), tag.getLocalizedDescription() }),
                        mx,
                        my,
                        11);
                }

                ++total;
            }

            GL11.glPopMatrix();
        }
    }
}
