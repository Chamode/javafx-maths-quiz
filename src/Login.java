
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Login {
    static Scene scene;
//    MathsLevel game = new MathsLevel();
    Register new_account = new Register();
    public HashMap<String,Student> StudentData;

    public Login() throws FileNotFoundException {
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10, 50, 50, 50));

        //Adding HBox
        HBox hb = new HBox();
        hb.setPadding(new Insets(20, 20, 20, 20));
        
        HBox radio = new HBox(5);
        radio.setPadding(new Insets(10));

        RadioButton student = new RadioButton("Student");
        RadioButton parent = new RadioButton("Parent");

        ToggleGroup group = new ToggleGroup();
        student.setToggleGroup(group);
        student.setSelected(true);
        parent.setToggleGroup(group);

        //Adding GridPane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);

        //Implementing Nodes for GridPane
        Label lblUserName = new Label("Username");
        final TextField txtUserName = new TextField();
        Label lblPassword = new Label("Password");
        final PasswordField pf = new PasswordField();
        Button btnLogin = new Button("Login");
        Label lblSignUp = new Label("New to Math Champs?");
        Button btnSignUp = new Button("Sign Up");

        //Adding Nodes to GridPane layout  
        gridPane.add(student, 0, 0);
        gridPane.add(parent, 1, 0);

        gridPane.add(lblUserName, 0, 2);
        gridPane.add(txtUserName, 1, 2);
        gridPane.add(lblPassword, 0, 3);
        gridPane.add(pf, 1, 3);
        gridPane.add(btnLogin, 1, 4);
        gridPane.add(lblSignUp, 1, 6);
        gridPane.add(btnSignUp, 1, 7);
        GridPane.setHalignment(btnLogin, HPos.RIGHT);
        GridPane.setHalignment(lblSignUp, HPos.RIGHT);
        GridPane.setHalignment(btnSignUp, HPos.RIGHT);

        //Adding text and DropShadow effect to it
//        // FileInputStream logo = new FileInputStream("src/reso/logo.png");
        Image logoimage = new Image(getClass().getClassLoader().getResourceAsStream("reso/logo.png"));
        ImageView logoview = new ImageView();
        logoview.setImage(logoimage);

        //Adding image to HBox
        hb.getChildren().add(logoview);
        hb.setAlignment(Pos.CENTER);

        //Add ID's to Nodes
        bp.setId("bp");
        gridPane.setId("root");
        btnLogin.setId("btnLogin");
        btnSignUp.setId("btnSignUp");

        //Add HBox and GridPane layout to BorderPane Layout
        bp.setTop(hb);
        bp.setCenter(gridPane);

        //Adding BorderPane to the scene and loading CSS
        scene = new Scene(bp);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("Login.css").toExternalForm());
        
        
        //END of JAVAFX CODE//
        //START of CONTROLLER CODE//
        
        
        btnLogin.setOnAction(e -> {
            String user_id = txtUserName.getText().trim();
            String providedPassword = pf.getText().trim();
            boolean passwordMatch = false;
            HashMap<String, Student> data=null;
            HashMap<String, Student> valid_user=null;
            Student obj = null;
            
            try {
                data = FileUtils.readFromFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            if(user_id.isEmpty()||providedPassword.isEmpty()){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Empty Fields");
                alert.setHeaderText(null);
                alert.setContentText("One or more fields are empty\nPlease fill up all fields.");
                alert.showAndWait();
            }
            else{
                if(data.containsKey(user_id)){
                    for (HashMap.Entry<String, Student> entry : data.entrySet()) {
                        String studentID = entry.getKey();
                        if (user_id.equals(studentID)) {
                            obj = entry.getValue();
                            passwordMatch = PasswordUtils.verifyUserPassword(providedPassword, obj.password, obj.salt_value);
                        }
                    }

                    if(passwordMatch) {
                        RadioButton userType = (RadioButton) group.getSelectedToggle();
                        if(userType.getText().equals("Student")){
                            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) e.getSource()).getScene().getWindow();
                            // Swap screen
                            MathsLevel game=null;
                            try{
                            game = new MathsLevel(user_id, obj); //passes user ID and the current student object down
                            }catch(FileNotFoundException ex){
                                ex.printStackTrace();
                            }
                            stageTheEventSourceNodeBelongs.setScene(game.getScene());
                        }
                        else if(userType.getText().equals("Parent")){
                            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) e.getSource()).getScene().getWindow();
                            // Swap screen
                            ParentView parentResult=null;
                            try{
                            parentResult = new ParentView(user_id, obj); //passes user ID and the current student object down
                            }catch(FileNotFoundException ex){
                                ex.printStackTrace();
                            }
                            stageTheEventSourceNodeBelongs.setScene(parentResult.getScene());
                        }
//                        System.out.println("VALID USER HASH MAP OBTAINED");
                        System.out.println(obj);
                    }
                    else if(!passwordMatch){
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Incorrect Password");
                        alert.setHeaderText(null);
                        alert.setContentText("Incorrect Password Entered!\nPlease try again.");
                        alert.showAndWait();
                    }
                }
                else{
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Invalid Username");
                    alert.setHeaderText(null);
                    alert.setContentText("Username entered does not exist!\nPlease try again.");
                    alert.showAndWait();
                }
            }
        });
        
        btnSignUp.setOnAction(e -> {
            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) e.getSource()).getScene().getWindow();
            // Swap screen
            stageTheEventSourceNodeBelongs.setScene(new_account.getScene());
        });
    }

    public static Scene getScene() {
        return scene;
    }

}
