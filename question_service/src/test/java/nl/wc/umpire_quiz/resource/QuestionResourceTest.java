package nl.wc.umpire_quiz.resource;

import jakarta.ws.rs.core.Response;
import nl.wc.umpire_quiz.dao.QuestionDao;
import nl.wc.umpire_quiz.model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionResourceTest {

    @Mock
    private QuestionDao questionDaoMock;

    @InjectMocks
    private QuestionResource sut;

    @Test
    void get() {
        int id = 42;
        sut.withId(id);
        Question q = new Question();
        q.setId(id);
        when(questionDaoMock.find(42L)).thenReturn(q);

        Response resp = sut.get();

        assertThat(resp.getEntity()).isEqualTo(q);
        assertThat(resp.getStatus()).isEqualTo(200);
    }
}