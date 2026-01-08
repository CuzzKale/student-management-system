package studentmanagementsystem;
import java.util.ArrayList;
import java.util.Arrays;
public class students {
public String name;
private double grade;
private String gradeLetter;
private double overallGrade;
public int id;
public String email;
private int attendence;
private char honors;
private char valedictorian;
private double[] grades; 
private String[] classes1; 
   
   // all classes students could take
   ArrayList<String> classes = new ArrayList<String>();

   // holds each grades students
   ArrayList<students> freshmen = new ArrayList<>();
   ArrayList<students> softmore = new ArrayList<>();
   ArrayList<students> junior = new ArrayList<>();
   ArrayList<students> senior = new ArrayList<>();
   
   // constructor - holds info for each student
    public students(String studentName, double studentGrade, String studentGradeLetter, double studentOverallGrade, int studentID, String studentEmail, int studentAttendence, char studentHonors, char studentValedictorian, double[] studentGrades, String[] studentClasses1){
        this.name = studentName;
        this.grade = studentGrade;
        this.overallGrade = studentOverallGrade;
        this.id = studentID;
        this.email = studentEmail;
        this.attendence = studentAttendence;
        this.honors = studentHonors;
        this.valedictorian = studentValedictorian;
        this.grades = studentGrades; 
        this.classes1 = studentClasses1;
    }
    public static void viewGrade(){
        
    }
    public static void addStudent(){  
    }
    public static void removeStudent(){
        
    }
    public static void changeYear(){
        
 }   
}
