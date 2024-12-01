package bpr.fitness.companion.backend.repository;

import bpr.fitness.companion.backend.model.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {
    @Query("SELECT e FROM ExerciseEntity e JOIN e.exerciseDayRoutine edr WHERE edr.dayRoutine.id = :dayRoutineId")
    List<ExerciseEntity> findByDayRoutineId(@Param("dayRoutineId") Long dayRoutineId);
}
