package nl.wc.umpire_quiz.model;

import jakarta.json.bind.annotation.JsonbProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static nl.wc.umpire_quiz.model.CountryCode.EN_US;
import static nl.wc.umpire_quiz.model.CountryCode.NL_NL;
import static nl.wc.umpire_quiz.model.Difficulty.*;

public class ImportedQuestion {
    @JsonbProperty("R1")
    private boolean r1;
    @JsonbProperty("R2")
    private boolean r2;
    @JsonbProperty("R3")
    private boolean r3;
    @JsonbProperty("BR")
    private boolean bR;
    private boolean fSeniors;
    private boolean fRegular;
    private boolean fBaseball;
    private int nDiff;
    private int cOuts;
    private int cBalls;
    private int cStrikes;
    private String sQuestion;
    private List<String> rgsAnswers;
    private int nCorrectAnswer;
    private String sRuling;

    public ImportedQuestion(boolean fSeniors, boolean fBaseball, boolean fRegular) {
        this.fSeniors = fSeniors;
        this.fBaseball = fBaseball;
        this.fRegular = fRegular;
        this.rgsAnswers = List.of("Bob", "Bob", "Bob");
        this.nCorrectAnswer = 2;
        this.sQuestion = "Bob";
        this.sRuling = "Bob";
    }

    public ImportedQuestion() {
        //Empty Constructor needed.
    }

    public String getsRuling() {
        return sRuling;
    }

    public void setsRuling(String sRuling) {
        this.sRuling = sRuling;
    }

    public int getnCorrectAnswer() {
        return nCorrectAnswer;
    }

    public void setnCorrectAnswer(int nCorrectAnswer) {
        this.nCorrectAnswer = nCorrectAnswer;
    }

    public List<String> getRgsAnswers() {
        return rgsAnswers;
    }

    public void setRgsAnswers(List<String> rgsAnswers) {
        this.rgsAnswers = rgsAnswers;
    }

    public String getsQuestion() {
        return sQuestion;
    }

    public void setsQuestion(String sQuestion) {
        this.sQuestion = sQuestion;
    }

    public int getcStrikes() {
        return cStrikes;
    }

    public void setcStrikes(int cStrikes) {
        this.cStrikes = cStrikes;
    }

    public int getcBalls() {
        return cBalls;
    }

    public void setcBalls(int cBalls) {
        this.cBalls = cBalls;
    }

    public int getcOuts() {
        return cOuts;
    }

    public void setcOuts(int cOuts) {
        this.cOuts = cOuts;
    }

    public int getnDiff() {
        return nDiff;
    }

    public void setnDiff(int nDiff) {
        this.nDiff = nDiff;
    }

    public boolean isfBaseball() {
        return fBaseball;
    }

    public void setfBaseball(boolean fBaseball) {
        this.fBaseball = fBaseball;
    }

    public boolean isfRegular() {
        return fRegular;
    }

    public void setfRegular(boolean fRegular) {
        this.fRegular = fRegular;
    }

    public boolean isfSeniors() {
        return fSeniors;
    }

    public void setfSeniors(boolean fSeniors) {
        this.fSeniors = fSeniors;
    }

    public boolean isBR() {
        return bR;
    }

    public void setBR(boolean bR) {
        this.bR = bR;
    }

    public boolean isR3() {
        return r3;
    }

    public void setR3(boolean r3) {
        this.r3 = r3;
    }

    public boolean isR2() {
        return r2;
    }

    public void setR2(boolean r2) {
        this.r2 = r2;
    }

    public boolean isR1() {
        return r1;
    }

    public void setR1(boolean r1) {
        this.r1 = r1;
    }

    public Question toQuestion() {
        Question q = new Question();
        q.setEnabled(true);
        q.setAnswers(this.transformAnswers());
        q.setGameState(this.transformGameState());
        q.setDifficulty(this.transformDifficulty());
        q.setI18nValue(this.transformValue());
        q.setI18nRuling(this.transformRuling());
        return q;
    }

    Map<CountryCode, String> transformRuling() {
        return Map.of(EN_US, this.sRuling, NL_NL, this.sRuling);
    }

    Map<CountryCode, String> transformValue() {
        return Map.of(NL_NL, this.sQuestion, EN_US, this.sQuestion);
    }

    Difficulty transformDifficulty() {
        return switch (this.nDiff) {
            case 4 -> UMPIRE_4;
            case 3 -> UMPIRE_3;
            case 2 -> UMPIRE_2;
            default -> UMPIRE_1;
        };
    }

    GameState transformGameState() {
        GameState gameState = new GameState();
        gameState.setBalls(this.cBalls);
        gameState.setStrikes(this.cStrikes);
        gameState.setOuts(this.cOuts);
        gameState.setRunnerBase1(this.r1);
        gameState.setRunnerBase2(this.r2);
        gameState.setRunnerBase3(this.r3);
        gameState.setBatterRunner(this.bR);
        return gameState;
    }

    List<Answer> transformAnswers() {
        List<Answer> answers = new ArrayList<>();
        this.rgsAnswers.forEach(a -> {
            if (a != null) {
                answers.add(new Answer(a));
            }
        });
        answers.get(this.nCorrectAnswer - 1)
               .setCorrect(true);
        return answers;
    }

    public boolean shouldBeImported() {
        return this.isfBaseball() && this.isfRegular() && this.isfSeniors();
    }
}