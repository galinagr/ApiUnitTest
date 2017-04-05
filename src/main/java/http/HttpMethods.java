package http;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;

public class HttpMethods {

    public final static void main(String[] args) throws IOException {

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGetRequest = new HttpGet("http://apidev.accuweather.com/developers/best-practices");
        HttpResponse httpResponse = httpClient.execute(httpGetRequest);

        System.out.println("----------------------------------------");
        System.out.println(httpResponse.getStatusLine());
        System.out.println("----------------------------------------");

        HttpEntity entity = httpResponse.getEntity();

        byte[] buffer = new byte[1024];
        if (entity != null) {
            InputStream inputStream = entity.getContent();
            File targetFile = new File("C:\\Workspace\\ApiUnitTest\\src\\main\\java\\testData\\xmlResult.xml");
            try {
                copyInputStreamToFile(inputStream, targetFile);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                httpClient.getConnectionManager().shutdown();
            }
        }
    }

    private static void copyInputStreamToFile(InputStream inputStream, File file) {
        try {
            OutputStream outputStream = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, len);

            }
            outputStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

