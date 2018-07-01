
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

public class MathsType {
    public String user_id;
    public Student obj;
    public String mathsType;
    public String mathsLevel;
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
    
    public String getMathsType() {
        return mathsType;
    }

    public void setMathsType(String mathsType) {
        this.mathsType = mathsType;
    }

    public String getMathsLevel() {
        return mathsLevel;
    }

    public void setMathsLevel(String mathsLevel) {
        this.mathsLevel = mathsLevel;
    }

    @Override
    public String toString() {
        return "MathsType{" + "user_id=" + user_id + ", obj=" + obj + ", mathsType=" + mathsType + ", mathsLevel=" + mathsLevel + ", scene=" + scene + '}';
    }
    
    public MathsType(String user_id, Student obj, String mathsLvl) throws FileNotFoundException {
        this.user_id = user_id;
        this.obj = obj;
        this.mathsLevel = mathsLvl;
        
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10, 50, 50, 50));

        //Adding HBox
        HBox hb = new HBox();
        hb.setPadding(new Insets(20, 20, 20, 20));

        VBox answerselection = new VBox(15);
        Pane textpane = new Pane();

        Text heading = new Text("Type of Math");
        // color selection
        RadioButton add = new RadioButton("Addition");
        RadioButton subtract = new RadioButton("Subtraction");
        RadioButton multiply = new RadioButton("Multiplication");
        RadioButton divide = new RadioButton("Division");
        Button btnNext = new Button("Next");

        ToggleGroup grouprb = new ToggleGroup();
        add.setToggleGroup(grouprb);
        subtract.setToggleGroup(grouprb);
        multiply.setToggleGroup(grouprb);
        divide.setToggleGroup(grouprb);

        answerselection.getChildren().addAll(
                heading, add, subtract, multiply, divide, btnNext
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
        
        btnNext.setOnAction(e -> {
            RadioButton mathsTypeSelected = (RadioButton) grouprb.getSelectedToggle();
            if (mathsTypeSelected != null) {
            this.mathsType = mathsTypeSelected.getText();
            Quiz quiz = null;
            try {
                quiz = new Quiz(user_id, obj, mathsLevel, mathsType);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MathsLevel.class.getName()).log(Level.SEVERE, null, ex);
            }

  
            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) e.getSource()).getScene().getWindow();
            // Swap screen
            stageTheEventSourceNodeBelongs.setScene(quiz.getScene());

        }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
}
