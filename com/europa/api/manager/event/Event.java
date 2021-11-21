/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package com.europa.api.manager.event;

public class Event
extends net.minecraftforge.fml.common.eventhandler.Event {
    public boolean cancelled;
    public Stage stage;

    public Event() {
    }

    public Event(Stage stage) {
        this.stage = stage;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    /*
     * WARNING - void declaration
     */
    public void setCancelled(boolean bl) {
        void cancel;
        this.cancelled = cancel;
    }

    public Stage getStage() {
        return this.stage;
    }

    public boolean isPre() {
        return this.stage == Stage.PRE;
    }

    public boolean isPost() {
        return this.stage == Stage.POST;
    }

    public static enum Stage {
        PRE,
        POST;

    }
}

