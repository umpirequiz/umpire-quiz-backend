package nl.wc.umpire_quiz.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import nl.wc.umpire_quiz.dao.QuestionDao;
import nl.wc.umpire_quiz.model.Question;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.CREATED;
import static jakarta.ws.rs.core.Response.Status.NO_CONTENT;
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
    public Response getAll(@QueryParam("q") String term, @QueryParam("enabled") boolean enabledOnly) {
        return Response.status(OK)
                .entity(dao.findBy(term, enabledOnly))
                .build();
    }

    @GET @Path("{id}")
    public Response get(@PathParam("id") long id) {
        return Response.status(OK)
                .entity(dao.find(id))
                .build();
    }

    @POST
    public Response add(Question q) {
        return Response.status(CREATED)
                .entity(dao.save(q))
                .build();
    }

    @DELETE @Path("{id}")
    public Response remove(@PathParam("id") long id) {
        dao.delete(id);
        return Response.status(NO_CONTENT)
                .build();
    }

    @PUT @Path("{id}")
    public Response update(@PathParam("id") long id, Question q) {
        return Response.status(OK)
                .entity(dao.update(id, q))
                .build();
    }
}
