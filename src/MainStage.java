import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Chamode
 */
public class MainStage extends Application {
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        primaryStage.setTitle("Maths Champ!");
        Login loginScene = new Login();
        primaryStage.setScene(loginScene.getScene());
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
