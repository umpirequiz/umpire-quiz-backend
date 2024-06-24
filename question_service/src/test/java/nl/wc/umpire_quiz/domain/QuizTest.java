package nl.wc.umpire_quiz.domain;

import nl.wc.umpire_quiz.model.Answer;
import nl.wc.umpire_quiz.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nl.wc.umpire_quiz.model.Difficulty.*;
import static org.assertj.core.api.Assertions.assertThat;

class QuizTest {
    private Quiz sut;

    @BeforeEach
    void setUp() {
        sut = new Quiz();
    }

    @Test
    void QuizHappyFlow() {
        Question q = new Question();
        Answer a = new Answer();

        assertThat(sut.getDifficulties()).containsExactlyInAnyOrderElementsOf(List.of(UMPIRE_2, UMPIRE_3, UMPIRE_4, UMPIRE_1));

        sut.setDifficulties(List.of(UMPIRE_1));
        assertThat(sut.getDifficulties()).containsExactlyInAnyOrderElementsOf(List.of(UMPIRE_1));

        sut.setQuizSize(55);
        assertThat(sut.getQuizSize()).isEqualTo(55);

        sut.setQuestions(List.of(q));
        assertThat(sut.getQuestions()).containsExactly(q);

        sut.setGivenAnswers(List.of(a));
        assertThat(sut.getGivenAnswers()).containsExactly(a);
    }

}