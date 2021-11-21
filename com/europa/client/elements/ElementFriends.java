/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  net.minecraft.entity.player.EntityPlayer
 */
package com.europa.client.elements;

import com.europa.Europa;
import com.europa.api.manager.element.Element;
import com.europa.api.manager.event.impl.render.EventRender2D;
import com.europa.api.manager.value.impl.ValueEnum;
import com.europa.api.manager.value.impl.ValueString;
import com.europa.client.modules.client.ModuleColor;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import net.minecraft.entity.player.EntityPlayer;

public class ElementFriends
extends Element {
    public static ValueString name = new ValueString("Name", "Name", "The name for the group of friends.", "The Goons");
    public static ValueEnum color = new ValueEnum("Color", "Color", "The color for the friend names.", Colors.White);

    public ElementFriends() {
        super("Friends", "Gives you a list of friends in your chunk distance.");
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void onRender2D(EventRender2D eventRender2D) {
        void event;
        super.onRender2D((EventRender2D)event);
        ArrayList friends = ElementFriends.mc.world.playerEntities.stream().filter(ElementFriends::lambda$onRender2D$0).collect(Collectors.toCollection(ArrayList::new));
        friends.sort(Comparator.comparing(EntityPlayer::func_70005_c_));
        this.frame.setWidth(friends.isEmpty() ? Europa.FONT_MANAGER.getStringWidth(name.getValue()) : Europa.FONT_MANAGER.getStringWidth(((EntityPlayer)friends.get(0)).getName()));
        this.frame.setHeight(Europa.FONT_MANAGER.getHeight() + (friends.isEmpty() ? Float.intBitsToFloat(Float.floatToIntBits(1.8033839E38f) ^ 0x7F07ABE9) : Float.intBitsToFloat(Float.floatToIntBits(4.580608f) ^ 0x7F129457) + (Europa.FONT_MANAGER.getHeight() + Float.intBitsToFloat(Float.floatToIntBits(10.604377f) ^ 0x7EA9AB87)) * (float)(friends.size() + 1)));
        Europa.FONT_MANAGER.drawString(name.getValue(), this.frame.getX(), this.frame.getY(), ModuleColor.getActualColor());
        int index = 10;
        for (EntityPlayer player : friends) {
            Europa.FONT_MANAGER.drawString((Object)this.getColor() + player.getName(), this.frame.getX(), this.frame.getY() + (float)index, ModuleColor.getActualColor());
            index += 10;
        }
    }

    public ChatFormatting getColor() {
        if (color.getValue().equals((Object)Colors.White)) {
            return ChatFormatting.WHITE;
        }
        if (color.getValue().equals((Object)Colors.Gray)) {
            return ChatFormatting.GRAY;
        }
        return ChatFormatting.RESET;
    }

    public static boolean lambda$onRender2D$0(EntityPlayer entityPlayer) {
        EntityPlayer p;
        return Europa.FRIEND_MANAGER.isFriend(p.getName());
    }

    public static enum Colors {
        Normal,
        White,
        Gray;

    }
}

