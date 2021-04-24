package net.equestriworlds.naughtyfilter;

import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

@RequiredArgsConstructor
public final class EventListener implements Listener {
    private final NaughtyFilterPlugin plugin;
    private static final String STARS = "****************";

    public void enable() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String[] tokens = event.getMessage().split(" ", 2);
        if (tokens.length != 2) return;
        String cmd = tokens[0];
        if (cmd.startsWith("/")) cmd = cmd.substring(1);
        if (!plugin.filterCommands.contains(cmd)) return;
        String message = tokens[1].toLowerCase();
        for (String naughty : plugin.naughtyList) {
            if (message.contains(naughty.toLowerCase())) {
                event.setCancelled(true);
                if (plugin.feedback != null) {
                    String msg = ChatColor.translateAlternateColorCodes('&', plugin.feedback);
                    event.getPlayer().sendMessage(msg);
                }
                return;
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        if (!plugin.filterChat) return;
        String message = event.getMessage();
        String lower = message.toLowerCase();
        StringBuilder sb = null;
        for (String naughty : plugin.naughtyList) {
            int index = lower.indexOf(naughty.toLowerCase());
            if (index < 0) continue;
            if (sb == null) sb = new StringBuilder(message);
            int len = naughty.length();
            sb.replace(index, index + len, STARS.substring(0, Math.min(STARS.length(), len)));
        }
        if (sb != null) {
            event.setMessage(sb.toString());
        }
    }
}
