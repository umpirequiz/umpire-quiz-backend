package nl.wc.umpire_quiz.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.OneToMany;
import nl.wc.umpire_quiz.model.Answer;
import nl.wc.umpire_quiz.model.Difficulty;
import nl.wc.umpire_quiz.model.Question;

import java.util.List;

import static nl.wc.umpire_quiz.model.Difficulty.*;

public class Quiz {
    public List<Difficulty> getDifficulties() {
        return difficulties;
    }

    public void setDifficulties(List<Difficulty> difficulties) {
        this.difficulties = difficulties;
    }

    @ElementCollection
    private List<Difficulty> difficulties = List.of(
            UMPIRE_1,
            UMPIRE_2,
            UMPIRE_3,
            UMPIRE_4);

    @OneToMany
    private List<Question> questions;

    @OneToMany
    private List<Answer> givenAnswers;

    private int quizSize;

    public Quiz() {
        //Needed for JPA
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Answer> getGivenAnswers() {
        return givenAnswers;
    }

    public void setGivenAnswers(List<Answer> givenAnswers) {
        this.givenAnswers = givenAnswers;
    }

    public int getQuizSize() {
        return quizSize;
    }

    public void setQuizSize(int quizSize) {
        this.quizSize = quizSize;
    }
}
