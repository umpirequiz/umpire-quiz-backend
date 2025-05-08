package nl.wc.umpire_quiz.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.CascadeType.REMOVE;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter
@Builder @AllArgsConstructor @NoArgsConstructor //@RequiredArgsConstructor
@Cacheable(false)
@With
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

    private String link;

    @OneToMany(mappedBy = "question", cascade = REMOVE, orphanRemoval = true)
    private List<QuestionError> errors;

    public @Size(min = 2, max = 6) List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(@Size(min = 2, max = 6) List<Answer> answers) {
        answers.forEach(a -> a.setQuestion(this));
        this.answers = answers;
    }

    public Question copy() {
        return this.withEnabled(true).withId(0);
    }

    public void removeError(QuestionError e) {
        this.errors.remove(e);
    }
}
