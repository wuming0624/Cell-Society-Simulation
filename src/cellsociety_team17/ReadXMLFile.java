// This entire file is part of my masterpiece.
// Wuming Zhang

package cellsociety_team17;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadXMLFile {

	private HashMap<String,Integer> simulationMap = new HashMap<String, Integer>();
	private int xDimension;
	private int yDimension;
	private HashMap<Coordinate, Cell> myGrid = new HashMap<Coordinate, Cell>();	
	
	public ReadXMLFile(String str){
		parserXML(str);
	}
	
	public void parserXML(String myStr) {

    try {

	File fXmlFile = new File(".\\src\\cellsociety_team17\\Parameters.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
			
	doc.getDocumentElement().normalize();

	NodeList simulations = doc.getElementsByTagName("Simulation");
	for (int i = 0; i < simulations.getLength(); i++){
		Node sNode = simulations.item(i);
		Element sElement = (Element) sNode;
		String tempStr = sElement.getElementsByTagName("Name").item(0).getTextContent();
		int tempInt = Integer.parseInt(sElement.getElementsByTagName("NumberOfStates").item(0).getTextContent());
		simulationMap.put(tempStr, tempInt);
	}
	
	NodeList dimension = doc.getElementsByTagName("Dimension");
	Node dNode = dimension.item(0);
	if (dNode.getNodeType() == Node.ELEMENT_NODE) {
		Element dElement = (Element) dNode;	
		xDimension = Integer.parseInt(dElement.getElementsByTagName("xDimension").item(0).getTextContent());
		yDimension = Integer.parseInt(dElement.getElementsByTagName("yDimension").item(0).getTextContent());
	}
	
		if (myStr!=null){
			readGrid(doc, myStr, myGrid);
		}

    } catch (Exception e) {

	e.printStackTrace();
    }
  }

	public void readGrid(Document doc, String cellType, HashMap<Coordinate, Cell> myMap) {
		NodeList myCells = doc.getElementsByTagName(cellType);
		
		for (int i = 0; i < myCells.getLength(); i++){
			Node cNode = myCells.item(i);
			Element cElement = (Element) cNode;
			int xCoordinate = Integer.parseInt(cElement.getAttribute("xCoordinate"));
			int yCoordinate = Integer.parseInt(cElement.getAttribute("yCoordinate")); 
			int myState = Integer.parseInt(cElement.getAttribute("state"));
			Coordinate currentCoord = new Coordinate(xCoordinate, yCoordinate);
			Cell myCell = null;
			myCell = CellFactory.CreateCell(cellType, currentCoord);
			myCell.setMyState(myState);
			myMap.put(currentCoord, myCell);	
		}

	}
	
	public HashMap<String,Integer> returnSimulationMap(){
		return simulationMap;
	}
	
	public int returnXDimension(){
		return xDimension;
	}
	
	public int returnYDimension(){
		return yDimension;
	}
	public HashMap<Coordinate, Cell> returnGrid(){
		return myGrid;
	}
}