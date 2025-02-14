package nl.wc.umpire_quiz.resource;

import jakarta.ws.rs.core.Response;
import nl.wc.umpire_quiz.model.QuizGeneration;
import nl.wc.umpire_quiz.model.QuizValidation;
import nl.wc.umpire_quiz.service.QuizService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuizResourceTest {
    @Mock
    private QuizService quizServiceMock;
    @InjectMocks
    private QuizResource sut;

    @Test
    void generateQuiz() {
        QuizGeneration q = new QuizGeneration();

        when(quizServiceMock.generateQuiz(anyInt(), anyList())).thenReturn(q);

        Response resp = sut.generateQuiz(10, "1");

        assertThat(resp.getEntity()).isEqualTo(q);
        assertThat(resp.getStatus()).isEqualTo(200);

        verify(quizServiceMock, times(1)).generateQuiz(anyInt(), anyList());
    }

    @Test
    void validateQuiz() {
        QuizGeneration qG = new QuizGeneration();
        QuizValidation qV = new QuizValidation();

        when(quizServiceMock.validateQuiz(any(QuizGeneration.class))).thenReturn(qV);

        Response resp = sut.validateQuiz(qG);

        assertThat(resp.getEntity()).isEqualTo(qV);
        assertThat(resp.getStatus()).isEqualTo(201);

        verify(quizServiceMock, times(1)).validateQuiz(any(QuizGeneration.class));
    }
}