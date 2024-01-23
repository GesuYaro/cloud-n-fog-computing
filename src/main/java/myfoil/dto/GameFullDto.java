package myfoil.dto;

import java.util.UUID;

public class GameFullDto {

    public GameFullDto(UUID id, String createdAt, String name) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
    }

    public GameFullDto() {
    }

    private UUID id;
    private String createdAt;
    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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
}
