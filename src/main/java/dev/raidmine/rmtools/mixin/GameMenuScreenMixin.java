package dev.raidmine.rmtools.mixin;

import dev.raidmine.rmtools.screen.SettingsScreen;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin extends Screen {
    protected GameMenuScreenMixin(Text title) { super(title); }
    @Inject(method = "initWidgets", at = @At("TAIL"))
    private void rmtools$button(CallbackInfo ci) {
        addDrawableChild(ButtonWidget.builder(Text.literal("RM Tools"), b -> client.setScreen(new SettingsScreen(this)))
            .dimensions(6, height - 28, 110, 20).build());
    }
}
