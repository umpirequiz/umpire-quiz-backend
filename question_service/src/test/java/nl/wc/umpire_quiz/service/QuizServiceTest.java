package nl.wc.umpire_quiz.service;

import nl.wc.umpire_quiz.dao.QuestionDao;
import nl.wc.umpire_quiz.model.Question;
import nl.wc.umpire_quiz.model.QuizGeneration;
import nl.wc.umpire_quiz.model.QuizGenerationQuestionDto;
import nl.wc.umpire_quiz.model.QuizValidation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static nl.wc.umpire_quiz.model.Difficulty.*;
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
    @CsvSource({"0,5", "1,1", "2,2"})
    void quizSizeOrDefault(int size, int expected) {
        assertThat(sut.quizSizeOrDefault(size)).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 15, 20})
    void generateQuiz(int amount) {
        List<QuizGenerationQuestionDto> questions = createQuestionList(amount);
        QuizGeneration quizGeneration = new QuizGeneration();
        quizGeneration.setQuizSize(20);

        when(questionDaoMock.getQuizQuestions(anyInt(), anyList())).thenReturn(questions);

        quizGeneration = sut.generateQuiz(quizGeneration.getQuizSize(), quizGeneration.getDifficulties());

        assertThat(quizGeneration.getQuizSize()).isEqualTo(amount);
        assertThat(quizGeneration.getQuestions()).containsExactlyInAnyOrderElementsOf(questions);

        verify(questionDaoMock, times(1)).getQuizQuestions(anyInt(), anyList());
    }

    @Test
    void validateQuiz() {
        List<QuizGenerationQuestionDto> questions = createQuestionList(10);
        QuizGeneration quizGeneration = new QuizGeneration();
        quizGeneration.setQuestions(questions);
        quizGeneration.setQuizSize(10);

        Question q0 = new Question();
        Question q1 = new Question();
        Question q2 = new Question();
        Question q3 = new Question();
        Question q4 = new Question();
        Question q5 = new Question();
        Question q6 = new Question();
        Question q7 = new Question();
        Question q8 = new Question();
        Question q9 = new Question();

        when(questionDaoMock.find(0L)).thenReturn(q0);
        when(questionDaoMock.find(1L)).thenReturn(q1);
        when(questionDaoMock.find(2L)).thenReturn(q2);
        when(questionDaoMock.find(3L)).thenReturn(q3);
        when(questionDaoMock.find(4L)).thenReturn(q4);
        when(questionDaoMock.find(5L)).thenReturn(q5);
        when(questionDaoMock.find(6L)).thenReturn(q6);
        when(questionDaoMock.find(7L)).thenReturn(q7);
        when(questionDaoMock.find(8L)).thenReturn(q8);
        when(questionDaoMock.find(9L)).thenReturn(q9);

        QuizValidation qV = sut.validateQuiz(quizGeneration);

        assertThat(qV.getDifficulties()).containsExactlyInAnyOrder(UMPIRE_1, UMPIRE_2, UMPIRE_3, UMPIRE_4);
        assertThat(qV.getQuizSize()).isEqualTo(10);
        assertThat(qV.getQuestions()).containsExactlyInAnyOrder(q0, q1, q2, q3, q4, q5, q6, q7, q8, q9);

        verify(questionDaoMock, times(10)).find(anyLong());
    }

    private List<QuizGenerationQuestionDto> createQuestionList(int length) {
        List<QuizGenerationQuestionDto> questions = new ArrayList<>();
        IntStream.range(0, length)
                 .forEach(x -> questions.add(new QuizGenerationQuestionDto(x)));
        return questions;
    }
}
