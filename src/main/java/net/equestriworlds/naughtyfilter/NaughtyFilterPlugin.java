package net.equestriworlds.naughtyfilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bukkit.plugin.java.JavaPlugin;

public final class NaughtyFilterPlugin extends JavaPlugin {
    protected NaughtyFilterCommand naughtyfilterCommand = new NaughtyFilterCommand(this);
    protected EventListener eventListener = new EventListener(this);
    protected List<String> naughtyList;
    protected List<String> chatList;
    protected List<String> filterCommands;
    protected boolean filterChat;
    protected String feedback;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfiguration();
        naughtyfilterCommand.enable();
        eventListener.enable();
        loadWordLists();
    }

    @Override
    public void onDisable() {
    }

    public void loadConfiguration() {
        reloadConfig();
        filterCommands = getConfig().getStringList("FilterCommands");
        filterChat = getConfig().getBoolean("FilterChat");
        feedback = getConfig().getString("Feedback");
    }

    public void loadWordLists() {
        naughtyList = loadList("naughty.txt");
        chatList = loadList("chat.txt");
    }

    private List<String> loadList(String filename) {
        File file = new File(getDataFolder(), filename);
        if (!file.exists()) {
            saveResource(filename, false);
        }
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                String word = line.trim();
                if (word.isEmpty() || word.startsWith("#")) continue;
                list.add(word);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        // Longer words first
        Collections.sort(list, (b, a) -> Integer.compare(a.length(), b.length()));
        getLogger().info(filename + ": " + list.size() + " naughty words loaded");
        return list;
    }
}
