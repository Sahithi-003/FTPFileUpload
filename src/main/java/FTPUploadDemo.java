
import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;
import java.io.InputStream;
public class FTPUploadDemo {
    public static void main(String[] args) {
        FTPClient client = new FTPClient();
        String filename = "data.txt";
        // Read the file from resources folder.
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(filename)) {
            client.connect("172.17.0.1");
            boolean login = client.login("ftpuser", "password");
            client.setFileTransferMode(client.BINARY_FILE_TYPE);
            client.enterLocalPassiveMode();
            if (login) {
                System.out.println("Login success...");

                // Store file to server
                boolean done = client.storeFile(filename, is);
                System.out.println(done);
                if(done)
                    System.out.println("upload success");
                client.logout();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
