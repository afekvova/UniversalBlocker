package ru.Overwrite.noCmd.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.configuration.file.FileConfiguration;
import ru.Overwrite.noCmd.Main;
import ru.Overwrite.noCmd.utils.RGBcolors;

public class CommandBlocker implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	  public void onLiteCommand(PlayerCommandPreprocessEvent e) {
		FileConfiguration config = Main.getInstance().getConfig();
		String com = e.getMessage();
	    Player p = e.getPlayer();
	    for (String command : config.getStringList("blocked-commands.lite")) {
	      if ((com.toLowerCase().startsWith("/" + command + " ") || com.equalsIgnoreCase("/" + command)) && !isAdmin(p)) {
	    	p.sendMessage(RGBcolors.translate(config.getString("messages.blockedcommand")).replace("%cmd%", command));
	        e.setCancelled(true);
	        if (config.getBoolean("settings.notify"))
	       	  Bukkit.broadcast(RGBcolors.translate(config.getString("messages.notify-cmd").replace("%player%", p.getName()).replace("%cmd%", command)), "ublocker.admin");
	       continue;
	      } 
	    } 
	    for (String command : config.getStringList("blocked-commands.full")) {
		  if ((com.toLowerCase().startsWith("/" + command + " ") || com.equalsIgnoreCase("/" + command)) && !config.getStringList("excluded-players").contains(p.getName())) {
		   	p.sendMessage(RGBcolors.translate(config.getString("messages.blockedcommand")).replace("%cmd%", command));
		    e.setCancelled(true);
		     if (config.getBoolean("settings.notify"))
		      Bukkit.broadcast(RGBcolors.translate(config.getString("messages.notify-cmd").replace("%player%", p.getName()).replace("%cmd%", command)), "ublocker.admin");
		   continue;
		  }     
	    }
}
	
	private boolean isAdmin(Player p) {
		FileConfiguration config = Main.getInstance().getConfig();
	  if (p.hasPermission("ublocker.bypass.commands") || config.getStringList("excluded-players").contains(p.getName())) {
		  return true;
	  }
	  return false;
	}
}
