/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.network.NetworkPlayerInfo
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.network.play.server.SPacketChat
 *  net.minecraft.scoreboard.ScorePlayerTeam
 *  net.minecraft.scoreboard.Team
 *  net.minecraft.util.text.ChatType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Text
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.europa.client.modules.client;

import com.europa.Europa;
import com.europa.api.manager.event.impl.network.EventPacket;
import com.europa.api.manager.misc.ChatManager;
import com.europa.api.manager.module.Module;
import com.europa.api.manager.module.ModuleCategory;
import com.europa.api.manager.value.impl.ValueBoolean;
import com.europa.api.manager.value.impl.ValueString;
import java.util.ListIterator;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.text.ChatType;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModuleStreamerMode
extends Module {
    public static ValueBoolean hideYou = new ValueBoolean("HideIGN", "HideIGN", "", false);
    public static ValueString yourName = new ValueString("YourName", "YourName", "", "You");
    public static ValueBoolean hideName = new ValueBoolean("HideOthers", "HideOthers", "", false);
    public static ValueString otherName = new ValueString("OthersName", "OthersName", "", "Enemy");
    public static ValueBoolean hideF3 = new ValueBoolean("HideF3", "HideF3", "", false);

    public ModuleStreamerMode() {
        super("StreamerMode", "Streamer Mode", "", ModuleCategory.CLIENT);
    }

    /*
     * WARNING - void declaration
     */
    @SubscribeEvent
    public void onReceive(EventPacket.Receive receive) {
        block1: {
            SPacketChat packet;
            void event;
            if (!(event.getPacket() instanceof SPacketChat) || (packet = (SPacketChat)event.getPacket()).getType() == ChatType.GAME_INFO) break block1;
            if (this.getChatNames(packet.getChatComponent().getFormattedText(), packet.getChatComponent().getUnformattedText())) {
                event.setCancelled(true);
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    public boolean getChatNames(String string, String string2) {
        void message;
        String out = message;
        if (hideName.getValue()) {
            if (ModuleStreamerMode.mc.player == null) {
                return false;
            }
            for (Object o : ModuleStreamerMode.mc.world.playerEntities) {
                if (!(o instanceof EntityPlayer)) continue;
                if (o == ModuleStreamerMode.mc.player) continue;
                EntityPlayer ent = (EntityPlayer)o;
                if (!Europa.FRIEND_MANAGER.isFriend(ent.getName())) {
                    if (!out.contains(ent.getName())) continue;
                    out = out.replaceAll(ent.getName(), otherName.getValue());
                    continue;
                }
                if (!Europa.FRIEND_MANAGER.isFriend(ent.getName()) || !out.contains(ent.getName())) continue;
                out = out.replaceAll(ent.getName(), "Friend");
            }
        }
        if (hideYou.getValue()) {
            if (ModuleStreamerMode.mc.player == null) {
                return false;
            }
            out = out.replace(ModuleStreamerMode.mc.player.getName(), yourName.getValue());
        }
        ChatManager.sendRawMessage(out);
        return true;
    }

    /*
     * WARNING - void declaration
     */
    @SubscribeEvent
    public void renderOverlayEvent(RenderGameOverlayEvent.Text text) {
        void event;
        if (FMLClientHandler.instance().getClient().player.capabilities.isCreativeMode) {
            return;
        }
        if (!hideF3.getValue()) {
            return;
        }
        ListIterator it = event.getLeft().listIterator();
        while (it.hasNext()) {
            String value = (String)it.next();
            if (!(value != null && value.startsWith("XYZ:") || value.startsWith("Looking at:") || value.startsWith("Block:"))) {
                if (!value.startsWith("Facing:")) continue;
            }
            it.remove();
        }
    }

    public static String getPlayerName(NetworkPlayerInfo networkPlayerInfo) {
        NetworkPlayerInfo networkPlayerInfoIn;
        String dname = networkPlayerInfoIn.getDisplayName() != null ? networkPlayerInfoIn.getDisplayName().getFormattedText() : ScorePlayerTeam.formatPlayerName((Team)networkPlayerInfoIn.getPlayerTeam(), (String)networkPlayerInfoIn.getGameProfile().getName());
        dname = dname.replace(ModuleStreamerMode.mc.player.getName(), yourName.getValue());
        return dname;
    }
}

