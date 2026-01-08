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
static boolean viewStudentLoop = false;


static int currentGrade; 
   
static boolean addStudentLoop = true;
static Scanner input = new Scanner(System.in);

   // all classes students could take
   ArrayList<String> classes = new ArrayList<String>();

   static ArrayList<Integer> IDS = new ArrayList<Integer>();
   
   
   // holds each grades students
   static ArrayList<students> school = new ArrayList<>();
   
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
    
    public static students addStudent(){ 
        String studentName = null;
        String studentEmail = null;
        int studentGrade = 0;
        
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
    
    public static void changeStudent(){
    // gets students name & then makes that into a school email
        System.out.print("Student First Name: ");
        String answerFirstName = input.nextLine().trim();
        System.out.print("Student Middle Initial: ");
        String answerMiddleName = input.nextLine().trim();
        System.out.print("Student Last Name: ");
        String answerLastName = input.nextLine().trim();
        
        System.out.println("What Grade Is This Student In? (9,10,11,or 12)");
        int answerStudentGrade = input.nextInt();
        
        for (int i = 0; i < school.size(); i++){
            if (school.get(i).name == null){
                school.get(i).name = answerFirstName + " " + answerMiddleName + " " + answerLastName;
                school.get(i).email = answerFirstName.substring(0, 0) + answerMiddleName.substring(0, 0) + answerLastName +  "@mail." + StudentManagementSystem.userSchoolName + ".edu";
                school.get(i).grade = answerStudentGrade;         
     }
    }
   }
    
    public static void addStudentToSchool(){
       students.school.add(addStudent());
    }
    public static void viewGrade(){
    System.out.println("What Grade Would You Like To View(9, 10, 11, or 12)");
            int viewStudentGrade = input.nextInt();  
            
        switch (viewStudentGrade){
            case 9:
                System.out.println("Freshmen: ");
                for (int i = 0; i < school.size(); i++){
                    if (school.get(i).grade == 9){
                        System.out.println((i + 1) + "." + " Name: " + school.get(i).name + " GPA: " + school.get(i).overallGrade + " Absences: " + school.get(i).absences
                         + " Honors: " + school.get(i).honors + " Valedictorian: " + school.get(i).valedictorian);
                    }
                }
                break;
            case 10: 
            System.out.println("Softmore: ");
                for (int i = 0; i < school.size(); i++){
                    if (school.get(i).grade == 10){
                        System.out.println((i + 1) + "." + " Name: " + school.get(i).name + " GPA: " + school.get(i).overallGrade + " Absences: " + school.get(i).absences
                         + " Honors: " + school.get(i).honors + " Valedictorian: " + school.get(i).valedictorian);
                    }
                }
                break;
            case 11: 
             System.out.println("Junior: ");
                for (int i = 0; i < school.size(); i++){
                    if (school.get(i).grade == 11){
                        System.out.println((i + 1) + "." + " Name: " + school.get(i).name + " GPA: " + school.get(i).overallGrade + " Absences: " + school.get(i).absences
                         + " Honors: " + school.get(i).honors + " Valedictorian: " + school.get(i).valedictorian);
                    }
                }
                break;   
            case 12:
            System.out.println("Seniors: ");
                for (int i = 0; i < school.size(); i++){
                    if (school.get(i).grade == 12){
                        System.out.println((i + 1) + "." + " Name: " + school.get(i).name + " GPA: " + school.get(i).overallGrade + " Absences: " + school.get(i).absences
                         + " Honors: " + school.get(i).honors + " Valedictorian: " + school.get(i).valedictorian);
                    }
                }
                break;   
            
        } 
       }
            
    
    private static void viewStudent(){
        
    }
    public static void removeStudent(){
        
    }
    public static void changeYear(){
        
 }   
}
