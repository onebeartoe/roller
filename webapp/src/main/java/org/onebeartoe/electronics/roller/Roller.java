
package org.onebeartoe.electronics.roller;

import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;

/**
 * Instances of this class represent a WiFi controlled car.
 * 
 * @author Roberto Marquez
 */
public class Roller 
{
    // pins 0 and 1 contol the motor on the ____RIGHT OR LEFT?___________________ side
    public final int pin0 = 0;    
    public final int pin1 = 1;
    
    // pins XXX and XXX control the motor on the ____RIGHT OR LEFT?___________________ side
    
    private int acceleration;
    
    private int FULL_SPEED = 100;
    
    public Roller()
    {
        Gpio.wiringPiSetup();
        
        // motor 1                      min=0, max=100)
        SoftPwm.softPwmCreate(pin0,     0,     FULL_SPEED);
        SoftPwm.softPwmCreate(pin1,     0,     FULL_SPEED);
    }
    
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
    
    public void moveForward()
    {
        SoftPwm.softPwmWrite(pin0, 0);
        SoftPwm.softPwmWrite(pin1, acceleration);
        
// move the other mother forward        
    }
    
}
