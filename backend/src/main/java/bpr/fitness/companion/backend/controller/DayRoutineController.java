package bpr.fitness.companion.backend.controller;

import bpr.fitness.companion.backend.model.dto.DayRoutine;
import bpr.fitness.companion.backend.service.DayRoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dayroutines")
public class DayRoutineController {

    private final DayRoutineService DayRoutineService;

    @Autowired
    public DayRoutineController(DayRoutineService dayRoutineService) {
        this.DayRoutineService = dayRoutineService;
    }

    // Create a new DayRoutine
    @PostMapping
    public ResponseEntity<DayRoutine> createDayRoutine(@RequestBody DayRoutine DayRoutine) {
        DayRoutine savedDayRoutine = DayRoutineService.createDayRoutine(DayRoutine);
        return new ResponseEntity<>(savedDayRoutine, HttpStatus.CREATED);
    }

    // Get all DayRoutines
    @GetMapping
    public List<DayRoutine> getAllDayRoutines() {
        return DayRoutineService.getAllDayRoutines();
    }

    // Get an DayRoutine by ID
    @GetMapping("/{id}")
    public ResponseEntity<DayRoutine> getDayRoutineById(@PathVariable Long id) {
        DayRoutine DayRoutine = DayRoutineService.getDayRoutineById(id);
        if (DayRoutine != null) {
            return new ResponseEntity<>(DayRoutine, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update an existing DayRoutine
    @PutMapping("/{id}")
    public ResponseEntity<DayRoutine> updateDayRoutine(@PathVariable Long id, @RequestBody DayRoutine DayRoutineDetails) {
        DayRoutine updatedDayRoutine = DayRoutineService.updateDayRoutine(id, DayRoutineDetails);
        return new ResponseEntity<>(updatedDayRoutine, HttpStatus.OK);
    }

    // Delete an DayRoutine
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDayRoutine(@PathVariable Long id) {
        DayRoutineService.deleteDayRoutine(id);
        return new ResponseEntity<>("DayRoutine with id " + id + " deleted", HttpStatus.OK);
    }
}

