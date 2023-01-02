package ru.Overwrite.noCmd.utils;

import org.bukkit.Bukkit;
import net.md_5.bungee.api.ChatColor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.md_5.bungee.api.ChatColor.COLOR_CHAR;

public class RGBcolors {
	
	private static final Pattern HEX_PATTERN = Pattern.compile("&#([a-fA-F\\d]{6})");
	private static final int SUB_VERSION = Integer.parseInt(
            Bukkit.getServer().getClass().getPackage().getName()
                    .replace(".", ",")
                    .split(",")[3]
                    .replace("1_", "")
                    .replaceAll("_R\\d", "")
                    .replace("v", "")
    );

	public static String translate(String message) {
        if (SUB_VERSION >= 16) {
            Matcher matcher = HEX_PATTERN.matcher(message);
            StringBuilder builder = new StringBuilder(message.length() + 4 * 8);
            while (matcher.find()) {
                String group = matcher.group(1);
                matcher.appendReplacement(builder, COLOR_CHAR + "x"
                        + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
                        + COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
                        + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5)
                );
            }
            message = matcher.appendTail(builder).toString();
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }

}
