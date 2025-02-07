package nl.wc.umpire_quiz.model;

import lombok.NonNull;

import java.util.Arrays;
import java.util.List;

public enum Difficulty {
    UMPIRE_1,
    UMPIRE_2,
    UMPIRE_3,
    UMPIRE_4;

    public static List<Difficulty> toDifficulties(@NonNull String levels) {
        return Arrays.stream(levels.split(""))
                .map(Difficulty::from)
                .toList();
    }

    private static Difficulty from(String s) {
        return switch (s) {
            case "1" -> UMPIRE_1;
            case "2" -> UMPIRE_2;
            case "3" -> UMPIRE_3;
            case "4" -> UMPIRE_4;
            default -> throw new IllegalArgumentException("Invalid difficulty");
        };
    }
    }
