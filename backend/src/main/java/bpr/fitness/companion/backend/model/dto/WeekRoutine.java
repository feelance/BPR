package bpr.fitness.companion.backend.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeekRoutine {
    private Long id;
    private String userId;
    private String name;
    private String notes;

}
