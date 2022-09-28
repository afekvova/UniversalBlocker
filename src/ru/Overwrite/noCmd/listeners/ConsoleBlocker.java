package ru.Overwrite.noCmd.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;
import ru.Overwrite.noCmd.Main;

public class ConsoleBlocker implements Listener {
 
  @EventHandler
  public void onConsoleCommand(ServerCommandEvent e) {
 	String cmd = e.getCommand().replace("/", "");
	   if (consoleBoolean(cmd))
 	     e.setCancelled(true); 
  }
 
  private boolean consoleBoolean(String message) {
	  FileConfiguration config = Main.getInstance().getConfig();
	  for (String s : config.getStringList("blocked-commands.console-blocked-commands")) {
	    if (s.equalsIgnoreCase(message))
	      return true; 
	  } 
	  return false;
    }
}
