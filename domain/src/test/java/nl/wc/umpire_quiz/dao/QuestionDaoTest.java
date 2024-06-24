package nl.wc.umpire_quiz.dao;

import jakarta.persistence.EntityManager;
import nl.wc.umpire_quiz.model.Answer;
import nl.wc.umpire_quiz.model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuestionDaoTest {
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

        assertThat(sut.update(question)).isEqualTo(question);

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
}