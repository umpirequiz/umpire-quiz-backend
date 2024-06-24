package nl.wc.umpire_quiz.model;

import java.util.List;
import java.util.Map;

public class QuizGenerationQuestionDto {
    private long id;
    private GameState gameState;
    private Map<CountryCode, String> i18nValue;
    private List<QuizGenerationAnswerDto> answers;

    public QuizGenerationQuestionDto() {
        //Needed?
    }

    public QuizGenerationQuestionDto(long id) {
        this.id = id;
    }

    public QuizGenerationQuestionDto(Question q) {
        this.setId(q.getId());
        this.setI18nValue(q.getI18nValue());
        this.setGameState(q.getGameState());
        this.setAnswers(q.getAnswers()
                         .stream()
                         .map(QuizGenerationAnswerDto::new)
                         .toList());
    }

    public List<QuizGenerationAnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<QuizGenerationAnswerDto> answers) {
        this.answers = answers;
    }

    public Map<CountryCode, String> getI18nValue() {
        return i18nValue;
    }

    public void setI18nValue(Map<CountryCode, String> i18nValue) {
        this.i18nValue = i18nValue;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
