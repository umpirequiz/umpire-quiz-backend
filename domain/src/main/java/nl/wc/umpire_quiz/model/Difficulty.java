package nl.wc.umpire_quiz.model;

import java.util.Arrays;
import java.util.List;

public enum Difficulty {
    U1, U2, U3, U4;

    public static List<Difficulty> toDifficulties(String levels) {
        return Arrays.stream(levelsOrDefault(levels).split("")).map(Difficulty::from).toList();
    }

    private static String levelsOrDefault(String levels) {
        return levels != null && !levels.isBlank() ? levels : "1234";
    }

    private static Difficulty from(String s) {
        return switch (s) {
            case "1" -> U1;
            case "2" -> U2;
            case "3" -> U3;
            case "4" -> U4;
            default -> throw new IllegalArgumentException("Invalid difficulty");
        };
    }
}
