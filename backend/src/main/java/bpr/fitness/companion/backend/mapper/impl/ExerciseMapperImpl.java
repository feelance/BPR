package bpr.fitness.companion.backend.mapper.impl;

import bpr.fitness.companion.backend.mapper.ExerciseDayRoutineMapper;
import bpr.fitness.companion.backend.mapper.ExerciseMapper;
import bpr.fitness.companion.backend.model.dto.Exercise;
import bpr.fitness.companion.backend.model.dto.ExerciseDayRoutine;
import bpr.fitness.companion.backend.model.entity.DayRoutineEntity;
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
            exerciseEntity.setExerciseDayRoutine(mapToExerciseDayRoutineEntity(exercise, exerciseEntity));
        }
        return exerciseEntity;
    }


    private List<ExerciseDayRoutineEntity> mapToExerciseDayRoutineEntity(Exercise exercise, ExerciseEntity exerciseEntity) {
        List<ExerciseDayRoutineEntity> exerciseDayRoutineEntities = null;
        if (exercise != null){
            exerciseDayRoutineEntities = new ArrayList<>();
            ExerciseDayRoutineEntity exerciseDayRoutineEntity = new ExerciseDayRoutineEntity();
            exerciseDayRoutineEntity.setDayRoutine(mapToDayRoutineEntity(exercise.getDayRoutineId()));
            exerciseDayRoutineEntity.setExercise(exerciseEntity);
            exerciseDayRoutineEntities.add(exerciseDayRoutineEntity);
        }
        return exerciseDayRoutineEntities;
    }

    private DayRoutineEntity mapToDayRoutineEntity(Long dayRoutineId) {
        DayRoutineEntity dayRoutineEntity = null;
        if (dayRoutineId != null){
            dayRoutineEntity = new DayRoutineEntity();
            dayRoutineEntity.setId(dayRoutineId);
        }
        return dayRoutineEntity;
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
            exercise.setExerciseDayRoutine(mapToListDayRoutineId(exerciseEntity.getExerciseDayRoutine()));
        }
        return exercise;
    }

    private List<Long> mapToListDayRoutineId(List<ExerciseDayRoutineEntity> exerciseDayRoutine) {
        List<Long> exerciseDayRoutineList = null;
        if(exerciseDayRoutine != null){
            exerciseDayRoutineList = new ArrayList<>();
            for (ExerciseDayRoutineEntity exerciseDayRoutineEntity : exerciseDayRoutine) {
                exerciseDayRoutineList.add(mapToDayRoutineId(exerciseDayRoutineEntity));
            }
        }
        return exerciseDayRoutineList;
    }

    private Long mapToDayRoutineId(ExerciseDayRoutineEntity exerciseDayRoutineEntity) {
       Long dayRoutineId = null;
        if (exerciseDayRoutineEntity != null){
            DayRoutineEntity exerciseDayRoutine  = exerciseDayRoutineEntity.getDayRoutine();
            if (exerciseDayRoutine != null){
                dayRoutineId = exerciseDayRoutine.getId();
            }
        }
        return dayRoutineId;
    }
}
