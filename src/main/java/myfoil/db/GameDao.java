package myfoil.db;

import com.yandex.ydb.table.query.Params;
import com.yandex.ydb.table.values.PrimitiveValue;
import myfoil.model.Game;
import myfoil.utils.ThrowingConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameDao implements Dao<Game> {

    private final EntityManager entityManager;

    public GameDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Game> findAll() {
        var games = new ArrayList<Game>();
        entityManager.execute("select * from " + Game.TABLE_NAME, Params.empty(), ThrowingConsumer.unchecked(result -> {
            var resultSet = result.getResultSet(0);
            while (resultSet.next()) {
                games.add(Game.fromResultSet(resultSet));
            }
        }));
        return games;
    }

//    CurrentUtcDateTime()

    @Override
    public void save(Game game) {
        entityManager.execute(
                "declare $id as Utf8;" +
                        "declare $created_at as Date;" +
                        "declare $name as Utf8;" +
                        "insert into games(id, created_at, name) values ($id, $created_at, $name)",
                Params.of("$id", PrimitiveValue.utf8(UUID.randomUUID().toString()),
                        "$created_at", PrimitiveValue.date(game.getCreatedAt()),
                        "$name", PrimitiveValue.utf8(game.getName()))
                );
    }

    @Override
    public void deleteById(UUID id) {
        entityManager.execute(
                "declare $id as Utf8;" +
                        "delete from games where id = $id",
                Params.of("$id", PrimitiveValue.utf8(id.toString())));
    }


}
