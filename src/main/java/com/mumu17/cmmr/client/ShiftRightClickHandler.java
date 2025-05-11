package com.mumu17.cmmr.client;

import com.mumu17.cmmr.common.CMMRMain;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CMMRMain.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ShiftRightClickHandler {

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        if (player.isShiftKeyDown() && event.getHand() == InteractionHand.MAIN_HAND) {
            // シフトキーを押しながら右クリックしたときの処理

        }
    }
}
