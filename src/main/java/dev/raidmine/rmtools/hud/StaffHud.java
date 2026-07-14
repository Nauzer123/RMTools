package dev.raidmine.rmtools.hud;

import dev.raidmine.rmtools.RMToolsClient;
import dev.raidmine.rmtools.punishment.PunishmentType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public final class StaffHud {
    private int warns, mutes, bans;
    private final long started = System.currentTimeMillis();
    private String notification;
    private long notificationUntil;
    public void record(PunishmentType type) {
        switch (type) { case WARN -> warns++; case MUTE -> mutes++; case BAN, PERMANENT_BAN -> bans++; }
    }
    public void notify(String text) { notification = text; notificationUntil = System.currentTimeMillis() + 4500; }
    public void tick() { if (notificationUntil < System.currentTimeMillis()) notification = null; }
    public void render(DrawContext ctx) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.options.hudHidden) return;
        int width = notification != null ? Math.max(260, mc.textRenderer.getWidth(notification) + 70) : 330;
        int x = RMToolsClient.CONFIG.get().hudX < 0 ? (mc.getWindow().getScaledWidth() - width) / 2 : RMToolsClient.CONFIG.get().hudX;
        int y = RMToolsClient.CONFIG.get().hudY;
        ctx.fill(x, y, x + width, y + 32, 0xD9141418);
        ctx.fill(x, y, x + width, y + 2, RMToolsClient.CONFIG.get().accent);
        ctx.drawTexture(Identifier.of("rmtools", "textures/gui/logo.png"), x + 8, y + 5, 0, 0, 22, 22, 1024, 1024);
        if (notification != null) {
            ctx.drawTextWithShadow(mc.textRenderer, notification, x + 38, y + 12, 0xFFFFB000);
        } else {
            long sec = (System.currentTimeMillis() - started) / 1000;
            String time = "%02d:%02d:%02d".formatted(sec / 3600, (sec / 60) % 60, sec % 60);
            String text = "Варны §f" + warns + "  §7Муты §f" + mutes + "  §7Баны §f" + bans + "  §7Сессия §f" + time;
            ctx.drawTextWithShadow(mc.textRenderer, text, x + 38, y + 12, 0xFFFFA000);
        }
    }
}
