
package org.onebeartoe.roller;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;
import static org.onebeartoe.roller.PwmApp.sleepo;

/**
 * @author Roberto Marquez
 */
public class RollerTest 
{
    public static void main(String [] args)
    {
        int leftMotorForwardPin = 1;
        int leftMotorBackwardPin = 0;
        System.out.println("PWM Test C");

        
//        GpioController gpio = GpioFactory.getInstance();
        Gpio.wiringPiSetup();

        // create soft-pwm pins (min=0 ; max=100)
        SoftPwm.softPwmCreate(leftMotorForwardPin, 0, 100);
        SoftPwm.softPwmCreate(leftMotorBackwardPin, 0, 100);
        
        int rightMotorForwardPin = 3;
        int rightMotorBackwardPin = 2;
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
        
//        gpio.shutdown();
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
}

