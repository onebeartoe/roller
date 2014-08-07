
package org.onebeartoe.roller.onebeartoe;

import com.pi4j.wiringpi.SoftPwm;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is a small program to check that both motors are correctly connected
 * to the RTK Motor Controller Board Kit for Raspberry Pi
 */
public class PwmApp 
{
    public static void main( String[] args )
    {        
        System.out.println( "PWM Test" );

        com.pi4j.wiringpi.Gpio.wiringPiSetup();

        // create soft-pwm pins (min=0 ; max=100)
        SoftPwm.softPwmCreate(1, 0, 100);
        sleepo(1500);
        
        SoftPwm.softPwmWrite(1, 50);        
        sleepo(4000);
        
        SoftPwm.softPwmWrite(1, 100);        
        sleepo(2000);
        
        SoftPwm.softPwmWrite(1, 0);
        
// todo: test the other motor        
    }
    
    public static void sleepo(long duration)
    {
        try 
        {
            Thread.sleep(duration);
        } 
        catch (InterruptedException ex) 
        {
            Logger.getLogger(PwmApp.class.getName()).log(Level.SEVERE, null, ex);
        }                
    }
}
