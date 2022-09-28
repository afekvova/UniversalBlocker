package ru.Overwrite.noCmd.listeners;

import org.bukkit.event.Listener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import com.destroystokyo.paper.event.server.AsyncTabCompleteEvent;
import ru.Overwrite.noCmd.Main;

public class TabComplete implements Listener {
	
	@EventHandler
	  public void onTabComplete(AsyncTabCompleteEvent e) {
		if (!(e.getSender() instanceof Player)) {
		  return;
		}
		FileConfiguration config = Main.getInstance().getConfig();
		Player p = (Player)e.getSender();
		for (String command : config.getStringList("blocked-commands.args-tab-complete")) {
		  if (e.getBuffer().equalsIgnoreCase("/" + command + " ") && !isAdmin(p)) {
			e.setCancelled(true);
	      }
	    }
	  }
	
	private boolean isAdmin(Player p) {
		FileConfiguration config = Main.getInstance().getConfig();
	  if (p.hasPermission("ublocker.bypass.tabcomplete") || config.getStringList("excluded-players").contains(p.getName())) {
		  return true;
	  }
	  return false;
	}
}


