package dev.raidmine.rmtools.screen;

import dev.raidmine.rmtools.RMToolsClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public final class SettingsScreen extends Screen {
    private final Screen parent;
    public SettingsScreen(Screen parent) { super(Text.literal("Настройки RM Tools")); this.parent = parent; }
    @Override protected void init() {
        int cx=width/2, y=70;
        addDrawableChild(ButtonWidget.builder(label("Скриншоты", RMToolsClient.CONFIG.get().screenshots), b -> { RMToolsClient.CONFIG.get().screenshots=!RMToolsClient.CONFIG.get().screenshots; b.setMessage(label("Скриншоты", RMToolsClient.CONFIG.get().screenshots)); save(); }).dimensions(cx-120,y,240,22).build()); y+=28;
        addDrawableChild(ButtonWidget.builder(label("Упоминания", RMToolsClient.CONFIG.get().detectMentions), b -> { RMToolsClient.CONFIG.get().detectMentions=!RMToolsClient.CONFIG.get().detectMentions; b.setMessage(label("Упоминания", RMToolsClient.CONFIG.get().detectMentions)); save(); }).dimensions(cx-120,y,240,22).build()); y+=28;
        addDrawableChild(ButtonWidget.builder(label("Запрещённые слова", RMToolsClient.CONFIG.get().detectForbiddenWords), b -> { RMToolsClient.CONFIG.get().detectForbiddenWords=!RMToolsClient.CONFIG.get().detectForbiddenWords; b.setMessage(label("Запрещённые слова", RMToolsClient.CONFIG.get().detectForbiddenWords)); save(); }).dimensions(cx-120,y,240,22).build()); y+=35;
        addDrawableChild(ButtonWidget.builder(Text.literal("Центрировать HUD"), b -> { RMToolsClient.CONFIG.get().hudX=-1; save(); }).dimensions(cx-120,y,240,22).build());
    }
    private Text label(String s, boolean v){ return Text.literal(s+": "+(v?"§aВкл":"§cВыкл")); }
    private void save(){ RMToolsClient.CONFIG.save(); }
    @Override public void render(DrawContext ctx,int mx,int my,float d){ renderBackground(ctx,mx,my,d);ctx.fill(width/2-150,35,width/2+150,height-35,0xE8141418);ctx.drawCenteredTextWithShadow(textRenderer,title,width/2,45,0xFFFFA000);super.render(ctx,mx,my,d);}
    @Override public void close(){ RMToolsClient.CONFIG.save(); client.setScreen(parent); }
}
