/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 */
package com.europa.api.manager.event.impl.world;

import com.europa.api.manager.event.Event;
import net.minecraft.entity.EntityLivingBase;

public class EventChorus
extends Event {
    public EntityLivingBase entityLivingBase;
    public double x;
    public double y;
    public double z;
    public boolean successful;

    public EventChorus(EntityLivingBase entityLivingBase, double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.entityLivingBase = entityLivingBase;
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

    public boolean isSuccessful() {
        return this.successful;
    }

    /*
     * WARNING - void declaration
     */
    public void setSuccessful(boolean bl) {
        void successful;
        this.successful = successful;
    }

    public EntityLivingBase getEntityLivingBase() {
        return this.entityLivingBase;
    }

    public boolean isCancelable() {
        return true;
    }
}

