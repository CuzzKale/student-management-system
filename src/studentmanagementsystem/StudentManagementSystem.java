package studentmanagementsystem;
import java.util.InputMismatchException;
import java.sql.*;
import org.h2.tools.Server;
import java.util.Scanner;
public class StudentManagementSystem {
static boolean menu = true;
static String userSchoolName; 
static boolean IDmakerLoop = true;

    public static void main(String[] args) throws SQLException{
         Scanner input = new Scanner(System.in);
        
       Database.startConnection();

       try (Connection conn = Database.continueConnection(); Statement stmt = conn.createStatement()){
           stmt.execute("CREATE TABLE IF NOT EXISTS students ("
                        + "id INT AUTO_INCREMENT PRIMARY KEY,"
                        + "student_Number INT,"
                        + "name VARCHAR(50),"
                        + "email VARCHAR(50),"
                        + "grade INT,"
                        + "absences INT DEFAULT 0,"
                        + "overallGrade DOUBLE DEFAULT 0.0,"
                        + "honors VARCHAR(1),"
                        + "valedictorian VARCHAR(1))"); 
           stmt.execute("CREATE TABLE IF NOT EXISTS classes1 (id INT AUTO_INCREMENT PRIMARY KEY, student_Number INT, classOne VARCHAR(50), classTwo VARCHAR(50), classThree VARCHAR(50), classFour VARCHAR(50), classFive VARCHAR(50),"
                   + " classSix VARCHAR(50), classSeven VARCHAR(50), FOREIGN KEY (student_Number) REFERENCES students(student_Number) ON DELETE CASCADE)");
           
           stmt.execute("CREATE TABLE IF NOT EXISTS studentGrades (id INT AUTO_INCREMENT PRIMARY KEY, student_Number INT, gradeOne DOUBLE DEFAULT 0.0, gradeTwo DOUBLE DEFAULT 0.0, gradeThree DOUBLE DEFAULT 0.0, gradeFour DOUBLE DEFAULT 0.0,"
                   + "gradeFive DOUBLE DEFAULT 0.0, gradeSix DOUBLE DEFAULT 0.0, gradeSeven DOUBLE DEFAULT 0.0, FOREIGN KEY (student_Number) REFERENCES students(student_Number) ON DELETE CASCADE)");
           
        
           stmt.execute("CREATE TABLE IF NOT EXISTS classes (id INT AUTO_INCREMENT PRIMARY KEY, student_Number INT UNIQUE AUTO_INCREMENT, class VARCHAR(50))");
           
           
           
           
    // intro
    System.out.print("**Welcome To ClassHelper, Your Application For Everything Student Management**\n"
            + "------------------------------------------------------------------------------\n"
            + "What Is The Name Of Your School: ");
    
    String schoolName = input.nextLine().trim();
    userSchoolName = schoolName.replaceAll("\\s","");
      
    
    // main menu
    while (menu){
        // updates students values based on previous input 
     students.studentUpdater();
    System.out.println(" ----------------------------\n"
            + "|Type 1: View Grades         |\n"
            + "|Type 2: Change Student Info |\n"
            + "|Type 3: Add Student         |\n"
            + "|Type 4: Remove Student      |\n"
            + "|Type 5: Add Classes         |\n"
            + "|Type 6: Change Year         |\n"
            + "|Type 7: Get Reports         |\n"
            + "|Type 8: Exit                |\n"
            + " ----------------------------");
    try{
       String stringMenuInput = input.nextLine().trim();
        int menuInput = Integer.parseInt(stringMenuInput);
       
   
    
    
    switch (menuInput){
        case 1:     
            students.viewGrade();
            break;
        case 2:
            students.changeStudent();
            break;
        case 3:
            students.addStudent();
           break; 
        case 4:
            students.removeStudent();
            break;
        case 5:
           students.addClasses();
            break;
        case 6:   
            students.changeYear();
        break;
        case 7:
            students.reports();
            break;
        case 8:
            System.out.println("Thank You For Using ClassHelper!");
            menu = false;
            break;
            
        default:
        System.out.println("Invalid Input. Try Again...");
            break;
   }
     }catch (NumberFormatException e){
            System.out.println("Invalid Input. Try Again...");
            }
   }
  }
 }
}
