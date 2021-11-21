/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiDisconnected
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.multiplayer.GuiConnecting
 *  net.minecraft.client.multiplayer.ServerData
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraftforge.client.event.GuiOpenEvent
 *  net.minecraftforge.event.world.WorldEvent$Load
 *  net.minecraftforge.event.world.WorldEvent$Unload
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.europa.client.modules.misc;

import com.europa.api.manager.module.Module;
import com.europa.api.manager.module.ModuleCategory;
import com.europa.api.manager.value.impl.ValueNumber;
import java.io.IOException;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModuleAutoReconnect
extends Module {
    public static ServerData lastConnectedServer;
    public static boolean hasAutoLogged;
    public static ValueNumber delay;

    public ModuleAutoReconnect() {
        super("AutoReconnect", "Auto Reconnect", "Autoamtically reconnects you when you get disconnected.", ModuleCategory.MISC);
    }

    public void updateLastConnectedServer() {
        ServerData data = mc.getCurrentServerData();
        if (data == null) {
            return;
        }
        lastConnectedServer = data;
    }

    /*
     * WARNING - void declaration
     */
    @SubscribeEvent
    public void onGuiOpened(GuiOpenEvent guiOpenEvent) {
        GuiDisconnectedOverride guiDisconnectedOverride;
        void event;
        block23: {
            block22: {
                void v0;
                try {
                    if (hasAutoLogged) {
                        return;
                    }
                    v0 = event;
                }
                catch (Exception exception) {
                    return;
                }
                GuiScreen guiScreen = v0.getGui();
                if (guiScreen instanceof GuiDisconnected) break block22;
                return;
            }
            void v2 = event;
            GuiScreen guiScreen = v2.getGui();
            if (!(guiScreen instanceof GuiDisconnectedOverride)) break block23;
            return;
        }
        ModuleAutoReconnect moduleAutoReconnect = this;
        moduleAutoReconnect.updateLastConnectedServer();
        void v5 = event;
        GuiScreen guiScreen = v5.getGui();
        GuiDisconnected disconnected = (GuiDisconnected)guiScreen;
        void v7 = event;
        GuiDisconnectedOverride guiDisconnectedOverride2 = guiDisconnectedOverride;
        GuiDisconnectedOverride guiDisconnectedOverride3 = guiDisconnectedOverride;
        GuiScreen guiScreen2 = disconnected.parentScreen;
        String string = "connect.failed";
        ITextComponent iTextComponent = disconnected.message;
        String string2 = disconnected.reason;
        ValueNumber valueNumber = delay;
        Number number = valueNumber.getValue();
        double d = number.doubleValue();
        guiDisconnectedOverride2(guiScreen2, string, iTextComponent, string2, d);
        v7.setGui((GuiScreen)guiDisconnectedOverride3);
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load load) {
        hasAutoLogged = false;
    }

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload unload) {
        this.updateLastConnectedServer();
    }

    public static ServerData access$000() {
        return lastConnectedServer;
    }

    static {
        delay = new ValueNumber("Delay", "Delay", "", Double.longBitsToDouble(Double.doubleToLongBits(0.13648758477837689) ^ 0x7FD5786CD87771A9L), Double.longBitsToDouble(Double.doubleToLongBits(3.602629809100103) ^ 0x7FECD22F93CD6FF5L), Double.longBitsToDouble(Double.doubleToLongBits(0.2567363502747789) ^ 0x7FEE6E5E4D07F90EL));
    }

    public static class GuiDisconnectedOverride
    extends GuiDisconnected {
        public GuiScreen parent;
        public ITextComponent message;
        public long reconnectTime;
        public GuiButton reconnectButton = null;

        public GuiDisconnectedOverride(GuiScreen screen, String reasonLocalizationKey, ITextComponent chatComp, String reason, double delay) {
            super(screen, reasonLocalizationKey, chatComp);
            this.parent = screen;
            this.message = chatComp;
            this.reconnectTime = System.currentTimeMillis() + (long)(delay * Double.longBitsToDouble(Double.doubleToLongBits(0.0017555392050407437) ^ 0x7FD38343DE3D5F29L));
        }

        public long getTimeUntilReconnect() {
            return this.reconnectTime - System.currentTimeMillis();
        }

        public double getTimeUntilReconnectInSeconds() {
            return (double)this.getTimeUntilReconnect() / Double.longBitsToDouble(Double.doubleToLongBits(2.8336657960270883E-4) ^ 0x7FBDD21A3132402FL);
        }

        public String getFormattedReconnectText() {
            return String.format("Reconnecting (%.1f)...", this.getTimeUntilReconnectInSeconds());
        }

        public ServerData getLastConnectedServerData() {
            if (ModuleAutoReconnect.access$000() != null) {
                ServerData serverData = ModuleAutoReconnect.access$000();
                return serverData;
            }
            ServerData serverData = this.mc.getCurrentServerData();
            return serverData;
        }

        public void reconnect() {
            ServerData data = this.getLastConnectedServerData();
            if (data == null) {
                return;
            }
            FMLClientHandler.instance().showGuiScreen((Object)new GuiConnecting(this.parent, this.mc, data));
        }

        public void initGui() {
            super.initGui();
            List multilineMessage = this.fontRenderer.listFormattedStringToWidth(this.message.getFormattedText(), this.width - 50);
            int textHeight = multilineMessage.size() * this.fontRenderer.FONT_HEIGHT;
            if (this.getLastConnectedServerData() == null) {
                return;
            }
            this.reconnectButton = new GuiButton(this.buttonList.size(), this.width / 2 - 100, this.height / 2 + textHeight / 2 + this.fontRenderer.FONT_HEIGHT + 23, this.getFormattedReconnectText());
            this.buttonList.add(this.reconnectButton);
        }

        /*
         * WARNING - void declaration
         */
        public void actionPerformed(GuiButton guiButton) throws IOException {
            void button;
            super.actionPerformed((GuiButton)button);
            if (!button.equals((Object)this.reconnectButton)) {
                return;
            }
        }

        public void updateScreen() {
            super.updateScreen();
            if (this.reconnectButton != null) {
                this.reconnectButton.displayString = this.getFormattedReconnectText();
            }
            if (System.currentTimeMillis() < this.reconnectTime) {
                return;
            }
            this.reconnect();
        }
    }
}

