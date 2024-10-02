package nl.wc.umpire_quiz.dao;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import nl.wc.umpire_quiz.model.Difficulty;
import nl.wc.umpire_quiz.model.Question;
import nl.wc.umpire_quiz.model.QuizGenerationQuestionDto;

import java.util.Collections;
import java.util.List;

@Dependent
public class QuestionDao {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Question save(Question q) {
        return em.merge(q);
    }

    @Transactional
    public void save(List<Question> q) {
        q.forEach(em::merge);
    }

    @Transactional
    public void delete(Question q) {
        delete(q.getId());
    }

    @Transactional
    public void delete(long id) {
        Question qDatabase = em.find(Question.class, id);
        qDatabase.setEnabled(false);
        save(qDatabase);
    }

    @Transactional
    public Question update(long id, Question q) {
        delete(id);
        return save(q.copy());
    }

    public List<Question> findBy(String term) {
        return findBy(term, false);
    }

    public List<Question> findBy(String term, boolean enabledQuestionsOnly) {
        if (!enabledQuestionsOnly) {
            return this.em.createNamedQuery("Question.findAll", Question.class).getResultList();
        }

        if (term == null || term.isBlank()) {
            return this.em.createNamedQuery("Question.findAllActive", Question.class).getResultList();
        }

        return this.em.createNamedQuery("Question.findBy", Question.class)
                .setParameter("q", "%" + term + "%")
                .getResultList();
    }

    public Question find(int id) {
        return this.find((long) id);
    }

    public Question find(long id) {
        return em.find(Question.class, id);
    }

    public List<QuizGenerationQuestionDto> getQuizQuestions(int quizSize, List<Difficulty> difficulties) {
        String query = "SELECT q FROM Question q WHERE q.enabled = TRUE AND q.difficulty IN :difficulties";
        List<Question> validQuestions = em.createQuery(query, Question.class)
                .setParameter("difficulties", difficulties)
                .getResultList();
        Collections.shuffle(validQuestions);
        try {
            return validQuestions.subList(0, quizSize)
                    .stream()
                    .map(QuizGenerationQuestionDto::new)
                    .toList();
        } catch (IndexOutOfBoundsException e) {
            return validQuestions.stream()
                    .map(QuizGenerationQuestionDto::new)
                    .toList();
        }
    }
}
