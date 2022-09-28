package ru.Overwrite.noCmd.listeners;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.Overwrite.noCmd.Main;
import ru.Overwrite.noCmd.utils.RGBcolors;

public class NumbersCheck implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	  public void onChatNumber(AsyncPlayerChatEvent e) {
	  FileConfiguration config = Main.getInstance().getConfig();
	    String message = e.getMessage();
	    Player p = e.getPlayer();
	    int count = 0;
	    int limit = config.getInt("chat-settings.maxmsg-numbers");
	    for (int a = 0, b = message.length(); a < b; a++) {
	      char c = message.charAt(a);
	      if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || 
	        c == '6' || c == '7' || c == '8' || c == '9')
	        count++; 
	    } 
	    if (count > limit && !isAdmin(p)) {
	      p.sendMessage(RGBcolors.translate(config.getString("messages.maxnumbers-msg")));
	      e.setCancelled(true);
	      if (config.getBoolean("settings.notify"))
	          Bukkit.broadcast(RGBcolors.translate(config.getString("messages.notify-maxnumbers")
	        		  .replace("%player%", p.getName()).replace("%limit%", config.getString("chat-settings.maxmsg-numbers")).replace("%msg", message)), "ublocker.admin");
	    } 
	  }
	
	private boolean isAdmin(Player p) {
		FileConfiguration config = Main.getInstance().getConfig();
	  if (p.hasPermission("ublocker.bypass.numbers") || config.getStringList("excluded-players").contains(p.getName())) {
		  return true;
	  }
	  return false;
	}

}

