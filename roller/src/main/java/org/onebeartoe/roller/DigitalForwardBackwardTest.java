package org.onebeartoe.roller;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import static org.onebeartoe.roller.PwmApp.sleepo;

/**
 * This works as a test to verify your Roller is hooked up correctly.
 * When the program runs, roller will move forward for 2 seconds and then 
 * backward for two seconds.
 */
public class DigitalForwardBackwardTest 
{

    // left motor
    private static GpioPinDigitalOutput leftForward;
    private static GpioPinDigitalOutput leftBackward;

    // right motor
    private static GpioPinDigitalOutput rightForward;
    private static GpioPinDigitalOutput rightBackward;

    public static void main(String[] args) 
    {
        System.out.println("Forward Backward Test H");

        GpioController gpio = GpioFactory.getInstance();

        // left motor
        leftForward = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "pin someting", PinState.LOW);
        leftBackward = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "pin somethings", PinState.LOW);

        // right motor
        rightForward = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "pin03", PinState.LOW);
        rightBackward = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "pin02", PinState.LOW);

        allLow();

        System.out.println("move forward");
        leftForward.high();
        rightBackward.high();
        sleepo(2000);
        leftForward.low();
        rightBackward.low();

        System.out.println("move backward");
        leftBackward.high();
        rightForward.high();
        sleepo(2000);
        leftBackward.low();
        rightForward.low();
        
        allLow();

        gpio.shutdown();
    }

    public static void allLow() 
    {
        leftBackward.low();
        leftForward.low();
        rightBackward.low();
        rightForward.low();
    }

}
