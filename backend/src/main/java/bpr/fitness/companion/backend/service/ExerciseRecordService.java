package bpr.fitness.companion.backend.service;

import bpr.fitness.companion.backend.model.dto.ExerciseRecord;

import java.util.List;

public interface ExerciseRecordService {
    /**
     * Create ExerciseRecord
     * @param exerciseRecord contains all exercise record details
     * @return ExerciseRecord
     */
    ExerciseRecord createExerciseRecord(ExerciseRecord exerciseRecord);

    /**
     * Get all exercise records
     * @return List<ExerciseRecord>
     */
    List<ExerciseRecord> getAllExerciseRecords();

    /**
     * Get exercise record by id
     * @param id of the exercise record we want to get
     * @return ExerciseRecord
     */
    ExerciseRecord getExerciseRecordById(Long id);

    /**
     * Update exercise record
     * @param id of the exercise record we want to update
     * @param recordDetails contains all exercise record details
     * @return ExerciseRecord
     */
    ExerciseRecord updateExerciseRecord(Long id, ExerciseRecord recordDetails);

    /**
     * Delete exercise record by id
     * @param id of the exercise record we want to delete
     */
    void deleteExerciseRecord(Long id);
}

