/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.util.ChatAllowedCharacters
 *  net.minecraftforge.common.MinecraftForge
 *  org.lwjgl.input.Keyboard
 */
package com.europa.client.gui.click.components.impl;

import com.europa.Europa;
import com.europa.api.manager.value.impl.ValueString;
import com.europa.api.utilities.math.TimerUtils;
import com.europa.client.gui.click.components.Component;
import com.europa.client.gui.click.components.impl.ModuleComponent;
import com.europa.client.modules.client.ModuleColor;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.input.Keyboard;

public class StringComponent
extends Component {
    public ValueString setting;
    public boolean listening;
    public String currentString = "";
    public TimerUtils timer = new TimerUtils();
    public TimerUtils backTimer = new TimerUtils();
    public TimerUtils deleteTimer = new TimerUtils();
    public boolean selecting = false;
    public boolean undoing = false;

    public StringComponent(ValueString setting, ModuleComponent parent, int offset) {
        super(parent.getParent().getX(), parent.getParent().getY() + offset, parent.getParent());
        this.setting = setting;
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void drawScreen(int n, int n2) {
        void mouseY;
        void mouseX;
        super.drawScreen((int)mouseX, (int)mouseY);
        if (this.timer.hasReached((long)34125802 ^ 0x208B67AL)) {
            this.undoing = !this.undoing;
            this.timer.reset();
        }
        Gui.drawRect((int)this.getX(), (int)this.getY(), (int)(this.getX() + this.getWidth()), (int)(this.getY() + 14), (int)new Color(40, 40, 40).getRGB());
        Gui.drawRect((int)(this.getX() - 1), (int)this.getY(), (int)this.getX(), (int)(this.getY() + 14), (int)new Color(30, 30, 30).getRGB());
        Gui.drawRect((int)(this.getX() + this.getWidth()), (int)this.getY(), (int)(this.getX() + this.getWidth() + 1), (int)(this.getY() + 14), (int)new Color(30, 30, 30).getRGB());
        Gui.drawRect((int)(this.getX() + 1), (int)(this.getY() + 1), (int)(this.getX() + this.getWidth() - 1), (int)(this.getY() + 13), (int)new Color(30, 30, 30).getRGB());
        if (this.selecting) {
            Gui.drawRect((int)(this.getX() + 3), (int)(this.getY() + 3), (int)((int)((float)(this.getX() + 3) + Europa.FONT_MANAGER.getStringWidth(this.currentString))), (int)((int)((float)this.getY() + Europa.FONT_MANAGER.getHeight() + Float.intBitsToFloat(Float.floatToIntBits(0.39460382f) ^ 0x7E8A0983))), (int)new Color(ModuleColor.getActualColor().getRed(), ModuleColor.getActualColor().getGreen(), ModuleColor.getActualColor().getBlue(), 100).getRGB());
        }
        if (this.listening) {
            Europa.FONT_MANAGER.drawString(this.currentString + (this.selecting ? "" : (this.undoing ? (Europa.MODULE_MANAGER.isModuleEnabled("Font") ? "|" : "\u23d0") : "")), this.getX() + 3, this.getY() + 3, Color.LIGHT_GRAY);
        } else {
            Europa.FONT_MANAGER.drawString(this.setting.getValue(), this.getX() + 3, this.getY() + 3, Color.LIGHT_GRAY);
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
        if (mouseButton == false) {
            if (mouseX >= this.getX() && mouseX <= this.getX() + this.getWidth() && mouseY >= this.getY() && mouseY <= this.getY() + this.getHeight()) {
                this.listening = !this.listening;
                this.currentString = this.setting.getValue();
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
        if (this.listening) {
            if (keyCode == true) {
                this.selecting = false;
                return;
            }
            if (keyCode == 28) {
                this.updateString();
                this.selecting = false;
                this.listening = false;
            } else if (keyCode == 14) {
                this.currentString = this.selecting ? "" : this.removeLastCharacter(this.currentString);
                this.selecting = false;
            } else if (keyCode == 47 && (Keyboard.isKeyDown((int)157) || Keyboard.isKeyDown((int)29))) {
                StringBuilder stringBuilder;
                StringComponent stringComponent = this;
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
                    stringComponent.currentString = string2;
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

    public void updateString() {
        if (this.currentString.length() > 0) {
            this.setting.setValue(this.currentString);
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
}

