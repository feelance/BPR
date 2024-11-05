package bpr.fitness.companion.backend.model.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Data
@Table(name = "WEEK_ROUTINE")
public class WeekRoutineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    private String notes;

    @OneToMany(mappedBy = "weekRoutine", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DayRoutineEntity> dayRoutines;

    @ManyToOne
    @JoinColumn(name = "acount_id", nullable = false)
    private AccountEntity account;

}