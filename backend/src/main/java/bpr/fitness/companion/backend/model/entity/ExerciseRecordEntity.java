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

    @OneToMany(mappedBy = "exerciseRecord", cascade = CascadeType.ALL, orphanRemoval = false)
    private Set<ExerciseEntity> exercise;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;
}
