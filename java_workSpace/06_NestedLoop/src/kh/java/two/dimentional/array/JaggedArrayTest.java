package kh.java.two.dimentional.array;

import java.util.Scanner;

/**
 * 
 * 가변배열 JaggedArray
 * 
 * int[][] arr = new int[3][];
 * 
 * 참조의 참조인 배열의 길이가 제각각인 배열
 *
 */
public class JaggedArrayTest {

	public static void main(String[] args) {
		JaggedArrayTest j = new JaggedArrayTest();
		j.test2();
	}
	
	/**
	 * 가변배열
	 */
	public void test1() {
		
		int[][] arr = new int[3][];
		
		//2차원배열 할당
		arr[0] = new int[10];
		arr[1] = new int[3];
		arr[2] = new int[7];
		
		//값 입력
		int k = 0;
		for(int i = 0; i < arr.length; i++)
			for(int j = 0; j < arr[i].length; j++)
				arr[i][j] = k++;
		
		
		for(int i = 0; i < arr.length; i++) {
//			System.out.println(arr[i].toString()); //객체의 정보를 문자열로  출력(hashCode값 포함)
			for(int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	/**
	 * 구매 목록 입력받는데, 의류 5종류, 식료품 2종류, 기타물품 3종류를 담을 수 있는
	 * 가변배열을 선언하고, 사용자로 부터 각각 입력 받으세요
	 */
	public void test2() {
		Scanner sc = new Scanner(System.in);
		String temp;
		
		String[][] arr = new String[3][];
		arr[0] = new String[5];
		arr[1] = new String[2];
		arr[2] = new String[3];
		
		for(int i=0; i<arr.length; i++) {
			temp= i==0 ? "의류": i==1 ? "식료품":"기타물품";
			for(int j=0; j<arr[i].length; j++) {
				System.out.print(temp+" " +(j+1) + "번 = ");
				arr[i][j]=sc.nextLine();
			}
		}
		System.out.println("======================");
		for(int i=0; i<arr.length; i++) {
			temp= i==0 ? "의류": i==1 ? "식료품":"기타물품";
			System.out.println("구매할 "+ temp+" 목록");
			for(int j=0; j<arr[i].length; j++) {
				System.out.print(arr[i][j]);
				System.out.print((j != arr[i].length - 1) ? ", " : "");
			}
			System.out.println();
		}
	}
}
