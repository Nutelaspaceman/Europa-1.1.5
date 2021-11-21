/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiNewChat
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.client.CPacketChatMessage
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 */
package com.europa.api.manager.misc;

import com.europa.api.utilities.render.RainbowUtils;
import com.europa.client.modules.client.ModuleColor;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class ChatManager {
    public Minecraft mc = Minecraft.getMinecraft();
    public GuiNewChat gameChatGUI;
    public static String prefix = (Object)ModuleColor.getBracketColour() + " [" + (Object)ModuleColor.getTextColor() + "Europa" + (Object)ModuleColor.getBracketColour() + "] " + (Object)ModuleColor.getTextColor();
    public static ChatManager INSTANCE;

    public ChatManager() {
        INSTANCE = this;
    }

    /*
     * WARNING - void declaration
     */
    public void printChatMessage(String string) {
        void message;
        if (Minecraft.getMinecraft().player == null) {
            return;
        }
        if (this.gameChatGUI == null) {
            this.gameChatGUI = Minecraft.getMinecraft().ingameGUI.getChatGUI();
        }
        this.gameChatGUI.printChatMessage((ITextComponent)new TextComponentString((String)message));
    }

    /*
     * WARNING - void declaration
     */
    public void sendChatMessage(String string) {
        void message;
        if (Minecraft.getMinecraft().player == null) {
            return;
        }
        Minecraft.getMinecraft().player.connection.sendPacket((Packet)new CPacketChatMessage((String)message));
    }

    public static void sendRawMessage(String string) {
        String message;
        Minecraft.getMinecraft().player.sendMessage((ITextComponent)new TextComponentString(message));
    }

    public static void printChatNotifyClient(String string) {
        String message;
        if (ModuleColor.prefixMode.getValue().equals((Object)ModuleColor.prefixModes.Rainbow) || ModuleColor.prefixMode.getValue().equals((Object)ModuleColor.prefixModes.Gradient)) {
            INSTANCE.printChatMessage((ModuleColor.prefix.getValue() ? "\u00a7+" + (Object)ChatFormatting.GRAY + " [" + (Object)ModuleColor.getTextColor() + "Europa" + (Object)ChatFormatting.GRAY + "] " : "") + "\u00a7r" + (Object)ChatFormatting.RESET + message);
        } else {
            INSTANCE.printChatMessage((ModuleColor.prefix.getValue() ? (Object)ChatFormatting.GRAY + " [" + (Object)ModuleColor.getTextColor() + "Europa" + (Object)ChatFormatting.GRAY + "] " : "") + (Object)ChatFormatting.RESET + message);
        }
    }

    public static void printTextComponentMessage(TextComponentString textComponentString) {
        TextComponentString message;
        if (Minecraft.getMinecraft().player == null) {
            return;
        }
        if (ChatManager.INSTANCE.gameChatGUI == null) {
            ChatManager.INSTANCE.gameChatGUI = Minecraft.getMinecraft().ingameGUI.getChatGUI();
        }
        ChatManager.INSTANCE.gameChatGUI.printChatMessage((ITextComponent)message);
    }

    /*
     * WARNING - void declaration
     */
    public static void sendClientMessage(String string, int n) {
        void id;
        String string2;
        if (Minecraft.getMinecraft().player == null) {
            return;
        }
        TextComponentString component = ModuleColor.prefixMode.getValue().equals((Object)ModuleColor.prefixModes.Rainbow) || ModuleColor.prefixMode.getValue().equals((Object)ModuleColor.prefixModes.Gradient) ? new TextComponentString((ModuleColor.prefix.getValue() ? "\u00a7+" + (Object)ChatFormatting.GRAY + " [" + (Object)ModuleColor.getTextColor() + "Europa" + (Object)ChatFormatting.GRAY + "] " : "") + "\u00a7r" + (Object)ChatFormatting.RESET + string2) : new TextComponentString((ModuleColor.prefix.getValue() ? (Object)ModuleColor.getBracketColour() + " [" + (Object)ModuleColor.getTextColor() + "Europa" + (Object)ModuleColor.getBracketColour() + "] " : "") + (Object)ChatFormatting.RESET + string2);
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)component, (int)id);
    }

    /*
     * WARNING - void declaration
     */
    public void drawRainbowString(String string, float f, float f2, boolean bl) {
        void text;
        int currentWidth = 0;
        boolean shouldRainbow = true;
        boolean shouldContinue = false;
        int[] counterChing = new int[]{1};
        for (int i = 0; i < text.length(); ++i) {
            void shadow;
            void y;
            void x;
            Color color = ModuleColor.prefixMode.getValue().equals((Object)ModuleColor.prefixModes.Rainbow) ? RainbowUtils.anyRainbowColor(counterChing[0] * 150, 180, 255) : RainbowUtils.getGradientOffset(new Color(ModuleColor.prefixStart.getValue().getRed(), ModuleColor.prefixStart.getValue().getGreen(), ModuleColor.prefixStart.getValue().getBlue()), new Color(ModuleColor.prefixEnd.getValue().getRed(), ModuleColor.prefixEnd.getValue().getGreen(), ModuleColor.prefixEnd.getValue().getBlue()), Math.abs(((float)(System.currentTimeMillis() % ((long)-1919065669 ^ 0xFFFFFFFF8D9D666BL)) / Float.intBitsToFloat(Float.floatToIntBits(4.989551E-4f) ^ 0x7E78CC4F) + Float.intBitsToFloat(Float.floatToIntBits(0.4183691f) ^ 0x7F76347A) / (float)(counterChing[0] * 2 + 10) * Float.intBitsToFloat(Float.floatToIntBits(0.9570142f) ^ 0x7F74FEE2)) % Float.intBitsToFloat(Float.floatToIntBits(0.21266626f) ^ 0x7E59C52F) - Float.intBitsToFloat(Float.floatToIntBits(5.415172f) ^ 0x7F2D4917)));
            char currentChar = text.charAt(i);
            char nextChar = text.charAt(MathHelper.clamp((int)(i + 1), (int)0, (int)(text.length() - 1)));
            if ((String.valueOf(currentChar) + nextChar).equals("\u00a7r")) {
                shouldRainbow = false;
            } else if ((String.valueOf(currentChar) + nextChar).equals("\u00a7+")) {
                shouldRainbow = true;
            }
            if (shouldContinue) {
                shouldContinue = false;
                continue;
            }
            if ((String.valueOf(currentChar) + nextChar).equals("\u00a7r")) {
                String escapeString = text.substring(i);
                this.drawString(escapeString, (float)(x + (float)currentWidth), (float)y, Color.WHITE.getRGB(), (boolean)shadow);
                break;
            }
            this.drawString(String.valueOf(currentChar).equals("\u00a7") ? "" : String.valueOf(currentChar), (float)(x + (float)currentWidth), (float)y, shouldRainbow ? color.getRGB() : Color.WHITE.getRGB(), (boolean)shadow);
            if (String.valueOf(currentChar).equals("\u00a7")) {
                shouldContinue = true;
            }
            currentWidth += this.getStringWidth(String.valueOf(currentChar));
            if (String.valueOf(currentChar).equals(" ")) continue;
            counterChing[0] = counterChing[0] + 1;
        }
    }

    /*
     * WARNING - void declaration
     */
    public int getStringWidth(String string) {
        void text;
        return this.mc.fontRenderer.getStringWidth((String)text);
    }

    /*
     * WARNING - void declaration
     */
    public float drawString(String string, float f, float f2, int n, boolean bl) {
        void shadow;
        void color;
        void y;
        void x;
        void text;
        this.mc.fontRenderer.drawString((String)text, (float)x, (float)y, (int)color, (boolean)shadow);
        return (float)x;
    }
}

