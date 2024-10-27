package bpr.fitness.companion.backend.service;

import bpr.fitness.companion.backend.model.dto.DayRoutine;

import java.util.List;

public interface DayRoutineService {
    DayRoutine createDayRoutine(DayRoutine DayRoutine);
    List<DayRoutine> getAllDayRoutines();
    DayRoutine getDayRoutineById(Long id);
    DayRoutine updateDayRoutine(Long id, DayRoutine DayRoutine);
    void deleteDayRoutine(Long id);
}
