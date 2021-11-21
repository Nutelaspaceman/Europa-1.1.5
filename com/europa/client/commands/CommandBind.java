/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  org.lwjgl.input.Keyboard
 */
package com.europa.client.commands;

import com.europa.Europa;
import com.europa.api.manager.command.Command;
import com.europa.api.manager.misc.ChatManager;
import com.europa.api.manager.module.Module;
import org.lwjgl.input.Keyboard;

public class CommandBind
extends Command {
    public CommandBind() {
        super("bind", "Binds a module with commands.", "bind <name> <key> | clear", "key", "keybind", "b");
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void onCommand(String[] arrstring) {
        block7: {
            void args;
            block6: {
                if (((void)args).length != 2) break block6;
                boolean found = false;
                for (Module module : Europa.getModuleManager().getModules()) {
                    if (!module.getName().equalsIgnoreCase((String)args[0])) continue;
                    module.setBind(Keyboard.getKeyIndex((String)args[1].toUpperCase()));
                    ChatManager.printChatNotifyClient(module.getName() + " bound to " + Keyboard.getKeyName((int)module.getBind()).toUpperCase());
                    found = true;
                    break;
                }
                if (found) break block7;
                ChatManager.printChatNotifyClient("Could not find module.");
                break block7;
            }
            if (((void)args).length == 1) {
                if (args[0].equalsIgnoreCase("clear")) {
                    for (Module module : Europa.getModuleManager().getModules()) {
                        module.setBind(0);
                    }
                    ChatManager.printChatNotifyClient("Successfully cleared all binds.");
                } else {
                    ChatManager.printChatNotifyClient(this.getSyntax());
                }
            } else {
                ChatManager.printChatNotifyClient(this.getSyntax());
            }
        }
    }
}

