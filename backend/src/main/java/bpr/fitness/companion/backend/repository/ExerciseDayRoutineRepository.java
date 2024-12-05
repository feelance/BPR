package bpr.fitness.companion.backend.repository;

import bpr.fitness.companion.backend.model.entity.DayRoutineEntity;
import bpr.fitness.companion.backend.model.entity.ExerciseDayRoutineEntity;
import bpr.fitness.companion.backend.model.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ExerciseDayRoutineRepository extends JpaRepository<ExerciseDayRoutineEntity, Long> {

    @Modifying
    @Transactional
    @Query(value = "delete from EXERCISE_DAY_ROUTINE where DAY_ROUTINE_ID = :dayRoutineId", nativeQuery = true)
    void deleteByDayRoutineId(@Param("dayRoutineId")Long dayRoutineId);
}
