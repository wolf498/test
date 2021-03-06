package de.presti.ree6.commands;

import de.presti.ree6.utils.Logger;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.interactions.InteractionHook;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.util.concurrent.TimeUnit;


public abstract class Command {

    final String cmd;
    final String desc;
    final Category cat;
    String[] alias;

    final CommandData commandData;

    public Command(String command, String description, Category category) {
        cmd = command;
        desc = description;
        cat = category;
        this.commandData = new CommandData(command, description);
    }

    public Command(String command, String description, Category category, String[] alias) {
        cmd = command;
        desc = description;
        cat = category;
        this.alias = alias;
        this.commandData = new CommandData(command, description);
    }

    public Command(String command, String description, Category category, CommandData commandData) {
        cmd = command;
        desc = description;
        cat = category;
        this.commandData = commandData;
    }

    public Command(String command, String description, Category category, String[] alias, CommandData commandData) {
        cmd = command;
        desc = description;
        cat = category;
        this.alias = alias;
        this.commandData = commandData;
    }

    public abstract void onPerform(Member sender, Message messageSelf, String[] args, TextChannel m, InteractionHook interactionHook);

    public String[] getAlias() {
        return alias;
    }

    public String getCmd() {
        return cmd;
    }

    public String getDesc() {
        return desc;
    }

    public Category getCategory() { return cat; }

    public void sendMessage(String msg, MessageChannel m, InteractionHook hook) {
        if (hook == null) m.sendMessage(msg).queue(); else hook.sendMessage(msg).queue();
    }

    public void sendMessage(String msg, int deleteSecond, MessageChannel m, InteractionHook hook) {
        if (hook == null) m.sendMessage(msg).delay(deleteSecond, TimeUnit.SECONDS).flatMap(Message::delete).queue(); else hook.sendMessage(msg).delay(deleteSecond, TimeUnit.SECONDS).flatMap(Message::delete).queue();
    }


    public void sendMessage(EmbedBuilder msg, MessageChannel m, InteractionHook hook) {
        if (hook == null) m.sendMessageEmbeds(msg.build()).queue(); else hook.sendMessageEmbeds(msg.build()).queue();
    }

    public void sendMessage(EmbedBuilder msg, int deleteSecond, MessageChannel m, InteractionHook hook) {
        if (hook == null) m.sendMessageEmbeds(msg.build()).delay(deleteSecond, TimeUnit.SECONDS).flatMap(Message::delete).queue(); else hook.sendMessageEmbeds(msg.build()).delay(deleteSecond, TimeUnit.SECONDS).flatMap(Message::delete).queue();
    }

    public static void deleteMessage(Message message) {
        if(message != null && message.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_MANAGE)) {
            try {
                message.delete().queue();
            } catch (Exception ex) {
                Logger.log("CommandSystem", "Couldn't delete a Message!");
            }
        }
    }

    public boolean isAlias(String arg) {
        if(getAlias() == null || getAlias().length == 0)
            return false;

        for(String alias : getAlias()) {
            if(alias.equalsIgnoreCase(arg)) {
                return true;
            }
        }
        return false;
    }

    public CommandData getCommandData() {
        return commandData;
    }
}