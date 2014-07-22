/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.onebeartoe.electronics.roller;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;

/**
 *
 * @author URHM020
 */
public class DirectionControl 
{
  /**
   * This was borrowed from https://gist.github.com/jewelsea/3388637
   * @return radial ticks around the clock center to mark time. 
   */
  public static Group createTicks(double clockRadius) 
  {
    final double TICK_START_OFFSET = percentOf(73, clockRadius);
    final double TICK_END_OFFSET   = percentOf(93, clockRadius);
//    final double TICK_START_OFFSET = percentOf(83, clockRadius);
//    final double TICK_END_OFFSET   = percentOf(93, clockRadius);

    final Group  ticks = new Group();
    for (int i = 0; i < 12; i++) {
      Line tick = new Line(0, -TICK_START_OFFSET, 0, -TICK_END_OFFSET);
      tick.getStyleClass().add("tick");
      tick.setLayoutX(clockRadius);
      tick.setLayoutY(clockRadius);
      tick.getTransforms().add(new Rotate(i * (360 / 12)));
      ticks.getChildren().add(tick);
    }
    return ticks;
  }    

  private static double percentOf(double percent, double clockRadius) 
  {
    return percent / 100 * clockRadius;
  }
  
}
