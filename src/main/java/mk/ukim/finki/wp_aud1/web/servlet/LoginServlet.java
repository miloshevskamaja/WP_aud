package mk.ukim.finki.wp_aud1.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp_aud1.model.User;
import mk.ukim.finki.wp_aud1.model.exceptions.InvalidArgumentException;
import mk.ukim.finki.wp_aud1.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.wp_aud1.service.AuthService;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name="LoginServlet", urlPatterns = "/servlet/login")
public class LoginServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final AuthService authService;

    public LoginServlet(SpringTemplateEngine springTemplateEngine, AuthService authService) {
        this.springTemplateEngine = springTemplateEngine;
        this.authService = authService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context=new WebContext(webExchange);

        this.springTemplateEngine.process("login.html",context,resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password= req.getParameter("password");

        User user = null;
        try{
            user=authService.login(username,password);
        }catch (InvalidUserCredentialsException ex){
            IWebExchange webExchange=JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
            WebContext context=new WebContext(webExchange);
            context.setVariable("hasError",true);
            context.setVariable("error",ex.getMessage());
            springTemplateEngine.process("login.html",context,resp.getWriter());
        }

        req.getSession().setAttribute("user",user);
        resp.sendRedirect("/servlet/thymeleaf/category");


    }
}
