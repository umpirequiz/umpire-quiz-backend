package nl.wc.umpire_quiz.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnswerTest {

    @Test
    void testAnswer() {
        InternationalizedStrings value = new InternationalizedStrings("Ja", "Nee");
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

        assertThat(a.getI18nValue()
                    .getNlNL()).isEqualTo("Answer");
        assertThat(a.getI18nValue()
                    .getEnUS()).isEqualTo("Answer");
    }
}