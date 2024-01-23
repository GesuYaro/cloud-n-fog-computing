package myfoil.model;

import com.yandex.ydb.table.result.ResultSetReader;

import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

public class Game {

    public static final String TABLE_NAME = "games";

    public Game(UUID id, Instant createdAt, String name) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
    }

    private UUID id;
    private Instant createdAt;
    private String name;

    public void setId(UUID id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
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
        return "Game{" +
                "id=" + id.toString() +
                ", createdAt=" + createdAt +
                ", name='" + name + '\'' +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public static Game fromResultSet(ResultSetReader resultSet) {
        var id = UUID.fromString(resultSet.getColumn("id").getUtf8());
        var createdAt = resultSet.getColumn("created_at").getDate()
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant();
        var name = resultSet.getColumn("name").getUtf8();
        return new Game(id, createdAt, name);
    }
}
