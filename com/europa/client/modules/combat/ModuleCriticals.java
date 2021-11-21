/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketPlayer$Position
 *  net.minecraft.network.play.client.CPacketUseEntity
 *  net.minecraft.network.play.client.CPacketUseEntity$Action
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.europa.client.modules.combat;

import com.europa.api.manager.event.impl.network.EventPacket;
import com.europa.api.manager.module.Module;
import com.europa.api.manager.module.ModuleCategory;
import com.europa.api.manager.value.impl.ValueEnum;
import com.europa.client.modules.combat.ModuleAutoCrystal;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModuleCriticals
extends Module {
    public static ValueEnum mode = new ValueEnum("Mode", "Mode", "The mode for the Criticals.", Modes.Packet);

    public ModuleCriticals() {
        super("Criticals", "Criticals", "Makes every one of your hits a critical.", ModuleCategory.COMBAT);
    }

    /*
     * WARNING - void declaration
     */
    @SubscribeEvent
    public void onPacketSend(EventPacket.Send send) {
        block0: {
            CPacketUseEntity packet;
            void event;
            if (!(event.getPacket() instanceof CPacketUseEntity) || (packet = (CPacketUseEntity)event.getPacket()).getAction() != CPacketUseEntity.Action.ATTACK || packet.getEntityFromWorld((World)ModuleCriticals.mc.world) instanceof EntityEnderCrystal) break block0;
            ModuleCriticals.doCritical(mode.getValue().toString());
        }
    }

    public static void doCritical(String string) {
        String mode;
        if (!mode.equals("None")) {
            if (!ModuleCriticals.mc.player.onGround) {
                return;
            }
            if (ModuleCriticals.mc.player.isInWater() || ModuleCriticals.mc.player.isInLava()) {
                return;
            }
            if (ModuleAutoCrystal.isSequential) {
                return;
            }
            if (mode.equals("Packet")) {
                ModuleCriticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(ModuleCriticals.mc.player.posX, ModuleCriticals.mc.player.posY + Double.longBitsToDouble(Double.doubleToLongBits(257.1226152887829) ^ 0x7FC011F63B72F4FFL), ModuleCriticals.mc.player.posZ, true));
                ModuleCriticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(ModuleCriticals.mc.player.posX, ModuleCriticals.mc.player.posY, ModuleCriticals.mc.player.posZ, false));
                ModuleCriticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(ModuleCriticals.mc.player.posX, ModuleCriticals.mc.player.posY + Double.longBitsToDouble(Double.doubleToLongBits(247433.60726351582) ^ 0x7FE925D8A756DFC6L), ModuleCriticals.mc.player.posZ, false));
                ModuleCriticals.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(ModuleCriticals.mc.player.posX, ModuleCriticals.mc.player.posY, ModuleCriticals.mc.player.posZ, false));
            } else if (mode.equals("Bypass")) {
                ModuleCriticals.mc.player.motionY = Double.longBitsToDouble(Double.doubleToLongBits(98.57642382264255) ^ 0x7FE13D7D80BEB8A5L);
                ModuleCriticals.mc.player.fallDistance = Float.intBitsToFloat(Float.floatToIntBits(178.62473f) ^ 0x7EFE5323);
                ModuleCriticals.mc.player.onGround = false;
            } else {
                ModuleCriticals.mc.player.jump();
                if (mode.equals("MiniJump")) {
                    ModuleCriticals.mc.player.motionY /= Double.longBitsToDouble(Double.doubleToLongBits(0.9448694671300228) ^ 0x7FEE3C5EE489FF24L);
                }
            }
        }
    }

    public static enum Modes {
        Packet,
        Jump,
        MiniJump,
        Bypass;

    }
}

