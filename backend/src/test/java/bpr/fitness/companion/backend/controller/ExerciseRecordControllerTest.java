package bpr.fitness.companion.backend.controller;
import bpr.fitness.companion.backend.model.dto.ExerciseRecord;
import bpr.fitness.companion.backend.service.ExerciseRecordService;
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

@WebMvcTest(ExerciseRecordController.class)
public class ExerciseRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExerciseRecordService exerciseRecordService;

    @Test
    public void testCreateExerciseRecord() throws Exception {
        ExerciseRecord newRecord = new ExerciseRecord();
        newRecord.setId(1L);
        newRecord.setName("Morning Workout");
        newRecord.setNotes("Focus on cardio");
        newRecord.setDayRoutines(new HashSet<>(Arrays.asList(101L, 102L)));
        newRecord.setUserId(501L);

        when(exerciseRecordService.createExerciseRecord(Mockito.any(ExerciseRecord.class))).thenReturn(newRecord);

        mockMvc.perform(post("/exerciseRecords")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "id": 1,
                            "name": "Morning Workout",
                            "notes": "Focus on cardio",
                            "dayRoutines": [101, 102],
                            "userId": 501
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Morning Workout"))
                .andExpect(jsonPath("$.notes").value("Focus on cardio"))
                .andExpect(jsonPath("$.userId").value(501))
                .andExpect(jsonPath("$.dayRoutines[0]").value(101));
    }

    @Test
    public void testGetAllExerciseRecords() throws Exception {
        ExerciseRecord record1 = new ExerciseRecord();
        record1.setId(1L);
        record1.setName("Morning Workout");
        record1.setNotes("Focus on cardio");
        record1.setDayRoutines(new HashSet<>(Arrays.asList(101L, 102L)));
        record1.setUserId(501L);

        ExerciseRecord record2 = new ExerciseRecord();
        record2.setId(2L);
        record2.setName("Evening Workout");
        record2.setNotes("Strength training");
        record2.setDayRoutines(new HashSet<>(Arrays.asList(103L, 104L)));
        record2.setUserId(502L);

        List<ExerciseRecord> records = Arrays.asList(record1, record2);

        when(exerciseRecordService.getAllExerciseRecords()).thenReturn(records);

        mockMvc.perform(get("/exerciseRecords"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Morning Workout"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Evening Workout"));
    }

    @Test
    public void testGetExerciseRecordById() throws Exception {
        ExerciseRecord record = new ExerciseRecord();
        record.setId(1L);
        record.setName("Morning Workout");
        record.setNotes("Focus on cardio");
        record.setDayRoutines(new HashSet<>(Arrays.asList(101L, 102L)));
        record.setUserId(501L);

        when(exerciseRecordService.getExerciseRecordById(1L)).thenReturn(record);

        mockMvc.perform(get("/exerciseRecords/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Morning Workout"))
                .andExpect(jsonPath("$.notes").value("Focus on cardio"))
                .andExpect(jsonPath("$.userId").value(501))
                .andExpect(jsonPath("$.dayRoutines[0]").value(101));
    }

    @Test
    public void testGetExerciseRecordById_NotFound() throws Exception {
        when(exerciseRecordService.getExerciseRecordById(99L)).thenReturn(null);

        mockMvc.perform(get("/exerciseRecords/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateExerciseRecord() throws Exception {
        ExerciseRecord updatedRecord = new ExerciseRecord();
        updatedRecord.setId(1L);
        updatedRecord.setName("Updated Morning Workout");
        updatedRecord.setNotes("Add stretching");
        updatedRecord.setDayRoutines(new HashSet<>(Arrays.asList(103L, 104L)));
        updatedRecord.setUserId(501L);

        when(exerciseRecordService.updateExerciseRecord(eq(1L), Mockito.any(ExerciseRecord.class)))
                .thenReturn(updatedRecord);

        mockMvc.perform(put("/exerciseRecords/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "id": 1,
                            "name": "Updated Morning Workout",
                            "notes": "Add stretching",
                            "dayRoutines": [103, 104],
                            "userId": 501
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Morning Workout"))
                .andExpect(jsonPath("$.notes").value("Add stretching"))
                .andExpect(jsonPath("$.userId").value(501))
                .andExpect(jsonPath("$.dayRoutines[1]").value(104));
    }

    @Test
    public void testDeleteExerciseRecord() throws Exception {
        doNothing().when(exerciseRecordService).deleteExerciseRecord(1L);

        mockMvc.perform(delete("/exerciseRecords/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Exercise record with id 1 deleted"));
    }
}

