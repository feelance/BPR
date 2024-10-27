package bpr.fitness.companion.backend.service.impl;

import bpr.fitness.companion.backend.exception.DatabaseConstraintException;
import bpr.fitness.companion.backend.exception.WeekRoutineNotFoundException;
import bpr.fitness.companion.backend.mapper.WeekRoutineMapper;
import bpr.fitness.companion.backend.model.dto.WeekRoutine;
import bpr.fitness.companion.backend.model.entity.WeekRoutineEntity;
import bpr.fitness.companion.backend.repository.WeekRoutineRepository;
import bpr.fitness.companion.backend.service.WeekRoutineService;
import bpr.fitness.companion.backend.service.WeekRoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeekRoutineServiceImpl implements WeekRoutineService {

    private final WeekRoutineRepository WeekRoutineRepository;
    private final WeekRoutineMapper mapper;

    @Autowired
    public WeekRoutineServiceImpl(WeekRoutineRepository WeekRoutineRepository, WeekRoutineMapper mapper) {
        this.WeekRoutineRepository = WeekRoutineRepository;
        this.mapper = mapper;
    }

    @Override
    public WeekRoutine createWeekRoutine(WeekRoutine WeekRoutine) throws DatabaseConstraintException {
        try{
            WeekRoutineEntity WeekRoutineEntity = mapper.mapToWeekRoutineEntity(WeekRoutine);
            WeekRoutineEntity = WeekRoutineRepository.save(WeekRoutineEntity);
            return mapper.mapToWeekRoutine(WeekRoutineEntity);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseConstraintException("Name: " + WeekRoutine.getName() + " already exists in in table WeekRoutine");
        }

    }

    @Override
    public List<WeekRoutine> getAllWeekRoutines() {
        List<WeekRoutineEntity> WeekRoutineEntitieList = WeekRoutineRepository.findAll();
        return mapper.mapToWeekRoutineList(WeekRoutineEntitieList);
    }

    @Override
    public WeekRoutine getWeekRoutineById(Long id) {
        Optional<WeekRoutineEntity> optWeekRoutineEntity = WeekRoutineRepository.findById(id);
        WeekRoutine WeekRoutine = null;
        if (optWeekRoutineEntity.isPresent()) {
            WeekRoutineEntity WeekRoutineEntity = optWeekRoutineEntity.get();
             WeekRoutine = mapper.mapToWeekRoutine(WeekRoutineEntity);
        }
        return WeekRoutine;
    }

    @Override
    public WeekRoutine updateWeekRoutine(Long id, WeekRoutine WeekRoutineDetails) throws WeekRoutineNotFoundException{
        Optional <WeekRoutineEntity> optWeekRoutineEntity = WeekRoutineRepository.findById(id);
        WeekRoutineEntity WeekRoutineEntity = null;
        WeekRoutine WeekRoutine = null;
        if (optWeekRoutineEntity.isPresent()){
            WeekRoutineDetails.setId(id);
            WeekRoutineEntity = mapper.mapToWeekRoutineEntity(WeekRoutineDetails);
            WeekRoutineRepository.save(WeekRoutineEntity);
        } else {
            throw new WeekRoutineNotFoundException("WeekRoutine with id " + id + "not found");
        }
        return mapper.mapToWeekRoutine(WeekRoutineEntity);
    }

    @Override
    public void deleteWeekRoutine(Long id) {
        if (!WeekRoutineRepository.existsById(id)) {
            throw new WeekRoutineNotFoundException("WeekRoutine with id " + id + "not found");
        }
        WeekRoutineRepository.deleteById(id);
    }
}