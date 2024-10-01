package nl.wc.umpire_quiz.resource;

import jakarta.ws.rs.core.Response;
import nl.wc.umpire_quiz.dao.QuestionDao;
import nl.wc.umpire_quiz.model.InternationalizedStrings;
import nl.wc.umpire_quiz.model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionsResourceTest {

    @Mock
    private QuestionDao questionDaoMock;

    @InjectMocks
    private QuestionsResource sut;

    @Test
    void getByTerm() {
        String term = "batter";
        Question q = new Question();
        q.setI18nValue(new InternationalizedStrings("batter", "slagman"));
        List<Question> questions = List.of(q);
        when(questionDaoMock.findBy(term)).thenReturn(questions);

        Response resp = sut.getAll(term);

        assertThat(resp.getEntity()).isEqualTo(questions);
        assertThat(resp.getStatus()).isEqualTo(200);
    }

    @Test
    void getAll() {
        Question q = new Question();
        q.setId(42);
        q.setI18nValue(new InternationalizedStrings("batter", "slagman"));
        List<Question> questions = List.of(q, q.copy(), q.copy());

        when(questionDaoMock.findBy(null)).thenReturn(questions);
        when(questionDaoMock.findBy("")).thenReturn(questions);
        when(questionDaoMock.findBy("   ")).thenReturn(questions);

        Response respNull = sut.getAll(null);
        Response respEmpty = sut.getAll("");
        Response respBlank = sut.getAll("   ");

        assertThat(respNull.getEntity()).isEqualTo(questions);
        assertThat(respNull.getStatus()).isEqualTo(200);
        assertThat(respEmpty.getEntity()).isEqualTo(questions);
        assertThat(respEmpty.getStatus()).isEqualTo(200);
        assertThat(respBlank.getEntity()).isEqualTo(questions);
        assertThat(respBlank.getStatus()).isEqualTo(200);
    }

    @Test
    void get() {
        int id = 42;
        Question q = new Question();
        q.setId(id);
        when(questionDaoMock.find(42L)).thenReturn(q);

        Response resp = sut.get(id);

        assertThat(resp.getEntity()).isEqualTo(q);
        assertThat(resp.getStatus()).isEqualTo(200);
    }

    @Test
    void add() {
    }

    @Test
    void remove() {
    }

    @Test
    void update() {
    }
}
