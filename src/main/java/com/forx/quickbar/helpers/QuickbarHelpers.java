package com.forx.quickbar.helpers;

import com.forx.quickbar.config.KeyBindings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.multiplayer.PlayerController;
import net.minecraft.client.settings.CreativeSettings;
import net.minecraft.client.settings.HotbarSnapshot;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.ArrayList;

public class QuickbarHelpers {
    public static int activeQuickbar = 0;

    public static enum MoveSide {
        UP,
        DOWN
    }

    public static void switchQuickbar(KeyBinding keyBinding, MoveSide side) {
        Minecraft mc = Minecraft.getInstance();
        ClientPlayerEntity player = mc.player;
        PlayerController playerController = Minecraft.getInstance().playerController;
        CreativeSettings creativeSettings = mc.getCreativeSettings();
        HotbarSnapshot hotbarSnapshot = creativeSettings.getHotbarSnapshot(activeQuickbar);

        // Save current quickbar
        for (int i = 0; i < PlayerInventory.getHotbarSize(); i++) {
            hotbarSnapshot.set(i, player.inventory.getStackInSlot(i).copy());
        }
        creativeSettings.save();

        // Change current quickbar
        if (side == MoveSide.UP) {
            activeQuickbar = (activeQuickbar == 8 ? 0 : activeQuickbar + 1);
        } else {
            activeQuickbar = (activeQuickbar == 0 ? 8 : activeQuickbar - 1);
        }
        hotbarSnapshot = creativeSettings.getHotbarSnapshot(activeQuickbar);

        // Load current quickbar
        for (int i = 0; i < PlayerInventory.getHotbarSize(); i++) {
            ItemStack itemStack = hotbarSnapshot.get(i).copy();
            player.inventory.setInventorySlotContents(i, itemStack);
            playerController.sendSlotPacket(itemStack, 36 + i);
        }

        player.container.detectAndSendChanges();
    }
}
