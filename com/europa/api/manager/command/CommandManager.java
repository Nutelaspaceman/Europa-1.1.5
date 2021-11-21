/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.client.event.ClientChatEvent
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package com.europa.api.manager.command;

import com.europa.api.manager.command.Command;
import com.europa.api.manager.misc.ChatManager;
import com.europa.client.commands.CommandBind;
import com.europa.client.commands.CommandFriend;
import com.europa.client.commands.CommandPrefix;
import com.europa.client.commands.CommandValue;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommandManager {
    public static Minecraft mc = Minecraft.getMinecraft();
    public String prefix = ";";
    public ArrayList<Command> commands;

    public CommandManager() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.commands = new ArrayList();
        this.register(new CommandBind());
        this.register(new CommandFriend());
        this.register(new CommandPrefix());
        this.register(new CommandValue());
    }

    /*
     * WARNING - void declaration
     */
    @SubscribeEvent
    public void onChatSent(ClientChatEvent clientChatEvent) {
        block3: {
            void event;
            String message = event.getMessage();
            if (!message.startsWith(this.getPrefix())) break block3;
            event.setCanceled(true);
            message = message.substring(this.getPrefix().length());
            if (message.split(" ").length > 0) {
                String name = message.split(" ")[0];
                boolean found = false;
                for (Command command : this.getCommands()) {
                    if (!command.getAliases().contains(name.toLowerCase()) && !command.getName().equalsIgnoreCase(name)) continue;
                    CommandManager.mc.ingameGUI.getChatGUI().addToSentMessages(event.getMessage());
                    command.onCommand(Arrays.copyOfRange(message.split(" "), 1, message.split(" ").length));
                    found = true;
                    break;
                }
                if (!found) {
                    ChatManager.printChatNotifyClient("Command could not be found.");
                }
            }
        }
    }

    /*
     * WARNING - void declaration
     */
    public void register(Command command) {
        void command2;
        this.commands.add((Command)command2);
    }

    public ArrayList<Command> getCommands() {
        return this.commands;
    }

    public String getPrefix() {
        return this.prefix;
    }

    /*
     * WARNING - void declaration
     */
    public void setPrefix(String string) {
        void prefix;
        this.prefix = prefix;
    }
}

