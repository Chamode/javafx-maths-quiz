import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
 
public class ParentView{
    static Scene scene;
    public String user_id;
    public Student obj;
    private TableView<Results> table = new TableView<Results>();
//    private final 
    public ObservableList<Results> data = FXCollections.observableArrayList();
    //            new Results("Test 1", "Division", "Easy", "90"),
//            new Results("Test 2", "Addition", "Hard", "60"),
//            new Results("Test 3", "Addition", "Moderate", "75"),
//            new Results("Test 4", "Subtraction", "Easy", "98"),
//            new Results("Test 5", "Multiplication", "Hard", "68")
    
    
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
    
    public ParentView(String user_id, Student obj) throws FileNotFoundException{
        this.user_id = user_id;
        this.obj = obj;
        scene = new Scene(new Group());
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10,50,50,50));
        
        //Adding HBox
        HBox hb = new HBox();
        hb.setPadding(new Insets(20,20,20,20));
        
        // FileInputStream logo = new FileInputStream("src/reso/logo.png");
        Image logoimage = new Image(getClass().getClassLoader().getResourceAsStream("reso/logo.png"));
        ImageView logoview = new ImageView(); 
        logoview.setImage(logoimage);
        
        //Adding image to HBox
        hb.getChildren().add(logoview);
        hb.setAlignment(Pos.CENTER); 
        
        final Label label = new Label("Student Results");
        label.setFont(new Font("Arial", 20));
        
        //Adding GridPane
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(30,30,30,30));
        gp.setHgap(15);
        gp.setVgap(15);
        
        //Implementing Nodes for GridPane
        Label lblName = new Label("Student Name");
        Label lblNameResult = new Label(obj.name);
        
        Label lblStudId = new Label("Student ID");
        Label lblIDResult = new Label(obj.id);
        
        //Adding Nodes to GridPane layout  
        gp.add(lblStudId, 0, 1);
        gp.add(lblIDResult, 1, 1);
        
        gp.add(lblName, 0, 0);
        gp.add(lblNameResult, 1, 0);
        
        TableColumn firstNameCol = new TableColumn("Test No.");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Results, String>("testNumber"));
 
        TableColumn mathTypeCol = new TableColumn("Type");
        mathTypeCol.setMinWidth(150);
        mathTypeCol.setCellValueFactory(new PropertyValueFactory<Results, String>("mathType"));
 
        TableColumn levelCol = new TableColumn("Difficulty Level");
        levelCol.setMinWidth(150);
        levelCol.setCellValueFactory(new PropertyValueFactory<Results, String>("diffLevel"));
        
        TableColumn scoreCol = new TableColumn("Score");
        scoreCol.setMinWidth(150);
        scoreCol.setCellValueFactory(new PropertyValueFactory<Results, String>("score"));
        
        ArrayList <QuizData> al = this.obj.QuizData;
        Iterator itr = al.iterator();
        int count=0;
        while(itr.hasNext()){
            count++;
            QuizData quiz = (QuizData) itr.next();
            String testNum = "Test "+count;
            String score = Integer.toString(quiz.getScore()*10)+"%";
            data.add(new Results(testNum,quiz.getType(),quiz.getDifficulty(),score));
        }
        
        table.setItems(data);
        
//        table.setFixedCellSize(100);
//        table.prefHeightProperty().bind(Bindings.size(table.getItems()).multiply(table.getFixedCellSize()).add(30));
        
//        table.setFixedCellSize(25);
//        table.prefHeightProperty().bind(table.fixedCellSizeProperty().multiply(Bindings.size(table.getItems()).add(1.01)));
//        table.minHeightProperty().bind(table.prefHeightProperty());
//        table.maxHeightProperty().bind(table.prefHeightProperty());

        table.getColumns().addAll(firstNameCol, mathTypeCol, levelCol, scoreCol);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(15);
        vbox.getChildren().addAll(gp, table);
        //Add ID's to Nodes
        bp.setId("bp");
        gp.setId("studentinfo");
        lblName.setId("lblName");
        lblStudId.setId("lblName");
        firstNameCol.setId("colHead");
        
        bp.setTop(hb);
        bp.setCenter(vbox);
        
        ((Group) scene.getRoot()).getChildren().addAll(bp);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("parentview.css").toExternalForm());
    }
    
    public static Scene getScene() {
        return scene;
    }
    
     public class Results {
 
        private final SimpleStringProperty testNumber;
        private final SimpleStringProperty mathType;
        private final SimpleStringProperty difficultyLevel;
        private final SimpleStringProperty score;
        
        private Results(String tNo, String mType, String dLevel, String sAmount) {
            this.testNumber = new SimpleStringProperty(tNo);
            this.mathType = new SimpleStringProperty(mType);
            this.difficultyLevel = new SimpleStringProperty(dLevel);
            this.score = new SimpleStringProperty(sAmount);
        }
 
        public String getTestNumber() {
            return testNumber.get();
        }
 
        public void setTestNumber(String tNo) {
            testNumber.set(tNo);
        }
 
        public String getMathType() {
            return mathType.get();
        }
 
        public void setMathType(String tNo) {
            mathType.set(tNo);
        }
 
        public String getDiffLevel() {
            return difficultyLevel.get();
        }
 
        public void setDiffLevel(String tNo) {
            difficultyLevel.set(tNo);
        }
        
        public String getScore() {
            return score.get();
        }
 
        public void setScore(String tNo) {
            score.set(tNo);
        }
    }
}
 
