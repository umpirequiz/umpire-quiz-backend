package nl.wc.umpire_quiz.model;

import org.junit.jupiter.api.Test;

import java.util.List;

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
        a1.setI18nValue(new InternationalizedStrings("Ja", "Yes"));

        Answer a2 = new Answer();
        a2.setId(55);
        a2.setCorrect(true);
        a2.setI18nValue(new InternationalizedStrings("Nee", "No"));


        Question q = new Question();
        q.setId(21);
        q.setI18nValue(new InternationalizedStrings("Vraag", "Question"));
        q.setGameState(gs);
        q.setAnswers(List.of(a1, a2));

        QuizGenerationQuestionDto qDto = new QuizGenerationQuestionDto(q);

        assertThat(qDto.getId()).isEqualTo(21);
        assertThat(qDto.getGameState()).isEqualTo(gs);
        assertThat(qDto.getI18nValue()).isEqualTo(new InternationalizedStrings("Vraag", "Question"));

        List<QuizGenerationAnswerDto> answers = qDto.getAnswers();

        assertThat(answers.stream()
                          .map(QuizGenerationAnswerDto::getId)).containsExactlyInAnyOrder(55L, 42L);
        assertThat(answers.stream()
                          .map(QuizGenerationAnswerDto::getI18nValue)).containsExactlyInAnyOrder(new InternationalizedStrings("Nee", "No"), new InternationalizedStrings("Ja", "Yes"));
    }
}