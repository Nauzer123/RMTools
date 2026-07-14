package dev.raidmine.rmtools.punishment;
public record Rule(String id, String title, PunishmentType type, String defaultDuration, String category) {}
