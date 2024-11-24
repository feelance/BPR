package bpr.fitness.companion.backend.controller;

import bpr.fitness.companion.backend.model.dto.WeekRoutine;
import bpr.fitness.companion.backend.service.WeekRoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class will contain all weekRoutine operations
 */
@RestController
@RequestMapping("/weekRoutines")
public class WeekRoutineController {

    private final WeekRoutineService weekRoutineService;

    @Autowired
    public WeekRoutineController(WeekRoutineService weekRoutineService) {
        this.weekRoutineService = weekRoutineService;
    }

    /**
     * Create a new weekRoutine
     * @param weekRoutine contains all week routines parameters
     * @return WeekRoutine
     */
    @PostMapping
    public ResponseEntity<WeekRoutine> createWeekRoutine(@RequestBody WeekRoutine weekRoutine) {
        WeekRoutine savedWeekRoutine = weekRoutineService.createWeekRoutine(weekRoutine);
        return new ResponseEntity<>(savedWeekRoutine, HttpStatus.CREATED);
    }

    /**
     * Get all weekRoutines
     * @return List<WeekRoutine>
     */
    @GetMapping
    public List<WeekRoutine> getAllWeekRoutines() {
        return weekRoutineService.getAllWeekRoutines();
    }

    /**
     * Get a weekRoutine by id
     * @param id of the weekRoutine
     * @return WeekRoutine
     */
    @GetMapping("/{id}")
    public ResponseEntity<WeekRoutine> getWeekRoutineById(@PathVariable Long id) {
        WeekRoutine weekRoutine = weekRoutineService.getWeekRoutineById(id);
        if (weekRoutine != null) {
            return new ResponseEntity<>(weekRoutine, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update an existing weekRoutine
     * @param id of the weekRoutine we want to update
     * @param weekRoutineDetails object that contains all details that we want to update
     * @return WeekRoutine
     */
    @PutMapping("/{id}")
    public ResponseEntity<WeekRoutine> updateWeekRoutine(@PathVariable Long id, @RequestBody WeekRoutine weekRoutineDetails) {
        WeekRoutine updatedWeekRoutine = weekRoutineService.updateWeekRoutine(id, weekRoutineDetails);
        return new ResponseEntity<>(updatedWeekRoutine, HttpStatus.OK);
    }

    /**
     * Delete a weekRoutine by id
     * @param id of the weekRoutine we want to delete
     * @return confirmation message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWeekRoutine(@PathVariable Long id) {
        weekRoutineService.deleteWeekRoutine(id);
        return new ResponseEntity<>("WeekRoutine with id " + id + " deleted",HttpStatus.OK);
    }

    /**
     * Get all week routines for a specific user
     * @param AccountId ID of the user
     * @return List<WeekRoutine>
     */
    @GetMapping("/user/{AccountId}")
    public ResponseEntity<List<WeekRoutine>> getWeekRoutinesByAccountId(@PathVariable Long AccountId) {
        List<WeekRoutine> weekRoutines = weekRoutineService.getWeekRoutinesByAccountId(AccountId);
        if (weekRoutines != null && !weekRoutines.isEmpty()) {
            return new ResponseEntity<>(weekRoutines, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

