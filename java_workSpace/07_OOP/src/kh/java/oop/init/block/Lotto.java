package kh.java.oop.init.block;

public class Lotto {

	int[] lotto = new int[6];
	//init 블럭, 생성과 동시에 6개의 난수를 지닌 lotto 배열 채우기
	{
		for(int i=0; i<6; i++){
			lotto[i] = (int)(Math.random()*48)+1;
		}
	}
}
