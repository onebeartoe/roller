
package org.onebeartoe.electronics.roller;

import org.onebeartoe.roller.hardware.Roller;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.onebeartoe.electronics.roller.ControlCommands.BACKWARD;
import static org.onebeartoe.electronics.roller.ControlCommands.BACKWARD_LEFT;
import static org.onebeartoe.electronics.roller.ControlCommands.BACKWARD_RIGHT;
import static org.onebeartoe.electronics.roller.ControlCommands.FORWARD;
import static org.onebeartoe.electronics.roller.ControlCommands.FORWARD_LEFT;
import static org.onebeartoe.electronics.roller.ControlCommands.FORWARD_RIGHT;

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
    public void destroy() 
    {
        super.destroy();
        
        String message = "on destroy, stopping roller";
        logger.log(Level.INFO, message);
        
        ServletContext sc = getServletContext();
        Roller roller = (Roller) sc.getAttribute(ROLLER_KEY);
        roller.stop();
    }
            
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String p = request.getParameter("message");
        messages.add(p);
        
        if(messages.size() > 50)
        {
            messages.remove(0);
        }
        
        ServletContext sc = getServletContext();
        Roller roller = (Roller) sc.getAttribute(ROLLER_KEY);
        
        String result;
        try
        {
            result = updateControls(roller, p);
            logger.log(Level.INFO, result);
        }
        catch(Exception e)
        {
            result = e.getMessage();
        }        
                
        OutputStream os = response.getOutputStream();
        PrintWriter pw = new PrintWriter(os);
        pw.println(result + "<br/>");
        pw.flush();
        pw.close();
    }
    
    @Override
    public void init() throws ServletException 
    {
        super.init();
        
        logger = Logger.getLogger(getClass().getName());
        
        if(messages == null)
        {
            messages = new ArrayList();
            messages.add("servlet started" + "<br/>");
        }
        
        ServletContext servletContext = getServletContext();
        Roller roller = (Roller) servletContext.getAttribute(ROLLER_KEY);
        if(roller == null)
        {
            roller = new Roller();
            int acceleration = 100;
            roller.setAcceleration(acceleration);
            servletContext.setAttribute(ROLLER_KEY, roller);
        }
    }
    
    private void issueCommand(Roller roller, ControlCommands command)
    {
        switch(command)
        {
            case FORWARD:
            {
                roller.moveForward();
                break;
            }
            case FORWARD_LEFT:
            {
                roller.moveForwardLeft();
                break;
            }
            case FORWARD_RIGHT:
            {
                roller.moveBackwardRight();
                break;
            }
            case BACKWARD:
            {
                roller.moveBackward();
                break;
            }
            case BACKWARD_LEFT:
            {
                roller.moveBackwardLeft();
                break;
            }
            case BACKWARD_RIGHT:
            {
                roller.moveBackwardRight();
                break;
            }
            case STOP:
            {
                roller.stop();
                break;
            }
        }        
    }
    
    private ControlCommands parseCommand(String message)
    {
        ControlCommands command;
        try
        {
            command = ControlCommands.valueOf(message);
        }
        catch(IllegalArgumentException e)
        {
            command = ControlCommands.UNKNOWN;
        } 
        
        return command;
    }
    
    private String updateControls(Roller roller, String message)
    {
        String result;
        
        if(message == null || message.trim().equals(""))
        {
            result = "no message given";
        }
        
        ControlCommands command = parseCommand(message);
        
        if(command == ControlCommands.UNKNOWN)
        {
            result = "unknown command recieved: " + message;
        }
        else
        {            
            issueCommand(roller, command);
            result = command + " processed";
        }
        
        return result;
    }
}
