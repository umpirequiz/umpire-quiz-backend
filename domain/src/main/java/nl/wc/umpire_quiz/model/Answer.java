package nl.wc.umpire_quiz.model;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @ManyToOne
    @JsonbTransient
    private Question question;

    @Embedded
    @AttributeOverride(name = "enUS", column = @Column(name = "i18nValue_EN_US"))
    @AttributeOverride(name = "nlNL", column = @Column(name = "i18nValue_NL_NL"))
    private InternationalizedStrings i18nValue;

    private boolean correct;

    public Answer(String answer) {
        this.i18nValue = new InternationalizedStrings(answer, answer);
    }

    public Answer() {
        // Empty Constructor is mandatory for JPA
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public InternationalizedStrings getI18nValue() {
        return i18nValue;
    }

    public void setI18nValue(InternationalizedStrings i18nValue) {
        this.i18nValue = i18nValue;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
