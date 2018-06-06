package ge.economy.hr.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author invisible
 */
@WebServlet(name = "PDServlet", urlPatterns = {"/PDServlet"})
public class PDServlet extends HttpServlet {

    private static final String dir = "C:\\Program Files\\glassfish4\\glassfish\\domains\\domain1\\docroot\\upload\\";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String file = request.getParameter("file");
        String personalId = request.getParameter("personalId");
        String filePath = dir + "\\" + personalId + file;

        try {
            processFile(response, filePath);
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                response.setStatus(404);
            }
        }
    }

    private void processFile(HttpServletResponse response, String path) throws IOException {
        try {

            response.addHeader("Content-Type", "text/plain");
            InputStream inputStream = new FileInputStream(new File(path));
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, len);
            }
        } catch (IOException ex) {
            throw ex;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
