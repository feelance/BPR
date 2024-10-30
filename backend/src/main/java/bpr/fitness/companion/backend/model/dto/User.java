package bpr.fitness.companion.backend.model.dto;

import lombok.Data;
import java.util.List;

@Data
public class User {
    private Long id;
    private String userName;
    private String password;
    private List<Long> exerciseRecordIds;
    private List<Long> weekRoutineIds;
}