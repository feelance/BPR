package bpr.fitness.companion.backend.mapper;

import bpr.fitness.companion.backend.model.dto.Exercise;
import bpr.fitness.companion.backend.model.entity.ExerciseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExerciseMapper {
    ExerciseEntity mapToExerciseEntity(Exercise exercise);

    List<Exercise> mapToExerciseList(List<ExerciseEntity> exerciseEntitieList);

    Exercise mapToExercise(ExerciseEntity exerciseEntity);
}
