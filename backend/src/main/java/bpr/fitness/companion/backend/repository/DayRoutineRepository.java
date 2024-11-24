package bpr.fitness.companion.backend.repository;

import bpr.fitness.companion.backend.model.entity.DayRoutineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DayRoutineRepository extends JpaRepository<DayRoutineEntity, Long> {
    /**
     * Find all day routine entities associated with a specific weekRoutineId
     *
     * @param weekRoutineId the ID of the week routine
     * @return a list of DayRoutineEntity objects associated with the specified weekRoutineId
     */
    List<DayRoutineEntity> findByWeekRoutineId(Long weekRoutineId);

}
