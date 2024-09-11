package nl.wc.umpire_quiz.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import nl.wc.umpire_quiz.dao.QuestionDao;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.OK;

@Path("/questions")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class QuestionsResource {
    private final QuestionDao dao;

    @Inject
    public QuestionsResource(QuestionDao dao) {
        this.dao = dao;
    }

    @GET
    public Response get(@QueryParam("text") String text) {
        return Response.status(OK)
                .entity(dao.findAll())
                .build();
    }
}
