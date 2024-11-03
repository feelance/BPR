package bpr.fitness.companion.backend.controller;

import bpr.fitness.companion.backend.model.dto.ExerciseDayRoutine;
import bpr.fitness.companion.backend.service.ExerciseDayRoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class will contain all operations related to ExerciseDayRoutine
 */
@RestController
@RequestMapping("/exerciseDayRoutines")
public class ExerciseDayRoutineController {

    private final ExerciseDayRoutineService exerciseDayRoutineService;

    @Autowired
    public ExerciseDayRoutineController(ExerciseDayRoutineService exerciseDayRoutineService) {
        this.exerciseDayRoutineService = exerciseDayRoutineService;
    }

    /**
     * Create a new exercise day routine
     * @param exerciseDayRoutine contains all exercise day routine details
     * @return ExerciseDayRoutine
     */
    @PostMapping
    public ResponseEntity<ExerciseDayRoutine> createExerciseDayRoutine(@RequestBody ExerciseDayRoutine exerciseDayRoutine) {
        ExerciseDayRoutine savedRoutine = exerciseDayRoutineService.createExerciseDayRoutine(exerciseDayRoutine);
        return new ResponseEntity<>(savedRoutine, HttpStatus.CREATED);
    }

    /**
     * Get all exercise day routines
     * @return List<ExerciseDayRoutine>
     */
    @GetMapping
    public List<ExerciseDayRoutine> getAllExerciseDayRoutines() {
        return exerciseDayRoutineService.getAllExerciseDayRoutines();
    }

    /**
     * Get an exercise day routine by id
     * @param id of the exercise day routine
     * @return ExerciseDayRoutine
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseDayRoutine> getExerciseDayRoutineById(@PathVariable Long id) {
        ExerciseDayRoutine routine = exerciseDayRoutineService.getExerciseDayRoutineById(id);
        if (routine != null) {
            return new ResponseEntity<>(routine, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update an existing exercise day routine
     * @param id of the exercise day routine we want to update
     * @param routineDetails object that contains all details that we want to update
     * @return ExerciseDayRoutine
     */
    @PutMapping("/{id}")
    public ResponseEntity<ExerciseDayRoutine> updateExerciseDayRoutine(@PathVariable Long id, @RequestBody ExerciseDayRoutine routineDetails) {
        ExerciseDayRoutine updatedRoutine = exerciseDayRoutineService.updateExerciseDayRoutine(id, routineDetails);
        return new ResponseEntity<>(updatedRoutine, HttpStatus.OK);
    }

    /**
     * Delete an exercise day routine by id
     * @param id of the exercise day routine we want to delete
     * @return confirmation message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExerciseDayRoutine(@PathVariable Long id) {
        exerciseDayRoutineService.deleteExerciseDayRoutine(id);
        return new ResponseEntity<>("Exercise day routine with id " + id + " deleted", HttpStatus.OK);
    }
}
