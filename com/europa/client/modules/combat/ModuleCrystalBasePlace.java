/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.BlockDeadBush
 *  net.minecraft.block.BlockFire
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.BlockObsidian
 *  net.minecraft.block.BlockSnow
 *  net.minecraft.block.BlockTallGrass
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketHeldItemChange
 *  net.minecraft.util.math.BlockPos
 */
package com.europa.client.modules.combat;

import com.europa.api.manager.module.Module;
import com.europa.api.manager.module.ModuleCategory;
import com.europa.api.manager.value.impl.ValueNumber;
import com.europa.api.utilities.crystal.CrystalUtils;
import com.europa.api.utilities.entity.InventoryUtils;
import com.europa.api.utilities.math.MathUtils;
import com.europa.api.utilities.world.BlockUtils;
import com.europa.client.modules.combat.ModuleAutoCrystal;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockDeadBush;
import net.minecraft.block.BlockFire;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.util.math.BlockPos;

public class ModuleCrystalBasePlace
extends Module {
    public static ValueNumber enemyRange = new ValueNumber("EnemyRange", "EnemyRange", "", 5, 2, 8);
    public static ValueNumber palceReach = new ValueNumber("PlaceReach", "PlaceReach", "", Double.longBitsToDouble(Double.doubleToLongBits(1.3332397578486466) ^ 0x7FE154F3365AFD87L), Double.longBitsToDouble(Double.doubleToLongBits(0.32754788733590184) ^ 0x7FD4F68B69FED263L), Double.longBitsToDouble(Double.doubleToLongBits(1.0088346981557588) ^ 0x7FE8242FDA3A625EL));
    public static ValueNumber blockDistance = new ValueNumber("BlockDistance", "BlockDistance", "BlockDistance", 4, 2, 6);
    public EntityPlayer target;
    public boolean placed = false;
    public int oldSlot = -1;
    public BlockPos currentPos = null;

    public ModuleCrystalBasePlace() {
        super("CrystalBasePlace", "Crystal Base Place", "Automatically places obsidian at the enemy's feet.", ModuleCategory.COMBAT);
    }

    @Override
    public void onMotionUpdate() {
        block8: {
            block9: {
                if (ModuleCrystalBasePlace.mc.player == null || ModuleCrystalBasePlace.mc.world == null) {
                    return;
                }
                this.oldSlot = ModuleCrystalBasePlace.mc.player.inventory.currentItem;
                this.target = (EntityPlayer)this.getClosest();
                int obiSlot = InventoryUtils.getHotbarBlockSlot(Blocks.OBSIDIAN);
                if (this.target == null) break block8;
                BlockPos playerPos = new BlockPos(Math.floor(this.target.posX), this.target.posY, Math.floor(this.target.posZ));
                if (!this.placed) {
                    if (ModuleAutoCrystal.renderPosition != null) {
                        return;
                    }
                    BlockPos pos = this.getPos(this.target);
                    if (pos != null) {
                        if (obiSlot != -1) {
                            ModuleCrystalBasePlace.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(obiSlot));
                        }
                        BlockUtils.placeBlock(pos, true, false);
                        ModuleCrystalBasePlace.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.oldSlot));
                        this.placed = true;
                        this.currentPos = pos;
                    }
                }
                if (this.currentPos == null || !this.placed) break block8;
                if ((double)ModuleCrystalBasePlace.mc.player.getDistance((Entity)this.target) > enemyRange.getValue().doubleValue() || ModuleCrystalBasePlace.mc.player.getDistanceSq(this.currentPos) > MathUtils.square(blockDistance.getValue().doubleValue())) break block9;
                if (playerPos.y <= this.currentPos.y || BlockUtils.isIntercepted(this.currentPos.up())) break block9;
                if (ModuleCrystalBasePlace.mc.world.getBlockState(this.currentPos.up()).getBlock() == Blocks.AIR) break block8;
            }
            this.placed = false;
        }
    }

    /*
     * WARNING - void declaration
     */
    public BlockPos getPos(EntityPlayer entityPlayer) {
        void target;
        BlockPos placePos = null;
        BlockPos playerPos = new BlockPos(Math.floor(target.posX), target.posY, Math.floor(target.posZ));
        double dist = MathUtils.square(palceReach.getValue().doubleValue());
        for (BlockPos pos : CrystalUtils.getSphere(palceReach.getValue().floatValue(), true, false)) {
            double pDist;
            if (pos.getY() >= playerPos.getY() || pos == playerPos || !this.canPlace(pos, true, true) || ModuleCrystalBasePlace.mc.world.getBlockState(pos.up()).getBlock() != Blocks.AIR || BlockUtils.isIntercepted(pos.up()) || BlockUtils.isIntercepted(pos.up()) || !((pDist = target.getDistanceSq(pos)) < dist)) continue;
            dist = pDist;
            placePos = pos;
        }
        return placePos;
    }

    public Entity getClosest() {
        Entity returnEntity = null;
        double dist = enemyRange.getValue().doubleValue();
        for (Entity entity : ModuleCrystalBasePlace.mc.world.loadedEntityList) {
            double pDist;
            if (!(entity instanceof EntityPlayer) || entity == null || (double)ModuleCrystalBasePlace.mc.player.getDistance(entity) > dist || entity == ModuleCrystalBasePlace.mc.player || !((pDist = (double)ModuleCrystalBasePlace.mc.player.getDistance(entity)) < dist)) continue;
            dist = pDist;
            returnEntity = entity;
        }
        return returnEntity;
    }

    /*
     * WARNING - void declaration
     */
    public boolean canPlace(BlockPos blockPos, boolean bl, boolean bl2) {
        block3: {
            block2: {
                void bedrock;
                void obsidian;
                void pos;
                Block block = BlockUtils.mc.world.getBlockState((BlockPos)pos).getBlock();
                if (block instanceof BlockAir) break block2;
                if (block instanceof BlockLiquid) break block2;
                if (block instanceof BlockTallGrass || block instanceof BlockFire) break block2;
                if (block instanceof BlockDeadBush || block instanceof BlockSnow || block instanceof BlockObsidian && obsidian != false) break block2;
                if (block != Blocks.BEDROCK || bedrock == false) break block3;
            }
            return true;
        }
        return false;
    }

    @Override
    public void onDisable() {
        this.placed = false;
    }
}

