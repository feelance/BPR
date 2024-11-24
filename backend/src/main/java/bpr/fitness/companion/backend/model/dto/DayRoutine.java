package bpr.fitness.companion.backend.model.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Data
public class DayRoutine {
    private Long id;
    private String name;
    private Set<Long> exerciseDayRoutines = new HashSet<>();;
    private Long weekRoutineId;
}
