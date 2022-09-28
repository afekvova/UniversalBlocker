package ru.Overwrite.noCmd.listeners;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import ru.Overwrite.noCmd.Main;
import ru.Overwrite.noCmd.utils.RGBcolors;

public class BanWords implements Listener {
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
	FileConfiguration config = Main.getInstance().getConfig();
		String message = e.getMessage().toLowerCase();
	    Player p = e.getPlayer();
	  for (String banword : config.getStringList("chat-settings.ban-words")) {
	    if (message.contains(banword) && !isAdmin(p)) {
	      p.sendMessage(RGBcolors.translate((config.getString("messages.blockedword"))).replace("%word%", banword));
	      e.setCancelled(true);
	     if (config.getBoolean("settings.notify"))
		    Bukkit.broadcast(RGBcolors.translate(config.getString("messages.notify-blockedword").replace("%player%", p.getName()).replace("%word%", banword)), "ublocker.admin");
	     }
	   }
	 }
	
	private Boolean isAdmin(Player p) {
		boolean bool = false;
		FileConfiguration config = Main.getInstance().getConfig();
	  if (p.hasPermission("ublocker.bypass.banwords") || config.getStringList("excluded-players").contains(p.getName())) {
		  bool = true;
	  }
	  return Boolean.valueOf(bool);
	}
}