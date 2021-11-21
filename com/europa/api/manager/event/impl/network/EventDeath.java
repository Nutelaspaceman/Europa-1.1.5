/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package com.europa.api.manager.event.impl.network;

import com.europa.api.manager.event.Event;
import net.minecraft.entity.player.EntityPlayer;

public class EventDeath
extends Event {
    public EntityPlayer player;

    public EventDeath(EntityPlayer player) {
        this.player = player;
    }
}

