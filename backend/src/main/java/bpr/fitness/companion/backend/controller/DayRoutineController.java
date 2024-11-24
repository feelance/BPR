package bpr.fitness.companion.backend.controller;

import bpr.fitness.companion.backend.model.dto.DayRoutine;
import bpr.fitness.companion.backend.service.DayRoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class will contain all dayRoutine operations
 */
@RestController
@RequestMapping("/dayRoutines")
public class DayRoutineController {

    private final DayRoutineService DayRoutineService;

    @Autowired
    public DayRoutineController(DayRoutineService dayRoutineService) {
        this.DayRoutineService = dayRoutineService;
    }

    /**
     * Create a new dayRoutine
     * @param dayRoutine contains all dayRoutine parameters
     * @return DayRoutine
     */
    @PostMapping
    public ResponseEntity<DayRoutine> createDayRoutine(@RequestBody DayRoutine dayRoutine) {
        DayRoutine savedDayRoutine = DayRoutineService.createDayRoutine(dayRoutine);
        return new ResponseEntity<>(savedDayRoutine, HttpStatus.CREATED);
    }

    /**
     * Get all dayRoutines
     * @return List<DayRoutine>
     */
    @GetMapping
    public List<DayRoutine> getAllDayRoutines() {
        return DayRoutineService.getAllDayRoutines();
    }

    /**
     * Get a dayRoutine by id
     * @param id of the dayRoutine
     * @return DayRoutine
     */
    @GetMapping("/{id}")
    public ResponseEntity<DayRoutine> getDayRoutineById(@PathVariable Long id) {
        DayRoutine DayRoutine = DayRoutineService.getDayRoutineById(id);
        if (DayRoutine != null) {
            return new ResponseEntity<>(DayRoutine, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update an existing dayRoutine
     * @param id  of the dayRoutine we want to update
     * @param DayRoutineDetails object that contains all details that we want to update
     * @return DayRoutine
     */
    @PutMapping("/{id}")
    public ResponseEntity<DayRoutine> updateDayRoutine(@PathVariable Long id, @RequestBody DayRoutine DayRoutineDetails) {
        DayRoutine updatedDayRoutine = DayRoutineService.updateDayRoutine(id, DayRoutineDetails);
        return new ResponseEntity<>(updatedDayRoutine, HttpStatus.OK);
    }

    /**
     * Delete a dayRoutine by id
     * @param id of the dayRoutine we want to delete
     * @return confirmation message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDayRoutine(@PathVariable Long id) {
        DayRoutineService.deleteDayRoutine(id);
        return new ResponseEntity<>("DayRoutine with id " + id + " deleted", HttpStatus.OK);
    }

    /**
     * Get all day routines by weekRoutineId
     *
     * @param weekRoutineId the ID of the week routine
     * @return ResponseEntity containing a list of DayRoutine objects if found, or NOT_FOUND status if no routines are found
     */
    @GetMapping("/byWeekRoutine/{weekRoutineId}")
    public ResponseEntity<List<DayRoutine>> getDayRoutinesByWeekRoutineId(@PathVariable Long weekRoutineId) {
        List<DayRoutine> dayRoutines = DayRoutineService.getDayRoutinesByWeekRoutineId(weekRoutineId);
        if (dayRoutines != null && !dayRoutines.isEmpty()) {
            return new ResponseEntity<>(dayRoutines, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

