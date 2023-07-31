package cn.wkt.command_listening;

import cn.wkt.command_listening.listen.PlayerCommandListening;
import org.bukkit.plugin.java.JavaPlugin;

public final class CommandListening extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerCommandListening(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
