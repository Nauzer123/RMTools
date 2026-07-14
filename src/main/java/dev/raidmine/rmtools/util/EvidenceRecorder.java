package dev.raidmine.rmtools.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.ScreenshotRecorder;
import net.minecraft.text.Text;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class EvidenceRecorder {
    private EvidenceRecorder() {}
    public static void capture(String player, String rule) {
        MinecraftClient client = MinecraftClient.getInstance();
        String safe = player.replaceAll("[^A-Za-z0-9_\\-]", "_");
        String name = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + "_" + safe + "_" + rule + ".png";
        ScreenshotRecorder.saveScreenshot(client.runDirectory, name, client.getFramebuffer(), 1, text -> {
            if (client.player != null) client.player.sendMessage(Text.literal("§6RM Tools §7Скриншот: §f" + name), false);
        });
    }
}
