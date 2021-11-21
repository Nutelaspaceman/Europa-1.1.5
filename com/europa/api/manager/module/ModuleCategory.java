/*
 * Decompiled with CFR 0.150.
 */
package com.europa.api.manager.module;

public enum ModuleCategory {
    COMBAT("Combat"),
    PLAYER("Player"),
    MISC("Misc"),
    MOVEMENT("Movement"),
    RENDER("Render"),
    CLIENT("Client"),
    HUD("HUD");

    public String name;

    public ModuleCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

