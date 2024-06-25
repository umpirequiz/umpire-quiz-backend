package nl.wc.umpire_quiz.model;

import java.util.List;

import static nl.wc.umpire_quiz.model.Difficulty.*;

public class QuizGeneration {
    private List<Difficulty> difficulties = List.of(
            UMPIRE_1,
            UMPIRE_2,
            UMPIRE_3,
            UMPIRE_4);
    private List<QuizGenerationQuestionDto> questions;
    private int quizSize;

    public QuizGeneration() {
        //Needed for JPA
    }

    public QuizGeneration(int quizSize, List<Difficulty> difficulties) {
        setQuizSize(quizSize);
        setDifficulties(difficulties);
    }

    public List<Difficulty> getDifficulties() {
        return difficulties;
    }

    public void setDifficulties(List<Difficulty> difficulties) {
        if (difficulties.isEmpty()) {
            this.difficulties = List.of(
                    UMPIRE_1,
                    UMPIRE_2,
                    UMPIRE_3,
                    UMPIRE_4);
        } else {
            this.difficulties = difficulties;
        }
    }

    public List<QuizGenerationQuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuizGenerationQuestionDto> questions) {
        this.questions = questions;
    }

    public int getQuizSize() {
        return quizSize;
    }

    public void setQuizSize(int quizSize) {
        this.quizSize = quizSize;
    }
}
