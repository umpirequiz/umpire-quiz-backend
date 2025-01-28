package nl.wc.umpire_quiz.model;

import java.io.Serializable;
import java.util.Objects;

public class QuestionErrorId implements Serializable {

    private Long id;
    private Long question;

    public QuestionErrorId() {}

    public QuestionErrorId(Long id, Long question) {
        this.id = id;
        this.question = question;
    }

    // hashCode and equals are required for composite keys
    @Override
    public int hashCode() {
        return Objects.hash(id, question);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        QuestionErrorId that = (QuestionErrorId) obj;
        return Objects.equals(id, that.id) && Objects.equals(question, that.question);
    }

}
