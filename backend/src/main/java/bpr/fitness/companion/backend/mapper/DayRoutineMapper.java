package bpr.fitness.companion.backend.mapper;

import bpr.fitness.companion.backend.model.dto.DayRoutine;
import bpr.fitness.companion.backend.model.entity.DayRoutineEntity;

import java.util.List;

/**
 * Mapper for all dayRoutine operations.
 */
public interface DayRoutineMapper {
    /**
     * Maps to dayRoutine Entity
     * @param dayRoutine contains all dayRoutine details
     * @return DayRoutineEntity
     */
    DayRoutineEntity mapToDayRoutineEntity(DayRoutine dayRoutine);

    /**
     * Maps to dayRoutine list
     * @param dayRoutineEntityList List of DayRoutineEntity
     * @return List<DayRoutine>
     */
    List<DayRoutine> mapToDayRoutineList(List<DayRoutineEntity> dayRoutineEntityList);

    /**
     * Maps to dayRoutine
     * @param dayRoutineEntity dayRoutine entity
     * @return DayRoutine
     */
    DayRoutine mapToDayRoutine(DayRoutineEntity dayRoutineEntity);
}
