/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.RenderItem
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.network.play.server.SPacketPlayerPosLook
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.europa.client.modules.client;

import com.europa.Europa;
import com.europa.api.manager.event.impl.network.EventPacket;
import com.europa.api.manager.event.impl.render.EventRender2D;
import com.europa.api.manager.module.Module;
import com.europa.api.manager.module.ModuleCategory;
import com.europa.api.manager.value.impl.ValueBoolean;
import com.europa.api.manager.value.impl.ValueColor;
import com.europa.api.manager.value.impl.ValueEnum;
import com.europa.api.manager.value.impl.ValueNumber;
import com.europa.api.manager.value.impl.ValueString;
import com.europa.api.utilities.math.AnimationUtils;
import com.europa.api.utilities.math.TimerUtils;
import com.europa.api.utilities.render.GifLocation;
import com.europa.api.utilities.render.RainbowUtils;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModuleHud
extends Module {
    public static ValueEnum colorMode = new ValueEnum("ColorMode", "ColorMode", "", colorModes.Wave);
    public static ValueColor daColor = new ValueColor("Color", "Color", "", new Color(255, 0, 255));
    public static ValueColor gradient1 = new ValueColor("Gradient1", "Gradient1", "", new Color(255, 0, 255));
    public static ValueColor gradient2 = new ValueColor("Gradient2", "Gradient2", "", new Color(0, 0, 255));
    public static ValueNumber rainbowOffset = new ValueNumber("RainbowOffset", "RainbowOffset", "", 255, 0, 255);
    public static ValueNumber rainbowSat = new ValueNumber("RainbowSaturation", "RainbowSat", "", 255, 0, 255);
    public static ValueNumber rainbowBri = new ValueNumber("RainbowBrightness", "RainbowSat", "", 255, 0, 255);
    public static ValueEnum infoColor = new ValueEnum("InfoColor", "InfoColor", "", infoColors.Gray);
    public static ValueEnum waterMode = new ValueEnum("WaterMode", "WaterMode", "", waterModes.Default);
    public static ValueString waterString = new ValueString("WaterString", "WaterStrig", "", "Europa");
    public static ValueBoolean watermark = new ValueBoolean("Watermark", "Watermark", "", true);
    public static ValueBoolean watermarkVersion = new ValueBoolean("WatermarkVersion", "WatermarkVersion", "", true);
    public static ValueBoolean welcomer = new ValueBoolean("Welcomer", "Welcomer", "", false);
    public static ValueBoolean fps = new ValueBoolean("FPS", "FPS", "", true);
    public static ValueBoolean tps = new ValueBoolean("TPS", "TPS", "", true);
    public static ValueBoolean ping = new ValueBoolean("Ping", "Ping", "", true);
    public static ValueBoolean packetPS = new ValueBoolean("Packets", "Packets", "", false);
    public static ValueBoolean speed = new ValueBoolean("Speed", "Speed", "", true);
    public static ValueBoolean brand = new ValueBoolean("ServerBrand", "ServerBrand", "", true);
    public static ValueEnum effectHud = new ValueEnum("EffectHud", "EffectHud", "", effectHudModes.Move);
    public static ValueBoolean potionEffects = new ValueBoolean("Effects", "Effect", "", true);
    public static ValueBoolean potionSync = new ValueBoolean("EffectSync", "EffectSync", "", false);
    public static ValueBoolean coords = new ValueBoolean("Coords", "Coords", "", true);
    public static ValueBoolean netherCoords = new ValueBoolean("NetherCoords", "NetherCoords", "", true);
    public static ValueBoolean direction = new ValueBoolean("Direction", "Direction", "", true);
    public static ValueBoolean lagNotifier = new ValueBoolean("LagNotifier", "LagNotifier", "", false);
    public static ValueBoolean rubberNotifier = new ValueBoolean("RubberNotifier", "RubberNotifier", "", false);
    public static ValueBoolean armor = new ValueBoolean("Armor", "Armor", "", false);
    public static ValueBoolean arrayList = new ValueBoolean("ArrayList", "ArrayList", "", true);
    public static ValueEnum arrayRendering = new ValueEnum("Rendering", "Rendering", "", renderingModes.Up);
    public static ValueEnum ordering = new ValueEnum("Ordering", "Ordering", "", orderModes.Length);
    public int components;
    public int leftComponents;
    public int packets;
    public AnimationUtils anim = new AnimationUtils((long)-1959169653 ^ 0xFFFFFFFF8B39707FL, Float.intBitsToFloat(Float.floatToIntBits(9.571654f) ^ 0x7E99257F), Float.intBitsToFloat(Float.floatToIntBits(0.03032185f) ^ 0x7E306587));
    public TimerUtils packetTimer = new TimerUtils();
    public Color colorHud;
    public boolean rubberbanded;
    public TimerUtils serverTimer = new TimerUtils();
    public TimerUtils rubberTimer = new TimerUtils();
    public static RenderItem itemRender = Minecraft.getMinecraft().getRenderItem();

    public ModuleHud() {
        super("Hud", "Hud", "", ModuleCategory.CLIENT);
    }

    public void renderGif() {
        GifLocation gif = new GifLocation("clientname/ban", 44, 1);
        gif.update();
        mc.getTextureManager().bindTexture(gif.getTexture());
        Gui.drawModalRectWithCustomSizedTexture((int)0, (int)0, (float)Float.intBitsToFloat(Float.floatToIntBits(1.2337768E38f) ^ 0x7EB9A35B), (float)Float.intBitsToFloat(Float.floatToIntBits(8.861761E37f) ^ 0x7E855643), (int)498, (int)494, (float)Float.intBitsToFloat(Float.floatToIntBits(0.037162792f) ^ 0x7EE13803), (float)Float.intBitsToFloat(Float.floatToIntBits(0.024583342f) ^ 0x7F3E6301));
    }

    /*
     * WARNING - void declaration
     */
    @SubscribeEvent
    public void onReceive(EventPacket.Receive receive) {
        block0: {
            void event;
            this.serverTimer.reset();
            if (!(event.getPacket() instanceof SPacketPlayerPosLook)) break block0;
            this.rubberbanded = true;
            this.rubberTimer.reset();
        }
    }

    @SubscribeEvent
    public void onSend(EventPacket.Send send) {
        ++this.packets;
    }

    @Override
    public void onUpdate() {
        block0: {
            if (!this.packetTimer.hasReached((long)227554878 ^ 0xD9035D6L)) break block0;
            this.packets = 0;
            this.packetTimer.reset();
        }
    }

    /*
     * Exception decompiling
     */
    @Override
    public void onRender2D(EventRender2D var1_1) {
        /*
         * This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
         * org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
         * org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:406)
         * org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:481)
         * org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:728)
         * org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:806)
         * org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:258)
         * org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:192)
         * org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:94)
         * org.benf.cfr.reader.entities.Method.analyse(Method.java:521)
         * org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:1035)
         * org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:922)
         * org.benf.cfr.reader.Driver.doJarVersionTypes(Driver.java:253)
         * org.benf.cfr.reader.Driver.doJar(Driver.java:135)
         * org.benf.cfr.reader.CfrDriverImpl.analyse(CfrDriverImpl.java:65)
         * org.benf.cfr.reader.Main.main(Main.java:49)
         */
        throw new IllegalStateException(Decompilation failed);
    }

    public ChatFormatting getInfoColor() {
        if (infoColor.getValue().equals((Object)infoColors.Gray)) {
            return ChatFormatting.GRAY;
        }
        return ChatFormatting.WHITE;
    }

    /*
     * WARNING - void declaration
     */
    public void drawStringWithShadow(String string, float f, float f2, int n) {
        void color;
        void y;
        void x;
        void text;
        Europa.FONT_MANAGER.drawString((String)text, (float)x, (float)y, new Color((int)color));
    }

    /*
     * WARNING - void declaration
     */
    public float getStringWidth(String string) {
        void text;
        return Europa.FONT_MANAGER.getStringWidth((String)text);
    }

    /*
     * WARNING - void declaration
     */
    public void drawRainbowString(String string, float f, float f2, int n) {
        void text;
        int currentWidth = 0;
        boolean shouldRainbow = true;
        boolean shouldContinue = false;
        int[] counterChing = new int[]{1};
        for (int i = 0; i < text.length(); ++i) {
            void y;
            void x;
            Color color = RainbowUtils.anyRainbowColor(counterChing[0] * rainbowOffset.getValue().intValue(), rainbowSat.getValue().intValue(), rainbowBri.getValue().intValue());
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
                Europa.FONT_MANAGER.drawString(escapeString, (float)(x + (float)currentWidth), (float)y, Color.WHITE);
                break;
            }
            Europa.FONT_MANAGER.drawString(String.valueOf(currentChar).equals("\u00a7") ? "" : String.valueOf(currentChar), (float)(x + (float)currentWidth), (float)y, shouldRainbow ? color : Color.WHITE);
            if (String.valueOf(currentChar).equals("\u00a7")) {
                shouldContinue = true;
            }
            currentWidth = (int)((float)currentWidth + this.getStringWidth(String.valueOf(currentChar)));
            if (String.valueOf(currentChar).equals(" ")) continue;
            counterChing[0] = counterChing[0] + 1;
        }
    }

    public static String getFacing() {
        switch (MathHelper.floor((double)((double)(ModuleHud.mc.player.rotationYaw * Float.intBitsToFloat(Float.floatToIntBits(0.24909489f) ^ 0x7F7F12BB) / Float.intBitsToFloat(Float.floatToIntBits(0.07333299f) ^ 0x7E222F9B)) + Double.longBitsToDouble(Double.doubleToLongBits(29.27572401542503) ^ 0x7FDD4695D95CF8E9L))) & 7) {
            case 0: {
                return "South";
            }
            case 1: {
                return "South";
            }
            case 2: {
                return "West";
            }
            case 3: {
                return "West";
            }
            case 4: {
                return "North";
            }
            case 5: {
                return "North";
            }
            case 6: {
                return "East";
            }
            case 7: {
                return "East";
            }
        }
        return "Invalid";
    }

    public static String getTowards() {
        switch (MathHelper.floor((double)((double)(ModuleHud.mc.player.rotationYaw * Float.intBitsToFloat(Float.floatToIntBits(1.0236098f) ^ 0x7E8305A5) / Float.intBitsToFloat(Float.floatToIntBits(0.01776704f) ^ 0x7F258C2F)) + Double.longBitsToDouble(Double.doubleToLongBits(21.564711397510802) ^ 0x7FD59090ED17FCC3L))) & 7) {
            case 0: {
                return "+Z";
            }
            case 1: {
                return "+Z";
            }
            case 2: {
                return "-X";
            }
            case 3: {
                return "-X";
            }
            case 4: {
                return "-Z";
            }
            case 5: {
                return "-Z";
            }
            case 6: {
                return "+X";
            }
            case 7: {
                return "+X";
            }
        }
        return "Invalid";
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int getPing() {
        if (ModuleHud.mc.player == null) return -1;
        if (mc.getConnection() == null) return -1;
        if (mc.getConnection().getPlayerInfo(ModuleHud.mc.player.getName()) == null) {
            return -1;
        }
        ModuleHud.mc.player.getName();
        return Objects.requireNonNull(mc.getConnection().getPlayerInfo(ModuleHud.mc.player.getName())).getResponseTime();
    }

    public static String getServerBrand() {
        String s;
        if (mc.getCurrentServerData() == null) {
            s = "Vanilla";
        } else {
            EntityPlayerSP it = ModuleHud.mc.player;
            boolean n = false;
            String getServerBrand = ModuleHud.mc.player.getServerBrand();
            s = getServerBrand == null ? "Vanilla" : getServerBrand;
        }
        return s;
    }

    /*
     * WARNING - void declaration
     */
    public void lambda$onRender2D$9(Color color, Color color2, int[] arrn, Color color3, ScaledResolution scaledResolution, Module module) {
        void scaledRes;
        void color4;
        void mods;
        void color22;
        void color1;
        void m;
        String string = m.getTag() + (Object)ChatFormatting.GRAY + m.getHudInfo();
        Color gradientColor = RainbowUtils.getGradientOffset((Color)color1, (Color)color22, Math.abs(((float)(System.currentTimeMillis() % ((long)2108332745 ^ 0x7DAA9D19L)) / Float.intBitsToFloat(Float.floatToIntBits(8.5311214E-4f) ^ 0x7E25A363) + Float.intBitsToFloat(Float.floatToIntBits(0.26764295f) ^ 0x7F29087F) / (float)(mods[0] * 2 + 10) * Float.intBitsToFloat(Float.floatToIntBits(0.36572203f) ^ 0x7EBB3FEB)) % Float.intBitsToFloat(Float.floatToIntBits(0.8683099f) ^ 0x7F5E498F) - Float.intBitsToFloat(Float.floatToIntBits(183.88963f) ^ 0x7CB7E3BF)));
        Color waveColor = RainbowUtils.astolfoRainbow((Color)color4, 50, (int)(mods[0] * 2 + 10));
        if (colorMode.getValue().equals((Object)colorModes.Rainbow)) {
            this.drawStringWithShadow("\u00a7+" + m.getTag() + "\u00a7r" + (Object)ChatFormatting.GRAY + m.getHudInfo(), (float)(scaledRes.getScaledWidth() - 2) - this.getStringWidth(string), scaledRes.getScaledHeight() + mods[0] * -10 - 12, colorMode.getValue().equals((Object)colorModes.Static) ? this.colorHud.getRGB() : (colorMode.getValue().equals((Object)colorModes.Wave) ? waveColor.getRGB() : gradientColor.getRGB()));
        } else {
            this.drawStringWithShadow(string, (float)(scaledRes.getScaledWidth() - 2) - this.getStringWidth(string), scaledRes.getScaledHeight() + mods[0] * -10 - 12, colorMode.getValue().equals((Object)colorModes.Static) ? this.colorHud.getRGB() : (colorMode.getValue().equals((Object)colorModes.Wave) ? waveColor.getRGB() : gradientColor.getRGB()));
        }
        void v0 = mods;
        v0[0] = v0[0] + true;
    }

    public static String lambda$onRender2D$8(Module module) {
        Module module2;
        return module2.getTag();
    }

    /*
     * WARNING - void declaration
     */
    public void lambda$onRender2D$7(Color color, Color color2, int[] arrn, Color color3, ScaledResolution scaledResolution, Module module) {
        void scaledRes;
        void color4;
        void mods;
        void color22;
        void color1;
        void m;
        String string = m.getTag() + (Object)ChatFormatting.GRAY + m.getHudInfo();
        Color gradientColor = RainbowUtils.getGradientOffset((Color)color1, (Color)color22, Math.abs(((float)(System.currentTimeMillis() % ((long)-2097645959 ^ 0xFFFFFFFF82F871A9L)) / Float.intBitsToFloat(Float.floatToIntBits(0.0037705372f) ^ 0x7F0D1B1E) + Float.intBitsToFloat(Float.floatToIntBits(0.515287f) ^ 0x7EA3E9D9) / (float)(mods[0] * 2 + 10) * Float.intBitsToFloat(Float.floatToIntBits(0.7716851f) ^ 0x7F458D28)) % Float.intBitsToFloat(Float.floatToIntBits(0.67488766f) ^ 0x7F2CC570) - Float.intBitsToFloat(Float.floatToIntBits(10.664624f) ^ 0x7EAAA24D)));
        Color waveColor = RainbowUtils.astolfoRainbow((Color)color4, 50, (int)(mods[0] * 2 + 10));
        if (colorMode.getValue().equals((Object)colorModes.Rainbow)) {
            this.drawStringWithShadow("\u00a7+" + m.getTag() + "\u00a7r" + (Object)ChatFormatting.GRAY + m.getHudInfo(), (float)(scaledRes.getScaledWidth() - 2) - this.getStringWidth(string), scaledRes.getScaledHeight() + mods[0] * -10 - 12, colorMode.getValue().equals((Object)colorModes.Static) ? this.colorHud.getRGB() : (colorMode.getValue().equals((Object)colorModes.Wave) ? waveColor.getRGB() : gradientColor.getRGB()));
        } else {
            this.drawStringWithShadow(string, (float)(scaledRes.getScaledWidth() - 2) - this.getStringWidth(string), scaledRes.getScaledHeight() + mods[0] * -10 - 12, colorMode.getValue().equals((Object)colorModes.Static) ? this.colorHud.getRGB() : (colorMode.getValue().equals((Object)colorModes.Wave) ? waveColor.getRGB() : gradientColor.getRGB()));
        }
        void v0 = mods;
        v0[0] = v0[0] + true;
    }

    /*
     * WARNING - void declaration
     */
    public Float lambda$onRender2D$6(Module module) {
        void module2;
        return Float.valueOf(this.getStringWidth(module2.getTag() + module2.getHudInfo()) * Float.intBitsToFloat(Float.floatToIntBits(-7.326278f) ^ 0x7F6A70DF));
    }

    /*
     * WARNING - void declaration
     */
    public void lambda$onRender2D$5(Color color, Color color2, Color color3, ScaledResolution scaledResolution, int[] arrn, PotionEffect potionEffect) {
        void potCount;
        void scaledRes;
        void effect;
        void color4;
        void color22;
        void color1;
        Color gradientColor = RainbowUtils.getGradientOffset((Color)color1, (Color)color22, Math.abs(((float)(System.currentTimeMillis() % ((long)-2026668457 ^ 0xFFFFFFFF87337987L)) / Float.intBitsToFloat(Float.floatToIntBits(0.0028532243f) ^ 0x7F40FD29) + Float.intBitsToFloat(Float.floatToIntBits(0.01413565f) ^ 0x7DC79937) / (float)(this.components * 2 + 10) * Float.intBitsToFloat(Float.floatToIntBits(0.28400162f) ^ 0x7E9168A9)) % Float.intBitsToFloat(Float.floatToIntBits(0.39718184f) ^ 0x7ECB5B6B) - Float.intBitsToFloat(Float.floatToIntBits(9.475102f) ^ 0x7E979A05)));
        Color waveColor = RainbowUtils.astolfoRainbow((Color)color4, 50, this.components * 2 + 10);
        String name = I18n.format((String)effect.getPotion().getName(), (Object[])new Object[0]);
        double duration = (float)effect.getDuration() / Float.intBitsToFloat(Float.floatToIntBits(1.5897324f) ^ 0x7E5497DF);
        int amplifier = effect.getAmplifier() + 1;
        int potionColor = effect.getPotion().getLiquidColor();
        double p1 = duration % Double.longBitsToDouble(Double.doubleToLongBits(0.02308112197540173) ^ 0x7FD9A293E028480DL);
        DecimalFormat format2 = new DecimalFormat("00");
        String seconds = format2.format(p1);
        String s = name + " " + amplifier + (Object)this.getInfoColor() + " " + (int)duration / 60 + ":" + seconds;
        String sR = "\u00a7+" + name + " " + amplifier + "\u00a7r" + (Object)this.getInfoColor() + " " + (int)duration / 60 + ":" + seconds;
        if (colorMode.getValue().equals((Object)colorModes.Rainbow)) {
            this.drawStringWithShadow(sR, (float)(scaledRes.getScaledWidth() - 2) - this.getStringWidth(s), 2 + potCount[0] * 10 + (effectHud.getValue().equals((Object)effectHudModes.Move) && !ModuleHud.mc.player.getActivePotionEffects().isEmpty() ? 25 : 0), potionSync.getValue() ? (colorMode.getValue().equals((Object)colorModes.Static) ? this.colorHud.getRGB() : (colorMode.getValue().equals((Object)colorModes.Wave) ? waveColor.getRGB() : gradientColor.getRGB())) : potionColor);
        } else {
            this.drawStringWithShadow(s, (float)(scaledRes.getScaledWidth() - 2) - this.getStringWidth(s), 2 + potCount[0] * 10 + (effectHud.getValue().equals((Object)effectHudModes.Move) && !ModuleHud.mc.player.getActivePotionEffects().isEmpty() ? 25 : 0), potionSync.getValue() ? (colorMode.getValue().equals((Object)colorModes.Static) ? this.colorHud.getRGB() : (colorMode.getValue().equals((Object)colorModes.Wave) ? waveColor.getRGB() : gradientColor.getRGB())) : potionColor);
        }
        void v0 = potCount;
        v0[0] = v0[0] + true;
        ++this.components;
    }

    /*
     * WARNING - void declaration
     */
    public void lambda$onRender2D$4(Color color, Color color2, int[] arrn, Color color3, ScaledResolution scaledResolution, Module module) {
        void scaledRes;
        void color4;
        void mods;
        void color22;
        void color1;
        void m;
        String string = m.getTag() + (Object)ChatFormatting.GRAY + m.getHudInfo();
        Color gradientColor = RainbowUtils.getGradientOffset((Color)color1, (Color)color22, Math.abs(((float)(System.currentTimeMillis() % ((long)2117042502 ^ 0x7E2F8696L)) / Float.intBitsToFloat(Float.floatToIntBits(0.0010046951f) ^ 0x7EF9AFF9) + Float.intBitsToFloat(Float.floatToIntBits(0.736677f) ^ 0x7E9C96DD) / (float)(mods[0] * 2 + 10) * Float.intBitsToFloat(Float.floatToIntBits(0.5016872f) ^ 0x7F006E93)) % Float.intBitsToFloat(Float.floatToIntBits(0.3803688f) ^ 0x7EC2BFB3) - Float.intBitsToFloat(Float.floatToIntBits(4.333528f) ^ 0x7F0AAC43)));
        Color waveColor = RainbowUtils.astolfoRainbow((Color)color4, 50, (int)(mods[0] * 2 + 10));
        if (colorMode.getValue().equals((Object)colorModes.Rainbow)) {
            this.drawRainbowString("\u00a7+" + m.getTag() + "\u00a7r" + (Object)ChatFormatting.GRAY + m.getHudInfo(), (float)(scaledRes.getScaledWidth() - 2) - this.getStringWidth(string), 2 + mods[0] * 10 + (effectHud.getValue().equals((Object)effectHudModes.Move) && !ModuleHud.mc.player.getActivePotionEffects().isEmpty() ? 25 : 0), colorMode.getValue().equals((Object)colorModes.Static) ? this.colorHud.getRGB() : (colorMode.getValue().equals((Object)colorModes.Wave) ? waveColor.getRGB() : gradientColor.getRGB()));
        } else {
            this.drawStringWithShadow(string, (float)(scaledRes.getScaledWidth() - 2) - this.getStringWidth(string), 2 + mods[0] * 10 + (effectHud.getValue().equals((Object)effectHudModes.Move) && !ModuleHud.mc.player.getActivePotionEffects().isEmpty() ? 25 : 0), colorMode.getValue().equals((Object)colorModes.Static) ? this.colorHud.getRGB() : (colorMode.getValue().equals((Object)colorModes.Wave) ? waveColor.getRGB() : gradientColor.getRGB()));
        }
        void v0 = mods;
        v0[0] = v0[0] + true;
    }

    public static String lambda$onRender2D$3(Module module) {
        Module module2;
        return module2.getTag();
    }

    /*
     * WARNING - void declaration
     */
    public void lambda$onRender2D$2(Color color, Color color2, int[] arrn, Color color3, ScaledResolution scaledResolution, Module module) {
        void scaledRes;
        void color4;
        void mods;
        void color22;
        void color1;
        void m;
        String string = m.getTag() + (Object)ChatFormatting.GRAY + m.getHudInfo();
        Color gradientColor = RainbowUtils.getGradientOffset((Color)color1, (Color)color22, Math.abs(((float)(System.currentTimeMillis() % ((long)2129097541 ^ 0x7EE77495L)) / Float.intBitsToFloat(Float.floatToIntBits(8.66834E-4f) ^ 0x7E193C3F) + Float.intBitsToFloat(Float.floatToIntBits(1.5896639f) ^ 0x7E6B7A1B) / (float)(mods[0] * 2 + 10) * Float.intBitsToFloat(Float.floatToIntBits(0.21881954f) ^ 0x7E60123B)) % Float.intBitsToFloat(Float.floatToIntBits(0.7683363f) ^ 0x7F44B1B0) - Float.intBitsToFloat(Float.floatToIntBits(35.610073f) ^ 0x7D8E70B7)));
        Color waveColor = RainbowUtils.astolfoRainbow((Color)color4, 50, (int)(mods[0] * 2 + 10));
        if (colorMode.getValue().equals((Object)colorModes.Rainbow)) {
            this.drawRainbowString("\u00a7+" + m.getTag() + "\u00a7r" + (Object)ChatFormatting.GRAY + m.getHudInfo(), (float)(scaledRes.getScaledWidth() - 2) - this.getStringWidth(string), 2 + mods[0] * 10 + (effectHud.getValue().equals((Object)effectHudModes.Move) && !ModuleHud.mc.player.getActivePotionEffects().isEmpty() ? 25 : 0), colorMode.getValue().equals((Object)colorModes.Static) ? this.colorHud.getRGB() : (colorMode.getValue().equals((Object)colorModes.Wave) ? waveColor.getRGB() : gradientColor.getRGB()));
        } else {
            this.drawStringWithShadow(string, (float)(scaledRes.getScaledWidth() - 2) - this.getStringWidth(string), 2 + mods[0] * 10 + (effectHud.getValue().equals((Object)effectHudModes.Move) && !ModuleHud.mc.player.getActivePotionEffects().isEmpty() ? 25 : 0), colorMode.getValue().equals((Object)colorModes.Static) ? this.colorHud.getRGB() : (colorMode.getValue().equals((Object)colorModes.Wave) ? waveColor.getRGB() : gradientColor.getRGB()));
        }
        void v0 = mods;
        v0[0] = v0[0] + true;
    }

    /*
     * WARNING - void declaration
     */
    public Float lambda$onRender2D$1(Module module) {
        void module2;
        return Float.valueOf(this.getStringWidth(module2.getTag() + module2.getHudInfo()) * Float.intBitsToFloat(Float.floatToIntBits(-22.808203f) ^ 0x7E367733));
    }

    /*
     * WARNING - void declaration
     */
    public void lambda$onRender2D$0(Color color, Color color2, Color color3, ScaledResolution scaledResolution, int[] arrn, PotionEffect potionEffect) {
        void potCount;
        void scaledRes;
        void effect;
        void color4;
        void color22;
        void color1;
        Color gradientColor = RainbowUtils.getGradientOffset((Color)color1, (Color)color22, Math.abs(((float)(System.currentTimeMillis() % ((long)1193219488 ^ 0x471F1270L)) / Float.intBitsToFloat(Float.floatToIntBits(0.0032956018f) ^ 0x7F2DFB06) + Float.intBitsToFloat(Float.floatToIntBits(0.9891066f) ^ 0x7EDD3617) / (float)(this.components * 2 + 10) * Float.intBitsToFloat(Float.floatToIntBits(0.866138f) ^ 0x7F5DBB38)) % Float.intBitsToFloat(Float.floatToIntBits(0.42879692f) ^ 0x7EDB8B45) - Float.intBitsToFloat(Float.floatToIntBits(4.1907325f) ^ 0x7F061A7B)));
        Color waveColor = RainbowUtils.astolfoRainbow((Color)color4, 50, this.components * 2 + 10);
        String name = I18n.format((String)effect.getPotion().getName(), (Object[])new Object[0]);
        double duration = (float)effect.getDuration() / Float.intBitsToFloat(Float.floatToIntBits(0.4254866f) ^ 0x7F4632E4);
        int amplifier = effect.getAmplifier() + 1;
        int potionColor = effect.getPotion().getLiquidColor();
        double p1 = duration % Double.longBitsToDouble(Double.doubleToLongBits(0.7415499151072334) ^ 0x7FA9BAC6E33796AFL);
        DecimalFormat format2 = new DecimalFormat("00");
        String seconds = format2.format(p1);
        String s = name + " " + amplifier + (Object)this.getInfoColor() + " " + (int)duration / 60 + ":" + seconds;
        String sR = "\u00a7+" + name + " " + amplifier + "\u00a7r" + (Object)this.getInfoColor() + " " + (int)duration / 60 + ":" + seconds;
        if (colorMode.getValue().equals((Object)colorModes.Rainbow) && potionSync.getValue()) {
            this.drawRainbowString(sR, (float)(scaledRes.getScaledWidth() - 2) - this.getStringWidth(s), scaledRes.getScaledHeight() + potCount[0] * -10 - 10 - 2, potionSync.getValue() ? (colorMode.getValue().equals((Object)colorModes.Static) ? this.colorHud.getRGB() : (colorMode.getValue().equals((Object)colorModes.Wave) ? waveColor.getRGB() : gradientColor.getRGB())) : potionColor);
        } else {
            this.drawStringWithShadow(s, (float)(scaledRes.getScaledWidth() - 2) - this.getStringWidth(s), scaledRes.getScaledHeight() + potCount[0] * -10 - 10 - 2, potionSync.getValue() ? (colorMode.getValue().equals((Object)colorModes.Static) ? this.colorHud.getRGB() : (colorMode.getValue().equals((Object)colorModes.Wave) ? waveColor.getRGB() : gradientColor.getRGB())) : potionColor);
        }
        void v0 = potCount;
        v0[0] = v0[0] + true;
        ++this.components;
    }

    public static enum orderModes {
        ABC,
        Length;

    }

    public static enum renderingModes {
        Up,
        Down;

    }

    public static enum colorModes {
        Static,
        Wave,
        Gradient,
        Rainbow;

    }

    public static enum waterModes {
        Default,
        Custom;

    }

    public static enum infoColors {
        Gray,
        White;

    }

    public static enum effectHudModes {
        Hide,
        Keep,
        Move;

    }
}

