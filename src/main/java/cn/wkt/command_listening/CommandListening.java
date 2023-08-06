package cn.wkt.command_listening;

import cn.wkt.command_listening.listen.PlayerCommandListening;
import cn.wkt.command_listening.listen.PlayerGameModeChangeListening;
import cn.wkt.command_listening.listen.WhiteListConfigAddCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class CommandListening extends JavaPlugin {

    private FileConfiguration config;
    File configFile;

    @Override
    public void onEnable() {

        // 创建或加载配置文件
        configFile = new File(getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            saveDefaultConfig();
            System.out.println("CommandListening 初始化完成配置文件");
        }
        config = YamlConfiguration.loadConfiguration(configFile);

        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new PlayerCommandListening(this), this);
        getServer().getPluginManager().registerEvents(new PlayerGameModeChangeListening(this), this);

        // 注册命令
        getCommand("addOp").setExecutor(new WhiteListConfigAddCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public FileConfiguration getConfig() {
        return config;
    }

    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
