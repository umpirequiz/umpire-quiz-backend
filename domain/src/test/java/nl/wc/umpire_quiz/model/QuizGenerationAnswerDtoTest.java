package nl.wc.umpire_quiz.model;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static nl.wc.umpire_quiz.model.CountryCode.EN_US;
import static nl.wc.umpire_quiz.model.CountryCode.NL_NL;
import static org.assertj.core.api.Assertions.assertThat;

class QuizGenerationAnswerDtoTest {

    @Test
    void regularConstructorTest() {
        QuizGenerationAnswerDto a = new QuizGenerationAnswerDto();

        a.setId(42L);
        assertThat(a.getId()).isEqualTo(42L);

        a.setI18nValue(Map.of(NL_NL, "Ja", EN_US, "Yes"));
        assertThat(a.getI18nValue()).isEqualTo(Map.of(NL_NL, "Ja", EN_US, "Yes"));
    }

    @Test
    void AnswerConstructorTest() {
        Answer a = new Answer();
        a.setId(55);
        a.setCorrect(true);
        a.setI18nValue(Map.of(NL_NL, "Nee", EN_US, "No"));

        QuizGenerationAnswerDto aDto = new QuizGenerationAnswerDto(a);

        assertThat(aDto.getId()).isEqualTo(55);
        assertThat(aDto.getI18nValue()).isEqualTo(Map.of(NL_NL, "Nee", EN_US, "No"));
    }
}