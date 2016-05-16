package nolifer.XAntiWDL;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class WDL
        extends JavaPlugin
        implements Listener, PluginMessageListener
{
    private static String prefix = ChatColor.GRAY + "[" + ChatColor.GOLD + "xStopWDL" + ChatColor.GRAY + "] " + ChatColor.DARK_GRAY;
    private static final String INIT_CHANNEL_NAME = "WDL|INIT";
    private static WDL instance;

    public void onEnable()
    {
        getConfig().options().copyDefaults(true);
        saveConfig();
        instance = this;
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getMessenger().registerIncomingPluginChannel(this, "WDL|INIT", this);
        getServer().getMessenger().registerOutgoingPluginChannel(this, "WDL|CONTROL");
    }

    public void onDisable()
    {
        getServer().getMessenger().unregisterIncomingPluginChannel(this, "WDL|INIT");
        getServer().getMessenger().unregisterOutgoingPluginChannel(this, "WDL|CONTROL");
    }

    public void onPluginMessageReceived(String channel, Player player, byte[] data)
    {
        if ((channel.equals("WDL|INIT"))){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), getConfig().getString("punish-command").replace("%p", player.getName()));
        }

        else if ((channel.equals("WDL|CONTROL"))){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), getConfig().getString("punish-command").replace("%p", player.getName()));
        }
    }

    public static void log(String message)
    {
        Bukkit.getConsoleSender().sendMessage(getPrefix() + message);
    }

    public static String getPrefix()
    {
        return prefix;
    }

    public static WDL getInstance()
    {
        return instance;
    }
}
