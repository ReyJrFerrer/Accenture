package accenture.iostreams;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class I0Demo {

    public static void main(String[] args) {
        // readingFilePerCharacter();

        readingFilePerLine();
    }
    public static void readingFilePerCharacter() {
        FileInputStream fis = null;
        try {
            //https://docs.oracle.com/javase/7/docs/api/java/io/FileInputStream.html
            //example of absolute directory
            fis = new FileInputStream("src/accenture/Streams/files/StudentInfo.txt");
            System.out.println("using file input stream as bytestream - reading one byte at a time:\n");
            int tempHolder; //placeholder of each character upon reading
            //value of -1 means EOF
            //https://docs.oracle.com/javase/7/docs/api/java/io/InputStreamReader.html#read()
            int counter = 0;
            while((tempHolder = fis.read()) != -1){ //per byte read per loop
                //https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html#sleep-long-
                try {
                    Thread.sleep(250);
                } catch (InterruptedException ie) {
                    System.err.println(ie.getMessage());
                }
                System.out.print((char) tempHolder);
                counter++;
            }
            System.out.println("\n\nNumber of loops performed: " + counter);
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                fis.close(); //perform
            } catch (IOException ioe) {
                System.err.println(ioe.getMessage());
            }
            System.out.println("\nReading of file is finished.");
        }
    }
    public static void readingFilePerLine() {
        try {
            List<Student> students = new ArrayList<>();
            System.out.println("\nusing buffered reader reading file content per line:\n");
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/accenture/Streams/files/StudentInfo.txt"));
            String tempLineHolder;
            int counter = 0;
            while ((tempLineHolder = bufferedReader.readLine()) != null){ //value of null in this case represents EOF
                System.out.println(tempLineHolder);
                Student student = new Student();
                //https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#split(java.lang.String)
                //perform data population to the student object
                student.setName(tempLineHolder.split("-")[0]); //index[0] refers to the name from the StudentInfo.txt
                //Parses the string argument as a signed decimal integer.
                student.setAge(Integer.parseInt(tempLineHolder.split("-")[1])); //index[0] refers to the age from the StudentInfo.txt
                students.add(student);
                counter++;
            }
            System.out.println("\nNumber of loops performed: " + counter);
            bufferedReader.close();
        } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}