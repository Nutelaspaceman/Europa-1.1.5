/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package com.europa.client.gui.click.components;

import com.europa.client.gui.click.components.Frame;
import net.minecraft.client.Minecraft;

public class Component {
    public static Minecraft mc = Minecraft.getMinecraft();
    public int x;
    public int y;
    public int width;
    public int height;
    public Frame parent;

    public Component(int x, int y, Frame parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.width = parent.width;
        this.height = 14;
    }

    public void drawScreen(int n, int n2) {
    }

    public void mouseClicked(int n, int n2, int n3) {
    }

    public void mouseReleased(int n, int n2, int n3) {
    }

    public void keyTyped(char c, int n) {
    }

    public void update(int n, int n2) {
    }

    public Frame getParent() {
        return this.parent;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    /*
     * WARNING - void declaration
     */
    public void setX(int n) {
        void x;
        this.x = x;
    }

    /*
     * WARNING - void declaration
     */
    public void setY(int n) {
        void y;
        this.y = y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}

