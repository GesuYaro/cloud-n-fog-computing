package myfoil.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import myfoil.db.Dao;
import myfoil.db.EntityManager;
import myfoil.db.GameDao;
import myfoil.model.Game;
import myfoil.service.GameService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ListGamesServlet extends HttpServlet {

    private final Dao<Game> dao = new GameDao(
            new EntityManager(System.getenv("DATABASE"), System.getenv("ENDPOINT"))
    );
    private final GameService service = new GameService(dao);
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        PrintWriter respWriter = resp.getWriter();
        respWriter.append(mapper.writeValueAsString(service.list()));
        respWriter.flush();
    }

}
