package nl.wc.umpire_quiz.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class DifficultyTest {

    @Test
    void allToDifficulties() {
        String levels = "1234";

        List<Difficulty> difficulties = Difficulty.toDifficulties(levels);

        assertThat(Difficulty.UMPIRE_1).isIn(difficulties);
        assertThat(Difficulty.UMPIRE_2).isIn(difficulties);
        assertThat(Difficulty.UMPIRE_3).isIn(difficulties);
        assertThat(Difficulty.UMPIRE_4).isIn(difficulties);
    }

    @Test
    void someToDifficulties() {
        String levels = "24";

        List<Difficulty> difficulties = Difficulty.toDifficulties(levels);

        assertThat(Difficulty.UMPIRE_2).isIn(difficulties);
        assertThat(Difficulty.UMPIRE_4).isIn(difficulties);
    }

    @Test
    void noneToDifficulties() {
        String levels = "";

        assertThatThrownBy(() -> Difficulty.toDifficulties(levels))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid difficulty");
    }

    @Test
    void unknownToDifficulties() {
        String levels = "ABC567";

        assertThatThrownBy(() -> Difficulty.toDifficulties(levels))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid difficulty");
    }

    @Test
    void nullToDifficulties() {
        String levels = null;

        assertThatThrownBy(() -> Difficulty.toDifficulties(levels))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("levels is marked non-null but is null");
    }
}