package nl.wc.umpire_quiz.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static nl.wc.umpire_quiz.model.Difficulty.UMPIRE_1;
import static org.assertj.core.api.Assertions.assertThat;

class QuestionTest {

    @Test
    void testQuestion() {
        InternationalizedStrings ruling1 = new InternationalizedStrings("Regul", "Ruling");
        InternationalizedStrings ruling2 = new InternationalizedStrings("Regel", "Ruling");
        InternationalizedStrings value = new InternationalizedStrings("Vraag", "Question");
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
        assertThat(q2.getI18nRuling()).isEqualTo(ruling1);
        assertThat(q2.getI18nRuling()).isNotEqualTo(ruling2);
        q2.setI18nRuling(ruling2);
        assertThat(q2.getI18nRuling()).isEqualTo(ruling2);
        assertThat(q2.getI18nRuling()).isNotEqualTo(ruling1);
        assertThat(q.getI18nRuling()).isEqualTo(ruling1);
        assertThat(q.getI18nRuling()).isNotEqualTo(ruling2);

        assertThat(q2.getI18nValue()).isEqualTo(value);

        assertThat(q2.getId()).isZero();

        assertThat(q2.getAnswers()).isEqualTo(answers);
        assertThat(a1.getQuestion()).isEqualTo(q2);
        assertThat(a2.getQuestion()).isEqualTo(q2);

        assertThat(q2.getDifficulty()).isEqualTo(UMPIRE_1);

        assertThat(q2.getGameState()).isEqualTo(gameState);
    }
}