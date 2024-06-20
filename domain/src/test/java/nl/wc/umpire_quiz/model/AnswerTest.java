package nl.wc.umpire_quiz.model;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static nl.wc.umpire_quiz.model.CountryCode.EN_US;
import static nl.wc.umpire_quiz.model.CountryCode.NL_NL;
import static org.assertj.core.api.Assertions.assertThat;

class AnswerTest {

    @Test
    void testAnswer() {
        Map<CountryCode, String> value = Map.of(NL_NL, "Ja", EN_US, "Nee");
        Answer a = new Answer();

        a.setId(42);
        assertThat(a.getId()).isEqualTo(42);

        a.setCorrect(true);
        assertThat(a.isCorrect()).isTrue();

        a.setCorrect(false);
        assertThat(a.isCorrect()).isFalse();

        a.setI18nValue(value);
        assertThat(a.getI18nValue()).isEqualTo(value);
    }

    @Test
    void testAnswerStringConstructor() {
        Answer a = new Answer("Answer");

        assertThat(a.getI18nValue()).containsExactlyEntriesOf(Map.of(NL_NL, "Answer", EN_US, "Answer"));
    }
}