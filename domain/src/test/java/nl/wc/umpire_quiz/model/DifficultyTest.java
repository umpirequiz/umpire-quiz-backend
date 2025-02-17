package nl.wc.umpire_quiz.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static nl.wc.umpire_quiz.model.Difficulty.UMPIRE_2;
import static nl.wc.umpire_quiz.model.Difficulty.UMPIRE_4;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class DifficultyTest {

    @Test
    void allToDifficulties() {
        List<Difficulty> difficulties = Difficulty.toDifficulties("1234");
        assertThat(difficulties).contains(Difficulty.values());
    }

    @Test
    void someToDifficulties() {
        List<Difficulty> difficulties = Difficulty.toDifficulties("24");
        assertThat(difficulties).contains(UMPIRE_2, UMPIRE_4);
    }

    @Test
    void emptyOrBlankToDifficulties() {
        List<Difficulty> difficulties = Difficulty.toDifficulties("");
        assertThat(difficulties).contains(Difficulty.values());

        difficulties = Difficulty.toDifficulties("       ");
        assertThat(difficulties).contains(Difficulty.values());
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
        List<Difficulty> difficulties = Difficulty.toDifficulties(null);
        assertThat(difficulties).contains(Difficulty.values());
    }
}