package bpr.fitness.companion.backend.service;

import bpr.fitness.companion.backend.model.dto.Exercise;

import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Optional;

public interface ExerciseService{
    Exercise createExercise(Exercise exercise);
    List<Exercise> getAllExercises();
    Exercise getExerciseById(Long id);
    Exercise updateExercise(Long id, Exercise exerciseDetails);
    void deleteExercise(Long id);
}
