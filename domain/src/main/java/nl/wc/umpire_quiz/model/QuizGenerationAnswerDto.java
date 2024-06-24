package nl.wc.umpire_quiz.model;

import java.util.Map;

public class QuizGenerationAnswerDto {
    private long id;
    private Map<CountryCode, String> i18nValue;

    public QuizGenerationAnswerDto() {
        //Needed?
    }

    public QuizGenerationAnswerDto(Answer a) {
        this.setId(a.getId());
        this.setI18nValue(a.getI18nValue());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<CountryCode, String> getI18nValue() {
        return i18nValue;
    }

    public void setI18nValue(Map<CountryCode, String> i18nValue) {
        this.i18nValue = i18nValue;
    }
}
