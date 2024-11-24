package bpr.fitness.companion.backend.service.impl;

import bpr.fitness.companion.backend.exception.DatabaseConstraintException;
import bpr.fitness.companion.backend.exception.WeekRoutineNotFoundException;
import bpr.fitness.companion.backend.mapper.WeekRoutineMapper;
import bpr.fitness.companion.backend.model.dto.WeekRoutine;
import bpr.fitness.companion.backend.model.entity.WeekRoutineEntity;
import bpr.fitness.companion.backend.repository.WeekRoutineRepository;
import bpr.fitness.companion.backend.service.WeekRoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Week Routine service
 */
@Service
public class WeekRoutineServiceImpl implements WeekRoutineService {

    private final WeekRoutineRepository weekRoutineRepository;
    private final WeekRoutineMapper mapper;

    @Autowired
    public WeekRoutineServiceImpl(WeekRoutineRepository weekRoutineRepository, WeekRoutineMapper mapper) {
        this.weekRoutineRepository = weekRoutineRepository;
        this.mapper = mapper;
    }

    /**
     * Create week routine
     * @param weekRoutine contains all the details for the weekRoutine
     * @return WeekRoutine
     */
    @Override
    public WeekRoutine createWeekRoutine(WeekRoutine weekRoutine) throws DatabaseConstraintException {
        try{
            WeekRoutineEntity weekRoutineEntity = mapper.mapToWeekRoutineEntity(weekRoutine);
            weekRoutineEntity = weekRoutineRepository.save(weekRoutineEntity);
            return mapper.mapToWeekRoutine(weekRoutineEntity);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseConstraintException("Name: " + weekRoutine.getName() + " already exists in in table WeekRoutine");
        }

    }

    /**
     * Get all week routines
     * @return List<WeekRoutine>
     */
    @Override
    public List<WeekRoutine> getAllWeekRoutines() {
        List<WeekRoutineEntity> WeekRoutineEntityList = weekRoutineRepository.findAll();
        return mapper.mapToWeekRoutineList(WeekRoutineEntityList);
    }

    /**
     * Get week routine by id
     * @param id of the weekRoutine we want to get
     * @return WeekRoutine
     */
    @Override
    public WeekRoutine getWeekRoutineById(Long id) {
        Optional<WeekRoutineEntity> optWeekRoutineEntity = weekRoutineRepository.findById(id);
        WeekRoutine WeekRoutine = null;
        if (optWeekRoutineEntity.isPresent()) {
            WeekRoutineEntity WeekRoutineEntity = optWeekRoutineEntity.get();
             WeekRoutine = mapper.mapToWeekRoutine(WeekRoutineEntity);
        }
        return WeekRoutine;
    }

    /**
     * Update week routine
     * @param id id of the week routine we want to update
     * @param weekRoutineDetails contains all the week routine details
     * @return WeekRoutine
     */
    @Override
    public WeekRoutine updateWeekRoutine(Long id, WeekRoutine weekRoutineDetails) throws WeekRoutineNotFoundException{
        Optional <WeekRoutineEntity> optWeekRoutineEntity = weekRoutineRepository.findById(id);
        WeekRoutineEntity weekRoutineEntity = null;
        if (optWeekRoutineEntity.isPresent()){
            weekRoutineDetails.setId(id);
            weekRoutineEntity = mapper.mapToWeekRoutineEntity(weekRoutineDetails);
            weekRoutineRepository.save(weekRoutineEntity);
        } else {
            throw new WeekRoutineNotFoundException("WeekRoutine with id " + id + "not found");
        }
        return mapper.mapToWeekRoutine(weekRoutineEntity);
    }

    /**
     * Delete week routine
     * @param id of the week routine we want to delete
     */
    @Override
    public void deleteWeekRoutine(Long id) {
        if (!weekRoutineRepository.existsById(id)) {
            throw new WeekRoutineNotFoundException("WeekRoutine with id " + id + "not found");
        }
        weekRoutineRepository.deleteById(id);
    }

    /**
     * Get week routines by user id
     * @param userId user id
     * @return List<WeekRoutine>
     */
    @Override
    public List<WeekRoutine> getWeekRoutinesByUserId(Long userId) {
        List<WeekRoutineEntity> weekRoutineEntities = weekRoutineRepository.findByUserId(userId);
        return mapper.mapToWeekRoutineList(weekRoutineEntities);
    }

}