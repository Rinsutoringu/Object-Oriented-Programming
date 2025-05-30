// Author: RinChord
// Date: 2025/3/6
// Description: Student class for question 6

package question6;

public class Student {
    
    // id of the student
    private int id;
    // name of the student
    private String name;
    // grade of the student
    private char grade;
    // student is sleeping or not in the lab
    private boolean sleeping;



    // initialize the student with the given id
    public Student(int id, String name)
    {
        if (id < 0) {
            id = 0;
        }
        this.id = id;
        this.name = name;
        this.grade = 'A';
        sleeping = false;
    }
    public Student(int id, String name, char grade)
    {
        if (id < 0) {
            id = 0;
        }
        this.id = id;
        this.name = name;
        this.grade = grade;
        sleeping = false;
    }
    
    // return the name of the student
    public String getName()
    {
        return name;
    }

    // set the name of the student
    public void setName(String name)
    {
        this.name = name;
    }

    // return the id of the student
    public int getId(){
        return id;
    }

    public char getGrade(){
        return grade;
    }

    public void setGrade(char grade){
        this.grade = grade;
    }

    public boolean Sleeping(){
        return sleeping;
    }

    public void goToSleep(){
        sleeping = true;
        switch (grade) {
            case 'A':
                this.grade = 'B';
                break;
                
            case 'B':
                this.grade = 'C';
                break;

            case 'C':
                this.grade = 'D';
                break;
        
            default:
                this.grade = 'F';
                break;
        }
    }

    public void wakeUp(){
        sleeping = false;
    }


    public static void testStudent() {
        // test the Student class
        Student s1 = new Student(9999999, "Philippe");
        System.out.println(s1.getId() == 9999999);

        // test the setName method
        System.out.println(s1.getName() == "Philippe");
        s1.setName("Meunier");
        System.out.println(s1.getName() == "Meunier");

        // test the setGrade method
        System.out.println(s1.getGrade() == 'A');
        s1.setGrade('F');
        System.out.println(s1.getGrade() == 'F');

        // test the Student class with negative id
        Student s2 = new Student(-9999999, "Unknown");
        System.out.println(s2.getId() == 0);
        Student s3 = new Student(0, "Unknown");
        System.out.println(s3.getId() == 0);

        // test the Student class with grade
        Student s4 = new Student(9999999, "Philippe", 'B');
        System.out.println(s4.getId() == 9999999);
        System.out.println(s4.getName() == "Philippe");
        System.out.println(s4.getGrade() == 'B');

        // Philippe is a good student
        Student s5 = new Student(-9999999, "Unknown", 'C');
        System.out.println(s5.getId() == 0);
        Student s6 = new Student(0, "Unknown", 'C');
        System.out.println(s6.getId() == 0);

        // test the Student class with sleeping
        System.out.println("Test the Student class with sleeping");
        Student s7 = new Student(9999999, "Philippe", 'A');
        System.out.println("" + s7.getId() + s7.getName() + s7.getGrade());
        System.out.println(s7.getGrade() == 'A');
        s7.goToSleep();
        System.out.println("zzz...");
        System.out.println(s7.getGrade() == 'B');
        System.out.println(""+ s7.getId() + s7.getName() + s7.getGrade());
        s7.wakeUp();
        System.out.println("Wake up!");
        System.out.println(s7.getGrade() == 'A');
        System.out.println(""+ s7.getId() + s7.getName() + s7.getGrade());
        
    }
    
}
