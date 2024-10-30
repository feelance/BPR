package bpr.fitness.companion.backend.service;

import bpr.fitness.companion.backend.model.dto.WeekRoutine;
import bpr.fitness.companion.backend.model.dto.WeekRoutine;

import java.util.List;

/**
 * Week Routine service
 */
public interface WeekRoutineService {
    /**
     * Create week routine
     * @param weekRoutine contains all the details for the weekRoutine
     * @return WeekRoutine
     */
    WeekRoutine createWeekRoutine(WeekRoutine weekRoutine);

    /**
     * Get all week routines
     * @return List<WeekRoutine>
     */
    List<WeekRoutine> getAllWeekRoutines();

    /**
     * Get week routine by id
     * @param id of the weekRoutine we want to get
     * @return WeekRoutine
     */
    WeekRoutine getWeekRoutineById(Long id);

    /**
     * Update week routine
     * @param id id of the week routine we want to update
     * @param weekRoutineDetails contains all the week routine details
     * @return WeekRoutine
     */
    WeekRoutine updateWeekRoutine(Long id, WeekRoutine weekRoutineDetails);

    /**
     * Delete week routine
     * @param id of the week routine we want to delete
     */
    void deleteWeekRoutine(Long id);
}
