package bpr.fitness.companion.backend.controller;

import bpr.fitness.companion.backend.model.dto.Exercise;
import bpr.fitness.companion.backend.service.ExerciseService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExerciseController.class)
public class ExerciseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExerciseService exerciseService;

    @Test
    public void testCreateExercise() throws Exception {
        Exercise newExercise = new Exercise();
        newExercise.setId(1L);
        newExercise.setName("Push-ups");
        newExercise.setDescription("Upper body strength");
        newExercise.setCategory("Strength");
        newExercise.setImageUrl("http://example.com/image.jpg");
        newExercise.setExerciseRecords(new HashSet<>(Arrays.asList(101L, 102L)));
        newExercise.setExerciseDayRoutine(Arrays.asList(201L, 202L));

        when(exerciseService.createExercise(Mockito.any(Exercise.class))).thenReturn(newExercise);

        mockMvc.perform(post("/exercises")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "id": 1,
                            "name": "Push-ups",
                            "description": "Upper body strength",
                            "category": "Strength",
                            "imageUrl": "http://example.com/image.jpg",
                            "exerciseRecords": [101, 102],
                            "exerciseDayRoutine": [201, 202]
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Push-ups"))
                .andExpect(jsonPath("$.description").value("Upper body strength"))
                .andExpect(jsonPath("$.category").value("Strength"))
                .andExpect(jsonPath("$.imageUrl").value("http://example.com/image.jpg"))
                .andExpect(jsonPath("$.exerciseRecords[0]").value(101))
                .andExpect(jsonPath("$.exerciseDayRoutine[1]").value(202));
    }

    @Test
    public void testGetAllExercises() throws Exception {
        Exercise exercise1 = new Exercise();
        exercise1.setId(1L);
        exercise1.setName("Push-ups");
        exercise1.setDescription("Upper body strength");
        exercise1.setCategory("Strength");
        exercise1.setImageUrl("http://example.com/image1.jpg");
        exercise1.setExerciseRecords(new HashSet<>(Arrays.asList(101L, 102L)));
        exercise1.setExerciseDayRoutine(Arrays.asList(201L, 202L));

        Exercise exercise2 = new Exercise();
        exercise2.setId(2L);
        exercise2.setName("Squats");
        exercise2.setDescription("Lower body strength");
        exercise2.setCategory("Strength");
        exercise2.setImageUrl("http://example.com/image2.jpg");
        exercise2.setExerciseRecords(new HashSet<>(Arrays.asList(103L, 104L)));
        exercise2.setExerciseDayRoutine(Arrays.asList(203L, 204L));

        List<Exercise> exercises = Arrays.asList(exercise1, exercise2);
        when(exerciseService.getAllExercises()).thenReturn(exercises);

        mockMvc.perform(get("/exercises"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Push-ups"))
                .andExpect(jsonPath("$[0].category").value("Strength"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Squats"))
                .andExpect(jsonPath("$[1].exerciseRecords[1]").value(104));
    }

    @Test
    public void testGetExerciseById() throws Exception {
        Exercise exercise = new Exercise();
        exercise.setId(1L);
        exercise.setName("Push-ups");
        exercise.setDescription("Upper body strength");
        exercise.setCategory("Strength");
        exercise.setImageUrl("http://example.com/image.jpg");
        exercise.setExerciseRecords(new HashSet<>(Arrays.asList(101L, 102L)));
        exercise.setExerciseDayRoutine(Arrays.asList(201L, 202L));

        when(exerciseService.getExerciseById(1L)).thenReturn(exercise);

        mockMvc.perform(get("/exercises/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Push-ups"))
                .andExpect(jsonPath("$.category").value("Strength"))
                .andExpect(jsonPath("$.exerciseRecords[0]").value(101))
                .andExpect(jsonPath("$.exerciseDayRoutine[1]").value(202));
    }

    @Test
    public void testUpdateExercise() throws Exception {
        Exercise updatedExercise = new Exercise();
        updatedExercise.setId(1L);
        updatedExercise.setName("Pull-ups");
        updatedExercise.setDescription("Upper body strength");
        updatedExercise.setCategory("Strength");
        updatedExercise.setImageUrl("http://example.com/image_updated.jpg");
        updatedExercise.setExerciseRecords(new HashSet<>(Arrays.asList(103L, 104L)));
        updatedExercise.setExerciseDayRoutine(Arrays.asList(203L, 204L));

        when(exerciseService.updateExercise(eq(1L), Mockito.any(Exercise.class))).thenReturn(updatedExercise);

        mockMvc.perform(put("/exercises/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "id": 1,
                            "name": "Pull-ups",
                            "description": "Upper body strength",
                            "category": "Strength",
                            "imageUrl": "http://example.com/image_updated.jpg",
                            "exerciseRecords": [103, 104],
                            "exerciseDayRoutine": [203, 204]
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Pull-ups"))
                .andExpect(jsonPath("$.category").value("Strength"))
                .andExpect(jsonPath("$.imageUrl").value("http://example.com/image_updated.jpg"))
                .andExpect(jsonPath("$.exerciseRecords[1]").value(104));
    }

    @Test
    public void testDeleteExercise() throws Exception {
        doNothing().when(exerciseService).deleteExercise(1L);

        mockMvc.perform(delete("/exercises/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Exercise with id 1 deleted"));
    }
}
