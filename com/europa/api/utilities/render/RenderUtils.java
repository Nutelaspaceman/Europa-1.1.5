/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelPlayer
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderGlobal
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.culling.Frustum
 *  net.minecraft.client.renderer.culling.ICamera
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package com.europa.api.utilities.render;

import com.europa.api.utilities.IMinecraft;
import com.europa.api.utilities.entity.EntityUtils;
import com.europa.api.utilities.render.RainbowUtils;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class RenderUtils
extends Tessellator
implements IMinecraft {
    public static ICamera camera = new Frustum();
    public static Frustum frustrum = new Frustum();
    public static AxisAlignedBB DEFAULT_AABB;
    public Map<Integer, Boolean> glCapMap = new HashMap<Integer, Boolean>();
    public static int deltaTime;
    public static RenderUtils INSTANCE;
    public static boolean depth;
    public static boolean texture;
    public static boolean clean;
    public static boolean bind;
    public static boolean override;

    public RenderUtils() {
        super(0x200000);
    }

    public static void prepare(int n) {
        int mode;
        RenderUtils.prepareGL();
        RenderUtils.begin(mode);
    }

    public static void prepareGL() {
        GL11.glBlendFunc((int)770, (int)771);
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.glLineWidth((float)Float.intBitsToFloat(Float.floatToIntBits(5.0675106f) ^ 0x7F22290C));
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask((boolean)false);
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.enableAlpha();
        GlStateManager.color((float)Float.intBitsToFloat(Float.floatToIntBits(11.925059f) ^ 0x7EBECD0B), (float)Float.intBitsToFloat(Float.floatToIntBits(18.2283f) ^ 0x7E11D38F), (float)Float.intBitsToFloat(Float.floatToIntBits(9.73656f) ^ 0x7E9BC8F3));
    }

    public static void begin(int n) {
        int mode;
        INSTANCE.getBuffer().begin(mode, DefaultVertexFormats.POSITION_COLOR);
    }

    public static void release() {
        RenderUtils.render();
        RenderUtils.releaseGL();
    }

    public static void render() {
        INSTANCE.draw();
    }

    public static void releaseGL() {
        GlStateManager.enableCull();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
        GlStateManager.color((float)Float.intBitsToFloat(Float.floatToIntBits(12.552789f) ^ 0x7EC8D839), (float)Float.intBitsToFloat(Float.floatToIntBits(7.122752f) ^ 0x7F63ED96), (float)Float.intBitsToFloat(Float.floatToIntBits(5.4278784f) ^ 0x7F2DB12E));
        GL11.glColor4f((float)Float.intBitsToFloat(Float.floatToIntBits(10.5715685f) ^ 0x7EA92525), (float)Float.intBitsToFloat(Float.floatToIntBits(4.9474883f) ^ 0x7F1E51D3), (float)Float.intBitsToFloat(Float.floatToIntBits(4.9044757f) ^ 0x7F1CF177), (float)Float.intBitsToFloat(Float.floatToIntBits(9.482457f) ^ 0x7E97B825));
    }

    /*
     * WARNING - void declaration
     */
    public static void drawOpenGradientBox(BlockPos blockPos, Color color, Color color2, double d) {
        for (EnumFacing face : EnumFacing.values()) {
            void height;
            void endColor;
            void startColor;
            BlockPos pos;
            if (face == EnumFacing.UP) continue;
            RenderUtils.drawGradientPlane(pos, face, (Color)startColor, (Color)endColor, (double)height);
        }
    }

    public static AxisAlignedBB interpolateAxis(AxisAlignedBB axisAlignedBB) {
        AxisAlignedBB bb;
        return new AxisAlignedBB(bb.minX - RenderUtils.mc.getRenderManager().viewerPosX, bb.minY - RenderUtils.mc.getRenderManager().viewerPosY, bb.minZ - RenderUtils.mc.getRenderManager().viewerPosZ, bb.maxX - RenderUtils.mc.getRenderManager().viewerPosX, bb.maxY - RenderUtils.mc.getRenderManager().viewerPosY, bb.maxZ - RenderUtils.mc.getRenderManager().viewerPosZ);
    }

    /*
     * WARNING - void declaration
     * Enabled aggressive block sorting
     */
    public static void drawGradientPlane(BlockPos blockPos, EnumFacing enumFacing, Color color, Color color2, boolean bl, boolean bl2) {
        void half;
        void top;
        void face;
        void endColor;
        void startColor;
        BlockPos pos;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder builder = tessellator.getBuffer();
        IBlockState iblockstate = RenderUtils.mc.world.getBlockState(pos);
        Vec3d interp = EntityUtils.interpolateEntity((Entity)RenderUtils.mc.player, mc.getRenderPartialTicks());
        AxisAlignedBB bb = iblockstate.getSelectedBoundingBox((World)RenderUtils.mc.world, pos).grow(Double.longBitsToDouble(Double.doubleToLongBits(7737.873906549376) ^ 0x7FDE5B925856F155L)).offset(-interp.x, -interp.y, -interp.z);
        float red = (float)startColor.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.011933815f) ^ 0x7F3C860C);
        float green = (float)startColor.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.014238123f) ^ 0x7F164704);
        float blue = (float)startColor.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.04742649f) ^ 0x7E3D4247);
        float alpha = (float)startColor.getAlpha() / Float.intBitsToFloat(Float.floatToIntBits(0.008362556f) ^ 0x7F76031A);
        float red1 = (float)endColor.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.10421128f) ^ 0x7EAA6CB9);
        float green1 = (float)endColor.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.08333851f) ^ 0x7ED5AD61);
        float blue1 = (float)endColor.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.0144527145f) ^ 0x7F13CB14);
        float alpha1 = (float)endColor.getAlpha() / Float.intBitsToFloat(Float.floatToIntBits(0.012371935f) ^ 0x7F35B3A8);
        double x1 = Double.longBitsToDouble(Double.doubleToLongBits(8.835131783660546E307) ^ 0x7FDF7440C3BC5D3DL);
        double y1 = Double.longBitsToDouble(Double.doubleToLongBits(1.118181430726592E308) ^ 0x7FE3E77F8F6B26DEL);
        double z1 = Double.longBitsToDouble(Double.doubleToLongBits(1.380140667303924E308) ^ 0x7FE8913BD76D7C97L);
        double x2 = Double.longBitsToDouble(Double.doubleToLongBits(4.752487534707832E307) ^ 0x7FD0EB5EEDAA85C9L);
        double y2 = Double.longBitsToDouble(Double.doubleToLongBits(9.887791724409538E306) ^ 0x7FAC294F41BA167FL);
        double z2 = Double.longBitsToDouble(Double.doubleToLongBits(5.35959611879942E307) ^ 0x7FD314AF0452AEC9L);
        if (face == EnumFacing.DOWN) {
            x1 = bb.minX;
            x2 = bb.maxX;
            y1 = bb.minY + (top != false ? Double.longBitsToDouble(Double.doubleToLongBits(3.143369194728737) ^ 0x7FE9259EBF94E906L) : Double.longBitsToDouble(Double.doubleToLongBits(1.7424901971094587E308) ^ 0x7FEF04716219751FL));
            y2 = bb.minY + (top != false ? Double.longBitsToDouble(Double.doubleToLongBits(25.313526608979853) ^ 0x7FD9504347A3FE87L) : Double.longBitsToDouble(Double.doubleToLongBits(8.360703780749126E307) ^ 0x7FDDC3DD2A7954CDL));
            z1 = bb.minZ;
            z2 = bb.maxZ;
        } else if (face == EnumFacing.UP) {
            x1 = bb.minX;
            x2 = bb.maxX;
            y1 = bb.maxY / (double)(half != false ? 2 : 1);
            y2 = bb.maxY / (double)(half != false ? 2 : 1);
            z1 = bb.minZ;
            z2 = bb.maxZ;
        } else if (face == EnumFacing.EAST) {
            x1 = bb.maxX;
            x2 = bb.maxX;
            y1 = bb.minY + (top != false ? Double.longBitsToDouble(Double.doubleToLongBits(18.522674392601733) ^ 0x7FD285CDFD2EAE5DL) : Double.longBitsToDouble(Double.doubleToLongBits(1.031120033212976E308) ^ 0x7FE25AC384422687L));
            y2 = bb.maxY / (double)(half != false ? 2 : 1);
            z1 = bb.minZ;
            z2 = bb.maxZ;
        } else if (face == EnumFacing.WEST) {
            x1 = bb.minX;
            x2 = bb.minX;
            y1 = bb.minY + (top != false ? Double.longBitsToDouble(Double.doubleToLongBits(3.6410682101818854) ^ 0x7FED20E85EA9E441L) : Double.longBitsToDouble(Double.doubleToLongBits(6.437474394910168E307) ^ 0x7FD6EB0D2975CFE9L));
            y2 = bb.maxY / (double)(half != false ? 2 : 1);
            z1 = bb.minZ;
            z2 = bb.maxZ;
        } else if (face == EnumFacing.SOUTH) {
            x1 = bb.minX;
            x2 = bb.maxX;
            y1 = bb.minY + (top != false ? Double.longBitsToDouble(Double.doubleToLongBits(15.296728066590147) ^ 0x7FCE97ECBDBB9EB7L) : Double.longBitsToDouble(Double.doubleToLongBits(9.653964997422201E307) ^ 0x7FE12F43C9CE0278L));
            y2 = bb.maxY / (double)(half != false ? 2 : 1);
            z1 = bb.maxZ;
            z2 = bb.maxZ;
        } else if (face == EnumFacing.NORTH) {
            x1 = bb.minX;
            x2 = bb.maxX;
            y1 = bb.minY + (top != false ? Double.longBitsToDouble(Double.doubleToLongBits(9.842466081175195) ^ 0x7FC3AF57B6D54603L) : Double.longBitsToDouble(Double.doubleToLongBits(1.5551384482214934E308) ^ 0x7FEBAEB0AB58F02FL));
            y2 = bb.maxY / (double)(half != false ? 2 : 1);
            z1 = bb.minZ;
            z2 = bb.minZ;
        }
        GlStateManager.pushMatrix();
        GlStateManager.disableDepth();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.depthMask((boolean)false);
        builder.begin(5, DefaultVertexFormats.POSITION_COLOR);
        if (face == EnumFacing.EAST || face == EnumFacing.WEST || face == EnumFacing.NORTH || face == EnumFacing.SOUTH) {
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
        } else if (face == EnumFacing.UP) {
            builder.pos(x1, y1, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
        } else if (face == EnumFacing.DOWN) {
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
        }
        tessellator.draw();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.popMatrix();
    }

    /*
     * WARNING - void declaration
     */
    public static void drawGradientPlane(BlockPos blockPos, EnumFacing enumFacing, Color color, Color color2, double d) {
        void face;
        void endColor;
        void startColor;
        void height;
        BlockPos pos;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder builder = tessellator.getBuffer();
        IBlockState iblockstate = RenderUtils.mc.world.getBlockState(pos);
        Vec3d interp = EntityUtils.interpolateEntity((Entity)RenderUtils.mc.player, mc.getRenderPartialTicks());
        AxisAlignedBB bb = iblockstate.getSelectedBoundingBox((World)RenderUtils.mc.world, pos).grow(Double.longBitsToDouble(Double.doubleToLongBits(3.0607151770573178) ^ 0x7F681E15DD1EA7FFL)).offset(-interp.x, -interp.y, -interp.z).expand(Double.longBitsToDouble(Double.doubleToLongBits(1.646911076243114E308) ^ 0x7FED50E4BCEAEB97L), (double)height, Double.longBitsToDouble(Double.doubleToLongBits(9.969131849133298E307) ^ 0x7FE1BEE28202EE6BL));
        float red = (float)startColor.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.090864085f) ^ 0x7EC516F3);
        float green = (float)startColor.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.058850195f) ^ 0x7E0E0CE7);
        float blue = (float)startColor.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.04067875f) ^ 0x7E599EC3);
        float alpha = (float)startColor.getAlpha() / Float.intBitsToFloat(Float.floatToIntBits(0.06464929f) ^ 0x7EFB66D9);
        float red1 = (float)endColor.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.015392774f) ^ 0x7F0331F9);
        float green1 = (float)endColor.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.011544588f) ^ 0x7F422583);
        float blue1 = (float)endColor.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(1.1073645f) ^ 0x7CF2BE1F);
        float alpha1 = (float)endColor.getAlpha() / Float.intBitsToFloat(Float.floatToIntBits(0.012541848f) ^ 0x7F327C53);
        double x1 = Double.longBitsToDouble(Double.doubleToLongBits(1.3225864938154327E308) ^ 0x7FE78AF6523D93C9L);
        double y1 = Double.longBitsToDouble(Double.doubleToLongBits(1.3775115287059444E308) ^ 0x7FE88540BE04EA82L);
        double z1 = Double.longBitsToDouble(Double.doubleToLongBits(6.46021304761502E307) ^ 0x7FD6FFC67387116DL);
        double x2 = Double.longBitsToDouble(Double.doubleToLongBits(1.2678712548475257E308) ^ 0x7FE691A0A4D499AEL);
        double y2 = Double.longBitsToDouble(Double.doubleToLongBits(1.6277632430317775E308) ^ 0x7FECF9A344900BB7L);
        double z2 = Double.longBitsToDouble(Double.doubleToLongBits(8.302590426886019E307) ^ 0x7FDD8EE6656C79C7L);
        if (face == EnumFacing.DOWN) {
            x1 = bb.minX;
            x2 = bb.maxX;
            y1 = bb.minY;
            y2 = bb.minY;
            z1 = bb.minZ;
            z2 = bb.maxZ;
        } else if (face == EnumFacing.UP) {
            x1 = bb.minX;
            x2 = bb.maxX;
            y1 = bb.maxY;
            y2 = bb.maxY;
            z1 = bb.minZ;
            z2 = bb.maxZ;
        } else if (face == EnumFacing.EAST) {
            x1 = bb.maxX;
            x2 = bb.maxX;
            y1 = bb.minY;
            y2 = bb.maxY;
            z1 = bb.minZ;
            z2 = bb.maxZ;
        } else if (face == EnumFacing.WEST) {
            x1 = bb.minX;
            x2 = bb.minX;
            y1 = bb.minY;
            y2 = bb.maxY;
            z1 = bb.minZ;
            z2 = bb.maxZ;
        } else if (face == EnumFacing.SOUTH) {
            x1 = bb.minX;
            x2 = bb.maxX;
            y1 = bb.minY;
            y2 = bb.maxY;
            z1 = bb.maxZ;
            z2 = bb.maxZ;
        } else if (face == EnumFacing.NORTH) {
            x1 = bb.minX;
            x2 = bb.maxX;
            y1 = bb.minY;
            y2 = bb.maxY;
            z1 = bb.minZ;
            z2 = bb.minZ;
        }
        GlStateManager.pushMatrix();
        GlStateManager.disableDepth();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.depthMask((boolean)false);
        builder.begin(5, DefaultVertexFormats.POSITION_COLOR);
        if (face == EnumFacing.EAST || face == EnumFacing.WEST || face == EnumFacing.NORTH || face == EnumFacing.SOUTH) {
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
        } else if (face == EnumFacing.UP) {
            builder.pos(x1, y1, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
        } else if (face == EnumFacing.DOWN) {
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
        }
        tessellator.draw();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.popMatrix();
    }

    /*
     * WARNING - void declaration
     */
    public static void glBillboard(float f, float f2, float f3) {
        void z;
        void y;
        float x;
        float scale = Float.intBitsToFloat(Float.floatToIntBits(43.059853f) ^ 0x7EF64945);
        GlStateManager.translate((double)((double)x - Minecraft.getMinecraft().getRenderManager().renderPosX), (double)((double)y - Minecraft.getMinecraft().getRenderManager().renderPosY), (double)((double)z - Minecraft.getMinecraft().getRenderManager().renderPosZ));
        GlStateManager.glNormal3f((float)Float.intBitsToFloat(Float.floatToIntBits(1.8430962E38f) ^ 0x7F0AA8BE), (float)Float.intBitsToFloat(Float.floatToIntBits(4.1650653f) ^ 0x7F054837), (float)Float.intBitsToFloat(Float.floatToIntBits(2.7096567E38f) ^ 0x7F4BDA17));
        GlStateManager.rotate((float)(-Minecraft.getMinecraft().player.rotationYaw), (float)Float.intBitsToFloat(Float.floatToIntBits(7.285461E36f) ^ 0x7CAF641F), (float)Float.intBitsToFloat(Float.floatToIntBits(7.6383615f) ^ 0x7F746D75), (float)Float.intBitsToFloat(Float.floatToIntBits(3.8722768E37f) ^ 0x7DE90DDF));
        GlStateManager.rotate((float)Minecraft.getMinecraft().player.rotationPitch, (float)(Minecraft.getMinecraft().gameSettings.thirdPersonView == 2 ? Float.intBitsToFloat(Float.floatToIntBits(-7.747731f) ^ 0x7F77ED6A) : Float.intBitsToFloat(Float.floatToIntBits(7.322496f) ^ 0x7F6A51E3)), (float)Float.intBitsToFloat(Float.floatToIntBits(8.078122E37f) ^ 0x7E731797), (float)Float.intBitsToFloat(Float.floatToIntBits(3.2507196E38f) ^ 0x7F748E95));
        GlStateManager.scale((float)(-scale), (float)(-scale), (float)scale);
    }

    /*
     * WARNING - void declaration
     */
    public static void glBillboardDistanceScaled(float f, float f2, float f3, EntityPlayer entityPlayer, float f4) {
        void scale;
        void player;
        void z;
        void y;
        float x;
        RenderUtils.glBillboard(x, (float)y, (float)z);
        int distance = (int)player.getDistance((double)x, (double)y, (double)z);
        float scaleDistance = (float)distance / Float.intBitsToFloat(Float.floatToIntBits(0.39821896f) ^ 0x7ECBE35B) / (Float.intBitsToFloat(Float.floatToIntBits(0.05389839f) ^ 0x7D5CC48F) + (Float.intBitsToFloat(Float.floatToIntBits(0.22974841f) ^ 0x7E6B432B) - scale));
        if (scaleDistance < Float.intBitsToFloat(Float.floatToIntBits(7.6779084f) ^ 0x7F75B16D)) {
            scaleDistance = Float.intBitsToFloat(Float.floatToIntBits(9.369836f) ^ 0x7E95EAD9);
        }
        GlStateManager.scale((float)scaleDistance, (float)scaleDistance, (float)scaleDistance);
    }

    /*
     * WARNING - void declaration
     */
    public static void drawBox(BlockPos blockPos, int n, int n2) {
        void sides;
        BlockPos blockPos2;
        void argb;
        int a = argb >>> 24 & 0xFF;
        int r = argb >>> 16 & 0xFF;
        int g = argb >>> 8 & 0xFF;
        int b = argb & 0xFF;
        RenderUtils.drawBox(blockPos2, r, g, b, a, (int)sides);
    }

    /*
     * WARNING - void declaration
     */
    public static void drawBox(BlockPos blockPos, int n, int n2, int n3, int n4, int n5) {
        void sides;
        void a;
        void b;
        void g;
        void r;
        BlockPos blockPos2;
        RenderUtils.drawBox(INSTANCE.getBuffer(), blockPos2.getX(), blockPos2.getY(), blockPos2.getZ(), Float.intBitsToFloat(Float.floatToIntBits(10.001433f) ^ 0x7EA005DF), Float.intBitsToFloat(Float.floatToIntBits(14.253013f) ^ 0x7EE40C57), Float.intBitsToFloat(Float.floatToIntBits(4.310877f) ^ 0x7F09F2B4), (int)r, (int)g, (int)b, (int)a, (int)sides);
    }

    /*
     * WARNING - void declaration
     */
    public static void drawBox(BufferBuilder bufferBuilder, float f, float f2, float f3, float f4, float f5, float f6, int n, int n2, int n3, int n4, int n5) {
        block5: {
            void h;
            void d;
            void a;
            void b;
            void g;
            void r;
            void z;
            void y;
            void w;
            void x;
            BufferBuilder buffer;
            void sides;
            if ((sides & 1) != 0) {
                buffer.pos((double)(x + w), (double)y, (double)z).color((int)r, (int)g, (int)b, (int)a).endVertex();
                buffer.pos((double)(x + w), (double)y, (double)(z + d)).color((int)r, (int)g, (int)b, (int)a).endVertex();
                buffer.pos((double)x, (double)y, (double)(z + d)).color((int)r, (int)g, (int)b, (int)a).endVertex();
                buffer.pos((double)x, (double)y, (double)z).color((int)r, (int)g, (int)b, (int)a).endVertex();
            }
            if ((sides & 2) != 0) {
                buffer.pos((double)(x + w), (double)(y + h), (double)z).color((int)r, (int)g, (int)b, (int)a).endVertex();
                buffer.pos((double)x, (double)(y + h), (double)z).color((int)r, (int)g, (int)b, (int)a).endVertex();
                buffer.pos((double)x, (double)(y + h), (double)(z + d)).color((int)r, (int)g, (int)b, (int)a).endVertex();
                buffer.pos((double)(x + w), (double)(y + h), (double)(z + d)).color((int)r, (int)g, (int)b, (int)a).endVertex();
            }
            if ((sides & 4) != 0) {
                buffer.pos((double)(x + w), (double)y, (double)z).color((int)r, (int)g, (int)b, (int)a).endVertex();
                buffer.pos((double)x, (double)y, (double)z).color((int)r, (int)g, (int)b, (int)a).endVertex();
                buffer.pos((double)x, (double)(y + h), (double)z).color((int)r, (int)g, (int)b, (int)a).endVertex();
                buffer.pos((double)(x + w), (double)(y + h), (double)z).color((int)r, (int)g, (int)b, (int)a).endVertex();
            }
            if ((sides & 8) != 0) {
                buffer.pos((double)x, (double)y, (double)(z + d)).color((int)r, (int)g, (int)b, (int)a).endVertex();
                buffer.pos((double)(x + w), (double)y, (double)(z + d)).color((int)r, (int)g, (int)b, (int)a).endVertex();
                buffer.pos((double)(x + w), (double)(y + h), (double)(z + d)).color((int)r, (int)g, (int)b, (int)a).endVertex();
                buffer.pos((double)x, (double)(y + h), (double)(z + d)).color((int)r, (int)g, (int)b, (int)a).endVertex();
            }
            if ((sides & 0x10) != 0) {
                buffer.pos((double)x, (double)y, (double)z).color((int)r, (int)g, (int)b, (int)a).endVertex();
                buffer.pos((double)x, (double)y, (double)(z + d)).color((int)r, (int)g, (int)b, (int)a).endVertex();
                buffer.pos((double)x, (double)(y + h), (double)(z + d)).color((int)r, (int)g, (int)b, (int)a).endVertex();
                buffer.pos((double)x, (double)(y + h), (double)z).color((int)r, (int)g, (int)b, (int)a).endVertex();
            }
            if ((sides & 0x20) == 0) break block5;
            buffer.pos((double)(x + w), (double)y, (double)(z + d)).color((int)r, (int)g, (int)b, (int)a).endVertex();
            buffer.pos((double)(x + w), (double)y, (double)z).color((int)r, (int)g, (int)b, (int)a).endVertex();
            buffer.pos((double)(x + w), (double)(y + h), (double)z).color((int)r, (int)g, (int)b, (int)a).endVertex();
            buffer.pos((double)(x + w), (double)(y + h), (double)(z + d)).color((int)r, (int)g, (int)b, (int)a).endVertex();
        }
    }

    /*
     * WARNING - void declaration
     */
    public static void drawBoundingBoxBlockPos(BlockPos blockPos, float f, int n, int n2, int n3, int n4) {
        void alpha;
        void b;
        void g;
        void r;
        BlockPos bp;
        void width;
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask((boolean)false);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glLineWidth((float)width);
        Minecraft mc = Minecraft.getMinecraft();
        double x = (double)bp.getX() - mc.getRenderManager().viewerPosX;
        double y = (double)bp.getY() - mc.getRenderManager().viewerPosY;
        double z = (double)bp.getZ() - mc.getRenderManager().viewerPosZ;
        AxisAlignedBB bb = new AxisAlignedBB(x, y, z, x + Double.longBitsToDouble(Double.doubleToLongBits(5.904661419426285) ^ 0x7FE79E5F90298E2AL), y + Double.longBitsToDouble(Double.doubleToLongBits(15.500682791548558) ^ 0x7FDF00597EAEBDF9L), z + Double.longBitsToDouble(Double.doubleToLongBits(384.3294781497497) ^ 0x7F8805458AE15EBFL));
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color((int)r, (int)g, (int)b, (int)alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color((int)r, (int)g, (int)b, (int)alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color((int)r, (int)g, (int)b, (int)alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color((int)r, (int)g, (int)b, (int)alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color((int)r, (int)g, (int)b, (int)alpha).endVertex();
        tessellator.draw();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color((int)r, (int)g, (int)b, (int)alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color((int)r, (int)g, (int)b, (int)alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color((int)r, (int)g, (int)b, (int)alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color((int)r, (int)g, (int)b, (int)alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color((int)r, (int)g, (int)b, (int)alpha).endVertex();
        tessellator.draw();
        bufferbuilder.begin(1, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color((int)r, (int)g, (int)b, (int)alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color((int)r, (int)g, (int)b, (int)alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color((int)r, (int)g, (int)b, (int)alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color((int)r, (int)g, (int)b, (int)alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color((int)r, (int)g, (int)b, (int)alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color((int)r, (int)g, (int)b, (int)alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color((int)r, (int)g, (int)b, (int)alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color((int)r, (int)g, (int)b, (int)alpha).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    /*
     * WARNING - void declaration
     */
    public static void drawGradientBlockOutline(BlockPos blockPos, Color color, Color color2, float f) {
        void linewidth;
        void endColor;
        void startColor;
        BlockPos pos;
        IBlockState iblockstate = RenderUtils.mc.world.getBlockState(pos);
        Vec3d interp = EntityUtils.interpolateEntity((Entity)RenderUtils.mc.player, mc.getRenderPartialTicks());
        RenderUtils.drawGradientBlockOutline(iblockstate.getSelectedBoundingBox((World)RenderUtils.mc.world, pos).grow(Double.longBitsToDouble(Double.doubleToLongBits(5140.135716816253) ^ 0x7FD4766F5E565753L)).offset(-interp.x, -interp.y, -interp.z), (Color)startColor, (Color)endColor, (float)linewidth);
    }

    /*
     * WARNING - void declaration
     */
    public static void drawGradientBlockOutline(AxisAlignedBB axisAlignedBB, Color color, Color color2, float f) {
        AxisAlignedBB bb;
        void linewidth;
        void endColor;
        void startColor;
        float red = (float)startColor.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.009788291f) ^ 0x7F5F5F11);
        float green = (float)startColor.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.23705815f) ^ 0x7D0DBF5F);
        float blue = (float)startColor.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.29198143f) ^ 0x7DEA7E97);
        float alpha = (float)startColor.getAlpha() / Float.intBitsToFloat(Float.floatToIntBits(0.012616771f) ^ 0x7F31B693);
        float red1 = (float)endColor.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.012736047f) ^ 0x7F2FAADA);
        float green1 = (float)endColor.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.010022703f) ^ 0x7F5B3643);
        float blue1 = (float)endColor.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.01259957f) ^ 0x7F316E6D);
        float alpha1 = (float)endColor.getAlpha() / Float.intBitsToFloat(Float.floatToIntBits(0.011501952f) ^ 0x7F4372AF);
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask((boolean)false);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glLineWidth((float)linewidth);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red1, green1, blue1, alpha1).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red1, green1, blue1, alpha1).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    /*
     * WARNING - void declaration
     */
    public static void drawBoxESP(BlockPos blockPos, Color color, int n) {
        void boxAlpha;
        void color2;
        BlockPos pos;
        RenderUtils.drawBox(pos, new Color(color2.getRed(), color2.getGreen(), color2.getBlue(), (int)boxAlpha));
    }

    /*
     * WARNING - void declaration
     */
    public static void drawBox(BlockPos blockPos, Color color) {
        block0: {
            void color2;
            BlockPos pos;
            AxisAlignedBB bb = new AxisAlignedBB((double)pos.getX() - RenderUtils.mc.getRenderManager().viewerPosX, (double)pos.getY() - RenderUtils.mc.getRenderManager().viewerPosY, (double)pos.getZ() - RenderUtils.mc.getRenderManager().viewerPosZ, (double)(pos.getX() + 1) - RenderUtils.mc.getRenderManager().viewerPosX, (double)(pos.getY() + 1) - RenderUtils.mc.getRenderManager().viewerPosY, (double)(pos.getZ() + 1) - RenderUtils.mc.getRenderManager().viewerPosZ);
            camera.setPosition(Objects.requireNonNull(RenderUtils.mc.getRenderViewEntity()).posX, RenderUtils.mc.getRenderViewEntity().posY, RenderUtils.mc.getRenderViewEntity().posZ);
            if (!camera.isBoundingBoxInFrustum(new AxisAlignedBB(bb.minX + RenderUtils.mc.getRenderManager().viewerPosX, bb.minY + RenderUtils.mc.getRenderManager().viewerPosY, bb.minZ + RenderUtils.mc.getRenderManager().viewerPosZ, bb.maxX + RenderUtils.mc.getRenderManager().viewerPosX, bb.maxY + RenderUtils.mc.getRenderManager().viewerPosY, bb.maxZ + RenderUtils.mc.getRenderManager().viewerPosZ))) break block0;
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask((boolean)false);
            GL11.glEnable((int)2848);
            GL11.glHint((int)3154, (int)4354);
            RenderGlobal.renderFilledBox((AxisAlignedBB)bb, (float)((float)color2.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.010107363f) ^ 0x7F5A995A)), (float)((float)color2.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.011656278f) ^ 0x7F41F9F9)), (float)((float)color2.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.015351498f) ^ 0x7F0484D9)), (float)((float)color2.getAlpha() / Float.intBitsToFloat(Float.floatToIntBits(0.012585007f) ^ 0x7F313158)));
            GL11.glDisable((int)2848);
            GlStateManager.depthMask((boolean)true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }

    /*
     * WARNING - void declaration
     */
    public static void drawBoxESP(BlockPos blockPos, Color color, float f, boolean bl, boolean bl2, int n, boolean bl3, double d, boolean bl4) {
        block1: {
            void air;
            void lineWidth;
            void outline;
            void height;
            void color2;
            BlockPos pos;
            void box;
            if (box != false) {
                void gradientBox;
                void boxAlpha;
                RenderUtils.drawBox(pos, new Color(color2.getRed(), color2.getGreen(), color2.getBlue(), (int)boxAlpha), (double)height, (boolean)gradientBox, (int)boxAlpha);
            }
            if (outline == false) break block1;
            RenderUtils.drawBlockOutline(pos, (Color)color2, (float)lineWidth, (boolean)air, (double)height);
        }
    }

    /*
     * WARNING - void declaration
     */
    public static void drawBlockOutline(BlockPos blockPos, Color color, float f, boolean bl, double d) {
        block2: {
            BlockPos pos;
            block1: {
                void air;
                IBlockState iblockstate = RenderUtils.mc.world.getBlockState(pos);
                if (air != false) break block1;
                if (iblockstate.getMaterial() == Material.AIR) break block2;
            }
            if (RenderUtils.mc.world.getWorldBorder().contains(pos)) {
                void linewidth;
                void color2;
                void height;
                AxisAlignedBB blockAxis = new AxisAlignedBB((double)pos.getX() - RenderUtils.mc.getRenderManager().viewerPosX, (double)pos.getY() - RenderUtils.mc.getRenderManager().viewerPosY, (double)pos.getZ() - RenderUtils.mc.getRenderManager().viewerPosZ, (double)(pos.getX() + 1) - RenderUtils.mc.getRenderManager().viewerPosX, (double)(pos.getY() + 1) - RenderUtils.mc.getRenderManager().viewerPosY + height, (double)(pos.getZ() + 1) - RenderUtils.mc.getRenderManager().viewerPosZ);
                RenderUtils.drawBlockOutline(blockAxis.grow(Double.longBitsToDouble(Double.doubleToLongBits(3177.4888695024906) ^ 0x7FC8B0B7AD1A7A6BL)), (Color)color2, (float)linewidth);
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    public static void drawBlockOutline(AxisAlignedBB axisAlignedBB, Color color, float f) {
        AxisAlignedBB bb;
        void linewidth;
        void color2;
        float red = (float)color2.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.010800879f) ^ 0x7F4FF62C);
        float green = (float)color2.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.013595752f) ^ 0x7F21C0B8);
        float blue = (float)color2.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.014829914f) ^ 0x7F0DF92B);
        float alpha = Float.intBitsToFloat(Float.floatToIntBits(5.635761f) ^ 0x7F345827);
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask((boolean)false);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glLineWidth((float)linewidth);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    /*
     * WARNING - void declaration
     */
    public static void drawColoredFlatBox(BlockPos blockPos, float f, boolean bl, double d) {
        block2: {
            BlockPos pos;
            block1: {
                void air;
                IBlockState iblockstate = RenderUtils.mc.world.getBlockState(pos);
                if (air != false) break block1;
                if (iblockstate.getMaterial() == Material.AIR) break block2;
            }
            if (RenderUtils.mc.world.getWorldBorder().contains(pos)) {
                void linewidth;
                void height;
                AxisAlignedBB blockAxis = new AxisAlignedBB((double)pos.getX() - RenderUtils.mc.getRenderManager().viewerPosX, (double)pos.getY() - RenderUtils.mc.getRenderManager().viewerPosY, (double)pos.getZ() - RenderUtils.mc.getRenderManager().viewerPosZ, (double)(pos.getX() + 1) - RenderUtils.mc.getRenderManager().viewerPosX, (double)(pos.getY() + 1) - RenderUtils.mc.getRenderManager().viewerPosY + height, (double)(pos.getZ() + 1) - RenderUtils.mc.getRenderManager().viewerPosZ);
                RenderUtils.drawColoredFlatBox(blockAxis.grow(Double.longBitsToDouble(Double.doubleToLongBits(5482.148565022259) ^ 0x7FD5086BE85B77EFL)), (float)linewidth);
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    public static void drawColoredFlatBox(AxisAlignedBB axisAlignedBB, float f) {
        AxisAlignedBB bb;
        void linewidth;
        float alpha = Float.intBitsToFloat(Float.floatToIntBits(13.809779f) ^ 0x7EDCF4DB);
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask((boolean)false);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glLineWidth((float)linewidth);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(Float.intBitsToFloat(Float.floatToIntBits(5.1694365f) ^ 0x7F256C06), Float.intBitsToFloat(Float.floatToIntBits(2.478717E38f) ^ 0x7F3A7A5A), Float.intBitsToFloat(Float.floatToIntBits(1.9199145E37f) ^ 0x7D6719EF), alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(Float.intBitsToFloat(Float.floatToIntBits(1.5403378E38f) ^ 0x7EE7C3A7), Float.intBitsToFloat(Float.floatToIntBits(13.219632f) ^ 0x7ED3839D), Float.intBitsToFloat(Float.floatToIntBits(2.724206E38f) ^ 0x7F4CF24D), alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(Float.intBitsToFloat(Float.floatToIntBits(2.3238642E38f) ^ 0x7F2ED3FF), Float.intBitsToFloat(Float.floatToIntBits(2.9685533E38f) ^ 0x7F5F5442), Float.intBitsToFloat(Float.floatToIntBits(4.9496536f) ^ 0x7F1E6390), alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(Float.intBitsToFloat(Float.floatToIntBits(7.2860913f) ^ 0x7F6927A9), Float.intBitsToFloat(Float.floatToIntBits(78.59313f) ^ 0x7D1D2FAF), Float.intBitsToFloat(Float.floatToIntBits(4.597998f) ^ 0x7F1322CD), alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(Float.intBitsToFloat(Float.floatToIntBits(4.738372f) ^ 0x7F17A0BE), Float.intBitsToFloat(Float.floatToIntBits(2.5195168E38f) ^ 0x7F3D8C21), Float.intBitsToFloat(Float.floatToIntBits(2.272865E38f) ^ 0x7F2AFDCA), alpha).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    /*
     * WARNING - void declaration
     */
    public static void drawBox(BlockPos blockPos, Color color, double d, boolean bl, int n) {
        block1: {
            void height;
            BlockPos pos;
            void color2;
            void gradient;
            if (gradient != false) {
                void alpha;
                Color endColor = new Color(color2.getRed(), color2.getGreen(), color2.getBlue(), (int)alpha);
                RenderUtils.drawOpenGradientBox(pos, (Color)color2, endColor, (double)height);
                return;
            }
            AxisAlignedBB bb = new AxisAlignedBB((double)pos.getX() - RenderUtils.mc.getRenderManager().viewerPosX, (double)pos.getY() - RenderUtils.mc.getRenderManager().viewerPosY, (double)pos.getZ() - RenderUtils.mc.getRenderManager().viewerPosZ, (double)(pos.getX() + 1) - RenderUtils.mc.getRenderManager().viewerPosX, (double)(pos.getY() + 1) - RenderUtils.mc.getRenderManager().viewerPosY + height, (double)(pos.getZ() + 1) - RenderUtils.mc.getRenderManager().viewerPosZ);
            camera.setPosition(Objects.requireNonNull(RenderUtils.mc.getRenderViewEntity()).posX, RenderUtils.mc.getRenderViewEntity().posY, RenderUtils.mc.getRenderViewEntity().posZ);
            if (!camera.isBoundingBoxInFrustum(new AxisAlignedBB(bb.minX + RenderUtils.mc.getRenderManager().viewerPosX, bb.minY + RenderUtils.mc.getRenderManager().viewerPosY, bb.minZ + RenderUtils.mc.getRenderManager().viewerPosZ, bb.maxX + RenderUtils.mc.getRenderManager().viewerPosX, bb.maxY + RenderUtils.mc.getRenderManager().viewerPosY, bb.maxZ + RenderUtils.mc.getRenderManager().viewerPosZ))) break block1;
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask((boolean)false);
            GL11.glEnable((int)2848);
            GL11.glHint((int)3154, (int)4354);
            RenderGlobal.renderFilledBox((AxisAlignedBB)bb, (float)((float)color2.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.079023026f) ^ 0x7EDED6D3)), (float)((float)color2.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.120759256f) ^ 0x7E8850A1)), (float)((float)color2.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.091729276f) ^ 0x7EC4DC8F)), (float)((float)color2.getAlpha() / Float.intBitsToFloat(Float.floatToIntBits(0.013379525f) ^ 0x7F2435CC)));
            GL11.glDisable((int)2848);
            GlStateManager.depthMask((boolean)true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }

    /*
     * WARNING - void declaration
     */
    public static void renderCrosses(BlockPos blockPos, Color color, float f) {
        block0: {
            void color2;
            void lineWidth;
            BlockPos pos;
            AxisAlignedBB bb = new AxisAlignedBB((double)pos.getX() - RenderUtils.mc.getRenderManager().viewerPosX, (double)pos.getY() - RenderUtils.mc.getRenderManager().viewerPosY, (double)pos.getZ() - RenderUtils.mc.getRenderManager().viewerPosZ, (double)(pos.getX() + 1) - RenderUtils.mc.getRenderManager().viewerPosX, (double)(pos.getY() + 1) - RenderUtils.mc.getRenderManager().viewerPosY, (double)(pos.getZ() + 1) - RenderUtils.mc.getRenderManager().viewerPosZ);
            camera.setPosition(Objects.requireNonNull(RenderUtils.mc.getRenderViewEntity()).posX, RenderUtils.mc.getRenderViewEntity().posY, RenderUtils.mc.getRenderViewEntity().posZ);
            if (!camera.isBoundingBoxInFrustum(new AxisAlignedBB(pos))) break block0;
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
            GlStateManager.disableTexture2D();
            GlStateManager.depthMask((boolean)false);
            GL11.glEnable((int)2848);
            GL11.glHint((int)3154, (int)4354);
            GL11.glLineWidth((float)lineWidth);
            RenderUtils.renderCrosses(bb, (Color)color2);
            GL11.glDisable((int)2848);
            GlStateManager.depthMask((boolean)true);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }

    /*
     * WARNING - void declaration
     */
    public static void renderCrosses(AxisAlignedBB axisAlignedBB, Color color) {
        AxisAlignedBB bb;
        void color2;
        int hex = color2.getRGB();
        float red = (float)(hex >> 16 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.010141646f) ^ 0x7F592925);
        float green = (float)(hex >> 8 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.09402426f) ^ 0x7EBF8FCB);
        float blue = (float)(hex & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.01439369f) ^ 0x7F14D383);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(1, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, Float.intBitsToFloat(Float.floatToIntBits(6.2025056f) ^ 0x7F467AED)).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, Float.intBitsToFloat(Float.floatToIntBits(13.170365f) ^ 0x7ED2B9D1)).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, Float.intBitsToFloat(Float.floatToIntBits(12.665132f) ^ 0x7ECAA461)).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, Float.intBitsToFloat(Float.floatToIntBits(6.132548f) ^ 0x7F443DD5)).endVertex();
        tessellator.draw();
    }

    /*
     * WARNING - void declaration
     */
    public static void drawOutlineLine(double d, double d2, double d3, double d4, float f, int n) {
        void color;
        void bottom;
        void top;
        double right;
        double left;
        void width;
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask((boolean)false);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glLineWidth((float)width);
        if (left < right) {
            double i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            void j = top;
            top = bottom;
            bottom = j;
        }
        float a = (float)(color >> 24 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.0137258265f) ^ 0x7F1FE24A);
        float r = (float)(color >> 16 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.01309684f) ^ 0x7F299421);
        float g = (float)(color >> 8 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.010762423f) ^ 0x7F4F54E0);
        float b = (float)(color & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.014254631f) ^ 0x7F168C41);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(left, (double)bottom, Double.longBitsToDouble(Double.doubleToLongBits(1.218645285212023E308) ^ 0x7FE5B14EA2BC5296L)).color(r, g, b, a).endVertex();
        bufferbuilder.pos(right, (double)bottom, Double.longBitsToDouble(Double.doubleToLongBits(1.3868782241790337E308) ^ 0x7FE8AFEFBCC64C92L)).color(r, g, b, a).endVertex();
        bufferbuilder.pos(right, (double)top, Double.longBitsToDouble(Double.doubleToLongBits(1.7327740273664738E308) ^ 0x7FEED82AB2D3399EL)).color(r, g, b, a).endVertex();
        bufferbuilder.pos(left, (double)top, Double.longBitsToDouble(Double.doubleToLongBits(4.724280987430946E307) ^ 0x7FD0D1A9E46D0C4DL)).color(r, g, b, a).endVertex();
        bufferbuilder.pos(left, (double)bottom, Double.longBitsToDouble(Double.doubleToLongBits(1.6226425550407737E308) ^ 0x7FECE24D93FC492CL)).color(r, g, b, a).endVertex();
        tessellator.draw();
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    /*
     * WARNING - void declaration
     */
    public static void drawRectrgb(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        void y;
        void w;
        void a;
        void b;
        void g;
        void r;
        void h;
        float x;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos((double)x, (double)h, Double.longBitsToDouble(Double.doubleToLongBits(9.873126907619453E307) ^ 0x7FE19322C499921BL)).color((float)(r / Float.intBitsToFloat(Float.floatToIntBits(0.008758856f) ^ 0x7F70814E)), (float)(g / Float.intBitsToFloat(Float.floatToIntBits(0.011129295f) ^ 0x7F4957A6)), (float)(b / Float.intBitsToFloat(Float.floatToIntBits(0.08990056f) ^ 0x7EC71DC9)), (float)(a / Float.intBitsToFloat(Float.floatToIntBits(0.118046276f) ^ 0x7E8EC23F))).endVertex();
        bufferbuilder.pos((double)w, (double)h, Double.longBitsToDouble(Double.doubleToLongBits(1.6387745625643133E308) ^ 0x7FED2BD0D903AB8AL)).color((float)(r / Float.intBitsToFloat(Float.floatToIntBits(0.100699f) ^ 0x7EB13B47)), (float)(g / Float.intBitsToFloat(Float.floatToIntBits(0.01207173f) ^ 0x7F3AC881)), (float)(b / Float.intBitsToFloat(Float.floatToIntBits(0.080900006f) ^ 0x7EDAAEE7)), (float)(a / Float.intBitsToFloat(Float.floatToIntBits(0.05701689f) ^ 0x7E168A8B))).endVertex();
        bufferbuilder.pos((double)w, (double)y, Double.longBitsToDouble(Double.doubleToLongBits(4.354100540066609E306) ^ 0x7F98CD41733C5BDFL)).color((float)(r / Float.intBitsToFloat(Float.floatToIntBits(0.013578997f) ^ 0x7F217A71)), (float)(g / Float.intBitsToFloat(Float.floatToIntBits(0.04730652f) ^ 0x7E3EC47B)), (float)(b / Float.intBitsToFloat(Float.floatToIntBits(0.010899194f) ^ 0x7F4D9288)), (float)(a / Float.intBitsToFloat(Float.floatToIntBits(0.0918661f) ^ 0x7EC3244B))).endVertex();
        bufferbuilder.pos((double)x, (double)y, Double.longBitsToDouble(Double.doubleToLongBits(2.8165720646742964E307) ^ 0x7FC40DFE1E51331BL)).color((float)(r / Float.intBitsToFloat(Float.floatToIntBits(0.035969656f) ^ 0x7E6C54EB)), (float)(g / Float.intBitsToFloat(Float.floatToIntBits(0.012732537f) ^ 0x7F2F9C22)), (float)(b / Float.intBitsToFloat(Float.floatToIntBits(0.110038914f) ^ 0x7E9E5C15)), (float)(a / Float.intBitsToFloat(Float.floatToIntBits(0.013974584f) ^ 0x7F1BF5A7))).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    /*
     * WARNING - void declaration
     */
    public static void drawRect(float f, float f2, float f3, float f4, int n) {
        void y;
        void width;
        void height;
        float x;
        void rgb;
        Color color = new Color((int)rgb);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        bufferBuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferBuilder.pos((double)x, (double)height, Double.longBitsToDouble(Double.doubleToLongBits(1.7440275154218355E307) ^ 0x7FB8D5F56E0824BFL)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos((double)width, (double)height, Double.longBitsToDouble(Double.doubleToLongBits(8.970041210315517E307) ^ 0x7FDFEF35478E5423L)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos((double)width, (double)y, Double.longBitsToDouble(Double.doubleToLongBits(1.1109098278862572E308) ^ 0x7FE3C65CA841DB5AL)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        bufferBuilder.pos((double)x, (double)y, Double.longBitsToDouble(Double.doubleToLongBits(1.6778616876832634E308) ^ 0x7FEDDDEF166601BDL)).color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    /*
     * WARNING - void declaration
     */
    public static void drawOutline(float f, float f2, float f3, float f4, float f5, int n) {
        void height;
        void width;
        void color;
        void y;
        void lineWidth;
        float x;
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3553);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glHint((int)3155, (int)4354);
        RenderUtils.drawRect(x + lineWidth, (float)y, x - lineWidth, (float)(y + lineWidth), (int)color);
        RenderUtils.drawRect(x + lineWidth, (float)y, (float)(width - lineWidth), (float)(y + lineWidth), (int)color);
        RenderUtils.drawRect(x, (float)y, x + lineWidth, (float)height, (int)color);
        RenderUtils.drawRect((float)(width - lineWidth), (float)y, (float)width, (float)height, (int)color);
        RenderUtils.drawRect(x + lineWidth, (float)(height - lineWidth), (float)(width - lineWidth), (float)height, (int)color);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2848);
        GL11.glHint((int)3154, (int)4352);
        GL11.glHint((int)3155, (int)4352);
    }

    /*
     * WARNING - void declaration
     */
    public static void drawRecta(float f, float f2, float f3, float f4, int n) {
        void color;
        float lvt_5_2_;
        void h;
        void y;
        void w;
        float x;
        float p_drawRect_2_ = x + w;
        float p_drawRect_3_ = y + h;
        if (x < p_drawRect_2_) {
            lvt_5_2_ = x;
            x = p_drawRect_2_;
            p_drawRect_2_ = lvt_5_2_;
        }
        if (y < p_drawRect_3_) {
            lvt_5_2_ = y;
            y = p_drawRect_3_;
            p_drawRect_3_ = lvt_5_2_;
        }
        float lvt_5_3_ = (float)(color >> 24 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.057955842f) ^ 0x7E12631B);
        float lvt_6_1_ = (float)(color >> 16 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.070744775f) ^ 0x7EEFE2A3);
        float lvt_7_1_ = (float)(color >> 8 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.08584332f) ^ 0x7ED0CE9F);
        float lvt_8_1_ = (float)(color & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.6575088f) ^ 0x7C57527F);
        Tessellator lvt_9_1_ = Tessellator.getInstance();
        BufferBuilder lvt_10_1_ = lvt_9_1_.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.color((float)lvt_6_1_, (float)lvt_7_1_, (float)lvt_8_1_, (float)lvt_5_3_);
        lvt_10_1_.begin(7, DefaultVertexFormats.POSITION);
        lvt_10_1_.pos((double)x, (double)p_drawRect_3_, Double.longBitsToDouble(Double.doubleToLongBits(1.1570676508599334E307) ^ 0x7FB07A292487A177L)).endVertex();
        lvt_10_1_.pos((double)p_drawRect_2_, (double)p_drawRect_3_, Double.longBitsToDouble(Double.doubleToLongBits(1.36127169834824E308) ^ 0x7FE83B3FB04F2CFDL)).endVertex();
        lvt_10_1_.pos((double)p_drawRect_2_, (double)y, Double.longBitsToDouble(Double.doubleToLongBits(1.5915725433131406E308) ^ 0x7FEC54B7F0485181L)).endVertex();
        lvt_10_1_.pos((double)x, (double)y, Double.longBitsToDouble(Double.doubleToLongBits(1.3041426104764812E308) ^ 0x7FE736EA108B0F77L)).endVertex();
        lvt_9_1_.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    /*
     * WARNING - void declaration
     */
    public static void drawDoubleRect(double d, double d2, double d3, double d4, int n) {
        void color;
        double bottom;
        void top;
        double j;
        double right;
        double left;
        if (left < right) {
            j = left;
            left = right;
            right = j;
        }
        if (top < bottom) {
            j = top;
            top = bottom;
            bottom = j;
        }
        float f3 = (float)(color >> 24 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.008837141f) ^ 0x7F6FC9A8);
        float f = (float)(color >> 16 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.24967287f) ^ 0x7D00AA3F);
        float f1 = (float)(color >> 8 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.08944767f) ^ 0x7EC83057);
        float f2 = (float)(color & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.063480444f) ^ 0x7EFD0209);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.color((float)f, (float)f1, (float)f2, (float)f3);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferbuilder.pos(left, bottom, Double.longBitsToDouble(Double.doubleToLongBits(5.792164346573969E307) ^ 0x7FD49EEC10737D75L)).endVertex();
        bufferbuilder.pos(right, bottom, Double.longBitsToDouble(Double.doubleToLongBits(1.3053260674455657E308) ^ 0x7FE73C4EA94A3489L)).endVertex();
        bufferbuilder.pos(right, (double)top, Double.longBitsToDouble(Double.doubleToLongBits(9.192985021720132E307) ^ 0x7FE05D32CEEB3EB4L)).endVertex();
        bufferbuilder.pos(left, (double)top, Double.longBitsToDouble(Double.doubleToLongBits(1.5975435153920324E308) ^ 0x7FEC6FED8D67B87BL)).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawSelectionBoundingBox(AxisAlignedBB axisAlignedBB) {
        AxisAlignedBB boundingBox;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(3, DefaultVertexFormats.POSITION);
        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
        tessellator.draw();
        vertexbuffer.begin(3, DefaultVertexFormats.POSITION);
        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
        tessellator.draw();
        vertexbuffer.begin(1, DefaultVertexFormats.POSITION);
        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.minY, boundingBox.maxZ).endVertex();
        vertexbuffer.pos(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ).endVertex();
        tessellator.draw();
    }

    /*
     * WARNING - void declaration
     */
    public static void drawCircle(float f, float f2, float f3, int n) {
        void color;
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.shadeModel((int)7425);
        GL11.glColor4d((double)RenderUtils.getRedFromHex((int)color), (double)RenderUtils.getGreenFromHex((int)color), (double)RenderUtils.getBlueFromHex((int)color), (double)RenderUtils.getAlphaFromHex((int)color));
        GL11.glBegin((int)9);
        for (int i = 0; i <= 360; ++i) {
            void y;
            void radius;
            float x;
            GL11.glVertex2d((double)(x + MathHelper.sin((float)((float)i * Float.intBitsToFloat(Float.floatToIntBits(0.30120212f) ^ 0x7ED338F1) / Float.intBitsToFloat(Float.floatToIntBits(0.07748035f) ^ 0x7EAAAE05))) * radius), (double)((double)(y + MathHelper.cos((float)((float)i * Float.intBitsToFloat(Float.floatToIntBits(0.6391426f) ^ 0x7F6A9102) / Float.intBitsToFloat(Float.floatToIntBits(0.011713186f) ^ 0x7F0BE8AA))) * radius)));
        }
        GL11.glColor4d((double)Double.longBitsToDouble(Double.doubleToLongBits(53.67714277800314) ^ 0x7FBAD6AC9D531F7FL), (double)Double.longBitsToDouble(Double.doubleToLongBits(501.6963415926147) ^ 0x7F8F5B243714F1FFL), (double)Double.longBitsToDouble(Double.doubleToLongBits(6.706577014744621) ^ 0x7FEAD388ECC9BBDCL), (double)Double.longBitsToDouble(Double.doubleToLongBits(14.139351235344405) ^ 0x7FDC47590B8CEC3FL));
        GL11.glEnd();
        GlStateManager.shadeModel((int)7424);
        GL11.glDisable((int)2848);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }

    public static double getAlphaFromHex(int n) {
        int color;
        return (float)(color >> 24 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.008373946f) ^ 0x7F7632E0);
    }

    public static double getRedFromHex(int n) {
        int color;
        return (float)(color >> 16 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.032228123f) ^ 0x7E7B01A3);
    }

    public static double getGreenFromHex(int n) {
        int color;
        return (float)(color >> 8 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.0140558975f) ^ 0x7F194AB5);
    }

    public static double getBlueFromHex(int n) {
        int color;
        return (float)(color & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.009096651f) ^ 0x7F6A0A1F);
    }

    /*
     * WARNING - void declaration
     */
    public static void drawFilledBox(AxisAlignedBB axisAlignedBB, int n) {
        AxisAlignedBB bb;
        void color;
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask((boolean)false);
        float alpha = (float)(color >> 24 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.0121679185f) ^ 0x7F385BF3);
        float red = (float)(color >> 16 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.009070697f) ^ 0x7F6B9D43);
        float green = (float)(color >> 8 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.013924689f) ^ 0x7F1B2461);
        float blue = (float)(color & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.067761265f) ^ 0x7EF5C66B);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color(red, green, blue, alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color(red, green, blue, alpha).endVertex();
        tessellator.draw();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void drawSolidBox() {
        RenderUtils.drawSolidBox(DEFAULT_AABB);
    }

    public static void drawSolidBox(AxisAlignedBB axisAlignedBB) {
        AxisAlignedBB bb;
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)bb.minX, (double)bb.minY, (double)bb.minZ);
        GL11.glVertex3d((double)bb.maxX, (double)bb.minY, (double)bb.minZ);
        GL11.glVertex3d((double)bb.maxX, (double)bb.minY, (double)bb.maxZ);
        GL11.glVertex3d((double)bb.minX, (double)bb.minY, (double)bb.maxZ);
        GL11.glVertex3d((double)bb.minX, (double)bb.maxY, (double)bb.minZ);
        GL11.glVertex3d((double)bb.minX, (double)bb.maxY, (double)bb.maxZ);
        GL11.glVertex3d((double)bb.maxX, (double)bb.maxY, (double)bb.maxZ);
        GL11.glVertex3d((double)bb.maxX, (double)bb.maxY, (double)bb.minZ);
        GL11.glVertex3d((double)bb.minX, (double)bb.minY, (double)bb.minZ);
        GL11.glVertex3d((double)bb.minX, (double)bb.maxY, (double)bb.minZ);
        GL11.glVertex3d((double)bb.maxX, (double)bb.maxY, (double)bb.minZ);
        GL11.glVertex3d((double)bb.maxX, (double)bb.minY, (double)bb.minZ);
        GL11.glVertex3d((double)bb.maxX, (double)bb.minY, (double)bb.minZ);
        GL11.glVertex3d((double)bb.maxX, (double)bb.maxY, (double)bb.minZ);
        GL11.glVertex3d((double)bb.maxX, (double)bb.maxY, (double)bb.maxZ);
        GL11.glVertex3d((double)bb.maxX, (double)bb.minY, (double)bb.maxZ);
        GL11.glVertex3d((double)bb.minX, (double)bb.minY, (double)bb.maxZ);
        GL11.glVertex3d((double)bb.maxX, (double)bb.minY, (double)bb.maxZ);
        GL11.glVertex3d((double)bb.maxX, (double)bb.maxY, (double)bb.maxZ);
        GL11.glVertex3d((double)bb.minX, (double)bb.maxY, (double)bb.maxZ);
        GL11.glVertex3d((double)bb.minX, (double)bb.minY, (double)bb.minZ);
        GL11.glVertex3d((double)bb.minX, (double)bb.minY, (double)bb.maxZ);
        GL11.glVertex3d((double)bb.minX, (double)bb.maxY, (double)bb.maxZ);
        GL11.glVertex3d((double)bb.minX, (double)bb.maxY, (double)bb.minZ);
        GL11.glEnd();
    }

    /*
     * WARNING - void declaration
     */
    public static void drawBoundingBox(AxisAlignedBB axisAlignedBB, float f, int n) {
        void width;
        AxisAlignedBB bb;
        void argb;
        int a = argb >>> 24 & 0xFF;
        int r = argb >>> 16 & 0xFF;
        int g = argb >>> 8 & 0xFF;
        int b = argb & 0xFF;
        RenderUtils.drawBoundingBox(bb, (float)width, r, g, b, a);
    }

    /*
     * WARNING - void declaration
     */
    public static void drawBoundingBox(AxisAlignedBB axisAlignedBB, float f, int n, int n2, int n3, int n4) {
        void alpha;
        void blue;
        void green;
        void red;
        AxisAlignedBB bb;
        void width;
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask((boolean)false);
        GlStateManager.glLineWidth((float)width);
        BufferBuilder bufferbuilder = INSTANCE.getBuffer();
        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color((int)red, (int)green, (int)blue, (int)alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color((int)red, (int)green, (int)blue, (int)alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color((int)red, (int)green, (int)blue, (int)alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color((int)red, (int)green, (int)blue, (int)alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.minZ).color((int)red, (int)green, (int)blue, (int)alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color((int)red, (int)green, (int)blue, (int)alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color((int)red, (int)green, (int)blue, (int)alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.minY, bb.maxZ).color((int)red, (int)green, (int)blue, (int)alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.maxZ).color((int)red, (int)green, (int)blue, (int)alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color((int)red, (int)green, (int)blue, (int)alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.maxZ).color((int)red, (int)green, (int)blue, (int)alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.maxZ).color((int)red, (int)green, (int)blue, (int)alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color((int)red, (int)green, (int)blue, (int)alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.minY, bb.minZ).color((int)red, (int)green, (int)blue, (int)alpha).endVertex();
        bufferbuilder.pos(bb.maxX, bb.maxY, bb.minZ).color((int)red, (int)green, (int)blue, (int)alpha).endVertex();
        bufferbuilder.pos(bb.minX, bb.maxY, bb.minZ).color((int)red, (int)green, (int)blue, (int)alpha).endVertex();
        RenderUtils.render();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void GLPre(float f) {
        float lineWidth;
        depth = GL11.glIsEnabled((int)2896);
        texture = GL11.glIsEnabled((int)3042);
        clean = GL11.glIsEnabled((int)3553);
        bind = GL11.glIsEnabled((int)2929);
        override = GL11.glIsEnabled((int)2848);
        RenderUtils.GLPre(depth, texture, clean, bind, override, lineWidth);
    }

    public static void GlPost() {
        RenderUtils.GLPost(depth, texture, clean, bind, override);
    }

    /*
     * WARNING - void declaration
     */
    public static void GLPre(boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5, float f) {
        void override;
        void bind;
        void clean;
        void lineWidth;
        void texture;
        boolean depth;
        if (depth) {
            GL11.glDisable((int)2896);
        }
        if (texture == false) {
            GL11.glEnable((int)3042);
        }
        GL11.glLineWidth((float)lineWidth);
        if (clean != false) {
            GL11.glDisable((int)3553);
        }
        if (bind != false) {
            GL11.glDisable((int)2929);
        }
        if (override == false) {
            GL11.glEnable((int)2848);
        }
        GlStateManager.blendFunc((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GL11.glHint((int)3154, (int)4354);
        GlStateManager.depthMask((boolean)false);
    }

    /*
     * WARNING - void declaration
     */
    public static void GLPost(boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5) {
        block4: {
            boolean depth;
            void texture;
            void clean;
            void bind;
            void override;
            GlStateManager.depthMask((boolean)true);
            if (override == false) {
                GL11.glDisable((int)2848);
            }
            if (bind != false) {
                GL11.glEnable((int)2929);
            }
            if (clean != false) {
                GL11.glEnable((int)3553);
            }
            if (texture == false) {
                GL11.glDisable((int)3042);
            }
            if (!depth) break block4;
            GL11.glEnable((int)2896);
        }
    }

    /*
     * WARNING - void declaration
     */
    public static void rainbowOutline(int n, int n2, float f, float f2, float f3, int n3, int n4) {
        void height;
        void lineThick;
        void y;
        int x;
        Color rainbowCol;
        void bri;
        void sat;
        void width;
        int[] counterChing = new int[]{1};
        int i = 0;
        while ((float)i < width / Float.intBitsToFloat(Float.floatToIntBits(0.29362026f) ^ 0x7E965565)) {
            rainbowCol = RainbowUtils.anyRainbowColor(counterChing[0] * 50, (int)sat, (int)bri);
            RenderUtils.drawRecta(x + i, (float)y, i + 2, (float)lineThick, rainbowCol.getRGB());
            counterChing[0] = counterChing[0] + 1;
            ++i;
        }
        int d = 0;
        while ((float)d < height / Float.intBitsToFloat(Float.floatToIntBits(0.1070069f) ^ 0x7DDB266F)) {
            rainbowCol = RainbowUtils.anyRainbowColor(counterChing[0] * 50, (int)sat, 255);
            RenderUtils.drawRecta((float)x + width, (float)(y + d), (float)lineThick, d + 2, rainbowCol.getRGB());
            counterChing[0] = counterChing[0] + 1;
            ++d;
        }
        int c = 0;
        while ((float)c < width / Float.intBitsToFloat(Float.floatToIntBits(0.8806751f) ^ 0x7F6173EC)) {
            rainbowCol = RainbowUtils.anyRainbowColor(counterChing[0] * 50, (int)sat, (int)bri);
            RenderUtils.drawRecta(x + c, (float)y + height + lineThick / Float.intBitsToFloat(Float.floatToIntBits(0.19170086f) ^ 0x7E444D3B), c + 2, (float)lineThick, rainbowCol.getRGB());
            counterChing[0] = counterChing[0] + 1;
            ++c;
        }
        int j = 0;
        while ((float)j < height / Float.intBitsToFloat(Float.floatToIntBits(0.5944587f) ^ 0x7F182E72)) {
            rainbowCol = RainbowUtils.anyRainbowColor(counterChing[0] * 100, (int)sat, (int)bri);
            RenderUtils.drawRecta(x, (float)(y + j), (float)lineThick, j + 2, rainbowCol.getRGB());
            counterChing[0] = counterChing[0] + 1;
            ++j;
        }
    }

    /*
     * WARNING - void declaration
     */
    public static void renderEntity(EntityPlayer entityPlayer, ModelBase modelBase, float f, float f2, float f3, float f4, float f5, float f6) {
        void limbSwingAmount;
        void limbSwing;
        void scale;
        EntityPlayer entity;
        void modelBase2;
        if (mc.getRenderManager() == null) {
            return;
        }
        if (modelBase2 instanceof ModelPlayer) {
            ModelPlayer modelPlayer = (ModelPlayer)modelBase2;
            modelPlayer.bipedHeadwear.showModel = false;
            modelPlayer.bipedBodyWear.showModel = false;
            modelPlayer.bipedLeftLegwear.showModel = false;
            modelPlayer.bipedRightLegwear.showModel = false;
            modelPlayer.bipedLeftArmwear.showModel = false;
            modelPlayer.bipedRightArmwear.showModel = false;
        }
        float partialTicks = mc.getRenderPartialTicks();
        double x = entity.posX - RenderUtils.mc.getRenderManager().viewerPosX;
        double y = entity.posY - RenderUtils.mc.getRenderManager().viewerPosY;
        double z = entity.posZ - RenderUtils.mc.getRenderManager().viewerPosZ;
        GlStateManager.pushMatrix();
        if (entity.isSneaking()) {
            y -= Double.longBitsToDouble(Double.doubleToLongBits(13.45461363651511) ^ 0x7FEAE8C31E5A4BCAL);
        }
        RenderUtils.renderLivingAt(x, y, z);
        RenderUtils.prepareRotations((EntityLivingBase)entity);
        float f42 = RenderUtils.prepareScale((EntityLivingBase)entity, (float)scale);
        float yaw = RenderUtils.handleRotationFloat((EntityLivingBase)entity, partialTicks);
        GlStateManager.enableAlpha();
        modelBase2.setLivingAnimations((EntityLivingBase)entity, (float)limbSwing, (float)limbSwingAmount, partialTicks);
        modelBase2.setRotationAngles((float)limbSwing, (float)limbSwingAmount, Float.intBitsToFloat(Float.floatToIntBits(1.1669066E38f) ^ 0x7EAF939B), yaw, entity.rotationPitch, f42, (Entity)entity);
        modelBase2.render((Entity)entity, (float)limbSwing, (float)limbSwingAmount, Float.intBitsToFloat(Float.floatToIntBits(9.5078E37f) ^ 0x7E8F0EB7), yaw, entity.rotationPitch, f42);
        GlStateManager.popMatrix();
    }

    /*
     * WARNING - void declaration
     */
    public static void renderLivingAt(double d, double d2, double d3) {
        void z;
        void y;
        double x;
        GlStateManager.translate((float)((float)x), (float)((float)y), (float)((float)z));
    }

    /*
     * WARNING - void declaration
     */
    public static float prepareScale(EntityLivingBase entityLivingBase, float f) {
        void scale;
        EntityLivingBase entity;
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale((float)Float.intBitsToFloat(Float.floatToIntBits(-10.400136f) ^ 0x7EA666F5), (float)Float.intBitsToFloat(Float.floatToIntBits(-9.592343f) ^ 0x7E997A3D), (float)Float.intBitsToFloat(Float.floatToIntBits(23.436003f) ^ 0x7E3B7CEF));
        double widthX = entity.getRenderBoundingBox().maxX - entity.getRenderBoundingBox().minX;
        double widthZ = entity.getRenderBoundingBox().maxZ - entity.getRenderBoundingBox().minZ;
        GlStateManager.scale((double)((double)scale + widthX), (double)((double)(scale * entity.height)), (double)((double)scale + widthZ));
        float f2 = Float.intBitsToFloat(Float.floatToIntBits(65.08312f) ^ 0x7F022A8F);
        GlStateManager.translate((float)Float.intBitsToFloat(Float.floatToIntBits(3.3419943E38f) ^ 0x7F7B6C78), (float)Float.intBitsToFloat(Float.floatToIntBits(-21.703114f) ^ 0x7E6DBF3F), (float)Float.intBitsToFloat(Float.floatToIntBits(1.3743181E38f) ^ 0x7ECEC8CF));
        return Float.intBitsToFloat(Float.floatToIntBits(11.578338f) ^ 0x7CB940DF);
    }

    public static void prepareRotations(EntityLivingBase entityLivingBase) {
        EntityLivingBase entityLivingBase2;
        GlStateManager.rotate((float)(Float.intBitsToFloat(Float.floatToIntBits(0.008832816f) ^ 0x7F24B784) - entityLivingBase2.rotationYaw), (float)Float.intBitsToFloat(Float.floatToIntBits(1.1144003E38f) ^ 0x7EA7AD23), (float)Float.intBitsToFloat(Float.floatToIntBits(5.227671f) ^ 0x7F274915), (float)Float.intBitsToFloat(Float.floatToIntBits(2.6400446E38f) ^ 0x7F469D69));
    }

    public static float handleRotationFloat(EntityLivingBase entityLivingBase, float f) {
        EntityLivingBase livingBase;
        return livingBase.rotationYawHead;
    }

    /*
     * WARNING - void declaration
     */
    public static void drawSidewaysGradient(float f, float f2, float f3, float f4, Color color, Color color2, int n, int n2) {
        void height;
        void width;
        void alpha2;
        void color22;
        void y;
        float x;
        void alpha1;
        void color1;
        RenderUtils.prepareGL();
        GL11.glShadeModel((int)7425);
        GL11.glEnable((int)2848);
        GL11.glBegin((int)7);
        GL11.glColor4f((float)((float)color1.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.093258716f) ^ 0x7EC1FE6D)), (float)((float)color1.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.29362985f) ^ 0x7DE956A7)), (float)((float)color1.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.011518868f) ^ 0x7F43B9A2)), (float)((float)alpha1 / Float.intBitsToFloat(Float.floatToIntBits(0.089654006f) ^ 0x7EC89C85)));
        GL11.glVertex2d((double)x, (double)((double)y));
        GL11.glColor4f((float)((float)color22.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.013414165f) ^ 0x7F24C716)), (float)((float)color22.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(6.233341E-5f) ^ 0x7BFDB8FF)), (float)((float)color22.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.10631739f) ^ 0x7EA6BCEF)), (float)((float)alpha2 / Float.intBitsToFloat(Float.floatToIntBits(0.093597986f) ^ 0x7EC0B04D)));
        GL11.glVertex2d((double)(x + width), (double)((double)y));
        GL11.glColor4f((float)((float)color22.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.01181007f) ^ 0x7F3E7F06)), (float)((float)color22.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.0496864f) ^ 0x7E3483F7)), (float)((float)color22.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.07141397f) ^ 0x7EED417D)), (float)((float)alpha2 / Float.intBitsToFloat(Float.floatToIntBits(0.014228609f) ^ 0x7F161F1D)));
        GL11.glVertex2d((double)(x + width), (double)((double)(y + height)));
        GL11.glColor4f((float)((float)color1.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.012489689f) ^ 0x7F33A18D)), (float)((float)color1.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.06379611f) ^ 0x7EFDA789)), (float)((float)color1.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.012435985f) ^ 0x7F34C04D)), (float)((float)alpha1 / Float.intBitsToFloat(Float.floatToIntBits(0.056789067f) ^ 0x7E179BA7)));
        GL11.glVertex2d((double)x, (double)((double)(y + height)));
        GL11.glEnd();
        RenderUtils.releaseGL();
    }

    /*
     * WARNING - void declaration
     */
    public static void drawCircle(float f, float f2, float f3, float f4, Color color) {
        void color2;
        void radius;
        void z;
        void y;
        float x;
        BlockPos pos = new BlockPos((double)x, (double)y, (double)z);
        AxisAlignedBB bb = new AxisAlignedBB((double)pos.getX() - RenderUtils.mc.getRenderManager().viewerPosX, (double)pos.getY() - RenderUtils.mc.getRenderManager().viewerPosY, (double)pos.getZ() - RenderUtils.mc.getRenderManager().viewerPosZ, (double)(pos.getX() + 1) - RenderUtils.mc.getRenderManager().viewerPosX, (double)(pos.getY() + 1) - RenderUtils.mc.getRenderManager().viewerPosY, (double)(pos.getZ() + 1) - RenderUtils.mc.getRenderManager().viewerPosZ);
        RenderUtils.drawCircleVertices(bb, (float)radius, (Color)color2);
    }

    /*
     * WARNING - void declaration
     */
    public static void drawCircleVertices(AxisAlignedBB axisAlignedBB, float f, Color color) {
        void color2;
        float r = (float)color2.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.06799387f) ^ 0x7EF4405F);
        float g = (float)color2.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.14126728f) ^ 0x7D6FA85F);
        float b = (float)color2.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.10135498f) ^ 0x7EB09333);
        float a = (float)color2.getAlpha() / Float.intBitsToFloat(Float.floatToIntBits(0.10700496f) ^ 0x7EA4256B);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask((boolean)false);
        GL11.glEnable((int)2848);
        GL11.glHint((int)3154, (int)4354);
        GL11.glLineWidth((float)Float.intBitsToFloat(Float.floatToIntBits(4.711665f) ^ 0x7F16C5F6));
        for (int i = 0; i < 360; ++i) {
            void radius;
            AxisAlignedBB bb;
            buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
            buffer.pos(bb.getCenter().x + Math.sin((double)i * Double.longBitsToDouble(Double.doubleToLongBits(0.5234292570199359) ^ 0x7FE99E15FB87AA04L) / Double.longBitsToDouble(Double.doubleToLongBits(0.008476520640446645) ^ 0x7FE7DC23577A08EAL)) * (double)radius, bb.minY, bb.getCenter().z + Math.cos((double)i * Double.longBitsToDouble(Double.doubleToLongBits(0.4296392219933493) ^ 0x7FD25ECECCF04CDDL) / Double.longBitsToDouble(Double.doubleToLongBits(0.3261844210137585) ^ 0x7FB260349F2E009FL)) * (double)radius).color(r, g, b, a).endVertex();
            buffer.pos(bb.getCenter().x + Math.sin((double)(i + 1) * Double.longBitsToDouble(Double.doubleToLongBits(0.6451235577936375) ^ 0x7FED852165C1923FL) / Double.longBitsToDouble(Double.doubleToLongBits(0.05192483434506725) ^ 0x7FCC15E452B97CF3L)) * (double)radius, bb.minY, bb.getCenter().z + Math.cos((double)(i + 1) * Double.longBitsToDouble(Double.doubleToLongBits(0.3999371395001485) ^ 0x7FD0B96ABCB420E1L) / Double.longBitsToDouble(Double.doubleToLongBits(0.015224943326098275) ^ 0x7FE9AE414D5A348EL)) * (double)radius).color(r, g, b, a).endVertex();
            tessellator.draw();
        }
        GL11.glDisable((int)2848);
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    /*
     * WARNING - void declaration
     */
    public static void drawEntityOnScreen(Entity entity, int n, int n2, int n3, float f, float f2) {
        Entity ent;
        void mouseY;
        void scale;
        void posY;
        void posX;
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)((float)posX), (float)((float)posY), (float)Float.intBitsToFloat(Float.floatToIntBits(1.6824397f) ^ 0x7D9F5A2F));
        GlStateManager.scale((float)((float)(-scale)), (float)((float)scale), (float)((float)scale));
        GlStateManager.rotate((float)Float.intBitsToFloat(Float.floatToIntBits(0.010010559f) ^ 0x7F100354), (float)Float.intBitsToFloat(Float.floatToIntBits(3.2874774E38f) ^ 0x7F775283), (float)Float.intBitsToFloat(Float.floatToIntBits(1.277499E38f) ^ 0x7EC03779), (float)Float.intBitsToFloat(Float.floatToIntBits(7.556555f) ^ 0x7F71CF4C));
        GlStateManager.rotate((float)Float.intBitsToFloat(Float.floatToIntBits(0.08215728f) ^ 0x7EAF4213), (float)Float.intBitsToFloat(Float.floatToIntBits(3.2189796E38f) ^ 0x7F722B4B), (float)Float.intBitsToFloat(Float.floatToIntBits(11.650432f) ^ 0x7EBA682B), (float)Float.intBitsToFloat(Float.floatToIntBits(1.8456703E38f) ^ 0x7F0ADA51));
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate((float)Float.intBitsToFloat(Float.floatToIntBits(-0.07293611f) ^ 0x7E925F87), (float)Float.intBitsToFloat(Float.floatToIntBits(4.4185975E37f) ^ 0x7E04F7A3), (float)Float.intBitsToFloat(Float.floatToIntBits(7.4331884f) ^ 0x7F6DDCAE), (float)Float.intBitsToFloat(Float.floatToIntBits(1.0855388E38f) ^ 0x7EA3556F));
        GlStateManager.rotate((float)(-((float)Math.atan((double)(mouseY / Float.intBitsToFloat(Float.floatToIntBits(0.04850099f) ^ 0x7F66A8F9)))) * Float.intBitsToFloat(Float.floatToIntBits(0.45494023f) ^ 0x7F48EDED)), (float)Float.intBitsToFloat(Float.floatToIntBits(7.337801f) ^ 0x7F6ACF44), (float)Float.intBitsToFloat(Float.floatToIntBits(5.3653236E37f) ^ 0x7E2174F3), (float)Float.intBitsToFloat(Float.floatToIntBits(2.4617955E38f) ^ 0x7F393475));
        GlStateManager.translate((float)Float.intBitsToFloat(Float.floatToIntBits(9.835158E37f) ^ 0x7E93FBA7), (float)Float.intBitsToFloat(Float.floatToIntBits(2.4084867E38f) ^ 0x7F3531C4), (float)Float.intBitsToFloat(Float.floatToIntBits(2.8240162E38f) ^ 0x7F547493));
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(Float.intBitsToFloat(Float.floatToIntBits(0.009158009f) ^ 0x7F220B79));
        rendermanager.setRenderShadow(false);
        rendermanager.renderEntity(ent, Double.longBitsToDouble(Double.doubleToLongBits(1.5829156308224974E308) ^ 0x7FEC2D44F635CC31L), Double.longBitsToDouble(Double.doubleToLongBits(1.7339437041438026E308) ^ 0x7FEEDD7F3831FB25L), Double.longBitsToDouble(Double.doubleToLongBits(8.225454125242748E307) ^ 0x7FDD4899452B1C5FL), Float.intBitsToFloat(Float.floatToIntBits(4.1008535E37f) ^ 0x7DF6CFA7), Float.intBitsToFloat(Float.floatToIntBits(4.240404f) ^ 0x7F07B164), false);
        rendermanager.setRenderShadow(true);
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture((int)OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
    }

    public static AxisAlignedBB fixBB(AxisAlignedBB axisAlignedBB) {
        AxisAlignedBB bb;
        return new AxisAlignedBB(bb.minX - RenderUtils.mc.getRenderManager().viewerPosX, bb.minY - RenderUtils.mc.getRenderManager().viewerPosY, bb.minZ - RenderUtils.mc.getRenderManager().viewerPosZ, bb.maxX - RenderUtils.mc.getRenderManager().viewerPosX, bb.maxY - RenderUtils.mc.getRenderManager().viewerPosY, bb.maxZ - RenderUtils.mc.getRenderManager().viewerPosZ);
    }

    /*
     * WARNING - void declaration
     */
    public static void drawOpenGradientBoxBB(AxisAlignedBB axisAlignedBB, Color color, Color color2, boolean bl) {
        for (EnumFacing face : EnumFacing.values()) {
            void depth;
            void endColor;
            void startColor;
            AxisAlignedBB bb;
            RenderUtils.drawGradientPlaneBB(bb.grow(Double.longBitsToDouble(Double.doubleToLongBits(2433.56417635812) ^ 0x7FC3616D3BB93E9BL)), face, (Color)startColor, (Color)endColor, (boolean)depth);
        }
    }

    /*
     * WARNING - void declaration
     */
    public static void drawGradientPlaneBB(AxisAlignedBB axisAlignedBB, EnumFacing enumFacing, Color color, Color color2, boolean bl) {
        void depth;
        void face;
        void endColor;
        void startColor;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder builder = tessellator.getBuffer();
        float red = (float)startColor.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.27211377f) ^ 0x7DF4527F);
        float green = (float)startColor.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.12230372f) ^ 0x7E857A5F);
        float blue = (float)startColor.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.008009558f) ^ 0x7F7C3A85);
        float alpha = (float)startColor.getAlpha() / Float.intBitsToFloat(Float.floatToIntBits(0.013573141f) ^ 0x7F2161E1);
        float red1 = (float)endColor.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.015459908f) ^ 0x7F024B8E);
        float green1 = (float)endColor.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.007945256f) ^ 0x7F7D2CD2);
        float blue1 = (float)endColor.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.009713325f) ^ 0x7F6024A3);
        float alpha1 = (float)endColor.getAlpha() / Float.intBitsToFloat(Float.floatToIntBits(0.008760959f) ^ 0x7F708A20);
        double x1 = Double.longBitsToDouble(Double.doubleToLongBits(1.1084267949930563E308) ^ 0x7FE3BB0C007E4029L);
        double y1 = Double.longBitsToDouble(Double.doubleToLongBits(1.136153967114939E308) ^ 0x7FE43965F4022B39L);
        double z1 = Double.longBitsToDouble(Double.doubleToLongBits(4.3109616506545676E307) ^ 0x7FCEB1EFD5AC3A73L);
        double x2 = Double.longBitsToDouble(Double.doubleToLongBits(1.6087854571859715E308) ^ 0x7FECA3282BE4DCDFL);
        double y2 = Double.longBitsToDouble(Double.doubleToLongBits(1.3893593717110467E308) ^ 0x7FE8BB3E317C7857L);
        double z2 = Double.longBitsToDouble(Double.doubleToLongBits(1.5476281096126842E308) ^ 0x7FEB8C7743188780L);
        switch (1.$SwitchMap$net$minecraft$util$EnumFacing[face.ordinal()]) {
            case 1: {
                AxisAlignedBB bb;
                x1 = bb.minX;
                x2 = bb.maxX;
                y1 = bb.minY;
                y2 = bb.minY;
                z1 = bb.minZ;
                z2 = bb.maxZ;
                break;
            }
            case 2: {
                AxisAlignedBB bb;
                x1 = bb.minX;
                x2 = bb.maxX;
                y1 = bb.maxY;
                y2 = bb.maxY;
                z1 = bb.minZ;
                z2 = bb.maxZ;
                break;
            }
            case 3: {
                AxisAlignedBB bb;
                x1 = bb.maxX;
                x2 = bb.maxX;
                y1 = bb.minY;
                y2 = bb.maxY;
                z1 = bb.minZ;
                z2 = bb.maxZ;
                break;
            }
            case 4: {
                AxisAlignedBB bb;
                x1 = bb.minX;
                x2 = bb.minX;
                y1 = bb.minY;
                y2 = bb.maxY;
                z1 = bb.minZ;
                z2 = bb.maxZ;
                break;
            }
            case 5: {
                AxisAlignedBB bb;
                x1 = bb.minX;
                x2 = bb.maxX;
                y1 = bb.minY;
                y2 = bb.maxY;
                z1 = bb.maxZ;
                z2 = bb.maxZ;
                break;
            }
            case 6: {
                AxisAlignedBB bb;
                x1 = bb.minX;
                x2 = bb.maxX;
                y1 = bb.minY;
                y2 = bb.maxY;
                z1 = bb.minZ;
                z2 = bb.minZ;
            }
        }
        if (face == EnumFacing.DOWN || face == EnumFacing.UP || face == EnumFacing.EAST || face == EnumFacing.WEST || face == EnumFacing.SOUTH || face == EnumFacing.NORTH) {
            // empty if block
        }
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        if (depth == false) {
            GlStateManager.depthMask((boolean)false);
            GlStateManager.disableDepth();
        }
        builder.begin(5, DefaultVertexFormats.POSITION_COLOR);
        if (face == EnumFacing.EAST || face == EnumFacing.WEST || face == EnumFacing.NORTH || face == EnumFacing.SOUTH) {
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
        } else if (face == EnumFacing.UP) {
            builder.pos(x1, y1, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y1, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y1, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x1, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z1).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
            builder.pos(x2, y2, z2).color(red1, green1, blue1, alpha1).endVertex();
        } else if (face == EnumFacing.DOWN) {
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y1, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x1, y2, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z1).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
            builder.pos(x2, y2, z2).color(red, green, blue, alpha).endVertex();
        }
        tessellator.draw();
        if (depth == false) {
            GlStateManager.depthMask((boolean)true);
            GlStateManager.enableDepth();
        }
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }

    /*
     * WARNING - void declaration
     */
    public static void drawBoxBB(AxisAlignedBB axisAlignedBB, Color color) {
        block0: {
            void color2;
            AxisAlignedBB bb;
            camera.setPosition(Objects.requireNonNull(RenderUtils.mc.getRenderViewEntity()).posX, RenderUtils.mc.getRenderViewEntity().posY, RenderUtils.mc.getRenderViewEntity().posZ);
            if (!camera.isBoundingBoxInFrustum(new AxisAlignedBB(bb.minX + RenderUtils.mc.getRenderManager().viewerPosX, bb.minY + RenderUtils.mc.getRenderManager().viewerPosY, bb.minZ + RenderUtils.mc.getRenderManager().viewerPosZ, bb.maxX + RenderUtils.mc.getRenderManager().viewerPosX, bb.maxY + RenderUtils.mc.getRenderManager().viewerPosY, bb.maxZ + RenderUtils.mc.getRenderManager().viewerPosZ))) break block0;
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableDepth();
            GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)0, (int)1);
            GlStateManager.disableTexture2D();
            GL11.glEnable((int)2848);
            GL11.glHint((int)3154, (int)4354);
            RenderGlobal.renderFilledBox((AxisAlignedBB)bb, (float)((float)color2.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.015003811f) ^ 0x7F0AD28B)), (float)((float)color2.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.008765428f) ^ 0x7F709CDF)), (float)((float)color2.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.088410296f) ^ 0x7ECA1075)), (float)((float)color2.getAlpha() / Float.intBitsToFloat(Float.floatToIntBits(0.012969875f) ^ 0x7F2B7F99)));
            GL11.glDisable((int)2848);
            GlStateManager.enableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }

    /*
     * WARNING - void declaration
     */
    public static void drawLine(double d, double d2, double d3, double d4, float f) {
        void y1;
        void x1;
        void y;
        double x;
        void width;
        GL11.glDisable((int)3553);
        GL11.glLineWidth((float)width);
        GL11.glBegin((int)1);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)x1, (double)y1);
        GL11.glEnd();
        GL11.glEnable((int)3553);
    }

    static {
        INSTANCE = new RenderUtils();
        DEFAULT_AABB = new AxisAlignedBB(Double.longBitsToDouble(Double.doubleToLongBits(1.4310172983923016E308) ^ 0x7FE979137A4093D9L), Double.longBitsToDouble(Double.doubleToLongBits(1.2283706187462381E308) ^ 0x7FE5DDA002B97AB6L), Double.longBitsToDouble(Double.doubleToLongBits(1.504232464556803E308) ^ 0x7FEAC6B6CA8AA20AL), Double.longBitsToDouble(Double.doubleToLongBits(100.00695490087308) ^ 0x7FA90071F2F7F2FFL), Double.longBitsToDouble(Double.doubleToLongBits(4.723666110916814) ^ 0x7FE2E508BA9E71E5L), Double.longBitsToDouble(Double.doubleToLongBits(2.1599342830113489E307) ^ 0x7FBEC22C15AE6577L));
    }
}

