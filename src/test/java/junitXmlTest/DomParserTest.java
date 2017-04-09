package junitXmlTest;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static domParser.DomExtract.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static testData.Constants.FILE_PLACE_DOM_XML;
import static testData.Constants.NODES_LIST;

public class DomParserTest {

    private static Logger LOGGER = LoggerFactory.getLogger(CustomDomParserTest.class);

    @BeforeClass
    public static void setUp() {
    }

    @Test
    public void testDOMXML() throws Exception {
        try {
            DOMParser parser = new DOMParser();
            try {
                parser.parse(FILE_PLACE_DOM_XML);
            } catch (FileNotFoundException e) {
                LOGGER.error("File is not found " + e);
            }
            Document doc = parser.getDocument();

            // Get the document's root XML node
            NodeList root = doc.getChildNodes();

            // Navigate down the hierarchy to get to the CEO node
            Node comp = getNode("Company", root);
            assertNotNull(comp);
            Node exec = getNode("Executive", comp.getChildNodes());
            assertNotNull(exec);
            String execType = getNodeAttr("type", exec);

            // Load the executive's data from the XML
            assertNotNull(exec);
            NodeList nodes = exec.getChildNodes();
            assertNotNull(nodes);
//            assertEquals(expectedDom().getLastName(), getNodeValue("LastName", nodes));
//            assertEquals(expectedDom().getFirstName(), getNodeValue("FirstName", nodes));
//            assertEquals(expectedDom().getStreet(), getNodeValue("street", nodes));
//            assertEquals(expectedDom().getCity(), getNodeValue("city", nodes));
//            assertEquals(expectedDom().getState(), getNodeValue("state", nodes));
//            assertEquals(expectedDom().getZip(), getNodeValue("zip", nodes));

            // assertEquals(NODES_LIST,getNodeValueAsList(TAG_NAMES_ARRAY, nodes));
            List<String> actualData = new ArrayList<>();
            actualData.add(getNodeValue("LastName", nodes));
            actualData.add(getNodeValue("FirstName", nodes));
            actualData.add(getNodeValue("street", nodes));
            actualData.add(getNodeValue("city", nodes));
            actualData.add(getNodeValue("state", nodes));
            actualData.add(getNodeValue("zip", nodes));

            assertEquals(NODES_LIST, actualData);

            String lastName = getNodeValue("LastName", nodes);
            String firstName = getNodeValue("FirstName", nodes);
            String street = getNodeValue("street", nodes);
            String city = getNodeValue("city", nodes);
            String state = getNodeValue("state", nodes);
            String zip = getNodeValue("zip", nodes);


            System.out.println("Executive Information:");
            System.out.println("Type: " + execType);
            System.out.println(lastName + ", " + firstName);
            System.out.println(street);
            System.out.println(city + ", " + state + " " + zip);
        } catch (Exception e1) {
            LOGGER.error("Parsing is not successful" + e1);
        }
    }

    @AfterClass
    public static void tearDown() {
        LOGGER.info("Successful XML parsed");
    }
}
