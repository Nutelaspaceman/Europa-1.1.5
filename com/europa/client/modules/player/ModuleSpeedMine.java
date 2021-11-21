/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Enchantments
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.network.play.client.CPacketPlayerDigging
 *  net.minecraft.network.play.client.CPacketPlayerDigging$Action
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3i
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.europa.client.modules.player;

import com.europa.api.manager.event.impl.render.EventRender3D;
import com.europa.api.manager.event.impl.world.EventBlock;
import com.europa.api.manager.event.impl.world.EventClickBlock;
import com.europa.api.manager.module.Module;
import com.europa.api.manager.module.ModuleCategory;
import com.europa.api.manager.value.impl.ValueBoolean;
import com.europa.api.manager.value.impl.ValueColor;
import com.europa.api.manager.value.impl.ValueEnum;
import com.europa.api.manager.value.impl.ValueNumber;
import com.europa.api.utilities.crystal.CrystalUtils;
import com.europa.api.utilities.entity.InventoryUtils;
import com.europa.api.utilities.math.MathUtils;
import com.europa.api.utilities.math.TimerUtils;
import com.europa.api.utilities.render.RenderUtils;
import com.europa.api.utilities.world.BlockUtils;
import com.europa.api.utilities.world.HoleUtils;
import com.europa.client.modules.player.ModulePacketMine;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModuleSpeedMine
extends Module {
    public TimerUtils timer = new TimerUtils();
    public static ValueEnum switchMode = new ValueEnum("Switch", "Switch", "The mode for switching.", SwitchModes.None);
    public static ValueNumber resetRange = new ValueNumber("ResetRange", "ResetRange", "The range for resetting the current position.", Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(0.023623316f) ^ 0x7DE185AF)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(7.4043756f) ^ 0x7F6CF0A5)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(0.04388196f) ^ 0x7F7BBD92)));
    public static ValueBoolean switchBlock = new ValueBoolean("SwitchBlock", "SwitchBlock", "When mining a block and you select another one, it mines that block instead.", true);
    public static ValueNumber speed = new ValueNumber("Speed", "Speed", "The speed for the mining.", Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(3.1776779f) ^ 0x7F786C20)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(1.7112044E38f) ^ 0x7F00BC99)), Float.valueOf(Float.intBitsToFloat(Float.floatToIntBits(15.198823f) ^ 0x7EF32E61)));
    public ValueBoolean autoMine = new ValueBoolean("AutoMine", "AutoMine", "", false);
    public ValueNumber autoMineRange = new ValueNumber("AutoMineRange", "AutoMineRange", "", Double.longBitsToDouble(Double.doubleToLongBits(0.2203019352181864) ^ 0x7FD432DA9380F9BBL), Double.longBitsToDouble(Double.doubleToLongBits(1.4519329411194038E308) ^ 0x7FE9D8633CF1EB54L), Double.longBitsToDouble(Double.doubleToLongBits(1.1655771630017673) ^ 0x7FD2A6343D40E9AFL));
    public static ValueEnum renderMode = new ValueEnum("RenderMode", "RenderMode", "", renderModes.Grow);
    public static ValueBoolean statusColor = new ValueBoolean("StatusColor", "StatusColor", "", false);
    public static ValueEnum statusMode = new ValueEnum("StatusMode", "StatusMode", "", statusModes.Static);
    public ValueColor color = new ValueColor("Color", "Color", "Color", new Color(255, 0, 0, 100));
    public List<BlockPos> offsets = new ArrayList<BlockPos>(Arrays.asList(new BlockPos[]{new BlockPos(0, -1, 0), new BlockPos(1, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(0, 0, 1), new BlockPos(0, 0, -1)}));
    public BlockPos currentPosition = null;
    public EnumFacing currentFacing = null;
    public long starTime = (long)2146049642 ^ 0x7FEA1E6AL;
    public EntityPlayer target;
    public boolean hitted = false;
    public BlockPos oldcity = null;

    public ModuleSpeedMine() {
        super("SpeedMine", "Speed Mine", "Makes mining faster and easier.", ModuleCategory.PLAYER);
    }

    @Override
    public void onDisable() {
        this.hitted = false;
    }

    @Override
    public void onRender3D(EventRender3D eventRender3D) {
        block1: {
            Color sColor;
            if (this.currentPosition == null) break block1;
            AxisAlignedBB bb = RenderUtils.fixBB(new AxisAlignedBB(this.currentPosition));
            double growAmt = this.normalize((float)(System.currentTimeMillis() - this.starTime) / Float.intBitsToFloat(Float.floatToIntBits(0.08986692f) ^ 0x7F700C26), Double.longBitsToDouble(Double.doubleToLongBits(7.007928029000862E307) ^ 0x7FD8F2F51150C7E9L), this.getBreakSpeed(this.currentPosition));
            double grow = MathHelper.clamp((double)growAmt, (double)Double.longBitsToDouble(Double.doubleToLongBits(1.1826870463364938E308) ^ 0x7FE50D727D931E57L), (double)Double.longBitsToDouble(Double.doubleToLongBits(2.975911616094987) ^ 0x7FE7CEAABFD750D9L));
            if (renderMode.getValue().equals((Object)renderModes.Grow)) {
                bb = bb.shrink(Double.longBitsToDouble(Double.doubleToLongBits(77.43114307965799) ^ 0x7FB35B97D924C1C7L));
                bb = bb.grow(grow);
            }
            float sGreen = MathHelper.clamp((float)((float)growAmt), (float)Float.intBitsToFloat(Float.floatToIntBits(5.297968E35f) ^ 0x7ACC11FF), (float)Float.intBitsToFloat(Float.floatToIntBits(7.8681602f) ^ 0x7F7BC7F8));
            Color color = statusMode.getValue().equals((Object)ModulePacketMine.statusModes.Static) ? new Color(this.timer.hasReached(this.calculateTime(this.currentPosition)) ? 0 : 255, this.timer.hasReached(this.calculateTime(this.currentPosition)) ? 255 : 0, 0, this.color.getValue().getAlpha()) : (sColor = new Color(255 - (int)(sGreen * Float.intBitsToFloat(Float.floatToIntBits(0.009426891f) ^ 0x7F65733F)), (int)(sGreen * Float.intBitsToFloat(Float.floatToIntBits(0.012214023f) ^ 0x7F371D53)), 0, this.color.getValue().getAlpha()));
            RenderUtils.drawFilledBox(bb, statusColor.getValue() ? sColor.getRGB() : this.color.getValue().getRGB());
            RenderUtils.drawBlockOutline(bb, statusColor.getValue() ? sColor : this.color.getValue(), Float.intBitsToFloat(Float.floatToIntBits(36.652126f) ^ 0x7D929BC7));
        }
    }

    @Override
    public void onUpdate() {
        block20: {
            block22: {
                block21: {
                    block19: {
                        block18: {
                            if (ModuleSpeedMine.mc.player == null) break block18;
                            if (ModuleSpeedMine.mc.world != null) break block19;
                        }
                        return;
                    }
                    this.target = CrystalUtils.getTarget(this.autoMineRange.getValue().floatValue());
                    if (this.autoMine.getValue() && this.target != null) {
                        if (HoleUtils.isInHole(this.target)) {
                            BlockPos cityPos = this.getCity(this.target);
                            if (ModuleSpeedMine.mc.world.getBlockState(cityPos).getBlock() != Blocks.AIR) {
                                if (!this.hitted && cityPos != null && ModuleSpeedMine.mc.world.getBlockState((BlockPos)cityPos).getBlock().blockHardness != Float.intBitsToFloat(Float.floatToIntBits(-7.198885f) ^ 0x7F665D44)) {
                                    this.timer.reset();
                                    ModuleSpeedMine.mc.playerController.isHittingBlock = false;
                                    ModuleSpeedMine.mc.player.swingArm(EnumHand.MAIN_HAND);
                                    this.currentPosition = cityPos;
                                    this.oldcity = cityPos;
                                    this.currentFacing = BlockUtils.getFirstFacing(cityPos);
                                    this.starTime = System.currentTimeMillis();
                                    ModuleSpeedMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, cityPos, BlockUtils.getFirstFacing(cityPos)));
                                    ModuleSpeedMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, cityPos, BlockUtils.getFirstFacing(cityPos)));
                                    this.hitted = true;
                                }
                            }
                            if (cityPos != null && this.oldcity != null) {
                                if (ModuleSpeedMine.mc.player.getDistanceSq(cityPos) > (double)MathUtils.square(resetRange.getValue().floatValue()) || !this.oldcity.equals((Object)cityPos) || ModuleSpeedMine.mc.world.getBlockState(cityPos).getBlock().equals((Object)Blocks.AIR)) {
                                    this.hitted = false;
                                }
                            }
                        }
                    }
                    if (this.currentPosition == null) break block20;
                    if (ModuleSpeedMine.mc.world.getBlockState(this.currentPosition).getBlock() == Blocks.AIR) break block21;
                    if (!(ModuleSpeedMine.mc.player.getDistanceSq(this.currentPosition) > (double)MathUtils.square(resetRange.getValue().floatValue()))) break block22;
                }
                ModuleSpeedMine.mc.playerController.isHittingBlock = false;
                ModuleSpeedMine.mc.playerController.curBlockDamageMP = Float.intBitsToFloat(Float.floatToIntBits(2.6906754E38f) ^ 0x7F4A6C86);
                this.currentPosition = null;
                this.currentFacing = null;
                return;
            }
            int slot = InventoryUtils.findItem(Items.DIAMOND_PICKAXE, 0, 9);
            int lastSlot = ModuleSpeedMine.mc.player.inventory.currentItem;
            if (slot == -1) {
                return;
            }
            if (ModuleSpeedMine.mc.player.inventory.currentItem == slot || !this.timer.hasReached(this.calculateTime(this.currentPosition))) break block20;
            if (!switchMode.getValue().equals((Object)SwitchModes.None)) {
                InventoryUtils.switchSlot(slot, switchMode.getValue().equals((Object)SwitchModes.Silent));
            }
            if (switchBlock.getValue()) {
                ModuleSpeedMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, this.currentPosition, this.currentFacing));
            }
            if (switchMode.getValue().equals((Object)SwitchModes.Silent)) {
                ModuleSpeedMine.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(lastSlot));
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    @SubscribeEvent
    public void onBlock(EventBlock eventBlock) {
        void event;
        if (ModuleSpeedMine.mc.player == null || ModuleSpeedMine.mc.world == null) {
            return;
        }
        if (ModuleSpeedMine.mc.world.getBlockState((BlockPos)event.getPos()).getBlock().blockHardness == Float.intBitsToFloat(Float.floatToIntBits(-19.81507f) ^ 0x7E1E8543)) {
            return;
        }
        this.timer.reset();
        ModuleSpeedMine.mc.playerController.isHittingBlock = false;
        ModuleSpeedMine.mc.player.swingArm(EnumHand.MAIN_HAND);
        if (event.getPos() != this.currentPosition) {
            if (this.currentPosition != null && switchBlock.getValue()) {
                ModuleSpeedMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.currentPosition, this.currentFacing));
                ModuleSpeedMine.mc.playerController.isHittingBlock = false;
                ModuleSpeedMine.mc.playerController.curBlockDamageMP = Float.intBitsToFloat(Float.floatToIntBits(3.2516966E38f) ^ 0x7F74A166);
            }
        }
        this.currentPosition = event.getPos();
        this.currentFacing = event.facing;
        this.starTime = System.currentTimeMillis();
        ModuleSpeedMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, event.getPos(), event.facing));
        ModuleSpeedMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, event.getPos(), event.facing));
        event.setCancelled(true);
    }

    @SubscribeEvent
    public void onClickBlock(EventClickBlock eventClickBlock) {
        if (ModuleSpeedMine.mc.playerController.curBlockDamageMP > Float.intBitsToFloat(Float.floatToIntBits(343.17242f) ^ 0x7E675ADF)) {
            ModuleSpeedMine.mc.playerController.isHittingBlock = true;
        }
    }

    /*
     * WARNING - void declaration
     */
    public BlockPos getCity(EntityPlayer entityPlayer) {
        BlockPos returnPos = null;
        for (BlockPos blockPos : this.offsets) {
            void player;
            BlockPos playerPos = new BlockPos(Math.floor(player.posX), player.posY, Math.floor(player.posZ));
            BlockPos pos = playerPos.add((Vec3i)blockPos);
            if (!(ModuleSpeedMine.mc.player.getDistanceSq(pos) < (double)MathUtils.square(resetRange.getValue().floatValue()))) continue;
            if (returnPos == null) {
                returnPos = pos;
            }
            if (!(ModuleSpeedMine.mc.player.getDistanceSq(returnPos) > ModuleSpeedMine.mc.player.getDistanceSq(pos))) continue;
            returnPos = pos;
        }
        return returnPos;
    }

    /*
     * WARNING - void declaration
     */
    public long calculateTime(BlockPos blockPos) {
        void position;
        IBlockState state = ModuleSpeedMine.mc.world.getBlockState((BlockPos)position);
        float hardness = state.getBlockHardness((World)ModuleSpeedMine.mc.world, (BlockPos)position);
        float breakSpeed = this.getBreakSpeed((BlockPos)position);
        if (breakSpeed == Float.intBitsToFloat(Float.floatToIntBits(-60.744534f) ^ 0x7DF2FA67)) {
            return (long)1610689876 ^ 0xFFFFFFFF9FFED2ABL;
        }
        float relative = breakSpeed / hardness / Float.intBitsToFloat(Float.floatToIntBits(0.97616214f) ^ 0x7E89E5C3);
        int ticks = (int)Math.ceil(speed.getValue().floatValue() / relative);
        return (long)ticks * ((long)1533733885 ^ 0x5B6AEBCFL);
    }

    /*
     * WARNING - void declaration
     */
    public float getBreakSpeed(BlockPos blockPos) {
        void position;
        IBlockState state = ModuleSpeedMine.mc.world.getBlockState((BlockPos)position);
        float maxSpeed = Float.intBitsToFloat(Float.floatToIntBits(41.943356f) ^ 0x7DA7C5FF);
        for (int i = 0; i <= 9; ++i) {
            float speed;
            ItemStack stack = ModuleSpeedMine.mc.player.inventory.getStackInSlot(i);
            if (stack.isEmpty() || (speed = stack.getDestroySpeed(state)) <= Float.intBitsToFloat(Float.floatToIntBits(106.26891f) ^ 0x7D5489AF)) continue;
            int efficiency = EnchantmentHelper.getEnchantmentLevel((Enchantment)Enchantments.EFFICIENCY, (ItemStack)stack);
            if (efficiency > 0) {
                speed += MathUtils.square(efficiency) + Float.intBitsToFloat(Float.floatToIntBits(9.79176f) ^ 0x7E9CAB0D);
            }
            if (!(speed > maxSpeed)) continue;
            maxSpeed = speed;
        }
        return maxSpeed;
    }

    /*
     * WARNING - void declaration
     */
    public double normalize(double d, double d2, double d3) {
        void max;
        void min;
        void value;
        return (double)((value - min) / (max - min));
    }

    public static enum SwitchModes {
        None,
        Normal,
        Silent;

    }

    public static enum statusModes {
        Static,
        Smooth;

    }

    public static enum renderModes {
        Grow,
        Static;

    }
}

