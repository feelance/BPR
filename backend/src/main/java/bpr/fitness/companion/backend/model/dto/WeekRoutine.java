package bpr.fitness.companion.backend.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeekRoutine {
    private Long id;
    private String name;
    private String notes;
    private Set<Long> dayRoutines;
    private Long userId;
}