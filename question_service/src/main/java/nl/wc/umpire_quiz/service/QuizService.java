package nl.wc.umpire_quiz.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import nl.wc.umpire_quiz.dao.QuestionDao;
import nl.wc.umpire_quiz.domain.Quiz;

@Dependent
public class QuizService {
    private final QuestionDao questionDao;

    @Inject
    public QuizService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public Quiz generateQuiz(Quiz quiz) {
        quiz.setQuestions(questionDao.getQuizQuestions(quiz.getQuizSize(), quiz.getDifficulties()));
        quiz.setQuizSize(quiz.getQuestions().size());
        return quiz;
    }
}
