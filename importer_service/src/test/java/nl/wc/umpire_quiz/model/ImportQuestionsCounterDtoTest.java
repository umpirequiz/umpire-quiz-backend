package nl.wc.umpire_quiz.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ImportQuestionsCounterDtoTest {
    @Test
    void testQuestionListConstructor() {
        List<Question> questions = List.of(new Question(), new Question(), new Question());

        ImportQuestionsCounterDto sut = new ImportQuestionsCounterDto(questions);

        assertThat(sut.getImportedQuestions()).containsExactlyInAnyOrderElementsOf(questions);
        assertThat(sut.getImportedQuestionCount()).isEqualTo(3);
    }
}