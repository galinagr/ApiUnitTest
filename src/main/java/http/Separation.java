package http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testData.Constants;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static testData.Constants.FILE_PLACE_NEW_XML;

class Separation {

    static void separation() throws IOException {
        Logger LOGGER = LoggerFactory.getLogger(Separation.class);
        URL obj = new URL(Constants.URL);
        URLConnection conn = obj.openConnection();

        //get all response headers
        Map<String, List<String>> map = conn.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            System.out.println("Key : " + entry.getKey() +
                    " ,Value : " + entry.getValue());
        }
        //get response body
        InputStream output = conn.getInputStream();
        Scanner s = new Scanner(output).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";
        result = result.substring(result.indexOf("<TwilioResponse>"), result.indexOf("</TwilioResponse>")).concat("</TwilioResponse>");
        try (PrintWriter out = new PrintWriter(FILE_PLACE_NEW_XML)) {
            out.println(result);
        } catch (FileNotFoundException e) {
            LOGGER.error("File is not found + " + e);
            LOGGER.info("Result after separation " + result);
        }
    }
}