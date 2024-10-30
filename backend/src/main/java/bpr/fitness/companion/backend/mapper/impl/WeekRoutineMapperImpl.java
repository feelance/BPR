package bpr.fitness.companion.backend.mapper.impl;

import bpr.fitness.companion.backend.mapper.WeekRoutineMapper;
import bpr.fitness.companion.backend.model.dto.WeekRoutine;
import bpr.fitness.companion.backend.model.entity.WeekRoutineEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeekRoutineMapperImpl implements WeekRoutineMapper {
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

    @Override
    public List<WeekRoutine> mapToWeekRoutineList(List<WeekRoutineEntity> weekRoutineEntitieList) {
        List<WeekRoutine> weekRoutineList = null;
        if (!CollectionUtils.isEmpty(weekRoutineEntitieList)){
            weekRoutineList = new ArrayList<>();
            for (WeekRoutineEntity weekRoutineEntity : weekRoutineEntitieList){
                weekRoutineList.add(mapToWeekRoutine(weekRoutineEntity));
            }
        }
        return weekRoutineList;
    }

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
