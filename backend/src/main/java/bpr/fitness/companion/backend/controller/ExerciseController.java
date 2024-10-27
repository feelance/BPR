package bpr.fitness.companion.backend.controller;

import bpr.fitness.companion.backend.model.dto.Exercise;
import bpr.fitness.companion.backend.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    // Create a new exercise
    @PostMapping
    public ResponseEntity<Exercise> createExercise(@RequestBody Exercise exercise) {
        Exercise savedExercise = exerciseService.createExercise(exercise);
        return new ResponseEntity<>(savedExercise, HttpStatus.CREATED);
    }

    // Get all exercises
    @GetMapping
    public List<Exercise> getAllExercises() {
        return exerciseService.getAllExercises();
    }

    // Get an exercise by ID
    @GetMapping("/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long id) {
        Exercise exercise = exerciseService.getExerciseById(id);
        if (exercise != null) {
            return new ResponseEntity<>(exercise, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update an existing exercise
    @PutMapping("/{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable Long id, @RequestBody Exercise exerciseDetails) {
        Exercise updatedExercise = exerciseService.updateExercise(id, exerciseDetails);
        return new ResponseEntity<>(updatedExercise, HttpStatus.OK);
    }

    // Delete an exercise
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
        return new ResponseEntity<>("Exercise with id " + id + " deleted",HttpStatus.OK);
    }
}

