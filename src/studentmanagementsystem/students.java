package studentmanagementsystem;
import java.util.ArrayList;
import java.util.Arrays;
public class students {
   String name;
   double grade;
   String gradeLetter;
   double overallGrade;
   int id;
   String email;
   int attendence;
   char honors;
   char valedictorian;
   int grade1;
   int grade2;
   int grade3;
   int grade4;
   int grade5;
   int grade6;
   int grade7;
   String class1;
   String class2;
   String class3;
   String class4;
   String class5;
   String class6;
   String class7;
   
   // all classes students could take
   ArrayList<String> classes = new ArrayList<String>();

   // holds each grades students
   ArrayList<students> freshmen = new ArrayList<>();
   ArrayList<students> softmore = new ArrayList<>();
   ArrayList<students> junior = new ArrayList<>();
   ArrayList<students> senior = new ArrayList<>();
   
    public students(String studentName, double studentGrade, String studentGradeLetter, double studentOverallGrade, int studentID, String studentEmail, int studentAttendence, char studentHonors, char studentValedictorian
){
        this.name = studentName;
        this.grade = studentGrade;
        this.overallGrade = studentOverallGrade;
        this.id = studentID;
        this.email = studentEmail;
        this.attendence = studentAttendence;
        this.honors = studentHonors;
        this.valedictorian = studentValedictorian;
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
