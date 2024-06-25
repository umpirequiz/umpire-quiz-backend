package nl.wc.umpire_quiz.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QuizGenerationAnswerDtoTest {

    @Test
    void regularConstructorTest() {
        QuizGenerationAnswerDto a = new QuizGenerationAnswerDto();

        a.setId(42L);
        assertThat(a.getId()).isEqualTo(42L);

        a.setI18nValue(new InternationalizedStrings("Ja", "Yes"));

        assertThat(a.getI18nValue()
                    .getNlNL()).isEqualTo("Ja");
        assertThat(a.getI18nValue()
                    .getEnUS()).isEqualTo("Yes");
    }

    @Test
    void AnswerConstructorTest() {
        Answer a = new Answer();
        a.setId(55);
        a.setCorrect(true);
        a.setI18nValue(new InternationalizedStrings("Nee", "No"));

        QuizGenerationAnswerDto aDto = new QuizGenerationAnswerDto(a);

        assertThat(aDto.getId()).isEqualTo(55);

        assertThat(aDto.getI18nValue()
                       .getNlNL()).isEqualTo("Nee");
        assertThat(aDto.getI18nValue()
                       .getEnUS()).isEqualTo("No");
    }
}