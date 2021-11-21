/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.ScaledResolution
 *  org.lwjgl.input.Mouse
 */
package com.europa.client.gui.click;

import com.europa.api.manager.module.ModuleCategory;
import com.europa.client.gui.click.components.Frame;
import com.europa.client.modules.client.ModuleGUI;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Mouse;

public class ClickGuiScreen
extends GuiScreen {
    public static ClickGuiScreen INSTANCE = new ClickGuiScreen();
    public ArrayList<Frame> frames = new ArrayList();

    public ClickGuiScreen() {
        int offset = 0;
        for (ModuleCategory category : ModuleCategory.values()) {
            if (category == ModuleCategory.HUD) continue;
            this.frames.add(new Frame(category, 10 + offset, 20));
            offset += 124;
        }
    }

    /*
     * WARNING - void declaration
     */
    public void drawScreen(int n, int n2, float f) {
        void partialTicks;
        void mouseY;
        void mouseX;
        super.drawScreen((int)mouseX, (int)mouseY, (float)partialTicks);
        ScaledResolution resolution = new ScaledResolution(this.mc);
        for (Frame panel : this.frames) {
            panel.drawScreen((int)mouseX, (int)mouseY);
            panel.updatePosition((int)mouseX, (int)mouseY);
            panel.refreshPosition();
            this.mouseScroll();
        }
    }

    /*
     * WARNING - void declaration
     */
    public void mouseClicked(int n, int n2, int n3) throws IOException {
        void mouseButton;
        void mouseY;
        void mouseX;
        super.mouseClicked((int)mouseX, (int)mouseY, (int)mouseButton);
        for (Frame panel : this.frames) {
            panel.mouseClicked((int)mouseX, (int)mouseY, (int)mouseButton);
        }
    }

    /*
     * WARNING - void declaration
     */
    public void mouseReleased(int n, int n2, int n3) {
        void state;
        void mouseY;
        void mouseX;
        super.mouseReleased((int)mouseX, (int)mouseY, (int)state);
        for (Frame panel : this.frames) {
            panel.mouseReleased((int)mouseX, (int)mouseY, (int)state);
        }
    }

    /*
     * WARNING - void declaration
     */
    public void keyTyped(char c, int n) throws IOException {
        void key;
        void typedChar;
        super.keyTyped((char)typedChar, (int)key);
        for (Frame panel : this.frames) {
            panel.keyTyped((char)typedChar, (int)key);
        }
    }

    public void onGuiClosed() {
        super.onGuiClosed();
        ModuleGUI.INSTANCE.disable();
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    public void mouseScroll() {
        int scroll = Mouse.getDWheel();
        for (Frame panel : this.frames) {
            if (scroll < 0) {
                panel.setY(panel.getY() - ModuleGUI.INSTANCE.scrollSpeed.getValue().intValue());
                continue;
            }
            if (scroll <= 0) continue;
            panel.setY(panel.getY() + ModuleGUI.INSTANCE.scrollSpeed.getValue().intValue());
        }
    }

    public static String capitalize(String string) {
        String input;
        return input;
    }
}

