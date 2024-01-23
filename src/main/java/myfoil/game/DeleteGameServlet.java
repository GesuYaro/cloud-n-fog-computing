package myfoil.game;

import myfoil.model.Game;
import myfoil.service.GameService;
import myfoil.db.Dao;
import myfoil.db.EntityManager;
import myfoil.db.GameDao;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class DeleteGameServlet extends HttpServlet {

    private final Dao<Game> dao = new GameDao(
            new EntityManager(System.getenv("DATABASE"), System.getenv("ENDPOINT"))
    );
    private final GameService service = new GameService(dao);

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.err.println("Got new delete game request");
        String id = req.getPathInfo();
        if (id == null || id.isBlank()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        System.err.println("with id: " + id);
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        service.delete(uuid);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
