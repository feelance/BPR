package bpr.fitness.companion.backend.controller;
import bpr.fitness.companion.backend.model.dto.WeekRoutine;
import bpr.fitness.companion.backend.service.WeekRoutineService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WeekRoutineController.class)
public class WeekRoutineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeekRoutineService weekRoutineService;

    @Test
    public void testCreateWeekRoutine() throws Exception {
        WeekRoutine newRoutine = new WeekRoutine();
        newRoutine.setId(1L);
        newRoutine.setName("Weekly Plan");
        newRoutine.setNotes("Focus on strength training");
        newRoutine.setDayRoutines(new HashSet<>(Arrays.asList(101L, 102L)));
        newRoutine.setUserId(501L);

        when(weekRoutineService.createWeekRoutine(Mockito.any(WeekRoutine.class))).thenReturn(newRoutine);

        mockMvc.perform(post("/weekroutines")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "id": 1,
                            "name": "Weekly Plan",
                            "notes": "Focus on strength training",
                            "dayRoutines": [101, 102],
                            "userId": 501
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Weekly Plan"))
                .andExpect(jsonPath("$.notes").value("Focus on strength training"))
                .andExpect(jsonPath("$.userId").value(501))
                .andExpect(jsonPath("$.dayRoutines[0]").value(101));
    }

    @Test
    public void testGetAllWeekRoutines() throws Exception {
        WeekRoutine routine1 = new WeekRoutine();
        routine1.setId(1L);
        routine1.setName("Weekly Plan 1");
        routine1.setNotes("Focus on strength training");
        routine1.setDayRoutines(new HashSet<>(Arrays.asList(101L, 102L)));
        routine1.setUserId(501L);

        WeekRoutine routine2 = new WeekRoutine();
        routine2.setId(2L);
        routine2.setName("Weekly Plan 2");
        routine2.setNotes("Focus on cardio");
        routine2.setDayRoutines(new HashSet<>(Arrays.asList(103L, 104L)));
        routine2.setUserId(502L);

        List<WeekRoutine> routines = Arrays.asList(routine1, routine2);

        when(weekRoutineService.getAllWeekRoutines()).thenReturn(routines);

        mockMvc.perform(get("/weekroutines"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Weekly Plan 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Weekly Plan 2"));
    }

    @Test
    public void testGetWeekRoutineById() throws Exception {
        WeekRoutine routine = new WeekRoutine();
        routine.setId(1L);
        routine.setName("Weekly Plan");
        routine.setNotes("Focus on strength training");
        routine.setDayRoutines(new HashSet<>(Arrays.asList(101L, 102L)));
        routine.setUserId(501L);

        when(weekRoutineService.getWeekRoutineById(1L)).thenReturn(routine);

        mockMvc.perform(get("/weekroutines/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Weekly Plan"))
                .andExpect(jsonPath("$.notes").value("Focus on strength training"))
                .andExpect(jsonPath("$.userId").value(501))
                .andExpect(jsonPath("$.dayRoutines[0]").value(101));
    }

    @Test
    public void testGetWeekRoutineById_NotFound() throws Exception {
        when(weekRoutineService.getWeekRoutineById(99L)).thenReturn(null);

        mockMvc.perform(get("/weekroutines/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateWeekRoutine() throws Exception {
        WeekRoutine updatedRoutine = new WeekRoutine();
        updatedRoutine.setId(1L);
        updatedRoutine.setName("Updated Weekly Plan");
        updatedRoutine.setNotes("Add flexibility training");
        updatedRoutine.setDayRoutines(new HashSet<>(Arrays.asList(103L, 104L)));
        updatedRoutine.setUserId(501L);

        when(weekRoutineService.updateWeekRoutine(eq(1L), Mockito.any(WeekRoutine.class))).thenReturn(updatedRoutine);

        mockMvc.perform(put("/weekroutines/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "id": 1,
                            "name": "Updated Weekly Plan",
                            "notes": "Add flexibility training",
                            "dayRoutines": [103, 104],
                            "userId": 501
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Weekly Plan"))
                .andExpect(jsonPath("$.notes").value("Add flexibility training"))
                .andExpect(jsonPath("$.userId").value(501))
                .andExpect(jsonPath("$.dayRoutines[1]").value(104));
    }

    @Test
    public void testDeleteWeekRoutine() throws Exception {
        doNothing().when(weekRoutineService).deleteWeekRoutine(1L);

        mockMvc.perform(delete("/weekroutines/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("WeekRoutine with id 1 deleted"));
    }

    @Test
    public void testGetWeekRoutinesByUser_Found() throws Exception {
        WeekRoutine routine1 = new WeekRoutine();
        routine1.setId(1L);
        routine1.setName("Weekly Plan 1");
        routine1.setNotes("Focus on strength training");
        routine1.setDayRoutines(new HashSet<>(Arrays.asList(101L, 102L)));
        routine1.setUserId(501L);

        WeekRoutine routine2 = new WeekRoutine();
        routine2.setId(2L);
        routine2.setName("Weekly Plan 2");
        routine2.setNotes("Focus on cardio");
        routine2.setDayRoutines(new HashSet<>(Arrays.asList(103L, 104L)));
        routine2.setUserId(501L);

        List<WeekRoutine> routines = Arrays.asList(routine1, routine2);

        when(weekRoutineService.getWeekRoutinesByUserId(501L)).thenReturn(routines);

        mockMvc.perform(get("/weekroutines/user/501"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Weekly Plan 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Weekly Plan 2"));
    }

    @Test
    public void testGetWeekRoutinesByUser_NotFound() throws Exception {
        when(weekRoutineService.getWeekRoutinesByUserId(501L)).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/weekroutines/user/501"))
                .andExpect(status().isNotFound());
    }

}
