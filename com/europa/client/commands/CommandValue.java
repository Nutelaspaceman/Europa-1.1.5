/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 */
package com.europa.client.commands;

import com.europa.Europa;
import com.europa.api.manager.command.Command;
import com.europa.api.manager.misc.ChatManager;
import com.europa.api.manager.module.Module;
import com.europa.api.manager.value.Value;
import com.europa.api.manager.value.impl.ValueBoolean;
import com.europa.api.manager.value.impl.ValueEnum;
import com.europa.api.manager.value.impl.ValueNumber;
import com.mojang.realmsclient.gui.ChatFormatting;

public class CommandValue
extends Command {
    public CommandValue() {
        super("value", "Let's you change module values with commands.", "value <module> <setting> <value>", "val", "v", "set");
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void onCommand(String[] arrstring) {
        block11: {
            Value v;
            void args;
            block13: {
                block15: {
                    block14: {
                        block12: {
                            Module m = Europa.getModuleManager().getModule((String)args[0]);
                            if (m != null) {
                                if (((void)args).length == 1) {
                                    ChatManager.printChatNotifyClient("Showing settings for " + (String)args[0] + ": " + (Object)ChatFormatting.GRAY + "Placeholder");
                                }
                            }
                            if (m == null) {
                                ChatManager.printChatNotifyClient("Unknown module.");
                                return;
                            }
                            if (m == null) break block11;
                            if (args[1] == null) break block11;
                            v = m.getValue((String)args[1]);
                            if (v == null) {
                                ChatManager.printChatNotifyClient("Unknown setting.");
                                return;
                            }
                            if (v == null) break block11;
                            if (!(v instanceof ValueBoolean)) break block12;
                            if (args[2] == null) {
                                ChatManager.printChatNotifyClient("Please give a value.");
                                return;
                            }
                            if (args[2] != null) {
                                ((ValueBoolean)v).setValue(Boolean.parseBoolean((String)args[2]));
                                ChatManager.printChatNotifyClient((String)args[1] + " from " + (String)args[0] + " has been set to " + (String)args[2]);
                            }
                            break block11;
                        }
                        if (!(v instanceof ValueNumber)) break block13;
                        if (args[2] == null) {
                            ChatManager.printChatNotifyClient("Please give a value.");
                            return;
                        }
                        if (args[2] == null) break block11;
                        if (((ValueNumber)v).getType() != 3) break block14;
                        ((ValueNumber)v).setValue(Float.valueOf(Float.parseFloat((String)args[2])));
                        ChatManager.printChatNotifyClient((String)args[1] + " from " + (String)args[0] + " has been set to " + (String)args[2]);
                        break block11;
                    }
                    if (((ValueNumber)v).getType() != 2) break block15;
                    ((ValueNumber)v).setValue(Double.parseDouble((String)args[2]));
                    ChatManager.printChatNotifyClient((String)args[1] + " from " + (String)args[0] + " has been set to " + (String)args[2]);
                    break block11;
                }
                if (((ValueNumber)v).getType() != 1) break block11;
                ((ValueNumber)v).setValue(Integer.parseInt((String)args[2]));
                ChatManager.printChatNotifyClient((String)args[1] + " from " + (String)args[0] + " has been set to " + (String)args[2]);
                break block11;
            }
            if (v instanceof ValueEnum) {
                if (args[2] == null) {
                    ChatManager.printChatNotifyClient("Please give a value.");
                    return;
                }
                if (args[2] != null) {
                    for (Enum enumValue : ((ValueEnum)v).getValues()) {
                        enumValue = ((ValueEnum)v).getEnumByName((String)args[2]);
                        if (enumValue == null) continue;
                        ((ValueEnum)v).setValue(enumValue);
                        ChatManager.printChatNotifyClient((String)args[1] + " from " + (String)args[0] + " has been set to " + (String)args[2]);
                    }
                }
            }
        }
    }
}

