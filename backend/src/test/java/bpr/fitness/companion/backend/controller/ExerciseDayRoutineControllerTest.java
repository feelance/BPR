package bpr.fitness.companion.backend.controller;
import bpr.fitness.companion.backend.model.dto.ExerciseDayRoutine;
import bpr.fitness.companion.backend.service.ExerciseDayRoutineService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExerciseDayRoutineController.class)
public class ExerciseDayRoutineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExerciseDayRoutineService exerciseDayRoutineService;

    @Test
    public void testCreateExerciseDayRoutine() throws Exception {
        ExerciseDayRoutine newRoutine = new ExerciseDayRoutine();
        newRoutine.setId(1L);
        newRoutine.setExerciseId(101L);
        newRoutine.setDayRoutineId(201L);
        newRoutine.setOrder(1);

        when(exerciseDayRoutineService.createExerciseDayRoutine(Mockito.any(ExerciseDayRoutine.class)))
                .thenReturn(newRoutine);

        mockMvc.perform(post("/exerciseDayRoutines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "id": 1,
                            "exerciseId": 101,
                            "dayRoutineId": 201,
                            "order": 1
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.exerciseId").value(101))
                .andExpect(jsonPath("$.dayRoutineId").value(201))
                .andExpect(jsonPath("$.order").value(1));
    }

    @Test
    public void testGetAllExerciseDayRoutines() throws Exception {
        ExerciseDayRoutine routine1 = new ExerciseDayRoutine();
        routine1.setId(1L);
        routine1.setExerciseId(101L);
        routine1.setDayRoutineId(201L);
        routine1.setOrder(1);

        ExerciseDayRoutine routine2 = new ExerciseDayRoutine();
        routine2.setId(2L);
        routine2.setExerciseId(102L);
        routine2.setDayRoutineId(202L);
        routine2.setOrder(2);

        List<ExerciseDayRoutine> routines = Arrays.asList(routine1, routine2);

        when(exerciseDayRoutineService.getAllExerciseDayRoutines()).thenReturn(routines);

        mockMvc.perform(get("/exerciseDayRoutines"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].exerciseId").value(101))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].exerciseId").value(102));
    }

    @Test
    public void testGetExerciseDayRoutineById() throws Exception {
        ExerciseDayRoutine routine = new ExerciseDayRoutine();
        routine.setId(1L);
        routine.setExerciseId(101L);
        routine.setDayRoutineId(201L);
        routine.setOrder(1);

        when(exerciseDayRoutineService.getExerciseDayRoutineById(1L)).thenReturn(routine);

        mockMvc.perform(get("/exerciseDayRoutines/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.exerciseId").value(101))
                .andExpect(jsonPath("$.dayRoutineId").value(201))
                .andExpect(jsonPath("$.order").value(1));
    }

    @Test
    public void testGetExerciseDayRoutineById_NotFound() throws Exception {
        when(exerciseDayRoutineService.getExerciseDayRoutineById(99L)).thenReturn(null);

        mockMvc.perform(get("/exerciseDayRoutines/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateExerciseDayRoutine() throws Exception {
        ExerciseDayRoutine updatedRoutine = new ExerciseDayRoutine();
        updatedRoutine.setId(1L);
        updatedRoutine.setExerciseId(103L);
        updatedRoutine.setDayRoutineId(203L);
        updatedRoutine.setOrder(2);

        when(exerciseDayRoutineService.updateExerciseDayRoutine(eq(1L), Mockito.any(ExerciseDayRoutine.class)))
                .thenReturn(updatedRoutine);

        mockMvc.perform(put("/exerciseDayRoutines/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "id": 1,
                            "exerciseId": 103,
                            "dayRoutineId": 203,
                            "order": 2
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.exerciseId").value(103))
                .andExpect(jsonPath("$.dayRoutineId").value(203))
                .andExpect(jsonPath("$.order").value(2));
    }

    @Test
    public void testDeleteExerciseDayRoutine() throws Exception {
        doNothing().when(exerciseDayRoutineService).deleteExerciseDayRoutine(1L);

        mockMvc.perform(delete("/exerciseDayRoutines/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Exercise day routine with id 1 deleted"));
    }
}
