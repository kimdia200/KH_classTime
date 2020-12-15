package kh.java.two.dimentional.array;
/**
 * 2차원 배열 : 배열 안의 배열
 * { {1,2,3}, {4,5,6}, {7,8,9} }
 * arr[0][0] ====> 1
 * arr[0][1] ====> 2
 * arr[0][2] ====> 3
 * 
 * arr[1][0] ====> 4
 * arr[1][1] ====> 5
 * arr[1][2] ====> 6
 * 
 * arr[2][0] ====> 7
 * arr[2][1] ====> 8
 * arr[2][2] ====> 9
 *
 */
public class TwoDimentionalArrayTest {

	public static void main(String[] args) {

		TwoDimentionalArrayTest t1 = new TwoDimentionalArrayTest();
		t1.test4();
		
	}

	public void test1() {
		//1. 배열선언
		int[][] arr;
		
		//2. 배열 할당
		arr = new int[2][3];
		
		//3. 값 대입
//		arr[0][0] = 1;
//		arr[0][1] = 2;
//		arr[0][2] = 3;
//		arr[1][0] = 4;
//		arr[1][1] = 5;
//		arr[1][2] = 6;
		//대입할 값사이의 규칙성이 있을대 중첩반복문으로 해결
		int k=1;
		for(int i=0; i<arr.length;i++) {
			for(int j=0; j<arr[i].length;j++) {
				arr[i][j]=k;
				k++;
			}
		}
		
		//4. 값 출력
//		System.out.println(arr[0][0]);
//		System.out.println(arr[0][1]);
//		System.out.println(arr[0][2]);
//		System.out.println(arr[1][0]);
//		System.out.println(arr[1][1]);
//		System.out.println(arr[1][2]);
		
		//i=현재 행의수, j=현재 열의 수
		//arr.length = 행의수, arr[x].length = 열의수
		for(int i=0; i<arr.length;i++) {
			for(int j=0; j<arr[i].length; j++) {
				System.out.println(arr[i][j]);
			}
		}
	}
	
	/**
	 * test1을 선언, 배열 할당, 값대입을 한번에 해주기
	 */
	public void test2() {
		int arr[][] = {
				{1,2,3},
				{4,5,6}
		};
		
		//값 출력
		//i=현재 행의수, j=현재 열의 수
		//arr.length = 행의수, arr[x].length = 열의수
		for(int i=0; i<arr.length;i++) {
			for(int j=0; j<arr[i].length; j++) {
				System.out.println(arr[i][j]);
			}
		}
	}
	
	/**
	 * @실습문제 : 알파벳 배열
	 * 0행에는 대문자 26개
	 * 1행에는 소문자 26개
	 */
	public void test3() {
		char[][] arr = new char[2][26];		
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				if(i==0) {
					arr[i][j]=(char)(j+'A');
				}else {
					arr[i][j]=(char)(j+'a');
				}
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}
	/**
	 * 2차원 배열의 이해
	 * -  배열안의 배열
	 * -  참조의 참조
	 */
	public void test4() {
		int[][] arr = new int[2][3];
		int k=100;
		//int[]의 길이 : 첫번째 가리키는 배열의 길이
		for(int i=0; i<arr.length; i++) {
			
			//int[][]의 길의
			for(int j=0; j<arr[i].length;j++) {
				arr[i][j]=k++;
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
}
