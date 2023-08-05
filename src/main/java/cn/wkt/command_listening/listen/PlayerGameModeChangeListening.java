package cn.wkt.command_listening.listen;

import cn.wkt.command_listening.CommandListening;
import cn.wkt.command_listening.util.LuckPermsUtils;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerGameModeChangeEvent;

import java.util.List;

/**
 * @author 小王
 * @version 1.1
 * @description: Class
 * @date 2023/8/5 13:44
 */
public class PlayerGameModeChangeListening implements Listener {

    public FileConfiguration config;

    public PlayerGameModeChangeListening(CommandListening plugin) {
        config = plugin.getConfig();
    }

    @EventHandler
    private void gameModeChangeListen(PlayerGameModeChangeEvent event) {
        // 获取玩家游戏模式
        Player player = event.getPlayer();
        System.out.println(player.getName() + "玩家游戏模式发生改变");
        if (event.getNewGameMode().name().equals(GameMode.CREATIVE.name())) {
            List<String> list = config.getStringList("opWhiteList");
            if (!list.contains(player.getName())) {
                LuckPermsUtils.kick(player.getName(), "游戏模式发生改变 请勿修改", true);
            }
        }
    }


}
