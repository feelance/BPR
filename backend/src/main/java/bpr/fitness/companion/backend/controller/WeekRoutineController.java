package bpr.fitness.companion.backend.controller;

import bpr.fitness.companion.backend.model.dto.WeekRoutine;
import bpr.fitness.companion.backend.service.WeekRoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weekroutines")
public class WeekRoutineController {

    private final WeekRoutineService weekRoutineService;

    @Autowired
    public WeekRoutineController(WeekRoutineService weekRoutineService) {
        this.weekRoutineService = weekRoutineService;
    }

    // Create a new weekRoutine
    @PostMapping
    public ResponseEntity<WeekRoutine> createWeekRoutine(@RequestBody WeekRoutine weekRoutine) {
        WeekRoutine savedWeekRoutine = weekRoutineService.createWeekRoutine(weekRoutine);
        return new ResponseEntity<>(savedWeekRoutine, HttpStatus.CREATED);
    }

    // Get all weekRoutines
    @GetMapping
    public List<WeekRoutine> getAllWeekRoutines() {
        return weekRoutineService.getAllWeekRoutines();
    }

    // Get an weekRoutine by ID
    @GetMapping("/{id}")
    public ResponseEntity<WeekRoutine> getWeekRoutineById(@PathVariable Long id) {
        WeekRoutine weekRoutine = weekRoutineService.getWeekRoutineById(id);
        if (weekRoutine != null) {
            return new ResponseEntity<>(weekRoutine, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update an existing weekRoutine
    @PutMapping("/{id}")
    public ResponseEntity<WeekRoutine> updateWeekRoutine(@PathVariable Long id, @RequestBody WeekRoutine weekRoutineDetails) {
        WeekRoutine updatedWeekRoutine = weekRoutineService.updateWeekRoutine(id, weekRoutineDetails);
        return new ResponseEntity<>(updatedWeekRoutine, HttpStatus.OK);
    }

    // Delete an weekRoutine
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWeekRoutine(@PathVariable Long id) {
        weekRoutineService.deleteWeekRoutine(id);
        return new ResponseEntity<>("WeekRoutine with id " + id + "deleted",HttpStatus.NO_CONTENT);
    }
}

