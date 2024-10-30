package bpr.fitness.companion.backend.mapper.impl;

import bpr.fitness.companion.backend.mapper.WeekRoutineMapper;
import bpr.fitness.companion.backend.model.dto.WeekRoutine;
import bpr.fitness.companion.backend.model.entity.WeekRoutineEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper for all weekRoutine operations
 */
@Service
public class WeekRoutineMapperImpl implements WeekRoutineMapper {
    /**
     * Maps to weekRoutine Entity
     * @param weekRoutine contains all weekRoutine details
     * @return WeekRoutineEntity
     */
    @Override
    public WeekRoutineEntity mapToWeekRoutineEntity(WeekRoutine weekRoutine) {
        WeekRoutineEntity weekRoutineEntity = null;
        if (weekRoutine != null){
            weekRoutineEntity = new WeekRoutineEntity();
            weekRoutineEntity.setId(weekRoutine.getId());
            weekRoutineEntity.setName(weekRoutine.getName());
            weekRoutineEntity.setNotes(weekRoutine.getNotes());
        }
        return weekRoutineEntity;
    }
    /**
     * Maps to weekRoutine Entity List
     * @param weekRoutineEntityList list of weekRoutineEntity
     * @return List<WeekRoutine>
     */
    @Override
    public List<WeekRoutine> mapToWeekRoutineList(List<WeekRoutineEntity> weekRoutineEntityList) {
        List<WeekRoutine> weekRoutineList = null;
        if (!CollectionUtils.isEmpty(weekRoutineEntityList)){
            weekRoutineList = new ArrayList<>();
            for (WeekRoutineEntity weekRoutineEntity : weekRoutineEntityList){
                weekRoutineList.add(mapToWeekRoutine(weekRoutineEntity));
            }
        }
        return weekRoutineList;
    }

    /**
     * Maps to weekRoutine
     * @param weekRoutineEntity weekRoutineEntity
     * @return WeekRoutine
     */
    @Override
    public WeekRoutine mapToWeekRoutine(WeekRoutineEntity weekRoutineEntity) {
        WeekRoutine weekRoutine = null;
        if (weekRoutineEntity != null){
            weekRoutine = new WeekRoutine();
            weekRoutine.setId(weekRoutineEntity.getId());
            weekRoutine.setName(weekRoutineEntity.getName());
            weekRoutine.setNotes(weekRoutineEntity.getNotes());
        }
        return weekRoutine;
    }
}
