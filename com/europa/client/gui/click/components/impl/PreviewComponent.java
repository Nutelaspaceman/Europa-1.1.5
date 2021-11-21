/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package com.europa.client.gui.click.components.impl;

import com.europa.Europa;
import com.europa.api.manager.value.impl.ValuePreview;
import com.europa.api.utilities.render.RenderUtils;
import com.europa.client.gui.click.components.Component;
import com.europa.client.gui.click.components.impl.ModuleComponent;
import java.awt.Color;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class PreviewComponent
extends Component {
    public ValuePreview setting;
    public static EntityEnderCrystal entityEnderCrystal;
    public boolean open = false;

    public PreviewComponent(ValuePreview setting, ModuleComponent parent, int offset) {
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
        Entity entity = this.setting.getEntity();
        Gui.drawRect((int)this.getX(), (int)this.getY(), (int)(this.getX() + this.getWidth()), (int)(this.getY() + (this.open ? 100 : 14)), (int)new Color(40, 40, 40).getRGB());
        Gui.drawRect((int)(this.getX() - 1), (int)this.getY(), (int)this.getX(), (int)(this.getY() + (this.open ? 100 : 14)), (int)new Color(30, 30, 30).getRGB());
        Gui.drawRect((int)(this.getX() + this.getWidth()), (int)this.getY(), (int)(this.getX() + this.getWidth() + 1), (int)(this.getY() + (this.open ? 100 : 14)), (int)new Color(30, 30, 30).getRGB());
        if (this.open) {
            if (entity instanceof EntityEnderCrystal) {
                EntityEnderCrystal ent;
                entityEnderCrystal = ent = new EntityEnderCrystal((World)PreviewComponent.mc.world, Double.longBitsToDouble(Double.doubleToLongBits(9.310613315809524E306) ^ 0x7FAA847B55B02A7FL), Double.longBitsToDouble(Double.doubleToLongBits(1.7125394916952668E308) ^ 0x7FEE7BF580E967CDL), Double.longBitsToDouble(Double.doubleToLongBits(1.351057559302745E308) ^ 0x7FE80CB4154FF45AL));
                ent.setShowBottom(false);
                ent.rotationYaw = Float.intBitsToFloat(Float.floatToIntBits(1.1630837E38f) ^ 0x7EAF005B);
                ent.rotationPitch = Float.intBitsToFloat(Float.floatToIntBits(2.1111544E38f) ^ 0x7F1ED35B);
                ent.innerRotation = 0;
                ent.prevRotationYaw = Float.intBitsToFloat(Float.floatToIntBits(3.176926E38f) ^ 0x7F6F015F);
                ent.prevRotationPitch = Float.intBitsToFloat(Float.floatToIntBits(2.4984888E38f) ^ 0x7F3BF725);
                if (ent != null) {
                    GL11.glScalef((float)Float.intBitsToFloat(Float.floatToIntBits(6.72125f) ^ 0x7F57147B), (float)Float.intBitsToFloat(Float.floatToIntBits(8.222657f) ^ 0x7E839001), (float)Float.intBitsToFloat(Float.floatToIntBits(7.82415f) ^ 0x7F7A5F70));
                    RenderUtils.drawEntityOnScreen((Entity)ent, this.getX() + this.getWidth() / 2, this.getY() + 90, 40, Float.intBitsToFloat(Float.floatToIntBits(4.219836E36f) ^ 0x7C4B2D7F), Float.intBitsToFloat(Float.floatToIntBits(8.549953E37f) ^ 0x7E80A539));
                }
            }
        }
        Europa.FONT_MANAGER.drawString(this.setting.getName(), this.getX() + 3, this.getY() + 3, Color.WHITE);
        Europa.FONT_MANAGER.drawString(this.open ? "-" : "+", (float)(this.getX() + this.getWidth() - 3) - Europa.FONT_MANAGER.getStringWidth("+"), this.getY() + 3, Color.WHITE);
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
        if (mouseButton == true && mouseX >= this.getX() && mouseX <= this.getX() + this.getWidth() && mouseY >= this.getY() && mouseY <= this.getY() + this.getHeight()) {
            this.open = !this.open;
        }
    }
}

