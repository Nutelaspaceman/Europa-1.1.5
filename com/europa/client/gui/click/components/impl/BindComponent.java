/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.Gui
 *  org.lwjgl.input.Keyboard
 */
package com.europa.client.gui.click.components.impl;

import com.europa.Europa;
import com.europa.api.manager.value.impl.ValueBind;
import com.europa.client.gui.click.components.Component;
import com.europa.client.gui.click.components.impl.ModuleComponent;
import java.awt.Color;
import net.minecraft.client.gui.Gui;
import org.lwjgl.input.Keyboard;

public class BindComponent
extends Component {
    public boolean binding;
    public ValueBind setting;

    public BindComponent(ValueBind setting, ModuleComponent parent, int offset) {
        super(parent.getParent().getX(), parent.getParent().getY() + offset, parent.getParent());
        this.setting = setting;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void drawScreen(int n, int n2) {
        void mouseY;
        void mouseX;
        super.drawScreen((int)mouseX, (int)mouseY);
        Gui.drawRect((int)this.getX(), (int)this.getY(), (int)(this.getX() + this.getWidth()), (int)(this.getY() + 14), (int)new Color(40, 40, 40).getRGB());
        Gui.drawRect((int)(this.getX() - 1), (int)this.getY(), (int)this.getX(), (int)(this.getY() + 14), (int)new Color(30, 30, 30).getRGB());
        Gui.drawRect((int)(this.getX() + this.getWidth()), (int)this.getY(), (int)(this.getX() + this.getWidth() + 1), (int)(this.getY() + 14), (int)new Color(30, 30, 30).getRGB());
        Gui.drawRect((int)(this.getX() + 1), (int)(this.getY() + 1), (int)(this.getX() + this.getWidth() - 1), (int)(this.getY() + 13), (int)new Color(30, 30, 30).getRGB());
        Europa.FONT_MANAGER.drawString(this.setting.getName(), this.getX() + 3, this.getY() + 3, Color.WHITE);
        Europa.FONT_MANAGER.drawString(this.binding ? "[...]" : "[" + Keyboard.getKeyName((int)this.setting.getValue()).toUpperCase() + "]", (float)(this.getX() + this.getWidth() - 3) - Europa.FONT_MANAGER.getStringWidth(this.binding ? "[...]" : "[" + Keyboard.getKeyName((int)this.setting.getValue()).toUpperCase() + "]"), this.getY() + 3, Color.LIGHT_GRAY);
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void mouseClicked(int n, int n2, int n3) {
        void mouseButton;
        void mouseY;
        void mouseX;
        super.mouseClicked((int)mouseX, (int)mouseY, (int)mouseButton);
        if (mouseButton == false && mouseX >= this.getX()) {
            if (mouseX <= this.getX() + this.getWidth()) {
                if (mouseY >= this.getY() && mouseY <= this.getY() + this.getHeight()) {
                    this.binding = !this.binding;
                }
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void keyTyped(char c, int n) {
        void keyCode;
        void typedChar;
        super.keyTyped((char)typedChar, (int)keyCode);
        if (this.binding) {
            if (keyCode == 211) {
                this.setting.setValue(0);
            } else if (keyCode != true) {
                this.setting.setValue((int)keyCode);
            }
            this.binding = false;
        }
    }
}

