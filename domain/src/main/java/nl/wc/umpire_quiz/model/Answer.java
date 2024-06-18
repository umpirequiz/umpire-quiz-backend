package nl.wc.umpire_quiz.model;

import jakarta.persistence.*;

import java.util.Locale;
import java.util.Map;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @ManyToOne
    private Question question;

    @ElementCollection
    private Map<Locale.IsoCountryCode, String> i18nValue;

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

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

}
