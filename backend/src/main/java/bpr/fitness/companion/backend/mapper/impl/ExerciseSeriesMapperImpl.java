package bpr.fitness.companion.backend.mapper.impl;

import bpr.fitness.companion.backend.mapper.ExerciseSeriesMapper;
import bpr.fitness.companion.backend.model.dto.ExerciseSeries;
import bpr.fitness.companion.backend.model.entity.ExerciseSeriesEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseSeriesMapperImpl implements ExerciseSeriesMapper {

    /**
     * Maps to ExerciseSeriesEntity
     * @param series contains all ExerciseSeries details
     * @return ExerciseSeriesEntity
     */
    @Override
    public ExerciseSeriesEntity mapToExerciseSeriesEntity(ExerciseSeries series) {
        ExerciseSeriesEntity entity = null;
        if (series != null) {
            entity = new ExerciseSeriesEntity();
            entity.setId(series.getId());
            entity.setWeight(series.getWeight());
            entity.setRepetitions(series.getRepetitions());
        }
        return entity;
    }

    /**
     * Maps to ExerciseSeries
     * @param entity ExerciseSeriesEntity
     * @return ExerciseSeries
     */
    @Override
    public ExerciseSeries mapToExerciseSeries(ExerciseSeriesEntity entity) {
        ExerciseSeries series = null;
        if (entity != null) {
            series = new ExerciseSeries();
            series.setId(entity.getId());
            series.setWeight(entity.getWeight());
            series.setRepetitions(entity.getRepetitions());
        }
        return series;
    }

    /**
     * Maps to ExerciseSeries list
     * @param entityList list of ExerciseSeriesEntity
     * @return List<ExerciseSeries>
     */
    @Override
    public List<ExerciseSeries> mapToExerciseSeriesList(List<ExerciseSeriesEntity> entityList) {
        return entityList.stream()
                .map(this::mapToExerciseSeries)
                .collect(Collectors.toList());
    }
}
