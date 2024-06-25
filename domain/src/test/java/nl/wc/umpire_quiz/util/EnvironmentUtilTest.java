package nl.wc.umpire_quiz.util;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.SetEnvironmentVariable;

import static nl.wc.umpire_quiz.util.EnvironmentUtil.getFrontendUrl;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EnvironmentUtilTest {

    @Test
    void getFrontendUrlTest() {
        assertThat(getFrontendUrl()).isEqualTo("http://localhost:4200");
    }

    @Test
    @SetEnvironmentVariable(key = "FRONTEND_URL", value = "https://test.umpire.quiz/")
    void getFrontendUrlWithSetEnv() {
        assertThat(getFrontendUrl()).isEqualTo("https://test.umpire.quiz/");
    }
}