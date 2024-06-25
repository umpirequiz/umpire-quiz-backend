package nl.wc.umpire_quiz.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nl.wc.umpire_quiz.model.Difficulty.*;
import static org.assertj.core.api.Assertions.assertThat;

class QuizGenerationTest {
    private QuizGeneration sut;

    @BeforeEach
    void setUp() {
        sut = new QuizGeneration();
    }

    @Test
    void QuizHappyFlow() {
        QuizGenerationQuestionDto q = new QuizGenerationQuestionDto();

        assertThat(sut.getDifficulties()).containsExactlyInAnyOrderElementsOf(List.of(UMPIRE_2, UMPIRE_3, UMPIRE_4, UMPIRE_1));

        sut.setDifficulties(List.of(UMPIRE_1));
        assertThat(sut.getDifficulties()).containsExactlyInAnyOrderElementsOf(List.of(UMPIRE_1));

        sut.setQuizSize(55);
        assertThat(sut.getQuizSize()).isEqualTo(55);

        sut.setQuestions(List.of(q));
        assertThat(sut.getQuestions()).containsExactly(q);
    }

    @Test
    void intAndListConstructor() {
        QuizGeneration sut = new QuizGeneration(15, List.of(UMPIRE_2));

        assertThat(sut.getQuizSize()).isEqualTo(15);
        assertThat(sut.getDifficulties()).containsExactly(UMPIRE_2);
    }

    @Test
    void setDifficulties() {
        sut.setDifficulties(List.of());
        assertThat(sut.getDifficulties()).containsExactlyInAnyOrderElementsOf(List.of(UMPIRE_2, UMPIRE_3, UMPIRE_4, UMPIRE_1));

        sut.setDifficulties(List.of(UMPIRE_1));
        assertThat(sut.getDifficulties()).containsExactlyInAnyOrderElementsOf(List.of(UMPIRE_1));
    }
}