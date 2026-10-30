import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class QuizGame {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Question> questions = new ArrayList<>();

    public QuizGame() {
        loadQuestions();
    }

    private void loadQuestions() {
        questions.add(new Question(
            "Which keyword is used to inherit a class in Java?",
            new String[]{"implements", "extends", "inherits", "super"},
            2
        ));
        questions.add(new Question(
            "Which method is the entry point of a Java application?",
            new String[]{"start()", "run()", "main()", "init()"},
            3
        ));
        questions.add(new Question(
            "Which data type stores true or false values?",
            new String[]{"int", "char", "String", "boolean"},
            4
        ));
        questions.add(new Question(
            "Which collection does not allow duplicate elements?",
            new String[]{"ArrayList", "HashSet", "LinkedList", "Vector"},
            2
        ));
        questions.add(new Question(
            "What is the size of an int in Java?",
            new String[]{"8 bits", "16 bits", "32 bits", "64 bits"},
            3
        ));
        questions.add(new Question(
            "Which symbol ends most Java statements?",
            new String[]{".", ":", ";", ","},
            3
        ));
        questions.add(new Question(
            "Which keyword creates an object?",
            new String[]{"new", "class", "this", "void"},
            1
        ));
        questions.add(new Question(
            "Which of these is not a primitive data type?",
            new String[]{"double", "boolean", "String", "char"},
            3
        ));
        questions.add(new Question(
            "Which block is used to handle an exception?",
            new String[]{"try-catch", "if-else", "for-while", "switch-case"},
            1
        ));
        questions.add(new Question(
            "Java source code is compiled into:",
            new String[]{"Machine code directly", "Bytecode", "HTML", "Assembly only"},
            2
        ));
    }

    public void start() {
        System.out.println("================================");
        System.out.println("         JAVA QUIZ GAME         ");
        System.out.println("================================");

        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            name = "Player";
        }

        List<Question> roundQuestions = new ArrayList<>(questions);
        Collections.shuffle(roundQuestions);

        int score = 0;
        int questionNumber = 1;

        for (Question question : roundQuestions) {
            System.out.println("\nQuestion " + questionNumber + " of " + roundQuestions.size());
            System.out.println(question.getText());

            String[] options = question.getOptions();
            for (int i = 0; i < options.length; i++) {
                System.out.println((i + 1) + ". " + options[i]);
            }

            int answer = readAnswer();
            if (question.isCorrect(answer)) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Incorrect.");
            }
            questionNumber++;
        }

        showResult(name, score, roundQuestions.size());
        scanner.close();
    }

    private int readAnswer() {
        while (true) {
            System.out.print("Your answer (1-4): ");
            String input = scanner.nextLine().trim();
            try {
                int answer = Integer.parseInt(input);
                if (answer >= 1 && answer <= 4) {
                    return answer;
                }
            } catch (NumberFormatException ignored) {
                // Ask again when the input is not a number.
            }
            System.out.println("Please enter a number from 1 to 4.");
        }
    }

    private void showResult(String name, int score, int total) {
        double percentage = (score * 100.0) / total;

        System.out.println("\n================================");
        System.out.println("             RESULT             ");
        System.out.println("================================");
        System.out.println("Player: " + name);
        System.out.println("Score: " + score + " / " + total);
        System.out.printf("Percentage: %.1f%%%n", percentage);

        if (percentage >= 80) {
            System.out.println("Excellent work!");
        } else if (percentage >= 50) {
            System.out.println("Good effort! Keep practising.");
        } else {
            System.out.println("Keep learning. You can improve!");
        }
        System.out.println("================================");
    }
}
