/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 */
package com.europa.api.utilities.crystal;

import com.europa.Europa;
import com.europa.api.utilities.IMinecraft;
import com.europa.api.utilities.math.MathUtils;
import com.europa.api.utilities.render.RenderUtils;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class CrystalUtils
implements IMinecraft {
    public static EntityPlayer getTarget(float f) {
        EntityPlayer targetPlayer = null;
        for (EntityPlayer player : new ArrayList(CrystalUtils.mc.world.playerEntities)) {
            float range;
            if (CrystalUtils.mc.player.getDistanceSq((Entity)player) > (double)MathUtils.square(range) || player == CrystalUtils.mc.player || Europa.FRIEND_MANAGER.isFriend(player.getName()) || player.isDead || player.getHealth() <= Float.intBitsToFloat(Float.floatToIntBits(1.2784752E38f) ^ 0x7EC05D13)) continue;
            if (targetPlayer == null) {
                targetPlayer = player;
                continue;
            }
            if (!(CrystalUtils.mc.player.getDistanceSq((Entity)player) < CrystalUtils.mc.player.getDistanceSq((Entity)targetPlayer))) continue;
            targetPlayer = player;
        }
        return targetPlayer;
    }

    /*
     * WARNING - void declaration
     */
    public static List<BlockPos> getSphere(float f, boolean bl, boolean bl2) {
        float range;
        ArrayList<BlockPos> blocks = new ArrayList<BlockPos>();
        int x = CrystalUtils.mc.player.getPosition().getX() - (int)range;
        while ((float)x <= (float)CrystalUtils.mc.player.getPosition().getX() + range) {
            int z = CrystalUtils.mc.player.getPosition().getZ() - (int)range;
            while ((float)z <= (float)CrystalUtils.mc.player.getPosition().getZ() + range) {
                void sphere;
                int y;
                int n = y = sphere != false ? CrystalUtils.mc.player.getPosition().getY() - (int)range : CrystalUtils.mc.player.getPosition().getY();
                while ((float)y < (float)CrystalUtils.mc.player.getPosition().getY() + range) {
                    void hollow;
                    double distance = (CrystalUtils.mc.player.getPosition().getX() - x) * (CrystalUtils.mc.player.getPosition().getX() - x) + (CrystalUtils.mc.player.getPosition().getZ() - z) * (CrystalUtils.mc.player.getPosition().getZ() - z) + (sphere != false ? (CrystalUtils.mc.player.getPosition().getY() - y) * (CrystalUtils.mc.player.getPosition().getY() - y) : 0);
                    if (distance < (double)(range * range) && (hollow == false || distance >= ((double)range - Double.longBitsToDouble(Double.doubleToLongBits(638.4060856917202) ^ 0x7F73F33FA9DAEA7FL)) * ((double)range - Double.longBitsToDouble(Double.doubleToLongBits(13.015128470890444) ^ 0x7FDA07BEEB3F6D07L)))) {
                        blocks.add(new BlockPos(x, y, z));
                    }
                    ++y;
                }
                ++z;
            }
            ++x;
        }
        return blocks;
    }

    /*
     * WARNING - void declaration
     */
    public static boolean canPlaceCrystal(BlockPos blockPos, boolean bl, boolean bl2, boolean bl3) {
        void multiPlace;
        void placeUnderBlock;
        BlockPos position;
        if (CrystalUtils.mc.world.getBlockState(position).getBlock() != Blocks.BEDROCK && CrystalUtils.mc.world.getBlockState(position).getBlock() != Blocks.OBSIDIAN) {
            return false;
        }
        if (CrystalUtils.mc.world.getBlockState(position.add(0, 1, 0)).getBlock() != Blocks.AIR || placeUnderBlock == false && CrystalUtils.mc.world.getBlockState(position.add(0, 2, 0)).getBlock() != Blocks.AIR) {
            return false;
        }
        if (multiPlace != false) {
            return CrystalUtils.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(position.add(0, 1, 0))).isEmpty() && placeUnderBlock == false && CrystalUtils.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(position.add(0, 2, 0))).isEmpty();
        }
        for (Entity entity : CrystalUtils.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(position.add(0, 1, 0)))) {
            if (entity instanceof EntityEnderCrystal) continue;
            return false;
        }
        if (placeUnderBlock == false) {
            for (Entity entity : CrystalUtils.mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(position.add(0, 2, 0)))) {
                void holePlace;
                if (entity instanceof EntityEnderCrystal || holePlace != false && entity instanceof EntityPlayer) continue;
                return false;
            }
        }
        return true;
    }

    /*
     * WARNING - void declaration
     */
    public static void drawText(BlockPos blockPos, String string) {
        void text;
        BlockPos pos;
        GlStateManager.pushMatrix();
        RenderUtils.glBillboardDistanceScaled((float)pos.getX() + Float.intBitsToFloat(Float.floatToIntBits(56.765804f) ^ 0x7D63102F), (float)pos.getY() + Float.intBitsToFloat(Float.floatToIntBits(22.30964f) ^ 0x7EB27A25), (float)pos.getZ() + Float.intBitsToFloat(Float.floatToIntBits(15.876006f) ^ 0x7E7E041F), (EntityPlayer)CrystalUtils.mc.player, Float.intBitsToFloat(Float.floatToIntBits(33.090816f) ^ 0x7D845CFF));
        GlStateManager.disableDepth();
        GlStateManager.translate((double)(-((double)Europa.FONT_MANAGER.getStringWidth((String)text) / Double.longBitsToDouble(Double.doubleToLongBits(0.2579471275262499) ^ 0x7FD08234AB34A1F3L))), (double)Double.longBitsToDouble(Double.doubleToLongBits(8.964569445934237E307) ^ 0x7FDFEA38A13E706BL), (double)Double.longBitsToDouble(Double.doubleToLongBits(1.3014105849625204E308) ^ 0x7FE72A76F089AAE7L));
        Europa.FONT_MANAGER.drawString((String)text, Float.intBitsToFloat(Float.floatToIntBits(2.2813044E36f) ^ 0x7BDBAE7F), Float.intBitsToFloat(Float.floatToIntBits(1.2346908E38f) ^ 0x7EB9C68F), Color.WHITE);
        GlStateManager.popMatrix();
    }

    public static boolean isEntityMoving(EntityLivingBase entityLivingBase) {
        EntityLivingBase entity;
        return entity.motionX > Double.longBitsToDouble(Double.doubleToLongBits(0.5327718501168097) ^ 0x7FE10C778D0F6544L) || entity.motionY > Double.longBitsToDouble(Double.doubleToLongBits(0.07461435496686485) ^ 0x7FB319ED266512E7L) || entity.motionZ > Double.longBitsToDouble(Double.doubleToLongBits(0.9006325807477794) ^ 0x7FECD1FB6B00C2E7L);
    }

    public static boolean canSeePos(BlockPos blockPos) {
        BlockPos pos;
        return CrystalUtils.mc.world.rayTraceBlocks(new Vec3d(CrystalUtils.mc.player.posX, CrystalUtils.mc.player.posY + (double)CrystalUtils.mc.player.getEyeHeight(), CrystalUtils.mc.player.posZ), new Vec3d((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()), false, true, false) == null;
    }
}

