package bpr.fitness.companion.backend.repository;

import bpr.fitness.companion.backend.model.entity.ExerciseDayRoutineEntity;
import bpr.fitness.companion.backend.model.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseDayRoutineRepository extends JpaRepository<ExerciseDayRoutineEntity, Long> {
}
