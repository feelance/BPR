package bpr.fitness.companion.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;


@Entity
@Data
@Table(name = "EXERCISE_SERIES")
public class ExerciseSeriesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal weight;

    @Column(nullable = false)
    private Integer repetitions;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private ExerciseEntity exercise;

    @ManyToOne
    @JoinColumn(name = "exercise_record_id")
    private ExerciseRecordEntity exerciseRecord;


}
