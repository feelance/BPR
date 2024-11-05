package bpr.fitness.companion.backend.mapper.impl;

import bpr.fitness.companion.backend.mapper.ExerciseMapper;
import bpr.fitness.companion.backend.model.dto.Exercise;
import bpr.fitness.companion.backend.model.entity.ExerciseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper for all exercise operations
 */
@Service
public class ExerciseMapperImpl implements ExerciseMapper {
    /**
     * Maps to exercise Entity
     * @param exercise contains all exercise details
     * @return ExerciseEntity
     */
    @Override
    public ExerciseEntity mapToExerciseEntity(Exercise exercise) {
        ExerciseEntity exerciseEntity = null;
        if (exercise != null){
            exerciseEntity = new ExerciseEntity();
            exerciseEntity.setId(exercise.getId());
            exerciseEntity.setName(exercise.getName());
            exerciseEntity.setDescription(exercise.getDescription());
            exerciseEntity.setCategory(exercise.getCategory());
            exerciseEntity.setImageUrl(exercise.getImageUrl());
        }
        return exerciseEntity;
    }

    /**
     * Maps to exercise Entity List
     * @param exerciseEntityList list of exerciseEntity
     * @return List<Exercise>
     */
    @Override
    public List<Exercise> mapToExerciseList(List<ExerciseEntity> exerciseEntityList) {
        List<Exercise> exerciseList = null;
        if (!CollectionUtils.isEmpty(exerciseEntityList)){
            exerciseList = new ArrayList<>();
            for (ExerciseEntity exerciseEntity : exerciseEntityList){
                exerciseList.add(mapToExercise(exerciseEntity));
            }
        }
        return exerciseList;
    }

    /**
     * Maps to exercise
     * @param exerciseEntity exerciseEntity
     * @return Exercise
     */
    @Override
    public Exercise mapToExercise(ExerciseEntity exerciseEntity) {
        Exercise exercise = null;
        if (exerciseEntity != null){
            exercise = new Exercise();
            exercise.setId(exerciseEntity.getId());
            exercise.setName(exerciseEntity.getName());
            exercise.setDescription(exerciseEntity.getDescription());
            exercise.setCategory(exerciseEntity.getCategory());
            exercise.setImageUrl(exerciseEntity.getImageUrl());
        }
        return exercise;
    }
}
