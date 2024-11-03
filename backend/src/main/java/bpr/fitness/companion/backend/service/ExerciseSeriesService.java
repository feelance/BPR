package bpr.fitness.companion.backend.service;

import bpr.fitness.companion.backend.model.dto.ExerciseSeries;

import java.util.List;

public interface ExerciseSeriesService {
    /**
     * Create ExerciseSeries
     * @param exerciseSeries contains all exercise series details
     * @return ExerciseSeries
     */
    ExerciseSeries createExerciseSeries(ExerciseSeries exerciseSeries);

    /**
     * Get all exercise series
     * @return List<ExerciseSeries>
     */
    List<ExerciseSeries> getAllExerciseSeries();

    /**
     * Get exercise series by id
     * @param id of the exercise series we want to get
     * @return ExerciseSeries
     */
    ExerciseSeries getExerciseSeriesById(Long id);

    /**
     * Update exercise series
     * @param id of the exercise series we want to update
     * @param seriesDetails contains all exercise series details
     * @return ExerciseSeries
     */
    ExerciseSeries updateExerciseSeries(Long id, ExerciseSeries seriesDetails);

    /**
     * Delete exercise series by id
     * @param id of the exercise series we want to delete
     */
    void deleteExerciseSeries(Long id);
}

