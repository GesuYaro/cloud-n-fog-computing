package myfoil.model;

import com.yandex.ydb.table.result.ResultSetReader;

import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

public class File {

    public static final String TABLE_NAME = "files";

    private UUID id;
    private Instant createdAt;
    private String description;
    private UUID gameId;
    private String url;
    private UUID typeId;
    private String size;

    public File() {
    }

    public File(UUID id, Instant createdAt, String description, UUID gameId, String url, UUID typeId, String size) {
        this.id = id;
        this.createdAt = createdAt;
        this.description = description;
        this.gameId = gameId;
        this.url = url;
        this.typeId = typeId;
        this.size = size;
    }

    public UUID getTypeId() {
        return typeId;
    }

    public void setTypeId(UUID typeId) {
        this.typeId = typeId;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", description='" + description + '\'' +
                ", gameId=" + gameId +
                ", url='" + url + '\'' +
                ", typeId=" + typeId +
                ", size='" + size + '\'' +
                '}';
    }

    public static File fromResultSet(ResultSetReader resultSet) {
        System.err.println("trying to parse result set");
        var id = UUID.fromString(resultSet.getColumn("id").getUtf8());
        var createdAt = resultSet.getColumn("created_at").getDatetime()
                .atZone(ZoneId.systemDefault())
                .toInstant();
        var description = resultSet.getColumn("description").getUtf8();
        var gameId = UUID.fromString(resultSet.getColumn("game_id").getUtf8());
        var url = resultSet.getColumn("url").getUtf8();
        var typeId = UUID.fromString(resultSet.getColumn("type_id").getUtf8());
        var size = resultSet.getColumn("size").getUtf8();
        var res = new File(id, createdAt, description, gameId, url, typeId, size);
        System.err.println("parsed: " + res);
        return res;
    }
}
