package ru.Overwrite.noCmd.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.RemoteServerCommandEvent;
import ru.Overwrite.noCmd.Main;

public class RconBlocker implements Listener {
	
  @EventHandler
  public void onConsoleCommand(RemoteServerCommandEvent e) {
	String cmd = e.getCommand().replace("/", "");
	   if (rconBoolean(cmd))
	 	 e.setCancelled(true); 
  }
	 
  private boolean rconBoolean(String message) {
      FileConfiguration config = Main.getInstance().getConfig();
	  for (String s : config.getStringList("blocked-commands.rcon-blocked-commands")) {
        if (s.equalsIgnoreCase(message))
		  return true; 
	  } 
	  return false;
	}
}
