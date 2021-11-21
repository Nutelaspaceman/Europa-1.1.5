/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.BlockPos
 */
package com.europa.api.manager.event.impl.player;

import com.europa.api.manager.event.Event;
import net.minecraft.util.math.BlockPos;

public class EventPlayerDestroyBlock
extends Event {
    public BlockPos pos;

    public EventPlayerDestroyBlock(BlockPos blockPos) {
        this.pos = blockPos;
    }

    public BlockPos getBlockPos() {
        return this.pos;
    }

    /*
     * WARNING - void declaration
     */
    public void setPos(BlockPos blockPos) {
        void pos;
        this.pos = pos;
    }
}

