package nl.wc.umpire_quiz.model;

import java.util.List;

public class ImportQuestionsCounterDto {
    private int importedQuestionCount;
    private List<Question> importedQuestions;

    public ImportQuestionsCounterDto(List<Question> q) {
        setImportedQuestionCount(q.size());
        setImportedQuestions(q);
    }

    public int getImportedQuestionCount() {
        return importedQuestionCount;
    }

    public void setImportedQuestionCount(int importedQuestionCount) {
        this.importedQuestionCount = importedQuestionCount;
    }

    public List<Question> getImportedQuestions() {
        return importedQuestions;
    }

    public void setImportedQuestions(List<Question> importedQuestions) {
        this.importedQuestions = importedQuestions;
    }
}
