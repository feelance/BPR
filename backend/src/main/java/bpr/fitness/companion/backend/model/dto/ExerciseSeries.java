package bpr.fitness.companion.backend.model.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ExerciseSeries {
    private Long id;
    private BigDecimal weight;
    private Integer repetitions;
    private Long exerciseId;
    private Long exerciseRecordId;
}