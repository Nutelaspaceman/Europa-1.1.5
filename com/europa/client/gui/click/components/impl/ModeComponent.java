/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.Gui
 */
package com.europa.client.gui.click.components.impl;

import com.europa.Europa;
import com.europa.api.manager.value.impl.ValueEnum;
import com.europa.client.gui.click.components.Component;
import com.europa.client.gui.click.components.impl.ModuleComponent;
import com.europa.client.modules.client.ModuleColor;
import java.awt.Color;
import net.minecraft.client.gui.Gui;

public class ModeComponent
extends Component {
    public ValueEnum setting;
    public int enumSize;

    public ModeComponent(ValueEnum setting, ModuleComponent parent, int offset) {
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
        Europa.FONT_MANAGER.drawString(this.setting.getName(), this.getX() + 3, this.getY() + 3, Color.WHITE);
        Europa.FONT_MANAGER.drawString(this.setting.getValue().toString(), (float)(this.getX() + this.getWidth() - 3) - Europa.FONT_MANAGER.getStringWidth(this.setting.getValue().toString()), this.getY() + 3, ModuleColor.getActualColor());
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void mouseClicked(int n, int n2, int n3) {
        block2: {
            void mouseButton;
            block3: {
                void mouseY;
                void mouseX;
                super.mouseClicked((int)mouseX, (int)mouseY, (int)mouseButton);
                if (mouseX < this.getX() || mouseX > this.getX() + this.getWidth()) break block2;
                if (mouseY < this.getY()) break block2;
                if (mouseY > this.getY() + this.getHeight()) break block2;
                if (mouseButton != false) break block3;
                int maxIndex = this.setting.getValues().size() - 1;
                ++this.enumSize;
                if (this.enumSize > maxIndex) {
                    this.enumSize = 0;
                }
                this.setting.setValue(this.setting.getValues().get(this.enumSize));
                break block2;
            }
            if (mouseButton != true) break block2;
            int maxIndex = this.setting.getValues().size() - 1;
            --this.enumSize;
            if (this.enumSize < 0) {
                this.enumSize = maxIndex;
            }
            this.setting.setValue(this.setting.getValues().get(this.enumSize));
        }
    }
}

