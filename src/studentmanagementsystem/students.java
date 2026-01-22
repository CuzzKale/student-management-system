package studentmanagementsystem;
import java.sql.*;
import org.h2.tools.Server;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
public class students{
public String name;
public int grade;
private String gradeLetter;
private double overallGrade;
private int id;
public String email;
private int absences;
private char honors;
private char valedictorian;
static boolean viewStudentLoop = true;
static boolean addClassesLoop = true;
static boolean mainClassLoop = true;
static boolean initialChange = false;
static boolean afterChange = false; 
static boolean afterChangeLoop = true;
static boolean addStudentLoop = true;
static boolean reportsLoop = true;
static boolean changeClassesLoop = true; 
static boolean changeStudentGrades = true;
static int index;
static Scanner input = new Scanner(System.in);
static int year = 2026;

    


    public static void addStudent() throws SQLException{
        try (Connection conn = Database.continueConnection()){
                      PreparedStatement psNext = conn.prepareStatement(
    "SELECT COALESCE(MAX(student_Number), 0) + 1 AS nextNumber FROM students"
        );
        ResultSet rsNext = psNext.executeQuery();
        rsNext.next();
        int studentNumber = rsNext.getInt("nextNumber");

        
   
      try{
        try{ 
   try (PreparedStatement ps = conn.prepareStatement("INSERT INTO students (student_Number, name, grade, overallGrade, email, absences, honors, valedictorian) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")){
     
  
    // input will be put together for full name and email
        System.out.print("Student First Name: ");
        String answerFirstName = input.nextLine().trim();
        System.out.print("Student Middle Initial: ");
        String answerMiddleName = input.nextLine().trim();
        System.out.print("Student Last Name: ");
        String answerLastName = input.nextLine().trim();
        String fullName = answerFirstName + " " + answerMiddleName + " " + answerLastName;
         String email = answerFirstName.substring(0, 1) + answerMiddleName.substring(0, 1) + answerLastName +  "@mail." + StudentManagementSystem.userSchoolName + ".edu";
        
   
        
        System.out.println("What Grade Is This Student In? (9,10,11,or 12)");
        String answerStudentGradeString = input.nextLine().trim();
        int answerStudentGrade = Integer.parseInt(answerStudentGradeString);

    // database student gains those values
        ps.setInt(1, studentNumber);
        ps.setString(2, fullName);
        ps.setInt(3, answerStudentGrade);
        ps.setDouble(4, 0.0);
        ps.setString(5, email);
        ps.setInt(6, 0);
        ps.setString(7, "N");
        ps.setString(8, "N");
        
        ps.executeUpdate();
        

   }
        try (PreparedStatement tr = conn.prepareStatement("INSERT INTO classes1 (student_Number, classOne, classTwo, classThree, classFour, classFive, classSix, classSeven"
                    + ") VALUES (?, 'BLANK', 'BLANK', 'BLANK', 'BLANK', 'BLANK', 'BLANK', 'BLANK')")){
            
            tr.setInt(1, studentNumber);
            tr.executeUpdate();
         
           // Insert blank grades for all of the students classes
                       
        }
        try (PreparedStatement ur = conn.prepareStatement("INSERT INTO studentGrades (student_Number, gradeOne, gradeTwo, gradeThree, gradeFour, gradeFive, gradeSix, gradeSeven"
                    + ") VALUES (?, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)")){
            ur.setInt(1, studentNumber);
            ur.executeUpdate();
        }
        
        } catch (StringIndexOutOfBoundsException e){
           System.out.println("Invalid Input. Try Again..."); 
     }
    }catch (NumberFormatException e){
            System.out.println("Invalid Input. Try Again...");
 }
        } catch (SQLException e){
                e.printStackTrace();
  }
    }
    
         
        

 

    
    public static void changeStudent() throws SQLException{
       
    afterChangeLoop = true;
    try{
        // student that will be changed
            System.out.print("Student id: ");
            String stringCheckId = input.nextLine().trim();
            int checkId = Integer.parseInt(stringCheckId);
            
            while (afterChangeLoop){
               System.out.println(
                         " ------------------------------\n"
                       + "|Type 1: View Student          |\n"
                       + "|Type 2: Change Name           |\n"
                       + "|Type 3: Change Grade          |\n"
                       + "|Type 4: Change Absences       |\n"
                       + "|Type 5: Change Schedule       |\n"
                       + "|Type 6: Change Student Grades |\n"
                       + "|Type 7: Quit Back To Main Menu|\n"
                       + " ------------------------------");
                try{
                    String stringAfterChangeLoopAnswer = input.nextLine().trim();
               int afterChangeLoopAnswer = Integer.parseInt(stringAfterChangeLoopAnswer);
                
               
             switch (afterChangeLoopAnswer){
                 
                 case 1:
                     studentUpdater();
                     viewStudent();
                 break;
                 // changes name
                 case 2:
                     System.out.print("Student First Name: ");
                      String answerFirstName = input.nextLine().trim();
                      System.out.print("Student Middle Initial: ");
                      String answerMiddleName = input.nextLine().trim();
                      System.out.print("Student Last Name: ");
                      String answerLastName = input.nextLine().trim();
                      String fullName = answerFirstName + " " + answerMiddleName + " " + answerLastName;
                      String email = answerFirstName.substring(0, 1) + answerMiddleName.substring(0, 1) + answerLastName +  "@mail." + StudentManagementSystem.userSchoolName + ".edu";
                     try(Connection conn = Database.continueConnection(); PreparedStatement ps = conn.prepareStatement("UPDATE students SET name = ?, email = ? WHERE student_Number = ?")){
                         ps.setInt(3, checkId);
                         ps.setString(1,  fullName);
                         ps.setString(2, email);
                         ps.executeUpdate();
                     }
                     break;
                     // changes grade
                 case 3:
                     System.out.print("Change Grade(9,10,11,12): ");
                     try{
                     String stringGradeChange = input.nextLine().trim();
                     int gradeChange = Integer.parseInt(stringGradeChange);
                     
                     if (gradeChange <= 12 && gradeChange >= 9){
                         try (Connection conn = Database.continueConnection(); PreparedStatement ps = conn.prepareStatement("UPDATE students SET grade = ? WHERE student_Number = ?")){ 
                            ps.setInt(1, gradeChange);
                            ps.setInt(2, checkId);   
                            ps.executeUpdate();
                                 }
                      }    
                     
                     else{
                         System.out.println("Invalid Input...");
                     }
                     }catch (NumberFormatException e){
                    System.out.println("Invalid Input. Try Again...");
                    }
                     break;
                    
                 // changes absences
                 case 4:
                     try (Connection conn = Database.continueConnection(); PreparedStatement ps = conn.prepareStatement("SELECT name, absences FROM students WHERE student_Number = ?")){
                        ps.setInt(1, checkId);
                     
                     try(ResultSet rs = ps.executeQuery()){
                         
                     while (rs.next()){
                         int absenses = rs.getInt("absences");
                         String theName = rs.getString("name");
                     
                     System.out.println(theName + "'s Absenses: " + absenses);
                     System.out.print("New Number Of Absenses: ");
                     }
                     }
                     }
                     
                     try (Connection conn = Database.continueConnection(); PreparedStatement ps = conn.prepareStatement("UPDATE students SET absences = ? WHERE student_Number = ?")){
                     try{   
                     String stringNewAbsenses = input.nextLine().trim();
                     int newAbsenses = Integer.parseInt(stringNewAbsenses);
                     ps.setInt(1, newAbsenses);
                     ps.setInt(2, checkId);
                     ps.executeUpdate();
                     }
                     catch (NumberFormatException e){
                    System.out.println("Invalid Input. Try Again...");
                     }
                     }
                     break;
                    
                     // change schedule
                 case 5:
                    changeClassesLoop = true; 
                     while (changeClassesLoop){
                    System.out.println("Available Classes: ");
                     try (Connection conn = Database.continueConnection(); Statement stmt = conn.createStatement()){
                    ResultSet rs = stmt.executeQuery("SELECT * FROM classes");
                    
                    while (rs.next()){
                       int pickId = rs.getInt("student_number"); 
                       String theClass = rs.getString("class");
                       System.out.println(pickId + ". " + theClass);                 
             }
            } 
                     
                    System.out.println("Current Schedule: ");
                    try (Connection conn = Database.continueConnection(); PreparedStatement ui = conn.prepareStatement("SELECT * FROM classes1 WHERE student_Number = ?")){
                        ui.setInt(1, checkId);
                    ResultSet rs = ui.executeQuery();
                    while (rs.next()){
                     String classOne = rs.getString("classOne");
                     String classTwo = rs.getString("classTwo");
                     String classThree = rs.getString("classThree");
                     String classFour = rs.getString("classFour");
                     String classFive = rs.getString("classFive");
                     String classSix = rs.getString("classSix");
                     String classSeven = rs.getString("classSeven");
                     
                     
                     System.out.println("1. " + classOne + "\n2. " + classTwo + "\n3. " + classThree + "\n4. " + classFour
                     + "\n5. " + classFive + "\n6. " + classSix + "\n7. " + classSeven);
                    }   
                   }
                    System.out.print("Type q To Quit\n"
                            + "Number Of Class To Change: ");
                    try{
                    try{
                    try{
                    String stringClassToChange = input.nextLine().trim();
                    if (stringClassToChange.equalsIgnoreCase("Q") || stringClassToChange.equalsIgnoreCase("Quit")){
                        changeClassesLoop = false;
                        break;
                    }
                    int classToChange = Integer.parseInt(stringClassToChange);
                    System.out.print("New Class(Choose From Available Classes): ");
                    String stringNewClass = input.nextLine().trim();
                    int newClass = Integer.parseInt(stringNewClass);
                    
                    HashMap<Integer, String> schedulePick = new HashMap<Integer, String>();
                    schedulePick.put(1, "classOne");
                    schedulePick.put(2, "classTwo");
                    schedulePick.put(3, "classThree");
                    schedulePick.put(4, "classFour");
                    schedulePick.put(5, "classFive");
                    schedulePick.put(6, "classSix");
                    schedulePick.put(7, "classSeven");
                    // gets the column to change inside classes1
                    String column = schedulePick.get(classToChange);
                    
                    String classToUpdate = null;
                    // finds class name based on num input
                     try (Connection conn = Database.continueConnection(); Statement stmt = conn.createStatement()){
                            ResultSet rs = stmt.executeQuery("SELECT * FROM classes");
                       while (rs.next()){
                          int newClassId = rs.getInt("student_Number");
                          String newClassName = rs.getString("class");
                           if (newClass == newClassId){
                          classToUpdate = newClassName;
                           }
                       }
                        }
                     // assigns the name of the class to the column of classes1 
                    try (Connection conn = Database.continueConnection(); PreparedStatement ps = conn.prepareStatement("UPDATE classes1 SET " + column + " = ? WHERE student_Number = ?")){
                        
                      
                      ps.setInt(2, checkId);
                      ps.setString(1, classToUpdate);
                      ps.executeLargeUpdate();
                    }
                    System.out.println("-------------------------------------------------------------------");
                  
                    }catch (ArrayIndexOutOfBoundsException e){
                      System.out.println("Invalid Input. Try Again...");  
                    }
                    }catch (NumberFormatException e){
                    System.out.println("Invalid Input. Try Again...");
                    }
                     } catch (IndexOutOfBoundsException e){
                    System.out.println("Invalid Input. Try Again...");     
                     }
                     }
                     break;
                 
                 case 6:
                     changeStudentGrades = true;
                     while (changeStudentGrades){
                         
                         // shows class schedule and their grades 
                  System.out.println("   Class            Grade\n"
                        + "   -----            -----");
                  String classOne  = null;
                  String classTwo = null;
                  String classThree = null;
                  String classFour = null;
                  String classFive = null;                          
                  String classSix = null;  
                  String classSeven = null;
                double studentGradeOne = 0.0;
                double studentGradeTwo = 0.0;
                double studentGradeThree = 0.0;
                double studentGradeFour = 0.0;
                double studentGradeFive = 0.0;
                double studentGradeSix = 0.0;
                double studentGradeSeven = 0.0;     
                try (Connection conn = Database.continueConnection(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM classes1 WHERE student_Number = ?")){
                    ps.setInt(1, checkId);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()){
                     classOne = rs.getString("classOne");
                     classTwo = rs.getString("classTwo");
                     classThree = rs.getString("classThree");
                     classFour = rs.getString("classFour");
                     classFive = rs.getString("classFive");
                     classSix = rs.getString("classSix");
                     classSeven = rs.getString("classSeven");
                    }
                }
                
                   
                try (Connection conn = Database.continueConnection(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM studentGrades WHERE student_Number = ?")){
                    ps.setInt(1, checkId);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()){
                     studentGradeOne = rs.getDouble("gradeOne");
                     studentGradeTwo = rs.getDouble("gradeTwo");
                     studentGradeThree = rs.getDouble("gradeThree");
                     studentGradeFour = rs.getDouble("gradeFour");
                     studentGradeFive = rs.getDouble("gradeFive");
                     studentGradeSix = rs.getDouble("gradeSix");
                     studentGradeSeven = rs.getDouble("gradeSeven");
                    }
                }
                     System.out.println("1. " + classOne + "             " + studentGradeOne + "\n2. " + classTwo +
                     "             " + studentGradeTwo + "\n3. " + classThree + "             " + studentGradeThree + "\n4. "
                     + classFour + "             " + studentGradeFour + "\n5. " + classFive + "             " + studentGradeFive + "\n6. "
                     + classSix + "             " + studentGradeSix + "\n7. " + classSeven + "             " + studentGradeSeven);
                       
                   
                System.out.print("Type q To Quit\n"
                        + "Number Of Class To Change Grade In: ");
                try{
                    try{
                String stringChangeGrade = input.nextLine().trim();
                if (stringChangeGrade.equalsIgnoreCase("Q") || stringChangeGrade.equalsIgnoreCase("Quit")){
                 changeClassesLoop = false;
                        break;   
                }
                
                
                
                
                
               
                int changeGrade = Integer.parseInt(stringChangeGrade);
                System.out.print("New Grade: ");
                String stringUpdatedGrade = input.nextLine().trim();
                double updatedGrade = Integer.parseInt(stringUpdatedGrade);
                
                // creates hashmap of all of studentGrades collumns for UPDATE statement
                HashMap<Integer, String> studentGradeNum = new HashMap<Integer, String>();
                studentGradeNum.put(1, "gradeOne");
                studentGradeNum.put(2, "gradeTwo");
                studentGradeNum.put(3, "gradeThree");
                studentGradeNum.put(4, "gradeFour");
                studentGradeNum.put(5, "gradeFive");
                studentGradeNum.put(6, "gradeSix");
                studentGradeNum.put(7, "gradeSeven");
                
                // gets collumn based on input int 
                String gradeColumn = studentGradeNum.get(changeGrade);
                        
                
              try (Connection conn = Database.continueConnection(); PreparedStatement ps = conn.prepareStatement("UPDATE studentGrades SET " + gradeColumn  + "= ? WHERE student_Number = ?")){
                  ps.setDouble(1, updatedGrade);
                  ps.setInt(2, checkId);
                  ps.executeUpdate();
              }
                    } catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("Invalid Input. Try Again...");
                    }
                }catch (NumberFormatException e){
                    System.out.println("Invalid Input. Try Again...");
                    }
                     }
                 break;
                 
                 case 7:
                 afterChangeLoop = false;
                 afterChange = false;
                 break;
             
             }
             
             
             
                
            } catch (NumberFormatException e){
            System.out.println("Invalid Input. Try Again...");
       
    }
   
    
            }     
     }  catch (NumberFormatException e){
         System.out.println("Invalid Input. Try Again...");
 }
}



    
    

    // view every students information from grade 
    public static void viewGrade() throws SQLException{
        
        
        
    System.out.println("What Grade Would You Like To View?(9, 10, 11, or 12)");
    
    try {   
        String stringViewStudentGrade = input.nextLine().trim();
            int viewStudentGrade = Integer.parseInt(stringViewStudentGrade);
            
        switch (viewStudentGrade){
            //freshmen
            case 9:
                
                System.out.println("Freshmen: ");
                try (Connection conn = Database.continueConnection(); Statement stmt = conn.createStatement()){
                    int i = 1;
                    ResultSet rs = stmt.executeQuery("SELECT * FROM students WHERE grade = 9");
                    while (rs.next()){
                    int studentId = rs.getInt("student_Number");
                    String studentName = rs.getString("name");
                    double studentOverallGrade = rs.getDouble("overallGrade");
                    int studentAbsences = rs.getInt("absences");
                    String studentHonors = rs.getString("honors");
                    String studentValedictorian = rs.getString("valedictorian");
                    
                    System.out.println(i + ". " + "ID: " + studentId + " Name: " + studentName + " GPA: " + studentOverallGrade + " Absences: " + studentAbsences
                    + " Honors: " + studentHonors + " Valedictorian: " + studentValedictorian);
                    System.out.println("----------------------------------------------------------------------");
                    i++;
                    }
                }
                break;
            case 10: 
            System.out.println("Softmores: ");
               try (Connection conn = Database.continueConnection(); Statement stmt = conn.createStatement()){
                    int i = 1;
                    ResultSet rs = stmt.executeQuery("SELECT * FROM students WHERE grade = 10");
                    while (rs.next()){
                    int studentId = rs.getInt("student_Number");
                    String studentName = rs.getString("name");
                    double studentOverallGrade = rs.getDouble("overallGrade");
                    int studentAbsences = rs.getInt("absences");
                    String studentHonors = rs.getString("honors");
                    String studentValedictorian = rs.getString("valedictorian");
                    
                    System.out.println(i + ". " + "ID: " + studentId + " Name: " + studentName + " GPA: " + studentOverallGrade + " Absences: " + studentAbsences
                    + " Honors: " + studentHonors + " Valedictorian: " + studentValedictorian);
                    System.out.println("----------------------------------------------------------------------");
                    i++;
                    }
                } 
                break;
            case 11: 
             System.out.println("Juniors: ");
                try (Connection conn = Database.continueConnection(); Statement stmt = conn.createStatement()){
                    int i = 1;
                    ResultSet rs = stmt.executeQuery("SELECT * FROM students WHERE grade = 11");
                    while (rs.next()){
                    int studentId = rs.getInt("student_Number");
                    String studentName = rs.getString("name");
                    double studentOverallGrade = rs.getDouble("overallGrade");
                    int studentAbsences = rs.getInt("absences");
                    String studentHonors = rs.getString("honors");
                    String studentValedictorian = rs.getString("valedictorian");
                    
                    System.out.println(i + ". " + "ID: " + studentId + " Name: " + studentName + " GPA: " + studentOverallGrade + " Absences: " + studentAbsences
                    + " Honors: " + studentHonors + " Valedictorian: " + studentValedictorian);
                    System.out.println("----------------------------------------------------------------------");
                    i++;
                    }
                } 
                break;   
            case 12:
            System.out.println("Seniors: ");
                try (Connection conn = Database.continueConnection(); Statement stmt = conn.createStatement()){
                    int i = 1;
                    ResultSet rs = stmt.executeQuery("SELECT * FROM students WHERE grade = 12");
                    while (rs.next()){
                    int studentId = rs.getInt("student_Number");
                    String studentName = rs.getString("name");
                    double studentOverallGrade = rs.getDouble("overallGrade");
                    int studentAbsences = rs.getInt("absences");
                    String studentHonors = rs.getString("honors");
                    String studentValedictorian = rs.getString("valedictorian");
                    
                    System.out.println(i + ". " + "ID: " + studentId + " Name: " + studentName + " GPA: " + studentOverallGrade + " Absences: " + studentAbsences
                    + " Honors: " + studentHonors + " Valedictorian: " + studentValedictorian);
                    System.out.println("----------------------------------------------------------------------");
                    i++;
                    }
                }
                break;   
        }
        }catch (NumberFormatException e){
                    System.out.println("Invalid Input. Try Again...");
                    }
        
        System.out.println(
                 " ----------------------------------\n"
                +"|Type 1: View Student              |\n"
                +"|Type 2: Change Student Information|\n"
                +"|Type 3: View Grade Report         |\n"
                +"|Type 4: Exit To Main Menu         |\n"
                + " ----------------------------------");
        try{
        String stringChoiceStudent = input.nextLine().trim();
        int choiceStudent = Integer.parseInt(stringChoiceStudent);
        
       if (choiceStudent == 1){
           viewStudentLoop = true;
           while (viewStudentLoop){
               viewStudent();
        System.out.println("Do You Want To View Another Student (Y/N)");
        String viewAnotherStudent = input.nextLine().trim();
         
        if (viewAnotherStudent.equalsIgnoreCase("Yes") || viewAnotherStudent.equalsIgnoreCase("Y")){
            viewStudentLoop = true;
        }
        else if (viewAnotherStudent.equalsIgnoreCase("No") || viewAnotherStudent.equalsIgnoreCase("N"))
            viewStudentLoop = false;
        }   
       }
       else if (choiceStudent == 2){
           students.afterChange = true;
           changeStudent();
       }
       // back to main menu
       else if (choiceStudent == 3){
           reports();
          viewStudentLoop = false; 
       }
       else if (choiceStudent == 4){
           viewStudentLoop = false; 
       }
      }catch (NumberFormatException e){
                    System.out.println("Invalid Input. Try Again...");
                    }
    }   
    // view one students information 
    private static void viewStudent() throws SQLException{
        System.out.println("Student ID: ");
        try{
        String stringViewStudentInput = input.nextLine().trim();
        int viewStudentInput = Integer.parseInt(stringViewStudentInput);
        try (Connection conn = Database.continueConnection(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM students WHERE student_Number = ?")){
            ps.setInt(1, viewStudentInput);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                String studentName = rs.getString("name");
                String studentEmail = rs.getString("email");
                int studentID = rs.getInt("student_Number");
                
                System.out.println(studentName + " | " + studentEmail + " | " + studentID);
            }
        }
                 System.out.println("   Class            Grade\n"
                        + "   -----            -----");
                  String classOne  = null;
                  String classTwo = null;
                  String classThree = null;
                  String classFour = null;
                  String classFive = null;                          
                  String classSix = null;  
                  String classSeven = null;
                double studentGradeOne = 0.0;
                double studentGradeTwo = 0.0;
                double studentGradeThree = 0.0;
                double studentGradeFour = 0.0;
                double studentGradeFive = 0.0;
                double studentGradeSix = 0.0;
                double studentGradeSeven = 0.0;     
                try (Connection conn = Database.continueConnection(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM classes1 WHERE student_Number = ?")){
                    ps.setInt(1, viewStudentInput);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()){
                     classOne = rs.getString("classOne");
                     classTwo = rs.getString("classTwo");
                     classThree = rs.getString("classThree");
                     classFour = rs.getString("classFour");
                     classFive = rs.getString("classFive");
                     classSix = rs.getString("classSix");
                     classSeven = rs.getString("classSeven");
                    }
                }
                
                   
                try (Connection conn = Database.continueConnection(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM studentGrades WHERE student_Number = ?")){
                    ps.setInt(1, viewStudentInput);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()){
                     studentGradeOne = rs.getDouble("gradeOne");
                     studentGradeTwo = rs.getDouble("gradeTwo");
                     studentGradeThree = rs.getDouble("gradeThree");
                     studentGradeFour = rs.getDouble("gradeFour");
                     studentGradeFive = rs.getDouble("gradeFive");
                     studentGradeSix = rs.getDouble("gradeSix");
                     studentGradeSeven = rs.getDouble("gradeSeven");
                    }
                }
                     System.out.println("1. " + classOne + "             " + studentGradeOne + "\n2. " + classTwo +
                     "             " + studentGradeTwo + "\n3. " + classThree + "             " + studentGradeThree + "\n4. "
                     + classFour + "             " + studentGradeFour + "\n5. " + classFive + "             " + studentGradeFive + "\n6. "
                     + classSix + "             " + studentGradeSix + "\n7. " + classSeven + "             " + studentGradeSeven);
                     
        } catch (NumberFormatException e){
                    System.out.println("Invalid Input. Try Again...");
                    }
    }
    // input allows for removal of student from database
    public static void removeStudent() throws SQLException{
     System.out.println("Type The ID Of The Student You Want To Delete");  
     try{
     String stringDeleteStudentInput = input.nextLine().trim();
     
     
       int deleteStudentInput = Integer.parseInt(stringDeleteStudentInput);
       try (Connection conn = Database.continueConnection(); PreparedStatement ps = conn.prepareStatement("DELETE FROM students WHERE student_Number = ?")){
           ps.setInt(1, deleteStudentInput);
           ps.executeUpdate();
      }
       try (Connection conn = Database.continueConnection(); PreparedStatement ps = conn.prepareStatement("DELETE FROM classes1 WHERE student_Number = ?")){
           ps.setInt(1, deleteStudentInput);
           ps.executeUpdate();
       }
       try (Connection conn = Database.continueConnection(); PreparedStatement ps = conn.prepareStatement("DELETE FROM studentGrades WHERE student_Number = ?")){
           ps.setInt(1, deleteStudentInput);
           ps.executeUpdate();
       }
       
     } catch(NumberFormatException e){
                    System.out.println("Invalid Input. Try Again...");
                    }
     
    }
    
    
    
    
    // any changes with classes that students can take
    public static void addClasses() throws SQLException{
        // required for both loops
    addClassesLoop = true;
    mainClassLoop = true;
    
    
    // menu 
    while (mainClassLoop){
    System.out.println(" --------------------------------------\n"
            + "|Type 1: View Current Available Classes|\n"
            + "|Type 2: Add Classes                   |\n"
            + "|Type 3: Change Class Name             |\n"
            + "|Type 4: Delete Class                  |\n"
            + "|Type 5: Quit Back To Main Menu        |\n"
            + " --------------------------------------");
    try{
    String stringAddClassesAnswer = input.nextLine().trim();
    int addClassesAnswer = Integer.parseInt(stringAddClassesAnswer);
    
    switch (addClassesAnswer){
        
        // prints classes
        case 1:
            try (Connection conn = Database.continueConnection(); Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery("SELECT * FROM classes");
                    int i = 1;
                    while (rs.next()){
                       
                       String theClass = rs.getString("class");
                       System.out.println(i + ". " + theClass);
                       i++;                          
             }
            } 
            break;
            
            // loop - add class - press q - exit
        case 2: 
            addClassesLoop = true;
         while (addClassesLoop){
    System.out.print("Type q to quit\n"
            + "Type Class To Add: ");
    String className = input.nextLine().trim();
    // press q = quit
    if (className.equalsIgnoreCase("q") || className.equalsIgnoreCase("quit")){
        addClassesLoop = false;
    }
    else{
   try (Connection conn = Database.continueConnection(); PreparedStatement ps = conn.prepareStatement("INSERT INTO classes (class) VALUES (?)")){
       ps.setString(1, className);
       ps.executeUpdate();
       }
      }
     }
    break;
         
        case 3: 
            // prints all classes with numbers
            try (Connection conn = Database.continueConnection(); Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery("SELECT * FROM classes");
                    
                    while (rs.next()){
                       int pickId = rs.getInt("student_number"); 
                       String theClass = rs.getString("class");
                       System.out.println(pickId + ". " + theClass);                          
             }
            } 
            
            // change class
            System.out.println("Type The Number Of The Class Name You Want To Change");
            try{
            String stringClassChangeInput = input.nextLine().trim();
            int classChangeInput = Integer.parseInt(stringClassChangeInput);
            System.out.print("Type The Change: ");
            String classChangeAnswer = input.nextLine().trim();
            try (Connection conn = Database.continueConnection(); PreparedStatement ps = conn.prepareStatement("UPDATE classes SET class = ? WHERE student_Number = ?")){
                
               ps.setString(1, classChangeAnswer);
               ps.setInt(2, classChangeInput);
               ps.executeUpdate();
            }
            }catch (NumberFormatException e){
            System.out.println("Invalid Input. Try Again...");
            }
           break;
           
           
           
           
           // delete a class based on user choice
        case 4: 
            try (Connection conn = Database.continueConnection(); Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery("SELECT * FROM classes");
                    
                    while (rs.next()){
                      int pickId = rs.getInt("student_number"); 
                       String theClass = rs.getString("class");
                       System.out.println(pickId + ". " + theClass);                       
             }
            } 
            
            
           System.out.println("Type The Number Of The Class Name You Want To Delete");
           try{
           String stringClassDelete = input.nextLine().trim();
           int classDelete = Integer.parseInt(stringClassDelete);
           
           try (Connection conn = Database.continueConnection(); PreparedStatement ps = conn.prepareStatement("DELETE FROM classes WHERE student_Number = ?")){
               ps.setInt(1, classDelete); 
               ps.executeUpdate();
               
            
               
               
                       }
                   
               
           }catch (NumberFormatException e){
            System.out.println("Invalid Input. Try Again...");
            }
            break;
        // exit add class 
        case 5:
        mainClassLoop = false;
        break;
        
        default:
        System.out.println("Invalid Input... Try Again.");
        break;
    }
    } 
    catch (NumberFormatException e){
            System.out.println("Invalid Input. Try Again...");
            }
  }
    }
    // calculates each students GPA 
    private static void calculateGPA(){
        // the whole school
        for (int i = 0; i < school.size(); i++){
            // *IMPORTANT* overallGrade is reset every time so running the method doesnt divide the existing overallGrade  - gets wrong num
            school.get(i).overallGrade = 0;
            double isGPA0 = 0;
            // makes sure GPA is not 0 because cant divide 0/0
            for (int t = 0; t < school.get(i).grades.length; t++){
               isGPA0 += school.get(i).grades[t];
            }
            // if 0 then 0
            if (isGPA0 == 0.0){
                school.get(i).overallGrade = 0.0;
            }
            // calculate gpa based on grades
            else{
            int classAmount = 0;
            for (int p = 0; p < school.get(i).grades.length; p++){
                if (school.get(i).grades[p] != 0.0 && school.get(i).classes1[p] != null){
                    school.get(i).overallGrade += school.get(i).grades[p];
                  classAmount++;                         
               }
            
        }
            school.get(i).overallGrade /= classAmount;
       }   
    }
    }
    // calculates if a student is in honors or not
    private static void calculateHonors(){
        
        for (int g = 0; g < school.size(); g++){
            if (school.get(g).overallGrade >= 87.5){
                school.get(g).honors = 'Y';
            }
            else if (school.get(g).overallGrade < 87.5){
                school.get(g).honors = 'N';
            }
        }
    }
    
    
    // finds the validictorian from each grade
    private static void calculateValidictorian(){
        double highestFreshmenGrade = 0.0;
       double highestSoftmoreGrade = 0.0;
       double highestJuniorGrade = 0.0;
       double highestSeniorGrade = 0.0;
       // calculates the highest GPA from each grade
        for (int w = 0; w < school.size(); w++){
            // 9th grade
            if (school.get(w).grade == 9){
               if (school.get(w).overallGrade > highestFreshmenGrade){
                   highestFreshmenGrade = school.get(w).overallGrade;
             }
            }
            // 10th grade
            if (school.get(w).grade == 10){
                if (school.get(w).overallGrade > highestSoftmoreGrade){
                highestSoftmoreGrade = school.get(w).overallGrade;
            }
           }
            // 11th grade
            if (school.get(w).grade == 11){
                if (school.get(w).overallGrade > highestJuniorGrade){
                    highestJuniorGrade = school.get(w).overallGrade;
             }
            }
            // 12th grade
            if (school.get(w).grade == 12){
                if (school.get(w).overallGrade > highestSeniorGrade){
                    highestSeniorGrade = school.get(w).overallGrade;
          }
         }
        }
        
        
        
        
        // if GPA is equal to highest in grade -> valedictorian 
        for (int y = 0; y < school.size(); y++){
            // 9th grade
            if (school.get(y).grade == 9){
                if (school.get(y).overallGrade == highestFreshmenGrade){
                    school.get(y).valedictorian = 'Y';
                }
                else {
                  school.get(y).valedictorian = 'N';  
             }
            }
            // 10th grade
            if (school.get(y).grade == 10){
                if (school.get(y).overallGrade == highestSoftmoreGrade){
                    school.get(y).valedictorian = 'Y';
                }
                else{
                    school.get(y).valedictorian = 'N';
             }
            }
            // 11th grade
            if (school.get(y).grade == 11){
                if (school.get(y).overallGrade == highestJuniorGrade){
                    school.get(y).valedictorian = 'Y';
                }
                else{
                    school.get(y).valedictorian = 'N';
             }
            }
            // 12th grade
            if (school.get(y).grade == 12){
                if (school.get(y).overallGrade == highestSeniorGrade){
                    school.get(y).valedictorian = 'Y';
                }
                else {
                    school.get(y).valedictorian = 'N';
             }
            }  
           }
          }
    
    
    
    
    // constantly updates students information based on user changes 
    public static void studentUpdater(){
        calculateGPA();
        calculateHonors();
        calculateValidictorian();
          }
    
    
    // updates everything that needs to happen if it is the following year
    public static void changeYear() throws SQLException{
        System.out.print("---WARNING---\n"
        + "- THIS WILL REMOVE ALL SENIORS FROM THE SCHOOL\n"
                + "- FRESHMEN, SOFTMORES, AND JUNIORS MOVED UP A GRADE\n"
        + "- ALL STUDENTS GRADES AND CLASSES WILL BE RESET\n"
                + "- CURRENT VERSION OF APPLICATION DOES NOT KEEP HISTORY OVER YEARS\n"
                + "\n Current Year: " + year + "\n"
                        + " Do You Want To Advance A Year? (Yes/No)");
        String changeYearAnswer = input.nextLine().trim();
        
        
        if (changeYearAnswer.equalsIgnoreCase("Yes") || changeYearAnswer.equalsIgnoreCase("Y")){
            // removes seniors & moves other students up a grade
            try (Connection conn = Database.continueConnection(); Statement stmt = conn.createStatement()){
                ResultSet rs = stmt.executeQuery("SELECT * FROM students WHERE grade = 12");
                // delete all seniors 
                while (rs.next()){
                    int studentId = rs.getInt("student_Number");
            PreparedStatement ps = conn.prepareStatement("DELETE FROM students WHERE student_Number = ?"); 
            ps.setInt(1, studentId);
            ps.executeUpdate();
            
            PreparedStatement is = conn.prepareStatement("DELETE FROM classes1 WHERE student_Number = ?");
            is.setInt(1, studentId);
            is.executeUpdate();
            
            PreparedStatement op = conn.prepareStatement("DELETE FROM studentGrades WHERE student_Number = ?");
            op.setInt(1, studentId);
            op.executeUpdate();
                 
             }
            }
            
            try (Connection conn = Database.continueConnection(); Statement stmt = conn.createStatement()){
                ResultSet rs = stmt.executeQuery("SELECT * FROM students WHERE grade = 9 OR grade = 10 OR grade = 11");
                // add 1 year onto students grades & reset their values 
                while (rs.next()){
               int studentId = rs.getInt("student_Number");
               
               PreparedStatement ps = conn.prepareStatement("UPDATE students SET grade = (grade + 1), overallGrade = 0.0, honors = 'N', valedictorian = 'N',"
                       + " absences = 0 WHERE student_Number = ?");
               ps.setInt(1, studentId);     
               ps.executeUpdate();
               // resets schedule
               PreparedStatement os = conn.prepareStatement("UPDATE classes1 SET classOne = 'BLANK', classTwo = 'BLANK', classThree = 'BLANK',"
                        + "classFour = 'BLANK', classFive = 'BLANK', classSix = 'BLANK', classSeven = 'BLANK' WHERE student_Number = ?");
                        os.setInt(1, studentId);
                        os.executeUpdate();
                   // resets grades     
                PreparedStatement us = conn.prepareStatement("UPDATE studentGrades SET gradeOne = 0.0, gradeTwo = 0.0, gradeThree = 0.0, gradeFour = 0.0, gradeFive = 0.0,"
                        + "gradeSix = 0.0, gradeSeven = 0.0 WHERE student_Number = ?");
                us.setInt(1, studentId);
               us.executeUpdate();
         }     
        }
       }
        else{
           System.out.println("Not Changing Year...");
        }
      }
    
    // displays a summary of a group of students - valedictorians, honors, average gpa, and total students
    public static void reports(){
        reportsLoop = true;
        while (reportsLoop){
            
           System.out.println(
                  " -----------------------\n"
                + "|Type 1: School Report  |\n"
                + "|Type 2: Freshmen Report|\n"
                + "|Type 3: Softmore Report|\n"
                + "|Type 4: Junior Report  |\n"
                + "|Type 5: Senior Report  |\n"
                + "|Type 6: Exit           |\n"
                + " -----------------------");
           try{
           String stringReportAnswer = input.nextLine().trim();
           int reportAnswer = Integer.parseInt(stringReportAnswer);
           switch (reportAnswer){
               // whole school 
               case 1:
                   int totalStudents = 0;
                   int averageGPA = 0;
                   for (int i = 0; i < school.size(); i++){
                       totalStudents++;
                       averageGPA += school.get(i).overallGrade;
                       if (school.get(i).valedictorian == 'Y'){
                    System.out.println("Grade " + school.get(i).grade + " Valedictorian: " + school.get(i).name + "(GPA: " + school.get(i).overallGrade + ")");
                       }
                   }
                   System.out.println("Honors Students: ");
                   for (int p = 0; p < school.size(); p++){
                       if (school.get(p).honors == 'Y'){
                           System.out.println(school.get(p).name + "(GPA: " + school.get(p).overallGrade + ")");
                       }
                   }
                System.out.println("Total Students: " + totalStudents);
                System.out.println("Average GPA: " + (averageGPA / school.size()));
                   break;
                   // freshmen
               case 2:
                   System.out.println("Freshmen Report: ");
                   int freshmenStudents = 0;
                   int averageFreshmenGPA = 0;
                   for (int t = 0; t < school.size(); t++){
                       if (school.get(t).grade == 9){
                       freshmenStudents++;
                       averageFreshmenGPA += school.get(t).overallGrade;
                       if (school.get(t).valedictorian == 'Y'){
                           System.out.println("Valedictorian: " + school.get(t).name + "(GPA: " + school.get(t).overallGrade + ")");
                       }
                       if (school.get(t).honors == 'Y'){
                           System.out.println("Honor Student: " + school.get(t).name + "(GPA: " + school.get(t).overallGrade + ")");
                       }
                        }
                         }
                   System.out.println("Freshmen Students: " + freshmenStudents);
                   System.out.println("Freshmen Average GPA: " + (averageFreshmenGPA/freshmenStudents));
                   break;
                   // softmores
               case 3:
                   System.out.println("Softmore Report: ");
                   int softmoreStudents = 0;
                   int averageSoftmoreGPA = 0;
                   for (int u = 0; u < school.size(); u++){
                       if (school.get(u).grade == 10){
                       softmoreStudents++;
                       averageSoftmoreGPA += school.get(u).overallGrade;
                       if (school.get(u).valedictorian == 'Y'){
                           System.out.println("Valedictorian: " + school.get(u).name + "(GPA: " + school.get(u).overallGrade + ")");
                       }
                       if (school.get(u).honors == 'Y'){
                           System.out.println("Honor Student: " + school.get(u).name + "(GPA: " + school.get(u).overallGrade + ")");
                       }
                        }
                         }
                   System.out.println("Softmore Students: " + softmoreStudents);
                   System.out.println("Softmores Average GPA: " + (averageSoftmoreGPA/softmoreStudents));
                   break;
                   // juniors
               case 4:
                   System.out.println("Juniors Report: ");
                   int juniorStudents = 0;
                   int averageJuniorGPA = 0;
                   for (int j = 0; j < school.size(); j++){
                       if (school.get(j).grade == 11){
                       juniorStudents++;
                       averageJuniorGPA += school.get(j).overallGrade;
                       if (school.get(j).valedictorian == 'Y'){
                           System.out.println("Valedictorian: " + school.get(j).name + "(GPA: " + school.get(j).overallGrade + ")");
                       }
                       if (school.get(j).honors == 'Y'){
                           System.out.println("Honor Student: " + school.get(j).name + "(GPA: " + school.get(j).overallGrade + ")");
                       }
                        }
                         }
                   System.out.println("Junior Students: " + juniorStudents);
                   System.out.println("Juniors Average GPA: " + (averageJuniorGPA/juniorStudents));
                   break;
                   // seniors
               case 5:
                   System.out.println("Seniors Report: ");
                   int seniorStudents = 0;
                   int averageSeniorGPA = 0;
                   for (int s = 0; s < school.size(); s++){
                       if (school.get(s).grade == 12){
                       seniorStudents++;
                       averageSeniorGPA += school.get(s).overallGrade;
                       if (school.get(s).valedictorian == 'Y'){
                           System.out.println("Valedictorian: " + school.get(s).name + "(GPA: " + school.get(s).overallGrade + ")");
                       }
                       if (school.get(s).honors == 'Y'){
                           System.out.println("Honor Student: " + school.get(s).name + "(GPA: " + school.get(s).overallGrade + ")");
                       }
                        }
                         }
                   System.out.println("Senior Students: " + seniorStudents);
                   System.out.println("Seniors Average GPA: " + (averageSeniorGPA/seniorStudents));
                   break;
               case 6:
                   reportsLoop = false; 
                   break;
           }
            }catch (NumberFormatException e){
            System.out.println("Invalid Input. Try Again...");
       }    
      }
     }

}