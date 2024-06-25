package nl.wc.umpire_quiz.model;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

class InternationalizedStringsTest {
    @Test
    void InternationalizedStringsRegularConstructor() {
        InternationalizedStrings sut = new InternationalizedStrings();

        sut.setNlNL("Vraag");
        sut.setEnUS("Question");

        assertThat(sut.getNlNL()).isEqualTo("Vraag");
        assertThat(sut.getEnUS()).isEqualTo("Question");
    }

    @Test
    void InternationalizedStringsStringConstructor() {
        InternationalizedStrings sut = new InternationalizedStrings("Vraag", "Question");

        assertThat(sut.getNlNL()).isEqualTo("Vraag");
        assertThat(sut.getEnUS()).isEqualTo("Question");
    }

    @Test
    void testHashCode() {
        InternationalizedStrings sut = new InternationalizedStrings("Vraag", "Question");

        int hashEnUS = Objects.hashCode(sut.getEnUS());
        int hashNlNL = Objects.hashCode(sut.getNlNL());

        assertThat(sut.hashCode()).isEqualTo(31 * hashEnUS + hashNlNL);
    }

    @Test
    void testEquals() {
        InternationalizedStrings is1 = new InternationalizedStrings("Vraag", "Question");
        InternationalizedStrings is2 = new InternationalizedStrings("Vraag", "Question");
        InternationalizedStrings is3 = new InternationalizedStrings("vraag", "Question");
        InternationalizedStrings is4 = new InternationalizedStrings("Vraag", "question");

        //noinspection AssertBetweenInconvertibleTypes
        assertThat(is1).isEqualTo(is2)
                       .isNotEqualTo(is3)
                       .isNotEqualTo(is4)
                       .isNotEqualTo(null)
                       .isNotEqualTo(new Answer());
    }
}