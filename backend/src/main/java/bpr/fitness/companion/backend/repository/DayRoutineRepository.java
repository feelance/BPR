package bpr.fitness.companion.backend.repository;

import bpr.fitness.companion.backend.model.entity.DayRoutineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayRoutineRepository extends JpaRepository<DayRoutineEntity, Long> {
}
