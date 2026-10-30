package com.quiz;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a completed quiz game score entry.
 */
public class ScoreRecord implements Comparable<ScoreRecord> {
    private final String playerName;
    private final String category;
    private final int score;
    private final int correctAnswers;
    private final int totalQuestions;
    private final int timeTakenSeconds;
    private final String timestamp;

    public ScoreRecord(String playerName, String category, int score, int correctAnswers, int totalQuestions, int timeTakenSeconds) {
        this.playerName = (playerName == null || playerName.trim().isEmpty()) ? "Anonymous Player" : playerName.trim();
        this.category = (category == null || category.trim().isEmpty()) ? "All" : category.trim();
        this.score = score;
        this.totalQuestions = Math.max(1, totalQuestions);
        this.correctAnswers = Math.min(this.totalQuestions, Math.max(0, correctAnswers));
        this.timeTakenSeconds = timeTakenSeconds;
        this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public ScoreRecord(String playerName, String category, int score, int totalQuestions, int timeTakenSeconds) {
        this(
            playerName,
            category,
            score,
            (score <= totalQuestions) ? score : Math.min(totalQuestions, score / 10),
            totalQuestions,
            timeTakenSeconds
        );
    }

    public String getPlayerName() { return playerName; }
    public String getCategory() { return category; }
    public int getScore() { return score; }
    public int getCorrectAnswers() { return correctAnswers; }
    public int getTotalQuestions() { return totalQuestions; }
    public int getTimeTakenSeconds() { return timeTakenSeconds; }
    public String getTimestamp() { return timestamp; }

    public double getPercentage() {
        return Math.round(((double) correctAnswers / totalQuestions) * 1000.0) / 10.0;
    }

    public String toJson() {
        return String.format(
            "{\"playerName\":\"%s\",\"category\":\"%s\",\"score\":%d,\"correctAnswers\":%d,\"totalQuestions\":%d,\"percentage\":%.1f,\"timeTakenSeconds\":%d,\"timestamp\":\"%s\"}",
            escapeJson(playerName),
            escapeJson(category),
            score,
            correctAnswers,
            totalQuestions,
            getPercentage(),
            timeTakenSeconds,
            timestamp
        );
    }

    private static String escapeJson(String raw) {
        if (raw == null) return "";
        return raw.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    @Override
    public int compareTo(ScoreRecord other) {
        // Higher score first, then shorter time
        if (this.score != other.score) {
            return Integer.compare(other.score, this.score);
        }
        return Integer.compare(this.timeTakenSeconds, other.timeTakenSeconds);
    }
}
