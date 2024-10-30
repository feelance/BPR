package bpr.fitness.companion.backend.mapper;

import bpr.fitness.companion.backend.model.dto.Exercise;
import bpr.fitness.companion.backend.model.entity.ExerciseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Mapper for all exercise operations
 */
public interface ExerciseMapper {
    /**
     * Maps to exercise Entity
     * @param exercise contains all exercise details
     * @return ExerciseEntity
     */
    ExerciseEntity mapToExerciseEntity(Exercise exercise);

    /**
     * Maps to exercise Entity List
     * @param exerciseEntityList list of exerciseEntity
     * @return List<Exercise>
     */
    List<Exercise> mapToExerciseList(List<ExerciseEntity> exerciseEntityList);

    /**
     * Maps to exercise
     * @param exerciseEntity exerciseEntity
     * @return Exercise
     */
    Exercise mapToExercise(ExerciseEntity exerciseEntity);
}
