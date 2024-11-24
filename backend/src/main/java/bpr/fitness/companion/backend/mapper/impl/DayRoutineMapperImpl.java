package bpr.fitness.companion.backend.mapper.impl;

import bpr.fitness.companion.backend.mapper.DayRoutineMapper;
import bpr.fitness.companion.backend.model.dto.DayRoutine;
import bpr.fitness.companion.backend.model.entity.DayRoutineEntity;
import bpr.fitness.companion.backend.model.entity.WeekRoutineEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
/**
 * Mapper for all dayRoutine operations.
 */
@Service
public class DayRoutineMapperImpl implements DayRoutineMapper {
    /**
     * Maps to dayRoutine Entity
     * @param dayRoutine contains all dayRoutine details
     * @return DayRoutineEntity
     */
    @Override
    public DayRoutineEntity mapToDayRoutineEntity(DayRoutine dayRoutine) {
        DayRoutineEntity dayRoutineEntity = null;
        if (dayRoutine != null){
            dayRoutineEntity = new DayRoutineEntity();
            dayRoutineEntity.setId(dayRoutine.getId());
            dayRoutineEntity.setName(dayRoutine.getName());
            dayRoutineEntity.setWeekRoutine(mapToWeekRoutineEntity(dayRoutine.getWeekRoutineId()));

        }
        return dayRoutineEntity;
    }

    private WeekRoutineEntity mapToWeekRoutineEntity(Long weekRoutineId) {
        if (weekRoutineId != null){
            WeekRoutineEntity weekRoutineEntity = new WeekRoutineEntity();
            weekRoutineEntity.setId(weekRoutineId);
            return weekRoutineEntity;
        }
        return null;
    }

    /**
     * Maps to dayRoutine list
     * @param dayRoutineEntityList List of DayRoutineEntity
     * @return List<DayRoutine>
     */
    @Override
    public List<DayRoutine> mapToDayRoutineList(List<DayRoutineEntity> dayRoutineEntityList) {
        List<DayRoutine> dayRoutineList = null;
        if (!CollectionUtils.isEmpty(dayRoutineEntityList)){
            dayRoutineList = new ArrayList<>();
            for (DayRoutineEntity dayRoutineEntity : dayRoutineEntityList){
                dayRoutineList.add(mapToDayRoutine(dayRoutineEntity));
            }
        }
        return dayRoutineList;
    }

    /**
     * Maps to dayRoutine
     * @param dayRoutineEntity dayRoutine entity
     * @return DayRoutine
     */
    @Override
    public DayRoutine mapToDayRoutine(DayRoutineEntity dayRoutineEntity) {
        DayRoutine dayRoutine = null;
        if (dayRoutineEntity != null){
            dayRoutine = new DayRoutine();
            dayRoutine.setId(dayRoutineEntity.getId());
            dayRoutine.setName(dayRoutineEntity.getName());
        }
        return dayRoutine;
    }
}
