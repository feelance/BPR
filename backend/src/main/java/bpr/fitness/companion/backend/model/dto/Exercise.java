package bpr.fitness.companion.backend.model.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Exercise {
    private Long id;
    private String name;
    private String description;
    private String category;
    private String image;
}
