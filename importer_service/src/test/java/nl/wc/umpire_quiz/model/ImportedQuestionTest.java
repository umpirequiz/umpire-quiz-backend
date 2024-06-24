package nl.wc.umpire_quiz.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static nl.wc.umpire_quiz.model.CountryCode.EN_US;
import static nl.wc.umpire_quiz.model.CountryCode.NL_NL;
import static nl.wc.umpire_quiz.model.Difficulty.*;
import static org.assertj.core.api.Assertions.assertThat;

class ImportedQuestionTest {
    private ImportedQuestion sut;

    @BeforeEach
    void setUp() {
        sut = new ImportedQuestion();
        sut.setBR(true);
        sut.setcBalls(1);
        sut.setcOuts(1);
        sut.setcStrikes(1);
        sut.setfBaseball(false);
        sut.setfRegular(false);
        sut.setfSeniors(true);
        sut.setsQuestion("Johannes");
        sut.setR1(true);
        sut.setR2(false);
        sut.setR3(false);
        sut.setnDiff(2);
        sut.setnCorrectAnswer(2);
        sut.setsRuling("Bob");

        List<String> answers = new ArrayList<>();
        answers.add("Ja");
        answers.add("Nee");
        answers.add(null);
        answers.add(null);

        sut.setRgsAnswers(answers);
    }

    @Test
    void set() {
        assertThat(sut.isR1()).isTrue();
        assertThat(sut.isR2()).isFalse();
        assertThat(sut.isR3()).isFalse();
        assertThat(sut.isBR()).isTrue();
        assertThat(sut.isfSeniors()).isTrue();
        assertThat(sut.isfRegular()).isFalse();
        assertThat(sut.isfBaseball()).isFalse();
        assertThat(sut.getnDiff()).isEqualTo(2);
        assertThat(sut.getcOuts()).isEqualTo(1);
        assertThat(sut.getcBalls()).isEqualTo(1);
        assertThat(sut.getcStrikes()).isEqualTo(1);
        assertThat(sut.getsQuestion()).isEqualTo("Johannes");
        assertThat(sut.getRgsAnswers()).containsExactly("Ja", "Nee", null, null);
        assertThat(sut.getnCorrectAnswer()).isEqualTo(2);
        assertThat(sut.getsRuling()).isEqualTo("Bob");
    }

    @Test
    void boolMutation() {
        sut.setR1(false);
        sut.setR2(true);
        sut.setR3(true);
        sut.setBR(false);

        assertThat(sut.isR1()).isFalse();
        assertThat(sut.isR2()).isTrue();
        assertThat(sut.isR3()).isTrue();
        assertThat(sut.isBR()).isFalse();
    }

    @Test
    void transformGameState() {
        sut.setR2(true);
        sut.setR3(true);
        GameState gs = sut.transformGameState();

        assertThat(gs).isNotNull();
        assertThat(gs.getBalls()).isEqualTo(1);
        assertThat(gs.getOuts()).isEqualTo(1);
        assertThat(gs.getStrikes()).isEqualTo(1);
        assertThat(gs.isRunnerBase1()).isTrue();
        assertThat(gs.isRunnerBase2()).isTrue();
        assertThat(gs.isRunnerBase3()).isTrue();
        assertThat(gs.isBatterRunner()).isTrue();
    }

    @Test
    void transformRulingAndValue() {
        assertThat(sut.transformValue()).containsExactlyEntriesOf(Map.of(NL_NL, "Johannes", EN_US, "Johannes"));
        assertThat(sut.transformRuling()).containsExactlyEntriesOf(Map.of(EN_US, "Bob", NL_NL, "Bob"));
    }

    @Test
    void transformDifficulty() {
        assertThat(sut.transformDifficulty()).isEqualTo(UMPIRE_2);

        sut.setnDiff(4);
        assertThat(sut.transformDifficulty()).isEqualTo(UMPIRE_4);

        sut.setnDiff(3);
        assertThat(sut.transformDifficulty()).isEqualTo(UMPIRE_3);

        sut.setnDiff(1);
        assertThat(sut.transformDifficulty()).isEqualTo(UMPIRE_1);
    }

    @Test
    void transformAnswers() {
        List<Answer> answers = sut.transformAnswers();

        assertThat(answers).hasSize(2);

        Answer correct = answers.stream()
                                .filter(Answer::isCorrect)
                                .findFirst()
                                .orElse(null);

        assertThat(correct).isNotNull();
        assertThat(correct.getI18nValue()).containsExactlyInAnyOrderEntriesOf(Map.of(NL_NL, "Nee", EN_US, "Nee"));
    }

    @Test
    void toQuestion() {
        Question q = sut.toQuestion();

        assertThat(q).isNotNull();
        assertThat(q.isEnabled()).isTrue();
        assertThat(q.getAnswers()).isNotNull()
                                  .isNotEmpty();
        assertThat(q.getGameState()).isNotNull();
        assertThat(q.getDifficulty()).isNotNull();
        assertThat(q.getI18nValue()).isNotNull();
        assertThat(q.getI18nRuling()).isNotNull();
    }
}