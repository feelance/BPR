package bpr.fitness.companion.backend.model.dto;

import lombok.Data;
import java.util.Set;

@Data
public class ExerciseRecord {
    private Long id;
    private String name;
    private String notes;
    private Set<Long> dayRoutines;
    private Long userId;
}