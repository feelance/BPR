package bpr.fitness.companion.backend.model.dto;


import lombok.Data;
import java.util.List;
import java.util.Set;

@Data
public class Exercise {
    private Long id;
    private String name;
    private String description;
    private String category;
    private String imageUrl;
    private Long dayRoutineId;

    private Set<Long> exerciseRecords;
    private List<Long> exerciseDayRoutine;
}