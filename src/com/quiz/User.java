package com.quiz;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a registered user account in the quiz system.
 */
public class User {
    private final String username;
    private final String password;
    private final String displayName;
    private final String createdAt;
    private int gamesPlayed;
    private int bestScore;

    public User(String username, String password, String displayName) {
        this.username = username.trim().toLowerCase();
        this.password = password;
        this.displayName = (displayName == null || displayName.trim().isEmpty()) ? username : displayName.trim();
        this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.gamesPlayed = 0;
        this.bestScore = 0;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getDisplayName() { return displayName; }
    public String getCreatedAt() { return createdAt; }
    public int getGamesPlayed() { return gamesPlayed; }
    public int getBestScore() { return bestScore; }

    public synchronized void recordGame(int score) {
        this.gamesPlayed++;
        if (score > this.bestScore) {
            this.bestScore = score;
        }
    }

    public String toJson() {
        return String.format(
            "{\"username\":\"%s\",\"displayName\":\"%s\",\"gamesPlayed\":%d,\"bestScore\":%d,\"createdAt\":\"%s\"}",
            escapeJson(username),
            escapeJson(displayName),
            gamesPlayed,
            bestScore,
            createdAt
        );
    }

    private static String escapeJson(String raw) {
        if (raw == null) return "";
        return raw.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
