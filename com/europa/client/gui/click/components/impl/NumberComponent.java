/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.util.ChatAllowedCharacters
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.opengl.GL11
 */
package com.europa.client.gui.click.components.impl;

import com.europa.Europa;
import com.europa.api.manager.value.impl.ValueNumber;
import com.europa.api.utilities.math.AnimationUtils;
import com.europa.api.utilities.math.MathUtils;
import com.europa.api.utilities.math.TimerUtils;
import com.europa.client.gui.click.components.Component;
import com.europa.client.gui.click.components.impl.ModuleComponent;
import com.europa.client.modules.client.ModuleColor;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class NumberComponent
extends Component {
    public ValueNumber setting;
    public double sliderWidth;
    public boolean dragging;
    public boolean typing;
    public String currentString = "";
    public boolean selecting = false;
    public TimerUtils backTimer = new TimerUtils();
    public boolean undoing = false;
    public TimerUtils timer = new TimerUtils();
    public AnimationUtils animationUtils;
    public boolean animated = false;

    public NumberComponent(ValueNumber setting, ModuleComponent parent, int offset) {
        super(parent.getParent().getX(), parent.getParent().getY() + offset, parent.getParent());
        this.setting = setting;
        this.dragging = false;
        this.typing = false;
        this.animationUtils = new AnimationUtils((long)-1770284037 ^ 0xFFFFFFFF967B9A0FL, Float.intBitsToFloat(Float.floatToIntBits(9.933269E37f) ^ 0x7E95758F), (float)(this.getWidth() - 1));
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void drawScreen(int n, int n2) {
        void mouseY;
        void mouseX;
        super.drawScreen((int)mouseX, (int)mouseY);
        if (this.timer.hasReached((long)1320065972 ^ 0x4EAE9A24L)) {
            this.undoing = !this.undoing;
            this.timer.reset();
        }
        Gui.drawRect((int)this.getX(), (int)this.getY(), (int)(this.getX() + this.getWidth()), (int)(this.getY() + 14), (int)new Color(40, 40, 40).getRGB());
        Gui.drawRect((int)(this.getX() + 1), (int)(this.getY() + 1), (int)(this.getX() + this.getWidth() - 1), (int)(this.getY() + 13), (int)new Color(50, 50, 50).getRGB());
        if (!this.typing) {
            Gui.drawRect((int)(this.getX() + 1), (int)(this.getY() + 1), (int)((int)((double)(this.getX() + 1) + this.sliderWidth)), (int)(this.getY() + 13), (int)ModuleColor.getColor());
        }
        Gui.drawRect((int)(this.getX() - 1), (int)this.getY(), (int)this.getX(), (int)(this.getY() + 14), (int)new Color(30, 30, 30).getRGB());
        Gui.drawRect((int)(this.getX() + this.getWidth()), (int)this.getY(), (int)(this.getX() + this.getWidth() + 1), (int)(this.getY() + 14), (int)new Color(30, 30, 30).getRGB());
        Europa.FONT_MANAGER.drawString(this.setting.getName(), this.getX() + 3, this.getY() + 3, Color.WHITE);
        Europa.FONT_MANAGER.drawString(this.setting.getValue() + (this.setting.getType() == 1 ? ".0" : ""), (float)(this.getX() + this.getWidth() - 3) - Europa.FONT_MANAGER.getStringWidth(this.setting.getValue() + (this.setting.getType() == 1 ? ".0" : "")), this.getY() + 3, Color.WHITE);
        if (!this.typing) {
            Europa.FONT_MANAGER.drawString(this.setting.getName(), this.getX() + 3, this.getY() + 3, Color.WHITE);
            Europa.FONT_MANAGER.drawString(this.setting.getValue() + (this.setting.getType() == 1 ? ".0" : ""), (float)(this.getX() + this.getWidth() - 3) - Europa.FONT_MANAGER.getStringWidth(this.setting.getValue() + (this.setting.getType() == 1 ? ".0" : "")), this.getY() + 3, Color.WHITE);
            this.animated = false;
        } else {
            if (!this.animated) {
                this.animationUtils.reset();
            }
            if (!this.animationUtils.isDone()) {
                GL11.glEnable((int)3089);
                NumberComponent.scissor(this.getX() + 1, this.getY() + 1, this.getWidth(), Double.longBitsToDouble(Double.doubleToLongBits(0.23670126609511655) ^ 0x7FE44C3A226670DCL));
                NumberComponent.drawRect(this.getX() + 1, this.getY() + 1, (float)(this.getX() + 1) + (float)this.sliderWidth - this.animationUtils.getValue(), this.getY() + 13, ModuleColor.getColor());
                GL11.glDisable((int)3089);
            }
            this.animated = true;
            Europa.FONT_MANAGER.drawString(this.currentString + (this.selecting ? "" : (this.undoing ? (Europa.MODULE_MANAGER.isModuleEnabled("Font") ? "|" : "\u23d0") : "")), (float)this.getX() + (float)(this.getWidth() - 1) / Float.intBitsToFloat(Float.floatToIntBits(0.37152806f) ^ 0x7EBE38ED) - Europa.FONT_MANAGER.getStringWidth(this.currentString) / Float.intBitsToFloat(Float.floatToIntBits(0.6535927f) ^ 0x7F2751DA), this.getY() + 3, Color.WHITE);
        }
    }

    /*
     * WARNING - void declaration
     * Enabled aggressive block sorting
     */
    @Override
    public void mouseClicked(int n, int n2, int n3) {
        void mouseButton;
        void mouseY;
        void mouseX;
        super.mouseClicked((int)mouseX, (int)mouseY, (int)mouseButton);
        if (mouseButton == false && mouseX >= this.getX() && mouseX <= this.getX() + this.getWidth() && mouseY >= this.getY() + 2 && mouseY <= this.getY() + this.getHeight() - 2) {
            this.dragging = true;
            return;
        }
        if (mouseButton != true) return;
        if (mouseX < this.getX()) return;
        if (mouseX > this.getX() + this.getWidth()) return;
        if (mouseY < this.getY() + 2) return;
        if (mouseY > this.getY() + this.getHeight() - 2) return;
        this.typing = !this.typing;
        this.currentString = this.setting.getValue().toString();
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
        this.dragging = false;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void update(int n, int n2) {
        block8: {
            double difference;
            block9: {
                block7: {
                    void mouseX;
                    difference = Math.min(98, Math.max(0, (int)(mouseX - this.getX())));
                    if (this.setting.getType() != 1) break block7;
                    this.sliderWidth = Float.intBitsToFloat(Float.floatToIntBits(0.014527884f) ^ 0x7EAA065D) * (float)(this.setting.getValue().intValue() - this.setting.getMinimum().intValue()) / (float)(this.setting.getMaximum().intValue() - this.setting.getMinimum().intValue());
                    if (!this.dragging) break block8;
                    if (difference == Double.longBitsToDouble(Double.doubleToLongBits(1.4007454973032137E308) ^ 0x7FE8EF2103A723F1L)) {
                        this.setting.setValue(this.setting.getMinimum());
                    } else {
                        int value = (int)MathUtils.roundToPlaces(difference / Double.longBitsToDouble(Double.doubleToLongBits(0.014794065701436805) ^ 0x7FD6CC59E2E67EB7L) * (double)(this.setting.getMaximum().intValue() - this.setting.getMinimum().intValue()) + (double)this.setting.getMinimum().intValue(), 0);
                        this.setting.setValue(value);
                    }
                    break block8;
                }
                if (this.setting.getType() != 2) break block9;
                this.sliderWidth = Double.longBitsToDouble(Double.doubleToLongBits(0.06935230413301949) ^ 0x7FE9411296274FFEL) * (this.setting.getValue().doubleValue() - this.setting.getMinimum().doubleValue()) / (this.setting.getMaximum().doubleValue() - this.setting.getMinimum().doubleValue());
                if (!this.dragging) break block8;
                if (difference == Double.longBitsToDouble(Double.doubleToLongBits(1.3453966521905491E308) ^ 0x7FE7F2E82F57B15AL)) {
                    this.setting.setValue(this.setting.getMinimum());
                } else {
                    double value = MathUtils.roundToPlaces(difference / Double.longBitsToDouble(Double.doubleToLongBits(0.10167540468383177) ^ 0x7FE2876639ECB412L) * (this.setting.getMaximum().doubleValue() - this.setting.getMinimum().doubleValue()) + this.setting.getMinimum().doubleValue(), 2);
                    this.setting.setValue(value);
                }
                break block8;
            }
            if (this.setting.getType() != 3) break block8;
            this.sliderWidth = Float.intBitsToFloat(Float.floatToIntBits(0.011726105f) ^ 0x7E841ED9) * (this.setting.getValue().floatValue() - this.setting.getMinimum().floatValue()) / (this.setting.getMaximum().floatValue() - this.setting.getMinimum().floatValue());
            if (this.dragging) {
                if (difference == Double.longBitsToDouble(Double.doubleToLongBits(1.402772054672317E308) ^ 0x7FE8F85D2793C1F3L)) {
                    this.setting.setValue(this.setting.getMinimum());
                } else {
                    float value = (float)MathUtils.roundToPlaces(difference / Double.longBitsToDouble(Double.doubleToLongBits(0.0639820208900176) ^ 0x7FE8E1202F412E69L) * (double)(this.setting.getMaximum().floatValue() - this.setting.getMinimum().floatValue()) + (double)this.setting.getMinimum().floatValue(), 2);
                    this.setting.setValue(Float.valueOf(value));
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
        this.backTimer.reset();
        if (this.typing) {
            if (keyCode == true) {
                this.selecting = false;
                return;
            }
            if (keyCode == 28) {
                this.updateString();
                this.selecting = false;
                this.typing = false;
            } else if (keyCode == 14) {
                this.currentString = this.selecting ? "" : this.removeLastCharacter(this.currentString);
                this.selecting = false;
            } else if (keyCode == 47 && (Keyboard.isKeyDown((int)157) || Keyboard.isKeyDown((int)29))) {
                StringBuilder stringBuilder;
                NumberComponent numberComponent = this;
                StringBuilder stringBuilder2 = stringBuilder;
                StringBuilder stringBuilder3 = stringBuilder;
                stringBuilder2();
                String string = this.currentString;
                StringBuilder stringBuilder4 = stringBuilder3.append(string);
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Clipboard clipboard = toolkit.getSystemClipboard();
                DataFlavor dataFlavor = DataFlavor.stringFlavor;
                Object object = clipboard.getData(dataFlavor);
                StringBuilder stringBuilder5 = stringBuilder4.append(object);
                String string2 = stringBuilder5.toString();
                try {
                    numberComponent.currentString = string2;
                }
                catch (Exception exception) {
                    exception.printStackTrace();
                }
            } else if (ChatAllowedCharacters.isAllowedCharacter((char)typedChar)) {
                this.currentString = this.selecting ? "" + (char)typedChar : this.currentString + (char)typedChar;
                this.selecting = false;
            }
            if (keyCode == 30 && Keyboard.isKeyDown((int)29)) {
                this.selecting = true;
            }
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public void updateString() {
        block69: {
            block71: {
                block70: {
                    if (this.currentString.length() <= 0) break block69;
                    if (this.setting.getType() != 1) break block70;
                    v0 = Integer.parseInt(this.currentString);
                    v1 = this.setting;
                    v2 = v1.getMaximum();
                    v3 = v2.intValue();
                    if (v0 > v3) ** GOTO lbl34
                    v4 = this.currentString;
                    v5 = Integer.parseInt(v4);
                    v6 = this.setting;
                    v7 = v6.getMinimum();
                    v8 = v7.intValue();
                    if (v5 < v8) ** GOTO lbl34
                    v9 = this.setting;
                    v10 = this.currentString;
                    v11 = Integer.parseInt(v10);
                    v12 = v11;
                    v9.setValue(v12);
                    break block69;
lbl34:
                    // 2 sources

                    v13 = this.setting;
                    v14 = this.setting;
                    v15 = v14.getValue();
                    try {
                        v13.setValue(v15);
                    }
                    catch (NumberFormatException e) {
                        this.setting.setValue(this.setting.getValue());
                    }
                    break block69;
                }
                if (this.setting.getType() != 3) break block71;
                v16 = Float.parseFloat(this.currentString);
                v17 = this.setting;
                v18 = v17.getMaximum();
                v19 = v18.floatValue();
                if (v16 > v19) ** GOTO lbl76
                v20 = this.currentString;
                v21 = Float.parseFloat(v20);
                v22 = this.setting;
                v23 = v22.getMinimum();
                v24 = v23.floatValue();
                if (v21 < v24) ** GOTO lbl76
                v25 = this.setting;
                v26 = this.currentString;
                v27 = Float.parseFloat(v26);
                v28 = Float.valueOf(v27);
                v25.setValue(v28);
                break block69;
lbl76:
                // 2 sources

                v29 = this.setting;
                v30 = this.setting;
                v31 = v30.getValue();
                try {
                    v29.setValue(v31);
                }
                catch (NumberFormatException e) {
                    this.setting.setValue(this.setting.getValue());
                }
                break block69;
            }
            if (this.setting.getType() != 2) break block69;
            v32 = Double.parseDouble(this.currentString);
            v33 = this.setting;
            v34 = v33.getMaximum();
            v35 = v34.doubleValue();
            if (v32 > v35) ** GOTO lbl118
            v36 = this.currentString;
            v37 = Double.parseDouble(v36);
            v38 = this.setting;
            v39 = v38.getMinimum();
            v40 = v39.doubleValue();
            if (v37 < v40) ** GOTO lbl118
            v41 = this.setting;
            v42 = this.currentString;
            v43 = Double.parseDouble(v42);
            v44 = v43;
            v41.setValue(v44);
            break block69;
lbl118:
            // 2 sources

            v45 = this.setting;
            v46 = this.setting;
            v47 = v46.getValue();
            try {
                v45.setValue(v47);
            }
            catch (NumberFormatException e) {
                this.setting.setValue(this.setting.getValue());
            }
        }
        this.currentString = "";
    }

    /*
     * WARNING - void declaration
     */
    public String removeLastCharacter(String string) {
        void input;
        if (input.length() > 0) {
            return input.substring(0, input.length() - 1);
        }
        return input;
    }

    /*
     * WARNING - void declaration
     */
    public static void drawRect(float f, float f2, float f3, float f4, int n) {
        void color;
        float bottom;
        void top;
        float j;
        float right;
        float left;
        if (left < right) {
            j = left;
            left = right;
            right = j;
        }
        if (top < bottom) {
            j = top;
            top = bottom;
            bottom = j;
        }
        float f32 = (float)(color >> 24 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.015153162f) ^ 0x7F0744F8);
        float f5 = (float)(color >> 16 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.10281151f) ^ 0x7EAD8ED7);
        float f1 = (float)(color >> 8 & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.049893465f) ^ 0x7E335D17);
        float f22 = (float)(color & 0xFF) / Float.intBitsToFloat(Float.floatToIntBits(0.097316556f) ^ 0x7EB84DE7);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferbuilder = tessellator.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.color((float)f5, (float)f1, (float)f22, (float)f32);
        bufferbuilder.begin(7, DefaultVertexFormats.POSITION);
        bufferbuilder.pos((double)left, (double)bottom, Double.longBitsToDouble(Double.doubleToLongBits(1.019881991218291E307) ^ 0x7FAD0C1583E04CDFL)).endVertex();
        bufferbuilder.pos((double)right, (double)bottom, Double.longBitsToDouble(Double.doubleToLongBits(1.7481753651024542E308) ^ 0x7FEF1E599571C332L)).endVertex();
        bufferbuilder.pos((double)right, (double)top, Double.longBitsToDouble(Double.doubleToLongBits(1.1079463692462994E307) ^ 0x7FAF8E2C134985AFL)).endVertex();
        bufferbuilder.pos((double)left, (double)top, Double.longBitsToDouble(Double.doubleToLongBits(3.929654556867817E307) ^ 0x7FCBFAE5D0CB7E47L)).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    /*
     * WARNING - void declaration
     */
    public static void scissor(double d, double d2, double d3, double d4) {
        void height;
        double y;
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        double scale = sr.getScaleFactor();
        y = (double)sr.getScaledHeight() - y;
        GL11.glScissor((int)((int)(x *= scale)), (int)((int)((y *= scale) - (height *= scale))), (int)((int)(width *= scale)), (int)((int)height));
    }
}

