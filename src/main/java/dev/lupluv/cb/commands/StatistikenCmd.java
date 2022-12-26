package dev.lupluv.cb.commands;

import dev.lupluv.cb.utils.Item;
import dev.lupluv.cb.utils.Lore;
import dev.lupluv.cb.utils.Strings;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class StatistikenCmd implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player player)) return true;
        if(args.length == 0){
            createAndOpenInventory(player);
        }else{
            player.sendMessage(Strings.prefix + "§cBenutzung /statistiken");
        }
        return false;
    }

    public static Inventory inv;
    public static String invname = "§6§lStatistiken";

    public static void createAndOpenInventory(Player player){

        inv = Bukkit.createInventory(null, 9*5, invname);

        Item sheep = new Item(Material.SHEEP_SPAWN_EGG);
        sheep.setDisplayName("§6§lGetötete Lebewesen:");
        sheep.setLore(Lore.create("§7Anzahl§8: §a" + player.getStatistic(Statistic.KILL_ENTITY)));


        Item grass_block = new Item(Material.GRASS_BLOCK);
        grass_block.setDisplayName("§6§lBlöcke gelauen");
        grass_block.setLore(Lore.create("§7Anzahl§8: §a" + player.getStatistic(Statistic.WALK_ONE_CM)));


        Item Stonesword = new Item(Material.STONE_SWORD);
        Stonesword.setDisplayName("§6§lGetötete Spieler");
        Stonesword.setLore(Lore.create("§7Anzahl§8: §a" + player.getStatistic(Statistic.PLAYER_KILLS)));


        Item TotemOfUndiyng = new Item(Material.TOTEM_OF_UNDYING);
        TotemOfUndiyng.setDisplayName("§6§lTode");
        TotemOfUndiyng.setLore(Lore.create("§7Anzahl§8: §a" + player.getStatistic(Statistic.DEATHS)));


        Item elytra = new Item(Material.ELYTRA);
        elytra.setDisplayName("§6§lGeflogene Blöcke");
        elytra.setLore(Lore.create("§7Anzahl§8: §a" + player.getStatistic(Statistic.FLY_ONE_CM)));


        Item enderperl = new Item(Material.ENDER_PEARL);
        enderperl.setDisplayName("§6§lJumps");
        enderperl.setLore(Lore.create("§7Anzahl§8: §a" + player.getStatistic(Statistic.JUMP)));


        Item clock = new Item(Material.CLOCK);
        clock.setDisplayName("§6§lSpielerzeit");
        clock.setLore(Lore.create("§a" + player.getPlayerTime() + " §7Minuten"));


        Item mobs = new Item(Material.ZOMBIE_HEAD);
        mobs.setDisplayName("§6§lMobs getötet");
        mobs.setLore(Lore.create("§7Anzahl§8: §a" + player.getStatistic(Statistic.MOB_KILLS)));
        inv.setItem(21, mobs.build());
        inv.setItem(24, clock.build());
        inv.setItem(22, enderperl.build());
        inv.setItem(20, elytra.build());
        inv.setItem(16, TotemOfUndiyng.build());
        inv.setItem(14, Stonesword.build());
        inv.setItem(12, grass_block.build());
        inv.setItem(10, sheep.build());

        Item item = new Item(Material.GRAY_STAINED_GLASS_PANE);
        item.setDisplayName(" ");
        for(int i = 0; i < inv.getSize(); i++){
            if(inv.getItem(i) == null || inv.getItem(i).getType() == Material.AIR){
                inv.setItem(i, item.build());
            }
        }

        player.openInventory(inv);



    }





    @EventHandler
    public void onClick(InventoryClickEvent e){

        if(!(e.getWhoClicked() instanceof Player)) return;
        if(e.getCurrentItem() == null) return;
        Player pl = (Player) e.getWhoClicked();


        if(e.getView().getTitle().equals(invname)) {
            e.setCancelled(true);
        }

    }



}