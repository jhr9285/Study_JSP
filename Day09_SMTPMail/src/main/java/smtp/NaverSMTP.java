// 개발자가 만들어야 하는 파일임!!
package smtp;

import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

// 네이버 SMTP 서버를 통해 이메일을 전송하는 클래스
public class NaverSMTP {
	private final Properties serverInfo; // 서버 정보
	private final Authenticator auth; 	 // 인증 정보
	
	public NaverSMTP() {
		// 네이버 SMTP 서버 접속 정보
		serverInfo = new Properties(); // Properties 객체 생성
		serverInfo.put("mail.smtp.host", "smtp.naver.com"); // 네이버 SMTP 주소 입력
		serverInfo.put("mail.smtp.port", "465"); // 네이버 SMTP 포트번호 입력
		serverInfo.put("mail.smtp.starttls.enable", "true");
        serverInfo.put("mail.smtp.auth", "true");
        serverInfo.put("mail.smtp.debug", "true");
        serverInfo.put("mail.smtp.socketFactory.port", "465");
        serverInfo.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        serverInfo.put("mail.smtp.socketFactory.fallback", "false");
        serverInfo.put("mail.smtp.ssl.trust", "smtp.naver.com"); // 보안상 이유로 ssl 코드 추가됨 (1/2)
        serverInfo.put("mail.smtp.ssl.protocols", "TLSv1.2"); // 보안상 이유로 ssl 코드 추가됨 (2/2)
        
        // 사용자 인증 정보
        auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("네이버 아이디", "네이버 비밀번호"); // 실제 네이버 ID와 암호를 적어야 됨
			}
        };
	}
	
	// 주어진 메일 내용을 네이버 SMTP 서버를 통해 전송하는 메소드 작성
	public void emailSending(Map<String, String> mailInfo) throws MessagingException {
		// 1. 세션 생성
		Session session = Session.getInstance(serverInfo, auth); // mail 서버의 세션!! (또 다른 종류의 세션)
		session.setDebug(true);
		
		// 2. 메시지 작성
		MimeMessage msg = new MimeMessage(session); // MimeMessage : 이메일을 보내기 위한 포맷
        // 참고) MIME(Multupurpose Internet Mail Extensions) : 전자우편을 위한 인터넷 표준 포맷
		msg.setFrom(new InternetAddress(mailInfo.get("from"))); // 보내는 사람
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mailInfo.get("to"))); // 받는 사람	
		msg.setSubject(mailInfo.get("subject")); // 제목
		msg.setContent(mailInfo.get("content"), mailInfo.get("format")); // 내용
		
		// 3. 전송
		Transport.send(msg);
	}
	
	
}
