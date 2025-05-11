package com.mumu17.cmmr.common;


import net.minecraftforge.common.ForgeConfigSpec;

public class CMMRModConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec CONFIG;

    public static final ForgeConfigSpec.ConfigValue<Integer> COMPACT_FISSION_REACTOR;

    static {
        BUILDER.push("CompactFissionReactor");
        COMPACT_FISSION_REACTOR = BUILDER
                .define("block.mekanismgenerators.fission_reactor_casing", 64*3).next()
                .define("block.mekanismgenerators.control_rod_assembly", 64*2).next()
                .define("block.mekanismgenerators.fission_fuel_assembly", 64*13).next()
                .define("block.mekanismgenerators.reactor_glass", 64*9);
        BUILDER.pop();

        CONFIG = BUILDER.build();
    }
}
