package com.forx.quickbar.client;

import com.forx.quickbar.FQuickbar;
import com.forx.quickbar.config.KeyBindings;
import com.forx.quickbar.helpers.QuickbarHelpers;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;

import java.util.ArrayList;

public class InputHandler {
    public static void init()
    {
        MinecraftForge.EVENT_BUS.register(new InputHandler());
    }

    @SubscribeEvent
    public void clientKeyInput(InputEvent.KeyInputEvent event) {
        handleKeyEvent(event.getKey(), event.getScanCode(), event.getModifiers(), event.getAction());
    }

    static void handleKeyEvent(int keyCode, int scanCode, int modifiers, int action) {
        InputMappings.Input input = InputMappings.getInputByCode(keyCode, scanCode);

        if (action == 1 && KeyBindings.changeQuickbarUp.isActiveAndMatches(input)) {
            QuickbarHelpers.switchQuickbar(KeyBindings.changeQuickbarUp, QuickbarHelpers.MoveSide.UP);
        } else if (action == 1 && KeyBindings.changeQuickbarDown.isActiveAndMatches(input)) {
            QuickbarHelpers.switchQuickbar(KeyBindings.changeQuickbarDown, QuickbarHelpers.MoveSide.DOWN);
        }
    }
}
