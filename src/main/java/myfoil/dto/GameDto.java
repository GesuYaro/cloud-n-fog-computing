package myfoil.dto;

import java.time.Instant;

public class GameDto {

    public GameDto(String createdAt, String name) {
        this.createdAt = createdAt;
        this.name = name;
    }

    public GameDto() {
    }

    private String createdAt;
    private String name;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GameDto{" +
                "createdAt=" + createdAt +
                ", name='" + name + '\'' +
                '}';
    }
}
