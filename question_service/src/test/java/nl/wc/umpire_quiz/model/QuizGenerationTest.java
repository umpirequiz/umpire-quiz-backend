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

        assertThat(sut.getDifficulties()).containsExactlyInAnyOrderElementsOf(List.of(U2, U3, U4, U1));

        sut.setDifficulties(List.of(U1));
        assertThat(sut.getDifficulties()).containsExactlyInAnyOrderElementsOf(List.of(U1));

        sut.setQuizSize(55);
        assertThat(sut.getQuizSize()).isEqualTo(55);

        sut.setQuestions(List.of(q));
        assertThat(sut.getQuestions()).containsExactly(q);
    }

    @Test
    void intAndListConstructor() {
        sut = new QuizGeneration(15, List.of(U2));

        assertThat(sut.getQuizSize()).isEqualTo(15);
        assertThat(sut.getDifficulties()).containsExactly(U2);
    }

    @Test
    void setDifficulties() {
        sut.setDifficulties(List.of());
        assertThat(sut.getDifficulties()).containsExactlyInAnyOrderElementsOf(List.of(U2, U3, U4, U1));

        sut.setDifficulties(List.of(U1));
        assertThat(sut.getDifficulties()).containsExactlyInAnyOrderElementsOf(List.of(U1));
    }
}