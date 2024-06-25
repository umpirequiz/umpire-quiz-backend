package nl.wc.umpire_quiz.util;

public enum EnvironmentUtil {
    ;
    private static final String FRONTEND_URL = "http://localhost:4200";

    public static String getFrontendUrl() {
        String environmentVariable = System.getenv("FRONTEND_URL");
        return environmentVariable != null ? environmentVariable : FRONTEND_URL;
    }
}
