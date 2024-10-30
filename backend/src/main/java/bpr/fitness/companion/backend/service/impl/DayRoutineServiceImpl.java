package bpr.fitness.companion.backend.service.impl;

import bpr.fitness.companion.backend.exception.DatabaseConstraintException;
import bpr.fitness.companion.backend.exception.DayRoutineNotFoundException;
import bpr.fitness.companion.backend.mapper.DayRoutineMapper;
import bpr.fitness.companion.backend.model.dto.DayRoutine;
import bpr.fitness.companion.backend.model.entity.DayRoutineEntity;
import bpr.fitness.companion.backend.repository.DayRoutineRepository;
import bpr.fitness.companion.backend.service.DayRoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Day routine service
 */
@Service
public class DayRoutineServiceImpl implements DayRoutineService {

    private final DayRoutineRepository DayRoutineRepository;
    private final DayRoutineMapper mapper;

    @Autowired
    public DayRoutineServiceImpl(DayRoutineRepository DayRoutineRepository, DayRoutineMapper mapper) {
        this.DayRoutineRepository = DayRoutineRepository;
        this.mapper = mapper;
    }
    /**
     * Create day routine
     * @param DayRoutine contains all dayRoutine details
     * @return DayRoutine
     */
    @Override
    public DayRoutine createDayRoutine(DayRoutine DayRoutine) throws DatabaseConstraintException {
        try{
            DayRoutineEntity DayRoutineEntity = mapper.mapToDayRoutineEntity(DayRoutine);
            DayRoutineEntity = DayRoutineRepository.save(DayRoutineEntity);
            return mapper.mapToDayRoutine(DayRoutineEntity);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseConstraintException("Name: " + DayRoutine.getName() + " already exists in in table DayRoutine");
        }

    }

    /**
     * Get all day routines
     * @return List<DayRoutine>
     */
    @Override
    public List<DayRoutine> getAllDayRoutines() {
        List<DayRoutineEntity> DayRoutineEntityList = DayRoutineRepository.findAll();
        return mapper.mapToDayRoutineList(DayRoutineEntityList);
    }

    /**
     * Get day routine by id
     * @param id of the day routine we want to get
     * @return DayRoutine
     */
    @Override
    public DayRoutine getDayRoutineById(Long id) {
        Optional<DayRoutineEntity> optDayRoutineEntity = DayRoutineRepository.findById(id);
        DayRoutine DayRoutine = null;
        if (optDayRoutineEntity.isPresent()) {
            DayRoutineEntity DayRoutineEntity = optDayRoutineEntity.get();
             DayRoutine = mapper.mapToDayRoutine(DayRoutineEntity);
        }
        return DayRoutine;
    }


    @Override
    public DayRoutine updateDayRoutine(Long id, DayRoutine dayRoutineDetails) throws DayRoutineNotFoundException{
        Optional <DayRoutineEntity> optDayRoutineEntity = DayRoutineRepository.findById(id);
        DayRoutineEntity dayRoutineEntity = null;
        if (optDayRoutineEntity.isPresent()){
            dayRoutineDetails.setId(id);
            dayRoutineEntity = mapper.mapToDayRoutineEntity(dayRoutineDetails);
            DayRoutineRepository.save(dayRoutineEntity);
        } else {
            throw new DayRoutineNotFoundException("DayRoutine with id " + id + "not found");
        }
        return mapper.mapToDayRoutine(dayRoutineEntity);
    }
    /**
     * Delete day routine
     * @param id of the dayRoutine we want to delete
     */
    @Override
    public void deleteDayRoutine(Long id) {
        if (!DayRoutineRepository.existsById(id)) {
            throw new DayRoutineNotFoundException("DayRoutine with id " + id + "not found");
        }
        DayRoutineRepository.deleteById(id);
    }
}