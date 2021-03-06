package de.presti.ree6.commands.impl.community;

import de.presti.ree6.commands.Category;
import de.presti.ree6.commands.Command;
import de.presti.ree6.main.Main;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.interactions.InteractionHook;

public class TwitchNotifier extends Command {


    public TwitchNotifier() {
        super("twitch", "Manage your Twitchnotifier!", Category.COMMUNITY, new String[]{ "twitchnotifier", "streamnotifier"});
    }

    @Override
    public void onPerform(Member sender, Message messageSelf, String[] args, TextChannel m, InteractionHook hook) {
        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("list")) {
                StringBuilder end = new StringBuilder("```\n");

                for(String users : Main.sqlWorker.getTwitchNotifier(m.getGuild().getId())) {
                    end.append(users).append("\n");
                }

                end.append("```");

                sendMessage(end.toString(), 10, m, hook);

            } else {
                sendMessage("Please use " + Main.sqlWorker.getSetting(sender.getGuild().getId(), "chatprefix").getStringValue() + "twitch list/add/remove", 5, m, hook);
            }
        } else if(args.length == 3) {

            if (messageSelf.getMentionedChannels().size() == 0) {
                sendMessage("Please use " + Main.sqlWorker.getSetting(sender.getGuild().getId(), "chatprefix").getStringValue() + "twitch add/remove TwitchName #Channel", 5, m, hook);
                return;
            }

            String name = args[1];
            if (args[0].equalsIgnoreCase("add")) {
                messageSelf.getMentionedChannels().get(0).createWebhook("Ree6-TwitchNotifier-" + name).queue(w -> Main.sqlWorker.addTwitchNotify(sender.getGuild().getId(), name.toLowerCase(), w.getId(), w.getToken()));
                sendMessage("A TwitchStream Notifier has been created for the User " + name + "!", 5, m, hook);

                if (!Main.twitchAPIHandler.isRegisterd(name)) {
                    Main.twitchAPIHandler.registerChannel(name);
                }
            } else {
                sendMessage("Please use " + Main.sqlWorker.getSetting(sender.getGuild().getId(), "chatprefix").getStringValue() + "twitch add TwitchName #Channel", 5, m, hook);
            }
        } else if(args.length == 2) {
            String name = args[1];
            if(args[0].equalsIgnoreCase("remove")) {
                Main.sqlWorker.removeTwitchNotify(sender.getGuild().getId(), name);
                sendMessage("A TwitchStream Notifier has been removed from the User " + name + "!", 5, m, hook);

                if(Main.twitchAPIHandler.isRegisterd(name)) {
                    if(Main.sqlWorker.getTwitchNotifyWebhookByName(name.toLowerCase()).length == 0) {
                        Main.twitchAPIHandler.unregisterChannel(name);
                    }
                }
            } else {
                sendMessage("Please use " + Main.sqlWorker.getSetting(sender.getGuild().getId(), "chatprefix").getStringValue() + "twitch remove TwitchName", 5, m, hook);
            }
        } else {
            sendMessage("Please use " + Main.sqlWorker.getSetting(sender.getGuild().getId(), "chatprefix").getStringValue() + "twitch list/add/remove", 5, m, hook);
        }
        deleteMessage(messageSelf);
    }
}
