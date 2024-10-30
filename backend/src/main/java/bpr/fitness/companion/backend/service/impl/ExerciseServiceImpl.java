package bpr.fitness.companion.backend.service.impl;

import bpr.fitness.companion.backend.exception.DatabaseConstraintException;
import bpr.fitness.companion.backend.exception.ExerciseNotFoundException;
import bpr.fitness.companion.backend.mapper.ExerciseMapper;
import bpr.fitness.companion.backend.model.dto.Exercise;
import bpr.fitness.companion.backend.model.entity.ExerciseEntity;
import bpr.fitness.companion.backend.repository.ExerciseRepository;
import bpr.fitness.companion.backend.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Exercise Service
 */
@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final ExerciseMapper mapper;


    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ExerciseMapper mapper) {
        this.exerciseRepository = exerciseRepository;
        this.mapper = mapper;
    }

    /**
     * Create Exercise
     * @param exercise contains all exercise details
     * @return Exercise
     */
    @Override
    public Exercise createExercise(Exercise exercise) throws DatabaseConstraintException {
        try{
            ExerciseEntity exerciseEntity = mapper.mapToExerciseEntity(exercise);
            exerciseEntity = exerciseRepository.save(exerciseEntity);
            return mapper.mapToExercise(exerciseEntity);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseConstraintException("Name: " + exercise.getName() + " already exists in in table Exercise");
        }

    }
    /**
     * Get all exercises
     * @return List<Exercise>
     */
    @Override
    public List<Exercise> getAllExercises() {
        List<ExerciseEntity> exerciseEntitieList = exerciseRepository.findAll();
        return mapper.mapToExerciseList(exerciseEntitieList);
    }
    /**
     * Get exercise by id
     * @param id of the exercise we want to get
     * @return Exercise
     */
    @Override
    public Exercise getExerciseById(Long id) {
        Optional<ExerciseEntity> optExerciseEntity = exerciseRepository.findById(id);
        Exercise exercise = null;
        if (optExerciseEntity.isPresent()) {
            ExerciseEntity exerciseEntity = optExerciseEntity.get();
             exercise = mapper.mapToExercise(exerciseEntity);
        }
        return exercise;
    }
    /**
     * Update exercise
     * @param id of the exercise we want to update
     * @param exerciseDetails contains all the exercise details
     * @return Exercise
     */
    @Override
    public Exercise updateExercise(Long id, Exercise exerciseDetails) throws ExerciseNotFoundException{
        Optional <ExerciseEntity> optExerciseEntity = exerciseRepository.findById(id);
        ExerciseEntity exerciseEntity = null;
        if (optExerciseEntity.isPresent()){
            exerciseDetails.setId(id);
            exerciseEntity = mapper.mapToExerciseEntity(exerciseDetails);
            exerciseRepository.save(exerciseEntity);
        } else {
            throw new ExerciseNotFoundException("Exercise with id " + id + "not found");
        }
        return mapper.mapToExercise(exerciseEntity);
    }
    /**
     * Delete exercise by id
     * @param id of the exercise we want to delete
     */
    @Override
    public void deleteExercise(Long id) {
        if (!exerciseRepository.existsById(id)) {
            throw new ExerciseNotFoundException("Exercise with id " + id + "not found");
        }
        exerciseRepository.deleteById(id);
    }
}