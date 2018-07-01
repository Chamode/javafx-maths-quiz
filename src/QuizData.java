
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chamode
 */
public class QuizData implements Serializable {
    String type;
    String difficulty;
    int score;

    public QuizData(String type, String difficulty, int score) {
        this.type = type;
        this.difficulty = difficulty;
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "QuizData{" + "type=" + type + ", difficulty=" + difficulty + ", score=" + score + '}';
    }
    
    
}
