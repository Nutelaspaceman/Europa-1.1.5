/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.SoundEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.RegistryEvent$Register
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.registries.IForgeRegistryEntry
 */
package com.europa.api.utilities.sound;

import com.europa.api.utilities.sound.SoundRegistrator;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class SoundRegisterListener {
    public SoundRegisterListener() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    /*
     * WARNING - void declaration
     */
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void registerSoundEvents(RegistryEvent.Register<SoundEvent> register) {
        void event;
        event.getRegistry().registerAll((IForgeRegistryEntry[])new SoundEvent[]{SoundRegistrator.DOUBLE_KILL});
    }
}

