package bpr.fitness.companion.backend.model.dto;

import lombok.Data;

@Data
public class ExerciseDayRoutine {
    private Long id;
    private Long exerciseId;
    private Long dayRoutineId;
    private int order;
}