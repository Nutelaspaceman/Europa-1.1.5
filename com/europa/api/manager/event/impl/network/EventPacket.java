/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.Packet
 */
package com.europa.api.manager.event.impl.network;

import com.europa.api.manager.event.Event;
import net.minecraft.network.Packet;

public class EventPacket
extends Event {
    public Packet packet;

    public EventPacket(Event.Stage stage, Packet packet) {
        super(stage);
        this.packet = packet;
    }

    /*
     * WARNING - void declaration
     */
    public void setPacket(Packet packet) {
        void packet2;
        this.packet = packet2;
    }

    public Packet getPacket() {
        return this.packet;
    }

    public static class Receive
    extends EventPacket {
        public Receive(Event.Stage stage, Packet packet) {
            super(stage, packet);
        }
    }

    public static class Send
    extends EventPacket {
        public Send(Event.Stage stage, Packet packet) {
            super(stage, packet);
        }
    }
}

