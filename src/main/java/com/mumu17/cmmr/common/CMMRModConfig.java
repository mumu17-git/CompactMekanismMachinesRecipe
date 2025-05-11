package com.mumu17.cmmr.common;


import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CMMRModConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec CONFIG = null;

    public static List<ForgeConfigSpec.ConfigValue<Integer>> COMPACT_FISSION_REACTOR = new ArrayList<>();

    public CMMRModConfig() {
        if(COMPACT_FISSION_REACTOR.isEmpty()) {
            BUILDER.push("CompactFissionReactor");
            COMPACT_FISSION_REACTOR.add(BUILDER.define("fission_reactor_casing", 64 * 3));
            COMPACT_FISSION_REACTOR.add(BUILDER.define("control_rod_assembly", 64 * 2));
            COMPACT_FISSION_REACTOR.add(BUILDER.define("fission_fuel_assembly", 64 * 13));
            COMPACT_FISSION_REACTOR.add(BUILDER.define("reactor_glass", 64 * 9));

            BUILDER.pop();

            CONFIG = BUILDER.build();
        }
    }
}
