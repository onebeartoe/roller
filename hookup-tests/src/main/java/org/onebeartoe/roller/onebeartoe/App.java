
package org.onebeartoe.roller.onebeartoe;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {        
        System.out.println( "m" );

        GpioController gpio = GpioFactory.getInstance();
        GpioPinDigitalOutput pin00;
        GpioPinDigitalOutput pin01;
           
        pin00 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "pin somethings", PinState.LOW);
        pin01 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "pin someting", PinState.LOW);         
//        pin00 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "pin05", PinState.LOW);
//        pin01 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "pin04", PinState.LOW);         
        sleepo(1500);
        
        pin00.high();
        sleepo(2000);
        pin00.low();
        
        sleepo(2000);
        
        pin01.high();
        sleepo(2000);
        pin01.low();
        
        gpio.shutdown();
    }
    
    public static void sleepo(long duration)
    {
        
                
        try 
        {
            Thread.sleep(duration);
        } 
        catch (InterruptedException ex) 
        {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }                
    }
}
