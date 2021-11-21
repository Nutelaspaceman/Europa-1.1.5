/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.Gui
 */
package com.europa.client.gui.click.components.impl;

import com.europa.Europa;
import com.europa.api.manager.module.Module;
import com.europa.api.manager.value.Value;
import com.europa.api.manager.value.impl.ValueBind;
import com.europa.api.manager.value.impl.ValueBoolean;
import com.europa.api.manager.value.impl.ValueColor;
import com.europa.api.manager.value.impl.ValueEnum;
import com.europa.api.manager.value.impl.ValueNumber;
import com.europa.api.manager.value.impl.ValuePreview;
import com.europa.api.manager.value.impl.ValueString;
import com.europa.client.gui.click.components.Component;
import com.europa.client.gui.click.components.Frame;
import com.europa.client.gui.click.components.impl.BindComponent;
import com.europa.client.gui.click.components.impl.BooleanComponent;
import com.europa.client.gui.click.components.impl.ColorComponent;
import com.europa.client.gui.click.components.impl.ModeComponent;
import com.europa.client.gui.click.components.impl.NumberComponent;
import com.europa.client.gui.click.components.impl.PreviewComponent;
import com.europa.client.gui.click.components.impl.StringComponent;
import com.europa.client.modules.client.ModuleColor;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.gui.Gui;

public class ModuleComponent
extends Component {
    public Module module;
    public ArrayList<Component> subButtons;
    public boolean open;

    public ModuleComponent(Module module, int x, int y, Frame parent) {
        super(x, y, parent);
        this.module = module;
        this.subButtons = new ArrayList();
        this.open = false;
        int tempY = y + 14;
        if (module.getValues() != null && !module.getValues().isEmpty()) {
            for (Value setting : module.getValues()) {
                if (setting instanceof ValueBoolean) {
                    this.subButtons.add(new BooleanComponent((ValueBoolean)setting, this, tempY));
                    tempY += 14;
                }
                if (setting instanceof ValueNumber) {
                    this.subButtons.add(new NumberComponent((ValueNumber)setting, this, tempY));
                    tempY += 14;
                }
                if (setting instanceof ValueEnum) {
                    this.subButtons.add(new ModeComponent((ValueEnum)setting, this, tempY));
                    tempY += 14;
                }
                if (setting instanceof ValueString) {
                    this.subButtons.add(new StringComponent((ValueString)setting, this, tempY));
                    tempY += 14;
                }
                if (setting instanceof ValueColor) {
                    this.subButtons.add(new ColorComponent((ValueColor)setting, this, tempY));
                    tempY += 100;
                }
                if (setting instanceof ValuePreview) {
                    this.subButtons.add(new PreviewComponent((ValuePreview)setting, this, tempY));
                    tempY += 70;
                }
                if (!(setting instanceof ValueBind)) continue;
                this.subButtons.add(new BindComponent((ValueBind)setting, this, tempY));
                tempY += 14;
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void drawScreen(int n, int n2) {
        void mouseY;
        void mouseX;
        super.drawScreen((int)mouseX, (int)mouseY);
        Gui.drawRect((int)this.getX(), (int)this.getY(), (int)(this.getX() + this.getWidth()), (int)(this.getY() + 14), (int)(this.module.isToggled() ? ModuleColor.getColor() : new Color(40, 40, 40).getRGB()));
        Gui.drawRect((int)(this.getX() - 1), (int)this.getY(), (int)this.getX(), (int)(this.getY() + 14), (int)new Color(30, 30, 30).getRGB());
        Gui.drawRect((int)(this.getX() + this.getWidth()), (int)this.getY(), (int)(this.getX() + this.getWidth() + 1), (int)(this.getY() + 14), (int)new Color(30, 30, 30).getRGB());
        Europa.FONT_MANAGER.drawString(this.module.getTag(), this.getX() + 3, this.getY() + 3, Color.WHITE);
        if (this.open && !this.getSubButtons().isEmpty()) {
            for (Component button : this.getSubButtons()) {
                button.drawScreen((int)mouseX, (int)mouseY);
            }
        }
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
        if (mouseX >= this.getX()) {
            if (mouseX <= this.getX() + this.getWidth()) {
                if (mouseY >= this.getY() && mouseY <= this.getY() + this.getHeight()) {
                    if (mouseButton == false) {
                        this.module.toggle();
                    } else if (mouseButton == true) {
                        this.open = !this.open;
                    }
                }
            }
        }
    }

    public boolean isOpen() {
        return this.open;
    }

    public ArrayList<Component> getSubButtons() {
        return this.subButtons;
    }
}

