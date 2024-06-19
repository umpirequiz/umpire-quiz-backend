package nl.wc.umpire_quiz.model;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.*;

import java.util.Map;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @ManyToOne
    @JsonbTransient
    private Question question;

    @ElementCollection
    @MapKeyEnumerated(STRING)
    private Map<CountryCode, String> i18nValue;

    private boolean correct;

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

    public Map<CountryCode, String> getI18nValue() {
        return i18nValue;
    }

    public void setI18nValue(Map<CountryCode, String> i18nValue) {
        this.i18nValue = i18nValue;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
