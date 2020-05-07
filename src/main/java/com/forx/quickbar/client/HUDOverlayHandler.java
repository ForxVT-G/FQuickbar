package com.forx.quickbar.client;

import com.forx.quickbar.helpers.EntityHelpers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;

@OnlyIn(Dist.CLIENT)
public class HudOverlayHandler
{
    /*protected Minecraft mc = Minecraft.getInstance();
    protected static final ResourceLocation WIDGETS_TEX_PATH = new ResourceLocation(
        "textures/gui/widgets.png");
    protected int scaledWidth;
    protected int scaledHeight;*/
    public static ArrayList<ArrayList<ItemStack>> quickbars = new ArrayList<ArrayList<ItemStack>>();
    public static int activeQuickbar = 0;

    public static void init()
    {
        //ForgeIngameGui.renderHotbar = false;
        MinecraftForge.EVENT_BUS.register(new HudOverlayHandler());

        for (int i = 0; i < 4; i++) {
            quickbars.add(new ArrayList<ItemStack>());

            for (int j = 0; j < 9; j++) {
                quickbars.get(i).add(ItemStack.EMPTY);
            }
        }
    }

    public static void switchQuickbar() {
        PlayerEntity player = EntityHelpers.getRenderViewEntity();

        if (player != null) {
            for (int i = 0; i < 9; i++) {
                quickbars.get(activeQuickbar).set(i, player.inventory.mainInventory.get(i));
            }

            activeQuickbar = (activeQuickbar == 3 ? 0 : activeQuickbar + 1);

            for (int i = 0; i < 9; i++) {
                player.inventory.setInventorySlotContents(i, quickbars.get(activeQuickbar).get(i));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onRender(RenderGameOverlayEvent.Post event)
    {
        /*if (event.isCanceled())
            return;

        this.scaledWidth = this.mc.func_228018_at_().getScaledWidth();
        this.scaledHeight = this.mc.func_228018_at_().getScaledHeight();

        renderHotbar(mc.getRenderPartialTicks());*/
    }

    /*private void renderHotbar(float partialTicks) {
        PlayerEntity playerentity = EntityHelpers.getRenderViewEntity();

        if (playerentity != null) {
            RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.mc.getTextureManager().bindTexture(WIDGETS_TEX_PATH);
            ItemStack itemstack = playerentity.getHeldItemOffhand();
            HandSide handside = playerentity.getPrimaryHand().opposite();
            int i = this.scaledWidth / 2;
            int j = mc.ingameGUI.getBlitOffset();
            int k = 182;
            int l = 91;
            mc.ingameGUI.setBlitOffset(-90);
            mc.ingameGUI.blit(i - 91, this.scaledHeight - 22, 0, 0, 182, 22);
            mc.ingameGUI.blit(i - 91 - 1 + playerentity.inventory.currentItem * 20, this.scaledHeight - 22 - 1, 0, 22, 24, 22);
            if (!itemstack.isEmpty()) {
                if (handside == HandSide.LEFT) {
                    mc.ingameGUI.blit(i - 91 - 29, this.scaledHeight - 23, 24, 22, 29, 24);
                } else {
                    mc.ingameGUI.blit(i + 91, this.scaledHeight - 23, 53, 22, 29, 24);
                }
            }

            mc.ingameGUI.setBlitOffset(j);
            RenderSystem.enableRescaleNormal();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();

            for(int i1 = 0; i1 < 9; ++i1) {
                int j1 = i - 90 + i1 * 20 + 2;
                int k1 = this.scaledHeight - 16 - 3;
                this.renderHotbarItem(j1, k1, partialTicks, playerentity, playerentity.inventory.mainInventory.get(i1));
            }

            if (!itemstack.isEmpty()) {
                int i2 = this.scaledHeight - 16 - 3;
                if (handside == HandSide.LEFT) {
                    this.renderHotbarItem(i - 91 - 26, i2, partialTicks, playerentity, itemstack);
                } else {
                    this.renderHotbarItem(i + 91 + 10, i2, partialTicks, playerentity, itemstack);
                }
            }

            if (this.mc.gameSettings.attackIndicator == AttackIndicatorStatus.HOTBAR) {
                float f = this.mc.player.getCooledAttackStrength(0.0F);
                if (f < 1.0F) {
                    int j2 = this.scaledHeight - 20;
                    int k2 = i + 91 + 6;
                    if (handside == HandSide.RIGHT) {
                        k2 = i - 91 - 22;
                    }

                    this.mc.getTextureManager().bindTexture(AbstractGui.GUI_ICONS_LOCATION);
                    int l1 = (int)(f * 19.0F);
                    RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                    mc.ingameGUI.blit(k2, j2, 0, 94, 18, 18);
                    mc.ingameGUI.blit(k2, j2 + 18 - l1, 18, 112 - l1, 18, l1);
                }
            }

            RenderSystem.disableRescaleNormal();
            RenderSystem.disableBlend();
        }
    }

    private void renderHotbarItem(int x, int y, float partialTicks, PlayerEntity player, ItemStack stack) {
        if (!stack.isEmpty()) {
            float f = (float)stack.getAnimationsToGo() - partialTicks;
            if (f > 0.0F) {
                RenderSystem.pushMatrix();
                float f1 = 1.0F + f / 5.0F;
                RenderSystem.translatef((float)(x + 8), (float)(y + 12), 0.0F);
                RenderSystem.scalef(1.0F / f1, (f1 + 1.0F) / 2.0F, 1.0F);
                RenderSystem.translatef((float)(-(x + 8)), (float)(-(y + 12)), 0.0F);
            }

            mc.getItemRenderer().renderItemAndEffectIntoGUI(player, stack, x, y);
            if (f > 0.0F) {
                RenderSystem.popMatrix();
            }

            mc.getItemRenderer().renderItemOverlays(this.mc.fontRenderer, stack, x, y);
        }
    }*/
}