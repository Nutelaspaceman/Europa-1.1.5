/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  org.lwjgl.input.Mouse
 */
package com.europa.client.gui.hud;

import com.europa.Europa;
import com.europa.api.manager.element.Element;
import com.europa.client.gui.click.components.Frame;
import com.europa.client.gui.hud.ElementFrame;
import com.europa.client.modules.client.ModuleGUI;
import com.europa.client.modules.client.ModuleHUDEditor;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;

public class HudEditorScreen
extends GuiScreen {
    public ArrayList<ElementFrame> elementFrames = new ArrayList();
    public Frame frame = new Frame(20, 20);

    public HudEditorScreen() {
        for (Element element : Europa.ELEMENT_MANAGER.getElements()) {
            this.addElement(element);
            element.setFrame(this.getFrame(element));
        }
    }

    /*
     * WARNING - void declaration
     */
    public void addElement(Element element) {
        void element2;
        this.elementFrames.add(new ElementFrame((Element)element2, Float.intBitsToFloat(Float.floatToIntBits(0.8995771f) ^ 0x7E464AAF), Float.intBitsToFloat(Float.floatToIntBits(1.853094f) ^ 0x7ECD322F), Float.intBitsToFloat(Float.floatToIntBits(0.11447282f) ^ 0x7F4A70BA), Float.intBitsToFloat(Float.floatToIntBits(0.16486806f) ^ 0x7F58D32C), this));
    }

    /*
     * WARNING - void declaration
     */
    public void drawScreen(int n, int n2, float f) {
        void partialTicks;
        void mouseY;
        void mouseX;
        super.drawScreen((int)mouseX, (int)mouseY, (float)partialTicks);
        this.frame.drawScreen((int)mouseX, (int)mouseY);
        this.frame.updatePosition((int)mouseX, (int)mouseY);
        this.frame.refreshPosition();
        this.mouseScroll();
        for (ElementFrame frame : this.elementFrames) {
            frame.drawScreen((int)mouseX, (int)mouseY, (float)partialTicks);
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
        this.frame.mouseClicked((int)mouseX, (int)mouseY, (int)mouseButton);
        for (ElementFrame frame : this.elementFrames) {
            frame.mouseClicked((int)mouseX, (int)mouseY, (int)mouseButton);
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
        this.frame.mouseReleased((int)mouseX, (int)mouseY, (int)state);
        for (ElementFrame frame : this.elementFrames) {
            frame.mouseReleased((int)mouseX, (int)mouseY, (int)state);
        }
    }

    /*
     * WARNING - void declaration
     */
    public void keyTyped(char c, int n) throws IOException {
        void keyCode;
        void typedChar;
        super.keyTyped((char)typedChar, (int)keyCode);
        this.frame.keyTyped((char)typedChar, (int)keyCode);
    }

    public void onGuiClosed() {
        block0: {
            super.onGuiClosed();
            if (ModuleHUDEditor.INSTANCE == null) break block0;
            ModuleHUDEditor.INSTANCE.disable();
        }
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    public void mouseScroll() {
        block1: {
            int scroll;
            block0: {
                scroll = Mouse.getDWheel();
                if (scroll >= 0) break block0;
                this.frame.setY(this.frame.getY() - ModuleGUI.INSTANCE.scrollSpeed.getValue().intValue());
                break block1;
            }
            if (scroll <= 0) break block1;
            this.frame.setY(this.frame.getY() + ModuleGUI.INSTANCE.scrollSpeed.getValue().intValue());
        }
    }

    public Frame getFrame() {
        return this.frame;
    }

    public ArrayList<ElementFrame> getElementFrames() {
        return this.elementFrames;
    }

    /*
     * WARNING - void declaration
     */
    public ElementFrame getFrame(Element element) {
        for (ElementFrame frame : this.elementFrames) {
            void element2;
            if (!frame.getElement().equals(element2)) continue;
            return frame;
        }
        return null;
    }
}

