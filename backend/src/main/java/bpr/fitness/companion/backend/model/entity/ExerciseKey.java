package bpr.fitness.companion.backend.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class ExerciseKey implements Serializable {

    private Long id;
    private String name;

    // Constructors, getters, setters

    public ExerciseKey() {}

    public ExerciseKey(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // equals() and hashCode() methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExerciseKey)) return false;
        ExerciseKey that = (ExerciseKey) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
