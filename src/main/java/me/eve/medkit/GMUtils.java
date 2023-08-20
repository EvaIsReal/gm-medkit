package me.eve.medkit;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.tr7zw.nbtapi.NBTTileEntity;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;

public class GMUtils {

    public static String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static void sendActionbar(Player p, String str) {
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                new ComponentBuilder(str).create());
    }

    public static String getSkullValue(Block skull) {
        NBTTileEntity tent = new NBTTileEntity(skull.getState());
        Gson gson = new Gson();
        JsonObject json = new JsonParser().parse(tent.toString()).getAsJsonObject();
        return json.get("Owner").getAsJsonObject()
                .get("Properties").getAsJsonObject()
                .get("textures").getAsJsonArray()
                .get(0).getAsJsonObject()
                .get("Value").getAsString();
    }

}
