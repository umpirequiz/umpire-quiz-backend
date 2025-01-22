package nl.wc.umpire_quiz.resource;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import nl.wc.umpire_quiz.dao.QuestionDao;
import nl.wc.umpire_quiz.model.Question;
import nl.wc.umpire_quiz.model.QuestionErrorDto;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.CREATED;
import static jakarta.ws.rs.core.Response.Status.NO_CONTENT;
import static jakarta.ws.rs.core.Response.Status.OK;

@Dependent
@Consumes(APPLICATION_JSON) @Produces(APPLICATION_JSON)
public class QuestionResource {

    private long id;

    private final QuestionDao dao;

    @Inject
    public QuestionResource(QuestionDao dao) {
        this.dao = dao;
    }

    @GET
    public Response get() {
        return Response.status(OK)
                .entity(dao.find(id))
                .build();
    }

    @DELETE
    public Response remove() {
        dao.delete(id);
        return Response.status(NO_CONTENT)
                .build();
    }

    @PUT
    public Response update(Question q) {
        return Response.status(OK)
                .entity(dao.update(id, q))
                .build();
    }

    @POST @Path("/errors")
    public Response add(QuestionErrorDto qe) {
        dao.addError(this.id, qe);
        return Response.status(CREATED).build();
    }

    @DELETE @Path("/errors/{errorId}")
    public Response removeError(@PathParam("errorId") long errorId) {
        try {
            dao.deleteError(errorId);
        } catch (NoResultException | NonUniqueResultException e) {
            throw new BadRequestException("errorId does not exist", e);
        }
        return Response.status(NO_CONTENT).build();
    }

    public QuestionResource withId(long id) {
        this.id = id;
        return this;
    }
}
