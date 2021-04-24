package net.equestriworlds.naughtyfilter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

@RequiredArgsConstructor
public final class NaughtyFilterCommand implements TabExecutor {
    private final NaughtyFilterPlugin plugin;

    public void enable() {
        plugin.getCommand("naughtyfilter").setExecutor(this);
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String alias, final String[] args) {
        if (args.length == 0) return false;
        switch (args[0]) {
        case "reload": {
            if (args.length != 1) return false;
            plugin.loadConfiguration();
            plugin.loadNaughtyList();
            sender.sendMessage("Configuration and word list reloaded");
            return true;
        }
        default:
            return false;
        }
    }

    @Override
    public List<String> onTabComplete(final CommandSender sender, final Command command, final String alias, final String[] args) {
        if (args.length == 1) {
            return Stream.of("reload").filter(s -> s.contains(args[0])).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
