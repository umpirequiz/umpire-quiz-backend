package nl.wc.umpire_quiz.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import nl.wc.umpire_quiz.model.Answer;
import nl.wc.umpire_quiz.model.Difficulty;
import nl.wc.umpire_quiz.model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static nl.wc.umpire_quiz.model.Difficulty.UMPIRE_1;
import static nl.wc.umpire_quiz.model.Difficulty.UMPIRE_2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionDaoTest {
    @Mock
    TypedQuery<Question> queryMock;
    @Mock
    EntityManager emMock;
    @InjectMocks
    QuestionDao sut;

    @Test
    void save() {
        Question question = new Question();
        question.setId(1L);

        when(emMock.merge(any(Question.class))).thenReturn(question);

        assertThat(sut.save(question)).isEqualTo(question);

        verify(emMock, times(1)).merge(any(Question.class));
    }

    @Test
    void saveWithList() {
        List<Question> questions = List.of(new Question(), new Question(), new Question());

        when(emMock.merge(any(Question.class))).thenAnswer(i -> i.getArgument(0));

        sut.save(questions);

        verify(emMock, times(3)).merge(any(Question.class));
    }

    @Test
    void delete() {
        Question question = new Question();
        question.setId(1L);
        question.setEnabled(true);
        assertThat(question.isEnabled()).isTrue();

        when(emMock.merge(any(Question.class))).thenReturn(question);
        when(emMock.find(Question.class, 1L)).thenReturn(question);

        sut.delete(question);

        verify(emMock, times(1)).find(Question.class, 1L);
        verify(emMock, times(1)).merge(any(Question.class));

        assertThat(question.isEnabled()).isFalse();
    }

    @Test
    void update() {
        Question question = new Question();
        List<Answer> answers = List.of(new Answer(), new Answer());
        question.setId(1L);
        question.setAnswers(answers);

        when(emMock.merge(any(Question.class))).thenReturn(question);
        when(emMock.find(Question.class, 1L)).thenReturn(question);

        assertThat(sut.update(1L, question)).isEqualTo(question);

        verify(emMock, times(2)).merge(any(Question.class));
        verify(emMock, times(1)).find(Question.class, 1L);
    }

    @Test
    void find() {
        Question question = new Question();
        question.setId(42L);

        when(emMock.find(Question.class, 42L)).thenReturn(question);
        assertThat(sut.find(42)).isEqualTo(question);
        assertThat(sut.find(42L)).isEqualTo(question);

        verify(emMock, times(2)).find(Question.class, 42L);
    }

    @ParameterizedTest
    @CsvSource({"5,5", "10,10", "20,20", "30,30", "40,40", "50,50", "60,55"})
    void getQuizQuestions(int quizSize, int expected) {
        List<Difficulty> difficulties = List.of(UMPIRE_1, UMPIRE_2);
        List<Question> questions = createQuestionList();
        List<Question> questions1 = new ArrayList<>(questions);

        when(emMock.createQuery(anyString(), eq(Question.class))).thenReturn(queryMock);
        when(queryMock.setParameter("difficulties", difficulties)).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(questions);

        assertThat(sut.getQuizQuestions(quizSize, difficulties)).hasSize(expected);

        assertThat(questions1).hasSameSizeAs(questions)
                              .containsExactlyInAnyOrderElementsOf(questions)
                              .isNotEqualTo(questions);

        verify(emMock, times(1)).createQuery(anyString(), eq(Question.class));
        verify(queryMock, times(1)).setParameter("difficulties", difficulties);
        verify(queryMock, times(1)).getResultList();
    }

    private List<Question> createQuestionList() {
        List<Question> questions = new ArrayList<>();
        IntStream.range(0, 55)
                 .forEach(x -> questions.add(new Question()));
        return questions;
    }
}
