package nl.wc.umpire_quiz.service;

import nl.wc.umpire_quiz.dao.QuestionDao;
import nl.wc.umpire_quiz.model.ImportedQuestion;
import nl.wc.umpire_quiz.model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImportServiceTest {
    @Mock
    private QuestionDao questionDaoMock;
    @InjectMocks
    private ImportService sut;
    @Captor
    private ArgumentCaptor<List<Question>> questionCaptor;

    @Test
    void importQuestion() {
        doNothing().when(questionDaoMock)
                   .save(questionCaptor.capture());

        List<ImportedQuestion> q = List.of(
                new ImportedQuestion(true, true, true),
                new ImportedQuestion(true, true, false),
                new ImportedQuestion(true, false, true),
                new ImportedQuestion(false, true, true),
                new ImportedQuestion(true, false, false),
                new ImportedQuestion(false, true, false),
                new ImportedQuestion(false, false, true),
                new ImportedQuestion(false, false, false)
        );

        assertThat(sut.importQuestions(q)).isNotNull();

        verify(questionDaoMock, times(1)).save(questionCaptor.capture());

        List<Question> capturedQuestions = questionCaptor.getValue();

        assertThat(capturedQuestions).hasSize(1);

    }

}