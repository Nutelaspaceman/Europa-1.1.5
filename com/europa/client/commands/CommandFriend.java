/*
 * Decompiled with CFR 0.150.
 */
package com.europa.client.commands;

import com.europa.Europa;
import com.europa.api.manager.command.Command;
import com.europa.api.manager.misc.ChatManager;

public class CommandFriend
extends Command {
    public CommandFriend() {
        super("friend", "Adds friends using commands.", "friend <add|del> <name> | clear", "f", "friends");
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void onCommand(String[] arrstring) {
        block6: {
            void args;
            block5: {
                if (args[0].equalsIgnoreCase("add")) {
                    if (Europa.FRIEND_MANAGER.isFriend((String)args[1])) {
                        ChatManager.printChatNotifyClient((String)args[1] + " is already a friend!");
                        return;
                    }
                    if (!Europa.FRIEND_MANAGER.isFriend((String)args[1])) {
                        Europa.FRIEND_MANAGER.addFriend((String)args[1]);
                        ChatManager.printChatNotifyClient("Added " + (String)args[1] + " to friends list");
                    }
                }
                if (args[0].equalsIgnoreCase("del")) break block5;
                if (!args[0].equalsIgnoreCase("remove")) break block6;
            }
            if (!Europa.FRIEND_MANAGER.isFriend((String)args[1])) {
                ChatManager.printChatNotifyClient((String)args[1] + " is not a friend!");
                return;
            }
            if (Europa.FRIEND_MANAGER.isFriend((String)args[1])) {
                Europa.FRIEND_MANAGER.removeFriend((String)args[1]);
                ChatManager.printChatNotifyClient("Removed " + (String)args[1] + " from friends list");
            }
        }
    }
}

