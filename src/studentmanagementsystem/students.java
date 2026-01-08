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
static boolean viewStudentLoop = true;


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
        // empty
        String studentName = null;
        String studentEmail = null;
        int studentGrade = 0;
        
        // makes sure studentID cannot be the same as another student
        int studentID = 1000000;
        
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
        
        // returns object
        students student = new students(studentName, studentGrade, studentOverallGrade, studentID, studentEmail, studentAbsences, studentHonors, studentValedictorian, studentGrades, studentClasses1);
        return student;
    }
    
    public static void changeStudent(){
    // input will be put together for full name and email
        System.out.print("Student First Name: ");
        String answerFirstName = input.nextLine().trim();
        System.out.print("Student Middle Initial: ");
        String answerMiddleName = input.nextLine().trim();
        System.out.print("Student Last Name: ");
        String answerLastName = input.nextLine().trim();
        
        System.out.println("What Grade Is This Student In? (9,10,11,or 12)");
        int answerStudentGrade = input.nextInt();
        input.nextLine();
        // if student info empty fill with entered info
        
        
        
        

        for (int i = 0; i < school.size(); i++){
            if (school.get(i).name == null){
                school.get(i).name = answerFirstName + " " + answerMiddleName + " " + answerLastName;
                school.get(i).email = answerFirstName.substring(0, 1) + answerMiddleName.substring(0, 1) + answerLastName +  "@mail." + StudentManagementSystem.userSchoolName + ".edu";
                school.get(i).grade = answerStudentGrade;  
                school.get(i).id += school.size();   
     }
    }
   }
    // adds student to system
    public static void addStudentToSchool(){
       students.school.add(addStudent());
    }
    
    // view every students information from grade 
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
        
        System.out.println("Type 1: View Student\n"
                + "Type 2: Change Student Information\n"
                + "Type 3: View Report");
        
        int choiceStudent = input.nextInt();
        
       if (choiceStudent == 1){
           while (viewStudentLoop){
               viewStudent();
        System.out.println("Do You Want To View Another Student (Y/N)");
        String viewAnotherStudent = input.nextLine();
         
        if (viewAnotherStudent.equalsIgnoreCase("Yes") || viewAnotherStudent.equalsIgnoreCase("Y")){
            viewStudentLoop = true;
        }
        else if (viewAnotherStudent.equalsIgnoreCase("No") || viewAnotherStudent.equalsIgnoreCase("N"))
            viewStudentLoop = false;
        }   
       }
       else if (choiceStudent == 2){
           // change student method 
       }
       else if (choiceStudent == 3){
           // report method
       }
      }
            
    // view one students information 
    private static void viewStudent(){
        input.nextLine();
        System.out.println("Type The First And Last Name Of The Student You Want To View");
        String viewStudentInput = input.nextLine();
        for (int i = 0; i < school.size(); i++){
            if (school.get(i).name.equalsIgnoreCase(viewStudentInput)){
                System.out.println(school.get(i).name + " | " + school.get(i).email + " | ID: " + school.get(i).id + "\n"
                        + "Class            Grade\n"
                        + "-----            -----");
                for (int p = 0; p < school.get(i).classes1.length; p++){
                   System.out.print(school.get(i).classes1[p]);
                   System.out.println("             " + school.get(i).grades[p]);
                }
              System.out.println("GPA: " + school.get(i).overallGrade + " Absences: " + school.get(i).absences + " Honors: " + school.get(i).honors + " Valedictorian: " + school.get(i).valedictorian);          
      }
     }  
    }
    // input allows for removal of student from database
    public static void removeStudent(){
        input.nextLine();
     System.out.println("Type The First, Middle Initial, and Last Name Of The Student You Want To Delete");   
       String deleteStudentInput = input.nextLine(); 
        for (int i = 0; i < school.size(); i++){
          if (school.get(i).name.equalsIgnoreCase(deleteStudentInput)){  
              school.remove(i);
      }   
     }
    }
    public static void changeYear(){
        
 }   
}
