package bpr.fitness.companion.backend.controller;

import bpr.fitness.companion.backend.model.dto.ExerciseSeries;
import bpr.fitness.companion.backend.service.ExerciseSeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class will contain all operations related to ExerciseSeries
 */
@RestController
@RequestMapping("/exerciseSeries")
public class ExerciseSeriesController {

    private final ExerciseSeriesService exerciseSeriesService;

    @Autowired
    public ExerciseSeriesController(ExerciseSeriesService exerciseSeriesService) {
        this.exerciseSeriesService = exerciseSeriesService;
    }

    /**
     * Create a new exercise series
     * @param exerciseSeries contains all exercise series details
     * @return ExerciseSeries
     */
    @PostMapping
    public ResponseEntity<ExerciseSeries> createExerciseSeries(@RequestBody ExerciseSeries exerciseSeries) {
        ExerciseSeries savedSeries = exerciseSeriesService.createExerciseSeries(exerciseSeries);
        return new ResponseEntity<>(savedSeries, HttpStatus.CREATED);
    }

    /**
     * Get all exercise series
     * @return List<ExerciseSeries>
     */
    @GetMapping
    public List<ExerciseSeries> getAllExerciseSeries() {
        return exerciseSeriesService.getAllExerciseSeries();
    }

    /**
     * Get an exercise series by id
     * @param id of the exercise series
     * @return ExerciseSeries
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseSeries> getExerciseSeriesById(@PathVariable Long id) {
        ExerciseSeries series = exerciseSeriesService.getExerciseSeriesById(id);
        if (series != null) {
            return new ResponseEntity<>(series, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update an existing exercise series
     * @param id of the exercise series we want to update
     * @param seriesDetails object that contains all details that we want to update
     * @return ExerciseSeries
     */
    @PutMapping("/{id}")
    public ResponseEntity<ExerciseSeries> updateExerciseSeries(@PathVariable Long id, @RequestBody ExerciseSeries seriesDetails) {
        ExerciseSeries updatedSeries = exerciseSeriesService.updateExerciseSeries(id, seriesDetails);
        return new ResponseEntity<>(updatedSeries, HttpStatus.OK);
    }

    /**
     * Delete an exercise series by id
     * @param id of the exercise series we want to delete
     * @return confirmation message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExerciseSeries(@PathVariable Long id) {
        exerciseSeriesService.deleteExerciseSeries(id);
        return new ResponseEntity<>("Exercise series with id " + id + " deleted", HttpStatus.OK);
    }
}

