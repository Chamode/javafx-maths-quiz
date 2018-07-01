
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vegnish
 */
public class FileUtils {
    public static HashMap<String, Student> readFromFile() throws IOException{
        ObjectInputStream ois=null;
        // read object from file
        try {
            InputStream fis = new FileInputStream("student.ser");
            fis = FileUtils.class.getResourceAsStream("student.ser");
            ois = new ObjectInputStream(fis);
        } 
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }
        
        Student obj = null;
        HashMap<String, Student> result=null;
        try{
//            while(ois.readObject() != null)
            result = (HashMap) ois.readObject();
//            System.out.println(result);
//            System.out.println("HashMap read from file");
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        
        for (HashMap.Entry<String, Student> entry : result.entrySet()) {
            String studentID = entry.getKey();
//            System.out.println(studentID);
            obj = entry.getValue();
//            System.out.println("test");
//            System.out.println(studentID + "   " + obj.toString());
        }
        ois.close();
        return result;
    }
    
    public static void writeToFile(String student_id,Student student_data) throws IOException{
//        HashMap<String, Student> data = new HashMap<String, Student>();
        HashMap<String, Student> data = readFromFile();
        
        ObjectOutputStream oos=null;
        try{
        FileOutputStream fos = new FileOutputStream("student.ser");
        oos = new ObjectOutputStream(fos);
        }
        catch(FileNotFoundException ex){
            ex.printStackTrace();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }
        
        data.put(student_id, student_data);
        oos.writeObject(data);
        System.out.println("Object written to file");
        oos.flush();
        oos.close();
    }
    
}
