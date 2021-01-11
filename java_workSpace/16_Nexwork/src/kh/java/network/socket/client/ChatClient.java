package kh.java.network.socket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {
	public static final String SERVER_IP = "localhost"; //khm
	int SERVER_PORT = 7777;
	
	public static void main(String[] args) {
		new ChatClient().init();
	}
	
	public void init() {
		
		try {
			//1.소켓 생성 및 연결요청
			Socket socket = new Socket(SERVER_IP, SERVER_PORT);

			//2.소켓용 입출력스트림 준비
			BufferedReader br = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			//3.서버통신
//			System.out.println("서버 : "+br.readLine());//서버 웰컴 메시지
			
			//4.채팅
			String data = "";
			Scanner sc = new Scanner(System.in);//사용자 키보드 입력을 받는 스트림
			while((data=br.readLine())!=null) {
				if("exit".equals(data)) {
					System.out.println("채팅프로그램을 종료합니다.");
					pw.println("exit");
					pw.flush();
					break;
				}
				System.out.println("서버 : "+data);
				System.out.print("클라이언트 : ");
				String msg = sc.nextLine();
				pw.println(msg);
				pw.flush();
			}
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
