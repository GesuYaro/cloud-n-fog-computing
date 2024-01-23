package myfoil.db;

import com.yandex.ydb.table.query.Params;
import com.yandex.ydb.table.values.PrimitiveValue;
import myfoil.model.File;
import myfoil.utils.ThrowingConsumer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class FileDao {

    private final EntityManager entityManager;


    public FileDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<File> findByGameAndType(UUID gameId, String fileType) {
        var files = new ArrayList<File>();
        var typeIds = new HashMap<String, String>();
        System.err.println("start to fetching from db");
        entityManager.execute(
                "declare $typeName as Utf8;" +
                "select * from file_types",
//                "where name = $typeName",
//                Params.of(
//                        "$typeName", PrimitiveValue.utf8(fileType)
//                ),
                Params.empty(),
                ThrowingConsumer.unchecked(result -> {
                    var resultSet = result.getResultSet(0);
                    while (resultSet.next()) {
                        typeIds.put(
                                resultSet.getColumn("name").getUtf8(),
                                resultSet.getColumn("id").getUtf8()
                        );
                    }
                })
        );
        System.err.println(typeIds);
        var typeId = typeIds.get(fileType);
        entityManager.execute(
                "declare $gameId as Utf8;" +
                        "declare $typeId as Utf8;" +
                        "select * from " + File.TABLE_NAME + " " +
                        "where type_id = $typeId and game_id = $gameId",
                Params.of(
                        "$gameId", PrimitiveValue.utf8(gameId.toString()),
                        "$typeId", PrimitiveValue.utf8(typeId)
                ),
                ThrowingConsumer.unchecked(result -> {
                    var resultSet = result.getResultSet(0);
                    while (resultSet.next()) {
                        System.err.println(resultSet);
                        files.add(File.fromResultSet(resultSet));
                    }
                })
        );
        return files;
    }

    public void save(File file) {
        entityManager.execute(
                "declare $id as Utf8;" +
                        "declare $created_at as Datetime;" +
                        "declare $description as Utf8;" +
                        "declare $game_id as Utf8;" +
                        "declare $url as Utf8;" +
                        "declare $type_id as Utf8;" +
                        "declare $size as Utf8;" +
                        "insert into files(id, created_at, description, game_id, url, type_id, size) " +
                        "values ($id, $created_at, $description, $game_id, $url, $type_id, $size)",
                Params.of(
                        "$id", PrimitiveValue.utf8(UUID.randomUUID().toString()),
                        "$created_at", PrimitiveValue.datetime(file.getCreatedAt()),
                        "$description", PrimitiveValue.utf8(file.getDescription()),
                        "$game_id", PrimitiveValue.utf8(file.getGameId().toString()),
                        "$url", PrimitiveValue.utf8(file.getUrl())
                )
                        .put("$type_id", PrimitiveValue.utf8(file.getTypeId().toString()))
                        .put("$size", PrimitiveValue.utf8(file.getSize()))
        );
    }

    public void deleteById(UUID id) {
        entityManager.execute(
                "declare $id as Utf8;" +
                        "delete from files where id = $id",
                Params.of("$id", PrimitiveValue.utf8(id.toString())));
    }
}
