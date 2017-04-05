package http;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
public class HttpMethods {

    /**
     * This class is the same as the ApacheHttpRestClient2 class, but with
     * fewer try/catch clauses, and fewer comments.
     */

    public final static void main(String[] args) {

        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpGet httpGetRequest = new HttpGet("https://any-api.com/trello_com/trello_com/docs/API_Description");
            HttpResponse httpResponse = httpClient.execute(httpGetRequest);

            System.out.println("----------------------------------------");
            System.out.println(httpResponse.getStatusLine());
            System.out.println("----------------------------------------");

            HttpEntity entity = httpResponse.getEntity();

            byte[] buffer = new byte[1024];
            if (entity != null) {
                InputStream inputStream = entity.getContent();
                File targetFile = new File("C:/REST/xmlResult.txt");
                OutputStream outputStream = new FileOutputStream((targetFile));
                try {
                    int bytesRead = 0;
                    BufferedInputStream bis = new BufferedInputStream(inputStream);
                    while ((bytesRead = bis.read(buffer)) != -1) {
                    //    IOUtils.copy(inputStream, outputStream);
                        String chunk = new String(buffer, 0, bytesRead);
                      System.out.println(chunk);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        inputStream.close();
                        outputStream.close();
                    } catch (Exception ignore) {
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
    }
//    InputStream is = response.getEntity().getContent();
//    Create a FileOutputStream
//
//    FileOutputStream fos = new FileOutputStream(new File(Environment.getExternalStorageDirectory(), "test.epub"));
//
//    and read from is and write with fos
//
//    int read = 0;
//    byte[] buffer = new byte[32768];
//while( (read = is.read(buffer)) > 0) {
//        fos.write(buffer, 0, read);
//    }
//
//fos.close();
//is.close();
}

