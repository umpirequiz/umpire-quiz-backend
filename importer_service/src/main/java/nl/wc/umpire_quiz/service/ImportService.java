package nl.wc.umpire_quiz.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import nl.wc.umpire_quiz.dao.QuestionDao;
import nl.wc.umpire_quiz.model.ImportQuestionsCounterDto;
import nl.wc.umpire_quiz.model.ImportedQuestion;

import java.util.List;

@Dependent
public class ImportService {
    private final QuestionDao questionDao;

    @Inject
    public ImportService(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    public ImportQuestionsCounterDto importQuestions(List<ImportedQuestion> q) {
        ImportQuestionsCounterDto questions = new ImportQuestionsCounterDto(q.stream()
                                                                             .filter(ImportedQuestion::shouldBeImported)
                                                                             .map(ImportedQuestion::toQuestion)
                                                                             .toList());
        questionDao.save(questions.getImportedQuestions());
        return questions;
    }
}
