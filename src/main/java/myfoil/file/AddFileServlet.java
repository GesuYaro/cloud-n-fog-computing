package myfoil.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import myfoil.db.EntityManager;
import myfoil.db.FileDao;
import myfoil.dto.FileDto;
import myfoil.service.FileService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class AddFileServlet extends HttpServlet {

    private final FileDao dao = new FileDao(
            new EntityManager(System.getenv("DATABASE"), System.getenv("ENDPOINT"))
    );

    private final FileService service = new FileService(dao);
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.err.println("Got new add file request");
        String body = null;
        try {
            body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        System.err.println("with body: " + body);

        var searchDto = mapper.readValue(body, FileDto.class);

        resp.setContentType("application/json");
        service.save(searchDto);
    }
}
