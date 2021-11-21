/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.Gui
 *  org.lwjgl.opengl.GL11
 */
package com.europa.client.gui.click.components.impl;

import com.europa.Europa;
import com.europa.api.manager.value.impl.ValueBoolean;
import com.europa.api.utilities.render.RenderUtils;
import com.europa.client.gui.click.components.Component;
import com.europa.client.gui.click.components.impl.ModuleComponent;
import com.europa.client.modules.client.ModuleColor;
import java.awt.Color;
import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;

public class BooleanComponent
extends Component {
    public ValueBoolean setting;

    public BooleanComponent(ValueBoolean setting, ModuleComponent parent, int offset) {
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
        Gui.drawRect((int)(this.getX() + this.getWidth() - 12), (int)(this.getY() + 2), (int)(this.getX() + this.getWidth() - 2), (int)(this.getY() + 12), (int)new Color(30, 30, 30).getRGB());
        if (this.setting.getValue()) {
            RenderUtils.prepareGL();
            GL11.glShadeModel((int)7425);
            GL11.glEnable((int)2848);
            GL11.glLineWidth((float)Float.intBitsToFloat(Float.floatToIntBits(0.04342526f) ^ 0x7D11DEAF));
            GL11.glBegin((int)1);
            GL11.glColor3f((float)((float)ModuleColor.getActualColor().getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.11525812f) ^ 0x7E930C73)), (float)((float)ModuleColor.getActualColor().getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.010934432f) ^ 0x7F4C2655)), (float)((float)ModuleColor.getActualColor().getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.18847941f) ^ 0x7D3E00BF)));
            GL11.glVertex2d((double)(this.getX() + this.getWidth() - 8), (double)(this.getY() + 10));
            GL11.glColor3f((float)((float)ModuleColor.getActualColor().getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.008369477f) ^ 0x7F762021)), (float)((float)ModuleColor.getActualColor().getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.102402516f) ^ 0x7EAEB869)), (float)((float)ModuleColor.getActualColor().getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.17724131f) ^ 0x7D4A7EBF)));
            GL11.glVertex2d((double)(this.getX() + this.getWidth() - 8 + 4), (double)(this.getY() + 4));
            GL11.glEnd();
            GL11.glBegin((int)1);
            GL11.glColor3f((float)((float)ModuleColor.getActualColor().getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.11534863f) ^ 0x7E933BE7)), (float)((float)ModuleColor.getActualColor().getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.09277556f) ^ 0x7EC1011D)), (float)((float)ModuleColor.getActualColor().getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.08886478f) ^ 0x7ECAFEBD)));
            GL11.glVertex2d((double)(this.getX() + this.getWidth() - 8), (double)(this.getY() + 10));
            GL11.glColor3f((float)((float)ModuleColor.getActualColor().getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.013863879f) ^ 0x7F1C2553)), (float)((float)ModuleColor.getActualColor().getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.011107416f) ^ 0x7F4AFBE1)), (float)((float)ModuleColor.getActualColor().getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.011841842f) ^ 0x7F3D0449)));
            GL11.glVertex2d((double)(this.getX() + this.getWidth() - 10), (double)(this.getY() + 7));
            GL11.glEnd();
            RenderUtils.releaseGL();
        }
        Gui.drawRect((int)(this.getX() - 1), (int)this.getY(), (int)this.getX(), (int)(this.getY() + 14), (int)new Color(30, 30, 30).getRGB());
        Gui.drawRect((int)(this.getX() + this.getWidth()), (int)this.getY(), (int)(this.getX() + this.getWidth() + 1), (int)(this.getY() + 14), (int)new Color(30, 30, 30).getRGB());
        Europa.FONT_MANAGER.drawString(this.setting.getName(), this.getX() + 3, this.getY() + 3, Color.WHITE);
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void mouseClicked(int n, int n2, int n3) {
        block0: {
            void mouseButton;
            void mouseY;
            void mouseX;
            super.mouseClicked((int)mouseX, (int)mouseY, (int)mouseButton);
            if (mouseButton != false || mouseX < this.getX() || mouseX > this.getX() + this.getWidth() || mouseY < this.getY() || mouseY > this.getY() + this.getHeight()) break block0;
            this.setting.setValue(!this.setting.getValue());
        }
    }
}

