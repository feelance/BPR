package bpr.fitness.companion.backend.mapper;

import bpr.fitness.companion.backend.model.dto.Exercise;
import bpr.fitness.companion.backend.model.dto.WeekRoutine;
import bpr.fitness.companion.backend.model.entity.ExerciseEntity;
import bpr.fitness.companion.backend.model.entity.WeekRoutineEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface WeekRoutineMapper {
    WeekRoutineEntity mapToWeekRoutineEntity(WeekRoutine weekRoutine);

    List<WeekRoutine> mapToWeekRoutineList(List<WeekRoutineEntity> weekRoutineEntityList);

    WeekRoutine mapToWeekRoutine(WeekRoutineEntity weekRoutineEntity);
}
