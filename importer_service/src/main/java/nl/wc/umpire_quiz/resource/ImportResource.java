package nl.wc.umpire_quiz.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import nl.wc.umpire_quiz.model.ImportedQuestion;
import nl.wc.umpire_quiz.service.ImportService;

import java.util.List;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

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
    public void importQuestions(List<ImportedQuestion> q) {
        importService.importQuestions(q);
    }
}
