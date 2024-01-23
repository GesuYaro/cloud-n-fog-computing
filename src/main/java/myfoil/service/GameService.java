package myfoil.service;

import myfoil.db.Dao;
import myfoil.dto.GameDto;
import myfoil.dto.GameFullDto;
import myfoil.model.Game;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class GameService {

    private final Dao<Game> gameDao;

    public GameService(Dao<Game> gameDao) {
        this.gameDao = gameDao;
    }

    public void save(GameDto gameDto) {
        System.err.println("trying to add game: " + gameDto);
        gameDao.save(new Game(null, Instant.parse(gameDto.getCreatedAt()), gameDto.getName()));
    }

    public List<GameFullDto> list() {
        return gameDao.findAll().stream()
                .map(it -> new GameFullDto(it.getId(), it.getCreatedAt().toString(), it.getName()))
                .toList();
    }

    public void delete(UUID id) {
        gameDao.deleteById(id);
    }
}
