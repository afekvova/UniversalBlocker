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

public class ChatFilter implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChatMessage(AsyncPlayerChatEvent e) {
		FileConfiguration config = Main.getInstance().getConfig();
		  String message = e.getMessage();
	      Player p = e.getPlayer();
	    if (message != null && containsBlockedChars(message) && !isAdmin(p)) {
	      p.sendMessage(RGBcolors.translate(config.getString("messages.blockedchatsymbol")));
	      e.setCancelled(true);
	    if (config.getBoolean("settings.notify"))
	      Bukkit.broadcast(RGBcolors.translate(config.getString("messages.notify-chatsymbol").replace("%player%", p.getName()).replace("%chatsymbol%", message)), "ublocker.admin");
	    } 
	}
	
	private boolean isAdmin(Player p) {
		FileConfiguration config = Main.getInstance().getConfig();
	  if (p.hasPermission("ublocker.bypass.chatsymbol") || config.getStringList("excluded-players").contains(p.getName())) {
		  return true;
	  }
	  return false;
	}
	  
    private boolean containsBlockedChars(String message) {
	  FileConfiguration config = Main.getInstance().getConfig();
	    char[] d = message.toLowerCase().toCharArray();
	    int b = d.length;
        for (int f = 0; f < b; f++) {
	    char c = d[f];
	  if (config.getString("chat-settings.allowed-chars").indexOf(c) == -1)
		 return true; 
	   } 
	 return false;
   }
}
