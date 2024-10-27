package bpr.fitness.companion.backend.service;

import bpr.fitness.companion.backend.model.dto.WeekRoutine;
import bpr.fitness.companion.backend.model.dto.WeekRoutine;

import java.util.List;

public interface WeekRoutineService {
    WeekRoutine createWeekRoutine(WeekRoutine weekRoutine);
    List<WeekRoutine> getAllWeekRoutines();
    WeekRoutine getWeekRoutineById(Long id);
    WeekRoutine updateWeekRoutine(Long id, WeekRoutine weekRoutine);
    void deleteWeekRoutine(Long id);
}
