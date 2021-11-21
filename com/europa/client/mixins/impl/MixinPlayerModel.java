/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelPlayer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 */
package com.europa.client.mixins.impl;

import com.europa.client.modules.render.ModuleSkeleton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ModelPlayer.class})
public class MixinPlayerModel {
    @Inject(method={"setRotationAngles"}, at={@At(value="RETURN")})
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn, CallbackInfo callbackInfo) {
        if (Minecraft.getMinecraft().world != null && Minecraft.getMinecraft().player != null && entityIn instanceof EntityPlayer) {
            ModuleSkeleton.addEntity((EntityPlayer)entityIn, (ModelPlayer)this);
        }
    }
}

