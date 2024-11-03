package bpr.fitness.companion.backend.mapper;

import bpr.fitness.companion.backend.model.dto.ExerciseSeries;
import bpr.fitness.companion.backend.model.entity.ExerciseSeriesEntity;

import java.util.List;

public interface ExerciseSeriesMapper {

    /**
     * Maps to ExerciseSeriesEntity
     * @param series contains all ExerciseSeries details
     * @return ExerciseSeriesEntity
     */
    ExerciseSeriesEntity mapToExerciseSeriesEntity(ExerciseSeries series);

    /**
     * Maps to ExerciseSeries list
     * @param seriesEntityList list of ExerciseSeriesEntity
     * @return List<ExerciseSeries>
     */
    List<ExerciseSeries> mapToExerciseSeriesList(List<ExerciseSeriesEntity> seriesEntityList);

    /**
     * Maps to ExerciseSeries
     * @param seriesEntity ExerciseSeriesEntity
     * @return ExerciseSeries
     */
    ExerciseSeries mapToExerciseSeries(ExerciseSeriesEntity seriesEntity);
}

