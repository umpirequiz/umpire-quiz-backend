package nl.wc.umpire_quiz.resource;

import jakarta.ws.rs.core.Response;
import nl.wc.umpire_quiz.dao.QuestionDao;
import nl.wc.umpire_quiz.model.InternationalizedStrings;
import nl.wc.umpire_quiz.model.Question;
import org.jetbrains.annotations.NotNull;
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
        when(questionDaoMock.findBy(term, false)).thenReturn(questions);

        Response resp = sut.getAll(term, false);

        assertThat(resp.getEntity()).isEqualTo(questions);
        assertThat(resp.getStatus()).isEqualTo(200);
    }

    @Test
    void getAllWithOrWithoutTermAndWithOrWithoutAllEnabled() {
        String b = "batter";
        String s = "slagman";
        String empty = "";
        String blank = " ";
        String aTerm = "aTerm";
        boolean all = false;
        boolean enabledOnly = true;

        Question.QuestionBuilder q = Question.builder();
        Question q1 = q.id(1).i18nValue(i18n(s, b)).enabled(all).build();
        Question q2 = q.id(2).i18nValue(i18n(s, b)).enabled(enabledOnly).build();
        Question q3 = q.id(3).i18nValue(i18n(aTerm, aTerm)).enabled(enabledOnly).build();

        List<Question> enabledQuestions = List.of(q1, q3);
        List<Question> allQuestions = List.of(q1, q2, q3);
        List<Question> enabledQuestionsFiltered = List.of(q3);

        when(questionDaoMock.findBy(null, enabledOnly)).thenReturn(enabledQuestions);
        when(questionDaoMock.findBy(empty, enabledOnly)).thenReturn(enabledQuestions);
        when(questionDaoMock.findBy(blank, enabledOnly)).thenReturn(enabledQuestions);
        when(questionDaoMock.findBy(aTerm, enabledOnly)).thenReturn(enabledQuestionsFiltered);
        when(questionDaoMock.findBy(null, all)).thenReturn(allQuestions);
        when(questionDaoMock.findBy(empty, all)).thenReturn(allQuestions);
        when(questionDaoMock.findBy(blank, all)).thenReturn(allQuestions);
        when(questionDaoMock.findBy(aTerm, all)).thenReturn(allQuestions);

        Response respNull = sut.getAll(null, enabledOnly);
        Response respEmpty = sut.getAll(empty, enabledOnly);
        Response respBlank = sut.getAll(blank, enabledOnly);
        Response respTerm = sut.getAll(aTerm, enabledOnly);

        Response respAll1 = sut.getAll(null, all);
        Response respAll4 = sut.getAll(empty, all);
        Response respAll3 = sut.getAll(blank, all);
        Response respAll2 = sut.getAll(aTerm, all);

        assertThat(respNull.getEntity()).isEqualTo(enabledQuestions);
        assertThat(respNull.getStatus()).isEqualTo(200);
        assertThat(respEmpty.getEntity()).isEqualTo(enabledQuestions);
        assertThat(respEmpty.getStatus()).isEqualTo(200);
        assertThat(respBlank.getEntity()).isEqualTo(enabledQuestions);
        assertThat(respBlank.getStatus()).isEqualTo(200);
        assertThat(respTerm.getEntity()).isEqualTo(enabledQuestionsFiltered);
        assertThat(respTerm.getStatus()).isEqualTo(200);

        assertThat(respAll1.getEntity()).isEqualTo(allQuestions);
        assertThat(respAll1.getStatus()).isEqualTo(200);
        assertThat(respAll2.getEntity()).isEqualTo(allQuestions);
        assertThat(respAll2.getStatus()).isEqualTo(200);
        assertThat(respAll3.getEntity()).isEqualTo(allQuestions);
        assertThat(respAll3.getStatus()).isEqualTo(200);
        assertThat(respAll4.getEntity()).isEqualTo(allQuestions);
        assertThat(respAll4.getStatus()).isEqualTo(200);
    }

    private static @NotNull InternationalizedStrings i18n(String nl, String en) {
        return new InternationalizedStrings(nl, en);
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
