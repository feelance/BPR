package bpr.fitness.companion.backend.mapper;

import bpr.fitness.companion.backend.model.dto.ExerciseDayRoutine;
import bpr.fitness.companion.backend.model.entity.ExerciseDayRoutineEntity;

import java.util.List;

public interface ExerciseDayRoutineMapper {

    /**
     * Maps to ExerciseDayRoutineEntity
     * @param routine contains all ExerciseDayRoutine details
     * @return ExerciseDayRoutineEntity
     */
    ExerciseDayRoutineEntity mapToExerciseDayRoutineEntity(ExerciseDayRoutine routine);

    /**
     * Maps to ExerciseDayRoutine list
     * @param routineEntityList list of ExerciseDayRoutineEntity
     * @return List<ExerciseDayRoutine>
     */
    List<ExerciseDayRoutine> mapToExerciseDayRoutineList(List<ExerciseDayRoutineEntity> routineEntityList);

    /**
     * Maps to ExerciseDayRoutine
     * @param routineEntity ExerciseDayRoutineEntity
     * @return ExerciseDayRoutine
     */
    ExerciseDayRoutine mapToExerciseDayRoutine(ExerciseDayRoutineEntity routineEntity);
}

