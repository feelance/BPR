package bpr.fitness.companion.backend.controller;

import bpr.fitness.companion.backend.model.dto.ExerciseRecord;
import bpr.fitness.companion.backend.service.ExerciseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class will contain all operations related to ExerciseRecord
 */
@RestController
@RequestMapping("/exerciseRecords")
public class ExerciseRecordController {

    private final ExerciseRecordService exerciseRecordService;

    @Autowired
    public ExerciseRecordController(ExerciseRecordService exerciseRecordService) {
        this.exerciseRecordService = exerciseRecordService;
    }

    /**
     * Create a new exercise record
     * @param exerciseRecord contains all exercise record details
     * @return ExerciseRecord
     */
    @PostMapping
    public ResponseEntity<ExerciseRecord> createExerciseRecord(@RequestBody ExerciseRecord exerciseRecord) {
        ExerciseRecord savedRecord = exerciseRecordService.createExerciseRecord(exerciseRecord);
        return new ResponseEntity<>(savedRecord, HttpStatus.CREATED);
    }

    /**
     * Get all exercise records
     * @return List<ExerciseRecord>
     */
    @GetMapping
    public List<ExerciseRecord> getAllExerciseRecords() {
        return exerciseRecordService.getAllExerciseRecords();
    }

    /**
     * Get an exercise record by id
     * @param id of the exercise record
     * @return ExerciseRecord
     */
    @GetMapping("/{id}")
    public ResponseEntity<ExerciseRecord> getExerciseRecordById(@PathVariable Long id) {
        ExerciseRecord record = exerciseRecordService.getExerciseRecordById(id);
        if (record != null) {
            return new ResponseEntity<>(record, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update an existing exercise record
     * @param id of the exercise record we want to update
     * @param recordDetails object that contains all details that we want to update
     * @return ExerciseRecord
     */
    @PutMapping("/{id}")
    public ResponseEntity<ExerciseRecord> updateExerciseRecord(@PathVariable Long id, @RequestBody ExerciseRecord recordDetails) {
        ExerciseRecord updatedRecord = exerciseRecordService.updateExerciseRecord(id, recordDetails);
        return new ResponseEntity<>(updatedRecord, HttpStatus.OK);
    }

    /**
     * Delete an exercise record by id
     * @param id of the exercise record we want to delete
     * @return confirmation message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExerciseRecord(@PathVariable Long id) {
        exerciseRecordService.deleteExerciseRecord(id);
        return new ResponseEntity<>("Exercise record with id " + id + " deleted", HttpStatus.OK);
    }

    /**
     * Get an exercise record by id
     * @param id of the exercise record
     * @return ExerciseRecord
     */
    @GetMapping("/byExerciseId/{id}")
    public List<ExerciseRecord>getExerciseRecordByExerciseIdId(@PathVariable Long id) {
        List<ExerciseRecord> records = exerciseRecordService.getExerciseRecordByExerciseId(id);
            return new ResponseEntity<>(records, HttpStatus.OK).getBody();
    }
}

