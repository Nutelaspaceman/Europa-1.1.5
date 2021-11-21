/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.entity.item.EntityBoat
 *  net.minecraft.entity.item.EntityMinecart
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraft.entity.monster.EntityIronGolem
 *  net.minecraft.entity.monster.EntityPigZombie
 *  net.minecraft.entity.passive.EntityAmbientCreature
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntitySquid
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.passive.EntityWolf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityFireball
 *  net.minecraft.entity.projectile.EntityShulkerBullet
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.Vec3d
 */
package com.europa.api.utilities.entity;

import com.europa.Europa;
import com.europa.api.utilities.IMinecraft;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityShulkerBullet;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class EntityUtils
implements IMinecraft {
    /*
     * WARNING - void declaration
     */
    public static Vec3d getLastTickPos(Entity entity, double d, double d2, double d3) {
        void z;
        void y;
        void x;
        Entity entity2;
        return new Vec3d((entity2.posX - entity2.lastTickPosX) * x, (entity2.posY - entity2.lastTickPosY) * y, (entity2.posZ - entity2.lastTickPosZ) * z);
    }

    /*
     * WARNING - void declaration
     */
    public static Vec3d getInterpolatedRenderPos(Entity entity, float f) {
        void ticks;
        Entity entity2;
        return EntityUtils.getInterpolatedPos(entity2, (double)ticks).subtract(Minecraft.getMinecraft().getRenderManager().renderPosX, Minecraft.getMinecraft().getRenderManager().renderPosY, Minecraft.getMinecraft().getRenderManager().renderPosZ);
    }

    public static boolean isProjectile(Entity entity) {
        Entity entity2;
        return entity2 instanceof EntityShulkerBullet || entity2 instanceof EntityFireball;
    }

    public static boolean isMobAggressive(Entity entity) {
        Entity entity2;
        block6: {
            block4: {
                block5: {
                    if (!(entity2 instanceof EntityPigZombie)) break block4;
                    if (((EntityPigZombie)entity2).isArmsRaised()) break block5;
                    if (!((EntityPigZombie)entity2).isAngry()) break block6;
                }
                return true;
            }
            if (entity2 instanceof EntityWolf) {
                return ((EntityWolf)entity2).isAngry() && !EntityUtils.mc.player.equals((Object)((EntityWolf)entity2).getOwner());
            }
            if (entity2 instanceof EntityEnderman) {
                return ((EntityEnderman)entity2).isScreaming();
            }
        }
        return EntityUtils.isHostileMob(entity2);
    }

    public static boolean isVehicle(Entity entity) {
        Entity entity2;
        return entity2 instanceof EntityBoat || entity2 instanceof EntityMinecart;
    }

    /*
     * WARNING - void declaration
     */
    public static Vec3d getCenter(double d, double d2, double d3) {
        void posZ;
        void posY;
        double posX;
        double x = Math.floor(posX) + Double.longBitsToDouble(Double.doubleToLongBits(22.5321180609703) ^ 0x7FD68838E3A57A77L);
        double y = Math.floor((double)posY);
        double z = Math.floor((double)posZ) + Double.longBitsToDouble(Double.doubleToLongBits(3.0619626332512278) ^ 0x7FE87EE643DB1AC2L);
        return new Vec3d(x, y, z);
    }

    public static float getHealth(Entity entity) {
        Entity entity2;
        if (EntityUtils.isLiving(entity2)) {
            EntityLivingBase livingBase = (EntityLivingBase)entity2;
            return livingBase.getHealth() + livingBase.getAbsorptionAmount();
        }
        return Float.intBitsToFloat(Float.floatToIntBits(3.0545621E38f) ^ 0x7F65CCBA);
    }

    /*
     * WARNING - void declaration
     */
    public static Vec3d getInterpolatedPos(Entity entity, double d) {
        void ticks;
        Entity entity2;
        return new Vec3d(entity2.lastTickPosX, entity2.lastTickPosY, entity2.lastTickPosZ).add(EntityUtils.getLastTickPos(entity2, (double)ticks, (double)ticks, (double)ticks));
    }

    /*
     * WARNING - void declaration
     */
    public static Vec3d interpolateEntity(Entity entity, float f) {
        void time;
        Entity entity2;
        return new Vec3d(entity2.lastTickPosX + (entity2.posX - entity2.lastTickPosX) * (double)time, entity2.lastTickPosY + (entity2.posY - entity2.lastTickPosY) * (double)time, entity2.lastTickPosZ + (entity2.posZ - entity2.lastTickPosZ) * (double)time);
    }

    public static boolean isntValid(Entity entity) {
        Entity entity2;
        return entity2 == null || EntityUtils.isDead(entity2) || entity2.equals((Object)EntityUtils.mc.player) || entity2 instanceof EntityPlayer && Europa.FRIEND_MANAGER.isFriend(entity2.getName());
    }

    public static boolean isDead(Entity entity) {
        Entity entity2;
        return !EntityUtils.isAlive(entity2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean isAlive(Entity entity) {
        Entity entity2;
        if (!EntityUtils.isLiving(entity2)) return false;
        if (entity2.isDead) return false;
        if (!(((EntityLivingBase)entity2).getHealth() > Float.intBitsToFloat(Float.floatToIntBits(8.671327E37f) ^ 0x7E8278BD))) return false;
        return true;
    }

    public static boolean isLiving(Entity entity) {
        Entity e;
        return e instanceof EntityLivingBase;
    }

    public static boolean isHostileMob(Entity entity) {
        Entity entity2;
        return entity2.isCreatureType(EnumCreatureType.MONSTER, false) && !EntityUtils.isNeutralMob(entity2);
    }

    public static boolean isNeutralMob(Entity entity) {
        Entity entity2;
        return entity2 instanceof EntityPigZombie || entity2 instanceof EntityWolf || entity2 instanceof EntityEnderman;
    }

    public static boolean isPassive(Entity entity) {
        Entity e;
        if (e instanceof EntityWolf && ((EntityWolf)e).isAngry()) {
            return false;
        }
        if (e instanceof EntityAnimal || e instanceof EntityAgeable || e instanceof EntityTameable || e instanceof EntityAmbientCreature || e instanceof EntitySquid) {
            return true;
        }
        return e instanceof EntityIronGolem && ((EntityIronGolem)e).getRevengeTarget() == null;
    }

    /*
     * WARNING - void declaration
     */
    public static List<BlockPos> getSphere(BlockPos blockPos, float f, int n, boolean bl, boolean bl2, int n2) {
        void r;
        BlockPos loc;
        ArrayList<BlockPos> circleBlocks = new ArrayList<BlockPos>();
        int cx = loc.getX();
        int cy = loc.getY();
        int cz = loc.getZ();
        int x = cx - (int)r;
        while ((float)x <= (float)cx + r) {
            int z = cz - (int)r;
            while ((float)z <= (float)cz + r) {
                void sphere;
                int y = sphere != false ? cy - (int)r : cy;
                while (true) {
                    void hollow;
                    void h;
                    float f2 = y;
                    float f3 = sphere != false ? (float)cy + r : (float)(cy + h);
                    if (!(f2 < f3)) break;
                    double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere != false ? (cy - y) * (cy - y) : 0);
                    if (dist < (double)(r * r) && (hollow == false || !(dist < (double)((r - Float.intBitsToFloat(Float.floatToIntBits(4.3435817f) ^ 0x7F0AFE9F)) * (r - Float.intBitsToFloat(Float.floatToIntBits(7.0435686f) ^ 0x7F6164EA)))))) {
                        void plusY;
                        BlockPos l = new BlockPos(x, y + plusY, z);
                        circleBlocks.add(l);
                    }
                    ++y;
                }
                ++z;
            }
            ++x;
        }
        return circleBlocks;
    }

    public static boolean isMoving() {
        return EntityUtils.mc.player.motionX > Double.longBitsToDouble(Double.doubleToLongBits(1.9208899197057635E307) ^ 0x7FBB5AB8AD5DB287L) || EntityUtils.mc.player.motionX < Double.longBitsToDouble(Double.doubleToLongBits(-4.710141003534489E307) ^ 0x7FD0C4C6CF24C3EFL) || EntityUtils.mc.player.motionZ > Double.longBitsToDouble(Double.doubleToLongBits(1.3182290674040571E308) ^ 0x7FE7771B096D7F3FL) || EntityUtils.mc.player.motionZ < Double.longBitsToDouble(Double.doubleToLongBits(-7.645656144229753E307) ^ 0x7FDB382D2E04048FL);
    }
}

