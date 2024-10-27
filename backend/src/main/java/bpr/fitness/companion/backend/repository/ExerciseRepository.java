package bpr.fitness.companion.backend.repository;

import bpr.fitness.companion.backend.model.entity.ExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long> {
}
