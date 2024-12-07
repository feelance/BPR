package bpr.fitness.companion.backend.mapper.impl;

import bpr.fitness.companion.backend.mapper.ExerciseRecordMapper;
import bpr.fitness.companion.backend.model.dto.ExerciseRecord;
import bpr.fitness.companion.backend.model.entity.AccountEntity;
import bpr.fitness.companion.backend.model.entity.ExerciseDayRoutineEntity;
import bpr.fitness.companion.backend.model.entity.ExerciseEntity;
import bpr.fitness.companion.backend.model.entity.ExerciseRecordEntity;
import bpr.fitness.companion.backend.repository.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Iterator;
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

    @Autowired
    ExerciseRepository exerciseRepository;
    @Override
    public ExerciseRecordEntity mapToExerciseRecordEntity(ExerciseRecord record) {
        ExerciseRecordEntity entity = null;
        if (record != null) {
            entity = new ExerciseRecordEntity();
            entity.setId(record.getId());
            ExerciseEntity exerciseEntity = new ExerciseEntity();
            exerciseEntity.setId(record.getId());
            AccountEntity acc = new AccountEntity();
            acc.setId(1L);
            entity.setExercise(exerciseRepository.findById(record.getExerciseId()).orElse(null));
            entity.setWeight(record.getWeight());
            entity.setRepetitions(record.getRepetitions());
            entity.setAccount(acc);
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
            record.setWeight(entity.getWeight());
            record.setRepetitions(entity.getRepetitions());
            record.setUserId(entity.getAccount().getId());
            record.setDate(Timestamp.valueOf(entity.getCreatedAt()));
            record.setExerciseId(entity.getExercise().getId());
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

