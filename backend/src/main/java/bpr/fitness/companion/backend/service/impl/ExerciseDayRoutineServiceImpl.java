package bpr.fitness.companion.backend.service.impl;

import bpr.fitness.companion.backend.exception.ExerciseDayRoutineNotFoundException;
import bpr.fitness.companion.backend.mapper.ExerciseDayRoutineMapper;
import bpr.fitness.companion.backend.model.dto.ExerciseDayRoutine;
import bpr.fitness.companion.backend.model.entity.ExerciseDayRoutineEntity;
import bpr.fitness.companion.backend.repository.ExerciseDayRoutineRepository;
import bpr.fitness.companion.backend.service.ExerciseDayRoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseDayRoutineServiceImpl implements ExerciseDayRoutineService {

    private final ExerciseDayRoutineRepository routineRepository;
    private final ExerciseDayRoutineMapper mapper;

    @Autowired
    public ExerciseDayRoutineServiceImpl(ExerciseDayRoutineRepository routineRepository, ExerciseDayRoutineMapper mapper) {
        this.routineRepository = routineRepository;
        this.mapper = mapper;
    }

    @Override
    public ExerciseDayRoutine createExerciseDayRoutine(ExerciseDayRoutine routine) {
        ExerciseDayRoutineEntity routineEntity = mapper.mapToExerciseDayRoutineEntity(routine);
        routineEntity = routineRepository.save(routineEntity);
        return mapper.mapToExerciseDayRoutine(routineEntity);
    }

    @Override
    public List<ExerciseDayRoutine> getAllExerciseDayRoutines() {
        List<ExerciseDayRoutineEntity> routineEntities = routineRepository.findAll();
        return mapper.mapToExerciseDayRoutineList(routineEntities);
    }

    @Override
    public ExerciseDayRoutine getExerciseDayRoutineById(Long id) {
        Optional<ExerciseDayRoutineEntity> optRoutineEntity = routineRepository.findById(id);
        return optRoutineEntity.map(mapper::mapToExerciseDayRoutine).orElse(null);
    }

    @Override
    public ExerciseDayRoutine updateExerciseDayRoutine(Long id, ExerciseDayRoutine routineDetails) {
        if (!routineRepository.existsById(id)) {
            throw new ExerciseDayRoutineNotFoundException("ExerciseDayRoutine with id " + id + " not found");
        }
        routineDetails.setId(id);
        ExerciseDayRoutineEntity routineEntity = mapper.mapToExerciseDayRoutineEntity(routineDetails);
        routineRepository.save(routineEntity);
        return mapper.mapToExerciseDayRoutine(routineEntity);
    }

    @Override
    public void deleteExerciseDayRoutine(Long id) {
        if (!routineRepository.existsById(id)) {
            throw new ExerciseDayRoutineNotFoundException("ExerciseDayRoutine with id " + id + " not found");
        }
        routineRepository.deleteById(id);
    }
}

