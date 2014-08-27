
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
        System.out.println("PWM Test 9");

        GpioController gpio = GpioFactory.getInstance();
        Gpio.wiringPiSetup();

        // create soft-pwm pins (min=0 ; max=100)
        SoftPwm.softPwmCreate(leftMotorForwardPin, 0, 100);
        SoftPwm.softPwmCreate(leftMotorBackwardPin, 0, 100);        
        sleepo(1500);
        
        SoftPwm.softPwmWrite(leftMotorForwardPin, 50);        
        sleepo(4000);
        
        SoftPwm.softPwmWrite(leftMotorForwardPin, 75);        
        sleepo(4000);                
        
        SoftPwm.softPwmWrite(leftMotorForwardPin, 100);        
        sleepo(2000);
        
        // be sure to stop the moter before moving it in the other direction
        SoftPwm.softPwmWrite(leftMotorForwardPin, 0);
        sleepo(2000);
        
        // move left motor backwards
        SoftPwm.softPwmWrite(leftMotorBackwardPin, 50);        
        sleepo(4000);
        
        SoftPwm.softPwmWrite(leftMotorBackwardPin, 75);        
        sleepo(4000);                
        
        SoftPwm.softPwmWrite(leftMotorBackwardPin, 100);        
        sleepo(2000);
        
        // be sure to stop the moter before moving it in the other direction
        SoftPwm.softPwmWrite(leftMotorBackwardPin, 0);
        sleepo(2000);
        
        // todo: test the other motor        
        
        gpio.shutdown();
    }
}
