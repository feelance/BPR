package bpr.fitness.companion.backend.model.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Entity
@Table(name = "DAY_ROUTINE")
public class DayRoutineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    private String notes;

    @OneToMany(
            mappedBy = "dayRoutine",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<ExerciseDayRoutineEntity> exerciseDayRoutines;

    @ManyToOne
    @JoinColumn(name = "week_routine_id")
    private WeekRoutineEntity weekRoutine;



}