package nl.wc.umpire_quiz.resource;

import nl.wc.umpire_quiz.model.ImportedQuestion;
import nl.wc.umpire_quiz.service.ImportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImportResourceTest {
    @Mock
    private ImportService importServiceMock;
    @InjectMocks
    private ImportResource sut;

    @Test
    void testImport() {
        doNothing().when(importServiceMock)
                   .importQuestions(anyList());

        sut.importQuestions(List.of(new ImportedQuestion()));

        verify(importServiceMock, times(1)).importQuestions(anyList());
    }
}