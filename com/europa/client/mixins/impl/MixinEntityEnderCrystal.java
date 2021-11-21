/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.util.DamageSource
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package com.europa.client.mixins.impl;

import com.europa.api.manager.event.impl.world.EventCrystalAttack;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={EntityEnderCrystal.class})
public class MixinEntityEnderCrystal {
    @Inject(method={"attackEntityFrom"}, at={@At(value="RETURN")}, cancellable=true)
    public void attackEntityFrom(DamageSource source, float amount, CallbackInfoReturnable<Boolean> info) {
        if (source.getTrueSource() != null) {
            EventCrystalAttack event = new EventCrystalAttack(source.getTrueSource().entityId);
            MinecraftForge.EVENT_BUS.post((Event)event);
            if (event.isCanceled()) {
                info.cancel();
            }
        }
    }
}

