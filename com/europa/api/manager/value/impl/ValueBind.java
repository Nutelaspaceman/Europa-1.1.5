/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package com.europa.api.manager.value.impl;

import com.europa.api.manager.event.impl.client.EventClient;
import com.europa.api.manager.value.Value;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;

public class ValueBind
extends Value {
    public int value;

    public ValueBind(String name, String tag, String description, int value) {
        super(name, tag, description);
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    /*
     * WARNING - void declaration
     */
    public void setValue(int n) {
        void value;
        MinecraftForge.EVENT_BUS.post((Event)new EventClient(this));
        this.value = value;
    }
}

