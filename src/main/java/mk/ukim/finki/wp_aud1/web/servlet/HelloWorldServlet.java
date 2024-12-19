package mk.ukim.finki.wp_aud1.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="hello", urlPatterns = "/hello")
public class HelloWorldServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        PrintWriter out=resp.getWriter();
        out.format("<html><head></head><body><h1>Hello World %s </h1></body></html>", username);
        out.flush();
//        PrintWriter writer=resp.getWriter();
//        writer.write("<html><head></head><body><h1>Hi</h1></body</html>");
//        writer.flush();
    }
}
