package nl.wc.umpire_quiz.dao;

import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import nl.wc.umpire_quiz.model.Question;

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
        Question qDatabase = em.find(Question.class, q.getId());
        qDatabase.setEnabled(false);
        save(qDatabase);
    }

    @Transactional
    public Question update(Question q) {
        delete(q);
        return save(q.copy());
    }

    public Question find(int id) {
        return this.find((long) id);
    }

    public Question find(long id) {
        return em.find(Question.class, id);
    }
}
