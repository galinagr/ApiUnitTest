package junitXml;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class CustomDomParserNewXmlTest {

    @Test
    public void testSongIsNotNullSimple() throws Exception {
        //given
        Song expectedSong = new Song();
        expectedSong.setTitle("Rhapsody of fire");

        //when
        Song parsedSong = CustomDomParser.parseXMLToSong("C:\\Workspace\\obp-system-tests\\src\\test\\xmlResult.xml");

        //then
        assertNotNull(parsedSong);
        Assert.assertEquals(expectedSong.getTitle(), parsedSong.getTitle());
    }

    @Test
    public void testSongIsNotNullComplex() throws Exception {
        //given
        Song expectedSong = constructExpectedSong();

        //when
        Song parsedSong = CustomDomParser.parseXMLToSong("C:\\Workspace\\obp-system-tests\\src\\test\\xmlResult.xml");

        //then
        assertNotNull(parsedSong);
        Assert.assertEquals(expectedSong, parsedSong);
        /*assertEquals(expectedSong.getTitle(), parsedSong.getTitle());
        assertEquals(expectedSong.getCompany(), parsedSong.getCompany());
        assertEquals(expectedSong.getCountry(), parsedSong.getCountry());
        assertEquals(expectedSong.getPrice(), parsedSong.getPrice());
        assertEquals(expectedSong.getArtist(), parsedSong.getArtist());
        assertEquals(expectedSong.getYear(), parsedSong.getYear());*/
    }
//TODO:-setAttributes(true/false); -saveData(true/false); - no empty; - format and limits of data; -
//API:http://33testers.blogspot.com/2015/07/api.html
    //https://www.playframework.com/documentation/2.5.x/api/java/index.html
    private Song constructExpectedSong() {
        return new Song("Rhapsody of fire", "Luca Turilli", "Italy", "Producer Valentin(c)",
                "19.99$", "2002");
    }
}
