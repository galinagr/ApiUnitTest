package junitXml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

//http://khpi-iip.mipk.kharkiv.edu/library/extent/prog/iipXML/examp/3/SaxToDom.html
//back to DOM

public class CustomDomParserNewXml {

    public static Song parseXMLToSong(String path) throws Exception {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(path);

        //change this piece of code to a loop in case you need to iterate over
        //multiple songs, int this case your should return array or list or set instead
        //of Song bean
        Node cd = doc.getElementsByTagName("CD").item(0);
        NodeList cdAttributes = cd.getChildNodes();
        Song parsedSong = new Song();

        for (int i = 0; i < cdAttributes.getLength(); i++) {
            Node node = cdAttributes.item(i);
            if ("TITLE".equals(node.getNodeName())) {
                parsedSong.setTitle(node.getTextContent());
                continue;
            }
            if ("ARTIST".equals(node.getNodeName())) {
                parsedSong.setArtist(node.getTextContent());
                continue;
            }
            if ("COUNTRY".equals(node.getNodeName())) {
                parsedSong.setCountry(node.getTextContent());
                continue;
            }
            if ("PRICE".equals(node.getNodeName())) {
                parsedSong.setPrice(node.getTextContent());
                continue;
            }
            if ("YEAR".equals(node.getNodeName())) {
                parsedSong.setYear(node.getTextContent());
                continue;
            }
            if ("COMPANY".equals(node.getNodeName())) {
                parsedSong.setCompany(node.getTextContent());
            }

        }
        return parsedSong;
    }
}
