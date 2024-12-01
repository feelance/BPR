package bpr.fitness.companion.backend.service;

import bpr.fitness.companion.backend.model.dto.Exercise;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Optional;

/**
 * Exercise Service
 */
public interface ExerciseService{
    /**
     * Create Exercise
     * @param exercise contains all exercise details
     * @return Exercise
     */
    Exercise createExercise(Exercise exercise);

    /**
     * Get all exercises
     * @return List<Exercise>
     */
    List<Exercise> getAllExercises();

    /**
     * Get exercise by id
     * @param id of the exercise we want to get
     * @return Exercise
     */
    Exercise getExerciseById(Long id);

    /**
     * Update exercise
     * @param id of the exercise we want to update
     * @param exerciseDetails contains all the exercise details
     * @return Exercise
     */
    Exercise updateExercise(Long id, Exercise exerciseDetails);

    /**
     * Delete exercise by id
     * @param id of the exercise we want to delete
     */
    void deleteExercise(Long id);

    /**
     * Get exercises by day routine ID
     * @param dayRoutineId Id of the exercise
     * @return List<Exercise>
     */
    List<Exercise> getExercisesByDayRoutineId(Long dayRoutineId);
}
