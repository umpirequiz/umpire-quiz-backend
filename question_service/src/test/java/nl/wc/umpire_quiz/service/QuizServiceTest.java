package nl.wc.umpire_quiz.service;

import nl.wc.umpire_quiz.dao.QuestionDao;
import nl.wc.umpire_quiz.domain.Quiz;
import nl.wc.umpire_quiz.model.Question;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {
    @Mock
    private QuestionDao questionDaoMock;

    @InjectMocks
    private QuizService sut;

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15, 20})
    void generateQuiz(int amount) {
        List<Question> questions = createQuestionList(amount);
        Quiz quiz = new Quiz();
        quiz.setQuizSize(20);

        when(questionDaoMock.getQuizQuestions(anyInt(), anyList())).thenReturn(questions);

        quiz = sut.generateQuiz(quiz);

        assertThat(quiz.getQuizSize()).isEqualTo(amount);
        assertThat(quiz.getQuestions()).containsExactlyInAnyOrderElementsOf(questions);

        verify(questionDaoMock, times(1)).getQuizQuestions(anyInt(), anyList());
    }

    private List<Question> createQuestionList(int length) {
        List<Question> questions = new ArrayList<>();
        IntStream.range(0, length)
                 .forEach(x -> questions.add(new Question()));
        return questions;
    }
}