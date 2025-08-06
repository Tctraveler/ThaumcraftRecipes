package com.Tc_traveler.thaumcraftrecipes.mixins.late.Thaumcraft;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.Tc_traveler.thaumcraftrecipes.crafting.NodeRecipe;
import com.Tc_traveler.thaumcraftrecipes.helper.ThaumcraftCraftingManager;
import com.Tc_traveler.thaumcraftrecipes.interfaces.ITileNode_Mixin;

import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.tiles.TileNode;

@Mixin(value = TileNode.class, remap = false)
public abstract class TileNode_Mixin extends TileThaumcraft implements ITileNode_Mixin {

    @Shadow
    byte nodeLock;

    @Shadow
    public abstract AspectList getAspects();

    @Shadow
    public abstract boolean takeFromContainer(Aspect aspect, int amount);

    @Unique
    private boolean thaumcraftRecipes$isCrafting = false;

    @Unique
    private AspectList thaumcraftRecipes$recipeEssentia = new AspectList();

    @Unique
    private ItemStack thaumcraftRecipes$recipeInput = null;

    @Unique
    private ItemStack thaumcraftRecipes$recipeOutput = null;

    @Inject(method = "writeToNBT", at = @At("TAIL"), remap = false)
    public void writeToNBT(NBTTagCompound nbttagcompound, CallbackInfo ci) {
        if (this.thaumcraftRecipes$recipeOutput != null) {
            nbttagcompound.setTag("recipeOutput", this.thaumcraftRecipes$recipeOutput.writeToNBT(new NBTTagCompound()));
        }
        if (this.thaumcraftRecipes$recipeInput != null) {
            nbttagcompound.setTag("recipeInput", this.thaumcraftRecipes$recipeInput.writeToNBT(new NBTTagCompound()));
        }
    }

    @Inject(method = "writeCustomNBT", at = @At("TAIL"), remap = false)
    public void writeCustomNBT(NBTTagCompound nbttagcompound, CallbackInfo ci) {
        nbttagcompound.setBoolean("isCrafting", this.thaumcraftRecipes$isCrafting);
        this.thaumcraftRecipes$recipeEssentia.writeToNBT(nbttagcompound, "recipeEssentia");
    }

    @Inject(method = "readFromNBT", at = @At("TAIL"), remap = false)
    public void readFromNBT(NBTTagCompound nbttagcompound, CallbackInfo ci) {
        this.thaumcraftRecipes$recipeOutput = ItemStack
            .loadItemStackFromNBT(nbttagcompound.getCompoundTag("recipeOutput"));
        this.thaumcraftRecipes$recipeInput = ItemStack
            .loadItemStackFromNBT(nbttagcompound.getCompoundTag("recipeInput"));
    }

    @Inject(method = "readCustomNBT", at = @At("TAIL"), remap = false)
    public void readCustomNBT(NBTTagCompound nbttagcompound, CallbackInfo ci) {
        this.thaumcraftRecipes$isCrafting = nbttagcompound.getBoolean("isCrafting");
        this.thaumcraftRecipes$recipeEssentia.readFromNBT(nbttagcompound, "recipeEssentia");
    }

    @Inject(method = "updateEntity", at = @At("TAIL"), require = 1, remap = false)
    public void updateEntity(CallbackInfo ci) {
        if (!worldObj.isRemote && this.thaumcraftRecipes$isCrafting) {
            this.thaumcraftRecipes$crafting();
            this.markDirty();
        }
    }

    @Unique
    @Override
    public void thaumcraftRecipes$startCrafting(EntityPlayer player) {
        if (!thaumcraftRecipes$isCrafting && nodeLock == 0 && !worldObj.isRemote) {
            List<EntityItem> items = this.getWorldObj()
                .getEntitiesWithinAABB(
                    EntityItem.class,
                    AxisAlignedBB
                        .getBoundingBox(xCoord - 2, yCoord - 2, zCoord - 2, xCoord + 3, yCoord + 3, zCoord + 3));
            for (EntityItem item : items) {
                if (!item.isDead) {
                    ItemStack itemStack = item.getEntityItem();
                    NodeRecipe recipe = ThaumcraftCraftingManager
                        .findMatchingNodeRecipe(getAspects(), itemStack, player);
                    if (recipe != null) {
                        if (itemStack.stackSize > 0) {
                            itemStack.stackSize--;
                            this.thaumcraftRecipes$recipeEssentia = recipe.aspects.copy();
                            this.thaumcraftRecipes$recipeInput = itemStack.copy();
                            this.thaumcraftRecipes$recipeInput.stackSize = 1;
                            this.thaumcraftRecipes$recipeOutput = recipe.getRecipeOutput()
                                .copy();
                            this.thaumcraftRecipes$isCrafting = true;
                            this.worldObj.playSoundEffect(
                                this.xCoord,
                                this.yCoord,
                                this.zCoord,
                                "thaumcraft:craftstart",
                                0.5F,
                                1.0F);
                            this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                            this.markDirty();
                        }
                    }
                }
            }
        }
    }

    @Unique
    private void thaumcraftRecipes$crafting() {
        if (this.thaumcraftRecipes$recipeEssentia.visSize() > 0) {
            for (Aspect aspect : this.thaumcraftRecipes$recipeEssentia.getAspects()) {
                if (this.thaumcraftRecipes$recipeEssentia.getAmount(aspect) > 0) {
                    this.takeFromContainer(aspect, 1);
                    this.thaumcraftRecipes$recipeEssentia.reduce(aspect, 1);
                    this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
                    this.markDirty();
                    return;
                }
            }
        } else {
            EntityItem out = new EntityItem(
                this.worldObj,
                this.xCoord,
                this.yCoord,
                this.zCoord,
                thaumcraftRecipes$recipeOutput.copy());
            this.worldObj.spawnEntityInWorld(out);
            this.thaumcraftRecipes$recipeOutput = null;
            this.thaumcraftRecipes$recipeInput = null;
            this.thaumcraftRecipes$isCrafting = false;
            this.thaumcraftRecipes$recipeEssentia = new AspectList();
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            this.markDirty();
        }
    }

}
