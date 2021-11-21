/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 */
package com.europa.client.modules.client;

import com.europa.Europa;
import com.europa.api.manager.module.Module;
import com.europa.api.manager.module.ModuleCategory;
import net.minecraft.client.gui.GuiScreen;

public class ModuleHUDEditor
extends Module {
    public static ModuleHUDEditor INSTANCE;

    public ModuleHUDEditor() {
        super("HUDEditor", "HUD Editor", "The client's HUD Editor.", ModuleCategory.CLIENT);
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if (ModuleHUDEditor.mc.player == null || ModuleHUDEditor.mc.world == null) {
            this.disable();
            return;
        }
        mc.displayGuiScreen((GuiScreen)Europa.HUD_EDITOR);
    }
}

