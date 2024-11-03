package bpr.fitness.companion.backend.repository;

import bpr.fitness.companion.backend.model.entity.ExerciseEntity;
import bpr.fitness.companion.backend.model.entity.ExerciseSeriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseSeriesRepository extends JpaRepository<ExerciseSeriesEntity, Long> {
}
