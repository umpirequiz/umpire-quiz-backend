package nl.wc.umpire_quiz.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import nl.wc.umpire_quiz.domain.Quiz;
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
    public Quiz generateQuiz(Quiz quiz) {
        return service.generateQuiz(quiz);
    }
}
