package bpr.fitness.companion.backend.mapper.impl;

import bpr.fitness.companion.backend.mapper.ExerciseDayRoutineMapper;
import bpr.fitness.companion.backend.model.dto.ExerciseDayRoutine;
import bpr.fitness.companion.backend.model.entity.ExerciseDayRoutineEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciseDayRoutineMapperImpl implements ExerciseDayRoutineMapper {

    /**
     * Maps to ExerciseDayRoutineEntity
     * @param routine contains all ExerciseDayRoutine details
     * @return ExerciseDayRoutineEntity
     */
    @Override
    public ExerciseDayRoutineEntity mapToExerciseDayRoutineEntity(ExerciseDayRoutine routine) {
        ExerciseDayRoutineEntity entity = null;
        if (routine != null) {
            entity = new ExerciseDayRoutineEntity();
            entity.setId(routine.getId());
            entity.setOrder(routine.getOrder());
        }
        return entity;
    }

    /**
     * Maps to ExerciseDayRoutine
     * @param entity ExerciseDayRoutineEntity
     * @return ExerciseDayRoutine
     */
    @Override
    public ExerciseDayRoutine mapToExerciseDayRoutine(ExerciseDayRoutineEntity entity) {
        ExerciseDayRoutine routine = null;
        if (entity != null) {
            routine = new ExerciseDayRoutine();
            routine.setId(entity.getId());
            routine.setOrder(entity.getOrder());
        }
        return routine;
    }

    /**
     * Maps to ExerciseDayRoutine list
     * @param entityList list of ExerciseDayRoutineEntity
     * @return List<ExerciseDayRoutine>
     */
    @Override
    public List<ExerciseDayRoutine> mapToExerciseDayRoutineList(List<ExerciseDayRoutineEntity> entityList) {
        List<ExerciseDayRoutine> routineList = null;
        if (entityList != null && !entityList.isEmpty()) {
            routineList = entityList.stream()
                    .map(this::mapToExerciseDayRoutine)
                    .collect(Collectors.toList());
        }
        return routineList;
    }
}

