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
import java.awt.Color;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;

public class ValueColor
extends Value {
    public Color value;
    public Boolean rainbow;

    public ValueColor(String name, String tag, String description, Color value) {
        super(name, tag, description);
        this.value = value;
        this.rainbow = false;
    }

    public Color getValue() {
        this.doRainbow();
        return this.value;
    }

    /*
     * WARNING - void declaration
     */
    public void setValue(Color color) {
        void value;
        MinecraftForge.EVENT_BUS.post((Event)new EventClient(this));
        this.value = value;
    }

    public void doRainbow() {
        block0: {
            if (!this.rainbow.booleanValue()) break block0;
            float[] hsb = Color.RGBtoHSB(this.value.getRed(), this.value.getGreen(), this.value.getBlue(), null);
            double rainbowState = Math.ceil((double)System.currentTimeMillis() / Double.longBitsToDouble(Double.doubleToLongBits(0.805424556079663) ^ 0x7FDDC609B7F83D33L));
            Color c = this.HSBAlpha(Color.getHSBColor((float)((rainbowState %= Double.longBitsToDouble(Double.doubleToLongBits(0.05979013478483638) ^ 0x7FD81CD0030C719BL)) / Double.longBitsToDouble(Double.doubleToLongBits(0.01935162868812896) ^ 0x7FE550E9D15C0172L)), hsb[1], hsb[2]), this.value.getAlpha());
            this.setValue(new Color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()));
        }
    }

    /*
     * WARNING - void declaration
     */
    public void setRainbow(boolean bl) {
        void rainbow;
        this.rainbow = (boolean)rainbow;
    }

    public Boolean getRainbow() {
        return this.rainbow;
    }

    /*
     * WARNING - void declaration
     */
    public Color HSBAlpha(Color color, int n) {
        void alpha;
        void color2;
        return new Color(color2.getRed(), color2.getGreen(), color2.getBlue(), (int)alpha);
    }
}

