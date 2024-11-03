package bpr.fitness.companion.backend.service;

import bpr.fitness.companion.backend.model.dto.ExerciseDayRoutine;

import java.util.List;

public interface ExerciseDayRoutineService {
    /**
     * Create ExerciseDayRoutine
     * @param exerciseDayRoutine contains all exercise day routine details
     * @return ExerciseDayRoutine
     */
    ExerciseDayRoutine createExerciseDayRoutine(ExerciseDayRoutine exerciseDayRoutine);

    /**
     * Get all exercise day routines
     * @return List<ExerciseDayRoutine>
     */
    List<ExerciseDayRoutine> getAllExerciseDayRoutines();

    /**
     * Get exercise day routine by id
     * @param id of the exercise day routine we want to get
     * @return ExerciseDayRoutine
     */
    ExerciseDayRoutine getExerciseDayRoutineById(Long id);

    /**
     * Update exercise day routine
     * @param id of the exercise day routine we want to update
     * @param routineDetails contains all exercise day routine details
     * @return ExerciseDayRoutine
     */
    ExerciseDayRoutine updateExerciseDayRoutine(Long id, ExerciseDayRoutine routineDetails);

    /**
     * Delete exercise day routine by id
     * @param id of the exercise day routine we want to delete
     */
    void deleteExerciseDayRoutine(Long id);
}

