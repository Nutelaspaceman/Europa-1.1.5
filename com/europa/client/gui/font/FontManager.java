/*
 * Decompiled with CFR 0.150.
 */
package com.europa.client.gui.font;

import com.europa.Europa;
import com.europa.api.utilities.IMinecraft;
import com.europa.client.gui.font.FontRenderer;
import java.awt.Color;
import java.awt.Font;
import java.io.InputStream;

public class FontManager
implements IMinecraft {
    public FontRenderer FONT_RENDERER;

    public void load() {
        this.FONT_RENDERER = new FontRenderer(FontManager.getFont("Lato-Medium.ttf", Float.intBitsToFloat(Float.floatToIntBits(0.042888146f) ^ 0x7F0FAB7B)));
    }

    /*
     * WARNING - void declaration
     */
    public float drawString(String string, float f, float f2, Color color) {
        void color2;
        void y;
        void x;
        void text;
        if (Europa.MODULE_MANAGER.isModuleEnabled("Font")) {
            return this.FONT_RENDERER.drawStringWithShadow((String)text, (float)x, (float)y, color2.getRGB());
        }
        return FontManager.mc.fontRenderer.drawStringWithShadow((String)text, (float)x, (float)y, color2.getRGB());
    }

    /*
     * WARNING - void declaration
     */
    public float getStringWidth(String string) {
        void text;
        if (Europa.MODULE_MANAGER.isModuleEnabled("Font")) {
            return this.FONT_RENDERER.getStringWidth((String)text);
        }
        return FontManager.mc.fontRenderer.getStringWidth((String)text);
    }

    public float getHeight() {
        if (Europa.MODULE_MANAGER.isModuleEnabled("Font")) {
            return this.FONT_RENDERER.getHeight();
        }
        return FontManager.mc.fontRenderer.FONT_HEIGHT;
    }

    /*
     * WARNING - void declaration
     */
    public static Font getFont(String string, float f) {
        Font awtClientFont;
        String fontName;
        void size;
        StringBuilder stringBuilder;
        StringBuilder stringBuilder2;
        Class<FontManager> class_;
        try {
            StringBuilder stringBuilder3;
            class_ = FontManager.class;
            stringBuilder2 = stringBuilder3;
            stringBuilder = stringBuilder3;
        }
        catch (Exception e) {
            e.printStackTrace();
            return new Font("default", 0, (int)size);
        }
        stringBuilder2();
        String string2 = "/assets/europa/fonts/";
        StringBuilder stringBuilder4 = stringBuilder.append(string2);
        String string3 = fontName;
        StringBuilder stringBuilder5 = stringBuilder4.append(string3);
        String string4 = stringBuilder5.toString();
        InputStream inputStream = class_.getResourceAsStream(string4);
        InputStream inputStream2 = inputStream;
        int n = 0;
        InputStream inputStream3 = inputStream2;
        Font font = Font.createFont(n, inputStream3);
        Font font2 = awtClientFont = font;
        int n2 = 0;
        void v15 = size;
        Font font3 = font2.deriveFont(n2, (float)v15);
        awtClientFont = font3;
        InputStream inputStream4 = inputStream2;
        inputStream4.close();
        return awtClientFont;
    }
}

