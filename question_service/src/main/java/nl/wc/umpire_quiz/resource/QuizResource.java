package nl.wc.umpire_quiz.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import nl.wc.umpire_quiz.model.QuizGeneration;
import nl.wc.umpire_quiz.service.QuizService;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.CREATED;
import static jakarta.ws.rs.core.Response.Status.OK;
import static nl.wc.umpire_quiz.model.Difficulty.toDifficulties;

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
    public Response generateQuiz(@QueryParam("quizSize") int quizSize, @QueryParam("levels") String levels) {
        return Response.status(OK)
                .entity(service.generateQuiz(quizSize, toDifficulties(levels)))
                .build();
    }

    @POST
    public Response validateQuiz(QuizGeneration quizGeneration) {
        return Response.status(CREATED)
                .entity(service.validateQuiz(quizGeneration))
                .build();
    }
}
