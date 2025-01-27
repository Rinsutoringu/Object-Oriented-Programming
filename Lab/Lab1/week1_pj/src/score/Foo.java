package score;

import java.util.ArrayList;

public class Foo {
	public static void main(String[] args) {
        System.out.println("hello world");

        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(1);
        a.add("");
        ArrayList<String> b = new ArrayList<String>();
        b.add("a");
        b.add(12);


        Integer a1 = a.get(1);
    }
}
