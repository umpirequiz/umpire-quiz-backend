package nl.wc.umpire_quiz.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Map;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Embedded
    private GameState gameState;

    private Difficulty difficulty;

    @SuppressWarnings("JpaAttributeTypeInspection")
    @MapKeyEnumerated(STRING)
    @ElementCollection
    @Lob
    private Map<CountryCode, String> i18nValue;

    @Size(min = 2, max = 6)
    @OneToMany(mappedBy = "question", cascade = ALL)
    private List<Answer> answers;

    @SuppressWarnings("JpaAttributeTypeInspection")
    @MapKeyEnumerated(STRING)
    @ElementCollection
    @Lob
    private Map<CountryCode, String> i18nRuling;

    private boolean enabled;

    public Question() {
        //Empty constructor for JPA
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Map<CountryCode, String> getI18nValue() {
        return i18nValue;
    }

    public void setI18nValue(Map<CountryCode, String> i18nValue) {
        this.i18nValue = i18nValue;
    }

    public @Size(min = 2, max = 6) List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(@Size(min = 2, max = 6) List<Answer> answers) {
        answers.forEach(a -> a.setQuestion(this));
        this.answers = answers;
    }

    public Question copy() {
        Question newQ = new Question();
        newQ.setEnabled(true);
        newQ.setAnswers(this.getAnswers());
        newQ.setDifficulty(this.getDifficulty());
        newQ.setGameState(this.getGameState());
        newQ.setI18nRuling(this.getI18nRuling());
        newQ.setI18nValue(this.getI18nValue());
        return newQ;
    }

    public Map<CountryCode, String> getI18nRuling() {
        return i18nRuling;
    }

    public void setI18nRuling(Map<CountryCode, String> i18nRuling) {
        this.i18nRuling = i18nRuling;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
