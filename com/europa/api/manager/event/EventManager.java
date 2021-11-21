/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.network.play.server.SPacketPlayerListItem
 *  net.minecraft.network.play.server.SPacketPlayerListItem$Action
 *  net.minecraft.network.play.server.SPacketPlayerListItem$AddPlayerData
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.Event
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  org.apache.commons.io.IOUtils
 */
package com.europa.api.manager.event;

import com.europa.api.manager.command.CommandManager;
import com.europa.api.manager.event.impl.network.EventPacket;
import com.europa.api.manager.event.impl.network.EventPlayerJoin;
import com.europa.api.manager.event.impl.network.EventPlayerLeave;
import com.google.common.collect.Maps;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.SPacketPlayerListItem;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class EventManager {
    public static Minecraft mc = Minecraft.getMinecraft();
    public int colorRGBEffectRed;
    public int colorRGBEffectGreen;
    public int colorRGBEffectBlue;
    public CommandManager commandManager = new CommandManager();
    public Map<String, String> uuidNameCache = Maps.newConcurrentMap();

    public EventManager() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    /*
     * WARNING - void declaration
     */
    @SubscribeEvent
    public void onWorldRender(RenderWorldLastEvent renderWorldLastEvent) {
        void event;
        if (event.isCanceled()) {
            return;
        }
        float[] tick_color = new float[]{(float)(System.currentTimeMillis() % ((long)1483066307 ^ 0x5865E6C3L)) / Float.intBitsToFloat(Float.floatToIntBits(4.213687E-5f) ^ 0x7E04BC1F)};
        int colorInterpolated = Color.HSBtoRGB(tick_color[0], Float.intBitsToFloat(Float.floatToIntBits(12.152629f) ^ 0x7EC2712B), Float.intBitsToFloat(Float.floatToIntBits(4.6224623f) ^ 0x7F13EB36));
        this.colorRGBEffectRed = colorInterpolated >> 16 & 0xFF;
        this.colorRGBEffectGreen = colorInterpolated >> 8 & 0xFF;
        this.colorRGBEffectBlue = colorInterpolated & 0xFF;
    }

    /*
     * WARNING - void declaration
     */
    @SubscribeEvent
    public void onReceive(EventPacket.Receive receive) {
        void event;
        if (event.getPacket() instanceof SPacketPlayerListItem) {
            SPacketPlayerListItem packet = (SPacketPlayerListItem)event.getPacket();
            if (packet.getAction() == SPacketPlayerListItem.Action.ADD_PLAYER) {
                for (SPacketPlayerListItem.AddPlayerData playerData : packet.getEntries()) {
                    if (playerData.getProfile().getId() == EventManager.mc.session.getProfile().getId()) continue;
                    new Thread(() -> this.lambda$onReceive$0(playerData)).start();
                }
            }
            if (packet.getAction() == SPacketPlayerListItem.Action.REMOVE_PLAYER) {
                for (SPacketPlayerListItem.AddPlayerData playerData : packet.getEntries()) {
                    if (playerData.getProfile().getId() == EventManager.mc.session.getProfile().getId()) continue;
                    new Thread(() -> this.lambda$onReceive$1(playerData)).start();
                }
            }
        }
    }

    public String resolveName(String string) {
        block21: {
            URL uRL;
            String uuid;
            if (this.uuidNameCache.containsKey(uuid = uuid.replace("-", ""))) {
                return this.uuidNameCache.get(uuid);
            }
            String url = "https://api.mojang.com/user/profiles/" + uuid + "/names";
            URL uRL2 = uRL;
            URL uRL3 = uRL;
            String string2 = url;
            uRL2(string2);
            String string3 = IOUtils.toString((URL)uRL3);
            String nameJson = string3;
            if (nameJson == null) break block21;
            String string4 = nameJson;
            int n = string4.length();
            if (n <= 0) break block21;
            String string5 = nameJson;
            Object object = JSONValue.parseWithException(string5);
            JSONArray jsonArray = (JSONArray)object;
            if (jsonArray == null) break block21;
            JSONArray jSONArray = jsonArray;
            JSONArray jSONArray2 = jsonArray;
            int n2 = jSONArray2.size();
            int n3 = n2 - 1;
            Object e = jSONArray.get(n3);
            JSONObject latestName = (JSONObject)e;
            if (latestName == null) break block21;
            JSONObject jSONObject = latestName;
            String string6 = "name";
            Object v = jSONObject.get(string6);
            try {
                return v.toString();
            }
            catch (IOException | ParseException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public int[] getRGB() {
        return new int[]{this.colorRGBEffectRed, this.colorRGBEffectGreen, this.colorRGBEffectBlue};
    }

    /*
     * WARNING - void declaration
     */
    public static Vec3d getInterpolatedAmount(Entity entity, double d) {
        void ticks;
        Entity entity2;
        return EventManager.getInterpolatedAmount(entity2, (double)ticks, (double)ticks, (double)ticks);
    }

    /*
     * WARNING - void declaration
     */
    public static Vec3d getInterpolatedAmount(Entity entity, double d, double d2, double d3) {
        void z;
        void y;
        void x;
        Entity entity2;
        return new Vec3d((entity2.posX - entity2.lastTickPosX) * x, (entity2.posY - entity2.lastTickPosY) * y, (entity2.posZ - entity2.lastTickPosZ) * z);
    }

    /*
     * WARNING - void declaration
     */
    public void lambda$onReceive$1(SPacketPlayerListItem.AddPlayerData addPlayerData) {
        void playerData;
        UUID id = playerData.getProfile().getId();
        EntityPlayer entity = EventManager.mc.world.getPlayerEntityByUUID(id);
        String name = this.resolveName(playerData.getProfile().getId().toString());
        if (name != null) {
            if (EventManager.mc.player != null) {
                if (EventManager.mc.player.ticksExisted >= 1000) {
                    MinecraftForge.EVENT_BUS.post((Event)new EventPlayerLeave(name, id, entity));
                }
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    public void lambda$onReceive$0(SPacketPlayerListItem.AddPlayerData addPlayerData) {
        void playerData;
        UUID id = playerData.getProfile().getId();
        String name = this.resolveName(playerData.getProfile().getId().toString());
        if (name != null && EventManager.mc.player != null && EventManager.mc.player.ticksExisted >= 1000) {
            MinecraftForge.EVENT_BUS.post((Event)new EventPlayerJoin(name, id));
        }
    }
}

