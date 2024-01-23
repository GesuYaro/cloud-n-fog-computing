package myfoil.dto;

import java.time.Instant;
import java.util.UUID;

public class FileDto {
    private String description;
    private UUID gameId;
    private String url;
    private UUID typeId;
    private String size;

    public FileDto(String description, UUID gameId, String url, UUID typeId, String size) {
        this.description = description;
        this.gameId = gameId;
        this.url = url;
        this.typeId = typeId;
        this.size = size;
    }

    public FileDto() {
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

    @Override
    public String toString() {
        return "FileDto{" +
                "description='" + description + '\'' +
                ", gameId=" + gameId +
                ", url='" + url + '\'' +
                ", typeId=" + typeId +
                ", size='" + size + '\'' +
                '}';
    }
}
