/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.europa.client.gui.click.components.impl;

import com.europa.Europa;
import com.europa.api.manager.value.impl.ValueColor;
import com.europa.api.utilities.render.RenderUtils;
import com.europa.client.gui.click.components.Component;
import com.europa.client.gui.click.components.impl.ModuleComponent;
import com.europa.client.modules.client.ModuleColor;
import java.awt.Color;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ColorComponent
extends Component {
    public ValueColor setting;
    public boolean open = false;
    public ResourceLocation alphaBG = new ResourceLocation("europa:alpha_texture.png");
    public boolean hueDragging;
    public float hueWidth;
    public boolean saturationDragging;
    public float satWidth;
    public boolean brightnessDragging;
    public float briWidth;
    public boolean alphaDragging;
    public float alphaWidth;

    public ColorComponent(ValueColor setting, ModuleComponent parent, int offset) {
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
        float[] hsb = Color.RGBtoHSB(this.setting.getValue().getRed(), this.setting.getValue().getGreen(), this.setting.getValue().getBlue(), null);
        Color color = Color.getHSBColor(hsb[0], Float.intBitsToFloat(Float.floatToIntBits(6.4887953f) ^ 0x7F4FA436), Float.intBitsToFloat(Float.floatToIntBits(4.629535f) ^ 0x7F142527));
        Gui.drawRect((int)this.getX(), (int)this.getY(), (int)(this.getX() + this.getWidth()), (int)(this.getY() + 14), (int)new Color(40, 40, 40).getRGB());
        Gui.drawRect((int)(this.getX() + this.getWidth() - 12), (int)(this.getY() + 2), (int)(this.getX() + this.getWidth() - 2), (int)(this.getY() + 12), (int)this.setting.getValue().getRGB());
        RenderUtils.drawOutline(this.getX() + this.getWidth() - 12, this.getY() + 2, this.getX() + this.getWidth() - 2, this.getY() + 12, Float.intBitsToFloat(Float.floatToIntBits(2.7144578f) ^ 0x7F2DB9AD), new Color(20, 20, 20).getRGB());
        if (this.open) {
            Gui.drawRect((int)this.getX(), (int)(this.getY() + 14), (int)(this.getX() + this.getWidth()), (int)(this.getY() + 28), (int)new Color(40, 40, 40).getRGB());
            float i = Float.intBitsToFloat(Float.floatToIntBits(1.3378998E38f) ^ 0x7EC94E07);
            while (i + Float.intBitsToFloat(Float.floatToIntBits(13.8331995f) ^ 0x7EDD54C9) < Float.intBitsToFloat(Float.floatToIntBits(0.07128618f) ^ 0x7F51FE7D)) {
                RenderUtils.drawRecta((float)(this.getX() + 2) + i, this.getY() + 16, Float.intBitsToFloat(Float.floatToIntBits(7.249331f) ^ 0x7F67FA85), Float.intBitsToFloat(Float.floatToIntBits(1.7045807f) ^ 0x7EEA2FB3), Color.getHSBColor(i / Float.intBitsToFloat(Float.floatToIntBits(0.115068644f) ^ 0x7F2BA91C), Float.intBitsToFloat(Float.floatToIntBits(4.3161592f) ^ 0x7F0A1DFA), Float.intBitsToFloat(Float.floatToIntBits(21.075346f) ^ 0x7E289A4F)).getRGB());
                i += Float.intBitsToFloat(Float.floatToIntBits(3.807338f) ^ 0x7E95CD0B);
            }
            RenderUtils.drawOutline(this.getX() + 2, this.getY() + 16, this.getX() + 2 + this.getWidth() - 4, this.getY() + 27, Float.intBitsToFloat(Float.floatToIntBits(2.7503529f) ^ 0x7F3005C8), new Color(0, 0, 0).getRGB());
            RenderUtils.drawRecta((float)(this.getX() + 2) + this.hueWidth, this.getY() + 16, Float.intBitsToFloat(Float.floatToIntBits(5.200255f) ^ 0x7F26687D), Float.intBitsToFloat(Float.floatToIntBits(1.2665411f) ^ 0x7E921E05), new Color(255, 255, 255).getRGB());
            Gui.drawRect((int)this.getX(), (int)(this.getY() + 28), (int)(this.getX() + this.getWidth()), (int)(this.getY() + 42), (int)new Color(40, 40, 40).getRGB());
            RenderUtils.drawSidewaysGradient(this.getX() + 2, this.getY() + 29, this.getWidth() - 4, Float.intBitsToFloat(Float.floatToIntBits(0.19645536f) ^ 0x7F792B98), new Color(255, 255, 255), color, 255, 255);
            RenderUtils.drawOutline(this.getX() + 2, this.getY() + 29, this.getX() + 2 + this.getWidth() - 4, this.getY() + 40, Float.intBitsToFloat(Float.floatToIntBits(103.69628f) ^ 0x7DCF647F), new Color(0, 0, 0).getRGB());
            RenderUtils.drawRecta((float)(this.getX() + 2) + this.satWidth, this.getY() + 29, Float.intBitsToFloat(Float.floatToIntBits(7.3489017f) ^ 0x7F6B2A34), Float.intBitsToFloat(Float.floatToIntBits(0.1948352f) ^ 0x7F7782E1), new Color(255, 255, 255).getRGB());
            Gui.drawRect((int)this.getX(), (int)(this.getY() + 42), (int)(this.getX() + this.getWidth()), (int)(this.getY() + 56), (int)new Color(40, 40, 40).getRGB());
            RenderUtils.drawSidewaysGradient(this.getX() + 2, this.getY() + 42, this.getWidth() - 4, Float.intBitsToFloat(Float.floatToIntBits(1.5246161f) ^ 0x7EF3269F), new Color(0, 0, 0), color, 255, 255);
            RenderUtils.drawOutline(this.getX() + 2, this.getY() + 42, this.getX() + 2 + this.getWidth() - 4, this.getY() + 53, Float.intBitsToFloat(Float.floatToIntBits(3.7803736f) ^ 0x7F71F1A4), new Color(0, 0, 0).getRGB());
            RenderUtils.drawRecta((float)(this.getX() + 2) + this.briWidth, this.getY() + 42, Float.intBitsToFloat(Float.floatToIntBits(8.346171f) ^ 0x7E8589EB), Float.intBitsToFloat(Float.floatToIntBits(0.08925866f) ^ 0x7C86CD3F), new Color(255, 255, 255).getRGB());
            Gui.drawRect((int)this.getX(), (int)(this.getY() + 56), (int)(this.getX() + this.getWidth()), (int)(this.getY() + 70), (int)new Color(40, 40, 40).getRGB());
            this.renderAlphaBG(this.getX() + 2, this.getY() + 55, this.alphaBG);
            RenderUtils.drawSidewaysGradient(this.getX() + 2, this.getY() + 55, this.getWidth() - 4, Float.intBitsToFloat(Float.floatToIntBits(0.13166903f) ^ 0x7F36D43F), new Color(0, 0, 0), color, 0, 255);
            RenderUtils.drawOutline(this.getX() + 2, this.getY() + 55, this.getX() + 2 + this.getWidth() - 4, this.getY() + 66, Float.intBitsToFloat(Float.floatToIntBits(19.69502f) ^ 0x7E9D8F67), new Color(0, 0, 0).getRGB());
            RenderUtils.drawRecta((float)(this.getX() + 2) + this.alphaWidth, this.getY() + 55, Float.intBitsToFloat(Float.floatToIntBits(6.702013f) ^ 0x7F5676E4), Float.intBitsToFloat(Float.floatToIntBits(0.13652846f) ^ 0x7F3BCE1E), new Color(255, 255, 255).getRGB());
            Gui.drawRect((int)this.getX(), (int)(this.getY() + 70), (int)(this.getX() + this.getWidth()), (int)(this.getY() + 84), (int)new Color(40, 40, 40).getRGB());
            Europa.FONT_MANAGER.drawString("Rainbow", this.getX() + 3, (float)(this.getY() + 78) - Europa.FONT_MANAGER.getHeight() / Float.intBitsToFloat(Float.floatToIntBits(0.8730777f) ^ 0x7F5F8205), Color.WHITE);
            Gui.drawRect((int)(this.getX() + this.getWidth() - 12), (int)(this.getY() + 72), (int)(this.getX() + this.getWidth() - 2), (int)(this.getY() + 82), (int)new Color(30, 30, 30).getRGB());
            if (this.setting.getRainbow().booleanValue()) {
                RenderUtils.prepareGL();
                GL11.glShadeModel((int)7425);
                GL11.glEnable((int)2848);
                GL11.glLineWidth((float)Float.intBitsToFloat(Float.floatToIntBits(0.2713932f) ^ 0x7EAAF40D));
                GL11.glBegin((int)1);
                GL11.glColor3f((float)((float)ModuleColor.getActualColor().getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.015137452f) ^ 0x7F070313)), (float)((float)ModuleColor.getActualColor().getGreen() / Float.intBitsToFloat(Float.floatToIntBits(1.1948546f) ^ 0x7CE7F0FF)), (float)((float)ModuleColor.getActualColor().getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.36357376f) ^ 0x7DC52657)));
                GL11.glVertex2d((double)(this.getX() + this.getWidth() - 8), (double)(this.getY() + 80));
                GL11.glColor3f((float)((float)ModuleColor.getActualColor().getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.015521388f) ^ 0x7F014D6B)), (float)((float)ModuleColor.getActualColor().getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.01025841f) ^ 0x7F5712E4)), (float)((float)ModuleColor.getActualColor().getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.10675689f) ^ 0x7EA5A35B)));
                GL11.glVertex2d((double)(this.getX() + this.getWidth() - 8 + 4), (double)(this.getY() + 74));
                GL11.glEnd();
                GL11.glBegin((int)1);
                GL11.glColor3f((float)((float)ModuleColor.getActualColor().getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.009417259f) ^ 0x7F654AD9)), (float)((float)ModuleColor.getActualColor().getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.07014828f) ^ 0x7EF0A9E7)), (float)((float)ModuleColor.getActualColor().getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.013465701f) ^ 0x7F239F3E)));
                GL11.glVertex2d((double)(this.getX() + this.getWidth() - 8), (double)(this.getY() + 80));
                GL11.glColor3f((float)((float)ModuleColor.getActualColor().getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.0155056f) ^ 0x7F010B33)), (float)((float)ModuleColor.getActualColor().getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.011914493f) ^ 0x7F3C3501)), (float)((float)ModuleColor.getActualColor().getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.012230922f) ^ 0x7F376434)));
                GL11.glVertex2d((double)(this.getX() + this.getWidth() - 10), (double)(this.getY() + 77));
                GL11.glEnd();
                RenderUtils.releaseGL();
            }
        }
        Gui.drawRect((int)(this.getX() - 1), (int)this.getY(), (int)this.getX(), (int)(this.getY() + 84), (int)new Color(30, 30, 30).getRGB());
        Gui.drawRect((int)(this.getX() + this.getWidth()), (int)this.getY(), (int)(this.getX() + this.getWidth() + 1), (int)(this.getY() + 84), (int)new Color(30, 30, 30).getRGB());
        Europa.FONT_MANAGER.drawString(this.setting.getName(), this.getX() + 3, this.getY() + 3, Color.WHITE);
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void mouseClicked(int n, int n2, int n3) {
        block4: {
            void mouseButton;
            void mouseY;
            void mouseX;
            block7: {
                block6: {
                    block5: {
                        block3: {
                            super.mouseClicked((int)mouseX, (int)mouseY, (int)mouseButton);
                            if (mouseX >= this.getX() && mouseX <= this.getX() + this.getWidth() && mouseY >= this.getY()) {
                                if (mouseY <= this.getY() + this.getHeight() && mouseButton == true) {
                                    boolean bl = this.open = !this.open;
                                }
                            }
                            if (!this.isMouseOnHue((int)mouseX, (int)mouseY) || mouseButton != false) break block3;
                            if (!this.open) break block3;
                            this.hueDragging = true;
                            break block4;
                        }
                        if (!this.isMouseOnSat((int)mouseX, (int)mouseY) || mouseButton != false) break block5;
                        if (!this.open) break block5;
                        this.saturationDragging = true;
                        break block4;
                    }
                    if (!this.isMouseOnBri((int)mouseX, (int)mouseY) || mouseButton != false || !this.open) break block6;
                    this.brightnessDragging = true;
                    break block4;
                }
                if (!this.isMouseOnAlpha((int)mouseX, (int)mouseY) || mouseButton != false) break block7;
                if (!this.open) break block7;
                this.alphaDragging = true;
                break block4;
            }
            if (!this.isMouseOnRainbow((int)mouseX, (int)mouseY) || mouseButton != false) break block4;
            if (this.open) {
                this.setting.setRainbow(this.setting.getRainbow() == false);
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void mouseReleased(int n, int n2, int n3) {
        void state;
        void mouseY;
        void mouseX;
        super.mouseReleased((int)mouseX, (int)mouseY, (int)state);
        this.hueDragging = false;
        this.saturationDragging = false;
        this.brightnessDragging = false;
        this.alphaDragging = false;
    }

    /*
     * WARNING - void declaration
     */
    public void renderAlphaBG(int n, int n2, ResourceLocation resourceLocation) {
        void y;
        void x;
        void texture;
        mc.getTextureManager().bindTexture((ResourceLocation)texture);
        GL11.glPushMatrix();
        GL11.glColor4f((float)Float.intBitsToFloat(Float.floatToIntBits(84.80346f) ^ 0x7D299B5F), (float)Float.intBitsToFloat(Float.floatToIntBits(356.26364f) ^ 0x7C3221BF), (float)Float.intBitsToFloat(Float.floatToIntBits(4.4841223f) ^ 0x7F0F7DEE), (float)Float.intBitsToFloat(Float.floatToIntBits(6.9323945f) ^ 0x7F5DD62D));
        Gui.drawScaledCustomSizeModalRect((int)x, (int)y, (float)Float.intBitsToFloat(Float.floatToIntBits(1.9387513E38f) ^ 0x7F11DAFE), (float)Float.intBitsToFloat(Float.floatToIntBits(1.7625584E38f) ^ 0x7F0499A4), (int)104, (int)16, (int)(this.getWidth() - 4), (int)11, (float)Float.intBitsToFloat(Float.floatToIntBits(0.112598404f) ^ 0x7F3699FE), (float)Float.intBitsToFloat(Float.floatToIntBits(0.60222334f) ^ 0x7E9A2B4F));
        GL11.glPopMatrix();
        GlStateManager.clear((int)256);
    }

    /*
     * WARNING - void declaration
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isMouseOnHue(int n, int n2) {
        void y;
        void x;
        if (x <= this.getX() + 2) return false;
        if (x >= this.getX() + 2 + this.getWidth() - 4) return false;
        if (y <= this.getY() + 16) return false;
        if (y >= this.getY() + 27) return false;
        return true;
    }

    /*
     * WARNING - void declaration
     */
    public boolean isMouseOnSat(int n, int n2) {
        void y;
        void x;
        return x > this.getX() + 2 && x < this.getX() + 2 + this.getWidth() - 4 && y > this.getY() + 29 && y < this.getY() + 40;
    }

    /*
     * WARNING - void declaration
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isMouseOnBri(int n, int n2) {
        void y;
        void x;
        if (x <= this.getX() + 2) return false;
        if (x >= this.getX() + 2 + this.getWidth() - 4) return false;
        if (y <= this.getY() + 42) return false;
        if (y >= this.getY() + 53) return false;
        return true;
    }

    /*
     * WARNING - void declaration
     */
    public boolean isMouseOnAlpha(int n, int n2) {
        void y;
        void x;
        return x > this.getX() + 2 && x < this.getX() + 2 + this.getWidth() - 4 && y > this.getY() + 55 && y < this.getY() + 66;
    }

    /*
     * WARNING - void declaration
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isMouseOnRainbow(int n, int n2) {
        void y;
        void x;
        if (x <= this.getX() + this.getWidth() - 12) return false;
        if (x >= this.getX() + this.getWidth() - 2) return false;
        if (y <= this.getY() + 72) return false;
        if (y >= this.getY() + 82) return false;
        return true;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void update(int n, int n2) {
        void mouseY;
        void mouseX;
        super.update((int)mouseX, (int)mouseY);
        float[] hsb = Color.RGBtoHSB(this.setting.getValue().getRed(), this.setting.getValue().getGreen(), this.setting.getValue().getBlue(), null);
        double difference = Math.min(95, Math.max(0, (int)(mouseX - this.getX())));
        this.hueWidth = Float.intBitsToFloat(Float.floatToIntBits(0.012939732f) ^ 0x7EEB012B) * (hsb[0] * Float.intBitsToFloat(Float.floatToIntBits(0.22324012f) ^ 0x7DD0990F) / Float.intBitsToFloat(Float.floatToIntBits(0.07544195f) ^ 0x7E2E814F));
        this.satWidth = Float.intBitsToFloat(Float.floatToIntBits(0.009555363f) ^ 0x7EA18E19) * (hsb[1] * Float.intBitsToFloat(Float.floatToIntBits(0.021556562f) ^ 0x7F049763) / Float.intBitsToFloat(Float.floatToIntBits(0.026331188f) ^ 0x7F63B481));
        this.briWidth = Float.intBitsToFloat(Float.floatToIntBits(0.02392782f) ^ 0x7E790447) * (hsb[2] * Float.intBitsToFloat(Float.floatToIntBits(0.09763377f) ^ 0x7E73F437) / Float.intBitsToFloat(Float.floatToIntBits(0.019418718f) ^ 0x7F2B1401));
        this.alphaWidth = Float.intBitsToFloat(Float.floatToIntBits(0.010174015f) ^ 0x7E9BB0E9) * ((float)this.setting.getValue().getAlpha() / Float.intBitsToFloat(Float.floatToIntBits(0.0089911735f) ^ 0x7F6C4FB7));
        this.changeColor(difference, new Color(Color.HSBtoRGB((float)(difference / Double.longBitsToDouble(Double.doubleToLongBits(0.15404371830294214) ^ 0x7F9477B45E21F7BFL) * Double.longBitsToDouble(Double.doubleToLongBits(0.050973544293479105) ^ 0x7FDC99345367453FL) / Double.longBitsToDouble(Double.doubleToLongBits(0.03014217321508198) ^ 0x7FE85D9700C1AF0AL)), hsb[1], hsb[2])), new Color(Color.HSBtoRGB(Float.intBitsToFloat(Float.floatToIntBits(1.8279414E38f) ^ 0x7F0984DF), hsb[1], hsb[2])), this.hueDragging);
        this.changeColor(difference, new Color(Color.HSBtoRGB(hsb[0], (float)(difference / Double.longBitsToDouble(Double.doubleToLongBits(0.1223112785883676) ^ 0x7FE88FCABD780F54L) * Double.longBitsToDouble(Double.doubleToLongBits(0.026943886254004668) ^ 0x7FED172D9927021DL) / Double.longBitsToDouble(Double.doubleToLongBits(0.05427001644334754) ^ 0x7FDD4947938E1C55L)), hsb[2])), new Color(Color.HSBtoRGB(hsb[0], Float.intBitsToFloat(Float.floatToIntBits(1.1082437E38f) ^ 0x7EA6BFFF), hsb[2])), this.saturationDragging);
        this.changeColor(difference, new Color(Color.HSBtoRGB(hsb[0], hsb[1], (float)(difference / Double.longBitsToDouble(Double.doubleToLongBits(0.12328622126775308) ^ 0x7FE84FAF90647595L) * Double.longBitsToDouble(Double.doubleToLongBits(0.09854681448488288) ^ 0x7FCFBA5D315669BFL) / Double.longBitsToDouble(Double.doubleToLongBits(0.029067112480345214) ^ 0x7FEB43C4E5F80CC0L)))), new Color(Color.HSBtoRGB(hsb[0], hsb[1], Float.intBitsToFloat(Float.floatToIntBits(3.3573391E38f) ^ 0x7F7C9400))), this.brightnessDragging);
        this.changeAlpha(difference, (float)(difference / Double.longBitsToDouble(Double.doubleToLongBits(0.014823398455503097) ^ 0x7FD99BBADCA7DC11L) * Double.longBitsToDouble(Double.doubleToLongBits(0.013271171619186513) ^ 0x7FE4CDEA80AC0D24L) / Double.longBitsToDouble(Double.doubleToLongBits(0.08218747250746601) ^ 0x7FDAEA3CFA8F7AADL)), this.alphaDragging);
    }

    /*
     * WARNING - void declaration
     */
    public void changeColor(double d, Color color, Color color2, boolean bl) {
        block2: {
            void difference;
            void dragging;
            if (dragging == false) break block2;
            if (difference == Double.longBitsToDouble(Double.doubleToLongBits(1.2749872908217061E308) ^ 0x7FE6B20E10D32E17L)) {
                void zeroColor;
                this.setting.setValue(new Color(zeroColor.getRed(), zeroColor.getGreen(), zeroColor.getBlue(), this.setting.getValue().getAlpha()));
            } else {
                void color3;
                this.setting.setValue(new Color(color3.getRed(), color3.getGreen(), color3.getBlue(), this.setting.getValue().getAlpha()));
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    public void changeAlpha(double d, float f, boolean bl) {
        block2: {
            void difference;
            void dragging;
            if (dragging == false) break block2;
            if (difference == Double.longBitsToDouble(Double.doubleToLongBits(7.977172206938858E307) ^ 0x7FDC6651265A7509L)) {
                this.setting.setValue(new Color(this.setting.getValue().getRed(), this.setting.getValue().getGreen(), this.setting.getValue().getBlue(), 0));
            } else {
                void alpha;
                this.setting.setValue(new Color(this.setting.getValue().getRed(), this.setting.getValue().getGreen(), this.setting.getValue().getBlue(), (int)(alpha * Float.intBitsToFloat(Float.floatToIntBits(0.015395311f) ^ 0x7F033C9D))));
            }
        }
    }
}

