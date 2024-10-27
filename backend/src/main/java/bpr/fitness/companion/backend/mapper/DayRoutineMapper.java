package bpr.fitness.companion.backend.mapper;

import bpr.fitness.companion.backend.model.dto.DayRoutine;
import bpr.fitness.companion.backend.model.entity.DayRoutineEntity;

import java.util.List;


public interface DayRoutineMapper {
    DayRoutineEntity mapToDayRoutineEntity(DayRoutine weekRoutine);

    List<DayRoutine> mapToDayRoutineList(List<DayRoutineEntity> weekRoutineEntityList);

    DayRoutine mapToDayRoutine(DayRoutineEntity weekRoutineEntity);
}
