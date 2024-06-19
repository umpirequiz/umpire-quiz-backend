package nl.wc.umpire_quiz.model;

import nl.wc.umpire_quiz.exceptions.GameStateValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameStateTest {
    @Test
    void GameStateHappyFlow() {
        GameState g = new GameState();

        g.setRunnerBase1(true);
        assertThat(g.isRunnerBase1()).isTrue();
        g.setRunnerBase1(false);
        assertThat(g.isRunnerBase1()).isFalse();

        g.setRunnerBase2(true);
        assertThat(g.isRunnerBase2()).isTrue();
        g.setRunnerBase2(false);
        assertThat(g.isRunnerBase2()).isFalse();

        g.setRunnerBase3(true);
        assertThat(g.isRunnerBase3()).isTrue();
        g.setRunnerBase3(false);
        assertThat(g.isRunnerBase3()).isFalse();

        g.setBatterRunner(true);
        assertThat(g.isBatterRunner()).isTrue();
        g.setBatterRunner(false);
        assertThat(g.isBatterRunner()).isFalse();

        g.setBalls(3);
        assertThat(g.getBalls()).isEqualTo(3);
        g.setBalls("0");
        assertThat(g.getBalls()).isZero();

        g.setOuts(3);
        assertThat(g.getOuts()).isEqualTo(3);
        g.setOuts("0");
        assertThat(g.getOuts()).isZero();

        g.setStrikes(3);
        assertThat(g.getStrikes()).isEqualTo(3);
        g.setStrikes("0");
        assertThat(g.getStrikes()).isZero();
    }

    @Test
    void NumberFormatException() {
        GameState g = new GameState();

        assertThatThrownBy(() -> {
            g.setStrikes("Piet");
        }).isInstanceOf(GameStateValidationException.class)
          .hasMessage("strikes have to be a numeric value.");

        assertThatThrownBy(() -> {
            g.setOuts("Piet");
        }).isInstanceOf(GameStateValidationException.class)
          .hasMessage("outs have to be a numeric value.");

        assertThatThrownBy(() -> {
            g.setBalls("Piet");
        }).isInstanceOf(GameStateValidationException.class)
          .hasMessage("balls have to be a numeric value.");
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 4})
    void NumberTooHighException(int value) {
        GameState g = new GameState();

        assertThatThrownBy(() -> {
            g.setStrikes(value);
        }).isInstanceOf(GameStateValidationException.class)
          .hasMessage("strikes can not be above 3 or below 0");

        assertThatThrownBy(() -> {
            g.setBalls(value);
        }).isInstanceOf(GameStateValidationException.class)
          .hasMessage("balls can not be above 3 or below 0");

        assertThatThrownBy(() -> {
            g.setOuts(value);
        }).isInstanceOf(GameStateValidationException.class)
          .hasMessage("outs can not be above 3 or below 0");
    }

}