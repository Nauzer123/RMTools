package dev.raidmine.rmtools.punishment;

import java.util.List;

public final class RuleRegistry {
    private RuleRegistry() {}
    public static final List<Rule> RULES = List.of(
      new Rule("2.1", "Флуд, капс, чрезмерный мат", PunishmentType.WARN, "", "Общение"),
      new Rule("2.2", "Оскорбление", PunishmentType.MUTE, "12h", "Общение"),
      new Rule("2.3", "Оскорбление родных", PunishmentType.MUTE, "5d", "Общение"),
      new Rule("2.4", "Сообщения сексуального характера", PunishmentType.MUTE, "2h", "Общение"),
      new Rule("2.5", "Неадекватное поведение", PunishmentType.MUTE, "2h", "Общение"),
      new Rule("2.6", "Реклама серверов и ресурсов", PunishmentType.BAN, "14d", "Общение"),
      new Rule("2.7", "Разжигание ненависти", PunishmentType.MUTE, "9h", "Общение"),
      new Rule("2.8", "Сторонние ссылки", PunishmentType.MUTE, "3d", "Общение"),
      new Rule("2.9", "Выдача себя за администратора", PunishmentType.MUTE, "12h", "Общение"),
      new Rule("2.10", "Неигровые угрозы", PunishmentType.MUTE, "12h", "Общение"),
      new Rule("2.11", "Угроза наказанием без причины", PunishmentType.MUTE, "6h", "Общение"),
      new Rule("2.12", "Обсуждение политики", PunishmentType.MUTE, "12h", "Общение"),
      new Rule("2.13", "Введение в заблуждение", PunishmentType.MUTE, "2h", "Общение"),
      new Rule("2.14", "Попрошайничество у администрации", PunishmentType.MUTE, "6h", "Общение"),
      new Rule("2.15", "Помехи в голосовом чате", PunishmentType.MUTE, "4h", "Общение"),
      new Rule("3.1", "Запрещённый никнейм", PunishmentType.PERMANENT_BAN, "", "Игровой процесс"),
      new Rule("3.2", "Более трёх аккаунтов", PunishmentType.PERMANENT_BAN, "", "Игровой процесс"),
      new Rule("3.3", "Торговля за реальные деньги", PunishmentType.BAN, "30d", "Игровой процесс"),
      new Rule("3.4", "Передача или продажа аккаунта", PunishmentType.PERMANENT_BAN, "", "Игровой процесс"),
      new Rule("3.5", "Взлом аккаунтов", PunishmentType.PERMANENT_BAN, "", "Игровой процесс"),
      new Rule("3.6", "Баги и лаг-машины", PunishmentType.BAN, "15d", "Игровой процесс"),
      new Rule("3.7", "Запрещённое ПО", PunishmentType.BAN, "30d", "Игровой процесс"),
      new Rule("3.8", "Обход бана за читы", PunishmentType.BAN, "30d", "Игровой процесс"),
      new Rule("3.9", "Подстрекательство к нарушению", PunishmentType.BAN, "3d", "Игровой процесс"),
      new Rule("3.10", "Тим с читером", PunishmentType.BAN, "15d", "Игровой процесс"),
      new Rule("3.11", "Превышение лимита союзников", PunishmentType.BAN, "7d", "Игровой процесс")
    );
}
