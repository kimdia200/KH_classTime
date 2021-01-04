package kh.java.collection.list;

import java.util.Comparator;

public class StudentNameAscending implements Comparator<Student> {

	/**
	 * 이름 String 오름차순
	 */
	@Override
	public int compare(Student s1, Student s2) {
		
		return s1.getName().compareTo(s2.getName());
	}

}
