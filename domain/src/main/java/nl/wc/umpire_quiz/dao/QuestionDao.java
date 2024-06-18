package nl.wc.umpire_quiz.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import nl.wc.umpire_quiz.model.Question;

public class QuestionDao {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Question save(Question q) {
        em.merge(q);
        return q;
    }
}
