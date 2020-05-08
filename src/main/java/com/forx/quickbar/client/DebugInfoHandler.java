package com.forx.quickbar.client;

import com.forx.quickbar.helpers.QuickbarHelpers;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.text.DecimalFormat;

@OnlyIn(Dist.CLIENT)
public class DebugInfoHandler
{
    private static final DecimalFormat saturationDF = new DecimalFormat("#.##");
    private static final DecimalFormat exhaustionValDF = new DecimalFormat("0.00");
    private static final DecimalFormat exhaustionMaxDF = new DecimalFormat("#.##");

    public static void init()
    {
        MinecraftForge.EVENT_BUS.register(new DebugInfoHandler());
    }

    @SubscribeEvent
    public void onTextRender(RenderGameOverlayEvent.Text textEvent)
    {
        if (textEvent.getType() != RenderGameOverlayEvent.ElementType.TEXT)
            return;

        if (Minecraft.getInstance().gameSettings.showDebugInfo)
        {
            String currentItem = Minecraft.getInstance().objectMouseOver.toString();

            textEvent.getLeft().add("active quickbar: " + QuickbarHelpers.activeQuickbar);
        }
    }
}
