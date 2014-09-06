
package org.onebeartoe.roller.hardware;

import com.pi4j.wiringpi.SoftPwm;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Roberto Marquez
 */
public class Motor 
{
    private final int forwardPin;
    
    private final int backwardPin;
    
    private final int PWM_MAX = 100;
    
    private Logger logger;
    
    public Motor(int forwardPin, int backwardPin)
    {
        String name = getClass().getName();
        logger = Logger.getLogger(name);
        
        this.forwardPin = forwardPin;
        this.backwardPin = backwardPin;
        
        int initValue = 0;        
        
        SoftPwm.softPwmCreate(forwardPin, initValue, PWM_MAX);
        SoftPwm.softPwmCreate(backwardPin, initValue, PWM_MAX);
    }
    
    private void move(int pin, int acceleration)
    {
        if(acceleration < 0)
        {
            acceleration = 0;
        }
        
        if(acceleration > PWM_MAX)
        {
            acceleration = PWM_MAX;
        }
    
        logger.log(Level.INFO, "motor move() called: " + pin + ", " + acceleration);
        
        SoftPwm.softPwmWrite(pin, acceleration);
    }
    
    public void moveForward(int acceleration)
    {
        // stop the opposite direction
        move(backwardPin, 0);
        
        move(forwardPin, acceleration);
    }
    
    public void moveBackward(int acceleration)
    {
        move(forwardPin, 0);
        
        move(backwardPin, acceleration);
    }
    
    public void idle()
    {
        SoftPwm.softPwmWrite(forwardPin, 0);
        SoftPwm.softPwmWrite(backwardPin, 0);
    }
    
}
