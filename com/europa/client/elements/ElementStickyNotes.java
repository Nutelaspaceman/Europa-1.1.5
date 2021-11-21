/*
 * Decompiled with CFR 0.150.
 */
package com.europa.client.elements;

import com.europa.Europa;
import com.europa.api.manager.element.Element;
import com.europa.api.manager.event.impl.render.EventRender2D;
import com.europa.api.manager.value.impl.ValueEnum;
import com.europa.api.manager.value.impl.ValueString;
import com.europa.client.modules.client.ModuleColor;

public class ElementStickyNotes
extends Element {
    public static ValueEnum lines = new ValueEnum("Lines", "Lines", "The amount of lines that should be rendered.", LinesAmount.One);
    public static ValueString lineOne = new ValueString("LineOne", "LineOne", "The first line.", "Placeholder");
    public static ValueString lineTwo = new ValueString("LineTwo", "LineTwo", "The second line.", "Placeholder");
    public static ValueString lineThree = new ValueString("LineThree", "LineThree", "The third line.", "Placeholder");
    public static ValueString lineFour = new ValueString("LineFour", "LineFour", "The fourth line.", "Placeholder");

    public ElementStickyNotes() {
        super("StickyNotes", "Sticky Notes", "Let's you write custom stuff on the screen.");
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void onRender2D(EventRender2D eventRender2D) {
        void event;
        super.onRender2D((EventRender2D)event);
        this.frame.setWidth(Europa.FONT_MANAGER.getStringWidth(lineOne.getValue()));
        this.frame.setHeight(Europa.FONT_MANAGER.getHeight() * (float)this.getMultiplier() + (float)this.getMultiplier());
        for (int i = 0; i <= this.getMultiplier() - 1; ++i) {
            Europa.FONT_MANAGER.drawString(i == 1 ? lineTwo.getValue() : (i == 2 ? lineThree.getValue() : (i == 3 ? lineFour.getValue() : lineOne.getValue())), this.frame.getX(), this.frame.getY() + (Europa.FONT_MANAGER.getHeight() + Float.intBitsToFloat(Float.floatToIntBits(14.699412f) ^ 0x7EEB30CB)) * (float)i, ModuleColor.getActualColor());
        }
    }

    public int getMultiplier() {
        switch (1.$SwitchMap$com$europa$client$elements$ElementStickyNotes$LinesAmount[((LinesAmount)lines.getValue()).ordinal()]) {
            case 1: {
                return 2;
            }
            case 2: {
                return 3;
            }
            case 3: {
                return 4;
            }
        }
        return 1;
    }

    public static enum LinesAmount {
        One,
        Two,
        Three,
        Four;

    }
}

