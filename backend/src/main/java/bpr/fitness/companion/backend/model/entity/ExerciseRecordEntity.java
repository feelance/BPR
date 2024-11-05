package bpr.fitness.companion.backend.model.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Data
@Table(name = "EXERCISE_RECORD")
public class ExerciseRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String notes;

    @OneToMany(mappedBy = "exerciseRecord", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<ExerciseSeriesEntity> day_routines;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;
}
