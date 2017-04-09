package domParser;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.FileNotFoundException;

import static testData.Constants.FILE_PLACE_DOM_XML;

public class DomExtract {

    private static Logger LOGGER = LoggerFactory.getLogger(DomExtract.class);

    public static Node getNode(String tagName, NodeList nodes) {
        for (int x = 0; x < nodes.getLength(); x++) {
            org.w3c.dom.Node node = nodes.item(x);
            if (node.getNodeName().equalsIgnoreCase(tagName)) {
                return node;
            }
        }

        return null;
    }

    public static String getNodeValue(Node node) {
        NodeList childNodes = node.getChildNodes();
        for (int x = 0; x < childNodes.getLength(); x++) {
            Node data = childNodes.item(x);
            if (data.getNodeType() == Node.TEXT_NODE)
                return data.getNodeValue();
        }
        return "";
    }

    public static String getNodeValue(String tagName, NodeList nodes) {
        for (int x = 0; x < nodes.getLength(); x++) {
            Node node = nodes.item(x);
            if (node.getNodeName().equalsIgnoreCase(tagName)) {
                NodeList childNodes = node.getChildNodes();
                for (int y = 0; y < childNodes.getLength(); y++) {
                    Node data = childNodes.item(y);
                    if (data.getNodeType() == Node.TEXT_NODE)
                        return data.getNodeValue();
                }
            }
        }
        return "";
    }


//    public static List<String> getNodeValueAsList(String[] tagName, NodeList nodes) {
//        List<String> nodesData = new ArrayList<>();
//        for (int x = 0; x < nodes.getLength(); x++) {
//            Node node = nodes.item(x);
//            if (node.getNodeName().equalsIgnoreCase(tagName[x])) {
//                NodeList childNodes = node.getChildNodes();
//                for (int y = 0; y < childNodes.getLength(); y++) {
//                    Node data = childNodes.item(y);
//                    if (data.getNodeType() == Node.TEXT_NODE)
//                        nodesData.add(data.getNodeValue());
//                }
//            }
//        }
//        return nodesData;
//    }

    public static String getNodeAttr(String attrName, Node node) {
        NamedNodeMap attrs = node.getAttributes();
        for (int y = 0; y < attrs.getLength(); y++) {
            Node attr = attrs.item(y);
            if (attr.getNodeName().equalsIgnoreCase(attrName)) {
                return attr.getNodeValue();
            }
        }
        return "";
    }

    public static String getNodeAttr(String tagName, String attrName, NodeList nodes) {
        for (int x = 0; x < nodes.getLength(); x++) {
            Node node = nodes.item(x);
            if (node.getNodeName().equalsIgnoreCase(tagName)) {
                NodeList childNodes = node.getChildNodes();
                for (int y = 0; y < childNodes.getLength(); y++) {
                    Node data = childNodes.item(y);
                    if (data.getNodeType() == Node.ATTRIBUTE_NODE) {
                        if (data.getNodeName().equalsIgnoreCase(attrName))
                            return data.getNodeValue();
                    }
                }
            }
        }

        return "";
    }

    public static Dom expectedDom() {
        Dom dom = new Dom();
        dom.setLastName("Smith");
        dom.setFirstName("Jim");
        dom.setStreet("123 Main Street");
        dom.setCity("Mytown");
        dom.setState("NY");
        dom.setZip("11234");
        return dom;
    }

    public static void main(String[] args) {
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
            assert comp != null;
            Node exec = getNode("Executive", comp.getChildNodes());
            String execType = getNodeAttr("type", exec);

            // Load the executive's data from the XML
            assert exec != null;
            NodeList nodes = exec.getChildNodes();
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
}

