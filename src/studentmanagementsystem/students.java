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

   // all classes students could take
   static ArrayList<String> classes = new ArrayList<String>();

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
        if (initialChange){
            
    // input will be put together for full name and email
        System.out.print("Student First Name: ");
        String answerFirstName = input.nextLine().trim();
        System.out.print("Student Middle Initial: ");
        String answerMiddleName = input.nextLine().trim();
        System.out.print("Student Last Name: ");
        String answerLastName = input.nextLine().trim();
        
        
        
        
        
        try{
        try{
        System.out.println("What Grade Is This Student In? (9,10,11,or 12)");
        
        String answerStudentGradeString = input.nextLine().trim();
        int answerStudentGrade = Integer.parseInt(answerStudentGradeString);
        // if student info empty fill with entered info
        
        
        
        

        for (int i = 0; i < school.size(); i++){
            if (school.get(i).name == null){
                school.get(i).name = answerFirstName + " " + answerMiddleName + " " + answerLastName;
                school.get(i).email = answerFirstName.substring(0, 1) + answerMiddleName.substring(0, 1) + answerLastName +  "@mail." + StudentManagementSystem.userSchoolName + ".edu";
                school.get(i).grade = answerStudentGrade;  
                school.get(i).id += school.size();   
      }
     }
    } catch (StringIndexOutOfBoundsException e){
           System.out.println("Invalid Input. Try Again..."); 
     }
    }catch (NumberFormatException e){
            System.out.println("Invalid Input. Try Again...");
            }
        initialChange = false;
   }
        
        
        
        
        
        
        
        
        
        
        else if (afterChange){
            
            afterChangeLoop = false;
            System.out.println("Type The First, Middle Initial, And Last Name Of The Student");
            String afterChangeName = input.nextLine().trim();
          
            for (int p = 0; p < school.size(); p++){
                if (school.get(p).name.equalsIgnoreCase(afterChangeName)){
                    index = p; 
                    students.afterChangeLoop = true;
                }
            }
            
            while (afterChangeLoop){
               System.out.println(
                         " ------------------------------\n"
                       + "|Type 1: View Student          |\n"
                       + "|Type 2: Change Name           |\n"
                       + "|Type 3: Change Grade          |\n"
                       + "|Type 4: Change ID             |\n"
                       + "|Type 5: Change Absences       |\n"
                       + "|Type 6: Change Schedule       |\n"
                       + "|Type 7: Change Student Grades |\n"
                       + "|Type 8: Quit Back To Main Menu|\n"
                       + " ------------------------------");
                try{
                    String stringAfterChangeLoopAnswer = input.nextLine().trim();
               int afterChangeLoopAnswer = Integer.parseInt(stringAfterChangeLoopAnswer);
                
               
             switch (afterChangeLoopAnswer){
             
                
                 
                 
                 case 1:
                     viewStudent();
                 break;
                 case 2:
                      System.out.print("Student First Name: ");
                      String answerFirstName = input.nextLine().trim();
                      System.out.print("Student Middle Initial: ");
                      String answerMiddleName = input.nextLine().trim();
                      System.out.print("Student Last Name: ");
                      String answerLastName = input.nextLine().trim();
                     school.get(index).name = answerFirstName + " " + answerMiddleName + " " + answerLastName; 
                     school.get(index).email = answerFirstName.substring(0, 1) + answerMiddleName.substring(0, 1) + answerLastName +  "@mail." + StudentManagementSystem.userSchoolName + ".edu";
                     break;
                 case 3:
                     System.out.print("Change Grade(9,10,11,12): ");
                     try{
                     String stringGradeChange = input.nextLine().trim();
                     int gradeChange = Integer.parseInt(stringGradeChange);
                     
                     if (gradeChange <= 12 && gradeChange >= 9){
                         school.get(index).grade = gradeChange;
                     }
                     else{
                         System.out.println("Invalid Input...");
                     }
                     }catch (NumberFormatException e){
                    System.out.println("Invalid Input. Try Again...");
                    }
                     break;
                    
                 case 4:
                     System.out.print("New ID(1000000-1999999): ");
                     try {
                     String stringNewID = input.nextLine().trim();
                     int newID = Integer.parseInt(stringNewID);
                     
                     if (newID >= 1000000 && newID <= 1999999){
                         school.get(index).id = newID;
                     }
                     else {
                         System.out.println("Invalid Input...");
                     }
                     }catch (NumberFormatException e){
                     System.out.println("Invalid Input. Try Again...");
                     }
                     break;
                     
                 case 5:
                     System.out.println("Current Absenses: " + school.get(index).absences);
                     System.out.print("New Number Of Absenses: ");
                     try{
                     String stringNewAbsenses = input.nextLine().trim();
                     int newAbsenses = Integer.parseInt(stringNewAbsenses);
                     school.get(index).absences = newAbsenses;
                     }catch (NumberFormatException e){
                    System.out.println("Invalid Input. Try Again...");
                    }
                     break;
                    
                 case 6:
                    changeClassesLoop = true; 
                     while (changeClassesLoop){
                    System.out.println("Available Classes: ");
                    for (int g = 0; g < classes.size(); g++){
                        System.out.println((g + 1) + ". " + classes.get(g));
                    }
                    System.out.println("Current Schedule");
                    for (int t = 0; t < school.get(index).classes1.length; t++){
                    System.out.println((t + 1) + ". " + school.get(index).classes1[t]);
                        
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
                    int newClass = input.nextInt();
                    school.get(index).classes1[classToChange - 1] = classes.get(newClass - 1);
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
                 
                 case 7:
                     changeStudentGrades = true;
                     while (changeStudentGrades){
                  System.out.println(school.get(index).name + " | " + school.get(index).email + " | ID: " + school.get(index).id + "\n"
                        + "Class            Grade\n"
                        + "-----            -----");
                for (int p = 0; p < school.get(index).classes1.length; p++){
                   System.out.print((p + 1) + ". " + school.get(index).classes1[p]);
                   System.out.println("             " + school.get(index).grades[p]);
                }
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
                school.get(index).grades[changeGrade - 1] = updatedGrade;
                    } catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("Invalid Input. Try Again...");
                    }
                }catch (NumberFormatException e){
                    System.out.println("Invalid Input. Try Again...");
                    }
                     }
                 break;
                 case 8:
                 afterChangeLoop = false;
                 afterChange = false;
                 break;
                 
             }
            } catch (NumberFormatException e){
            System.out.println("Invalid Input. Try Again...");
       }
      }
     }
    }
    // adds student to system
    public static void addStudentToSchool(){
       students.school.add(addStudent());
    }
    
    // view every students information from grade 
    public static void viewGrade(){
    System.out.println("What Grade Would You Like To View?(9, 10, 11, or 12)");
    
    try {
        String stringViewStudentGrade = input.nextLine().trim();
            int viewStudentGrade = Integer.parseInt(stringViewStudentGrade);
            
        switch (viewStudentGrade){
            case 9:
                System.out.println("Freshmen: ");
                for (int i = 0; i < school.size(); i++){
                    if (school.get(i).grade == 9){
                        System.out.println((i + 1) + "." + " Name: " + school.get(i).name + " GPA: " + school.get(i).overallGrade + " Absences: " + school.get(i).absences
                         + " Honors: " + school.get(i).honors + " Valedictorian: " + school.get(i).valedictorian);
                        System.out.println("----------------------------------------------------------------------");
                    }
                }
                break;
            case 10: 
            System.out.println("Softmore: ");
                for (int i = 0; i < school.size(); i++){
                    if (school.get(i).grade == 10){
                        System.out.println((i + 1) + "." + " Name: " + school.get(i).name + " GPA: " + school.get(i).overallGrade + " Absences: " + school.get(i).absences
                         + " Honors: " + school.get(i).honors + " Valedictorian: " + school.get(i).valedictorian);
                        System.out.println("----------------------------------------------------------------------");
                    }
                }
                break;
            case 11: 
             System.out.println("Junior: ");
                for (int i = 0; i < school.size(); i++){
                    if (school.get(i).grade == 11){
                        System.out.println((i + 1) + "." + " Name: " + school.get(i).name + " GPA: " + school.get(i).overallGrade + " Absences: " + school.get(i).absences
                         + " Honors: " + school.get(i).honors + " Valedictorian: " + school.get(i).valedictorian);
                        System.out.println("----------------------------------------------------------------------");
                    }
                }
                break;   
            case 12:
            System.out.println("Seniors: ");
                for (int i = 0; i < school.size(); i++){
                    if (school.get(i).grade == 12){
                        System.out.println((i + 1) + "." + " Name: " + school.get(i).name + " GPA: " + school.get(i).overallGrade + " Absences: " + school.get(i).absences
                         + " Honors: " + school.get(i).honors + " Valedictorian: " + school.get(i).valedictorian);
                        System.out.println("----------------------------------------------------------------------");
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
                +"|Type 3: Exit Back To Main Menu    |\n"
                + " ----------------------------------");
        try{
        String stringChoiceStudent = input.nextLine().trim();
        int choiceStudent = Integer.parseInt(stringChoiceStudent);
        
       if (choiceStudent == 1){
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
       else if (choiceStudent == 3){
          viewStudentLoop = false; 
       }
      }catch (NumberFormatException e){
                    System.out.println("Invalid Input. Try Again...");
                    }
    }   
    // view one students information 
    private static void viewStudent(){
        System.out.println("Type The First, Middle Initial, And Last Name Of The Student You Want To View");
        String viewStudentInput = input.nextLine().trim();
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
     System.out.println("Type The First, Middle Initial, and Last Name Of The Student You Want To Delete");   
       String deleteStudentInput = input.nextLine().trim(); 
        for (int i = 0; i < school.size(); i++){
          if (school.get(i).name.equalsIgnoreCase(deleteStudentInput)){  
              school.remove(i);
      }   
     }
    }
    // any changes with classes that students can take
    public static void addClasses(){
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
    
    int addClassesAnswer = input.nextInt();
    
    switch (addClassesAnswer){
        
        // prints classes
        case 1:
        System.out.println("Current Classes: \n"
            + Arrays.asList(students.classes));    
            break;
            
            // loop - add class - press q - exit
        case 2: 
            addClassesLoop = true;
         input.nextLine();
         while (addClassesLoop){
    System.out.print("Type q to quit\n"
            + "Type Class To Add: ");
    String className = input.nextLine().trim();
    // press q = quit
    if (className.equalsIgnoreCase("q") || className.equalsIgnoreCase("quit")){
        addClassesLoop = false;
        System.out.println("");
    }
    else{
     classes.add(className);
    }
   }
         break;
         
        case 3: 
            // prints all classes with numbers
            for (int i = 0; i < classes.size(); i++){
            System.out.println((i + 1) + ". " + classes.get(i));
            }
            System.out.println("Type The Number Of The Class Name You Want To Change");
            int classChangeInput = input.nextInt();
            input.nextLine();
            System.out.print("Type The Change: ");
            String classChangeAnswer = input.nextLine().trim();
            classes.set((classChangeInput - 1), classChangeAnswer);
           break;
           
        case 4: 
            for (int i = 0; i < classes.size(); i++){
            System.out.println((i + 1) + ". " + classes.get(i));
            }
           System.out.println("Type The Number Of The Class Name You Want To Delete"); 
           int classDelete = input.nextInt();
           classes.remove(classDelete - 1);
            break;
        
        case 5:
        mainClassLoop = false;
        break;
        
        default:
        System.out.println("Invalid Input... Try Again.");
        break;
    }
   }
  }
    // calculates each students GPA 
    private static void calculateGPA(){
        // the whole school
        for (int i = 0; i < school.size(); i++){
            
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
                    school.get(i).overallGrade += school.get(i).grades[p];
                  classAmount++;                         
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
    public static void changeYear(){
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
        for (int i = school.size() - 1; i >= 0; i--){
            if (school.get(i).grade == 12){
                school.remove(i);
         }
            else if (school.get(i).grade == 11){
                school.get(i).grade = 12;
            }
            else if (school.get(i).grade == 10){
                school.get(i).grade = 11;
            }
            else if (school.get(i).grade == 9){
                school.get(i).grade = 10;
            }
        }
        // resets some information of students
        for (int p = 0; p < school.size(); p++){
            school.get(p).overallGrade = 0.0;
            school.get(p).honors = 'N';
            school.get(p).valedictorian = 'N';
            school.get(p).absences = 0;
            for (int g = 0; g < school.get(p).grades.length; g++){
                school.get(p).grades[g] = 0.0; 
                school.get(p).classes1[g] = null;
         }
        }
       }
        else{
           System.out.println("Not Changing Year...");
        }
      }
    
    // displays a summary of a group of students
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
               case 2:
                   
                   break;
               case 3:
                   break;
               case 4:
                   break;
               case 5:
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
