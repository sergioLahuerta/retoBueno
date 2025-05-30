package Controller;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.bson.Document;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@WebServlet("/api/uploadCV")
@MultipartConfig
public class CVServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "/home/ec2-user/retoBueno/CV-candidatos";
    private static final String MONGO_URI = "mongodb+srv://root:Aa12021888.@clustersioprueba.ykpwkjn.mongodb.net/?retryWrites=true&w=majority&appName=ClusterSIOPrueba";

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
        try {

            // Crear carpeta si no existe
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            Part filePart = req.getPart("cv");
            String nombre = req.getParameter("nombre");
            String apellido = req.getParameter("last-name");
            String email = req.getParameter("email");
            String restaurant = req.getParameter("candidacy");
            String job = req.getParameter("job");
            String policy = req.getParameter("policy");

            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String fullPath = UPLOAD_DIR + File.separator + fileName;
            filePart.write(fullPath);

            // Conexión a MongoDB
            try (MongoClient client = MongoClients.create(MONGO_URI)) {
                MongoDatabase db = client.getDatabase("reto_final");
                MongoCollection<Document> col = db.getCollection("candidatos");

                Document doc = new Document("nombre", nombre)
                        .append("apellido", apellido)
                        .append("email", email)
                        .append("restaurant", restaurant)
                        .append("job", job)
                        .append("acepta_politica", policy != null)
                        .append("ruta_cv", fullPath);
                col.insertOne(doc);
            }

            resp.setContentType("application/json");
            resp.getWriter().write("{\"message\": \"CV subido y registrado correctamente\"}");

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"message\": \"Error al procesar el archivo\"}");
        }
    }
}
