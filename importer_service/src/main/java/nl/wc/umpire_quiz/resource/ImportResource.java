package nl.wc.umpire_quiz.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import nl.wc.umpire_quiz.model.ImportedQuestion;
import nl.wc.umpire_quiz.service.ImportService;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static jakarta.ws.rs.core.Response.Status.CREATED;

@Path("/")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class ImportResource {
    private final ImportService importService;

    @Inject
    public ImportResource(ImportService importService) {
        this.importService = importService;
    }

    @POST
    public Response importQuestions(List<ImportedQuestion> q) {
        return Response.status(CREATED)
                       .entity(importService.importQuestions(q))
                       .build();
    }
}
