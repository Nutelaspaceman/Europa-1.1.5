/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.gui.inventory.GuiCrafting
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 */
package com.europa.api.utilities.entity;

import com.europa.api.utilities.IMinecraft;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;

public class InventoryUtils
implements IMinecraft {
    public static int getCombatBlock(String string) {
        String input;
        int obsidianSlot = InventoryUtils.findBlock(Blocks.OBSIDIAN, 0, 9);
        int chestSlot = InventoryUtils.findBlock(Blocks.ENDER_CHEST, 0, 9);
        if (obsidianSlot == -1 && chestSlot == -1) {
            return -1;
        }
        if (obsidianSlot != -1 && chestSlot == -1) {
            return obsidianSlot;
        }
        if (obsidianSlot == -1) {
            return chestSlot;
        }
        if (input.equals("Obsidian")) {
            return obsidianSlot;
        }
        return chestSlot;
    }

    /*
     * WARNING - void declaration
     */
    public static void switchSlot(int n, boolean bl) {
        int slot;
        void silent;
        if (silent != false) {
            InventoryUtils.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(slot));
        } else {
            InventoryUtils.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(slot));
            InventoryUtils.mc.player.inventory.currentItem = slot;
        }
    }

    /*
     * WARNING - void declaration
     */
    public static int findItem(Item item, int n, int n2) {
        void maximum;
        for (void i = minimum; i <= maximum; ++i) {
            Item item2;
            ItemStack stack = InventoryUtils.mc.player.inventory.getStackInSlot((int)i);
            if (stack.getItem() != item2) continue;
            return (int)i;
        }
        return -1;
    }

    /*
     * WARNING - void declaration
     */
    public static int findBlock(Block block, int n, int n2) {
        void maximum;
        for (void i = minimum; i <= maximum; ++i) {
            Block block2;
            ItemStack stack = InventoryUtils.mc.player.inventory.getStackInSlot((int)i);
            if (!(stack.getItem() instanceof ItemBlock)) continue;
            ItemBlock item = (ItemBlock)stack.getItem();
            if (item.getBlock() != block2) continue;
            return (int)i;
        }
        return -1;
    }

    public static void switchToSlot(Class<? extends Item> class_) {
        Class<? extends Item> clazz;
        if (InventoryUtils.mc.player.getHeldItemMainhand().getItem().getClass().isAssignableFrom(clazz)) {
            return;
        }
        int slot = InventoryUtils.getHotbarItemSlot(clazz);
        if (slot == -1) {
            return;
        }
        InventoryUtils.mc.player.inventory.currentItem = slot;
    }

    public static void switchToSlot(Item item) {
        Item item2;
        if (InventoryUtils.mc.player.getHeldItemMainhand().getItem() == item2) {
            return;
        }
        int slot = InventoryUtils.getHotbarItemSlot(item2.getClass());
        if (slot == -1) {
            return;
        }
        InventoryUtils.mc.player.inventory.currentItem = slot;
    }

    public static void switchToPacketSlot(Item item) {
        Item item2;
        if (InventoryUtils.mc.player.getHeldItemMainhand().getItem() == item2) {
            return;
        }
        int slot = InventoryUtils.getHotbarItemSlot(item2.getClass());
        if (slot == -1) {
            return;
        }
        InventoryUtils.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(slot));
    }

    public static void switchToPacketSlot(Class<? extends Item> class_) {
        Class<? extends Item> clazz;
        if (InventoryUtils.mc.player.getHeldItemMainhand().getItem().getClass().isAssignableFrom(clazz)) {
            return;
        }
        int slot = InventoryUtils.getHotbarItemSlot(clazz);
        if (slot == -1) {
            return;
        }
        InventoryUtils.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(slot));
    }

    /*
     * WARNING - void declaration
     */
    public static int findItemInventorySlot(Item item, boolean bl) {
        AtomicInteger slot = new AtomicInteger();
        slot.set(-1);
        for (Map.Entry<Integer, ItemStack> entry : InventoryUtils.getInventoryAndHotbarSlots().entrySet()) {
            Item item2;
            if (entry.getValue().getItem() != item2) continue;
            if (entry.getKey() == 45) {
                void offHand;
                if (offHand == false) continue;
            }
            slot.set(entry.getKey());
            return slot.get();
        }
        return slot.get();
    }

    public static Map<Integer, ItemStack> getInventoryAndHotbarSlots() {
        if (InventoryUtils.mc.currentScreen instanceof GuiCrafting) {
            return InventoryUtils.getOtherSlot(10, 45);
        }
        return InventoryUtils.getInventorySlots(9, 44);
    }

    /*
     * WARNING - void declaration
     */
    public static Map<Integer, ItemStack> getInventorySlots(int n, int n2) {
        void last;
        HashMap<Integer, ItemStack> fullInventorySlots = new HashMap<Integer, ItemStack>();
        for (int current = currentI; current <= last; ++current) {
            fullInventorySlots.put(current, (ItemStack)InventoryUtils.mc.player.inventoryContainer.getInventory().get(current));
        }
        return fullInventorySlots;
    }

    /*
     * WARNING - void declaration
     */
    public static Map<Integer, ItemStack> getOtherSlot(int n, int n2) {
        void last;
        HashMap<Integer, ItemStack> fullInventorySlots = new HashMap<Integer, ItemStack>();
        for (int current = currentI; current <= last; ++current) {
            fullInventorySlots.put(current, (ItemStack)InventoryUtils.mc.player.openContainer.getInventory().get(current));
        }
        return fullInventorySlots;
    }

    public static void offhandItem(Item item) {
        block0: {
            Item item2;
            int slot = InventoryUtils.findItemInventorySlot(item2, false);
            if (slot == -1) break block0;
            InventoryUtils.mc.playerController.windowClick(InventoryUtils.mc.player.inventoryContainer.windowId, slot, 0, ClickType.PICKUP, (EntityPlayer)InventoryUtils.mc.player);
            InventoryUtils.mc.playerController.windowClick(InventoryUtils.mc.player.inventoryContainer.windowId, 45, 0, ClickType.PICKUP, (EntityPlayer)InventoryUtils.mc.player);
            InventoryUtils.mc.playerController.windowClick(InventoryUtils.mc.player.inventoryContainer.windowId, slot, 0, ClickType.PICKUP, (EntityPlayer)InventoryUtils.mc.player);
            InventoryUtils.mc.playerController.updateController();
        }
    }

    public static int getHotbarItemSlot(Class<? extends Item> class_) {
        int slot = -1;
        for (int i = 0; i < 9; ++i) {
            Class<? extends Item> item;
            if (!InventoryUtils.mc.player.inventory.getStackInSlot(i).getItem().getClass().isAssignableFrom(item)) continue;
            slot = i;
            break;
        }
        return slot;
    }

    public static int getHotbarBlockSlot(Block block) {
        int slot = -1;
        for (int i = 0; i < 9; ++i) {
            Block block2;
            Item item = InventoryUtils.mc.player.inventory.getStackInSlot(i).getItem();
            if (!(item instanceof ItemBlock) || !((ItemBlock)item).getBlock().equals((Object)block2)) continue;
            slot = i;
            break;
        }
        return slot;
    }

    public static int getHotbarItemSlot(Item item) {
        int slot = -1;
        for (int i = 0; i < 9; ++i) {
            Item item2;
            Item selection = InventoryUtils.mc.player.inventory.getStackInSlot(i).getItem();
            if (!selection.equals((Object)item2)) continue;
            slot = i;
            break;
        }
        return slot;
    }

    public static int getInventoryItemSlot(Item item) {
        for (int i = 0; i < 36; ++i) {
            Item item2;
            Item cacheItem = InventoryUtils.mc.player.inventory.getStackInSlot(i).getItem();
            if (cacheItem != item2) continue;
            if (i < 9) {
                i += 36;
            }
            return i;
        }
        return -1;
    }

    /*
     * WARNING - void declaration
     */
    public boolean isHotbar(int n) {
        void slot;
        return slot == false || slot == true || slot == 2 || slot == 3 || slot == 4 || slot == 5 || slot == 6 || slot == 7 || slot == 8;
        {
        }
    }

    public static enum SwitchModes {
        Normal,
        Silent,
        Strict;

    }

    public static enum Items {
        Obsidian,
        Chest;

    }
}

