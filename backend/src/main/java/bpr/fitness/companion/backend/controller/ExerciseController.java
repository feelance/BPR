package bpr.fitness.companion.backend.controller;

import bpr.fitness.companion.backend.model.dto.Exercise;
import bpr.fitness.companion.backend.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class will contain all exercise operations
 */
@RestController
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    /**
     * Create a new exercise
     * @param exercise contains all exercise details
     * @return Exercise
     */
    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exercise) {
        Exercise savedExercise = exerciseService.createExercise(exercise);
        return new ResponseEntity<>(savedExercise, HttpStatus.CREATED);
    }

    /**
     * Get  all exercises
     * @return List<Exercise>
     */
    @GetMapping
    public List<Exercise> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    /**
     * Get an exercise by id
     * @param id of the exercise
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long id) {
        Exercise exercise = exerciseService.getExerciseById(id);
        if (exercise != null) {
            return new ResponseEntity<>(exercise, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update an existing exercise
     * @param id of the exercise we want to update
     * @param exerciseDetails object that contains all details that we want to update
     * @return Exercise
     */
    @PutMapping("/{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable Long id, @RequestBody Exercise exerciseDetails) {
        Exercise updatedExercise = exerciseService.updateExercise(id, exerciseDetails);
        return new ResponseEntity<>(updatedExercise, HttpStatus.OK);
    }

    /**
     * Delete an exercise by id
     * @param id of the exercise we want to delete
     * @return confirmation message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return new ResponseEntity<>("Exercise with id " + id + " deleted",HttpStatus.OK);
    }

    /**
     * Get all exercises associated with a specific day routine ID.
     *
     * @param id the ID of the day routine
     * @return a list of exercises
     */
    @GetMapping("/byDayRoutineId/{id}")
    public ResponseEntity<List<Exercise>> getExercisesByDayRoutineId(@PathVariable Long id) {
        List<Exercise> exercises = exerciseService.getExercisesByDayRoutineId(id);
        if (exercises != null && !exercises.isEmpty()) {
            return new ResponseEntity<>(exercises, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}

