
package onebeartoe.roller.controls;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.onebeartoe.electronics.roller.DirectionControl;

/**
 * @author URHM020
 */
public class OnebeartoeRollerControlsSaver extends Application 
{
    private final Logger logger = Logger.getLogger(OnebeartoeRollerControlsSaver.class.getName());
    
    private Group ticks;
    
    @Override
    public void start(Stage primaryStage) 
    {
        ticks = DirectionControl.createTicks(150);
        
        Button saveButton = new Button();
        saveButton.setText("Save");
        saveButton.setOnAction(new EventHandler<ActionEvent>() 
        {
            @Override
            public void handle(ActionEvent event) 
            {
                System.out.println("saving snapshot");
                
                // this is for the screen grap of the map with identifiers
                WritableImage image = ticks.snapshot(new SnapshotParameters(), null);
                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
                File outfile = new File("clock.png");

                try 
                {
                    System.out.println("outptuing to : " + outfile.getAbsolutePath() );
                    ImageIO.write(bufferedImage, "png", outfile);
                } 
                catch (IOException ex) 
                {
                    logger.log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        
        BorderPane root = new BorderPane();
        root.setCenter(ticks);
        root.setBottom(saveButton);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        launch(args);
    }
}
