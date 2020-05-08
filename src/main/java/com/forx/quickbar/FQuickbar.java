package com.forx.quickbar;

import com.forx.quickbar.client.DebugInfoHandler;
import com.forx.quickbar.client.HudOverlayHandler;
import com.forx.quickbar.config.KeyBindings;
import com.forx.quickbar.client.InputHandler;
import com.forx.quickbar.helpers.QuickbarHelpers;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ModInfo.MODID_LOWER)
public class FQuickbar
{
    private static final Logger LOGGER = LogManager.getLogger();

    public FQuickbar() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::preInitClient);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void preInitClient(final FMLClientSetupEvent event)
    {
        KeyBindings.init();
        InputHandler.init();
        HudOverlayHandler.init();
        DebugInfoHandler.init();
    }
}
