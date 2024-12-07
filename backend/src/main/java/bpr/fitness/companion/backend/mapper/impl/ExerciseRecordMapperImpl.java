package bpr.fitness.companion.backend.mapper.impl;

import bpr.fitness.companion.backend.mapper.ExerciseRecordMapper;
import bpr.fitness.companion.backend.model.dto.ExerciseRecord;
import bpr.fitness.companion.backend.model.entity.ExerciseDayRoutineEntity;
import bpr.fitness.companion.backend.model.entity.ExerciseEntity;
import bpr.fitness.companion.backend.model.entity.ExerciseRecordEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExerciseRecordMapperImpl implements ExerciseRecordMapper {

    /**
     * Maps to ExerciseRecordEntity
     * @param record contains all ExerciseRecord details
     * @return ExerciseRecordEntity
     */
    @Override
    public ExerciseRecordEntity mapToExerciseRecordEntity(ExerciseRecord record) {
        ExerciseRecordEntity entity = null;
        if (record != null) {
            entity = new ExerciseRecordEntity();
            entity.setId(record.getId());
            entity.setExercise(new ExerciseEntity(record.getExercises().get));
        }
        return entity;
    }

    /**
     * Maps to ExerciseRecord
     * @param entity ExerciseRecordEntity
     * @return ExerciseRecord
     */
    @Override
    public ExerciseRecord mapToExerciseRecord(ExerciseRecordEntity entity) {
        ExerciseRecord record = null;
        if (entity != null) {
            record = new ExerciseRecord();
            record.setId(entity.getId());
            record.setName(entity.getName());
            record.setNotes(entity.getNotes());

        }
        return record;
    }

    /**
     * Maps to ExerciseRecord list
     * @param entityList list of ExerciseRecordEntity
     * @return List<ExerciseRecord>
     */
    @Override
    public List<ExerciseRecord> mapToExerciseRecordList(List<ExerciseRecordEntity> entityList) {
        return entityList.stream()
                .map(this::mapToExerciseRecord)
                .collect(Collectors.toList());
    }
}

