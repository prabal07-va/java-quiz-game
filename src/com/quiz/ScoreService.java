package com.quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Manages player scores and leaderboard.
 */
public class ScoreService {
    private final List<ScoreRecord> scores = new CopyOnWriteArrayList<>();

    public ScoreService() {
        // Seed initial high scores
        scores.add(new ScoreRecord("Alex Dev", "Java Basics", 100, 10, 10, 58));
        scores.add(new ScoreRecord("Sophia Code", "OOP Concepts", 90, 9, 10, 64));
        scores.add(new ScoreRecord("James Byte", "Collections", 80, 8, 10, 72));
    }

    public synchronized void addScore(ScoreRecord record) {
        scores.add(record);
    }

    public List<ScoreRecord> getTopScores(int limit) {
        List<ScoreRecord> sorted = new ArrayList<>(scores);
        Collections.sort(sorted);
        if (limit > 0 && limit < sorted.size()) {
            return sorted.subList(0, limit);
        }
        return sorted;
    }

    public String getLeaderboardJson(int limit) {
        List<ScoreRecord> top = getTopScores(limit);
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < top.size(); i++) {
            sb.append(top.get(i).toJson());
            if (i < top.size() - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}
