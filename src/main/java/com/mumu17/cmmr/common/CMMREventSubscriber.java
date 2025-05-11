package com.mumu17.cmmr.common;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
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
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Files;
import java.nio.file.Path;

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

        // コンフィグファイルのパスを取得
        Path configPath = FMLPaths.CONFIGDIR.get().resolve(CMMRMain.MODID+"-config.toml");

        // コンフィグファイルが存在しない場合に自動生成
        if (!Files.exists(configPath)) {
            CommentedFileConfig configData = CommentedFileConfig.builder(configPath).sync().autosave().writingMode(WritingMode.REPLACE).build();
            configData.load();
            CMMRModConfig.CONFIG.setConfig(configData);
            configData.save();
        }
    }

}

