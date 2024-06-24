package nl.wc.umpire_quiz.model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static nl.wc.umpire_quiz.model.CountryCode.EN_US;
import static nl.wc.umpire_quiz.model.CountryCode.NL_NL;
import static org.assertj.core.api.Assertions.assertThat;

class QuizGenerationQuestionDtoTest {

    @Test
    void regularConstructorTest() {
        QuizGenerationQuestionDto q = new QuizGenerationQuestionDto();
        assertThat(q).isNotNull();
    }

    @Test
    void idConstructorTest() {
        QuizGenerationQuestionDto q = new QuizGenerationQuestionDto(55L);
        assertThat(q.getId()).isEqualTo(55L);
    }

    @Test
    void questionConstructorTest() {
        GameState gs = new GameState();
        gs.setOuts(1);
        gs.setBalls(2);
        gs.setStrikes(3);
        gs.setBatterRunner(true);
        gs.setRunnerBase1(false);
        gs.setRunnerBase2(false);
        gs.setRunnerBase3(false);

        Answer a1 = new Answer();
        a1.setId(42);
        a1.setCorrect(false);
        a1.setI18nValue(Map.of(NL_NL, "Ja", EN_US, "Yes"));

        Answer a2 = new Answer();
        a2.setId(55);
        a2.setCorrect(true);
        a2.setI18nValue(Map.of(NL_NL, "Nee", EN_US, "No"));


        Question q = new Question();
        q.setId(21);
        q.setI18nValue(Map.of(NL_NL, "Vraag", EN_US, "Question"));
        q.setGameState(gs);
        q.setAnswers(List.of(a1, a2));

        QuizGenerationQuestionDto qDto = new QuizGenerationQuestionDto(q);

        assertThat(qDto.getId()).isEqualTo(21);
        assertThat(qDto.getGameState()).isEqualTo(gs);
        assertThat(qDto.getI18nValue()).containsExactlyInAnyOrderEntriesOf(Map.of(NL_NL, "Vraag", EN_US, "Question"));

        List<QuizGenerationAnswerDto> answers = qDto.getAnswers();

        assertThat(answers.stream()
                          .map(QuizGenerationAnswerDto::getId)).containsExactlyInAnyOrder(55L, 42L);
        assertThat(answers.stream()
                          .map(QuizGenerationAnswerDto::getI18nValue)).containsExactlyInAnyOrder(Map.of(NL_NL, "Nee", EN_US, "No"), Map.of(NL_NL, "Ja", EN_US, "Yes"));
    }
}