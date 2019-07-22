package fr.wcs.jedi.controller;

import fr.wcs.jedi.model.Jedi;
import fr.wcs.jedi.model.TestJDBC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "Servlet",urlPatterns = {"/jedis"})
public class JediFinderServlet extends HttpServlet {

    private final Logger slf4jLogger = LoggerFactory.getLogger(JediFinderServlet.class);

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        slf4jLogger.info("Calling JDBC");
        List<Jedi> jediList = new ArrayList<>();
        TestJDBC jdbc= new TestJDBC();
        jediList= jdbc.execRequete(request);
        if (false) response.sendError(500);  // Si pb retourne erreur serveur
        request.setAttribute("jedilist",jediList);
        request.getRequestDispatcher("/jedis.jsp").forward(request,response);
        //response.getOutputStream().println("coucou");   // Affiche du texte dans la page web
    }
}
