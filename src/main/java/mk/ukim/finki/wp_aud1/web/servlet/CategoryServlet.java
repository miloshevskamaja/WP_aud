package mk.ukim.finki.wp_aud1.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp_aud1.service.CategoryService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

@WebServlet(name="category-servlet", urlPatterns = "/servlet/category")
public class CategoryServlet  extends HttpServlet {

  private final CategoryService categoryService;

  public CategoryServlet(CategoryService categoryService){
      this.categoryService=categoryService;
  }






    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ipAddress=req.getRemoteAddr();
        String clientAgent=req.getHeader("User-Agent");
        PrintWriter out=resp.getWriter();
        out.format("<html><head></head><body><h3>Category List</h3><h3>User info</h3>IP Address: %s Client Agent: %s <ul>",ipAddress,clientAgent);
        this.categoryService.listCategories().stream().forEach(r->
                out.format("<li>%s (%s)</li>",r.getName(),r.getDescription()));
        out.println("</ul>");

//        out.println("<form method='POST' action='/servlet/category'>\n" +
//                "\t<label for='name'>Name:</label>\n" +
//                "\t<input id='name' type='text' name='name'/>\n" +
//                "\t<label for='description'>Description:</label>\n" +
//                "\t<input id='description' type='text' name='description'/>\n" +
//                "\t<input type='submit' value='Submit'/>\n" +
//                "</form></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryName=req.getParameter("name");
        String description=req.getParameter("description");
        categoryService.create(categoryName,description);
        resp.sendRedirect("/servlet/category");
    }
}

