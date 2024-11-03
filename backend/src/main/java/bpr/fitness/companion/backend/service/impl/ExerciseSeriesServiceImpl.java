package bpr.fitness.companion.backend.service.impl;

import bpr.fitness.companion.backend.exception.ExerciseSeriesNotFoundException;
import bpr.fitness.companion.backend.mapper.ExerciseSeriesMapper;
import bpr.fitness.companion.backend.model.dto.ExerciseSeries;
import bpr.fitness.companion.backend.model.entity.ExerciseSeriesEntity;
import bpr.fitness.companion.backend.repository.ExerciseSeriesRepository;
import bpr.fitness.companion.backend.service.ExerciseSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseSeriesServiceImpl implements ExerciseSeriesService {

    private final ExerciseSeriesRepository seriesRepository;
    private final ExerciseSeriesMapper mapper;

    @Autowired
    public ExerciseSeriesServiceImpl(ExerciseSeriesRepository seriesRepository, ExerciseSeriesMapper mapper) {
        this.seriesRepository = seriesRepository;
        this.mapper = mapper;
    }

    @Override
    public ExerciseSeries createExerciseSeries(ExerciseSeries series) {
        ExerciseSeriesEntity seriesEntity = mapper.mapToExerciseSeriesEntity(series);
        seriesEntity = seriesRepository.save(seriesEntity);
        return mapper.mapToExerciseSeries(seriesEntity);
    }

    @Override
    public List<ExerciseSeries> getAllExerciseSeries() {
        List<ExerciseSeriesEntity> seriesEntities = seriesRepository.findAll();
        return mapper.mapToExerciseSeriesList(seriesEntities);
    }

    @Override
    public ExerciseSeries getExerciseSeriesById(Long id) {
        Optional<ExerciseSeriesEntity> optSeriesEntity = seriesRepository.findById(id);
        return optSeriesEntity.map(mapper::mapToExerciseSeries).orElse(null);
    }

    @Override
    public ExerciseSeries updateExerciseSeries(Long id, ExerciseSeries seriesDetails) {
        if (!seriesRepository.existsById(id)) {
            throw new ExerciseSeriesNotFoundException("ExerciseSeries with id " + id + " not found");
        }
        seriesDetails.setId(id);
        ExerciseSeriesEntity seriesEntity = mapper.mapToExerciseSeriesEntity(seriesDetails);
        seriesRepository.save(seriesEntity);
        return mapper.mapToExerciseSeries(seriesEntity);
    }

    @Override
    public void deleteExerciseSeries(Long id) {
        if (!seriesRepository.existsById(id)) {
            throw new ExerciseSeriesNotFoundException("ExerciseSeries with id " + id + " not found");
        }
        seriesRepository.deleteById(id);
    }
}
