package cn.wkt.command_listening.listen;

import cn.wkt.command_listening.CommandListening;
import com.google.common.collect.Lists;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 小王
 * @version 1.1
 * @description: Class
 * @date 2023/8/5 14:23
 */
public class WhiteListConfigAddCommand implements CommandExecutor {

    private final CommandListening plugin;
    public FileConfiguration config;

    public WhiteListConfigAddCommand(CommandListening plugin) {
        this.plugin = plugin;
        config = plugin.getConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (0 == args.length) {
            sender.sendMessage("错误 帮助");
            sender.sendMessage("addOp add player[] 可将多名玩家添加至op白名单");
            sender.sendMessage("addOp delete player[] 可将多名玩家从op白名单删除");
            sender.sendMessage("addOp reload 重载配置文件");
            return true;
        } else if (args[0].equalsIgnoreCase("add")) {
            add(sender, args);
            return true;
        } else if (args[0].equalsIgnoreCase("delete")) {
            delete(sender, args);
            return true;
        } else if (args[0].equalsIgnoreCase("reload")) {
            reload(sender);
            return true;
        }
        return false;
    }

    private void add(CommandSender sender, String[] args) {
        List<String> list = config.getStringList("opWhiteList");
        ArrayList<String> newList = Lists.newArrayList(args);
        newList.remove("add");
        newList.stream().filter(e -> !list.contains(e)).forEach(list::add);
        System.out.println(list);
        config.set("opWhiteList", list);
        plugin.saveConfig();
        sender.sendMessage("添加成功" + newList);
    }

    private void delete(CommandSender sender, String[] args) {
        List<String> list = config.getStringList("opWhiteList");
        ArrayList<String> newList = Lists.newArrayList(args);
        newList.remove("delete");
        newList.stream().filter(list::contains).forEach(list::remove);
        config.set("opWhiteList", list);
        plugin.saveConfig();
        sender.sendMessage("删除成功" + newList);
    }

    private void reload(CommandSender sender) {
        plugin.reloadConfig();
        sender.sendMessage("重载成功");
    }

}
