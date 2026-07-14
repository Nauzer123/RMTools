package dev.raidmine.rmtools.punishment;

import dev.raidmine.rmtools.RMToolsClient;
import dev.raidmine.rmtools.util.EvidenceRecorder;
import net.minecraft.client.MinecraftClient;

public final class PunishmentManager {
    public void execute(String player, Rule rule, String duration) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.getNetworkHandler() == null) return;
        String template = switch (rule.type()) {
            case WARN -> RMToolsClient.CONFIG.get().warnCommand;
            case MUTE -> RMToolsClient.CONFIG.get().muteCommand;
            case BAN -> RMToolsClient.CONFIG.get().banCommand;
            case PERMANENT_BAN -> RMToolsClient.CONFIG.get().permanentBanCommand;
        };
        String command = template.replace("{player}", player).replace("{rule}", rule.id())
            .replace("{duration}", duration == null || duration.isBlank() ? rule.defaultDuration() : duration);
        if (command.startsWith("/")) command = command.substring(1);
        client.getNetworkHandler().sendChatCommand(command);
        RMToolsClient.HUD.record(rule.type());
        if (RMToolsClient.CONFIG.get().screenshots) EvidenceRecorder.capture(player, rule.id());
    }
}
