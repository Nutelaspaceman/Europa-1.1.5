/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 */
package com.europa.client.gui.hud;

import com.europa.Europa;
import com.europa.api.manager.element.Element;
import com.europa.api.manager.event.impl.render.EventRender2D;
import com.europa.api.utilities.IMinecraft;
import com.europa.client.gui.hud.HudEditorScreen;
import java.awt.Color;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

public class ElementFrame
implements IMinecraft {
    public Element element;
    public float x;
    public float y;
    public float width;
    public float height;
    public float dragX;
    public float dragY;
    public boolean dragging;
    public boolean visible;
    public HudEditorScreen parent;

    public ElementFrame(Element element, float x, float y, float width, float height, HudEditorScreen parent) {
        this.element = element;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.parent = parent;
        this.dragging = false;
        this.visible = true;
    }

    /*
     * WARNING - void declaration
     */
    public void drawScreen(int n, int n2, float f) {
        block7: {
            void partialTicks;
            if (this.element == null || !Europa.ELEMENT_MANAGER.isElementEnabled(this.element.getName())) break block7;
            if (this.dragging) {
                void mouseY;
                void mouseX;
                this.x = this.dragX + (float)mouseX;
                this.y = this.dragY + (float)mouseY;
                ScaledResolution resolution = new ScaledResolution(mc);
                if ((double)this.x < Double.longBitsToDouble(Double.doubleToLongBits(1.6916582217715779E308) ^ 0x7FEE1CCDD7767A58L)) {
                    this.x = Float.intBitsToFloat(Float.floatToIntBits(4.9986014E37f) ^ 0x7E166BD3);
                }
                if ((double)this.y < Double.longBitsToDouble(Double.doubleToLongBits(9.52408722466754E307) ^ 0x7FE0F414836643D0L)) {
                    this.y = Float.intBitsToFloat(Float.floatToIntBits(2.3789903E38f) ^ 0x7F32F9B0);
                }
                if (this.x > (float)resolution.getScaledWidth() - this.width) {
                    this.x = (float)resolution.getScaledWidth() - this.width;
                }
                if (this.y > (float)resolution.getScaledHeight() - this.height) {
                    this.y = (float)resolution.getScaledHeight() - this.height;
                }
            }
            if (this.dragging) {
                Gui.drawRect((int)((int)this.x), (int)((int)this.y), (int)((int)(this.x + this.width)), (int)((int)(this.y + this.height)), (int)new Color(Color.DARK_GRAY.getRed(), Color.DARK_GRAY.getGreen(), Color.DARK_GRAY.getBlue(), 100).getRGB());
            } else {
                Gui.drawRect((int)((int)this.x), (int)((int)this.y), (int)((int)(this.x + this.width)), (int)((int)(this.y + this.height)), (int)new Color(Color.BLACK.getRed(), Color.BLACK.getGreen(), Color.BLACK.getBlue(), 100).getRGB());
            }
            this.element.onRender2D(new EventRender2D((float)partialTicks));
        }
    }

    /*
     * WARNING - void declaration
     */
    public void mouseClicked(int n, int n2, int n3) {
        void mouseY;
        void mouseX;
        void mouseButton;
        if (mouseButton == false && this.isHovering((int)mouseX, (int)mouseY)) {
            this.dragX = this.x - (float)mouseX;
            this.dragY = this.y - (float)mouseY;
            this.dragging = true;
        }
    }

    public void mouseReleased(int n, int n2, int n3) {
        this.dragging = false;
    }

    /*
     * WARNING - void declaration
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isHovering(int n, int n2) {
        void mouseY;
        void mouseX;
        if (!((float)mouseX >= this.x)) return false;
        if (!((float)mouseX <= this.x + this.width)) return false;
        if (!((float)mouseY >= this.y)) return false;
        if (!((float)mouseY <= this.y + this.height)) return false;
        return true;
    }

    public Element getElement() {
        return this.element;
    }

    public HudEditorScreen getParent() {
        return this.parent;
    }

    public float getX() {
        return this.x;
    }

    /*
     * WARNING - void declaration
     */
    public void setX(float f) {
        void x;
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    /*
     * WARNING - void declaration
     */
    public void setY(float f) {
        void y;
        this.y = y;
    }

    public float getWidth() {
        return this.width;
    }

    /*
     * WARNING - void declaration
     */
    public void setWidth(float f) {
        void width;
        this.width = width;
    }

    public float getHeight() {
        return this.height;
    }

    /*
     * WARNING - void declaration
     */
    public void setHeight(float f) {
        void height;
        this.height = height;
    }

    public boolean isDragging() {
        return this.dragging;
    }

    /*
     * WARNING - void declaration
     */
    public void setDragging(boolean bl) {
        void dragging;
        this.dragging = dragging;
    }

    public boolean isVisible() {
        return this.visible;
    }

    /*
     * WARNING - void declaration
     */
    public void setVisible(boolean bl) {
        void visible;
        this.visible = visible;
    }
}

