/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  org.lwjgl.opengl.GL11
 */
package com.europa.client.gui.font;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import org.lwjgl.opengl.GL11;

public class CustomFont {
    public static boolean assumeNonVolatile;
    public static int gcTicks;
    public static int GC_TICKS;
    public static int CACHED_FONT_REMOVAL_TIME;
    public Font font;
    public int fontHeight = -1;
    public CharLocation[] charLocations = null;
    public HashMap<String, CachedFont> cachedStrings = new HashMap();
    public int textureID = 0;
    public int textureWidth = 0;
    public int textureHeight = 0;

    public CustomFont(Font font) {
        this.font = font;
        this.charLocations = new CharLocation[256];
        this.renderBitmap(0, 256);
    }

    public int getHeight() {
        return (this.fontHeight - 8) / 2;
    }

    /*
     * WARNING - void declaration
     */
    public void drawString(String string, double d, double d2, int n) {
        void text;
        void color;
        void y;
        void x;
        double scale = Double.longBitsToDouble(Double.doubleToLongBits(6.615448407929718) ^ 0x7FCA76381B81BBCFL);
        double reverse = Double.longBitsToDouble(Double.doubleToLongBits(0.2351066577787205) ^ 0x7FDE17F9971DA0C3L);
        GlStateManager.pushMatrix();
        GlStateManager.scale((double)Double.longBitsToDouble(Double.doubleToLongBits(29.078499140215964) ^ 0x7FED14188507FDDFL), (double)Double.longBitsToDouble(Double.doubleToLongBits(16.976301963401497) ^ 0x7FE0F9EEECEBD47CL), (double)Double.longBitsToDouble(Double.doubleToLongBits(4.177890442298529) ^ 0x7FC0B628E97FC5CBL));
        GL11.glTranslated((double)(x * Double.longBitsToDouble(Double.doubleToLongBits(0.969132070377238) ^ 0x7FEF03214278CCDDL)), (double)(y * Double.longBitsToDouble(Double.doubleToLongBits(0.7475017061180943) ^ 0x7FE7EB88B2AF67C7L) - Double.longBitsToDouble(Double.doubleToLongBits(0.7570690305052032) ^ 0x7FE839E8D4DAB260L)), (double)Double.longBitsToDouble(Double.doubleToLongBits(1.3494038906871303E308) ^ 0x7FE8052AF282A96FL));
        GlStateManager.bindTexture((int)this.textureID);
        float red = (float)(color >> 16 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.120077275f) ^ 0x7E8AEB13);
        float green = (float)(color >> 8 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.015050172f) ^ 0x7F0994FF);
        float blue = (float)(color & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.09046397f) ^ 0x7EC6452D);
        float alpha = (float)(color >> 24 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.07047931f) ^ 0x7EEF5775);
        GlStateManager.color((float)red, (float)green, (float)blue, (float)alpha);
        double currX = Double.longBitsToDouble(Double.doubleToLongBits(1.5746733532606273E308) ^ 0x7FEC07B5B085168DL);
        CachedFont cached = this.cachedStrings.get(text);
        if (cached != null) {
            GL11.glCallList((int)cached.getDisplayList());
            cached.setLastUsage(System.currentTimeMillis());
            GlStateManager.popMatrix();
            return;
        }
        int list = -1;
        if (assumeNonVolatile) {
            list = GL11.glGenLists((int)1);
            GL11.glNewList((int)list, (int)4865);
        }
        GL11.glBegin((int)7);
        for (char ch : text.toCharArray()) {
            if (Character.getNumericValue(ch) >= this.charLocations.length) {
                GL11.glEnd();
                GlStateManager.scale((double)Double.longBitsToDouble(Double.doubleToLongBits(1.775826226404408) ^ 0x7FEC69C8C2DC938EL), (double)Double.longBitsToDouble(Double.doubleToLongBits(1.2044081174990917) ^ 0x7FE34541723B20DEL), (double)Double.longBitsToDouble(Double.doubleToLongBits(0.22849597015411957) ^ 0x7FDD3F5B1F8A34C7L));
                Minecraft.getMinecraft().fontRenderer.drawString(String.valueOf(ch), (float)currX * Float.intBitsToFloat(Float.floatToIntBits(26.218346f) ^ 0x7F51BF2C) + Float.intBitsToFloat(Float.floatToIntBits(27.870398f) ^ 0x7E5EF693), Float.intBitsToFloat(Float.floatToIntBits(0.14261268f) ^ 0x7E12090F), (int)color, false);
                currX += (double)Minecraft.getMinecraft().fontRenderer.getStringWidth(String.valueOf(ch)) * Double.longBitsToDouble(Double.doubleToLongBits(0.20191953364354756) ^ 0x7FD9D87FD0B61809L);
                GlStateManager.scale((double)Double.longBitsToDouble(Double.doubleToLongBits(28.4737501596925) ^ 0x7FEC7947B0C25AA4L), (double)Double.longBitsToDouble(Double.doubleToLongBits(147.14406412028185) ^ 0x7FB2649C2C5BA467L), (double)Double.longBitsToDouble(Double.doubleToLongBits(26.71160465005691) ^ 0x7FEAB62BB8EBAD0BL));
                GlStateManager.bindTexture((int)this.textureID);
                GlStateManager.color((float)red, (float)green, (float)blue, (float)alpha);
                GL11.glBegin((int)7);
                continue;
            }
            if (this.charLocations.length <= ch) continue;
            CharLocation fontChar = this.charLocations[ch];
            if (fontChar == null) continue;
            this.drawChar(fontChar, (float)currX, Float.intBitsToFloat(Float.floatToIntBits(1.0698629E38f) ^ 0x7EA0F99F));
            currX += (double)CharLocation.access$000(fontChar) - Double.longBitsToDouble(Double.doubleToLongBits(1.4705512228097988) ^ 0x7FD78760B810F96DL);
        }
        GL11.glEnd();
        if (assumeNonVolatile) {
            this.cachedStrings.put((String)text, new CachedFont(this, list, System.currentTimeMillis()));
            GL11.glEndList();
        }
        GlStateManager.popMatrix();
    }

    /*
     * WARNING - void declaration
     */
    public void drawChar(CharLocation charLocation, float f, float f2) {
        void y;
        void x;
        void ch;
        float width = CharLocation.access$000((CharLocation)ch);
        float height = CharLocation.access$100((CharLocation)ch);
        float srcX = CharLocation.access$200((CharLocation)ch);
        float srcY = CharLocation.access$300((CharLocation)ch);
        float renderX = srcX / (float)this.textureWidth;
        float renderY = srcY / (float)this.textureHeight;
        float renderWidth = width / (float)this.textureWidth;
        float renderHeight = height / (float)this.textureHeight;
        GL11.glTexCoord2f((float)renderX, (float)renderY);
        GL11.glVertex2f((float)x, (float)y);
        GL11.glTexCoord2f((float)renderX, (float)(renderY + renderHeight));
        GL11.glVertex2f((float)x, (float)(y + height));
        GL11.glTexCoord2f((float)(renderX + renderWidth), (float)(renderY + renderHeight));
        GL11.glVertex2f((float)(x + width), (float)(y + height));
        GL11.glTexCoord2f((float)(renderX + renderWidth), (float)renderY);
        GL11.glVertex2f((float)(x + width), (float)y);
    }

    /*
     * WARNING - void declaration
     */
    public void renderBitmap(int n, int n2) {
        void stopChar;
        BufferedImage[] fontImages = new BufferedImage[stopChar];
        int rowHeight = 0;
        int charX = 0;
        int charY = 0;
        for (void targetChar = startChar; targetChar < stopChar; ++targetChar) {
            BufferedImage fontImage = this.drawCharToImage((char)targetChar);
            CharLocation fontChar = new CharLocation(charX, charY, fontImage.getWidth(), fontImage.getHeight());
            if (CharLocation.access$100(fontChar) > this.fontHeight) {
                this.fontHeight = CharLocation.access$100(fontChar);
            }
            if (CharLocation.access$100(fontChar) > rowHeight) {
                rowHeight = CharLocation.access$100(fontChar);
            }
            if (this.charLocations.length <= targetChar) continue;
            this.charLocations[targetChar] = fontChar;
            fontImages[targetChar] = fontImage;
            if ((charX += CharLocation.access$000(fontChar)) <= 2048) continue;
            if (charX > this.textureWidth) {
                this.textureWidth = charX;
            }
            charX = 0;
            charY += rowHeight;
            rowHeight = 0;
        }
        this.textureHeight = charY + rowHeight;
        BufferedImage bufferedImage = new BufferedImage(this.textureWidth, this.textureHeight, 2);
        Graphics2D graphics2D = (Graphics2D)bufferedImage.getGraphics();
        graphics2D.setFont(this.font);
        graphics2D.setColor(new Color(255, 255, 255, 0));
        graphics2D.fillRect(0, 0, this.textureWidth, this.textureHeight);
        graphics2D.setColor(Color.WHITE);
        for (void targetChar2 = startChar; targetChar2 < stopChar; ++targetChar2) {
            if (fontImages[targetChar2] == null || this.charLocations[targetChar2] == null) continue;
            graphics2D.drawImage((Image)fontImages[targetChar2], CharLocation.access$200(this.charLocations[targetChar2]), CharLocation.access$300(this.charLocations[targetChar2]), null);
        }
        this.textureID = TextureUtil.uploadTextureImageAllocate((int)TextureUtil.glGenTextures(), (BufferedImage)bufferedImage, (boolean)true, (boolean)true);
    }

    /*
     * WARNING - void declaration
     */
    public BufferedImage drawCharToImage(char c) {
        int charHeight;
        void ch;
        Graphics2D graphics2D = (Graphics2D)new BufferedImage(1, 1, 2).getGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.setFont(this.font);
        FontMetrics fontMetrics = graphics2D.getFontMetrics();
        int charWidth = fontMetrics.charWidth((char)ch) + 8;
        if (charWidth <= 8) {
            charWidth = 7;
        }
        if ((charHeight = fontMetrics.getHeight() + 3) <= 0) {
            charHeight = this.font.getSize();
        }
        BufferedImage fontImage = new BufferedImage(charWidth, charHeight, 2);
        Graphics2D graphics = (Graphics2D)fontImage.getGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.setFont(this.font);
        graphics.setColor(Color.WHITE);
        graphics.drawString(String.valueOf((char)ch), 3, 1 + fontMetrics.getAscent());
        return fontImage;
    }

    /*
     * WARNING - void declaration
     */
    public int getStringWidth(String string) {
        void text;
        int width = 0;
        for (int n : text.toCharArray()) {
            CharLocation fontChar;
            int index = n < this.charLocations.length ? n : 3;
            if (this.charLocations.length <= index || (fontChar = this.charLocations[index]) == null) continue;
            width += CharLocation.access$000(fontChar) - 8;
        }
        return width / 2;
    }

    public Font getFont() {
        return this.font;
    }

    static {
        CACHED_FONT_REMOVAL_TIME = 30000;
        GC_TICKS = 600;
    }

    public class CachedFont {
        public int displayList;
        public long lastUsage;
        public boolean deleted;
        public CustomFont this$0;

        public CachedFont(CustomFont this$0, int displayList, long lastUsage, boolean deleted) {
            this.this$0 = this$0;
            this.deleted = false;
            this.displayList = displayList;
            this.lastUsage = lastUsage;
            this.deleted = deleted;
        }

        public CachedFont(CustomFont this$0, int displayList, long lastUsage) {
            this.this$0 = this$0;
            this.deleted = false;
            this.displayList = displayList;
            this.lastUsage = lastUsage;
        }

        public void finalize() {
            block0: {
                if (this.deleted) break block0;
                GL11.glDeleteLists((int)this.displayList, (int)1);
            }
        }

        public int getDisplayList() {
            return this.displayList;
        }

        public long getLastUsage() {
            return this.lastUsage;
        }

        public boolean isDeleted() {
            return this.deleted;
        }

        /*
         * WARNING - void declaration
         */
        public void setLastUsage(long l) {
            void lastUsage;
            this.lastUsage = lastUsage;
        }

        /*
         * WARNING - void declaration
         */
        public void setDeleted(boolean bl) {
            void deleted;
            this.deleted = deleted;
        }
    }

    private static class CharLocation {
        public int x;
        public int y;
        public int width;
        public int height;

        public CharLocation(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public static int access$000(CharLocation charLocation) {
            CharLocation x0;
            return x0.width;
        }

        public static int access$100(CharLocation charLocation) {
            CharLocation x0;
            return x0.height;
        }

        public static int access$200(CharLocation charLocation) {
            CharLocation x0;
            return x0.x;
        }

        public static int access$300(CharLocation charLocation) {
            CharLocation x0;
            return x0.y;
        }
    }
}

