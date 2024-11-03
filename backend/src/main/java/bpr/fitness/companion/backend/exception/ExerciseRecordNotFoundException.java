package bpr.fitness.companion.backend.exception;

public class ExerciseRecordNotFoundException extends RuntimeException {
    public ExerciseRecordNotFoundException(String message) {
        super(message);
    }
}