package bpr.fitness.companion.backend.service.impl;

import bpr.fitness.companion.backend.exception.DatabaseConstraintException;
import bpr.fitness.companion.backend.exception.DayRoutineNotFoundException;
import bpr.fitness.companion.backend.mapper.DayRoutineMapper;
import bpr.fitness.companion.backend.model.dto.DayRoutine;
import bpr.fitness.companion.backend.model.entity.DayRoutineEntity;
import bpr.fitness.companion.backend.repository.DayRoutineRepository;
import bpr.fitness.companion.backend.repository.ExerciseDayRoutineRepository;
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

    private final DayRoutineRepository dayRoutineRepository;
    private final DayRoutineMapper mapper;
    private final ExerciseDayRoutineRepository exerciseDayRoutineRepository;

    @Autowired
    public DayRoutineServiceImpl(DayRoutineRepository DayRoutineRepository, DayRoutineMapper mapper, ExerciseDayRoutineRepository exerciseDayRoutineRepository) {
        this.dayRoutineRepository = DayRoutineRepository;
        this.mapper = mapper;
        this.exerciseDayRoutineRepository = exerciseDayRoutineRepository;
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
            DayRoutineEntity = dayRoutineRepository.save(DayRoutineEntity);
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
        List<DayRoutineEntity> DayRoutineEntityList = dayRoutineRepository.findAll();
        return mapper.mapToDayRoutineList(DayRoutineEntityList);
    }

    /**
     * Get day routine by id
     * @param id of the day routine we want to get
     * @return DayRoutine
     */
    @Override
    public DayRoutine getDayRoutineById(Long id) {
        Optional<DayRoutineEntity> optDayRoutineEntity = dayRoutineRepository.findById(id);
        DayRoutine DayRoutine = null;
        if (optDayRoutineEntity.isPresent()) {
            DayRoutineEntity DayRoutineEntity = optDayRoutineEntity.get();
             DayRoutine = mapper.mapToDayRoutine(DayRoutineEntity);
        }
        return DayRoutine;
    }


    @Override
    public DayRoutine updateDayRoutine(Long id, DayRoutine dayRoutineDetails) throws DayRoutineNotFoundException{
        Optional <DayRoutineEntity> optDayRoutineEntity = dayRoutineRepository.findById(id);
        DayRoutineEntity dayRoutineEntity = null;
        if (optDayRoutineEntity.isPresent()){
            dayRoutineEntity = optDayRoutineEntity.get();
            dayRoutineEntity.setName(dayRoutineDetails.getName());
            dayRoutineEntity.setNotes(dayRoutineDetails.getNotes());
            dayRoutineRepository.save(dayRoutineEntity);
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
        if (!dayRoutineRepository.existsById(id)) {
            throw new DayRoutineNotFoundException("DayRoutine with id " + id + "not found");
        }
        try{
            exerciseDayRoutineRepository.deleteByDayRoutineId(id);
            dayRoutineRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            dayRoutineRepository.deleteById(id);
        }
    }

    /**
     * Fetch all day routines associated with a specific weekRoutineId
     *
     * @param weekRoutineId the ID of the week routine
     * @return a list of DayRoutine objects associated with the specified weekRoutineId
     */
    @Override
    public List<DayRoutine> getDayRoutinesByWeekRoutineId(Long weekRoutineId) {
        List<DayRoutineEntity> dayRoutineEntities = dayRoutineRepository.findByWeekRoutineId(weekRoutineId);
        return mapper.mapToDayRoutineList(dayRoutineEntities);
    }

}