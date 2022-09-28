package ru.Overwrite.noCmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import ru.Overwrite.noCmd.listeners.*;
import ru.Overwrite.noCmd.utils.*;

public class Main extends JavaPlugin {
	
  private static Main instance;
	  
  public static Main getInstance() {
     return instance;
  }
  
  public void onEnable() {
	  if (getServer().getName() == "CraftBukkit") {
		  setEnabled(false);
		  getLogger().info("§6=============§6! WARNING ! §c=============");
		  getLogger().info("§eЭтот плагин работает только на Paper и его форках!");
		  getLogger().info("§eСкачать Paper для новых версий: §ahttps://papermc.io/downloads");
		  getLogger().info("§eСкачать Paper для старых версий: §ahttps://papermc.io/legacy §7((в тесте выбирайте 2 вариант ответа))");
		  getLogger().info("§6=============§6! WARNING ! §c=============");
		  return;
	  }
	instance = this;
    getCommand("universalblocker").setExecutor(new CommandClass(this));
    saveDefaultConfig();
    reloadConfig();
    if (getConfig().getBoolean("settings.enable-metrics")) {
      new Metrics(this, 15379);
    }
    if (getConfig().getBoolean("settings.enable-allowed-chars")) {
        Bukkit.getPluginManager().registerEvents(new ChatFilter(), this);
        getLogger().info("allowed-chars - enabled");
    }
    if (getConfig().getBoolean("settings.enable-command-blocker")) {
        Bukkit.getPluginManager().registerEvents(new CommandBlocker(), this);
        getLogger().info("command-blocker - enabled");
    }
    if (getConfig().getBoolean("settings.enable-console-blocker")) {
        Bukkit.getPluginManager().registerEvents(new ConsoleBlocker(), this);
        getLogger().info("console-blocker - enabled");
    }
    if (getConfig().getBoolean("settings.enable-rcon-blocker")) {
        Bukkit.getPluginManager().registerEvents(new RconBlocker(), this);
        getLogger().info("rcon-blocker - enabled");
    }
    if (getConfig().getBoolean("settings.enable-numbers-check")) {
        Bukkit.getPluginManager().registerEvents(new NumbersCheck(), this);
        getLogger().info("numbers-check - enabled");
    }
    if (getConfig().getBoolean("settings.enable-symbol-blocker")) {
        Bukkit.getPluginManager().registerEvents(new SyntaxBlocker(), this);
        getLogger().info("symbol-blocker - enabled");
    }
    if (getConfig().getBoolean("settings.enable-blocksyntax")) {
        Bukkit.getPluginManager().registerEvents(new BlockSyntax(), this);
        getLogger().info("blocksyntax - enabled");
    }
    if (getConfig().getBoolean("settings.enable-words-blocker")) {
    	Bukkit.getPluginManager().registerEvents(new BanWords(), this);
    	getLogger().info("words-blocker - enabled");
    }
    if (getConfig().getBoolean("settings.enable-tab-complere-blocker")) {
    	Bukkit.getPluginManager().registerEvents(new TabComplete(), this);
    	getLogger().info("tab-complere-blocker - enabled");
    }
  }
	
  public void onDisable() {
    if (getConfig().getBoolean("shutdown-on-disable")) {
	  Bukkit.shutdown();
    }
  }
}
