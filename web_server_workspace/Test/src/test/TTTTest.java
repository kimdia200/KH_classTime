package test;

import java.util.ArrayList;
import java.util.List;

public class TTTTest {
	public static void main(String[] args) {
		String x = "[1, 2, 3, 4, 5, 6, 7, 10, 19]";
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(10);
		list.add(19);
		System.out.println(list);
		
		for(String s : x.split("")) {
			System.out.println(s);
		}
		
	}
}
