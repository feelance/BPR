package bpr.fitness.companion.backend.repository;

import bpr.fitness.companion.backend.model.entity.ExerciseEntity;
import bpr.fitness.companion.backend.model.entity.ExerciseRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExerciseRecordRepository extends JpaRepository<ExerciseRecordEntity, Long> {

    @Query("SELECT e FROM ExerciseRecordEntity e WHERE e.exercise.id = :exerciseId")
    List<ExerciseRecordEntity> findByExerciseId(@Param("exerciseId") Long exerciseId);


}
