/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiMultiplayer
 *  net.minecraft.client.gui.GuiOptions
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiWorldSelection
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.europa.client.gui.special;

import com.europa.Europa;
import com.europa.api.utilities.render.RenderUtils;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class EuropaMainMenu
extends GuiScreen {
    public ResourceLocation resourceLocation = new ResourceLocation("europa:mainmenu.png");
    public int y;
    public int x;
    public int singleplayerWidth;
    public int multiplayerWidth;
    public int settingsWidth;
    public int exitWidth;
    public int textHeight;
    public float xOffset;
    public float yOffset;

    public void initGui() {
        this.x = this.width / 2;
        this.y = this.height / 4 + 48;
        this.buttonList.add(new TextButton(0, this.x, this.y + 20, "Singleplayer"));
        this.buttonList.add(new TextButton(1, this.x, this.y + 44, "Multiplayer"));
        this.buttonList.add(new TextButton(2, this.x, this.y + 66, "Options"));
        this.buttonList.add(new TextButton(2, this.x, this.y + 88, "Quit"));
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.shadeModel((int)7425);
        GlStateManager.shadeModel((int)7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public void updateScreen() {
        super.updateScreen();
    }

    /*
     * WARNING - void declaration
     */
    public void mouseClicked(int n, int n2, int n3) {
        block1: {
            void mouseY;
            void mouseX;
            block3: {
                block2: {
                    block0: {
                        if (!EuropaMainMenu.isHovered(this.x - (int)Europa.FONT_MANAGER.getStringWidth("Singleplayer") / 2, this.y + 20, (int)Europa.FONT_MANAGER.getStringWidth("Singleplayer"), (int)Europa.FONT_MANAGER.getHeight(), (int)mouseX, (int)mouseY)) break block0;
                        this.mc.displayGuiScreen((GuiScreen)new GuiWorldSelection((GuiScreen)this));
                        break block1;
                    }
                    if (!EuropaMainMenu.isHovered(this.x - (int)Europa.FONT_MANAGER.getStringWidth("Multiplayer") / 2, this.y + 44, (int)Europa.FONT_MANAGER.getStringWidth("Multiplayer"), (int)Europa.FONT_MANAGER.getHeight(), (int)mouseX, (int)mouseY)) break block2;
                    this.mc.displayGuiScreen((GuiScreen)new GuiMultiplayer((GuiScreen)this));
                    break block1;
                }
                if (!EuropaMainMenu.isHovered(this.x - (int)Europa.FONT_MANAGER.getStringWidth("Options") / 2, this.y + 66, (int)Europa.FONT_MANAGER.getStringWidth("Options"), (int)Europa.FONT_MANAGER.getHeight(), (int)mouseX, (int)mouseY)) break block3;
                this.mc.displayGuiScreen((GuiScreen)new GuiOptions((GuiScreen)this, this.mc.gameSettings));
                break block1;
            }
            if (!EuropaMainMenu.isHovered(this.x - (int)Europa.FONT_MANAGER.getStringWidth("Quit") / 2, this.y + 88, (int)Europa.FONT_MANAGER.getStringWidth("Quit"), (int)Europa.FONT_MANAGER.getHeight(), (int)mouseX, (int)mouseY)) break block1;
            this.mc.shutdown();
        }
    }

    /*
     * WARNING - void declaration
     */
    public void drawScreen(int n, int n2, float f) {
        void partialTicks;
        void mouseY;
        void mouseX;
        this.xOffset = Float.intBitsToFloat(Float.floatToIntBits(-110.63207f) ^ 0x7D5D439F) * (((float)mouseX - (float)this.width / Float.intBitsToFloat(Float.floatToIntBits(0.42138848f) ^ 0x7ED7C03B)) / ((float)this.width / Float.intBitsToFloat(Float.floatToIntBits(0.056305815f) ^ 0x7F66A0ED)));
        this.yOffset = Float.intBitsToFloat(Float.floatToIntBits(-5.81684f) ^ 0x7F3A238E) * (((float)mouseY - (float)this.height / Float.intBitsToFloat(Float.floatToIntBits(0.8170179f) ^ 0x7F512816)) / ((float)this.height / Float.intBitsToFloat(Float.floatToIntBits(0.3316505f) ^ 0x7F39CE18)));
        this.x = this.width / 2;
        this.y = this.height / 4 + 48;
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        this.mc.getTextureManager().bindTexture(this.resourceLocation);
        EuropaMainMenu.drawCompleteImage(Float.intBitsToFloat(Float.floatToIntBits(-0.55055946f) ^ 0x7E8CF177) + this.xOffset, Float.intBitsToFloat(Float.floatToIntBits(-0.016965298f) ^ 0x7D9AFACF) + this.yOffset, this.width + 32, this.height + 18);
        super.drawScreen((int)mouseX, (int)mouseY, (float)partialTicks);
    }

    /*
     * WARNING - void declaration
     */
    public static void drawCompleteImage(float f, float f2, float f3, float f4) {
        void width;
        void height;
        void posY;
        float posX;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)posX, (float)posY, (float)Float.intBitsToFloat(Float.floatToIntBits(3.0431784E38f) ^ 0x7F64F17C));
        GL11.glBegin((int)7);
        GL11.glTexCoord2f((float)Float.intBitsToFloat(Float.floatToIntBits(1.1640711E38f) ^ 0x7EAF2663), (float)Float.intBitsToFloat(Float.floatToIntBits(1.524874E38f) ^ 0x7EE57003));
        GL11.glVertex3f((float)Float.intBitsToFloat(Float.floatToIntBits(1.6939362E38f) ^ 0x7EFEE00D), (float)Float.intBitsToFloat(Float.floatToIntBits(1.7422403E38f) ^ 0x7F031254), (float)Float.intBitsToFloat(Float.floatToIntBits(1.5163773E38f) ^ 0x7EE428BB));
        GL11.glTexCoord2f((float)Float.intBitsToFloat(Float.floatToIntBits(7.240035E37f) ^ 0x7E59DF33), (float)Float.intBitsToFloat(Float.floatToIntBits(4.7188916f) ^ 0x7F170129));
        GL11.glVertex3f((float)Float.intBitsToFloat(Float.floatToIntBits(3.0378397E38f) ^ 0x7F648AAA), (float)height, (float)Float.intBitsToFloat(Float.floatToIntBits(1.969856E38f) ^ 0x7F14320C));
        GL11.glTexCoord2f((float)Float.intBitsToFloat(Float.floatToIntBits(11.200683f) ^ 0x7EB335FF), (float)Float.intBitsToFloat(Float.floatToIntBits(7.7575216f) ^ 0x7F783D9E));
        GL11.glVertex3f((float)width, (float)height, (float)Float.intBitsToFloat(Float.floatToIntBits(1.6734088E38f) ^ 0x7EFBC95D));
        GL11.glTexCoord2f((float)Float.intBitsToFloat(Float.floatToIntBits(5.5092797f) ^ 0x7F304C05), (float)Float.intBitsToFloat(Float.floatToIntBits(1.7766657E38f) ^ 0x7F05A956));
        GL11.glVertex3f((float)width, (float)Float.intBitsToFloat(Float.floatToIntBits(2.0478936E38f) ^ 0x7F1A10FF), (float)Float.intBitsToFloat(Float.floatToIntBits(2.7254236E38f) ^ 0x7F4D09C0));
        GL11.glEnd();
        GL11.glPopMatrix();
    }

    /*
     * WARNING - void declaration
     */
    public BufferedImage parseBackground(BufferedImage bufferedImage) {
        int height;
        void background;
        int width = 1920;
        int srcWidth = background.getWidth();
        int srcHeight = background.getHeight();
        for (height = 1080; width < srcWidth || height < srcHeight; width *= 2, height *= 2) {
        }
        BufferedImage imgNew = new BufferedImage(width, height, 2);
        Graphics g = imgNew.getGraphics();
        g.drawImage((Image)background, 0, 0, null);
        g.dispose();
        return imgNew;
    }

    /*
     * WARNING - void declaration
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean isHovered(int n, int n2, int n3, int n4, int n5, int n6) {
        void height;
        void y;
        void mouseY;
        void width;
        int x;
        void mouseX;
        if (mouseX < x) return false;
        if (mouseX > x + width) return false;
        if (mouseY < y) return false;
        if (mouseY >= y + height) return false;
        return true;
    }

    public static boolean isCustomFont() {
        return Europa.getModuleManager().isModuleEnabled("Font");
    }

    private static class TextButton
    extends GuiButton {
        public TextButton(int buttonId, int x, int y, String buttonText) {
            super(buttonId, x, y, (int)Europa.FONT_MANAGER.getStringWidth(buttonText), (int)Europa.FONT_MANAGER.getHeight(), buttonText);
        }

        /*
         * WARNING - void declaration
         */
        public void drawButton(Minecraft minecraft, int n, int n2, float f) {
            if (this.visible) {
                void mouseY;
                void mouseX;
                this.enabled = true;
                this.hovered = (float)mouseX >= (float)this.x - Europa.FONT_MANAGER.getStringWidth(this.displayString) / Float.intBitsToFloat(Float.floatToIntBits(0.36750862f) ^ 0x7EBC2A17) && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
                this.drawGradientRect(this.x - 40 + (this.hovered ? -2 : 0), this.y - 5 + (this.hovered ? -2 : 0), this.x + 40 + (this.hovered ? 2 : 0), (int)((float)this.y + Europa.FONT_MANAGER.getHeight() + Float.intBitsToFloat(Float.floatToIntBits(0.26383278f) ^ 0x7E271517) + (float)(this.hovered ? 2 : 0)), new Color(40, 71, 237).getRGB(), new Color(239, 151, 244).getRGB());
                RenderUtils.drawOutlineLine(this.x - 40 + (this.hovered ? -2 : 0), this.y - 5 + (this.hovered ? -2 : 0), this.x + 40 + (this.hovered ? 2 : 0), (float)this.y + Europa.FONT_MANAGER.getHeight() + Float.intBitsToFloat(Float.floatToIntBits(1.328367f) ^ 0x7F0A07EE) + (float)(this.hovered ? 2 : 0), Float.intBitsToFloat(Float.floatToIntBits(0.6850277f) ^ 0x7F2F5DFA), new Color(0, 0, 0).getRGB());
                Europa.FONT_MANAGER.drawString(this.displayString, this.x - (int)Europa.FONT_MANAGER.getStringWidth(this.displayString) / 2, this.y, Color.WHITE);
            }
        }

        /*
         * WARNING - void declaration
         */
        public boolean mousePressed(Minecraft minecraft, int n, int n2) {
            void mouseY;
            void mouseX;
            return this.enabled && this.visible && (float)mouseX >= (float)this.x - Europa.FONT_MANAGER.getStringWidth(this.displayString) / Float.intBitsToFloat(Float.floatToIntBits(0.46001193f) ^ 0x7EEB86AF) && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
        }
    }
}

