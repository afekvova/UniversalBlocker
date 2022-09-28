package ru.Overwrite.noCmd.listeners;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import ru.Overwrite.noCmd.Main;
import ru.Overwrite.noCmd.utils.RGBcolors;

public class BlockSyntax implements Listener {
	
  @EventHandler(priority = EventPriority.HIGHEST)
  public void onCommand(PlayerCommandPreprocessEvent e) {
	  FileConfiguration config = Main.getInstance().getConfig();
	  Player p = e.getPlayer();
	 if (e.getMessage().split(" ")[0].contains(":") && !config.getStringList("excluded-players").contains(p.getName())) {
	   e.setCancelled(true);
	   p.sendMessage(RGBcolors.translate(config.getString("messages.blocksyntax")));
	  if (config.getBoolean("settings.notify"))
	    Bukkit.broadcast(RGBcolors.translate(config.getString("messages.blocksyntaxnotify").replace("%player%", p.getName()).replace("%cmd%", e.getMessage())), "ublocker.admin");
    } 
  }
}
