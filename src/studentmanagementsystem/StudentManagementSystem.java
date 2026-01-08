package studentmanagementsystem;

import java.util.Scanner;
public class StudentManagementSystem {
static boolean menu = true;
static String userSchoolName; 
static boolean IDmakerLoop = true;

    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    // intro
    System.out.println("Welcome To ClassHelper, Your Application For Everything Student Management."
            + " What Is The Name Of Your School?");
    String schoolName = input.nextLine();
    userSchoolName = schoolName.replaceAll("\\s","");
    
    // main menu
    while (menu){
    System.out.println("Type 1: View Grades\n"
            + "Type 2: Change Student Info\n"
            + "Type 3: Add Student\n"
            + "Type 4: View Student\n"
            + "Type 5: Remove Student\n"
            + "Type 6: Change Year");
    
    int menuInput = input.nextInt();
    
    switch (menuInput){
        case 1:     
            students.viewGrade();
            break;
        case 2:
            break;
        case 3:
            students.addStudent();
            students.addStudentToSchool();
            students.changeStudent();
           break; 
        case 4:
            break;
        case 5:
            students.removeStudent();
            break;
        default:
            break;
   }
  }
 }
}
