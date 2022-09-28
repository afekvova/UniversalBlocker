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

public class SyntaxBlocker implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	  public void onSyntax(PlayerCommandPreprocessEvent e) {
		FileConfiguration config = Main.getInstance().getConfig();
	    for (String symbol : config.getStringList("symbols.blocked-symbols")) {
	      String com = e.getMessage();
	      Player p = e.getPlayer();
	      if (!startWithExcluded(com)) {
	      if (com.toLowerCase().contains(symbol) && !isAdmin(p)) {
	    	p.sendMessage(RGBcolors.translate(config.getString("messages.blockedsymbol")).replace("%symbol%", symbol));
	        e.setCancelled(true);
	        if (config.getBoolean("settings.notify"))
	          Bukkit.broadcast(RGBcolors.translate(config.getString("messages.notify-symbol").replace("%player%", p.getName()).replace("%symbol%", symbol)), "ublocker.admin");
	        }
	      }
        }
	  }
	
	private boolean startWithExcluded(String com) {
	FileConfiguration config = Main.getInstance().getConfig();
	   for (String excluded : config.getStringList("symbols.excluded-commands")) {
	     if (com.toLowerCase().startsWith("/" + excluded + " ")) {
	    	 return true;
	     }
	  }
	  return false;  
	}
	
	private boolean isAdmin(Player p) {
		FileConfiguration config = Main.getInstance().getConfig();
	  if (p.hasPermission("ublocker.bypass.symbol") || config.getStringList("excluded-players").contains(p.getName())) {
		  return true;
	  }
	  return false;
	}
}
