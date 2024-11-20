package bpr.fitness.companion.backend.controller;
import bpr.fitness.companion.backend.model.dto.DayRoutine;
import bpr.fitness.companion.backend.service.DayRoutineService;
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

@WebMvcTest(DayRoutineController.class)
public class DayRoutineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DayRoutineService dayRoutineService;

    @Test
    public void testCreateDayRoutine() throws Exception {
        DayRoutine newDayRoutine = new DayRoutine();
        newDayRoutine.setId(1L);
        newDayRoutine.setName("Morning Routine");
        newDayRoutine.setExerciseDayRoutines(new HashSet<>(Arrays.asList(101L, 102L)));
        newDayRoutine.setWeekRoutineId(1001L);

        when(dayRoutineService.createDayRoutine(Mockito.any(DayRoutine.class))).thenReturn(newDayRoutine);

        mockMvc.perform(post("/dayroutines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "id": 1,
                            "name": "Morning Routine",
                            "exerciseDayRoutines": [101, 102],
                            "weekRoutineId": 1001
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Morning Routine"))
                .andExpect(jsonPath("$.weekRoutineId").value(1001))
                .andExpect(jsonPath("$.exerciseDayRoutines[0]").value(101));
    }

    @Test
    public void testGetAllDayRoutines() throws Exception {
        DayRoutine routine1 = new DayRoutine();
        routine1.setId(1L);
        routine1.setName("Morning Routine");
        routine1.setExerciseDayRoutines(new HashSet<>(Arrays.asList(101L, 102L)));
        routine1.setWeekRoutineId(1001L);

        DayRoutine routine2 = new DayRoutine();
        routine2.setId(2L);
        routine2.setName("Evening Routine");
        routine2.setExerciseDayRoutines(new HashSet<>(Arrays.asList(103L, 104L)));
        routine2.setWeekRoutineId(1002L);

        List<DayRoutine> routines = Arrays.asList(routine1, routine2);

        when(dayRoutineService.getAllDayRoutines()).thenReturn(routines);

        mockMvc.perform(get("/dayroutines"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Morning Routine"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Evening Routine"));
    }

    @Test
    public void testGetDayRoutineById() throws Exception {
        DayRoutine routine = new DayRoutine();
        routine.setId(1L);
        routine.setName("Morning Routine");
        routine.setExerciseDayRoutines(new HashSet<>(Arrays.asList(101L, 102L)));
        routine.setWeekRoutineId(1001L);

        when(dayRoutineService.getDayRoutineById(1L)).thenReturn(routine);

        mockMvc.perform(get("/dayroutines/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Morning Routine"))
                .andExpect(jsonPath("$.weekRoutineId").value(1001));
    }

    @Test
    public void testGetDayRoutineById_NotFound() throws Exception {
        when(dayRoutineService.getDayRoutineById(99L)).thenReturn(null);

        mockMvc.perform(get("/dayroutines/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateDayRoutine() throws Exception {
        DayRoutine updatedRoutine = new DayRoutine();
        updatedRoutine.setId(1L);
        updatedRoutine.setName("Updated Morning Routine");
        updatedRoutine.setExerciseDayRoutines(new HashSet<>(Arrays.asList(103L, 104L)));
        updatedRoutine.setWeekRoutineId(1003L);

        when(dayRoutineService.updateDayRoutine(eq(1L), Mockito.any(DayRoutine.class))).thenReturn(updatedRoutine);

        mockMvc.perform(put("/dayroutines/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "id": 1,
                            "name": "Updated Morning Routine",
                            "exerciseDayRoutines": [103, 104],
                            "weekRoutineId": 1003
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Morning Routine"))
                .andExpect(jsonPath("$.weekRoutineId").value(1003))
                .andExpect(jsonPath("$.exerciseDayRoutines[1]").value(104));
    }

    @Test
    public void testDeleteDayRoutine() throws Exception {
        doNothing().when(dayRoutineService).deleteDayRoutine(1L);

        mockMvc.perform(delete("/dayroutines/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("DayRoutine with id 1 deleted"));

    }
}
