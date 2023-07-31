package cn.wkt.command_listening.listen;

import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.Node;
import me.lucko.luckperms.api.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Set;

public class PlayerCommandListening implements Listener {

    final static LuckPermsApi api = LuckPerms.getApi();

    @EventHandler
    public void luckUserCommandListen(PlayerCommandPreprocessEvent event) {
        System.out.println("*-----*");
        String name = event.getPlayer().getName();
        User user = api.getUser(name);
        if (null == user) {
            return;
        }
        System.out.println(user);
        Set<Node> set;
        set = user.getPermanentPermissionNodes();
        for (Node e : set) {
            System.out.println(e);
        }
    }
}
