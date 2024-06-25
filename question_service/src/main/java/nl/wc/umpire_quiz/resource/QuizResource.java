package nl.wc.umpire_quiz.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import nl.wc.umpire_quiz.model.Difficulty;
import nl.wc.umpire_quiz.model.QuizGeneration;
import nl.wc.umpire_quiz.service.QuizService;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.CREATED;
import static jakarta.ws.rs.core.Response.Status.OK;

@Path("/quizzes")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class QuizResource {
    private final QuizService service;

    @Inject
    public QuizResource(QuizService service) {
        this.service = service;
    }

    @GET
    public Response generateQuiz(@QueryParam("quizSize") int quizSize, @QueryParam("difficulty") List<Difficulty> difficulties) {
        return Response.status(OK)
                       .entity(service.generateQuiz(quizSize, difficulties))
                       .build();
    }

    @POST
    public Response validateQuiz(QuizGeneration quizGeneration) {
        return Response.status(CREATED)
                       .entity(service.validateQuiz(quizGeneration))
                       .build();
    }
}
