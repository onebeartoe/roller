
package org.onebeartoe.roller.hardware;

import com.pi4j.wiringpi.Gpio;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Instances of this class represent a WiFi controlled car.
 * 
 * The roller starts off with an acceleration of 0; don't for get to 
 * call #setAcceleration() with something appropriate.
 * 
 * @author Roberto Marquez
 */
public class Roller 
{
    final private Motor leftMotor;
    
    final private Motor rightMotor;
    
    public static final int LEFT_FORWARD_PIN = 1;
    public static final int LEFT_BACKWARD_PIN = 0;

    public static final int RIGHT_FORWARD_PIN = 4;
    public static final int RIGHT_BACKWARD_PIN = 3;
// works for digital fowarsd backward test    
//    public static final int RIGHT_FORWARD_PIN = 3;
//    public static final int RIGHT_BACKWARD_PIN = 4;
    
    private int acceleration;
    
//    private int FULL_SPEED = 100;
    private Logger logger;
    
    
    public Roller()
    {
        Gpio.wiringPiSetup();

        leftMotor = new Motor(LEFT_FORWARD_PIN, LEFT_BACKWARD_PIN);
        rightMotor = new Motor(RIGHT_FORWARD_PIN, RIGHT_BACKWARD_PIN);
        
        String name = getClass().getName();
        logger = Logger.getLogger(name);
    }

    public void moveBackward()
    {
        logger.log(Level.INFO, "backward called on roller");
        
        leftMotor.moveBackward(acceleration);
        rightMotor.moveBackward(acceleration);
    }

    public void moveBackwardLeft()
    {
        int slowerAcceleration = turnAcceleration();
        
        leftMotor.moveBackward(slowerAcceleration);
        rightMotor.moveBackward(acceleration);
    }
    
    public void moveBackwardRight()
    {
        int slowerAcceleration = turnAcceleration();
        
        leftMotor.moveBackward(acceleration);
        rightMotor.moveBackward(slowerAcceleration);
    }
    
    public void moveForward()
    {
        logger.log(Level.INFO, "forward called on roller");
        
        leftMotor.moveForward(acceleration);
        rightMotor.moveForward(acceleration);
    }

    public void moveForwardLeft()
    {
        int slowerAcceleration = turnAcceleration();
        
        leftMotor.moveForward(slowerAcceleration);        
        rightMotor.moveForward(acceleration);
    }

    public void moveForwardRight()
    {
        int slowerAcceleration = turnAcceleration();
        
        leftMotor.moveForward(acceleration);
        rightMotor.moveForward(slowerAcceleration);
    }
    
    /**
     * @param acceleration a value between 0-100 
     */
    public void setAcceleration(int acceleration)
    {
        if(acceleration > 100)
        {
            this.acceleration = 100;
        }
        else if(acceleration < 0)
        {
            this.acceleration = 0;
        }
        else
        {
            this.acceleration = acceleration;
        }
    }    
    
    public void stop()
    {
        logger.log(Level.INFO, "stop called on roller");
        
        leftMotor.idle();
        rightMotor.idle();
    }
    
    private int turnAcceleration()
    {
        int slowerAcceleration = acceleration / 3;
        
        return slowerAcceleration;
    }
}
