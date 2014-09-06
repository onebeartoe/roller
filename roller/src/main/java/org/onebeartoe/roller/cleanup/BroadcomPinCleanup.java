
package org.onebeartoe.roller.cleanup;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import java.util.ArrayList;
import java.util.List;
import sun.security.x509.OIDMap;

/**
 * Hello world!
 *
 */
public class BroadcomPinCleanup 
{
    public static void main( String[] args )
    {
        System.out.println("Broadcom Pin Cleanup A");

        List<Pin> pins = loadGpioPins();
        
        GpioController gpio = GpioFactory.getInstance();

        for(Pin pin : pins)
        {
            String name = "pin " + pin.getName();
            GpioPinDigitalOutput p = gpio.provisionDigitalOutputPin(pin, name, PinState.LOW);
            p.low();
        }
        
        gpio.shutdown();
    }
    
    private static List<Pin> loadGpioPins()
    {
        List<Pin> pins = new ArrayList();
        
        pins.add(RaspiPin.GPIO_00);
        pins.add(RaspiPin.GPIO_01);
        pins.add(RaspiPin.GPIO_02);
        pins.add(RaspiPin.GPIO_03);
        pins.add(RaspiPin.GPIO_04);
        pins.add(RaspiPin.GPIO_05);
        pins.add(RaspiPin.GPIO_06);
        pins.add(RaspiPin.GPIO_07);
        pins.add(RaspiPin.GPIO_08);
        pins.add(RaspiPin.GPIO_09);
        pins.add(RaspiPin.GPIO_10);
        pins.add(RaspiPin.GPIO_11);
        pins.add(RaspiPin.GPIO_12);
        pins.add(RaspiPin.GPIO_13);
        pins.add(RaspiPin.GPIO_14);
        pins.add(RaspiPin.GPIO_15);
        pins.add(RaspiPin.GPIO_16);
        pins.add(RaspiPin.GPIO_17);
        pins.add(RaspiPin.GPIO_18);
        pins.add(RaspiPin.GPIO_19);
        pins.add(RaspiPin.GPIO_20);
        
        return pins;
    }
}
