package nl.wc.umpire_quiz.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Data
@Builder @AllArgsConstructor @NoArgsConstructor //@RequiredArgsConstructor
@NamedQuery(name = "Question.findAll", query = "select q from Question q")
@NamedQuery(name = "Question.findEnabled", query = "select q from Question q where q.enabled = true")
@NamedQuery(name = "Question.findEnabledBy", query = "select q from Question q where q.enabled = true and (q.i18nValue.enUS like :q or q.i18nValue.nlNL like :q)")
public class Question {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Embedded
    private GameState gameState;

    private Difficulty difficulty;

    @Embedded
    @AttributeOverride(name = "enUS", column = @Column(name = "i18nValue_EN_US"))
    @AttributeOverride(name = "nlNL", column = @Column(name = "i18nValue_NL_NL"))
    private InternationalizedStrings i18nValue;

    @Size(min = 2, max = 6)
    @OneToMany(mappedBy = "question", cascade = ALL)
    @Builder.Default
    private List<Answer> answers = new ArrayList<>();

    @Embedded
    @AttributeOverride(name = "enUS", column = @Column(name = "i18nRuling_EN_US"))
    @AttributeOverride(name = "nlNL", column = @Column(name = "i18nRuling_NL_NL"))
    private InternationalizedStrings i18nRuling;

    private boolean enabled;

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

}
