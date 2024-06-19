package nl.wc.umpire_quiz.model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static nl.wc.umpire_quiz.model.CountryCode.EN_US;
import static nl.wc.umpire_quiz.model.CountryCode.NL_NL;
import static nl.wc.umpire_quiz.model.Difficulty.UMPIRE_1;
import static org.assertj.core.api.Assertions.assertThat;

class QuestionTest {

    @Test
    void testQuestion() {
        Map<CountryCode, String> ruling1 = Map.of(NL_NL, "Regul", EN_US, "Ruling");
        Map<CountryCode, String> ruling2 = Map.of(NL_NL, "Regel", EN_US, "Ruling");
        Map<CountryCode, String> value = Map.of(NL_NL, "Vraag", EN_US, "Question");
        GameState gameState = new GameState();
        Answer a1 = new Answer();
        Answer a2 = new Answer();
        List<Answer> answers = List.of(a1, a2);
        Question q = new Question();

        q.setI18nValue(value);
        assertThat(q.getI18nValue()).isEqualTo(value);

        q.setId(42);
        assertThat(q.getId()).isEqualTo(42);

        q.setAnswers(answers);
        assertThat(q.getAnswers()).isEqualTo(answers);
        assertThat(a1.getQuestion()).isEqualTo(q);
        assertThat(a2.getQuestion()).isEqualTo(q);

        q.setDifficulty(UMPIRE_1);
        assertThat(q.getDifficulty()).isEqualTo(UMPIRE_1);

        q.setEnabled(false);
        assertThat(q.isEnabled()).isFalse();

        q.setI18nRuling(ruling1);
        assertThat(q.getI18nRuling()).isEqualTo(ruling1);
        assertThat(q.getI18nRuling()).isNotEqualTo(ruling2);

        q.setGameState(gameState);
        assertThat(q.getGameState()).isEqualTo(gameState);

        Question q2 = q.copy();
        assertThat(q2.isEnabled()).isTrue();
        q2.setI18nRuling(ruling2);
        assertThat(q2.getI18nRuling()).isEqualTo(ruling2);
        assertThat(q2.getI18nRuling()).isNotEqualTo(ruling1);
        assertThat(q.getI18nRuling()).isEqualTo(ruling1);
        assertThat(q.getI18nRuling()).isNotEqualTo(ruling2);
    }

}