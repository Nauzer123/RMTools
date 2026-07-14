package dev.raidmine.rmtools.screen;

import dev.raidmine.rmtools.RMToolsClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public final class MainMenuScreen extends Screen {
    private final Screen parent;
    private long opened;
    public MainMenuScreen(Screen parent) { super(Text.literal("RM Tools")); this.parent = parent; }
    @Override protected void init() {
        opened = System.currentTimeMillis();
        int cx = width / 2;
        addDrawableChild(ButtonWidget.builder(Text.literal("Выдать наказание"), b -> {
            String player = RMToolsClient.CHAT.selectedPlayer();
            if (player == null) player = "Player";
            client.setScreen(new PunishmentScreen(this, player));
        }).dimensions(cx - 100, height / 2 + 80, 200, 22).build());
        addDrawableChild(ButtonWidget.builder(Text.literal("Настройки"), b -> client.setScreen(new SettingsScreen(this)))
            .dimensions(cx - 100, height / 2 + 108, 200, 22).build());
    }
    @Override public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        renderBackground(ctx, mouseX, mouseY, delta);
        float t = Math.min(1f, (System.currentTimeMillis() - opened) / 280f);
        int panelW = (int)(420 * (0.92f + .08f * t));
        int x = (width - panelW) / 2;
        int y = height / 2 - 155;
        ctx.fill(x, y, x + panelW, y + 310, 0xE8141418);
        ctx.fill(x, y, x + panelW, y + 3, RMToolsClient.CONFIG.get().accent);
        ctx.drawTexture(Identifier.of("rmtools", "textures/gui/logo.png"), width / 2 - 70, y + 25, 0, 0, 140, 140, 1024, 1024);
        ctx.drawCenteredTextWithShadow(textRenderer, "RM TOOLS", width / 2, y + 170, 0xFFFFA000);
        ctx.drawCenteredTextWithShadow(textRenderer, "RaidMine Staff Toolkit", width / 2, y + 188, 0xFFB8B8C0);
        super.render(ctx, mouseX, mouseY, delta);
    }
    @Override public void close() { client.setScreen(parent); }
}
