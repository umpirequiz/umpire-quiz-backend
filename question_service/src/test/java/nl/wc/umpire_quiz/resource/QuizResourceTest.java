package nl.wc.umpire_quiz.resource;

import nl.wc.umpire_quiz.domain.QuizGeneration;
import nl.wc.umpire_quiz.domain.QuizValidation;
import nl.wc.umpire_quiz.service.QuizService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuizResourceTest {
    @Mock
    private QuizService quizServiceMock;
    @InjectMocks
    private QuizResource sut;

    @Test
    void generateQuiz() {
        QuizGeneration q = new QuizGeneration();

        when(quizServiceMock.generateQuiz(any(QuizGeneration.class))).thenReturn(q);

        assertThat(sut.generateQuiz(q)).isEqualTo(q);

        verify(quizServiceMock, times(1)).generateQuiz(any(QuizGeneration.class));
    }

    @Test
    void validateQuiz() {
        QuizGeneration qG = new QuizGeneration();
        QuizValidation qV = new QuizValidation();

        when(quizServiceMock.validateQuiz(any(QuizGeneration.class))).thenReturn(qV);

        assertThat(sut.validateQuiz(qG)).isEqualTo(qV);

        verify(quizServiceMock, times(1)).validateQuiz(any(QuizGeneration.class));
    }
}