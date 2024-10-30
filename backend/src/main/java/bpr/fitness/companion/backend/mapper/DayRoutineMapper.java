package bpr.fitness.companion.backend.mapper;

import bpr.fitness.companion.backend.model.dto.DayRoutine;
import bpr.fitness.companion.backend.model.entity.DayRoutineEntity;

import java.util.List;

/**
 * Mapper for all dayRoutine operations.
 */
public interface DayRoutineMapper {
    /**
     * Maps DayRoutine
     * @param weekRoutine
     * @return
     */
    DayRoutineEntity mapToDayRoutineEntity(DayRoutine weekRoutine);

    List<DayRoutine> mapToDayRoutineList(List<DayRoutineEntity> weekRoutineEntityList);

    DayRoutine mapToDayRoutine(DayRoutineEntity weekRoutineEntity);
}
