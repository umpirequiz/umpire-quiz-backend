package nl.wc.umpire_quiz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@Builder @AllArgsConstructor @NoArgsConstructor //@RequiredArgsConstructor
@IdClass(QuestionErrorId.class)
public class QuestionError {

    @Id
    private Long id;

    @Id
    @ManyToOne
    @JoinColumn(name = "questionId", referencedColumnName = "id")
    private Question question;

    private String description;

}
