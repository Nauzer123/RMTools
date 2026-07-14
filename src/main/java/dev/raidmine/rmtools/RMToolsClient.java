package dev.raidmine.rmtools;

import dev.raidmine.rmtools.chat.ChatMonitor;
import dev.raidmine.rmtools.config.ConfigManager;
import dev.raidmine.rmtools.hud.StaffHud;
import dev.raidmine.rmtools.punishment.PunishmentManager;
import dev.raidmine.rmtools.screen.MainMenuScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public final class RMToolsClient implements ClientModInitializer {
    public static final String MOD_ID = "rmtools";
    public static final ConfigManager CONFIG = new ConfigManager();
    public static final StaffHud HUD = new StaffHud();
    public static final ChatMonitor CHAT = new ChatMonitor();
    public static final PunishmentManager PUNISHMENTS = new PunishmentManager();
    private static KeyBinding openKey;

    @Override public void onInitializeClient() {
        CONFIG.load();
        openKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.rmtools.open", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT_SHIFT, "category.rmtools"));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openKey.wasPressed()) client.setScreen(new MainMenuScreen(client.currentScreen));
            CHAT.tick(client);
            HUD.tick();
        });
        HudRenderCallback.EVENT.register((context, tickCounter) -> HUD.render(context));
    }
}
