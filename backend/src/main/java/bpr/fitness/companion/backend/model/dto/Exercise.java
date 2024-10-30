package bpr.fitness.companion.backend.model.dto;


import bpr.fitness.companion.backend.model.entity.ExerciseDayRoutineEntity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Exercise {
    private Long id;
    private String name;
    private String description;
    private String category;
    private String image;

    @OneToMany(mappedBy = "exercise")
    private Set<ExerciseDayRoutineEntity> exercises;
}
