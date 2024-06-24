package nl.wc.umpire_quiz.resource;

import nl.wc.umpire_quiz.domain.Quiz;
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
        Quiz q = new Quiz();

        when(quizServiceMock.generateQuiz(any(Quiz.class))).thenReturn(q);

        assertThat(sut.generateQuiz(q)).isEqualTo(q);

        verify(quizServiceMock, times(1)).generateQuiz(any(Quiz.class));
    }
}