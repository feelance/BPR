package bpr.fitness.companion.backend.model.entity;

import bpr.fitness.companion.backend.model.dto.DayRoutine;
import bpr.fitness.companion.backend.model.dto.Exercise;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "EXERCISE_DAY_ROUTINE")
public class ExerciseDayRoutineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private ExerciseEntity exercise;

    @ManyToOne
    @JoinColumn(name = "day_routine_id")
    private DayRoutineEntity dayRoutine;
}
