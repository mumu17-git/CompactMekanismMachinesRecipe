package com.mumu17.cmmr.common;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CMMRMain.MODID)
public class CMMRMain {

    public static final String MODID = "cmmr";
    private static final Logger LOGGER = LogUtils.getLogger();

    public CMMRMain() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(CMMREventSubscriber::onCommonSetup);
    }
}
