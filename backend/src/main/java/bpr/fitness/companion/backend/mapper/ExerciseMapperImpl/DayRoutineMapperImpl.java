package bpr.fitness.companion.backend.mapper.ExerciseMapperImpl;

import bpr.fitness.companion.backend.mapper.DayRoutineMapper;
import bpr.fitness.companion.backend.model.dto.DayRoutine;
import bpr.fitness.companion.backend.model.entity.DayRoutineEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class DayRoutineMapperImpl implements DayRoutineMapper {
    @Override
    public DayRoutineEntity mapToDayRoutineEntity(DayRoutine weekRoutine) {
        DayRoutineEntity weekRoutineEntity = null;
        if (weekRoutine != null){
            weekRoutineEntity = new DayRoutineEntity();
            weekRoutineEntity.setId(weekRoutine.getId());
            weekRoutineEntity.setFullRoutineId(weekRoutine.getFullRoutineId());
            weekRoutineEntity.setName(weekRoutine.getName());

        }
        return weekRoutineEntity;
    }

    @Override
    public List<DayRoutine> mapToDayRoutineList(List<DayRoutineEntity> weekRoutineEntitieList) {
        List<DayRoutine> weekRoutineList = null;
        if (!CollectionUtils.isEmpty(weekRoutineEntitieList)){
            weekRoutineList = new ArrayList<>();
            for (DayRoutineEntity weekRoutineEntity : weekRoutineEntitieList){
                weekRoutineList.add(mapToDayRoutine(weekRoutineEntity));
            }
        }
        return weekRoutineList;
    }

    @Override
    public DayRoutine mapToDayRoutine(DayRoutineEntity weekRoutineEntity) {
        DayRoutine weekRoutine = null;
        if (weekRoutineEntity != null){
            weekRoutine = new DayRoutine();
            weekRoutine.setId(weekRoutineEntity.getId());
            weekRoutine.setFullRoutineId(weekRoutineEntity.getFullRoutineId());
            weekRoutine.setName(weekRoutineEntity.getName());
        }
        return weekRoutine;
    }
}
