import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
 
public class Register{
    static Scene scene;
//    Login login = new Login();
    
     public Register() throws FileNotFoundException{  
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10,50,50,50));
        
        //Adding HBox
        HBox hb = new HBox();
        hb.setPadding(new Insets(20,20,20,20));
        
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
        gridPane.setPadding(new Insets(30,30,30,30));
        gridPane.setHgap(15);
        gridPane.setVgap(10);
        
        //Implementing Nodes for GridPane
        Label lblFullName = new Label("Full Name");
        final TextField txtFullName = new TextField();
        Label lblFullNameError = new Label();
        
        Label lblAge = new Label("Age");
        final TextField txtAge = new TextField();
        Label lblAgeError = new Label();
        
        Label lblUserName = new Label("Username");
        final TextField txtUserName = new TextField();
        Label lblUserNameError = new Label();
        
        Label lblPassword = new Label("Password");
        final PasswordField pf = new PasswordField();
        Label lblPasswordError = new Label();
        
        Button btnSignUp = new Button("Sign Up");
        Label lblLogin = new Label("Already have an account?");
        Button btnLogin = new Button("Log In");
        
        //Adding Nodes to GridPane layout  
        gridPane.add(lblFullName, 0, 0);
        gridPane.add(txtFullName, 1, 0);
        gridPane.add(lblFullNameError, 0, 1);
        
        
        gridPane.add(lblAge, 0, 2);
        gridPane.add(txtAge, 1, 2);
        gridPane.add(lblAgeError, 0, 3);
        
        gridPane.add(lblUserName, 0, 4);
        gridPane.add(txtUserName, 1, 4);
        gridPane.add(lblUserNameError, 0, 5);
        
        gridPane.add(lblPassword, 0, 6);
        gridPane.add(pf, 1, 6);
        gridPane.add(lblPasswordError, 0, 7);
        
        gridPane.add(btnSignUp, 1, 8);
        gridPane.add(lblLogin, 1, 10);
        gridPane.add(btnLogin, 1, 11);
        GridPane.setHalignment(btnLogin, HPos.RIGHT);
        GridPane.setHalignment(lblLogin, HPos.RIGHT);
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
        btnLogin.setId("btnSignUp");
        btnSignUp.setId("btnLogin");
//        "\"C:\\Users\\Chamode\\Desktop\\OOP\\oop2project\\src\\logo.png\""
        //Add HBox and GridPane layout to BorderPane Layout
        bp.setTop(hb);
        bp.setCenter(gridPane);  
        
        //Adding BorderPane to the scene and loading CSS
        scene = new Scene(bp);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("Login.css").toExternalForm());
        
        
        //END of JAVAFX CODE//
        //START of CONTROLLER CODE//
        
        
        btnLogin.setOnAction(e -> {
            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) e.getSource()).getScene().getWindow();
            // Swap screen
            stageTheEventSourceNodeBelongs.setScene(Login.getScene());
        });
        
         btnSignUp.setOnAction(e -> {
            String name = txtFullName.getText().trim();
            String year_born = txtAge.getText().trim();
            String username = txtUserName.getText().trim();
            String password = pf.getText().trim();
//            String salt = PasswordUtils.getSalt(30);
//            String secure_password = PasswordUtils.generateSecurePassword(password, salt);
            HashMap<String, Student> data=null;

            try {
                data = FileUtils.readFromFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            if(name.isEmpty()||year_born.isEmpty()||username.isEmpty()||password.isEmpty()){
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Empty Fields");
                alert.setHeaderText(null);
                alert.setContentText("One or more fields are empty\nPlease fill up all fields.");
                alert.showAndWait();
            }
            else{
                if(username.contains(" ")){
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Whitespace");
                    alert.setHeaderText(null);
                    alert.setContentText("Please re-enter username\nUsername cannot have whitespaces!");
                    alert.showAndWait();
                }
                else if(password.contains(" ")){
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Whitespace");
                    alert.setHeaderText(null);
                    alert.setContentText("Please re-enter password\nPassword cannot have whitespaces!");
                    alert.showAndWait();
                }
                else{
                    if(data.containsKey(username)){
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Existing Username");
                        alert.setHeaderText(null);
                        alert.setContentText("Username has been chosen before\nPlease try with a different username.");
                        alert.showAndWait();
                    }
                    else{
                        String salt = PasswordUtils.getSalt(30);
                        String secure_password = PasswordUtils.generateSecurePassword(password, salt);
                        Student studentNew = new Student(username, secure_password, salt, name, Integer.parseInt(year_born), new ArrayList<QuizData>());
                        try {
                            FileUtils.writeToFile(username, studentNew);
                            System.out.println("New Object Created");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setHeaderText(null);
                        alert.setContentText("Your account has been created\nYou will now be re-directed to Login page.");
                        alert.showAndWait();
                        Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) e.getSource()).getScene().getWindow();
                        // Swap screen
                        stageTheEventSourceNodeBelongs.setScene(Login.getScene());
                    }  
                }
            }
         });
     }
     
     
      public static Scene getScene() {
        return scene;
    }
}
