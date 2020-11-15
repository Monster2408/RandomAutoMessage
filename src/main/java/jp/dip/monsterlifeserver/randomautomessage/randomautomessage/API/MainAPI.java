package jp.dip.monsterlifeserver.randomautomessage.randomautomessage.API;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static jp.dip.monsterlifeserver.randomautomessage.randomautomessage.RandomAutoMessage.config;
import static jp.dip.monsterlifeserver.randomautomessage.randomautomessage.RandomAutoMessage.msgConfig;

public class MainAPI {

    private static Plugin plugin;

    public static String HelpMessage;
    public static String ReloadMessage;
    public static String HelpFormat;
    public static String ManyArgument;
    public static String PLPrefix;
    public static String PluginReloaded;
    public static String doNotHavePermission;

    public static int AutomaticMessageInterval;
    private static List<String> Messages;

    private static BukkitRunnable task;

    public static void MainAPI(Plugin plugin) {
        MainAPI.plugin = plugin;
    }

    private static void sendAutoMessage() {
        Collections.shuffle(Messages);
        String text = Messages.get(0);
        Bukkit.broadcastMessage(text);
    }

    public static void createTimer(int count) {
        if (task != null && !task.isCancelled()) task.cancel();
        task = new BukkitRunnable() {
            int i = count;
            public void run() {
                if (i==0) {
                    i = count;
                    sendAutoMessage();
                }
                i--;
            }
        };
        task.runTaskTimer(plugin, 0L, 20L);
    }

    public static void loadConfig() {
        AutomaticMessageInterval = config.getConfig().getInt("AutomaticMessageInterval");
        Messages = new ArrayList<>();
        for (String text : config.getConfig().getStringList("Messages")) {
            Messages.add(replaceColorCode(text));
        }
    }

    public static void loadMessage() {
        PLPrefix = replaceColorCode(config.getConfig().getString("Prefix"));
        HelpMessage = replaceColorCode(msgConfig.getConfig().getString("help.help"));
        ReloadMessage = replaceColorCode(msgConfig.getConfig().getString("help.reload"));
        HelpFormat = replaceColorCode(msgConfig.getConfig().getString("help.format"));
        ManyArgument = replaceColorCode(msgConfig.getConfig().getString("help.manyArgument"));
        PluginReloaded = replaceColorCode(msgConfig.getConfig().getString("PluginReloaded"));
        doNotHavePermission = replaceColorCode(msgConfig.getConfig().getString("doNotHavePermission"));
    }

    public static String replaceColorCode(String source) {
        if (source == null)
            return null;
        return ChatColor.translateAlternateColorCodes('&', source);
    }

    public static String deleteColorCode(String source) {
        if (source == null)
            return null;
        return ChatColor.stripColor(source);
    }

}
