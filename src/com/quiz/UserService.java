package com.quiz;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service managing user registration, authentication, and profiles.
 * Contains ZERO pre-loaded data - only records actual user data.
 */
public class UserService {
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final Path storageFile;

    public UserService() {
        this.storageFile = Paths.get("data", "users.txt");
        loadFromFile();
    }

    public synchronized User register(String username, String password, String displayName) throws IllegalArgumentException {
        if (username == null || username.trim().length() < 3) {
            throw new IllegalArgumentException("Username must be at least 3 characters long.");
        }
        if (password == null || password.trim().length() < 4) {
            throw new IllegalArgumentException("Password must be at least 4 characters long.");
        }

        String normalizedUsername = username.trim().toLowerCase();
        if (users.containsKey(normalizedUsername)) {
            throw new IllegalArgumentException("Username is already taken. Please choose another username.");
        }

        User user = new User(normalizedUsername, password, displayName);
        users.put(normalizedUsername, user);
        saveToFile();
        return user;
    }

    public User authenticate(String username, String password) {
        if (username == null || password == null) return null;
        String normalizedUsername = username.trim().toLowerCase();
        User user = users.get(normalizedUsername);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public User getUser(String username) {
        if (username == null) return null;
        return users.get(username.trim().toLowerCase());
    }

    public synchronized void recordGame(String username, int score) {
        if (username == null) return;
        User user = users.get(username.trim().toLowerCase());
        if (user != null) {
            user.recordGame(score);
            saveToFile();
        }
    }

    private void saveToFile() {
        try {
            Path parent = storageFile.getParent();
            if (parent != null && !Files.exists(parent)) {
                Files.createDirectories(parent);
            }
            List<String> lines = new ArrayList<>();
            for (User u : users.values()) {
                // Format: username|password|displayName|gamesPlayed|bestScore|createdAt
                lines.add(String.join("|",
                    u.getUsername(),
                    u.getPassword(),
                    u.getDisplayName(),
                    String.valueOf(u.getGamesPlayed()),
                    String.valueOf(u.getBestScore()),
                    u.getCreatedAt()
                ));
            }
            Files.write(storageFile, lines, StandardCharsets.UTF_8);
        } catch (IOException ignored) {}
    }

    private void loadFromFile() {
        try {
            if (Files.exists(storageFile)) {
                List<String> lines = Files.readAllLines(storageFile, StandardCharsets.UTF_8);
                for (String line : lines) {
                    String[] parts = line.split("\\|");
                    if (parts.length >= 3) {
                        User u = new User(parts[0], parts[1], parts[2]);
                        if (parts.length >= 5) {
                            try {
                                int games = Integer.parseInt(parts[3]);
                                int best = Integer.parseInt(parts[4]);
                                for (int i = 0; i < games; i++) {
                                    u.recordGame(best);
                                }
                            } catch (NumberFormatException ignored) {}
                        }
                        users.put(u.getUsername(), u);
                    }
                }
            }
        } catch (IOException ignored) {}
    }
}
