
package org.onebeartoe.roller;

import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;
import static org.onebeartoe.roller.PwmApp.sleepo;

/**
 * @author Roberto Marquez
 */
public class PwmPinCleanup 
{
    public static void main(String [] args)
    {
        System.out.println("PWM Pin Cleanup A");

        Gpio.wiringPiSetup();

        for(int p=0; p<21; p++)
        {
            System.out.print("creating pin " + p);
            SoftPwm.softPwmCreate(p, 0, 100);
            sleepo(300);
            System.out.println(", cleaning, ");
            SoftPwm.softPwmWrite(p, 50);        
            System.out.println("done");
        }
    }
}

