/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.util.ChatAllowedCharacters
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL20
 */
package com.europa.client.gui.font;

import com.europa.api.utilities.render.RenderUtils;
import com.europa.client.gui.font.CustomFont;
import java.awt.Font;
import java.util.Random;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL20;

public class FontRenderer {
    public Font font;
    public CustomFont defaultFont;
    public CustomFont boldFont;
    public CustomFont italicFont;
    public CustomFont boldItalicFont;

    public FontRenderer(Font font) {
        this.font = font;
        this.defaultFont = new CustomFont(font);
        this.boldFont = new CustomFont(font.deriveFont(1));
        this.italicFont = new CustomFont(font.deriveFont(2));
    }

    public int getHeight() {
        return this.defaultFont.getHeight() / 2;
    }

    public int getSize() {
        return this.defaultFont.getFont().getSize();
    }

    /*
     * WARNING - void declaration
     */
    public void drawString(String string, float f, float f2, int n) {
        void color;
        void y;
        void x;
        void s;
        this.drawString((String)s, (float)x, (float)y, (int)color, false);
    }

    /*
     * WARNING - void declaration
     */
    public int drawStringWithShadow(String string, float f, float f2, int n) {
        void color;
        void y;
        void x;
        void text;
        return this.drawString((String)text, (float)x, (float)y, (int)color, true);
    }

    /*
     * WARNING - void declaration
     */
    public void drawCenteredString(String string, float f, float f2, int n, boolean bl) {
        void shadow;
        void color;
        void y;
        void x;
        void s;
        this.drawString((String)s, (float)(x - (float)this.getStringWidth((String)s) / Float.intBitsToFloat(Float.floatToIntBits(0.06558233f) ^ 0x7D865007)), (float)y, (int)color, (boolean)shadow);
    }

    /*
     * WARNING - void declaration
     */
    public void drawCenteredString(String string, float f, float f2, int n) {
        void color;
        void y;
        void x;
        void s;
        this.drawString((String)s, (float)(x - (float)this.getStringWidth((String)s) / Float.intBitsToFloat(Float.floatToIntBits(0.9740728f) ^ 0x7F795CD6)), (float)y, (int)color);
    }

    /*
     * WARNING - void declaration
     */
    public int drawString(String string, float f, float f2, int n, boolean bl) {
        void color;
        void x;
        void text;
        void currY;
        block2: {
            void dropShadow;
            void y;
            currY = y - Float.intBitsToFloat(Float.floatToIntBits(0.5678633f) ^ 0x7F515F7D);
            if (text.contains("\n")) {
                String[] parts = text.split("\n");
                float newY = Float.intBitsToFloat(Float.floatToIntBits(2.5433182E38f) ^ 0x7F3F5687);
                for (String s : parts) {
                    this.drawText(s, (float)x, (float)(currY + newY), (int)color, (boolean)dropShadow);
                    newY += (float)this.getHeight();
                }
                return 0;
            }
            if (dropShadow == false) break block2;
            GL20.glUseProgram((int)0);
            int alpha = 1 - (color >> 24 & 0xFF) / 255;
            this.drawText((String)text, (float)(x + Float.intBitsToFloat(Float.floatToIntBits(4.1587253f) ^ 0x7F051447)), (float)(currY + Float.intBitsToFloat(Float.floatToIntBits(7.4003906f) ^ 0x7F6CD000)), -16777216, true);
        }
        return this.drawText((String)text, (float)x, (float)currY, (int)color, false);
    }

    /*
     * WARNING - void declaration
     */
    public int drawText(String string, float f, float f2, int n, boolean bl) {
        void color;
        void y;
        void x;
        void text;
        if (text == null) {
            return 0;
        }
        if (text.isEmpty()) {
            return (int)x;
        }
        GlStateManager.translate((double)((double)x - Double.longBitsToDouble(Double.doubleToLongBits(4.38206358885917) ^ 0x7FE9873BAD6C91F2L)), (double)((double)y + Double.longBitsToDouble(Double.doubleToLongBits(2.232718711342534) ^ 0x7FE1DC9BA0B3114DL)), (double)Double.longBitsToDouble(Double.doubleToLongBits(4.631075733333635E307) ^ 0x7FD07CB79FC5E477L));
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.enableTexture2D();
        int currentColor = color;
        if ((currentColor & 0xFC000000) == 0) {
            currentColor |= 0xFF000000;
        }
        int alpha = currentColor >> 24 & 0xFF;
        if (text.contains("\u00a7")) {
            String[] parts = text.split("\u00a7");
            CustomFont currentFont = this.defaultFont;
            double width = Double.longBitsToDouble(Double.doubleToLongBits(1.0591535192481754E307) ^ 0x7FAE2A6AE450258FL);
            boolean randomCase = false;
            boolean bold = false;
            boolean italic = false;
            boolean strikeThrough = false;
            boolean underline = false;
            for (int index = 0; index < parts.length; ++index) {
                String part = parts[index];
                if (part.isEmpty()) continue;
                if (index == 0) {
                    currentFont.drawString(part, width, Double.longBitsToDouble(Double.doubleToLongBits(1.7403185334101932E308) ^ 0x7FEEFA8BF6FD04E3L), currentColor);
                    width += (double)currentFont.getStringWidth(part);
                    continue;
                }
                String words = part.substring(1);
                char type = part.charAt(0);
                int colorIndex = "0123456789abcdefklmnor".indexOf(type);
                switch (colorIndex) {
                    case 0: 
                    case 1: 
                    case 2: 
                    case 3: 
                    case 4: 
                    case 5: 
                    case 6: 
                    case 7: 
                    case 8: 
                    case 9: 
                    case 10: 
                    case 11: 
                    case 12: 
                    case 13: 
                    case 14: 
                    case 15: {
                        void ignoreColor;
                        if (ignoreColor == false) {
                            currentColor = ColorUtils.hexColors[colorIndex] | alpha << 24;
                        }
                        bold = false;
                        italic = false;
                        randomCase = false;
                        underline = false;
                        strikeThrough = false;
                        break;
                    }
                    case 16: {
                        randomCase = true;
                        break;
                    }
                    case 17: {
                        bold = true;
                        break;
                    }
                    case 18: {
                        strikeThrough = true;
                        break;
                    }
                    case 19: {
                        underline = true;
                        break;
                    }
                    case 20: {
                        italic = true;
                        break;
                    }
                    case 21: {
                        currentColor = color;
                        if ((currentColor & 0xFC000000) == 0) {
                            currentColor |= 0xFF000000;
                        }
                        bold = false;
                        italic = false;
                        randomCase = false;
                        underline = false;
                        strikeThrough = false;
                    }
                }
                currentFont = bold && italic ? this.boldItalicFont : (bold ? this.boldFont : (italic ? this.italicFont : this.defaultFont));
                if (randomCase) {
                    currentFont.drawString(ColorUtils.randomMagicText(words), width, Double.longBitsToDouble(Double.doubleToLongBits(1.3058468768906788E308) ^ 0x7FE73EAE3A179A5DL), currentColor);
                } else {
                    currentFont.drawString(words, width, Double.longBitsToDouble(Double.doubleToLongBits(7.763767538645454E307) ^ 0x7FDBA3D272534C33L), currentColor);
                }
                if (strikeThrough) {
                    RenderUtils.drawLine(width / Double.longBitsToDouble(Double.doubleToLongBits(0.17700542826788399) ^ 0x7FC6A81D26D0012BL) + Double.longBitsToDouble(Double.doubleToLongBits(5.651080109473515) ^ 0x7FE69AB4BE850F34L), (double)currentFont.getHeight() / Double.longBitsToDouble(Double.doubleToLongBits(0.713411139170128) ^ 0x7FEED44398EACF4BL), (width + (double)currentFont.getStringWidth(words)) / Double.longBitsToDouble(Double.doubleToLongBits(0.10496540302048672) ^ 0x7FBADF033D2F381FL) + Double.longBitsToDouble(Double.doubleToLongBits(8.26073021668133) ^ 0x7FD0857E6E53735BL), (double)currentFont.getHeight() / Double.longBitsToDouble(Double.doubleToLongBits(0.5296440772389001) ^ 0x7FE8F2D822C85B89L), (float)this.getHeight() / Float.intBitsToFloat(Float.floatToIntBits(0.5113f) ^ 0x7E82E48F));
                }
                if (underline) {
                    RenderUtils.drawLine(width / Double.longBitsToDouble(Double.doubleToLongBits(0.6500457943014719) ^ 0x7FE4CD2CD66DAB56L) + Double.longBitsToDouble(Double.doubleToLongBits(4.716072070397632) ^ 0x7FE2DD41FF2FBF53L), (double)currentFont.getHeight() / Double.longBitsToDouble(Double.doubleToLongBits(0.6659823723165498) ^ 0x7FE54FBA4399FC89L), (width + (double)currentFont.getStringWidth(words)) / Double.longBitsToDouble(Double.doubleToLongBits(0.9891211714029902) ^ 0x7FEFA6E1715EA02EL) + Double.longBitsToDouble(Double.doubleToLongBits(14.815455926410829) ^ 0x7FDDA183706E871DL), (double)currentFont.getHeight() / Double.longBitsToDouble(Double.doubleToLongBits(0.5080189605191866) ^ 0x7FE041B0FAA5B0B7L), (float)this.getHeight() / Float.intBitsToFloat(Float.floatToIntBits(0.93159944f) ^ 0x7EEE7D4D));
                }
                width += (double)currentFont.getStringWidth(words);
            }
        } else {
            this.defaultFont.drawString((String)text, Double.longBitsToDouble(Double.doubleToLongBits(1.3252565393574456E308) ^ 0x7FE797212446ECCAL), Double.longBitsToDouble(Double.doubleToLongBits(1.725745634513711E308) ^ 0x7FEEB82384F45173L), currentColor);
        }
        GlStateManager.disableBlend();
        GlStateManager.translate((double)(-((double)x - Double.longBitsToDouble(Double.doubleToLongBits(9.379497132989217) ^ 0x7FDAC24D72BE3A99L))), (double)(-((double)y + Double.longBitsToDouble(Double.doubleToLongBits(12.356911352889389) ^ 0x7FC8B6BD15B8764BL))), (double)Double.longBitsToDouble(Double.doubleToLongBits(1.0390626317735182E308) ^ 0x7FE27EF53081780CL));
        GlStateManager.color((float)Float.intBitsToFloat(Float.floatToIntBits(4.432856f) ^ 0x7F0DD9F5), (float)Float.intBitsToFloat(Float.floatToIntBits(7.113679f) ^ 0x7F63A342), (float)Float.intBitsToFloat(Float.floatToIntBits(7.094939f) ^ 0x7F6309BE), (float)Float.intBitsToFloat(Float.floatToIntBits(6.4096394f) ^ 0x7F4D1BC4));
        return (int)(x + (float)this.getStringWidth((String)text));
    }

    /*
     * WARNING - void declaration
     */
    public int getColorCode(char c) {
        void charCode;
        return ColorUtils.hexColors[FontRenderer.getColorIndex((char)charCode)];
    }

    /*
     * WARNING - void declaration
     */
    public int getStringWidth(String string) {
        void text;
        void currentText = text;
        if (text.contains("\u00a7")) {
            String[] parts = text.split("\u00a7");
            CustomFont currentFont = this.defaultFont;
            int width = 0;
            boolean bold = false;
            boolean italic = false;
            for (int index = 0; index < parts.length; ++index) {
                String part = parts[index];
                if (part.isEmpty()) continue;
                if (index == 0) {
                    width += currentFont.getStringWidth(part);
                    continue;
                }
                String words = part.substring(1);
                char type = part.charAt(0);
                int colorIndex = FontRenderer.getColorIndex(type);
                if (colorIndex < 16) {
                    bold = false;
                    italic = false;
                } else if (colorIndex == 17) {
                    bold = true;
                } else if (colorIndex == 20) {
                    italic = true;
                } else if (colorIndex == 21) {
                    bold = false;
                    italic = false;
                }
                currentFont = bold && italic ? this.boldItalicFont : (bold ? this.boldFont : (italic ? this.italicFont : this.defaultFont));
                width += currentFont.getStringWidth(words);
            }
            return width / 2;
        }
        return this.defaultFont.getStringWidth((String)currentText) / 2;
    }

    /*
     * WARNING - void declaration
     */
    public int getCharWidth(char c) {
        void character;
        return this.getStringWidth(String.valueOf((char)character));
    }

    public void onResourceManagerReload(IResourceManager iResourceManager) {
    }

    public void bindTexture(ResourceLocation resourceLocation) {
    }

    public static int getColorIndex(char c) {
        char type;
        switch (type) {
            case '0': 
            case '1': 
            case '2': 
            case '3': 
            case '4': 
            case '5': 
            case '6': 
            case '7': 
            case '8': 
            case '9': {
                return type - 48;
            }
            case 'a': 
            case 'b': 
            case 'c': 
            case 'd': 
            case 'e': 
            case 'f': {
                return type - 97 + 10;
            }
            case 'k': 
            case 'l': 
            case 'm': 
            case 'n': 
            case 'o': {
                return type - 107 + 16;
            }
            case 'r': {
                return 21;
            }
        }
        return -1;
    }

    private static class ColorUtils {
        public static int[] hexColors;
        public static Random random;
        public static String magicAllowedCharacters;

        public static String randomMagicText(String string) {
            String text;
            StringBuilder stringBuilder = new StringBuilder();
            for (char ch : text.toCharArray()) {
                if (!ChatAllowedCharacters.isAllowedCharacter((char)ch)) continue;
                int index = random.nextInt("\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00c2\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00c2\u00aa\u00c2\u00ba\u00c2\u00bf\u00c2\u00ae\u00c2\u00ac\u00c2\u00bd\u00c2\u00bc\u00c2\u00a1\u00c2\u00ab\u00c2\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00c2\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00c2\u00b0\u2219\u00c2\u00b7\u221a\u207f\u00c2\u00b2\u25a0\u0000".length());
                stringBuilder.append("\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00c2\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00c2\u00aa\u00c2\u00ba\u00c2\u00bf\u00c2\u00ae\u00c2\u00ac\u00c2\u00bd\u00c2\u00bc\u00c2\u00a1\u00c2\u00ab\u00c2\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00c2\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00c2\u00b0\u2219\u00c2\u00b7\u221a\u207f\u00c2\u00b2\u25a0\u0000".charAt(index));
            }
            return stringBuilder.toString();
        }

        static {
            magicAllowedCharacters = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00c2\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00c2\u00aa\u00c2\u00ba\u00c2\u00bf\u00c2\u00ae\u00c2\u00ac\u00c2\u00bd\u00c2\u00bc\u00c2\u00a1\u00c2\u00ab\u00c2\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00c2\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00c2\u00b0\u2219\u00c2\u00b7\u221a\u207f\u00c2\u00b2\u25a0\u0000";
            int[] arrn = new int[16];
            hexColors = arrn;
            arrn[0] = 0;
            ColorUtils.hexColors[1] = 170;
            ColorUtils.hexColors[2] = 43520;
            ColorUtils.hexColors[3] = 43690;
            ColorUtils.hexColors[4] = 0xAA0000;
            ColorUtils.hexColors[5] = 0xAA00AA;
            ColorUtils.hexColors[6] = 0xFFAA00;
            ColorUtils.hexColors[7] = 0xAAAAAA;
            ColorUtils.hexColors[8] = 0x555555;
            ColorUtils.hexColors[9] = 0x5555FF;
            ColorUtils.hexColors[10] = 0x55FF55;
            ColorUtils.hexColors[11] = 0x55FFFF;
            ColorUtils.hexColors[12] = 0xFF5555;
            ColorUtils.hexColors[13] = 0xFF55FF;
            ColorUtils.hexColors[14] = 0xFFFF55;
            ColorUtils.hexColors[15] = 0xFFFFFF;
            random = new Random();
        }
    }
}

