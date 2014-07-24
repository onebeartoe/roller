
package org.onebeartoe.electronics.roller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Roberto Marquez
 */
@WebServlet(urlPatterns = {"/controller"}, loadOnStartup = 1)
public class DirectionControlsServlet extends HttpServlet
{
    private Logger logger;
    
    private volatile List<String> messages;
    
    public static final String ROLLER_KEY = "roller";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String m = request.getParameter("message");
        messages.add(m);
                
        OutputStream os = response.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        pw.println(m + "._.<br/>");
        pw.flush();
        pw.close();
    }    
    
    @Override
    public void init() throws ServletException 
    {
        super.init();
        
        logger = Logger.getLogger(getClass().getName());
        
        messages = new ArrayList();
        messages.add("servlet started" + "<br/>");
        
        ServletContext servletContext = getServletContext();
        Roller roller = (Roller) servletContext.getAttribute(ROLLER_KEY);
        if(roller == null)
        {
            roller = new Roller();
            servletContext.setAttribute(ROLLER_KEY, roller);
        }
    }
}
