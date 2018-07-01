
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Quiz {

    public String user_id;
    public Student obj;
    public String mathsType;
    public String mathsLevel;
    final int QUESTION_NO = 5;
    Scene scene;

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

    @Override
    public String toString() {
        return "Quiz{" + "user_id=" + user_id + ", obj=" + obj + ", mathsType=" + mathsType + ", mathsLevel=" + mathsLevel + ", QUESTION_NO=" + QUESTION_NO + ", scene=" + scene + '}';
    }

    public int mathsLevelConverter(String level) {
        switch (level) {
            case "Beginner":
                return 10;
            case "Intermediate":
                return 100;
            case "Expert":
                return 1000;
        }
        return 0;
    }

    public String mathsTypeConverter(String type) {
        switch (type) {
            case "Addition":
                return " + ";
            case "Subtraction":
                return " - ";
            case "Multiplication":
                return " * ";
            case "Division":
                return " / ";
        }
        return " ";
    }

    public List<Integer> generateQuestion(int level, String operation) {
        boolean flag = false;
        List<Integer> numbers = new ArrayList<Integer>();
        int firstDigit = (int) Math.round(Math.random() * (level));
        int secondDigit = (int) Math.round(Math.random() * (level));

        int answer = 0;
        if (operation.equals("Addition")) {
            answer = firstDigit + secondDigit;
            flag = true;
        }

        if (operation.equals("Subtraction")) {
            if (firstDigit >= secondDigit) {
                answer = firstDigit - secondDigit;
                flag = true;

            } else if (firstDigit < secondDigit) {
                List<Integer> tempNumbers = generateQuestion(level, operation);
                numbers.clear();
                numbers.addAll(tempNumbers);
            }
        }
        if (operation.equals("Multiplication")) {
            answer = firstDigit * secondDigit;
            flag = true;
        }
        if (operation.equals("Division")) {
            if (firstDigit == 0 || firstDigit % secondDigit != 0) {
                List<Integer> tempNumbers = generateQuestion(level, operation);
                numbers.clear();
                numbers.addAll(tempNumbers);
            } else {
                answer = firstDigit / secondDigit;
                flag = true;
            }
        }

        numbers.add(firstDigit);
        numbers.add(secondDigit);
        numbers.add(answer);
        if (flag) {
            int count = 0;
            while (count != 3) {
                int fakeAnswer = (int) Math.round(Math.random() * (level));
                if (operation.equals("Multiplication")) {
                    fakeAnswer = fakeAnswer * secondDigit;
                }
//            && !numbers.contains(fakeAnswer)
                if (fakeAnswer != answer) {
                    numbers.add(fakeAnswer);
                    count++;
                }

            }
        }

        return numbers;
    }

    public Quiz(String user_id, Student obj, String mathsLevel, String mathsType) throws FileNotFoundException {
        this.user_id = user_id;
        this.obj = obj;
        this.mathsLevel = mathsLevel;
        this.mathsType = mathsType;
        System.out.println(user_id);
        System.out.println(obj.toString());
        BorderPane bp = new BorderPane();
//        bp.setPadding(new Insets(20, 30, 30, 30));
        ScrollPane sp = new ScrollPane();
        bp.setPadding(new Insets(10, 50, 50, 50));

        //Adding HBox
        HBox hb = new HBox();
        hb.setPadding(new Insets(20, 50, 50, 50));

        VBox answerselection = new VBox(15);

        int mathsLevelConverted = mathsLevelConverter(mathsLevel);
        String mathsTypeConverted = mathsTypeConverter(mathsType);
        ArrayList<ToggleGroup> ToggleGroupList = new ArrayList<>();
        ArrayList<String> Answers = new ArrayList<>();

        for (int i = 0; i < QUESTION_NO; i++) {
            List<Integer> QuestionDigits = generateQuestion(mathsLevelConverted, mathsType);
            List<String> StringQuestionDigits = new ArrayList<String>(QuestionDigits.size());
            ArrayList<String> AnswerList = new ArrayList<>();
            for (Integer myInt : QuestionDigits) {
                StringQuestionDigits.add(String.valueOf(myInt));
            }

            Answers.add(StringQuestionDigits.get(2));

            Label labelQ = new Label((i + 1) + ")  " + StringQuestionDigits.get(0) + mathsTypeConverted + StringQuestionDigits.get(1));

            AnswerList.add(StringQuestionDigits.get(2));
            AnswerList.add(StringQuestionDigits.get(3));
            AnswerList.add(StringQuestionDigits.get(4));
            AnswerList.add(StringQuestionDigits.get(5));

            Collections.shuffle(AnswerList);

            RadioButton q_opt1 = new RadioButton(AnswerList.get(0));
            RadioButton q_opt2 = new RadioButton(AnswerList.get(1));
            RadioButton q_opt3 = new RadioButton(AnswerList.get(2));
            RadioButton q_opt4 = new RadioButton(AnswerList.get(3));

            ToggleGroup q1_rb = new ToggleGroup();
            q_opt1.setToggleGroup(q1_rb);
            q_opt2.setToggleGroup(q1_rb);
            q_opt3.setToggleGroup(q1_rb);
            q_opt4.setToggleGroup(q1_rb);

            ToggleGroupList.add(q1_rb);
            answerselection.getChildren().addAll(
                    labelQ, q_opt1, q_opt2, q_opt3, q_opt4
            );

            //Add ID's to Nodes
            bp.setId("bp");
            labelQ.setId("q");
            answerselection.setId("as");

        }

        Button btnFinish = new Button("Finish");

        answerselection.getChildren().addAll(
                btnFinish
        );
        btnFinish.setId("btn");
        btnFinish.setOnAction(e -> {
            int count = 0;
            int selectedCount = 0;
            int totalCorrect = 0;
            for (ToggleGroup TGCount : ToggleGroupList) {
                Toggle selectedToggle = TGCount.getSelectedToggle();
                if (selectedToggle != null) {
                    selectedCount++;
                }
            }
            if (selectedCount == QUESTION_NO) {
                for (ToggleGroup TGQ : ToggleGroupList) {
                    Toggle selectedToggle = TGQ.getSelectedToggle();
                    RadioButton selectedRB = (RadioButton) selectedToggle;
                    if (Answers.get(count).contains(selectedRB.getText())) {
                        System.out.println((count + 1) + " Correct");
                        totalCorrect += 1;
                    } else {
                        System.out.println((count + 1) + " Wrong");
                    }
                    count++;
                }
                String results = Integer.toString(totalCorrect) + " / " + Integer.toString(QUESTION_NO);
                QuizData qData = new QuizData(this.mathsType, this.mathsLevel, totalCorrect);
                obj.updateQuizData(qData);
                
                FileUtils writer = new FileUtils();
                try {
                    writer.writeToFile(user_id, obj);
                } catch (IOException ex) {
                    Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                System.out.println("updated : "+obj.toString());
                
                
                StudentResults sR = null;
                try {
                    sR = new StudentResults(results);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Quiz.class.getName()).log(Level.SEVERE, null, ex);
                }
                Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) e.getSource()).getScene().getWindow();
                // Swap screen
                stageTheEventSourceNodeBelongs.setScene(sR.getScene());

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Answer All Questions!");
                alert.setHeaderText(null);
                alert.setContentText("You did not answer all the questions. Please make sure you have answered all!");

                alert.showAndWait();
            }
        });

        //Adding text and DropShadow effect to it
        // FileInputStream logo = new FileInputStream("src/reso/logo.png");
        Image logoimage = new Image(getClass().getClassLoader().getResourceAsStream("reso/logo.png"));
        ImageView logoview = new ImageView();
        logoview.setImage(logoimage);

        //Adding image to HBox
        hb.getChildren().add(logoview);
        hb.setAlignment(Pos.CENTER);
        //Add HBox and GridPane layout to BorderPane Layout
        bp.setTop(hb);
        bp.setCenter(answerselection);
        sp.setContent(bp);
        sp.setHbarPolicy(ScrollBarPolicy.NEVER);
        sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        //Adding BorderPane to the scene and loading CSS
        scene = new Scene(sp, 450,550);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("style.css").toExternalForm());
    }

    public Scene getScene() {
        return scene;
    }
}
