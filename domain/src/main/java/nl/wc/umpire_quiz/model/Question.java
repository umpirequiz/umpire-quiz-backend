package nl.wc.umpire_quiz.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Locale.IsoCountryCode;
import java.util.Map;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @Embedded
    private GameState gameState;
    private Difficulty difficulty;
    @ElementCollection
    private Map<IsoCountryCode, String> i18nValue;
    @Size(min = 2, max = 6)
    @OneToMany(mappedBy = "question", cascade = ALL)
    private List<Answer> answers;
    @ElementCollection
    private Map<IsoCountryCode, String> i18nRuling;
    private boolean enabled;
}
