package kh.java.object.array.student.run;

public class Main {

	public static void main(String[] args) {

//		StudentManager sm = new StudentManager();
//		sm.inputStudent();
//		sm.printStudent();
		int i=0, j=5;
		OUT:
		for ( ; ; ){
		i++;
		for (;;){
		if(i>--j){
		break OUT;
		}
		}
		}
		System.out.println("i=" + i + ", j=" +j);
	}
}
