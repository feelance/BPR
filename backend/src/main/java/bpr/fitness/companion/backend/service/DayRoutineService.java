package bpr.fitness.companion.backend.service;

import bpr.fitness.companion.backend.model.dto.DayRoutine;

import java.util.List;

/**
 * Day routine service
 */
public interface DayRoutineService {
    /**
     * Create day routine
     * @param DayRoutine contains all dayRoutine details
     * @return DayRoutine
     */
    DayRoutine createDayRoutine(DayRoutine DayRoutine);

    /**
     * Get all day routines
     * @return List<DayRoutine>
     */
    List<DayRoutine> getAllDayRoutines();

    /**
     * Get day routine by id
     * @param id of the day routine we want to get
     * @return DayRoutine
     */
    DayRoutine getDayRoutineById(Long id);

    /**
     * Update day routine
     * @param id of the dayRoutine we want to update
     * @param dayRoutineDetails contains all dayRoutine details
     * @return DayRoutine
     */
    DayRoutine updateDayRoutine(Long id, DayRoutine dayRoutineDetails);

    /**
     * Delete day routine
     * @param id of the dayRoutine we want to delete
     */
    void deleteDayRoutine(Long id);
}
