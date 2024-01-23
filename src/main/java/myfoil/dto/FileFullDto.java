package myfoil.dto;

import java.time.Instant;
import java.util.UUID;

public class FileFullDto {

    private UUID id;
    private Instant createdAt;
    private String description;
    private UUID gameId;
    private String url;
    private UUID typeId;
    private String size;

    public FileFullDto(UUID id, Instant createdAt, String description, UUID gameId, String url, UUID typeId, String size) {
        this.id = id;
        this.createdAt = createdAt;
        this.description = description;
        this.gameId = gameId;
        this.url = url;
        this.typeId = typeId;
        this.size = size;
    }

    public FileFullDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UUID getTypeId() {
        return typeId;
    }

    public void setTypeId(UUID typeId) {
        this.typeId = typeId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
