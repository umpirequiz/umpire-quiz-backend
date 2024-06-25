package nl.wc.umpire_quiz.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import nl.wc.umpire_quiz.dao.QuestionDao;
import nl.wc.umpire_quiz.model.Difficulty;
import nl.wc.umpire_quiz.model.QuizGeneration;
import nl.wc.umpire_quiz.model.QuizValidation;

import java.util.List;

@Dependent
public class QuizService {
    public static final int DEFAULT_SIZE = 10;
    private final QuestionDao questionDao;

    @Inject
    public QuizService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public QuizGeneration generateQuiz(int quizSize, List<Difficulty> difficulties) {
        QuizGeneration quizGeneration = new QuizGeneration(quizSizeOrDefault(quizSize), difficulties);
        quizGeneration.setQuestions(questionDao.getQuizQuestions(quizGeneration.getQuizSize(), quizGeneration.getDifficulties()));
        quizGeneration.setQuizSize(quizGeneration.getQuestions()
                                                 .size());
        return quizGeneration;
    }

    int quizSizeOrDefault(int quizSize) {
        return quizSize > 0 ? quizSize : DEFAULT_SIZE;
    }

    public QuizValidation validateQuiz(QuizGeneration quizGeneration) {
        QuizValidation qV = new QuizValidation(quizGeneration);
        qV.setQuestions(quizGeneration.getQuestions()
                                      .stream()
                                      .map(q -> questionDao.find(q.getId()))
                                      .toList());
        return qV;
    }
}
