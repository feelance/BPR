package bpr.fitness.companion.backend.service.impl;

import bpr.fitness.companion.backend.exception.ExerciseRecordNotFoundException;
import bpr.fitness.companion.backend.mapper.ExerciseRecordMapper;
import bpr.fitness.companion.backend.model.dto.ExerciseRecord;
import bpr.fitness.companion.backend.model.entity.ExerciseRecordEntity;
import bpr.fitness.companion.backend.repository.ExerciseRecordRepository;
import bpr.fitness.companion.backend.service.ExerciseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExerciseRecordServiceImpl implements ExerciseRecordService {

    private final ExerciseRecordRepository recordRepository;
    private final ExerciseRecordMapper mapper;

    @Autowired
    public ExerciseRecordServiceImpl(ExerciseRecordRepository recordRepository, ExerciseRecordMapper mapper) {
        this.recordRepository = recordRepository;
        this.mapper = mapper;
    }

    @Override
    public ExerciseRecord createExerciseRecord(ExerciseRecord record) {
        ExerciseRecordEntity recordEntity = mapper.mapToExerciseRecordEntity(record);
        recordEntity = recordRepository.save(recordEntity);
        return mapper.mapToExerciseRecord(recordEntity);
    }

    @Override
    public List<ExerciseRecord> getAllExerciseRecords() {
        List<ExerciseRecordEntity> recordEntities = recordRepository.findAll();
        return mapper.mapToExerciseRecordList(recordEntities);
    }

    @Override
    public ExerciseRecord getExerciseRecordById(Long id) {
        Optional<ExerciseRecordEntity> optRecordEntity = recordRepository.findById(id);
        return optRecordEntity.map(mapper::mapToExerciseRecord).orElse(null);
    }

    @Override
    public ExerciseRecord updateExerciseRecord(Long id, ExerciseRecord recordDetails) {
        if (!recordRepository.existsById(id)) {
            throw new ExerciseRecordNotFoundException("ExerciseRecord with id " + id + " not found");
        }
        recordDetails.setId(id);
        ExerciseRecordEntity recordEntity = mapper.mapToExerciseRecordEntity(recordDetails);
        recordRepository.save(recordEntity);
        return mapper.mapToExerciseRecord(recordEntity);
    }

    @Override
    public void deleteExerciseRecord(Long id) {
        if (!recordRepository.existsById(id)) {
            throw new ExerciseRecordNotFoundException("ExerciseRecord with id " + id + " not found");
        }
        recordRepository.deleteById(id);
    }

    @Override
    public List<ExerciseRecord> getExerciseRecordByExerciseId(Long id) {
        List<ExerciseRecordEntity> recordEntities = recordRepository.findByExerciseId(id);
        return recordEntities.stream()
                .map(mapper::mapToExerciseRecord) // Assuming this maps Entity to DTO
                .collect(Collectors.toList()); // Collect stream into a List
    }

}

