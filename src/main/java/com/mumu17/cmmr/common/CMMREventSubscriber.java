package com.mumu17.cmmr.common;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = CMMRMain.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CMMREventSubscriber {

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        if (player.isShiftKeyDown() && event.getHand() == InteractionHand.MAIN_HAND) {
            // シフトキーを押しながら右クリックしたときの処理

        }
    }


    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CMMRModConfig.CONFIG);
    }

}

