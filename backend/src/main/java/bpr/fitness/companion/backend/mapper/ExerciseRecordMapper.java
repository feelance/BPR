package bpr.fitness.companion.backend.mapper;

import bpr.fitness.companion.backend.model.dto.ExerciseRecord;
import bpr.fitness.companion.backend.model.entity.ExerciseRecordEntity;

import java.util.List;

public interface ExerciseRecordMapper {

    /**
     * Maps to ExerciseRecordEntity
     * @param record contains all ExerciseRecord details
     * @return ExerciseRecordEntity
     */
    ExerciseRecordEntity mapToExerciseRecordEntity(ExerciseRecord record);

    /**
     * Maps to ExerciseRecord list
     * @param recordEntityList list of ExerciseRecordEntity
     * @return List<ExerciseRecord>
     */
    List<ExerciseRecord> mapToExerciseRecordList(List<ExerciseRecordEntity> recordEntityList);

    /**
     * Maps to ExerciseRecord
     * @param recordEntity ExerciseRecordEntity
     * @return ExerciseRecord
     */
    ExerciseRecord mapToExerciseRecord(ExerciseRecordEntity recordEntity);
}

