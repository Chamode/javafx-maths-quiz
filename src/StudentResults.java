
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class StudentResults {

    Scene scene;

    public StudentResults(String result) throws FileNotFoundException {

        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10, 50, 50, 50));

        //Adding HBox
        HBox hb = new HBox();
        hb.setPadding(new Insets(0, 20, 0, 20));

        VBox answerselection = new VBox(15);

        Text congratulations = new Text("Congratulations!!");
        congratulations.setFill(Color.web("#f78415"));

        Text score = new Text("Your score is "+ result);

        Text complete = new Text("You have successfully completed your Maths Quiz!");

//        FileInputStream happy = new FileInputStream("src/happy.gif");
        Image happyimage = new Image(getClass().getClassLoader().getResourceAsStream("happy.gif"));
        ImageView happyview = new ImageView();
        happyview.setImage(happyimage);

        Button btnAttemptAnother = new Button("Attempt Again");
        Button btnComplete = new Button("Complete");
        GridPane grid = new GridPane();

        grid.add(btnAttemptAnother, 0, 0);
        grid.add(btnComplete, 1, 0);

        answerselection.getChildren().addAll(
                congratulations, happyview, score, complete, btnComplete
        );
        
        btnComplete.setOnAction(actionEvent -> Platform.exit());
        answerselection.setAlignment(Pos.CENTER);
        // FileInputStream logo = new FileInputStream("src/reso/logo.png");
        Image logoimage = new Image(getClass().getClassLoader().getResourceAsStream("reso/logo.png"));
        ImageView logoview = new ImageView();
        logoview.setImage(logoimage);

        //Adding image to HBox
        hb.getChildren().add(logoview);
        hb.setAlignment(Pos.CENTER);

        //Add ID's to Nodes
        bp.setId("bp");
        btnAttemptAnother.setId("btn");
        btnComplete.setId("btn");
        congratulations.setId("message");
        score.setId("score");
        answerselection.setId("as");
        //btnNext.setId("btn");

        //Add HBox and GridPane layout to BorderPane Layout
        bp.setTop(hb);
        bp.setCenter(answerselection);
        //Adding BorderPane to the scene and loading CSS
        scene = new Scene(bp, 450,550);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("style.css").toExternalForm());

    }

    public Scene getScene() {
        return scene;
    }
}
