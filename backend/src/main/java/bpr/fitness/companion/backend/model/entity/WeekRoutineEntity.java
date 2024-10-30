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
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

}