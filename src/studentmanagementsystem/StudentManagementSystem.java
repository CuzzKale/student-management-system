package studentmanagementsystem;
import java.util.Scanner;
public class StudentManagementSystem {
static boolean menu = true;
static String userSchoolName; 
static boolean IDmakerLoop = false;

    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    // intro
    System.out.println("Welcome To ClassHelper, Your Application For Everything Student Management."
            + "What Is The Name Of Your School?");
    String schoolName = input.nextLine();
    userSchoolName = schoolName;
    
    // main menu
    while (menu){
    System.out.println("Type 1: View Grades\n"
            + "Type 2: Change Student Info\n"
            + "Type 3: Add Student\n"
            + "Type 4: Remove Student Or Entire Grade\n"
            + "Type 5: Change Year");
    
    int menuInput = input.nextInt();
    
    switch (menuInput){
        case 1:      
            break;
        case 2:
            break;
        case 3:
            while (IDmakerLoop){
            students.addStudent();
            students.addStudentsToGrade();
            
            System.out.println("Do You Want To Add Another Student? (Y/N) ");
            String IDmakerLoopAnswer = input.nextLine();
            
            if (IDmakerLoopAnswer.equalsIgnoreCase("Yes") || IDmakerLoopAnswer.equalsIgnoreCase("Y")){
                IDmakerLoop = true;
            }
           }
           break; 
        case 4:
            break;
        case 5:
            break;
        default:
            break;
   }
  }
 }
}
