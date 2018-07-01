
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MathsLevel {
    public String user_id;
    public Student obj;
    public String mathsLevel;
    private final BorderPane bp;
    Scene scene;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Student getObj() {
        return obj;
    }

    public void setObj(Student obj) {
        this.obj = obj;
    }
    
    
    public String getMathsLevel() {
        return mathsLevel;
    }

    public void setMathsLevel(String mathsLevel) {
        this.mathsLevel = mathsLevel;
    }

    @Override
    public String toString() {
        return "MathsLevel{" + "user_id=" + user_id + ", obj=" + obj + ", mathsLevel=" + mathsLevel + ", scene=" + scene + '}';
    }

    
    public MathsLevel(String user_id, Student obj) throws FileNotFoundException {
        this.user_id = user_id;
        this.obj = obj;
        bp = new BorderPane();
        bp.setPadding(new Insets(10, 50, 50, 50));

        //Adding HBox
        HBox hb = new HBox();
        hb.setPadding(new Insets(20, 20, 20, 20));

        RadioButton student = new RadioButton("Student");
        RadioButton parent = new RadioButton("Parent");

        ToggleGroup group = new ToggleGroup();
        student.setToggleGroup(group);
        student.setSelected(true);
        parent.setToggleGroup(group);

        VBox answerselection = new VBox(15);
        Pane textpane = new Pane();

        Text heading = new Text("Level of Difficulty");
        // color selection
        RadioButton beginner = new RadioButton("Beginner");
        RadioButton intermediate = new RadioButton("Intermediate");
        RadioButton advanced = new RadioButton("Advanced");
        Button btnNext = new Button("Next");

        ToggleGroup grouprb = new ToggleGroup();
        beginner.setToggleGroup(grouprb);
        intermediate.setToggleGroup(grouprb);
        advanced.setToggleGroup(grouprb);

        answerselection.getChildren().addAll(
                heading, beginner, intermediate, advanced, btnNext
        );

        //Adding text and DropShadow effect to it
        // FileInputStream logo = new FileInputStream("src/reso/logo.png");
        Image logoimage = new Image(getClass().getClassLoader().getResourceAsStream("reso/logo.png"));
        ImageView logoview = new ImageView();
        logoview.setImage(logoimage);

        //Adding image to HBox
        hb.getChildren().add(logoview);
        hb.setAlignment(Pos.CENTER);

        //Add ID's to Nodes
        bp.setId("bp");
        heading.setId("h");
        answerselection.setId("as");
        btnNext.setId("btn");

        //Add HBox and GridPane layout to BorderPane Layout
        bp.setTop(hb);
        bp.setCenter(answerselection);

        //Adding BorderPane to the scene and loading CSS
        scene = new Scene(bp, 450,550);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("style.css").toExternalForm());

        //END of JAVAFX CODE//
        //START of CONTROLLER CODE//
        
        
        btnNext.setOnAction(e -> {
            RadioButton mathsLevelSelected = (RadioButton) grouprb.getSelectedToggle(); //gets the selected radiobutton
            if (mathsLevelSelected != null) {
                this.mathsLevel = mathsLevelSelected.getText();
                MathsType type = null;
                try {
                    type = new MathsType(user_id, obj, mathsLevel); //create a MathsType object with user_id, obj and MathsLevel
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MathsLevel.class.getName()).log(Level.SEVERE, null, ex);
                }
                 
                Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) e.getSource()).getScene().getWindow();
                // Swap screen
                stageTheEventSourceNodeBelongs.setScene(type.getScene());

            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Option not chosen!");
                alert.setHeaderText(null);
                alert.setContentText("Please pick one of the options!");

                alert.showAndWait();
                    
            }

        });
    }

    public Scene getScene() {
        return scene;
    }

    public Pane getRootPane() {
        return bp;
    }
}
