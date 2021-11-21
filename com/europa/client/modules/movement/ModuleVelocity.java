/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.network.play.server.SPacketEntityVelocity
 *  net.minecraft.network.play.server.SPacketExplosion
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.europa.client.modules.movement;

import com.europa.api.manager.event.impl.network.EventPacket;
import com.europa.api.manager.event.impl.player.EventPush;
import com.europa.api.manager.module.Module;
import com.europa.api.manager.module.ModuleCategory;
import com.europa.api.manager.value.impl.ValueBoolean;
import com.europa.api.manager.value.impl.ValueNumber;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModuleVelocity
extends Module {
    public static ValueBoolean noPush = new ValueBoolean("NoPush", "NoPush", "", false);
    public static ValueNumber horizontal = new ValueNumber("Horizontal", "Horizontal", "", 0, 0, 100);
    public static ValueNumber vertical = new ValueNumber("Vertical", "Vertical", "", 0, 0, 100);

    public ModuleVelocity() {
        super("Velocity", "Velocity", "Stops knockback", ModuleCategory.MOVEMENT);
    }

    /*
     * WARNING - void declaration
     */
    @SubscribeEvent
    public void onReceive(EventPacket.Receive receive) {
        SPacketEntityVelocity sPacketEntityVelocity;
        void event;
        if (ModuleVelocity.mc.player == null || ModuleVelocity.mc.world == null) {
            return;
        }
        if (event.getPacket() instanceof SPacketEntityVelocity && (sPacketEntityVelocity = (SPacketEntityVelocity)event.getPacket()).getEntityID() == ModuleVelocity.mc.player.entityId) {
            if (horizontal.getValue().floatValue() == Float.intBitsToFloat(Float.floatToIntBits(6.2763993E37f) ^ 0x7E3CDF9F) && vertical.getValue().floatValue() == Float.intBitsToFloat(Float.floatToIntBits(1.843314E38f) ^ 0x7F0AACF0)) {
                event.setCancelled(true);
            } else {
                sPacketEntityVelocity.motionX = (int)((float)sPacketEntityVelocity.motionX * horizontal.getValue().floatValue());
                sPacketEntityVelocity.motionY = (int)((float)sPacketEntityVelocity.motionY * vertical.getValue().floatValue());
                sPacketEntityVelocity.motionZ = (int)((float)sPacketEntityVelocity.motionZ * horizontal.getValue().floatValue());
            }
        }
        if (event.getPacket() instanceof SPacketExplosion) {
            SPacketExplosion sPacketExplosion = (SPacketExplosion)event.getPacket();
            if (horizontal.getValue().floatValue() == Float.intBitsToFloat(Float.floatToIntBits(3.3063597E38f) ^ 0x7F78BE2C) && vertical.getValue().floatValue() == Float.intBitsToFloat(Float.floatToIntBits(5.0592924E37f) ^ 0x7E183F5F)) {
                event.setCancelled(true);
            } else {
                sPacketExplosion.motionX *= horizontal.getValue().floatValue();
                sPacketExplosion.motionY *= vertical.getValue().floatValue();
                sPacketExplosion.motionZ *= horizontal.getValue().floatValue();
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    @SubscribeEvent
    public void onPush(EventPush eventPush) {
        block0: {
            void event;
            if (!noPush.getValue()) break block0;
            event.setCancelled(true);
        }
    }
}

