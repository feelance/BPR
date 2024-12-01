package bpr.fitness.companion.backend.mapper.impl;

import bpr.fitness.companion.backend.mapper.ExerciseDayRoutineMapper;
import bpr.fitness.companion.backend.mapper.ExerciseMapper;
import bpr.fitness.companion.backend.model.dto.Exercise;
import bpr.fitness.companion.backend.model.dto.ExerciseDayRoutine;
import bpr.fitness.companion.backend.model.entity.ExerciseDayRoutineEntity;
import bpr.fitness.companion.backend.model.entity.ExerciseEntity;
import bpr.fitness.companion.backend.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
            exercise.setExerciseDayRoutine(mapToDayRoutineId(exerciseEntity.getExerciseDayRoutine()));
        }
        return exercise;
    }

    private List<Long> mapToDayRoutineId(List<ExerciseDayRoutineEntity> exerciseDayRoutine) {
        Set<ExerciseDayRoutine> exerciseDayRoutineSet = null;
        if(exerciseDayRoutine != null){
            exerciseDayRoutineSet = new HashSet<>();
            
        }
    }
}
