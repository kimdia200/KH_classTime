package kh.java.array;

public class ArrayCopyTest {
/**
 * 배열 복사
 * 1. 얕은복사 shallow copy
 * 배열변수(주소값)의 복사(공유). 즉, 참조만 하겠다는 의미!
 * 
 * 2. 깊은복사 deep copy
 * 배열객체의 값복사
 */
	public static void main(String[] args) {
		ArrayCopyTest a = new ArrayCopyTest();
		a.test4();
	}
	
	
	/**
	 * 얕은 복사 : 주소값만 복사, 같은 배열을 공유
	 */
	public void test1() {
		int[] arr1 = {1,2,3};
		int[] arr2 = arr1;
		
		
		//값변경, arr1[1]이랑 arr2[1] 둘다 변경됨(주소를 공유하기 때문)
		arr1[1] +=10;
		
		for(int i = 0; i<arr1.length; i++) {
			System.out.println(arr1[i] + " --- " + arr2[i]);
		}
		
		//해시코드 확인
		System.out.println(arr1.hashCode());
		System.out.println(arr2.hashCode());
		
		
		//배열 주소값 비교
		System.out.println(arr1==arr2);
		
		//얕은 복사의 활용
		printArr(arr1);
		
		for(int i =0; i<arr1.length;i++) {
			System.out.println(arr1[i]+" --- "+arr2[i]);
		}
	}
	
	//얕은복사의 활용 메소드
	//같은 참조값을 가르키고 있음을 확인할수 있다.
	public void printArr(int[] arr) {
		System.out.println(arr.hashCode());
		for(int i =0 ; i<arr.length; i++) {
			System.out.println(arr[i]+" ");
		}
	}
	
	/**
	 * 깊은 복사 1 : 반복문을 통해 직접 값 복사하기
	 */
	public void test2() {
		//크기가 같은 배열 두개준비
		int[] arr1 = {1,2,3};
		int[] arr2 = new int[arr1.length];
		
		//값복사 = 깊은복사
		for(int i = 0 ; i<arr1.length;i++) {
			arr2[i] = arr1[i];
		}
		
		//값변경, arr1[1] 이랑 arr2[1]랑 값이 달라지는것을 확인, 주소값이 다르기 때문
		arr1[1] *=100;
		
		//값 확인
		for(int i = 0; i<arr1.length; i++) {
			System.out.println(arr1[i] + " --- " + arr2[i]);
		}
		
		//해시코드 확인
		System.out.println(arr1.hashCode());
		System.out.println(arr2.hashCode());
		
		
		//배열 주소값 비교
		System.out.println(arr1==arr2);
	}
	
	/**
	 * 깊은복사2 : System.arraycopy(원본배열, 시작인덱스, 도착지배열,시작 인덱스, 복사할 길이)
	 * 
	 */
	public void test3() {
		char[] arr1 = {'a','b','c','d','e'};
		char[] arr2 = new char[arr1.length];
		
		//스태틱 메소드를 사용 깊은복사
//		System.arraycopy(arr1, 0, arr2, 0, arr1.length);
		System.arraycopy(arr1, 0, arr2, 3, 2); //이거는 변형 인덱스 3,4 두개만 복사됨

		//값 확인
		for(int i = 0; i<arr1.length; i++) {
			System.out.println(arr1[i] + " --- " + arr2[i]);
		}
		
		//해시코드 확인
		System.out.println(arr1.hashCode());
		System.out.println(arr2.hashCode());
				
				
		//배열 주소값 비교
		System.out.println(arr1==arr2);
	}
	/**
	 * 깊은복사3 : clone()
	 */
	public void test4() {
		double[] arr1 = new double[] {0.123, 0.741, 0.324};
		double[] arr2 = arr1.clone();
		
		//값 확인
		for(int i = 0; i<arr1.length; i++) {
			System.out.println(arr1[i] + " --- " + arr2[i]);
		}
		
		//해시코드 확인
		System.out.println(arr1.hashCode());
		System.out.println(arr2.hashCode());
				
				
		//배열 주소값 비교
		System.out.println(arr1==arr2);
	}
}
