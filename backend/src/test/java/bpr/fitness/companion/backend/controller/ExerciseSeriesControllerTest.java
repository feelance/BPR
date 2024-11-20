package bpr.fitness.companion.backend.controller;
import bpr.fitness.companion.backend.model.dto.ExerciseSeries;
import bpr.fitness.companion.backend.service.ExerciseSeriesService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ExerciseSeriesController.class)
public class ExerciseSeriesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExerciseSeriesService exerciseSeriesService;

    @Test
    public void testCreateExerciseSeries() throws Exception {
        ExerciseSeries newSeries = new ExerciseSeries();
        newSeries.setId(1L);
        newSeries.setWeight(BigDecimal.valueOf(50.5));
        newSeries.setRepetitions(10);
        newSeries.setExerciseId(101L);
        newSeries.setExerciseRecordId(201L);

        when(exerciseSeriesService.createExerciseSeries(Mockito.any(ExerciseSeries.class))).thenReturn(newSeries);

        mockMvc.perform(post("/exerciseSeries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "id": 1,
                            "weight": 50.5,
                            "repetitions": 10,
                            "exerciseId": 101,
                            "exerciseRecordId": 201
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.weight").value(50.5))
                .andExpect(jsonPath("$.repetitions").value(10))
                .andExpect(jsonPath("$.exerciseId").value(101))
                .andExpect(jsonPath("$.exerciseRecordId").value(201));
    }

    @Test
    public void testGetAllExerciseSeries() throws Exception {
        ExerciseSeries series1 = new ExerciseSeries();
        series1.setId(1L);
        series1.setWeight(BigDecimal.valueOf(50.5));
        series1.setRepetitions(10);
        series1.setExerciseId(101L);
        series1.setExerciseRecordId(201L);

        ExerciseSeries series2 = new ExerciseSeries();
        series2.setId(2L);
        series2.setWeight(BigDecimal.valueOf(60.0));
        series2.setRepetitions(8);
        series2.setExerciseId(102L);
        series2.setExerciseRecordId(202L);

        List<ExerciseSeries> seriesList = Arrays.asList(series1, series2);

        when(exerciseSeriesService.getAllExerciseSeries()).thenReturn(seriesList);

        mockMvc.perform(get("/exerciseSeries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].weight").value(50.5))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].weight").value(60.0));
    }

    @Test
    public void testGetExerciseSeriesById() throws Exception {
        ExerciseSeries series = new ExerciseSeries();
        series.setId(1L);
        series.setWeight(BigDecimal.valueOf(50.5));
        series.setRepetitions(10);
        series.setExerciseId(101L);
        series.setExerciseRecordId(201L);

        when(exerciseSeriesService.getExerciseSeriesById(1L)).thenReturn(series);

        mockMvc.perform(get("/exerciseSeries/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.weight").value(50.5))
                .andExpect(jsonPath("$.repetitions").value(10))
                .andExpect(jsonPath("$.exerciseId").value(101))
                .andExpect(jsonPath("$.exerciseRecordId").value(201));
    }

    @Test
    public void testGetExerciseSeriesById_NotFound() throws Exception {
        when(exerciseSeriesService.getExerciseSeriesById(99L)).thenReturn(null);

        mockMvc.perform(get("/exerciseSeries/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateExerciseSeries() throws Exception {
        ExerciseSeries updatedSeries = new ExerciseSeries();
        updatedSeries.setId(1L);
        updatedSeries.setWeight(BigDecimal.valueOf(55.0));
        updatedSeries.setRepetitions(12);
        updatedSeries.setExerciseId(103L);
        updatedSeries.setExerciseRecordId(203L);

        when(exerciseSeriesService.updateExerciseSeries(eq(1L), Mockito.any(ExerciseSeries.class))).thenReturn(updatedSeries);

        mockMvc.perform(put("/exerciseSeries/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "id": 1,
                            "weight": 55.0,
                            "repetitions": 12,
                            "exerciseId": 103,
                            "exerciseRecordId": 203
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.weight").value(55.0))
                .andExpect(jsonPath("$.repetitions").value(12))
                .andExpect(jsonPath("$.exerciseId").value(103))
                .andExpect(jsonPath("$.exerciseRecordId").value(203));
    }

    @Test
    public void testDeleteExerciseSeries() throws Exception {
        doNothing().when(exerciseSeriesService).deleteExerciseSeries(1L);

        mockMvc.perform(delete("/exerciseSeries/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Exercise series with id 1 deleted"));
    }
}
