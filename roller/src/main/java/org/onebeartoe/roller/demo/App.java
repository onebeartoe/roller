
package org.onebeartoe.roller.demo;

import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import org.onebeartoe.roller.hardware.Roller;
import org.onebeartoe.roller.test.PwmTest;
import static org.onebeartoe.roller.test.PwmTest.sleepo;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Roller Demo B");

        Roller roller = new Roller();
        roller.setAcceleration(100);
        
        roller.moveForward();
        sleepo(3000);
        
        roller.moveForwardLeft();
        sleepo(3000);
        
        roller.moveForwardRight();
        sleepo(3000);
        
        
        roller.moveBackward();
        sleepo(3000);
                
        roller.stop();
    }

}
