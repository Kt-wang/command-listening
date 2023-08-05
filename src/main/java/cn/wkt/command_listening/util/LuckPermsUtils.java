package cn.wkt.command_listening.util;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.BanEntry;
import org.bukkit.BanList;
import org.bukkit.Bukkit;

public class LuckPermsUtils {

    public final static LuckPerms api = LuckPermsProvider.get();

    /**
     * @param name 玩家名字 提出玩家
     */
    public static void kick(String name, String message, boolean flag) {
        if (flag) {
            Bukkit.getServer().getPlayer(name).kickPlayer(message);
        } else {
            BanList banList = Bukkit.getBanList(BanList.Type.NAME);
            BanEntry entry = banList.getBanEntry(name);
            if (null != entry) {
                Bukkit.getServer().getPlayer(name).kickPlayer(message);
            }
        }
    }



}
