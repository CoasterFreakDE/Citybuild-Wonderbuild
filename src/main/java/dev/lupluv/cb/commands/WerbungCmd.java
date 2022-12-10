package dev.lupluv.cb.commands;

import dev.lupluv.cb.economy.Economy;
import dev.lupluv.cb.utils.Strings;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WerbungCmd implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        if(!player.hasPermission("cb.werbung")){
            player.sendMessage(Strings.noPerms);
            return true;
        }

        if(args.length == 1){

            if(Economy.getBalance(player.getUniqueId()) >= 5000) {
                sendAd(player, String.join(" ", args));
            }

        }else{
            player.sendMessage(Strings.prefix + "§7Benutzung: /werbung §a<Nachricht>");
        }

        return false;
    }

    // --------------------------------
    //
    // Werbung von §a{%player.name%}
    //
    // {%message%}
    //
    // --------------------------------

    public void sendAd(Player player, String message) {

        Bukkit.getOnlinePlayers().forEach(all ->{
            all.sendMessage(Strings.prefix + "§7--------------------------------");
            all.sendMessage(Strings.prefix);
            all.sendMessage(Strings.prefix + "§7Werbung von §a" + player.getName());
            all.sendMessage(Strings.prefix);
            all.sendMessage(Strings.prefix + "§7" + message);
            all.sendMessage(Strings.prefix);
            all.sendMessage(Strings.prefix + "§7--------------------------------");
        });

    }

}
