package com.mumu17.cmmr.common;

import java.util.List;
import java.util.Map;

import static com.mumu17.cmmr.common.CMMRModConfig.COMPACT_FISSION_REACTOR;

public class CMMRItemList {
    public static final String FISSION_REACTOR_CASING = "mekanismgenerators:fission_reactor_casing";
    public static final String CONTROL_ROD_ASSEMBLY = "mekanismgenerators:control_rod_assembly";
    public static final String FISSION_FUEL_ASSEMBLY = "mekanismgenerators:fission_fuel_assembly";
    public static final String REACTOR_GLASS = "mekanismgenerators:reactor_glass";

    public static final Map<String,Integer> COMPACT_FISSION_REACTOR_LIST = Map.of(
            FISSION_REACTOR_CASING, COMPACT_FISSION_REACTOR.get(0).get(),
            CONTROL_ROD_ASSEMBLY, COMPACT_FISSION_REACTOR.get(1).get(),
            FISSION_FUEL_ASSEMBLY, COMPACT_FISSION_REACTOR.get(2).get(),
            REACTOR_GLASS, COMPACT_FISSION_REACTOR.get(3).get()
    );


}
