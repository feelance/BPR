package bpr.fitness.companion.backend.mapper;

import bpr.fitness.companion.backend.model.dto.Exercise;
import bpr.fitness.companion.backend.model.dto.WeekRoutine;
import bpr.fitness.companion.backend.model.entity.ExerciseEntity;
import bpr.fitness.companion.backend.model.entity.WeekRoutineEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Mapper for all weekRoutine operations
 */
public interface WeekRoutineMapper {
    /**
     * Maps to weekRoutine Entity
     * @param weekRoutine contains all weekRoutine details
     * @return WeekRoutineEntity
     */
    WeekRoutineEntity mapToWeekRoutineEntity(WeekRoutine weekRoutine);

    /**
     * Maps to weekRoutine Entity List
     * @param weekRoutineEntityList list of weekRoutineEntity
     * @return List<WeekRoutine>
     */
    List<WeekRoutine> mapToWeekRoutineList(List<WeekRoutineEntity> weekRoutineEntityList);

    /**
     * Maps to weekRoutine
     * @param weekRoutineEntity weekRoutineEntity
     * @return WeekRoutine
     */
    WeekRoutine mapToWeekRoutine(WeekRoutineEntity weekRoutineEntity);


}
