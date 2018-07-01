
import java.io.Serializable;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Chamode
 */
public class Student implements Serializable {

    String id;
    String password;
    String salt_value;
    String name;
    int year_born;
    ArrayList<QuizData> QuizData;

    public Student(String id, String password, String salt_value, String name, int year_born, ArrayList<QuizData> QuizData) {
        this.id = id;
        this.password = password;
        this.salt_value = salt_value;
        this.name = name;
        this.year_born = year_born;
        this.QuizData = QuizData;
    }

    public Student(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt_value() {
        return salt_value;
    }

    public void setSalt_value(String salt_value) {
        this.salt_value = salt_value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear_born() {
        return year_born;
    }

    public void setYear_born(int year_born) {
        this.year_born = year_born;
    }

    public ArrayList<QuizData> getQuizData() {
        return QuizData;
    }

    public void setQuizData(ArrayList<QuizData> QuizData) {
        this.QuizData = QuizData;
    }

    public void updateQuizData(QuizData quizData) {
        this.QuizData.add(quizData);
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", password=" + password +  ", Salt Value=" + salt_value + ", name=" + name + ", QuizData=" + QuizData.toString() + '}';
    }

}
