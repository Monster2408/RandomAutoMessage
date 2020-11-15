package jp.dip.monsterlifeserver.randomautomessage.randomautomessage;

import jp.dip.monsterlifeserver.randomautomessage.randomautomessage.API.CustomConfig;
import jp.dip.monsterlifeserver.randomautomessage.randomautomessage.API.MainAPI;
import jp.dip.monsterlifeserver.randomautomessage.randomautomessage.Command.MainCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import static jp.dip.monsterlifeserver.randomautomessage.randomautomessage.API.MainAPI.AutomaticMessageInterval;

public final class RandomAutoMessage extends JavaPlugin {

    public static CustomConfig config;
    public static CustomConfig msgConfig;

    @Override
    public void onEnable() {
        config = new CustomConfig(this);
        config.saveDefaultConfig();
        msgConfig = new CustomConfig(this, "message.yml");
        msgConfig.saveDefaultConfig();

        MainAPI.MainAPI(this);
        MainAPI.loadConfig();
        MainAPI.loadMessage();
        MainAPI.createTimer(AutomaticMessageInterval);

        getCommand("RandomAutoMessage").setExecutor(new MainCommand());
    }

    @Override
    public void onDisable() {}
}
