package myfoil.dto;

import java.util.UUID;

public class FileSearchDto {
    private UUID gameId;
    private String type;

    public FileSearchDto(UUID gameId, String type) {
        this.gameId = gameId;
        this.type = type;
    }

    public FileSearchDto() {
    }

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "FileSearchDto{" +
                "gameId=" + gameId +
                ", type='" + type + '\'' +
                '}';
    }
}
