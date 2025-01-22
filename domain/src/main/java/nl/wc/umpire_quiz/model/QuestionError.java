package nl.wc.umpire_quiz.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter
@Builder @AllArgsConstructor @NoArgsConstructor //@RequiredArgsConstructor
@IdClass(QuestionErrorId.class)
public class QuestionError {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Id
    @ManyToOne
    @JoinColumn(name = "questionId", referencedColumnName = "id")
    private Question question;

    private String message;

    public static QuestionError of(Question q, QuestionErrorDto dto){
        return builder().question(q).message(dto.message()).build();
    }
}
