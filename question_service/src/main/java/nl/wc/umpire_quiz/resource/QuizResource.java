package nl.wc.umpire_quiz.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import nl.wc.umpire_quiz.domain.QuizGeneration;
import nl.wc.umpire_quiz.domain.QuizValidation;
import nl.wc.umpire_quiz.service.QuizService;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/quizzes")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class QuizResource {
    private final QuizService service;

    @Inject
    public QuizResource(QuizService service) {
        this.service = service;
    }

    @POST
    public QuizGeneration generateQuiz(QuizGeneration quizGeneration) {
        return service.generateQuiz(quizGeneration);
    }

    @PUT
    public QuizValidation validateQuiz(QuizGeneration quizGeneration) {
        return service.validateQuiz(quizGeneration);
    }
}
