/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.europa.client.mixins.impl;

import com.europa.Europa;
import com.europa.api.utilities.render.OutlineUtils;
import com.europa.api.utilities.render.RenderUtils;
import com.europa.client.modules.render.ModuleKillEffects;
import com.europa.client.modules.render.ModulePlayerChams;
import com.europa.client.modules.render.ModulePopChams;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={RenderLivingBase.class}, priority=999)
public abstract class MixinRendererLivingEntity<T extends EntityLivingBase>
extends Render<T> {
    @Shadow
    protected ModelBase mainModel;
    private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");

    protected MixinRendererLivingEntity() {
        super(null);
    }

    @Overwrite
    protected void renderModel(T entitylivingbaseIn, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float scaleFactor) {
        boolean isPlayer = entitylivingbaseIn instanceof EntityPlayer;
        if (!this.bindEntityTexture((Entity)entitylivingbaseIn)) {
            return;
        }
        Minecraft mc = Minecraft.getMinecraft();
        mc.gameSettings.fancyGraphics = false;
        if (Europa.getModuleManager().isModuleEnabled("PlayerChams") && isPlayer && entitylivingbaseIn != ModulePopChams.player && entitylivingbaseIn != ModuleKillEffects.player) {
            GL11.glPushAttrib((int)1048575);
            GL11.glDisable((int)3008);
            GL11.glDisable((int)3553);
            GL11.glDisable((int)2896);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glLineWidth((float)1.5f);
            GL11.glEnable((int)2960);
            GL11.glDisable((int)2929);
            GL11.glDepthMask((boolean)false);
            GL11.glEnable((int)10754);
            if (Europa.FRIEND_MANAGER.isFriend(entitylivingbaseIn.getName()) && !ModulePlayerChams.syncFriend.getValue()) {
                if (ModulePlayerChams.fhiddenSync.getValue()) {
                    GL11.glColor4f((float)((float)ModulePlayerChams.fcolor.getRed() / 255.0f), (float)((float)ModulePlayerChams.fcolor.getGreen() / 255.0f), (float)((float)ModulePlayerChams.fcolor.getBlue() / 255.0f), (float)((float)ModulePlayerChams.fdaColor.getValue().getAlpha() / 255.0f));
                } else {
                    GL11.glColor4f((float)((float)ModulePlayerChams.fhideColor.getRed() / 255.0f), (float)((float)ModulePlayerChams.fhideColor.getGreen() / 255.0f), (float)((float)ModulePlayerChams.fhideColor.getBlue() / 255.0f), (float)((float)ModulePlayerChams.fhiddenColor.getValue().getAlpha() / 255.0f));
                }
            } else if (ModulePlayerChams.hiddenSync.getValue()) {
                GL11.glColor4f((float)((float)ModulePlayerChams.color.getRed() / 255.0f), (float)((float)ModulePlayerChams.color.getGreen() / 255.0f), (float)((float)ModulePlayerChams.color.getBlue() / 255.0f), (float)((float)ModulePlayerChams.daColor.getValue().getAlpha() / 255.0f));
            } else {
                GL11.glColor4f((float)((float)ModulePlayerChams.hideColor.getRed() / 255.0f), (float)((float)ModulePlayerChams.hideColor.getGreen() / 255.0f), (float)((float)ModulePlayerChams.hideColor.getBlue() / 255.0f), (float)((float)ModulePlayerChams.hiddenColor.getValue().getAlpha() / 255.0f));
            }
            if (Europa.FRIEND_MANAGER.isFriend(entitylivingbaseIn.getName())) {
                if (ModulePlayerChams.fhidden.getValue()) {
                    this.mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
                }
            } else if (ModulePlayerChams.hidden.getValue()) {
                this.mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
            }
            GL11.glEnable((int)2929);
            GL11.glDepthMask((boolean)true);
            if (Europa.FRIEND_MANAGER.isFriend(entitylivingbaseIn.getName()) && !ModulePlayerChams.syncFriend.getValue()) {
                GL11.glColor4f((float)((float)ModulePlayerChams.fcolor.getRed() / 255.0f), (float)((float)ModulePlayerChams.fcolor.getGreen() / 255.0f), (float)((float)ModulePlayerChams.fcolor.getBlue() / 255.0f), (float)((float)ModulePlayerChams.fdaColor.getValue().getAlpha() / 255.0f));
            } else {
                GL11.glColor4f((float)((float)ModulePlayerChams.color.getRed() / 255.0f), (float)((float)ModulePlayerChams.color.getGreen() / 255.0f), (float)((float)ModulePlayerChams.color.getBlue() / 255.0f), (float)((float)ModulePlayerChams.daColor.getValue().getAlpha() / 255.0f));
            }
            if (Europa.FRIEND_MANAGER.isFriend(entitylivingbaseIn.getName())) {
                if (ModulePlayerChams.fvisible.getValue()) {
                    this.mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
                }
            } else if (ModulePlayerChams.visible.getValue()) {
                this.mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
            }
            if (ModulePlayerChams.enchanted.getValue()) {
                mc.getTextureManager().bindTexture(RES_ITEM_GLINT);
                GL11.glTexCoord3d((double)1.0, (double)1.0, (double)1.0);
                GL11.glEnable((int)3553);
                GL11.glBlendFunc((int)768, (int)771);
                GL11.glColor4f((float)((float)ModulePlayerChams.enchantedColor.getValue().getRed() / 255.0f), (float)((float)ModulePlayerChams.enchantedColor.getValue().getGreen() / 255.0f), (float)((float)ModulePlayerChams.enchantedColor.getValue().getBlue() / 255.0f), (float)((float)ModulePlayerChams.enchantedColor.getValue().getAlpha() / 255.0f));
                this.mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            }
            GL11.glEnable((int)3042);
            GL11.glEnable((int)2896);
            GL11.glEnable((int)3553);
            GL11.glEnable((int)3008);
            GL11.glPopAttrib();
            if (ModulePlayerChams.outline.getValue()) {
                if (ModulePlayerChams.outlineMode.getValue().equals((Object)ModulePlayerChams.outlineModes.Wire)) {
                    GL11.glPushAttrib((int)1048575);
                    GL11.glPolygonMode((int)1032, (int)6913);
                    GL11.glDisable((int)3008);
                    GL11.glDisable((int)3553);
                    GL11.glDisable((int)2896);
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)770, (int)771);
                    GL11.glLineWidth((float)ModulePlayerChams.width.getValue().floatValue());
                    GL11.glEnable((int)2960);
                    GL11.glDisable((int)2929);
                    GL11.glDepthMask((boolean)false);
                    GL11.glEnable((int)10754);
                    if (Europa.FRIEND_MANAGER.isFriend(entitylivingbaseIn.getName()) && !ModulePlayerChams.syncFriend.getValue()) {
                        GL11.glColor4f((float)((float)ModulePlayerChams.foutColor.getRed() / 255.0f), (float)((float)ModulePlayerChams.foutColor.getGreen() / 255.0f), (float)((float)ModulePlayerChams.foutColor.getBlue() / 255.0f), (float)1.0f);
                    } else {
                        GL11.glColor4f((float)((float)ModulePlayerChams.outColor.getRed() / 255.0f), (float)((float)ModulePlayerChams.outColor.getGreen() / 255.0f), (float)((float)ModulePlayerChams.outColor.getBlue() / 255.0f), (float)1.0f);
                    }
                    this.mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
                    GL11.glEnable((int)2929);
                    GL11.glDepthMask((boolean)true);
                    this.mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
                    GL11.glEnable((int)3042);
                    GL11.glEnable((int)2896);
                    GL11.glEnable((int)3553);
                    GL11.glEnable((int)3008);
                    GL11.glPopAttrib();
                } else {
                    if (Europa.FRIEND_MANAGER.isFriend(entitylivingbaseIn.getName()) && !ModulePlayerChams.syncFriend.getValue()) {
                        OutlineUtils.setColor(ModulePlayerChams.foutColor);
                    } else {
                        OutlineUtils.setColor(ModulePlayerChams.outColor);
                    }
                    OutlineUtils.renderOne(ModulePlayerChams.width.getValue().floatValue());
                    this.mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
                    OutlineUtils.renderTwo();
                    this.mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
                    OutlineUtils.renderThree();
                    OutlineUtils.renderFour();
                    if (Europa.FRIEND_MANAGER.isFriend(entitylivingbaseIn.getName()) && !ModulePlayerChams.syncFriend.getValue()) {
                        OutlineUtils.setColor(ModulePlayerChams.foutColor);
                    } else {
                        OutlineUtils.setColor(ModulePlayerChams.outColor);
                    }
                    this.mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
                    OutlineUtils.renderFive();
                    OutlineUtils.setColor(Color.WHITE);
                }
            }
            if (Europa.FRIEND_MANAGER.isFriend(entitylivingbaseIn.getName())) {
                if (!ModulePlayerChams.fvisible.getValue()) {
                    this.mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
                }
            } else if (!ModulePlayerChams.visible.getValue()) {
                this.mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
            }
        }
        if (entitylivingbaseIn == ModulePopChams.player && ModulePopChams.player != null) {
            GL11.glPushAttrib((int)1048575);
            RenderUtils.prepareGL();
            GL11.glEnable((int)2881);
            GL11.glEnable((int)2848);
            GL11.glColor4f((float)((float)ModulePopChams.color.getRed() / 255.0f), (float)((float)ModulePopChams.color.getGreen() / 255.0f), (float)((float)ModulePopChams.color.getBlue() / 255.0f), (float)ModulePopChams.opacity);
            GL11.glPolygonMode((int)1032, (int)6914);
            this.mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
            GL11.glColor4f((float)((float)ModulePopChams.outlineColor.getRed() / 255.0f), (float)((float)ModulePopChams.outlineColor.getGreen() / 255.0f), (float)((float)ModulePopChams.outlineColor.getBlue() / 255.0f), (float)ModulePopChams.opacity);
            GL11.glPolygonMode((int)1032, (int)6913);
            this.mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
            GL11.glPolygonMode((int)1032, (int)6914);
            GL11.glPopAttrib();
            RenderUtils.releaseGL();
        }
        if (entitylivingbaseIn == ModuleKillEffects.player && ModuleKillEffects.player != null) {
            GL11.glPushAttrib((int)1048575);
            RenderUtils.prepareGL();
            GL11.glEnable((int)2881);
            GL11.glEnable((int)2848);
            GL11.glColor4f((float)((float)ModuleKillEffects.chamColor.getValue().getRed() / 255.0f), (float)((float)ModuleKillEffects.chamColor.getValue().getGreen() / 255.0f), (float)((float)ModuleKillEffects.chamColor.getValue().getBlue() / 255.0f), (float)ModuleKillEffects.opacity);
            GL11.glPolygonMode((int)1032, (int)6914);
            this.mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
            GL11.glColor4f((float)((float)ModuleKillEffects.chamColor.getValue().getRed() / 255.0f), (float)((float)ModuleKillEffects.chamColor.getValue().getGreen() / 255.0f), (float)((float)ModuleKillEffects.chamColor.getValue().getBlue() / 255.0f), (float)ModuleKillEffects.opacity);
            GL11.glPolygonMode((int)1032, (int)6913);
            this.mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
            GL11.glPolygonMode((int)1032, (int)6914);
            GL11.glPopAttrib();
            RenderUtils.releaseGL();
        }
        if (!(Europa.getModuleManager().isModuleEnabled("PlayerChams") && isPlayer || entitylivingbaseIn == ModulePopChams.player)) {
            this.mainModel.render(entitylivingbaseIn, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, scaleFactor);
        }
    }
}

