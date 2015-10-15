package cellsociety_team17;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.w3c.dom.NodeList;

public class XMLParser {
	
	private DocumentBuilderFactory documentBuilderFactory;
	private DocumentBuilder documentBuilder;
	private Document parsedFile;
	private String GUIInformation = "GUI";
	private Map<String, String> GUIMap = new HashMap<String, String>();
	private Map<String, String> BackendMap = new HashMap<String, String>();


	private Document parseXML() throws ParserConfigurationException, SAXException, IOException {
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		parsedFile = documentBuilder.parse(new File("Input.xml"));
		return parsedFile;
	}
	
	
	
	public void readout() throws ParserConfigurationException, SAXException, IOException{
		parsedFile = parseXML();
		// Learned mechanics of the Document class, Nodes, and Elements from online resource:
		// http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
		if(parsedFile.hasChildNodes()){
			//populateGUIMap(parsedFile.getElementsByTagName("staff"));
			populateBackendMap(parsedFile.getElementsByTagName("staff"));
		}
		
		
		}



	private void populateBackendMap(NodeList nodeList) {
		
		NodeList newList = nodeList.item(0).getChildNodes();
		
		for(int i = 0; i < newList.getLength(); i++){
			NamedNodeMap nodeMap = newList.item(i).getAttributes();
			
			for(int j = 0; j < nodeMap.getLength(); j++){
				//////System.out.println(nodeMap.item(j).getNodeName());
				//////System.out.println(nodeMap.item(j).getNodeValue());
			}
//			Node currentNode = nodeList.item(i);
//			Element currentElement = (Element) currentNode;
			
			
		}
		
	}



	private void populateGUIMap(NodeList nodeList) {
		// TODO Auto-generated method stub
		
	}
	

}
