package com.forx.quickbar.helpers;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;

public class EntityHelpers {
    public static PlayerEntity getRenderViewEntity() {
        return !(Minecraft.getInstance().getRenderViewEntity() instanceof PlayerEntity) ?
            null : (PlayerEntity)Minecraft.getInstance().getRenderViewEntity();
    }
}
