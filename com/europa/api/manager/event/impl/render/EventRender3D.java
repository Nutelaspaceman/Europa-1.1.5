/*
 * Decompiled with CFR 0.150.
 */
package com.europa.api.manager.event.impl.render;

import com.europa.api.manager.event.Event;

public class EventRender3D
extends Event {
    public float partialTicks;

    public EventRender3D(float partialTicks) {
        this.partialTicks = partialTicks;
    }

    /*
     * WARNING - void declaration
     */
    public void setPartialTicks(float f) {
        void partialTicks;
        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {
        return this.partialTicks;
    }
}

