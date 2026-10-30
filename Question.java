public class Question {
    private final String text;
    private final String[] options;
    private final int correctOption;

    public Question(String text, String[] options, int correctOption) {
        if (options == null || options.length != 4) {
            throw new IllegalArgumentException("Each question must have exactly 4 options.");
        }
        if (correctOption < 1 || correctOption > 4) {
            throw new IllegalArgumentException("Correct option must be between 1 and 4.");
        }
        this.text = text;
        this.options = options.clone();
        this.correctOption = correctOption;
    }

    public String getText() {
        return text;
    }

    public String[] getOptions() {
        return options.clone();
    }

    public boolean isCorrect(int answer) {
        return answer == correctOption;
    }
}
