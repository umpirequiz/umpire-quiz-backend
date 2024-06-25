package nl.wc.umpire_quiz.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static nl.wc.umpire_quiz.model.Difficulty.*;
import static org.assertj.core.api.Assertions.assertThat;

class QuizValidationTest {
    @Test
    void quizValidationFromGeneration() {
        QuizGeneration qG = new QuizGeneration();
        qG.setQuizSize(42);

        QuizValidation qV = new QuizValidation(qG);

        assertThat(qV.getQuizSize()).isEqualTo(42);
        assertThat(qV.getDifficulties()).containsExactlyInAnyOrder(UMPIRE_1, UMPIRE_3, UMPIRE_2, UMPIRE_4);
    }

    @Test
    void quizValidationHappyFlow() {
        Question q = new Question();
        QuizValidation qV = new QuizValidation();
        qV.setQuizSize(42);
        qV.setDifficulties(List.of(UMPIRE_1));
        qV.setQuestions(List.of(q));

        assertThat(qV.getQuizSize()).isEqualTo(42);
        assertThat(qV.getDifficulties()).containsExactly(UMPIRE_1);
        assertThat(qV.getQuestions()).containsExactly(q);
    }
}