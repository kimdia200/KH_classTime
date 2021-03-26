import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		String a = "1994-02-06";
		System.out.println(a.indexOf('-'));
		System.out.println(a.lastIndexOf('-'));
		System.out.println(a.substring(0,4));
		System.out.println(a.substring(5,7));
		System.out.println(a.substring(8));
		
		String[] aa = {"운동", "등산", "게임", "여행"};
		
		System.out.println(Arrays.toString(aa));

		
	}
}
