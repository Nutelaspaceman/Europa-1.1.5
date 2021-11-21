/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.MoverType
 */
package com.europa.api.manager.event.impl.player;

import com.europa.api.manager.event.Event;
import net.minecraft.entity.MoverType;

public class EventPlayerMove
extends Event {
    public MoverType type;
    public double x;
    public double y;
    public double z;

    public EventPlayerMove(MoverType type, double x, double y, double z) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /*
     * WARNING - void declaration
     */
    public void setType(MoverType moverType) {
        void type;
        this.type = type;
    }

    /*
     * WARNING - void declaration
     */
    public void setX(double d) {
        void x;
        this.x = x;
    }

    /*
     * WARNING - void declaration
     */
    public void setY(double d) {
        void y;
        this.y = y;
    }

    /*
     * WARNING - void declaration
     */
    public void setZ(double d) {
        void z;
        this.z = z;
    }

    public MoverType getType() {
        return this.type;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }
}

