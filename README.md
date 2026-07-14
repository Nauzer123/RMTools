# RM Tools 1.0.0

Клиентский Fabric-мод для персонала RaidMine. Авторы: **NAU1ZER** и **xzktoV2_1**.

## Реализовано

- меню на Right Shift;
- меню выбора наказания по пунктам 2.1–3.11;
- команды LiteBans `/warn`, `/mute`, `/ban` через редактируемые шаблоны;
- HUD: варны, муты, баны и время сессии;
- уведомления об упоминаниях и запрещённых словах;
- автоматический скриншот после команды;
- кнопка настроек в ESC;
- JSON-конфиг `config/rmtools.json`;
- логотип RM и адаптивный интерфейс.

## Установка

1. Установить LabyMod 4 с профилем Minecraft 1.21.11/Fabric.
2. Установить Fabric API для 1.21.11.
3. Собрать проект командой `./gradlew build` на Java 21.
4. Скопировать `build/libs/rm-tools-1.0.0.jar` в папку `mods` выбранной игровой директории LabyMod.
5. Запустить игру. Настройки создадутся в `config/rmtools.json`.

## SmartChat

Мод читает итоговый компонент чата, поэтому работает поверх стандартного чата и SmartChat без обязательной зависимости от SmartChat API. Служебные имена `Server`, `CMI`, `ChatTools`, `Console` игнорируются.

## Команды

```json
"warnCommand": "/warn {player} {rule}",
"muteCommand": "/mute {player} {duration} {rule}",
"banCommand": "/ban {player} {duration} {rule}",
"permanentBanCommand": "/ban {player} {rule}"
```

## Важное ограничение текущей сборки

Точное нажатие колёсиком по визуальной области ника требует отдельного version-specific mixin для внутреннего рендера ChatHud/SmartChat. Основа распознавания ников и меню присутствует, но этот перехват следует проверить и при необходимости адаптировать под фактическую сборку LabyMod 4 и SmartChat.
