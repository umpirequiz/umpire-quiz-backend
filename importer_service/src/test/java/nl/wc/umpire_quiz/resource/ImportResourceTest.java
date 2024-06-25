package nl.wc.umpire_quiz.resource;

import jakarta.ws.rs.core.Response;
import nl.wc.umpire_quiz.model.ImportQuestionsCounterDto;
import nl.wc.umpire_quiz.model.ImportedQuestion;
import nl.wc.umpire_quiz.service.ImportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
        ImportQuestionsCounterDto iDto = new ImportQuestionsCounterDto(List.of());
        when(importServiceMock.importQuestions(anyList())).thenReturn(iDto);

        Response resp = sut.importQuestions(List.of(new ImportedQuestion()));

        assertThat(resp.getStatus()).isEqualTo(201);
        assertThat(resp.getEntity()).isEqualTo(iDto);

        verify(importServiceMock, times(1)).importQuestions(anyList());
    }
}