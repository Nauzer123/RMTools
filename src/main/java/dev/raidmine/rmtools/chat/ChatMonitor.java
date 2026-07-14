package dev.raidmine.rmtools.chat;

import dev.raidmine.rmtools.RMToolsClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ChatMonitor {
    private static final Pattern NAME = Pattern.compile("(?:^|\\s)([A-Za-z0-9_]{3,16})(?=\\s*[:»>])");
    private String selectedPlayer;
    public void accept(Text message) {
        String raw = message.getString();
        String lower = raw.toLowerCase(Locale.ROOT);
        Matcher m = NAME.matcher(raw);
        String sender = m.find() ? m.group(1) : null;
        if (sender == null || RMToolsClient.CONFIG.get().ignoredNames.stream().anyMatch(s -> s.equalsIgnoreCase(sender))) return;
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player != null && RMToolsClient.CONFIG.get().detectMentions && lower.contains(mc.player.getName().getString().toLowerCase(Locale.ROOT)))
            RMToolsClient.HUD.notify(sender + " упомянул вас в чате");
        if (RMToolsClient.CONFIG.get().detectForbiddenWords && RMToolsClient.CONFIG.get().forbiddenWords.stream().anyMatch(w -> !w.isBlank() && lower.contains(w.toLowerCase(Locale.ROOT))))
            RMToolsClient.HUD.notify(sender + " нарушил правила чата!");
        selectedPlayer = sender;
    }
    public String selectedPlayer() { return selectedPlayer; }
    public void select(String player) { selectedPlayer = player; }
    public void tick(MinecraftClient client) {}
}
