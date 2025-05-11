package com.mumu17.cmmr.client;

import com.mumu17.cmmr.common.CMMRItemList;
import com.mumu17.cmmr.common.CMMRMain;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mumu17.cmmr.common.CMMRItemList.*;

@Mod.EventBusSubscriber(modid = CMMRMain.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ShiftRightClickHandler {

    public static Map<String,Integer> CRAFTING_LIST_COUNT = null;
    public static Map<String,Integer> CRAFTING_LIST = null;

    public static ItemStack getItemStackFromId(String itemId, int count) {
        // アイテムIDからアイテムを取得
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemId));
        if (item != null) {
            // アイテムスタックを作成
            return new ItemStack(item, count);
        } else {
            // アイテムが見つからない場合は空のアイテムスタックを返す
            return ItemStack.EMPTY;
        }
    }



    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if(event.getLevel().isClientSide()) {
            return;
        }
        Crafting(event,COMPACT_FISSION_REACTOR);
        Crafting(event,COMPACT_INDUSTRIAL_TURBINE);
        Crafting(event,COMPACT_THERMAL_EVAPORATION);
    }
    
    public static void Crafting(PlayerInteractEvent.RightClickBlock event, String cid) {
        if(cid.equals(COMPACT_FISSION_REACTOR)) {
            CRAFTING_LIST_COUNT = getZeroCountMap(COMPACT_FISSION_REACTOR_LIST);
            CRAFTING_LIST = Map.copyOf(COMPACT_FISSION_REACTOR_LIST);
        }else if(cid.equals(COMPACT_INDUSTRIAL_TURBINE)) {
            CRAFTING_LIST_COUNT = getZeroCountMap(COMPACT_INDUSTRIAL_TURBINE_LIST);
            CRAFTING_LIST = Map.copyOf(COMPACT_INDUSTRIAL_TURBINE_LIST);
        }else if(cid.equals(COMPACT_THERMAL_EVAPORATION)) {
            CRAFTING_LIST_COUNT = getZeroCountMap(COMPACT_THERMAL_EVAPORATION_LIST);
            CRAFTING_LIST = Map.copyOf(COMPACT_THERMAL_EVAPORATION_LIST);
        }else {
            return;
        }
        
        Player player = event.getEntity();
        if (player.isShiftKeyDown() && event.getHand() == InteractionHand.MAIN_HAND) {
            // シフトキーを押しながら右クリックしたときの処理
            BlockState state = event.getLevel().getBlockState(event.getPos());
            BlockEntity blockEntity = event.getLevel().getBlockEntity(event.getPos());

            if (blockEntity instanceof ChestBlockEntity) {
                ChestBlockEntity chest = (ChestBlockEntity) blockEntity;
                NonNullList<ItemStack> items = NonNullList.create();
                for (int i0 = 0; i0 < chest.getContainerSize(); i0++) {
                    items.add(chest.getItem(i0));
                }
                for (ItemStack item : items) {
                    if (item.isEmpty()) {
                        continue;
                    }

                    String itemId = MekGen+":"+item.getItem().toString();


                    if (CRAFTING_LIST.containsKey(itemId)) {
                        CRAFTING_LIST_COUNT.replace(itemId, CRAFTING_LIST_COUNT.get(itemId) + item.getCount());
                    }
                }

                List<Integer> CRAFTING_CHECK = null;
                if(cid.equals(COMPACT_FISSION_REACTOR)) {
                    CRAFTING_CHECK = List.of(
                            CRAFTING_LIST_COUNT.get(FISSION_REACTOR_CASING) - CRAFTING_LIST.get(FISSION_REACTOR_CASING),
                            CRAFTING_LIST_COUNT.get(CONTROL_ROD_ASSEMBLY) - CRAFTING_LIST.get(CONTROL_ROD_ASSEMBLY),
                            CRAFTING_LIST_COUNT.get(FISSION_FUEL_ASSEMBLY) - CRAFTING_LIST.get(FISSION_FUEL_ASSEMBLY),
                            CRAFTING_LIST_COUNT.get(REACTOR_GLASS) - CRAFTING_LIST.get(REACTOR_GLASS));
                }
                boolean check_count = true;

                for (int c : CRAFTING_CHECK) {
                    if (c < 0) {
                        check_count = false;
                        break;
                    }
                }
                if (check_count) {
                    for (int i1 = 0; i1 < chest.getContainerSize(); i1++) {
                        chest.setItem(i1, ItemStack.EMPTY);
                    }

                    ItemStack itemStack_result = getItemStackFromId(cid, 1);
                    chest.setItem(13, itemStack_result);

                    int slotId = 0;

                    for(int i2 = 0; i2 < CRAFTING_LIST_COUNT.size();i2++) {
                        int count = CRAFTING_CHECK.get(i2);
                        while (count > 0) {
                            if(!chest.getItem(i2).isEmpty()) continue;
                            int remove_count = (count >= 64 ? 64 : CRAFTING_LIST_COUNT.get(i2));
                            count -= remove_count;
                            ItemStack itemStack = getItemStackFromId(CRAFTING_LIST.keySet().toArray()[i2].toString(), remove_count);
                            chest.setItem(slotId, itemStack);
                        }
                    }

                    chest.setChanged();
                }
            }
        }
    }

    public static Map<String, Integer> getZeroCountMap(Map<String, Integer> craftingList) {
        Map<String,Integer> _craftingList = new HashMap<>(Map.copyOf(craftingList));
        for(int i = 0;i < _craftingList.size();i++) {
            String key = _craftingList.keySet().toArray()[i].toString();
            _craftingList.replace(key, 0);
        }
        return _craftingList;
    }
}

