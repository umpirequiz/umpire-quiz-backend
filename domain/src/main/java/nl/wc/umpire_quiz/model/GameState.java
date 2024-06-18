package nl.wc.umpire_quiz.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import nl.wc.umpire_quiz.exceptions.GameStateValidationException;

@Embeddable
public class GameState {
    private boolean runnerBase1;
    private boolean runnerBase2;
    private boolean runnerBase3;
    private boolean batterRunner;
    @Max(value = 3)
    private int outs;
    @Max(value = 3)
    private int balls;
    @Max(value = 3)
    private int strikes;

    public GameState() {
        // Empty Constructor is mandatory for JPA
    }

    public static GameStateBuilder builder() {
        return new GameStateBuilder();
    }

    @Override
    public String toString() {
        return "\n\tRunner on base 1: " + runnerBase1 + "\n" +
                "\tRunner on base 2: " + runnerBase2 + "\n" +
                "\tRunner on base 3: " + runnerBase3 + "\n" +
                "\tWith Batter/Runner: " + batterRunner + "\n" +
                "\tOuts: " + outs + "\n" +
                "\tBalls: " + balls + "\n" +
                "\tStrikes: " + strikes + "\n";
    }

    public boolean isRunnerBase1() {
        return runnerBase1;
    }

    public void setRunnerBase1(boolean runnerBase1) {
        this.runnerBase1 = runnerBase1;
    }

    public boolean isRunnerBase2() {
        return runnerBase2;
    }

    public void setRunnerBase2(boolean runnerBase2) {
        this.runnerBase2 = runnerBase2;
    }

    public boolean isRunnerBase3() {
        return runnerBase3;
    }

    public void setRunnerBase3(boolean runnerBase3) {
        this.runnerBase3 = runnerBase3;
    }

    public boolean isBatterRunner() {
        return batterRunner;
    }

    public void setBatterRunner(boolean batterRunner) {
        this.batterRunner = batterRunner;
    }

    public int getOuts() {
        return outs;
    }

    public void setOuts(int outs) {
        if (outs > 3 || outs < 0) {
            throw new GameStateValidationException("outs can not be above 3 or below 0");
        }
        this.outs = outs;
    }

    public void setOuts(String outs) {
        try {
            setOuts(Integer.parseInt(outs));
        } catch (NumberFormatException e) {
            throw new GameStateValidationException("outs have to be a numeric value.");
        }
    }

    public int getBalls() {
        return balls;
    }

    public void setBalls(int balls) {
        if (balls > 3 || balls < 0) {
            throw new GameStateValidationException("balls can not be above 3 or below 0");
        }
        this.balls = balls;
    }

    public void setBalls(String balls) {
        try {
            setBalls(Integer.parseInt(balls));
        } catch (NumberFormatException e) {
            throw new GameStateValidationException("balls have to be a numeric value.");
        }
    }

    public int getStrikes() {
        return strikes;
    }

    public void setStrikes(int strikes) {
        if (strikes > 3 || strikes < 0) {
            throw new GameStateValidationException("strikes can not be above 3 or below 0");
        }
        this.strikes = strikes;
    }

    public void setStrikes(String strikes) {
        try {
            setStrikes(Integer.parseInt(strikes));
        } catch (NumberFormatException e) {
            throw new GameStateValidationException("strikes have to be a numeric value.");
        }
    }

    public static class GameStateBuilder {
        private final GameState gameState;

        private GameStateBuilder() {
            this.gameState = new GameState();
        }

        public GameStateBuilder withRunner(int base, boolean flag) {
            switch (base) {
                case 1 -> gameState.setRunnerBase1(flag);
                case 2 -> gameState.setRunnerBase2(flag);
                case 3 -> gameState.setRunnerBase3(flag);
                default -> throw new UnsupportedOperationException("Can not set a base besides 1 to 3");
            }
            return this;
        }

        public GameStateBuilder withBatterRunner(boolean flag) {
            gameState.setBatterRunner(flag);
            return this;
        }

        public GameStateBuilder withOuts(int outs) {
            gameState.setOuts(outs);
            return this;
        }

        public GameStateBuilder withOuts(String outs) {
            gameState.setOuts(outs);
            return this;
        }

        public GameStateBuilder withBalls(int balls) {
            gameState.setBalls(balls);
            return this;
        }

        public GameStateBuilder withBalls(String balls) {
            gameState.setBalls(balls);
            return this;
        }

        public GameStateBuilder withStrikes(int strikes) {
            gameState.setStrikes(strikes);
            return this;
        }

        public GameStateBuilder withStrikes(String strikes) {
            gameState.setStrikes(strikes);
            return this;
        }

        public GameState build() {
            return gameState;
        }
    }

}