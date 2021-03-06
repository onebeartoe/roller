
package org.onebeartoe.electronics.roller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Roberto Marquez
 */
@WebServlet(urlPatterns = {"/logs"}, loadOnStartup = 1)
public class LogsServlet extends HttpServlet
{
    private Logger logger;
    
    private volatile List<String> messages;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        request.setAttribute("messages", messages);
        
        String e = request.getParameter("e");
        if(e != null)
        {
            messages.add(e);
        }
                
        ServletContext context = getServletContext();
        RequestDispatcher rd = context.getRequestDispatcher("/logs/index.jsp");
        rd.forward(request, response);
    }
    
    @Override
    public void init() throws ServletException 
    {
        super.init();
        
        logger = Logger.getLogger(getClass().getName());

// fix this
        messages = new ArrayList();
        messages.add("servlet started" + "<br/>");
    }
}
