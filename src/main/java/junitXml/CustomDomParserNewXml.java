package junitXml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import testData.Constants;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static testData.Constants.*;

//http://khpi-iip.mipk.kharkiv.edu/library/extent/prog/iipXML/examp/3/SaxToDom.html
//back to DOM

public class CustomDomParserNewXml {

    public static SongNew parseXMLToSongNew(String path) throws Exception {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(path);

        //change this piece of code to a loop in case you need to iterate over
        //multiple songs, int this case your should return array or list or set instead
        //of Song bean
        Node cd = doc.getElementsByTagName(Constants.TAG_NAME_NEW).item(0);
        NodeList cdAttributes = cd.getChildNodes();
        SongNew parsedSongNew = new SongNew();

        for (int i = 0; i < cdAttributes.getLength(); i++) {
            Node node = cdAttributes.item(i);
            if (NAME.equals(node.getNodeName())) {
                parsedSongNew.setName(node.getTextContent());
                continue;
            }
            if (URI.equals(node.getNodeName())) {
                parsedSongNew.setUri(node.getTextContent());
                continue;
            }
            if (SUBRESOURCE_URIS.equals(node.getNodeName())) {
                parsedSongNew.setSubresourceUris(node.getTextContent());
                continue;
            }
        }
        return parsedSongNew;
    }
}
