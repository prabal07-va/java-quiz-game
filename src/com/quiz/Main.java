package com.quiz;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Main application launcher for the Java Quiz Game Web Server.
 */
public class Main {
    private static final int DEFAULT_PORT = 8080;

    public static void main(String[] args) {
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Invalid port number specified. Falling back to default: " + DEFAULT_PORT);
            }
        }

        // Locate web root directory
        Path webDir = resolveWebDirectory();
        if (webDir == null) {
            System.err.println("ERROR: Could not locate the 'web' directory containing HTML/CSS/JS assets.");
            System.err.println("Please run the program from the project root directory.");
            System.exit(1);
        }

        try {
            QuizServer server = new QuizServer(port, webDir);
            server.start();

            // Register graceful shutdown hook
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("\nStopping Java Quiz Game Server...");
                server.stop();
                System.out.println("Server stopped successfully.");
            }));

            System.out.println("Serving web assets from: " + webDir.toAbsolutePath());
            System.out.println("Press Ctrl+C to terminate the server.\n");

        } catch (IOException e) {
            System.err.println("Failed to start server on port " + port + ": " + e.getMessage());
            System.err.println("Hint: Port " + port + " might already be in use. Try passing another port: java -cp bin com.quiz.Main 8081");
        }
    }

    private static Path resolveWebDirectory() {
        Path[] candidates = new Path[]{
            Paths.get("web"),
            Paths.get("quiz-game-java/web"),
            Paths.get("../web")
        };

        for (Path candidate : candidates) {
            if (Files.exists(candidate) && Files.isDirectory(candidate)) {
                return candidate.toAbsolutePath().normalize();
            }
        }
        return null;
    }
}
