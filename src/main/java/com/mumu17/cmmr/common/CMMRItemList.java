package com.mumu17.cmmr.common;

import java.util.List;
import java.util.Map;

public class CMMRItemList {
    public static final String Mekanism = "mekanism";
    public static final String MekGen = "mekanismgenerators";
    public static final String CMM = "compactmekanismmachines";

    public static final String FISSION_REACTOR_CASING = MekGen+":fission_reactor_casing";
    public static final String CONTROL_ROD_ASSEMBLY = MekGen+":control_rod_assembly";
    public static final String FISSION_FUEL_ASSEMBLY = MekGen+":fission_fuel_assembly";
    public static final String REACTOR_GLASS = MekGen+":reactor_glass";
    public static final String COMPACT_FISSION_REACTOR = CMM+":compact_fission_reactor";
    public static final Map<String,Integer> COMPACT_FISSION_REACTOR_LIST = Map.of(
            FISSION_REACTOR_CASING, 64 * 3,
            CONTROL_ROD_ASSEMBLY, 64 * 2,
            FISSION_FUEL_ASSEMBLY, 64 * 13,
            REACTOR_GLASS, 64 * 9
    );

    public static final String TURBINE_CASING = MekGen+":turbine_casing";
    public static final String TURBINE_VENT = MekGen+":turbine_vent";
    public static final String SATURATING_CONDENSER = MekGen+":saturating_condenser";
    public static final String ELECTROMAGNETIC_COIL = MekGen+":electromagnetic_coil";
    public static final String PRESSURE_DISPERSER = Mekanism+":pressure_disperser";
    public static final String STRUCTURAL_GLASS = Mekanism+":structural_glass";
    public static final String COMPACT_INDUSTRIAL_TURBINE = CMM+":compact_industrial_turbine";
    public static final Map<String,Integer> COMPACT_INDUSTRIAL_TURBINE_LIST = Map.of(
            TURBINE_CASING, 64 * 3,
            TURBINE_VENT, 64 * 2,
            SATURATING_CONDENSER, 64 * 2,
            ELECTROMAGNETIC_COIL, 8,
            PRESSURE_DISPERSER, 64 * 2,
            STRUCTURAL_GLASS, 64 * 17
    );

    public static final String THERMAL_EVAPORATION_BLOCK = Mekanism+":thermal_evaporation_block";
    public static final String THERMAL_EVAPORATION_CONTROLLER = Mekanism+":thermal_evaporation_controller";
    public static final String THERMAL_EVAPORATION_VALVE = Mekanism+":thermal_evaporation_valve";
    public static final String COMPACT_THERMAL_EVAPORATION = CMM+":compact_thermal_evaporation";
    public static final Map<String,Integer> COMPACT_THERMAL_EVAPORATION_LIST = Map.of(
            THERMAL_EVAPORATION_BLOCK, 64 + 24,
            THERMAL_EVAPORATION_CONTROLLER, 1,
            THERMAL_EVAPORATION_VALVE, 4,
            STRUCTURAL_GLASS, 64 * 2 + 16
    );

}
