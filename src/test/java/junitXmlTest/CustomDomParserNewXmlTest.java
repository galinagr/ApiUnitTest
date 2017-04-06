package junitXmlTest;

import http.HttpMethods;
import junitXml.CustomDomParserNewXml;
import junitXml.SongNew;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static testData.Constants.*;

public class CustomDomParserNewXmlTest {

    private static Logger LOGGER = LoggerFactory.getLogger(CustomDomParserTest.class);

    @BeforeClass
    public static void setUp() {
        try {
            HttpMethods.sendGet();
            LOGGER.info("Get is successful!!!");
        } catch (IOException e) {
            LOGGER.error("Get is failed " + e);
        }
    }
    @Test
    public void checkResponseCode() throws Exception {
        Assert.assertEquals(SUCCESS_CODE, HttpMethods.getStatusCode());
        LOGGER.info("Response code is " + SUCCESS_CODE);
    }

    @Test
    public void testSongIsNotNullSimpleXML() throws Exception {
        //given
        SongNew expectedSong = new SongNew();
        expectedSong.setName(NODES_DATA_NEW[0]);

        //when
        SongNew parsedSong = CustomDomParserNewXml.parseXMLToSongNew(FILE_PLACE_NEW_XML);

        //then
        assertNotNull(parsedSong);
        assertEquals(expectedSong.getName(), parsedSong.getName());
    }

    @Test
    public void testSongIsNotNullComplexXML() throws Exception {
        //given
        SongNew expectedSong = constructExpectedSongXML();

        //when
        SongNew parsedSong = CustomDomParserNewXml.parseXMLToSongNew(FILE_PLACE_NEW_XML);

        //then
        assertNotNull(parsedSong);
        assertEquals(expectedSong, parsedSong);
        assertEquals(expectedSong.getName(), parsedSong.getName());
        assertEquals(expectedSong.getSubresourceUris(), parsedSong.getSubresourceUris());
        assertEquals(expectedSong.getUri(), parsedSong.getUri());
    }
    @AfterClass
    public static void tearDown() {
        LOGGER.info("Successful XML parsed");
    }


//TODO:-setAttributes(true/false); -saveData(true/false); - no empty; - format and limits of data; -
//API:http://33testers.blogspot.com/2015/07/api.html
    //https://www.playframework.com/documentation/2.5.x/api/java/index.html
    private SongNew constructExpectedSongXML() {
        return new SongNew(NODES_DATA_NEW[0],NODES_DATA_NEW[1],NODES_DATA_NEW[2]);
    }
}
