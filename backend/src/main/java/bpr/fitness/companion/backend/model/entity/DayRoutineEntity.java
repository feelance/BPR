package bpr.fitness.companion.backend.model.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DAY_ROUTINE")
public class DayRoutineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "dayRoutine")
    private Set<ExerciseDayRoutineEntity> exerciseDayRoutines;

    @ManyToOne
    @JoinColumn(name = "week_routine_id")
    private WeekRoutineEntity weekRoutine;



}