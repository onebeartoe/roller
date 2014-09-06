
package org.onebeartoe.roller.test;

import org.onebeartoe.roller.hardware.Roller;
import static org.onebeartoe.roller.test.PwmTest.sleepo;

/**
 * This is a small program to check that both motors are correctly connected
 * to the RTK Motor Controller Board Kit for Raspberry Pi.
 */
public class RollerTest 
{
    public static void main( String[] args )
    {        
        System.out.println("Roller Test B");

        Roller roller = new Roller();
        roller.setAcceleration(100);
        
        roller.moveForward();
        sleepo(3000);
        
        roller.moveBackward();
        sleepo(3000);
        
        roller.stop();       
    }
}
