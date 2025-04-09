package question4;

public class Student {
    // id of the student
    private int id;
    // student is sleeping or not in the lab
    private boolean sleeping;



    // initialize the student with the given id
    public Student(int id)
    {
        sleeping = false;
        this.id = id;
        
    }

    // return the id of the student
    public int getID(){
        return id;
    }

    public boolean isSleeping(){
        return sleeping;
    }

    public void fallAsleep(){
        sleeping = true;
    }

    public void wakeUp(){
        sleeping = false;
    }

	public static void testStudent() {
		Student s = new Student(1234567890);

		System.out.println(s.getID() == 1234567890);
		System.out.println(s.isSleeping() == false);
		s.fallAsleep();
		System.out.println(s.isSleeping() == true);
		s.fallAsleep(); // should do nothing because the student is already sleeping
		System.out.println(s.isSleeping() == true);
		s.wakeUp();
		System.out.println(s.isSleeping() == false);
		s.wakeUp(); // should do nothing because the student is already awake
		System.out.println(s.isSleeping() == false);
	}
}