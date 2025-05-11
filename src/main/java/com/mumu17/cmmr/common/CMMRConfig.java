package com.mumu17.cmmr.common;


public class ModConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec CONFIG;

    public static final ForgeConfigSpec.BooleanValue EXAMPLE_SETTING;

    static {
        BUILDER.comment("General settings").push("general");
        EXAMPLE_SETTING = BUILDER.comment("Example setting")
                .define("exampleSetting", true);
        BUILDER.pop();

        CONFIG = BUILDER.build();
    }
}
