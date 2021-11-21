/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.IBlockAccess
 */
package com.europa.client.modules.combat;

import com.europa.api.manager.misc.ChatManager;
import com.europa.api.manager.module.Module;
import com.europa.api.manager.module.ModuleCategory;
import com.europa.api.manager.value.impl.ValueBoolean;
import com.europa.api.manager.value.impl.ValueEnum;
import com.europa.api.manager.value.impl.ValueNumber;
import com.europa.api.utilities.crystal.CrystalUtils;
import com.europa.api.utilities.entity.InventoryUtils;
import com.europa.api.utilities.world.BlockUtils;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;

public class ModuleAutoTrap
extends Module {
    public static EntityPlayer target = null;
    public int placements;
    public Vec3d[] offsets = new Vec3d[]{new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-12.70930411607072) ^ 0x7FD96B29E8BAE245L), Double.longBitsToDouble(Double.doubleToLongBits(1.0695487518619992E308) ^ 0x7FE309E1AC43DB5BL), Double.longBitsToDouble(Double.doubleToLongBits(1.3898341353500821E308) ^ 0x7FE8BD680AF7E621L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(11.65455321855089) ^ 0x7FD74F21997655D1L), Double.longBitsToDouble(Double.doubleToLongBits(4.543329638262753E307) ^ 0x7FD02CBF10874CA3L), Double.longBitsToDouble(Double.doubleToLongBits(1.2803236157753495E308) ^ 0x7FE6CA5F502A138DL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(2.5342186903570137E307) ^ 0x7FC20B530D71FC67L), Double.longBitsToDouble(Double.doubleToLongBits(1.812463801241337E306) ^ 0x7F84A5F5386EE43FL), Double.longBitsToDouble(Double.doubleToLongBits(-51.6068516213524) ^ 0x7FB9CDAD505D9DD7L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(5.617090066678542E307) ^ 0x7FD3FF5C73450201L), Double.longBitsToDouble(Double.doubleToLongBits(8.184940273330963E307) ^ 0x7FDD23ACBE829B3BL), Double.longBitsToDouble(Double.doubleToLongBits(8.742034748119456) ^ 0x7FD17BEBFA7F565BL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(-4.664159158748255) ^ 0x7FE2A81956A8A688L), Double.longBitsToDouble(Double.doubleToLongBits(34.50366551835463) ^ 0x7FB140781C98BA5FL), Double.longBitsToDouble(Double.doubleToLongBits(1.2942333158915355E308) ^ 0x7FE709C2159FDADCL)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(4.921438733305393) ^ 0x7FE3AF8DA2A341DFL), Double.longBitsToDouble(Double.doubleToLongBits(7.919795611198031) ^ 0x7FEFADDEE6946646L), Double.longBitsToDouble(Double.doubleToLongBits(1.4287079435793766E308) ^ 0x7FE96E8D6E778942L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.590574799023954E307) ^ 0x7FB6A68997A9F59FL), Double.longBitsToDouble(Double.doubleToLongBits(8.741388869824133) ^ 0x7FD17B97526B06AFL), Double.longBitsToDouble(Double.doubleToLongBits(-6.886132843562002) ^ 0x7FEB8B66687C0A88L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(4.0207624438413595E307) ^ 0x7FCCA0F7A75B8B87L), Double.longBitsToDouble(Double.doubleToLongBits(7.107588586873259) ^ 0x7FEC6E2BB3D82C8CL), Double.longBitsToDouble(Double.doubleToLongBits(13.555171246712641) ^ 0x7FDB1C3F67D89EE5L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(1.4020824524787114E308) ^ 0x7FE8F538ADC5D95CL), Double.longBitsToDouble(Double.doubleToLongBits(0.38513719503800053) ^ 0x7FD8A6167A4A5425L), Double.longBitsToDouble(Double.doubleToLongBits(4.471559927213953) ^ 0x7FE1E2E09B05F3C8L)), new Vec3d(Double.longBitsToDouble(Double.doubleToLongBits(6.131733730806719E307) ^ 0x7FD5D46700988CD5L), Double.longBitsToDouble(Double.doubleToLongBits(0.12998025666110288) ^ 0x7FC0A3316BBE1883L), Double.longBitsToDouble(Double.doubleToLongBits(2.968408324008927E307) ^ 0x7FC522C1BF303D43L))};
    public static ValueEnum item = new ValueEnum("Item", "Item", "The item for block placing.", InventoryUtils.Items.Obsidian);
    public static ValueEnum switchMode = new ValueEnum("Switch", "Switch", "The mode for switching.", InventoryUtils.SwitchModes.Normal);
    public static ValueNumber blocks = new ValueNumber("Blocks", "Blocks", "The amount of blocks that can be placed per tick.", 8, 1, 40);
    public static ValueNumber range = new ValueNumber("Range", "Range", "The maximum range that the block can be away.", Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(0.38584408f) ^ 0x7E458D5B)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(7.2009587E37f) ^ 0x7E58B22B)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(1.7292773f) ^ 0x7EDD58F5)));
    public static ValueNumber targetRange = new ValueNumber("TargetRange", "TargetRange", "The range for the targeting.", Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(1.3390633f) ^ 0x7F6B666D)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(1.8326165E38f) ^ 0x7F09DEE9)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(0.17609157f) ^ 0x7F345159)));
    public static ValueBoolean selfDisable = new ValueBoolean("SelfDisable", "SelfDisable", "Automatically disables when there are no more holes.", true);

    public ModuleAutoTrap() {
        super("AutoTrap", "Auto Trap", "Automatically traps your target with obsidian.", ModuleCategory.COMBAT);
    }

    @Override
    public void onMotionUpdate() {
        block5: {
            target = CrystalUtils.getTarget(targetRange.getValue().floatValue());
            if (target == null) {
                return;
            }
            int slot = InventoryUtils.getCombatBlock(item.getValue().toString());
            int lastSlot = ModuleAutoTrap.mc.player.inventory.currentItem;
            if (slot == -1) {
                ChatManager.sendClientMessage("No blocks could be found.", 256);
                this.disable();
                return;
            }
            this.placements = 0;
            if (!this.getPlaceableBlocks(target).isEmpty()) {
                InventoryUtils.switchSlot(slot, switchMode.getValue().equals((Object)InventoryUtils.SwitchModes.Silent));
                for (BlockPos position : this.getPlaceableBlocks(target)) {
                    this.placeBlock(position);
                }
                if (!switchMode.getValue().equals((Object)InventoryUtils.SwitchModes.Strict)) {
                    InventoryUtils.switchSlot(lastSlot, switchMode.getValue().equals((Object)InventoryUtils.SwitchModes.Silent));
                }
            }
            if (!selfDisable.getValue() || !this.getPlaceableBlocks(target).isEmpty()) break block5;
            this.disable();
        }
    }

    /*
     * WARNING - void declaration
     */
    public void placeBlock(BlockPos blockPos) {
        void position;
        if (BlockUtils.isPositionPlaceable((BlockPos)position, true, true, false) && this.placements < blocks.getValue().intValue()) {
            BlockUtils.placeBlock((BlockPos)position, EnumHand.MAIN_HAND, true);
            ++this.placements;
        }
    }

    /*
     * WARNING - void declaration
     */
    public List<BlockPos> getPlaceableBlocks(EntityPlayer entityPlayer) {
        ArrayList<BlockPos> positions = new ArrayList<BlockPos>();
        for (Vec3d vec3d : this.offsets) {
            void player;
            BlockPos position = new BlockPos(vec3d.add(player.getPositionVector()));
            if (!ModuleAutoTrap.mc.world.getBlockState(position).getBlock().isReplaceable((IBlockAccess)ModuleAutoTrap.mc.world, position)) continue;
            positions.add(position);
        }
        return positions;
    }
}

