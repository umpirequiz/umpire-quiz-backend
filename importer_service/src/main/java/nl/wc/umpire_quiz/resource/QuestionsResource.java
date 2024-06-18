package nl.wc.umpire_quiz.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import nl.wc.umpire_quiz.dao.QuestionDao;
import nl.wc.umpire_quiz.model.Question;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/Questions")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class QuestionsResource {
    private final QuestionDao questionDao;

    @Inject
    public QuestionsResource(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @POST
    public Question createQuestion(Question q) {
        return questionDao.save(q);
    }
}
