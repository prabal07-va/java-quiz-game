package com.quiz;

import java.util.List;

/**
 * Represents a single quiz question with multiple choice options.
 */
public class Question {
    private final int id;
    private final String question;
    private final List<String> options;
    private final int correctIndex;
    private final String explanation;
    private final String category;
    private final String difficulty;

    public Question(int id, String question, List<String> options, int correctIndex, String explanation, String category, String difficulty) {
        this.id = id;
        this.question = question;
        this.options = options;
        this.correctIndex = correctIndex;
        this.explanation = explanation;
        this.category = category;
        this.difficulty = difficulty;
    }

    public int getId() { return id; }
    public String getQuestion() { return question; }
    public List<String> getOptions() { return options; }
    public int getCorrectIndex() { return correctIndex; }
    public String getExplanation() { return explanation; }
    public String getCategory() { return category; }
    public String getDifficulty() { return difficulty; }

    public String toJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"id\":").append(id).append(",");
        sb.append("\"question\":\"").append(escapeJson(question)).append("\",");
        sb.append("\"options\":[");
        for (int i = 0; i < options.size(); i++) {
            sb.append("\"").append(escapeJson(options.get(i))).append("\"");
            if (i < options.size() - 1) sb.append(",");
        }
        sb.append("],");
        sb.append("\"correctIndex\":").append(correctIndex).append(",");
        sb.append("\"explanation\":\"").append(escapeJson(explanation)).append("\",");
        sb.append("\"category\":\"").append(escapeJson(category)).append("\",");
        sb.append("\"difficulty\":\"").append(escapeJson(difficulty)).append("\"");
        sb.append("}");
        return sb.toString();
    }

    private static String escapeJson(String raw) {
        if (raw == null) return "";
        return raw.replace("\\", "\\\\")
                  .replace("\"", "\\\"")
                  .replace("\b", "\\b")
                  .replace("\f", "\\f")
                  .replace("\n", "\\n")
                  .replace("\r", "\\r")
                  .replace("\t", "\\t");
    }
}
