package fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class FileUtil {
    // 파일 업로드(multipart/form-data 요청) 처리
    public static MultipartRequest uploadFile(HttpServletRequest req,
            String saveDirectory, int maxPostSize) {
        try {
            // 파일 업로드
            return new MultipartRequest(req, saveDirectory, maxPostSize, "UTF-8");
        }
        catch (Exception e) {
            // 업로드 실패
            e.printStackTrace();
            return null;
        }
    }

    // 명시한 파일을 찾아 다운로드합니다. (다운로드 : 서버에 파일 입력 후 출력)
    public static void download(HttpServletRequest req, HttpServletResponse resp,
            String directory, String sfileName, String ofileName) {
        String sDirectory = req.getServletContext().getRealPath(directory);
        try {
            // 파일을 찾아 입력 스트림 생성
            File file = new File(sDirectory, sfileName);
            InputStream iStream = new FileInputStream(file); // 업캐스팅

            // 한글 파일명 깨짐 방지 (Internet Explorer만 해당, Edge는 비해당)
            String client = req.getHeader("User-Agent");
            if (client.indexOf("WOW64") == -1) { // 브라우저가 Internet Explorer(=WOW64)인지 알아내는 코드 
                ofileName = new String(ofileName.getBytes("UTF-8"), "ISO-8859-1");
            }
            else {
                ofileName = new String(ofileName.getBytes("KSC5601"), "ISO-8859-1");
            }

            // 파일 다운로드용 응답 헤더 설정 (★★★반드시 다운로드만 하도록 설정하는 코드. 안하면 브라우저가 '보여주려고' 함)
            resp.reset();
            resp.setContentType("application/octet-stream"); 
            resp.setHeader("Content-Disposition",
                           "attachment; filename=\"" + ofileName + "\""); // 첨부파일 다운로드 시 이름 ofileName으로 설정
            resp.setHeader("Content-Length", "" + file.length() );

            //out.clear();  // 출력 스트림 초기화

            // response 내장 객체로부터 새로운 출력 스트림 생성
            OutputStream oStream = resp.getOutputStream();

            // 출력 스트림에 파일 내용 출력    (int)file.length() = iStream.read(b) ==> '숫자' !!!
            byte b[] = new byte[(int)file.length()]; // file.length 만큼 배열 사이즈 설정 ==> file을 한꺼번에 읽겠다는 의미
            int readBuffer = 0;
            while ( (readBuffer = iStream.read(b)) > 0 ) { // 읽어온 내용이 0보다 크다면 (readBuffer에 iStream.read(b)를 대입)
                oStream.write(b, 0, readBuffer);
            }

            // 입/출력 스트림 닫음
            iStream.close();
            oStream.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다.");
            e.printStackTrace();
        }
        catch (Exception e) {
            System.out.println("예외가 발생하였습니다.");
            e.printStackTrace();
        }
    }

    // 지정한 위치의 파일을 삭제합니다.
    public static void deleteFile(HttpServletRequest req,
            String directory, String filename) {
        String sDirectory = req.getServletContext().getRealPath(directory);
        File file = new File(sDirectory + File.separator + filename);
        if (file.exists()) {
            file.delete();
        }
    }
}
