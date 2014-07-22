
package org.onebeartoe.roller.onebeartoe;

import com.pi4j.wiringpi.SoftPwm;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class PwmApp 
{
    public static void main( String[] args )
    {        
        System.out.println( "A" );

        com.pi4j.wiringpi.Gpio.wiringPiSetup();

        // create soft-pwm pins (min=0 ; max=100)
        SoftPwm.softPwmCreate(1, 0, 100);
        sleepo(1500);
        
        SoftPwm.softPwmWrite(1, 50);        
        sleepo(4000);
        
        SoftPwm.softPwmWrite(1, 100);        
        sleepo(2000);
        
        SoftPwm.softPwmWrite(1, 0);
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
