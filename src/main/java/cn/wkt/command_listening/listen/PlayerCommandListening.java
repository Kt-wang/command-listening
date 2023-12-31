package cn.wkt.command_listening.listen;

import cn.wkt.command_listening.CommandListening;
import cn.wkt.command_listening.util.LuckPermsUtils;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.NodeType;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Collection;
import java.util.List;

public class PlayerCommandListening implements Listener {

    public FileConfiguration config;

    public PlayerCommandListening(CommandListening plugin) {
        config = plugin.getConfig();
    }


    /**
     * @param event 玩家输入指令事件
     */
    @EventHandler
    public void luckUserCommandListen(PlayerCommandPreprocessEvent event) {
        listenPlayer(event.getPlayer());
    }


    /**
     * @param event 玩家进入世界事件
     */
    @EventHandler
    public void Listen(PlayerJoinEvent event) {
        listenPlayer(event.getPlayer());
    }


    private void listenPlayer(Player player) {
        String name = player.getName();
        User user = LuckPermsUtils.api.getUserManager().getUser(name);
        if (null != user) {
            List<String> list = config.getStringList("opWhiteList");
            if (list.contains(name)) {
                System.out.println("白名单玩家" + name);
            } else {
                Collection<Node> nodes = user.getNodes();
                // 计算是否存在 * 的权限系节点
                long count = nodes.stream().
                        filter(e -> e.getType().name().equals(NodeType.PERMISSION.name())).
                        filter(e -> e.getKey().equals("*")).count();
                if (count > 0) {
                    System.out.println(name + "玩家权限节点为*数量" + count);
                    BanList banList = Bukkit.getBanList(BanList.Type.NAME);
                    String msg = "检测到你的权限有问题 请联系管理员";
                    banList.addBan(name, msg, null, "KleinBlue_");
                    LuckPermsUtils.kick(name, msg, false);
                }
            }
        }
    }

}
