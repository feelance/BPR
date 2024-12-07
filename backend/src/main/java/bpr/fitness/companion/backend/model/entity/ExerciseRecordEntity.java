package bpr.fitness.companion.backend.model.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Data
@Table(name = "EXERCISE_RECORD")
public class ExerciseRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal weight;

    @Column(nullable = false)
    private Integer repetitions;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private ExerciseEntity exercise;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;


    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
