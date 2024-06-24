package nl.wc.umpire_quiz.domain;

import nl.wc.umpire_quiz.model.Difficulty;
import nl.wc.umpire_quiz.model.Question;

import java.util.List;

public class QuizValidation {
    private List<Difficulty> difficulties;
    private List<Question> questions;
    private int quizSize;

    public QuizValidation() {
        //Needed
    }

    public QuizValidation(QuizGeneration qG) {
        this.setDifficulties(qG.getDifficulties());
        this.setQuizSize(qG.getQuizSize());
    }

    public List<Difficulty> getDifficulties() {
        return difficulties;
    }

    public void setDifficulties(List<Difficulty> difficulties) {
        this.difficulties = difficulties;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getQuizSize() {
        return quizSize;
    }

    public void setQuizSize(int quizSize) {
        this.quizSize = quizSize;
    }
}
