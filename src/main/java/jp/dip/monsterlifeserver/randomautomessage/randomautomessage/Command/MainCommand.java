package jp.dip.monsterlifeserver.randomautomessage.randomautomessage.Command;

import jp.dip.monsterlifeserver.randomautomessage.randomautomessage.API.MainAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static jp.dip.monsterlifeserver.randomautomessage.randomautomessage.API.MainAPI.*;
import static jp.dip.monsterlifeserver.randomautomessage.randomautomessage.RandomAutoMessage.*;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("RandomAutoMessage")) {
            if (sender.hasPermission("RandomAutoMessage.admin")) {
                if (args.length == 0) {
                    sendHelp(sender);
                    return true;
                } else if (args.length >= 2) {
                    sender.sendMessage(PLPrefix + ManyArgument);
                    return true;
                } else {
                    if (args[0].equalsIgnoreCase("help")) {
                        sendHelp(sender);
                        return true;
                    } else if (args[0].equalsIgnoreCase("reload")) {
                        config.reloadConfig();
                        msgConfig.reloadConfig();

                        MainAPI.loadConfig();
                        MainAPI.loadMessage();
                        MainAPI.createTimer(AutomaticMessageInterval);
                        sender.sendMessage(PLPrefix + PluginReloaded);
                        return true;
                    }
                }
            } else {
                sender.sendMessage(PLPrefix + doNotHavePermission);
                return true;
            }
        }
        return false;
    }

    private static void sendHelp(CommandSender sender) {
        String text = replaceColorCode(HelpFormat);
        text.replace("{COMMAND}", "/ram help");
        text.replace("{USAGE}", HelpMessage);
        sender.sendMessage(text);
        text = replaceColorCode(HelpFormat);
        text.replace("{COMMAND}", "/ram reload");
        text.replace("{USAGE}", ReloadMessage);
        sender.sendMessage(text);
    }

}
