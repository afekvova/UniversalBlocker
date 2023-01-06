package ru.Overwrite.noCmd.listeners;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandSendEvent;
import ru.Overwrite.noCmd.Main;

public class CommandHider implements Listener {
	    FileConfiguration config = Main.getInstance().getConfig();
	
	Main main;	
	public CommandHider(Main main) {
        Bukkit.getPluginManager().registerEvents(this, main);
        this.main = main;
        main.getLogger().info("command-hider - enabled");
    }
	
	@EventHandler
	  public void onCommandSend(PlayerCommandSendEvent e) {
	    Player p = e.getPlayer();
	    if (!isAdmin(p)) {
	      e.getCommands().removeIf(cmd -> config.getStringList("blocked-commands.lite").contains(cmd) || config.getStringList("blocked-commands.full").contains(cmd));
	      if (config.getBoolean("settings.enable-blocksyntax")) {
	        e.getCommands().removeIf(cmd -> cmd.contains(":"));
	      }
	    } 
	  }
	
	private boolean isAdmin(Player p) {
	  if (p.hasPermission("ublocker.bypass.tabcomplete") || config.getStringList("excluded-players").contains(p.getName())) {
		  return true;
	  }
	  return false;
	}
}
