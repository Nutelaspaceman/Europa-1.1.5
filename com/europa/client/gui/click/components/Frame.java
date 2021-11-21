/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 */
package com.europa.client.gui.click.components;

import com.europa.Europa;
import com.europa.api.manager.element.Element;
import com.europa.api.manager.module.Module;
import com.europa.api.manager.module.ModuleCategory;
import com.europa.client.gui.click.ClickGuiScreen;
import com.europa.client.gui.click.components.Component;
import com.europa.client.gui.click.components.impl.ColorComponent;
import com.europa.client.gui.click.components.impl.ModuleComponent;
import com.europa.client.gui.click.components.impl.PreviewComponent;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

public class Frame {
    public static Minecraft mc = Minecraft.getMinecraft();
    public ArrayList<Component> buttons;
    public String tab;
    public int x;
    public int y;
    public int dragX;
    public int dragY;
    public int width;
    public int height;
    public boolean open = true;
    public boolean dragging;

    public Frame(ModuleCategory category, int x, int y) {
        this.tab = category.getName();
        this.x = x;
        this.y = y;
        this.width = 100;
        this.height = 0;
        this.dragX = 0;
        this.dragY = 0;
        this.dragging = false;
        this.buttons = new ArrayList();
        int offset = 14;
        for (Module module : Europa.MODULE_MANAGER.getModules(category)) {
            this.buttons.add(new ModuleComponent(module, x, y + offset, this));
            offset += 14;
        }
        this.height = offset;
        this.refreshPosition();
    }

    public Frame(int x, int y) {
        this.tab = "HUD";
        this.x = x;
        this.y = y;
        this.width = 100;
        this.height = 0;
        this.dragX = 0;
        this.dragY = 0;
        this.dragging = false;
        this.buttons = new ArrayList();
        int offset = 14;
        for (Element element : Europa.ELEMENT_MANAGER.getElements()) {
            this.buttons.add(new ModuleComponent(element, x, y + offset, this));
            offset += 14;
        }
        this.height = offset;
        this.refreshPosition();
    }

    /*
     * WARNING - void declaration
     */
    public void drawScreen(int n, int n2) {
        Gui.drawRect((int)this.x, (int)this.y, (int)(this.x + this.width), (int)(this.y + 14), (int)new Color(30, 30, 30).getRGB());
        Gui.drawRect((int)(this.x - 1), (int)this.y, (int)this.x, (int)(this.y + 14), (int)new Color(30, 30, 30).getRGB());
        Gui.drawRect((int)(this.x + this.width), (int)this.y, (int)(this.x + this.width + 1), (int)(this.y + 14), (int)new Color(30, 30, 30).getRGB());
        if (this.open) {
            Gui.drawRect((int)(this.x - 1), (int)(this.y + this.height), (int)(this.x + this.width + 1), (int)(this.y + this.height + 1), (int)new Color(30, 30, 30).getRGB());
        }
        Europa.FONT_MANAGER.drawString(ClickGuiScreen.capitalize(this.tab), this.x + 3, this.y + 3, Color.WHITE);
        if (this.open) {
            for (Component button : this.buttons) {
                void mouseY;
                void mouseX;
                button.drawScreen((int)mouseX, (int)mouseY);
                button.update((int)mouseX, (int)mouseY);
                if (!(button instanceof ModuleComponent)) continue;
                ModuleComponent moduleButton = (ModuleComponent)button;
                if (!moduleButton.isOpen()) continue;
                for (Component settingButton : moduleButton.getSubButtons()) {
                    settingButton.drawScreen((int)mouseX, (int)mouseY);
                    settingButton.update((int)mouseX, (int)mouseY);
                }
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    public void mouseClicked(int n, int n2, int n3) {
        void mouseButton;
        void mouseY;
        void mouseX;
        if (this.isMouseOnHeader((int)mouseX, (int)mouseY) && mouseButton == false) {
            this.setDragging(true);
            this.dragX = mouseX - this.getX();
            this.dragY = mouseY - this.getY();
        }
        if (mouseButton == true && this.isMouseOnHeader((int)mouseX, (int)mouseY)) {
            boolean bl = !this.open ? true : (this.open = false);
            if (this.open) {
                int offset = 14;
                for (Component ignored : this.buttons) {
                    offset += 14;
                }
                this.height = offset;
            } else {
                this.height = 14;
            }
        }
        for (Component button : this.buttons) {
            ModuleComponent moduleButton;
            button.mouseClicked((int)mouseX, (int)mouseY, (int)mouseButton);
            if (!(button instanceof ModuleComponent) || !(moduleButton = (ModuleComponent)button).isOpen()) continue;
            for (Component settingButton : moduleButton.getSubButtons()) {
                settingButton.mouseClicked((int)mouseX, (int)mouseY, (int)mouseButton);
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    public void mouseReleased(int n, int n2, int n3) {
        this.setDragging(false);
        for (Component button : this.buttons) {
            ModuleComponent moduleButton;
            void state;
            void mouseY;
            void mouseX;
            button.mouseReleased((int)mouseX, (int)mouseY, (int)state);
            if (!(button instanceof ModuleComponent) || !(moduleButton = (ModuleComponent)button).isOpen()) continue;
            for (Component settingButton : moduleButton.getSubButtons()) {
                settingButton.mouseReleased((int)mouseX, (int)mouseY, (int)state);
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    public void keyTyped(char c, int n) {
        for (Component button : this.buttons) {
            void key;
            void typedChar;
            button.keyTyped((char)typedChar, (int)key);
            if (!(button instanceof ModuleComponent)) continue;
            ModuleComponent moduleButton = (ModuleComponent)button;
            if (!moduleButton.isOpen()) continue;
            for (Component settingButton : moduleButton.getSubButtons()) {
                settingButton.keyTyped((char)typedChar, (int)key);
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    public void updatePosition(int n, int n2) {
        block0: {
            void mouseY;
            void mouseX;
            if (!this.dragging) break block0;
            this.setX((int)(mouseX - this.dragX));
            this.setY((int)(mouseY - this.dragY));
        }
    }

    public void refreshPosition() {
        int offset = 14;
        for (Component button : this.buttons) {
            ModuleComponent moduleButton;
            button.setX(this.x);
            button.setY(this.y + offset);
            offset += 14;
            if (!(button instanceof ModuleComponent) || !(moduleButton = (ModuleComponent)button).isOpen()) continue;
            for (Component settingButton : moduleButton.getSubButtons()) {
                settingButton.setX(this.x);
                settingButton.setY(this.y + offset);
                if (settingButton instanceof ColorComponent) {
                    if (((ColorComponent)settingButton).open) {
                        offset += 84;
                        continue;
                    }
                    offset += 14;
                    continue;
                }
                if (settingButton instanceof PreviewComponent) {
                    if (((PreviewComponent)settingButton).open) {
                        offset += 100;
                        continue;
                    }
                    offset += 14;
                    continue;
                }
                offset += 14;
            }
        }
        this.height = offset;
    }

    /*
     * WARNING - void declaration
     */
    public boolean isMouseOnHeader(int n, int n2) {
        void mouseY;
        void mouseX;
        return mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + 14;
    }

    /*
     * WARNING - void declaration
     */
    public void setDragging(boolean bl) {
        void dragging;
        this.dragging = dragging;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    /*
     * WARNING - void declaration
     */
    public void setX(int n) {
        void x;
        this.x = x;
    }

    /*
     * WARNING - void declaration
     */
    public void setY(int n) {
        void y;
        this.y = y;
    }
}

