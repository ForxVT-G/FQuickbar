package com.forx.quickbar.config;

import java.util.List;
import com.forx.quickbar.ModInfo;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import com.google.common.collect.ImmutableList;
import org.lwjgl.glfw.GLFW;

public final class KeyBindings {
    private static final String categoryName = ModInfo.MODID;
    private static final List<KeyBinding> allBindings;
    public static final KeyBinding changeQuickbar;

    static InputMappings.Input getKey(int key) {
        return InputMappings.Type.KEYSYM.getOrMakeInput(key);
    }

    static {
        allBindings = ImmutableList.of(
            changeQuickbar = new KeyBinding("key.fquickbar.changeQuickbar",
                KeyConflictContext.IN_GAME, getKey(GLFW.GLFW_KEY_LEFT_ALT), categoryName)
        );
    }

    public static void init() {
        for (KeyBinding binding : allBindings) {
            ClientRegistry.registerKeyBinding(binding);
        }
    }
}
