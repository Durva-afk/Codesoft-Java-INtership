package package1;

import java.util.*;

class Question {
    String questionText;
    String[] options;
    int correctAnswer; // Index of the correct answer (0-based)

    Question(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

public class QuizApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Question> questions = new ArrayList<>();
        int score = 0;
        Map<Question, Boolean> results = new LinkedHashMap<>();

        // Sample questions
        questions.add(new Question(
                "What is the largest mammal on Earth?",
                new String[]{"1. Elephant", "2. Blue Whale", "3. Shark", "4. Dinosaur"},
                2));
        questions.add(new Question(
                "Which planet is known as the Red Planet?",
                new String[]{"1. Earth", "2. Mars", "3. Jupiter", "4. Venus"},
                1));
        questions.add(new Question(
                "Which gas do plants absorb during photosynthesis?",
                new String[]{"1. CO2", "2. Nitrogen", "3. Oxygen", "4. Hydrogen"},
                2));

        System.out.println("Welcome to the Quiz! You have 10 seconds to answer each question.\n");

        for (Question question : questions) {
            System.out.println(question.questionText);
            for (String option : question.options) {
                System.out.println(option);
            }

            // Timer for 10 seconds
            System.out.print("Enter your answer (1-4): ");
            long startTime = System.currentTimeMillis();
            boolean answered = false;
            int userAnswer = -1;

            while ((System.currentTimeMillis() - startTime) < 10000) {
                if (scanner.hasNextInt()) {
                    userAnswer = scanner.nextInt();
                    answered = true;
                    break;
                }
            }

            if (!answered) {
                System.out.println("\nTime's up! No answer recorded.");
                results.put(question, false);
            } else if (userAnswer - 1 == question.correctAnswer) {
                System.out.println("Correct!");
                score++;
                results.put(question, true);
            } else {
                System.out.println("Wrong!");
                results.put(question, false);
            }

            System.out.println();
        }

        // Display Results
        System.out.println("\n--- Quiz Results ---");
        System.out.println("Your Score: " + score + "/" + questions.size());
        for (Map.Entry<Question, Boolean> entry : results.entrySet()) {
            Question question = entry.getKey();
            boolean correct = entry.getValue();
            System.out.println("\nQuestion: " + question.questionText);
            System.out.println("Your Answer: " + (correct ? "Correct" : "Incorrect"));
            System.out.println("Correct Answer: " + question.options[question.correctAnswer]);
        }

        scanner.close();
    }
}
