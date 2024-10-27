package bpr.fitness.companion.backend.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class DatabaseConstraintException extends DataIntegrityViolationException {
        public DatabaseConstraintException(String message) {
            super(message);
        }
    }

