package kh.java.network.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * ip : 자동으로 Server computer의 ip가 할당
 * port : 지정 
 *
 */
public class ChatServer {
	public static final int PORT=7777;
	
	public static void main(String[] args) {
		new ChatServer().init();
	}

	//서버프로그램 시작
	public void init() {
		BufferedReader br =null;
		PrintWriter pw = null;
		try {
			//1.server Socket생성
			ServerSocket serverSocket = new ServerSocket(PORT);
			while(true) {
				System.out.println("["+InetAddress.getLocalHost().getHostAddress()+
						":"+PORT+"]에서 연결 대기중...");
				
				//2.Client 연결요청이 있을때, 클라이언트 통신용 소켓 생성
				//연결요청이 있을때까지 대기하다가 연결요청이 발생하면 socket객체 생성
				Socket socket = serverSocket.accept(); 
				
				//3.소켓용 입출력스트림 준비
				br = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				//이건 처음 써보네 메모(BufferedWriter보다 편한게 브릿지 스트림 없어도됨)
				pw = new PrintWriter(socket.getOutputStream());
				
				//4.welcome message
				pw.println("환영합니다.");
				pw.flush();//버퍼내용을 즉시 쓰기, 버퍼가 가득 차지 않아서 전송하기
				
				//5. 채팅
				String data = "";
				Scanner sc = new Scanner(System.in);//서버쪽 사용자 키보드 입력;
				while((data = br.readLine())!= null) {
					if("exit".equals(data)) {
						System.out.println("클라이언트가 채팅방을 나갔습니다.");
						pw.println("exit");
						pw.flush();
						break;
					}
					System.out.println("클라이언트 : "+data);
					System.out.print("서버 : ");
					String msg = sc.nextLine();
					pw.println(msg);
					pw.flush();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			pw.close();
		}
	}
}
