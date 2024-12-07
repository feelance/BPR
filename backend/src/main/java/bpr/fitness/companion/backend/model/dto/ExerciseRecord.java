package bpr.fitness.companion.backend.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;

@Data
public class ExerciseRecord {
    private Long id;
    private BigDecimal weight;
    private Integer repetitions;
    private Long exerciseId;
    private Long userId;
    private Timestamp date;
}