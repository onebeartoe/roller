
package org.onebeartoe.roller.test;

import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.onebeartoe.roller.hardware.Roller;

/**
 * @author Roberto Marquez
 */
public class PwmTest 
{
    public static void main(String [] args)
    {
        int leftMotorForwardPin = Roller.LEFT_FORWARD_PIN;
        int leftMotorBackwardPin = Roller.LEFT_BACKWARD_PIN;
        
        int rightMotorForwardPin = Roller.RIGHT_FORWARD_PIN;
        int rightMotorBackwardPin = Roller.RIGHT_BACKWARD_PIN;
        
        System.out.println("PWM Test C");
        
        Gpio.wiringPiSetup();

        SoftPwm.softPwmCreate(leftMotorForwardPin, 0, 100);
        SoftPwm.softPwmCreate(leftMotorBackwardPin, 0, 100);        
        
        SoftPwm.softPwmCreate(rightMotorForwardPin, 0, 100);
        SoftPwm.softPwmCreate(rightMotorBackwardPin, 0, 100);
        
        SoftPwm.softPwmWrite(leftMotorForwardPin, 0);
        SoftPwm.softPwmWrite(leftMotorBackwardPin, 0);        
        SoftPwm.softPwmWrite(rightMotorForwardPin, 0);
        SoftPwm.softPwmWrite(rightMotorBackwardPin, 0);
        
        sleepo(1500);
        
        // tests follow
        System.out.println("left motor:");
        testSide(leftMotorForwardPin, leftMotorBackwardPin);
                
        System.out.println("right motor:");                        
        testSide(rightMotorForwardPin, rightMotorBackwardPin);
        
        System.out.println("shutting down");
        SoftPwm.softPwmWrite(leftMotorForwardPin, 0);
        SoftPwm.softPwmWrite(leftMotorBackwardPin, 0);        
        SoftPwm.softPwmWrite(rightMotorForwardPin, 0);
        SoftPwm.softPwmWrite(rightMotorBackwardPin, 0);
    }
    
    private static void testSide(int forwardPin, int backwardPin)
    {
        SoftPwm.softPwmWrite(forwardPin, 50);        
        sleepo(4000);
        
        SoftPwm.softPwmWrite(forwardPin, 75);        
        sleepo(4000);                
        
        SoftPwm.softPwmWrite(forwardPin, 100);        
        sleepo(2000);
        
        // be sure to stop the moter before moving it in the other direction
        SoftPwm.softPwmWrite(forwardPin, 0);
        sleepo(2000);
        
        // move left motor backwards
        SoftPwm.softPwmWrite(backwardPin, 50);        
        sleepo(4000);
        
        SoftPwm.softPwmWrite(backwardPin, 75);        
        sleepo(4000);                
        
        SoftPwm.softPwmWrite(backwardPin, 100);        
        sleepo(2000);
        
        // be sure to stop the moter before moving it in the other direction
        SoftPwm.softPwmWrite(backwardPin, 0);
        sleepo(2000);        
    }

    public static void sleepo(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException ex) {
            Logger.getLogger(RollerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

