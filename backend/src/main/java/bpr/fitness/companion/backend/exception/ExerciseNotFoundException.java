package bpr.fitness.companion.backend.exception;

public class ExerciseNotFoundException extends RuntimeException {
    public ExerciseNotFoundException(String message) {
        super(message);
    }
}