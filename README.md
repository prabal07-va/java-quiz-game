# 🎯 quizHunt - Interactive Java Quiz Web Game

> 🌐 **Live Web Version (Play on any PC/Mobile)**:
> 👉 **[https://prabal07-va.github.io/java-quiz-game/](https://prabal07-va.github.io/java-quiz-game/)**

---

A full-stack Java web application featuring user authentication, interactive quiz gameplay with real-time seconds countdown timer, dynamic question evaluation, sound effects, animated confetti, and a detailed end result breakdown with answer review and persistent leaderboard. Zero pre-loaded dummy data — all records reflect real player activity.

---

## 🌟 Key Features

1. **Pure Java Backend Server (`com.sun.net.httpserver.HttpServer`)**:
   - Zero external dependencies required (works out of the box with standard Java SE 8+ and Java 26+).
   - High-performance multi-threaded HTTP server handling static asset serving (`HTML`, `CSS`, `JS`, `SVG`, `JSON`).
   - REST API endpoints for question retrieval, category filtering, leaderboard ranking, and score submission.

2. **Real-time Seconds Countdown Timer**:
   - Customizable time limits per question (e.g. **10s Blitz**, **15s Standard**, or **30s Relaxed**).
   - Circular SVG animated countdown ring visually depleting second-by-second.
   - Dynamic visual urgency states: Green (>7s) $\rightarrow$ Amber Warning (4–7s) $\rightarrow$ Flashing Crimson Alert ($\le$ 3s).
   - Audio feedback: subtle ticking sounds and urgent countdown warning beeps (synthesized via browser Web Audio API — zero external audio files needed).
   - Automatic timeout handling: reveals the correct answer and logs a timeout when time expires.

3. **Comprehensive End Result Dashboard**:
   - Score gauge with animated percentage ring (e.g., $90\%$).
   - Tiered performance titles & badges (e.g. *Grand Java Architect*, *Senior Java Developer*, *Proficient Coder*).
   - Canvas confetti shower for scores $\ge 70\%$.
   - Complete stat counters: Total Score, Correct Answers, Incorrect Answers, Timed Out, Total Time Taken, and Average Time per Question.
   - **Detailed Question Review**: Expandable question-by-question breakdown showing player's answer vs. the correct answer, seconds spent, and in-depth explanations.
   - **Hall of Fame Leaderboard**: Ranks top scores with medal badges ($🥇, 🥈, 🥉$).
   - One-click **Copy Result** button to share score summary.

---

## 📂 Project Structure

```
quiz-game-java/
├── src/
│   └── com/
│       └── quiz/
│           ├── Main.java              # Application launcher & port configuration
│           ├── QuizServer.java        # Embedded HTTP web server & REST handlers
│           ├── Question.java          # Question data model with JSON serialization
│           ├── QuestionService.java   # Question bank & category query engine
│           ├── ScoreRecord.java       # Player score model & ranking comparator
│           └── ScoreService.java      # Leaderboard manager
├── web/
│   ├── index.html                     # Main interactive quiz single-page web app
│   ├── style.css                      # Modern dark glassmorphism styling & animations
│   └── app.js                         # Game controller, timer, Web Audio & confetti
├── bin/                               # Compiled Java bytecode (.class files)
├── run.bat                            # One-click Windows build & run script
├── compile.bat                        # One-click Windows compile script
└── README.md                          # Documentation
```

---

## 🚀 How to Run

### Method 1: One-Click Run (Windows)
Double-click `run.bat` or run in terminal:
```powershell
.\run.bat
```
Then open your browser and navigate to:
```
http://localhost:8080
```

### Method 2: Manual Terminal Commands
From inside the `quiz-game-java` folder:

1. **Compile**:
   ```powershell
   javac -d bin src\com\quiz\*.java
   ```

2. **Run**:
   ```powershell
   java -cp bin com.quiz.Main 8080
   ```

3. **Open in Browser**:
   Visit [http://localhost:8080](http://localhost:8080)

---

## 🎮 Keyboard Shortcuts

- `1`, `2`, `3`, `4` or `A`, `B`, `C`, `D`: Select option A, B, C, or D
- `Enter`: Advance to next question after answering

---

## 🛠️ Adding New Questions
To add or modify questions, open `src/com/quiz/QuestionService.java` and add entries inside `loadQuestions()`:
```java
questionBank.add(new Question(
    id++,
    "Your question text here?",
    Arrays.asList("Option A", "Option B", "Option C", "Option D"),
    0, // 0-indexed correct option (0 = A, 1 = B, 2 = C, 3 = D)
    "Detailed explanation why this answer is correct.",
    "Java Basics", // Category
    "Medium"       // Difficulty
));
```
