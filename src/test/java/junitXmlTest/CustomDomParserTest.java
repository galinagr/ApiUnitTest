package junitXmlTest;

import http.HttpMethods;
import junitXml.CustomDomParser;
import junitXml.Song;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static testData.Constants.*;

public class CustomDomParserTest {

    private Logger LOGGER = LoggerFactory.getLogger(CustomDomParserTest.class);

    @Before
    public final void setUp() {
        try {
            HttpMethods.sendGet();
            LOGGER.info("Get is successful!!!");
        } catch (IOException e) {
            LOGGER.error("Get is failed " + e);
        }
    }

    @After
    public final void tearDown() {
        LOGGER.info("Successful XML parsed");
    }

    @Test
    public void checkResponseCode() throws Exception {
        Assert.assertEquals(SUCCESS_CODE, HttpMethods.getStatusCode());
        LOGGER.info("Response code is " + SUCCESS_CODE);
    }

    @Test
    public void testSongIsNotNullSimple() throws Exception {
        //given
        Song expectedSong = new Song();
        expectedSong.setTitle("Rhapsody of fire");

        //when
        Song parsedSong = CustomDomParser.parseXMLToSong(FILE_PLACE_OLD_XML);

        //then
        assertNotNull(parsedSong);
        assertEquals(expectedSong.getTitle(), parsedSong.getTitle());
    }

    @Test
    public void testSongIsNotNullComplex() throws Exception {
        //given
        Song expectedSong = constructExpectedSong();

        //when
        Song parsedSong = CustomDomParser.parseXMLToSong(FILE_PLACE_OLD_XML);

        //then
        assertNotNull(parsedSong);
        assertEquals(expectedSong, parsedSong);
        assertEquals(expectedSong.getTitle(), parsedSong.getTitle());
        assertEquals(expectedSong.getCompany(), parsedSong.getCompany());
        assertEquals(expectedSong.getCountry(), parsedSong.getCountry());
        assertEquals(expectedSong.getPrice(), parsedSong.getPrice());
        assertEquals(expectedSong.getArtist(), parsedSong.getArtist());
        assertEquals(expectedSong.getYear(), parsedSong.getYear());
    }

    //TODO:-setAttributes(true/false); -saveData(true/false); - no empty; - format and limits of data; -
//API:http://33testers.blogspot.com/2015/07/api.html
    private Song constructExpectedSong() {
        return new Song(NODES_DATA[0], NODES_DATA[1], NODES_DATA[2], NODES_DATA[3],
                NODES_DATA[4], NODES_DATA[5]);
    }
}
