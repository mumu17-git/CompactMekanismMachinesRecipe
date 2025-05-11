package com.mumu17.cmmr.client;

import com.mumu17.cmmr.common.CMMRItemList;
import com.mumu17.cmmr.common.CMMRMain;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Map;

import static com.mumu17.cmmr.common.CMMRItemList.*;
import static com.mumu17.cmmr.common.CMMRModConfig.COMPACT_FISSION_REACTOR;

@Mod.EventBusSubscriber(modid = CMMRMain.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ShiftRightClickHandler {

    public static Map<String,Integer> COMPACT_FISSION_REACTOR_LIST_COUNT = Map.of(
            FISSION_REACTOR_CASING, 0,
            CONTROL_ROD_ASSEMBLY, 0,
            FISSION_FUEL_ASSEMBLY, 0,
            REACTOR_GLASS, 0
    );

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
    @OnlyIn(Dist.CLIENT)
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
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

                    String itemId = item.getItem().toString();

                    if (CMMRItemList.COMPACT_FISSION_REACTOR_LIST.containsKey(itemId)) {
                        COMPACT_FISSION_REACTOR_LIST_COUNT.put(itemId, COMPACT_FISSION_REACTOR_LIST_COUNT.get(itemId) + item.getCount());
                    }
                }

                List<Integer> COMPACT_FISSION_REACTOR_CHECK = List.of(
                        COMPACT_FISSION_REACTOR_LIST_COUNT.get(FISSION_REACTOR_CASING) - COMPACT_FISSION_REACTOR.get(0).get(),
                        COMPACT_FISSION_REACTOR_LIST_COUNT.get(CONTROL_ROD_ASSEMBLY) - COMPACT_FISSION_REACTOR.get(1).get(),
                        COMPACT_FISSION_REACTOR_LIST_COUNT.get(FISSION_FUEL_ASSEMBLY) - COMPACT_FISSION_REACTOR.get(2).get(),
                        COMPACT_FISSION_REACTOR_LIST_COUNT.get(REACTOR_GLASS) - COMPACT_FISSION_REACTOR.get(3).get());

                boolean check_count = true;

                for (int c : COMPACT_FISSION_REACTOR_CHECK) {
                    if (c < 0) {
                        check_count = false;
                        break;
                    }
                }

                if (check_count) {
                    for (int i1 = 0; i1 < chest.getContainerSize(); i1++) {
                        chest.setItem(i1, ItemStack.EMPTY);
                    }

                    int slotId = 0;

                    for(int i2 = 0; i2 < COMPACT_FISSION_REACTOR_LIST_COUNT.size();i2++) {
                        int count = COMPACT_FISSION_REACTOR_CHECK.get(i2);
                        while (count > 0) {
                            int remove_count = (count >= 64 ? 64 : COMPACT_FISSION_REACTOR_LIST_COUNT.get(i2));
                            count -= remove_count;
                            ItemStack itemStack = getItemStackFromId(COMPACT_FISSION_REACTOR_LIST.keySet().toArray()[i2].toString(), remove_count);
                            chest.setItem(slotId, itemStack);
                        }
                    }

                    chest.setChanged();
                }
            }
        }
    }
}

