package de.presti.ree6.commands.impl.fun;

import de.presti.ree6.api.JSONApi;
import de.presti.ree6.commands.Category;
import de.presti.ree6.commands.Command;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.interactions.InteractionHook;
import org.json.JSONObject;

public class DogeCoin extends Command {

    public DogeCoin() {
        super("dogecoin", "Shows you the pricing of DogeCoins", Category.FUN, new String[] { "doge" });
    }

    @Override
    public void onPerform(Member sender, Message messageSelf, String[] args, TextChannel m, InteractionHook hook) {
        JSONObject js = JSONApi.getData(JSONApi.Requests.GET, "https://data.messari.io/api/v1/assets/doge/metrics");

        sendMessage("The Current price of DogeCoins are " + js.getJSONObject("data").getJSONObject("market_data").getFloat("price_usd") + " USD!", m, hook);
        deleteMessage(messageSelf);
    }
}
