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

public class ValueBoolean
extends Value {
    public boolean value;

    public ValueBoolean(String name, String tag, String description, boolean value) {
        super(name, tag, description);
        this.value = value;
    }

    public boolean getValue() {
        return this.value;
    }

    /*
     * WARNING - void declaration
     */
    public void setValue(boolean bl) {
        void value;
        MinecraftForge.EVENT_BUS.post((Event)new EventClient(this));
        this.value = value;
    }
}

