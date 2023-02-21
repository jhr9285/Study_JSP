package websocket;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/ChatingServer") // ServerEndpoint : WebServlet annotaion과 유사한 기능
public class ChatServer {
	// 멀티채팅이라서 s 붙여서 복수로 네이밍+설정(set 사용)
    // ★★★주의!! 세션이 제네릭임 ==> 웹소켓의 세션(O) 쿠키와 유사한 세션(X) (웹소켓도 서버라서 접속자를 구분하는 세션이 있다)
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen // 클라이언트 접속 시 실행
	public void onOpen(Session session) {
		clients.add(session);
		System.out.println("웹소켓 연결 : " + session.getId());
		
		// 여기에서 소켓아이디와 로그인아이디를 매칭(pairing)시켜서 저장시키는 코드를 입력해야 한다.
		//        session.getId()
	}
	
	@OnMessage // 클라이언트의 메시지를 받으면 실행
	public void onMessage(String message, Session session) throws Exception {
		System.out.println("메시지 전송 : " + session.getId() + " : " + message); // 세션 아이디
		synchronized (clients) {
			for(Session client : clients) { // 모든 클라이언트에 메시지 전달
				if (!client.equals(session)) { // 단, 메시지를 보낸 클라이언트는 제외 (내가 보낸 메시지는 제외 - sendMessage()에서 함)
					client.getBasicRemote().sendText(message);
				}
			}
		}
	}
	
	@OnClose // 클라이언트와의 연결이 끊기면 실행
	public void onClose(Session session) {
		clients.remove(session);
		System.out.println("웹소켓 종료 : " + session.getId());
	}
	
	@OnError // 에러 발생 시 실행
	public void onError(Throwable e) {
		System.out.println("에러 발생");
		e.printStackTrace();
	}
}
