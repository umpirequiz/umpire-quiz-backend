package nl.wc.umpire_quiz.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import nl.wc.umpire_quiz.dao.QuestionDao;
import nl.wc.umpire_quiz.domain.QuizGeneration;
import nl.wc.umpire_quiz.domain.QuizValidation;

@Dependent
public class QuizService {
    private final QuestionDao questionDao;

    @Inject
    public QuizService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public QuizGeneration generateQuiz(QuizGeneration quizGeneration) {
        quizGeneration.setQuestions(questionDao.getQuizQuestions(quizGeneration.getQuizSize(), quizGeneration.getDifficulties()));
        quizGeneration.setQuizSize(quizGeneration.getQuestions()
                                                 .size());
        return quizGeneration;
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
