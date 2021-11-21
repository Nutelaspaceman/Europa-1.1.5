/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.Vec3d
 */
package com.europa.api.utilities.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

public class MathUtils {
    /*
     * WARNING - void declaration
     */
    public static double roundToPlaces(double d, int n) {
        void places;
        double number;
        BigDecimal decimal = new BigDecimal(number);
        decimal = decimal.setScale((int)places, RoundingMode.HALF_UP);
        return decimal.doubleValue();
    }

    /*
     * WARNING - void declaration
     */
    public static Vec3d roundVector(Vec3d vec3d, int n) {
        void places;
        Vec3d vec3d2;
        return new Vec3d(MathUtils.roundToPlaces(vec3d2.x, (int)places), MathUtils.roundToPlaces(vec3d2.y, (int)places), MathUtils.roundToPlaces(vec3d2.z, (int)places));
    }

    public static double square(double d) {
        double input;
        return input * input;
    }

    public static float square(float f) {
        float input;
        return input * input;
    }

    /*
     * WARNING - void declaration
     */
    public static double distance(float f, float f2, float f3, float f4) {
        void y1;
        void y;
        void x1;
        float x;
        return Math.sqrt((x - x1) * (x - x1) + (y - y1) * (y - y1));
    }

    /*
     * WARNING - void declaration
     */
    public static Vec3d getInterpolatedRenderPos(Entity entity, float f) {
        void ticks;
        Entity entity2;
        return MathUtils.interpolateEntity(entity2, (float)ticks).subtract(Minecraft.getMinecraft().getRenderManager().renderPosX, Minecraft.getMinecraft().getRenderManager().renderPosY, Minecraft.getMinecraft().getRenderManager().renderPosZ);
    }

    /*
     * WARNING - void declaration
     */
    public static Vec3d interpolateEntity(Entity entity, float f) {
        void time;
        Entity entity2;
        return new Vec3d(entity2.lastTickPosX + (entity2.posX - entity2.lastTickPosX) * (double)time, entity2.lastTickPosY + (entity2.posY - entity2.lastTickPosY) * (double)time, entity2.lastTickPosZ + (entity2.posZ - entity2.lastTickPosZ) * (double)time);
    }

    public static double[] directionSpeed(double d) {
        double speed;
        Minecraft mc = Minecraft.getMinecraft();
        float forward = mc.player.movementInput.moveForward;
        float side = mc.player.movementInput.moveStrafe;
        float yaw = mc.player.prevRotationYaw + (mc.player.rotationYaw - mc.player.prevRotationYaw) * mc.getRenderPartialTicks();
        if (forward != Float.intBitsToFloat(Float.floatToIntBits(1.9741632E38f) ^ 0x7F148500)) {
            if (side > Float.intBitsToFloat(Float.floatToIntBits(3.3727889E38f) ^ 0x7F7DBD8D)) {
                yaw += (float)(forward > Float.intBitsToFloat(Float.floatToIntBits(2.556533E38f) ^ 0x7F405509) ? -45 : 45);
            } else if (side < Float.intBitsToFloat(Float.floatToIntBits(3.3104196E38f) ^ 0x7F790C5D)) {
                yaw += (float)(forward > Float.intBitsToFloat(Float.floatToIntBits(1.8714153E38f) ^ 0x7F0CCA26) ? 45 : -45);
            }
            side = Float.intBitsToFloat(Float.floatToIntBits(1.6154958E37f) ^ 0x7D42754F);
            if (forward > Float.intBitsToFloat(Float.floatToIntBits(2.6036586E38f) ^ 0x7F43E0A4)) {
                forward = Float.intBitsToFloat(Float.floatToIntBits(6.4496064f) ^ 0x7F4E632D);
            } else if (forward < Float.intBitsToFloat(Float.floatToIntBits(3.2306641E38f) ^ 0x7F730C54)) {
                forward = Float.intBitsToFloat(Float.floatToIntBits(-192.56981f) ^ 0x7CC091DF);
            }
        }
        double sin = Math.sin(Math.toRadians(yaw + Float.intBitsToFloat(Float.floatToIntBits(0.077464305f) ^ 0x7F2AA59B)));
        double cos = Math.cos(Math.toRadians(yaw + Float.intBitsToFloat(Float.floatToIntBits(0.026198639f) ^ 0x7E629E87)));
        double posX = (double)forward * speed * cos + (double)side * speed * sin;
        double posZ = (double)forward * speed * sin - (double)side * speed * cos;
        return new double[]{posX, posZ};
    }
}

