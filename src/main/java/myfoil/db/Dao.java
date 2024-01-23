package myfoil.db;

import java.util.List;
import java.util.UUID;

public interface Dao<T> {

    List<T> findAll();

    void save(T t);

    void deleteById(UUID id);

}