package dev.raidmine.rmtools.screen;

import dev.raidmine.rmtools.RMToolsClient;
import dev.raidmine.rmtools.punishment.Rule;
import dev.raidmine.rmtools.punishment.RuleRegistry;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import java.util.ArrayList;
import java.util.List;

public final class PunishmentScreen extends Screen {
    private final Screen parent; private final String player; private TextFieldWidget search; private final List<ButtonWidget> rows = new ArrayList<>();
    public PunishmentScreen(Screen parent, String player) { super(Text.literal("Наказание: " + player)); this.parent = parent; this.player = player; }
    @Override protected void init() {
        search = new TextFieldWidget(textRenderer, width/2-180, 55, 360, 22, Text.literal("Поиск"));
        search.setPlaceholder(Text.literal("Пункт или описание...")); search.setChangedListener(s -> rebuild()); addDrawableChild(search); rebuild();
    }
    private void rebuild() {
        rows.forEach(this::remove); rows.clear();
        String q = search == null ? "" : search.getText().toLowerCase(); int y = 88;
        for (Rule rule : RuleRegistry.RULES) {
            if (!q.isBlank() && !(rule.id()+" "+rule.title()).toLowerCase().contains(q)) continue;
            Rule r = rule;
            ButtonWidget b = ButtonWidget.builder(Text.literal(r.id()+"  "+r.title()+"   ["+r.type()+"]"), x -> RMToolsClient.PUNISHMENTS.execute(player, r, r.defaultDuration()))
                .dimensions(width/2-180, y, 360, 20).build(); rows.add(b); addDrawableChild(b); y += 23; if (y > height-35) break;
        }
    }
    @Override public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        renderBackground(ctx, mouseX, mouseY, delta); ctx.fill(width/2-200, 30, width/2+200, height-20, 0xE8141418);
        ctx.fill(width/2-200, 30, width/2+200, 33, RMToolsClient.CONFIG.get().accent);
        ctx.drawCenteredTextWithShadow(textRenderer, "Наказание для " + player, width/2, 39, 0xFFFFA000); super.render(ctx, mouseX, mouseY, delta);
    }
    @Override public void close() { client.setScreen(parent); }
}
