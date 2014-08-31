
package org.onebeartoe.roller;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import static org.onebeartoe.roller.PwmApp.sleepo;

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
        
        GpioPinDigitalOutput pin03;
        GpioPinDigitalOutput pin02;
           
        // left motor
        pin00 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "pin somethings", PinState.LOW);
        pin01 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "pin someting", PinState.LOW);
        
        pin03 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "pin05", PinState.LOW);
        pin02 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "pin04", PinState.LOW);         
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

}
