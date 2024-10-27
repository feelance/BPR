package bpr.fitness.companion.backend.repository;

import bpr.fitness.companion.backend.model.entity.ExerciseEntity;
import bpr.fitness.companion.backend.model.entity.WeekRoutineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeekRoutineRepository extends JpaRepository<WeekRoutineEntity, Long> {
}
