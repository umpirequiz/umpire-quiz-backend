package nl.wc.umpire_quiz.exceptions;

public class GameStateValidationException extends RuntimeException {

    public GameStateValidationException(String message) {
        super(message);
    }
}
