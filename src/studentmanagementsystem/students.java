package studentmanagementsystem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class students {
public String name;
static public int grade;
private String gradeLetter;
private double overallGrade;
private int id;
public String email;
private int absences;
private char honors;
private char valedictorian;
private double[] grades = new double[7]; 
private String[] classes1 = new String[7]; 

   
static boolean addStudentLoop = true;
static Scanner input = new Scanner(System.in);

   // all classes students could take
   ArrayList<String> classes = new ArrayList<String>();

   static ArrayList<Integer> IDS = new ArrayList<Integer>();
   
   
   // holds each grades students
   static ArrayList<students> freshmen = new ArrayList<>();
   static ArrayList<students> softmore = new ArrayList<>();
   static ArrayList<students> junior = new ArrayList<>();
   static ArrayList<students> senior = new ArrayList<>();
   static ArrayList<ArrayList> school = new ArrayList<>();
   
   // constructor - holds info for each student
    public students(String studentName, int studentGrade, double studentOverallGrade, int studentID, String studentEmail, int studentAbsences, char studentHonors, char studentValedictorian, double[] studentGrades, String[] studentClasses1){
        this.name = studentName;
        this.grade = studentGrade;
        this.overallGrade = studentOverallGrade;
        this.id = studentID;
        this.email = studentEmail;
        this.absences = studentAbsences;
        this.honors = studentHonors;
        this.valedictorian = studentValedictorian;
        this.grades = studentGrades; 
        this.classes1 = studentClasses1;
    }
    
    public static void viewGrade(){
        
    }
    public static students addStudent(){ 
        // gets students name & then makes that into a school email
        System.out.print("Student First Name: ");
        String answerFirstName = input.nextLine().trim();
        System.out.print("Student Middle Initial: ");
        String answerMiddleName = input.nextLine().trim();
        System.out.print("Student Last Name: ");
        String answerLastName = input.nextLine().trim();
        String studentName = answerFirstName + " " + answerMiddleName.toUpperCase() + " " + answerLastName;
        String studentEmail = answerFirstName.substring(0, 0) + answerMiddleName.substring(0, 0) +
       answerLastName + "@mail." + StudentManagementSystem.userSchoolName.trim();
        
        
        
        // gets students grade
        System.out.println("What Grade Is This Student In? (9,10,11,or 12)");
        int answerStudentGrade = input.nextInt();
        int studentGrade = answerStudentGrade;
        
        
        // makes sure studentID cannot be the same as another student
        int studentID = 0000000;
        int randomNum = 1 + (int)(Math.random()*99999);
        int i = 0;
        while (i < IDS.size()){
        if (randomNum != IDS.get(i)){
            studentID = randomNum;
            IDS.add(studentID);
        }
        else {
            randomNum = 1 + (int)(Math.random()*999999);
            i--; 
     }
    }
        // starts at 0;
        int studentOverallGrade = 0;
        
        // start with 0
        int studentAbsences = 0;
        
        // starts without these awards
        char studentHonors = 'N';
        char studentValedictorian = 'N';
        
        // every value set at 0.0
        double[]studentGrades = {0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        
        // every value starts empty
        String[]studentClasses1 = {null,null,null,null,null,null,null};
        
        
        students student = new students(studentName, studentGrade, studentOverallGrade, studentID, studentEmail, studentAbsences, studentHonors, studentValedictorian, studentGrades, studentClasses1);
        return student;
    }
    public static void addStudentsToGrade(){
        if (grade == 9){
        students.freshmen.add(students.addStudent());
        } else if(grade == 10){  
        students.softmore.add(students.addStudent());
        } else if (grade == 11){
         students.junior.add(students.addStudent());   
        } else if (grade == 12){
        students.senior.add(students.addStudent());
        } else{
            System.out.println("Could Not Add Student To System. Wrong Grade Input...");
      } 
    }
    public static void removeStudent(){
        
    }
    public static void changeYear(){
        
 }   
}
