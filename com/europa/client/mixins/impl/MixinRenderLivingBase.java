/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.entity.layers.LayerRenderer
 *  net.minecraft.client.renderer.texture.DynamicTexture
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityGhast
 *  net.minecraft.entity.monster.EntityGolem
 *  net.minecraft.entity.monster.EntityIronGolem
 *  net.minecraft.entity.monster.EntityMagmaCube
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.monster.EntityPigZombie
 *  net.minecraft.entity.monster.EntitySlime
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntitySquid
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EnumPlayerModelParts
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraftforge.client.event.RenderLivingEvent$Post
 *  net.minecraftforge.client.event.RenderLivingEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.Event
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.opengl.GL11
 */
package com.europa.client.mixins.impl;

import com.europa.Europa;
import com.europa.api.manager.event.impl.render.EventRenderEntityLayer;
import com.europa.api.utilities.render.OutlineUtils;
import com.europa.client.modules.render.ModuleESP;
import com.europa.client.modules.render.ModulePopChams;
import com.europa.client.modules.render.ModuleShaderChams;
import com.google.common.collect.Lists;
import java.awt.Color;
import java.nio.FloatBuffer;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={RenderLivingBase.class})
public abstract class MixinRenderLivingBase<T extends EntityLivingBase>
extends Render<T> {
    @Shadow
    private static final Logger LOGGER = LogManager.getLogger();
    @Shadow
    private static final DynamicTexture TEXTURE_BRIGHTNESS = new DynamicTexture(16, 16);
    @Shadow
    protected ModelBase mainModel;
    @Shadow
    protected FloatBuffer brightnessBuffer = GLAllocation.createDirectFloatBuffer((int)4);
    @Shadow
    protected List<LayerRenderer<T>> layerRenderers = Lists.newArrayList();
    @Shadow
    protected boolean renderMarker;
    @Shadow
    public static float NAME_TAG_RANGE = 64.0f;
    @Shadow
    public static float NAME_TAG_RANGE_SNEAK = 32.0f;

    public MixinRenderLivingBase() {
        super(null);
    }

    @Shadow
    protected float getSwingProgress(T livingBase, float partialTickTime) {
        return livingBase.getSwingProgress(partialTickTime);
    }

    @Shadow
    protected float interpolateRotation(float prevYawOffset, float yawOffset, float partialTicks) {
        float f;
        for (f = yawOffset - prevYawOffset; f < -180.0f; f += 360.0f) {
        }
        while (f >= 180.0f) {
            f -= 360.0f;
        }
        return prevYawOffset + partialTicks * f;
    }

    @Shadow
    protected void renderLivingAt(T entityLivingBaseIn, double x, double y, double z) {
        GlStateManager.translate((float)((float)x), (float)((float)y), (float)((float)z));
    }

    @Shadow
    protected float handleRotationFloat(T livingBase, float partialTicks) {
        return (float)((EntityLivingBase)livingBase).ticksExisted + partialTicks;
    }

    @Shadow
    protected void applyRotations(T entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
        GlStateManager.rotate((float)(180.0f - rotationYaw), (float)0.0f, (float)1.0f, (float)0.0f);
        if (((EntityLivingBase)entityLiving).deathTime > 0) {
            float f;
            float f2 = ((float)((EntityLivingBase)entityLiving).deathTime + partialTicks - 1.0f) / 20.0f * 1.6f;
            f2 = MathHelper.sqrt((float)f2);
            if (f > 1.0f) {
                f2 = 1.0f;
            }
        } else {
            String s = TextFormatting.getTextWithoutFormattingCodes((String)entityLiving.getName());
            if (s != null && ("Dinnerbone".equals(s) || "Grumm".equals(s)) && (!(entityLiving instanceof EntityPlayer) || ((EntityPlayer)entityLiving).isWearing(EnumPlayerModelParts.CAPE))) {
                GlStateManager.translate((float)0.0f, (float)(((EntityLivingBase)entityLiving).height + 0.1f), (float)0.0f);
                GlStateManager.rotate((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            }
        }
    }

    @Shadow
    public float prepareScale(T entitylivingbaseIn, float partialTicks) {
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale((float)-1.0f, (float)-1.0f, (float)1.0f);
        float f = 0.0625f;
        GlStateManager.translate((float)0.0f, (float)-1.501f, (float)0.0f);
        return 0.0625f;
    }

    @Shadow
    protected boolean setScoreTeamColor(T entityLivingBaseIn) {
        GlStateManager.disableLighting();
        GlStateManager.setActiveTexture((int)OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
        return true;
    }

    @Shadow
    protected void renderModel(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
    }

    @Shadow
    protected void renderLayers(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scaleIn) {
    }

    @Inject(method={"renderName"}, at={@At(value="HEAD")}, cancellable=true)
    public void obRenderNamePre(T entity, double x, double y, double z, CallbackInfo ci) {
        if (!ModuleShaderChams.renderNameTags) {
            ci.cancel();
        }
    }

    @Inject(method={"renderLayers"}, at={@At(value="HEAD")}, cancellable=true)
    public void onInjectRenderLayers(CallbackInfo ci) {
        if (!ModuleShaderChams.renderNameTags) {
            ci.cancel();
        }
    }

    @Redirect(method={"renderLayers"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/renderer/entity/layers/LayerRenderer;doRenderLayer(Lnet/minecraft/entity/EntityLivingBase;FFFFFFF)V"))
    public void onRenderLayersDoLayers(LayerRenderer<EntityLivingBase> layer, EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scaleIn) {
        EventRenderEntityLayer event = new EventRenderEntityLayer(entity, layer);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (!event.isCanceled()) {
            layer.doRenderLayer(entity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scaleIn);
        }
    }

    @Shadow
    protected void unsetScoreTeamColor() {
        GlStateManager.enableLighting();
        GlStateManager.setActiveTexture((int)OpenGlHelper.lightmapTexUnit);
        GlStateManager.enableTexture2D();
        GlStateManager.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
    }

    @Shadow
    protected boolean setDoRenderBrightness(T entityLivingBaseIn, float partialTicks) {
        return false;
    }

    @Shadow
    protected void unsetBrightness() {
    }

    @Overwrite
    public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
        Color color = new Color(ModuleESP.color.getRed(), ModuleESP.color.getGreen(), ModuleESP.color.getBlue());
        float lineWidth = (float)ModuleESP.width.getValue().doubleValue();
        if (MinecraftForge.EVENT_BUS.post((Event)new RenderLivingEvent.Pre(entity, (RenderLivingBase)RenderLivingBase.class.cast((Object)this), partialTicks, x, y, z))) {
            return;
        }
        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        GL11.glEnable((int)2848);
        this.mainModel.swingProgress = this.getSwingProgress(entity, partialTicks);
        boolean shouldSit = entity.isRiding() && entity.getRidingEntity() != null && entity.getRidingEntity().shouldRiderSit();
        this.mainModel.isRiding = shouldSit;
        this.mainModel.isChild = entity.isChild();
        try {
            float f = this.interpolateRotation(((EntityLivingBase)entity).prevRenderYawOffset, ((EntityLivingBase)entity).renderYawOffset, partialTicks);
            float f1 = this.interpolateRotation(((EntityLivingBase)entity).prevRotationYawHead, ((EntityLivingBase)entity).rotationYawHead, partialTicks);
            float f2 = f1 - f;
            if (shouldSit && entity.getRidingEntity() instanceof EntityLivingBase) {
                EntityLivingBase entitylivingbase = (EntityLivingBase)entity.getRidingEntity();
                f = this.interpolateRotation(entitylivingbase.prevRenderYawOffset, entitylivingbase.renderYawOffset, partialTicks);
                f2 = f1 - f;
                float f3 = MathHelper.wrapDegrees((float)f2);
                if (f3 < -85.0f) {
                    f3 = -85.0f;
                }
                if (f3 >= 85.0f) {
                    f3 = 85.0f;
                }
                f = f1 - f3;
                if (f3 * f3 > 2500.0f) {
                    f += f3 * 0.2f;
                }
                f2 = f1 - f;
            }
            float f7 = ((EntityLivingBase)entity).prevRotationPitch + (((EntityLivingBase)entity).rotationPitch - ((EntityLivingBase)entity).prevRotationPitch) * partialTicks;
            this.renderLivingAt(entity, x, y, z);
            float f8 = this.handleRotationFloat(entity, partialTicks);
            this.applyRotations(entity, f8, f, partialTicks);
            float f4 = this.prepareScale(entity, partialTicks);
            float f5 = 0.0f;
            float f6 = 0.0f;
            if (!entity.isRiding()) {
                f5 = ((EntityLivingBase)entity).prevLimbSwingAmount + (((EntityLivingBase)entity).limbSwingAmount - ((EntityLivingBase)entity).prevLimbSwingAmount) * partialTicks;
                f6 = ((EntityLivingBase)entity).limbSwing - ((EntityLivingBase)entity).limbSwingAmount * (1.0f - partialTicks);
                if (entity.isChild()) {
                    f6 *= 3.0f;
                }
                if (f5 > 1.0f) {
                    f5 = 1.0f;
                }
                f2 = f1 - f;
            }
            GlStateManager.enableAlpha();
            this.mainModel.setLivingAnimations(entity, f6, f5, partialTicks);
            this.mainModel.setRotationAngles(f6, f5, f8, f2, f7, f4, entity);
            if (Europa.getModuleManager().isModuleEnabled("ESP")) {
                GlStateManager.depthMask((boolean)true);
                if (ModuleESP.players.getValue()) {
                    if (entity instanceof EntityPlayer && entity != ModulePopChams.player) {
                        if (entity != Minecraft.getMinecraft().player) {
                            OutlineUtils.setColor(color);
                            this.renderModel(entity, f6, f5, f8, f2, f7, f4);
                            OutlineUtils.renderOne(lineWidth);
                            this.renderModel(entity, f6, f5, f8, f2, f7, f4);
                            OutlineUtils.renderTwo();
                            this.renderModel(entity, f6, f5, f8, f2, f7, f4);
                            OutlineUtils.renderThree();
                            OutlineUtils.renderFour();
                            OutlineUtils.setColor(color);
                            this.renderModel(entity, f6, f5, f8, f2, f7, f4);
                            OutlineUtils.renderFive();
                            OutlineUtils.setColor(Color.WHITE);
                        }
                    } else {
                        this.renderModel(entity, f6, f5, f8, f2, f7, f4);
                    }
                }
                if (ModuleESP.mobs.getValue()) {
                    if (entity instanceof EntityMob || entity instanceof EntitySlime || entity instanceof EntityMagmaCube || entity instanceof EntityGhast) {
                        if (entity != Minecraft.getMinecraft().player && !(entity instanceof EntityPigZombie)) {
                            OutlineUtils.setColor(Color.RED);
                            this.renderModel(entity, f6, f5, f8, f2, f7, f4);
                            OutlineUtils.renderOne(lineWidth);
                            this.renderModel(entity, f6, f5, f8, f2, f7, f4);
                            OutlineUtils.renderTwo();
                            this.renderModel(entity, f6, f5, f8, f2, f7, f4);
                            OutlineUtils.renderThree();
                            OutlineUtils.renderFour();
                            OutlineUtils.setColor(Color.RED);
                            this.renderModel(entity, f6, f5, f8, f2, f7, f4);
                            OutlineUtils.renderFive();
                            OutlineUtils.setColor(Color.WHITE);
                        }
                    } else {
                        this.renderModel(entity, f6, f5, f8, f2, f7, f4);
                    }
                }
                if (ModuleESP.animals.getValue()) {
                    if (entity instanceof EntityAnimal || entity instanceof EntityIronGolem || entity instanceof EntityGolem || entity instanceof EntitySquid || entity instanceof EntityPigZombie) {
                        if (entity != Minecraft.getMinecraft().player) {
                            OutlineUtils.setColor(Color.GREEN);
                            this.renderModel(entity, f6, f5, f8, f2, f7, f4);
                            OutlineUtils.renderOne(lineWidth);
                            this.renderModel(entity, f6, f5, f8, f2, f7, f4);
                            OutlineUtils.renderTwo();
                            this.renderModel(entity, f6, f5, f8, f2, f7, f4);
                            OutlineUtils.renderThree();
                            OutlineUtils.renderFour();
                            OutlineUtils.setColor(Color.GREEN);
                            this.renderModel(entity, f6, f5, f8, f2, f7, f4);
                            OutlineUtils.renderFive();
                            OutlineUtils.setColor(Color.WHITE);
                        }
                    } else {
                        this.renderModel(entity, f6, f5, f8, f2, f7, f4);
                    }
                }
            } else {
                this.renderModel(entity, f6, f5, f8, f2, f7, f4);
            }
            if (this.renderOutlines) {
                boolean flag1 = this.setScoreTeamColor(entity);
                GlStateManager.enableColorMaterial();
                GlStateManager.enableOutlineMode((int)ModuleESP.color.getRGB());
                if (!this.renderMarker) {
                    this.renderModel(entity, f6, f5, f8, f2, f7, f4);
                }
                if (!(entity instanceof EntityPlayer) || !((EntityPlayer)entity).isSpectator()) {
                    this.renderLayers(entity, f6, f5, partialTicks, f8, f2, f7, f4);
                }
                GlStateManager.disableOutlineMode();
                GlStateManager.disableColorMaterial();
                if (flag1) {
                    this.unsetScoreTeamColor();
                }
            } else {
                boolean flag = this.setDoRenderBrightness(entity, partialTicks);
                this.renderModel(entity, f6, f5, f8, f2, f7, f4);
                if (flag) {
                    this.unsetBrightness();
                }
                GlStateManager.depthMask((boolean)true);
                if (!(entity instanceof EntityPlayer) || !((EntityPlayer)entity).isSpectator()) {
                    this.renderLayers(entity, f6, f5, partialTicks, f8, f2, f7, f4);
                }
            }
            GlStateManager.disableRescaleNormal();
        }
        catch (Exception exception) {
            LOGGER.error("Couldn't render entity", (Throwable)exception);
        }
        GlStateManager.setActiveTexture((int)OpenGlHelper.lightmapTexUnit);
        GlStateManager.enableTexture2D();
        GlStateManager.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
        GlStateManager.enableCull();
        GL11.glDisable((int)2848);
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
        MinecraftForge.EVENT_BUS.post((Event)new RenderLivingEvent.Post(entity, (RenderLivingBase)RenderLivingBase.class.cast((Object)this), partialTicks, x, y, z));
    }
}

