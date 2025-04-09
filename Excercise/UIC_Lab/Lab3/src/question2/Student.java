// Author: RinChord
// Date: 2025/3/6
// Description: Student class for question 2

package question2;

public class Student {
    
    // id of the student
    private int id;


    // initialize the student with the given id
    public Student(int id) {
        if (id < 0) {
            id = 0;
        }
        this.id = id;
    }

    // return the id of the student
    public int getId(){
        return id;
    }

    public static void testStudent(int id) {
        // test the Student class
        Student student = new Student(id);
        System.out.println(student.getId());
    }
    
}
