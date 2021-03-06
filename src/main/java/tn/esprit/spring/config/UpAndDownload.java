package tn.esprit.spring.config;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpSession;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
public class UpAndDownload {

    private DefaultSftpSessionFactory gimmeFactory(){
        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory();
        factory.setHost("192.168.137.129");
        factory.setPort(22);
        factory.setUser("ftpuser");
        factory.setPassword("ftpuser");
        return factory;
    }

    public void upload(){

        SftpSession session = gimmeFactory().getSession();
        InputStream resourceAsStream =
                UpAndDownload.class.getClassLoader().getResourceAsStream("mytextfile.txt");
        try {
            session.write(resourceAsStream, "/home/ftpuser/mynewfile" + LocalDateTime.now() +".txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        session.close();
    }

    public String download(){
        SftpSession session = gimmeFactory().getSession();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            session.read("upload/downloadme.txt", outputStream);
            return new String(outputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
