package myfoil.service;

import myfoil.db.FileDao;
import myfoil.dto.FileFullDto;
import myfoil.dto.FileDto;
import myfoil.dto.FileSearchDto;
import myfoil.model.File;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class FileService {

    private final FileDao fileDao;

    public FileService(FileDao fileDao) {
        this.fileDao = fileDao;
    }

    public void save(FileDto fileDto) {
        System.err.println("trying to add game: " + fileDto);
        fileDao.save(new File(
                null,
                Instant.now(),
                fileDto.getDescription(),
                fileDto.getGameId(),
                fileDto.getUrl(),
                fileDto.getTypeId(),
                fileDto.getSize()
        ));
    }

    public List<FileFullDto> list(FileSearchDto dto) {
        System.err.println("trying to find files via dao");
        return fileDao.findByGameAndType(dto.getGameId(), dto.getType()).stream()
                .map(
                        it -> new FileFullDto(
                                it.getId(),
                                it.getCreatedAt(),
                                it.getDescription(),
                                it.getGameId(),
                                it.getUrl(),
                                it.getTypeId(),
                                it.getSize()
                        )
                )
                .toList();
    }

    public void delete(UUID id) {
        fileDao.deleteById(id);
    }
}
