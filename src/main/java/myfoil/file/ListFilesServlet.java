package myfoil.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import myfoil.db.FileDao;
import myfoil.dto.FileSearchDto;
import myfoil.service.FileService;
import myfoil.db.EntityManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

public class ListFilesServlet extends HttpServlet {

    private final FileDao dao = new FileDao(
            new EntityManager(System.getenv("DATABASE"), System.getenv("ENDPOINT"))
    );

    private final FileService service = new FileService(dao);
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.err.println("Got new list files request");
        String body = null;
        try {
            body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        System.err.println("with body: " + body);

        var searchDto = mapper.readValue(body, FileSearchDto.class);

        resp.setContentType("application/json");
        PrintWriter respWriter = resp.getWriter();
        respWriter.append(mapper.writeValueAsString(service.list(searchDto)));
        respWriter.flush();
    }
}
