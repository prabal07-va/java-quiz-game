package com.quiz;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.Executors;

/**
 * Embedded HTTP server hosting REST APIs and static quiz webpage assets.
 */
public class QuizServer {
    private final int port;
    private final QuestionService questionService;
    private final ScoreService scoreService;
    private final UserService userService;
    private final Path webRootDir;
    private HttpServer server;

    public QuizServer(int port, Path webRootDir) {
        this.port = port;
        this.webRootDir = webRootDir;
        this.questionService = new QuestionService();
        this.scoreService = new ScoreService();
        this.userService = new UserService();
    }

    public void start() throws IOException {
        server = HttpServer.create(new InetSocketAddress(port), 0);
        server.setExecutor(Executors.newCachedThreadPool());

        // API endpoints
        server.createContext("/api/questions", new QuestionsHandler());
        server.createContext("/api/categories", new CategoriesHandler());
        server.createContext("/api/leaderboard", new LeaderboardHandler());
        server.createContext("/api/submit", new SubmitScoreHandler());
        server.createContext("/api/auth/register", new RegisterHandler());
        server.createContext("/api/auth/login", new LoginHandler());
        server.createContext("/api/auth/profile", new ProfileHandler());
        server.createContext("/api/user/history", new UserHistoryHandler());

        // Static files handler for root and assets
        server.createContext("/", new StaticFileHandler());

        server.start();
        System.out.println("==================================================");
        System.out.println("  quizHunt Web Server is running!");
        System.out.println("  Access webpage at: http://localhost:" + port);
        System.out.println("==================================================");
    }

    public void stop() {
        if (server != null) {
            server.stop(0);
        }
    }

    // --- Handlers ---

    private class QuestionsHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            addCorsHeaders(exchange);
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            Map<String, String> params = parseQueryParams(exchange.getRequestURI().getQuery());
            String category = params.getOrDefault("category", "All");
            int limit = 10;
            try {
                if (params.containsKey("limit")) {
                    limit = Integer.parseInt(params.get("limit"));
                }
            } catch (NumberFormatException ignored) {}

            List<Question> questions = questionService.getQuestions(category, limit, true);
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < questions.size(); i++) {
                sb.append(questions.get(i).toJson());
                if (i < questions.size() - 1) sb.append(",");
            }
            sb.append("]");

            sendJsonResponse(exchange, 200, sb.toString());
        }
    }

    private class CategoriesHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            addCorsHeaders(exchange);
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            List<String> categories = questionService.getCategories();
            StringBuilder sb = new StringBuilder("[\"All\"");
            for (String cat : categories) {
                sb.append(",\"").append(cat.replace("\"", "\\\"")).append("\"");
            }
            sb.append("]");

            sendJsonResponse(exchange, 200, sb.toString());
        }
    }

    private class LeaderboardHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            addCorsHeaders(exchange);
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            String json = scoreService.getLeaderboardJson(20);
            sendJsonResponse(exchange, 200, json);
        }
    }

    private class SubmitScoreHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            addCorsHeaders(exchange);
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendJsonResponse(exchange, 405, "{\"error\":\"Method Not Allowed\"}");
                return;
            }

            try (InputStream is = exchange.getRequestBody()) {
                String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
                Map<String, String> jsonMap = parseSimpleJson(body);

                String name = jsonMap.getOrDefault("playerName", "Guest");
                String cat = jsonMap.getOrDefault("category", "All");
                int score = Integer.parseInt(jsonMap.getOrDefault("score", "0"));
                int correctAnswers = Integer.parseInt(jsonMap.getOrDefault("correctAnswers", String.valueOf(score <= 10 ? score : score / 10)));
                int total = Integer.parseInt(jsonMap.getOrDefault("totalQuestions", "10"));
                int timeTaken = Integer.parseInt(jsonMap.getOrDefault("timeTakenSeconds", "0"));

                ScoreRecord record = new ScoreRecord(name, cat, score, correctAnswers, total, timeTaken);
                scoreService.addScore(record);
                userService.recordGame(name, score);

                sendJsonResponse(exchange, 200, "{\"status\":\"ok\",\"record\":" + record.toJson() + "}");
            } catch (Exception e) {
                sendJsonResponse(exchange, 400, "{\"error\":\"Invalid payload: " + e.getMessage() + "\"}");
            }
        }
    }

    private class RegisterHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            addCorsHeaders(exchange);
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendJsonResponse(exchange, 405, "{\"error\":\"Method Not Allowed\"}");
                return;
            }

            try (InputStream is = exchange.getRequestBody()) {
                String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
                Map<String, String> jsonMap = parseSimpleJson(body);

                String username = jsonMap.get("username");
                String password = jsonMap.get("password");
                String displayName = jsonMap.getOrDefault("displayName", username);

                User user = userService.register(username, password, displayName);
                sendJsonResponse(exchange, 200, "{\"status\":\"ok\",\"user\":" + user.toJson() + "}");
            } catch (IllegalArgumentException e) {
                sendJsonResponse(exchange, 400, "{\"error\":\"" + escapeJson(e.getMessage()) + "\"}");
            } catch (Exception e) {
                sendJsonResponse(exchange, 500, "{\"error\":\"Internal server error: " + escapeJson(e.getMessage()) + "\"}");
            }
        }
    }

    private class LoginHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            addCorsHeaders(exchange);
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendJsonResponse(exchange, 405, "{\"error\":\"Method Not Allowed\"}");
                return;
            }

            try (InputStream is = exchange.getRequestBody()) {
                String body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
                Map<String, String> jsonMap = parseSimpleJson(body);

                String username = jsonMap.get("username");
                String password = jsonMap.get("password");

                User user = userService.authenticate(username, password);
                if (user != null) {
                    sendJsonResponse(exchange, 200, "{\"status\":\"ok\",\"user\":" + user.toJson() + "}");
                } else {
                    sendJsonResponse(exchange, 401, "{\"error\":\"Invalid username or password. Please try again.\"}");
                }
            } catch (Exception e) {
                sendJsonResponse(exchange, 500, "{\"error\":\"Internal error: " + escapeJson(e.getMessage()) + "\"}");
            }
        }
    }

    private class ProfileHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            addCorsHeaders(exchange);
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            Map<String, String> params = parseQueryParams(exchange.getRequestURI().getQuery());
            String username = params.get("username");
            if (username == null || username.trim().isEmpty()) {
                sendJsonResponse(exchange, 400, "{\"error\":\"Username parameter required\"}");
                return;
            }

            User user = userService.getUser(username);
            if (user != null) {
                sendJsonResponse(exchange, 200, "{\"status\":\"ok\",\"user\":" + user.toJson() + "}");
            } else {
                sendJsonResponse(exchange, 404, "{\"error\":\"User not found\"}");
            }
        }
    }

    private class UserHistoryHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            addCorsHeaders(exchange);
            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            Map<String, String> params = parseQueryParams(exchange.getRequestURI().getQuery());
            String username = params.get("username");
            String json = scoreService.getUserHistoryJson(username);
            sendJsonResponse(exchange, 200, json);
        }
    }

    private class StaticFileHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String pathStr = exchange.getRequestURI().getPath();
            if (pathStr.equals("/") || pathStr.isEmpty()) {
                pathStr = "/index.html";
            }

            // Sanitize path to prevent directory traversal
            Path resolved = webRootDir.resolve(pathStr.substring(1)).normalize();
            if (!resolved.startsWith(webRootDir) || !Files.exists(resolved) || Files.isDirectory(resolved)) {
                String notFound = "<h1>404 Not Found</h1><p>The requested file does not exist.</p>";
                byte[] bytes = notFound.getBytes(StandardCharsets.UTF_8);
                exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
                exchange.sendResponseHeaders(404, bytes.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(bytes);
                }
                return;
            }

            String mime = getMimeType(resolved.getFileName().toString());
            byte[] fileBytes = Files.readAllBytes(resolved);

            addCorsHeaders(exchange);
            exchange.getResponseHeaders().set("Content-Type", mime);
            exchange.sendResponseHeaders(200, fileBytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(fileBytes);
            }
        }
    }

    // --- Helpers ---

    private static void sendJsonResponse(HttpExchange exchange, int statusCode, String json) throws IOException {
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    private static void addCorsHeaders(HttpExchange exchange) {
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");
    }

    private static String getMimeType(String filename) {
        if (filename.endsWith(".html") || filename.endsWith(".htm")) return "text/html; charset=UTF-8";
        if (filename.endsWith(".css")) return "text/css; charset=UTF-8";
        if (filename.endsWith(".js")) return "application/javascript; charset=UTF-8";
        if (filename.endsWith(".json")) return "application/json; charset=UTF-8";
        if (filename.endsWith(".svg")) return "image/svg+xml";
        if (filename.endsWith(".png")) return "image/png";
        if (filename.endsWith(".ico")) return "image/x-icon";
        return "application/octet-stream";
    }

    private static Map<String, String> parseQueryParams(String query) {
        Map<String, String> params = new HashMap<>();
        if (query == null || query.trim().isEmpty()) return params;

        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            if (idx > 0) {
                String key = URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8);
                String val = URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8);
                params.put(key, val);
            }
        }
        return params;
    }

    private static Map<String, String> parseSimpleJson(String json) {
        Map<String, String> map = new HashMap<>();
        if (json == null) return map;
        String clean = json.trim();
        if (clean.startsWith("{")) clean = clean.substring(1);
        if (clean.endsWith("}")) clean = clean.substring(0, clean.length() - 1);

        // Simple token split by comma outside quotes
        List<String> pairs = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder current = new StringBuilder();
        for (int i = 0; i < clean.length(); i++) {
            char c = clean.charAt(i);
            if (c == '\"' && (i == 0 || clean.charAt(i - 1) != '\\')) {
                inQuotes = !inQuotes;
            }
            if (c == ',' && !inQuotes) {
                pairs.add(current.toString().trim());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }
        if (current.length() > 0) {
            pairs.add(current.toString().trim());
        }

        for (String pair : pairs) {
            int colonIdx = pair.indexOf(':');
            if (colonIdx > 0) {
                String key = pair.substring(0, colonIdx).trim().replaceAll("^\"|\"$", "");
                String value = pair.substring(colonIdx + 1).trim().replaceAll("^\"|\"$", "");
                map.put(key, value);
            }
        }
        return map;
    }

    private static String escapeJson(String raw) {
        if (raw == null) return "";
        return raw.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
