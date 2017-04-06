package http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

import static testData.Constants.FILE_PLACE_NEW_XML;
import static testData.Constants.URL;

public class HttpMethods {

    private static String statusCode;

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpMethods.class);

    public static String getStatusCode() {
        return statusCode;
    }

    public static void sendGet () throws IOException, FileNotFoundException {

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGetRequest = new HttpGet(URL);
        HttpResponse httpResponse = httpClient.execute(httpGetRequest);
        statusCode = String.valueOf(httpResponse.getStatusLine());
        LOGGER.info("----------------------------------------");
        LOGGER.info("INFO" + statusCode);
        LOGGER.info("----------------------------------------");

        HttpEntity entity = httpResponse.getEntity();

        if (entity != null) {
            InputStream inputStream = entity.getContent();
            File targetFile = new File(FILE_PLACE_NEW_XML);
            try {
                copyInputStreamToFile(inputStream, targetFile);
                Separation.separation();
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
           LOGGER.debug("Exception is " + e);
        }
    }
}

