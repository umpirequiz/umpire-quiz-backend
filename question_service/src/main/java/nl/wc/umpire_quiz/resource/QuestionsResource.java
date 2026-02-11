package nl.wc.umpire_quiz.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import nl.wc.umpire_quiz.dao.QuestionDao;
import nl.wc.umpire_quiz.model.Question;
import nl.wc.umpire_quiz.model.QuestionCountDto;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.CREATED;
import static jakarta.ws.rs.core.Response.Status.OK;

@Path("/questions")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class QuestionsResource {
    private final QuestionDao dao;
    private final QuestionResource questionResource;

    @Inject
    public QuestionsResource(QuestionDao dao, QuestionResource questionResource) {
        this.dao = dao;
        this.questionResource = questionResource;
    }

    @GET
    public Response getAll(@QueryParam("q") String term,
                           @QueryParam("all") boolean all,
                           @QueryParam("bugs") boolean bugs) {
        return Response.status(OK)
                .entity(dao.findBy(term, all, bugs))
                .build();
    }

    @POST
    public Response add(Question q) {
        return Response.status(CREATED)
                .entity(dao.save(q))
                .build();
    }

    @GET @Path("count")
    public List<QuestionCountDto> count() {
        return dao.count();
    }

    @Path("{id}")
    public QuestionResource remove(@PathParam("id") long id) {
        return this.questionResource.withId(id);
    }
}
