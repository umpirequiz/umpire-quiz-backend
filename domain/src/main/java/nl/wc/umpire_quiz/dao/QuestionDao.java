package nl.wc.umpire_quiz.dao;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import nl.wc.umpire_quiz.model.Difficulty;
import nl.wc.umpire_quiz.model.Question;
import nl.wc.umpire_quiz.model.QuestionError;
import nl.wc.umpire_quiz.model.QuestionErrorDto;
import nl.wc.umpire_quiz.model.QuizGenerationQuestionDto;

import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

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
    public void addError(long questionId, QuestionErrorDto dto) {
        var q = find(questionId);
        if (q == null)
            throw new IllegalArgumentException("questionId does not exist");

        em.merge(QuestionError.of(q, dto));
    }

    @Transactional
    public void delete(Question q) {
        delete(q.getId());
    }

    @Transactional
    public void delete(long id) {
        var q = em.find(Question.class, id);
        q.setEnabled(false);
        save(q);
    }

    @Transactional
    public Question update(long id, Question q) {
        delete(id);
        return save(q.copy());
    }

    public List<Question> findBy(String term, boolean allQuestions) {
        return findBy(term, allQuestions, false);
    }

    public List<Question> findBy(String term, boolean allQuestions, boolean bugs) {
        TypedQuery<Question> query = this.em.createQuery(query(term, allQuestions, bugs), Question.class);
        if (isPresent(term)) {
            query.setParameter("term", "%" + term + "%");
        }
        return query.getResultList();
    }

    String query(String term, boolean allQuestions) {
        return query(term, allQuestions, false);
    }

    String query(String term, boolean allQuestions, boolean bugs) {
        StringBuilder query = new StringBuilder("select DISTINCT(q) from Question q ");
        if (bugs) {
            query.append("JOIN FETCH q.errors e ");
        }
        if (isPresent(term) || !allQuestions) {
            query.append("WHERE ");
            StringJoiner where = new StringJoiner(" and ");
            if (isPresent(term)) {
                where.add("(q.i18nValue.enUS like :term or q.i18nValue.nlNL like :term)");
            }
            if (!allQuestions) {
                where.add("q.enabled = true");
            }
            query.append(where);
        }
        System.out.println("query=" + query);
        return query.toString();
    }

    private boolean isPresent(String term) {
        return (term != null && !term.isBlank());
    }

    public Question find(int id) {
        return this.find((long) id);
    }

    public Question find(long id) {
        return em.find(Question.class, id);
    }

    public List<QuizGenerationQuestionDto> getQuizQuestions(int quizSize, List<Difficulty> difficulties) {
        String query = "SELECT q FROM Question q WHERE q.enabled = TRUE AND q.difficulty IN :difficulties";
        List<Question> validQuestions = em.createQuery(
                        query, Question.class)
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

    @Transactional
    public void deleteError(long errorId) {
        var e = em.createQuery("SELECT e FROM QuestionError e WHERE e.id = :id", QuestionError.class)
                .setParameter("id", errorId)
                .getSingleResult();
        var q = e.getQuestion();
        q.removeError(e);
        em.merge(q);
    }
}
