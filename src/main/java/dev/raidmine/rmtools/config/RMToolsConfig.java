package dev.raidmine.rmtools.config;

import java.util.ArrayList;
import java.util.List;

public final class RMToolsConfig {
    public int accent = 0xFFFF8A00;
    public int accent2 = 0xFFFFC300;
    public int background = 0xD9141418;
    public float uiScale = 1.0f;
    public float hudScale = 1.0f;
    public int hudX = -1;
    public int hudY = 8;
    public boolean sound = true;
    public boolean screenshots = true;
    public boolean detectMentions = true;
    public boolean detectForbiddenWords = true;
    public String warnCommand = "/warn {player} {rule}";
    public String muteCommand = "/mute {player} {duration} {rule}";
    public String banCommand = "/ban {player} {duration} {rule}";
    public String permanentBanCommand = "/ban {player} {rule}";
    public List<String> forbiddenWords = new ArrayList<>();
    public List<String> ignoredNames = new ArrayList<>(List.of("server", "cmi", "chattools", "console"));
}
