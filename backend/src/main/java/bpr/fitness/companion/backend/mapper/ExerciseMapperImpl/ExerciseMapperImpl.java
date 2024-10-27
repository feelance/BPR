package bpr.fitness.companion.backend.mapper.ExerciseMapperImpl;

import bpr.fitness.companion.backend.mapper.ExerciseMapper;
import bpr.fitness.companion.backend.model.dto.Exercise;
import bpr.fitness.companion.backend.model.entity.ExerciseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseMapperImpl implements ExerciseMapper {
    @Override
    public ExerciseEntity mapToExerciseEntity(Exercise exercise) {
        ExerciseEntity exerciseEntity = null;
        if (exercise != null){
            exerciseEntity = new ExerciseEntity();
            exerciseEntity.setId(exercise.getId());
            exerciseEntity.setName(exercise.getName());
            exerciseEntity.setDescription(exercise.getDescription());
            exerciseEntity.setCategory(exercise.getCategory());
            exerciseEntity.setImageUrl(exercise.getImage());
        }
        return exerciseEntity;
    }

    @Override
    public List<Exercise> mapToExerciseList(List<ExerciseEntity> exerciseEntitieList) {
        List<Exercise> exerciseList = null;
        if (!CollectionUtils.isEmpty(exerciseEntitieList)){
            exerciseList = new ArrayList<>();
            for (ExerciseEntity exerciseEntity : exerciseEntitieList){
                exerciseList.add(mapToExercise(exerciseEntity));
            }
        }
        return exerciseList;
    }

    @Override
    public Exercise mapToExercise(ExerciseEntity exerciseEntity) {
        Exercise exercise = null;
        if (exerciseEntity != null){
            exercise = new Exercise();
            exercise.setId(exerciseEntity.getId());
            exercise.setName(exerciseEntity.getName());
            exercise.setDescription(exerciseEntity.getDescription());
            exercise.setCategory(exerciseEntity.getCategory());
            exercise.setImage(exerciseEntity.getImageUrl());
        }
        return exercise;
    }
}
