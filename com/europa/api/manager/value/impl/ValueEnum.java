/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.Event
 */
package com.europa.api.manager.value.impl;

import com.europa.api.manager.event.impl.client.EventClient;
import com.europa.api.manager.value.Value;
import java.util.ArrayList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;

public class ValueEnum
extends Value {
    public Enum value;

    public ValueEnum(String name, String tag, String description, Enum value) {
        super(name, tag, description);
        this.value = value;
    }

    public Enum getValue() {
        return this.value;
    }

    /*
     * WARNING - void declaration
     */
    public void setValue(Enum enum_) {
        void value;
        MinecraftForge.EVENT_BUS.post((Event)new EventClient(this));
        this.value = value;
    }

    /*
     * WARNING - void declaration
     */
    public Enum getEnumByName(String string) {
        Enum enumRequested = null;
        for (Enum enums : this.getValues()) {
            void name;
            if (!enums.name().equals(name)) continue;
            enumRequested = enums;
            break;
        }
        return enumRequested;
    }

    public ArrayList<Enum> getValues() {
        ArrayList<Enum> enumList = new ArrayList<Enum>();
        for (Enum enums : (Enum[])this.value.getClass().getEnumConstants()) {
            enumList.add(enums);
        }
        return enumList;
    }
}

