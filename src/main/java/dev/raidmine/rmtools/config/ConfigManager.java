package dev.raidmine.rmtools.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class ConfigManager {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Path path = FabricLoader.getInstance().getConfigDir().resolve("rmtools.json");
    private RMToolsConfig value = new RMToolsConfig();
    public RMToolsConfig get() { return value; }
    public void load() {
        try {
            if (Files.exists(path)) value = gson.fromJson(Files.readString(path), RMToolsConfig.class);
            else save();
        } catch (Exception e) { value = new RMToolsConfig(); save(); }
    }
    public void save() {
        try { Files.createDirectories(path.getParent()); Files.writeString(path, gson.toJson(value)); }
        catch (IOException ignored) {}
    }
    public Path path() { return path; }
}
