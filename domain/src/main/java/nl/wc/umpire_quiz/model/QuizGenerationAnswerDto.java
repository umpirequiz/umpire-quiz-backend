package nl.wc.umpire_quiz.model;

public class QuizGenerationAnswerDto {
    private long id;
    private InternationalizedStrings i18nValue;

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

    public InternationalizedStrings getI18nValue() {
        return i18nValue;
    }

    public void setI18nValue(InternationalizedStrings i18nValue) {
        this.i18nValue = i18nValue;
    }
}
