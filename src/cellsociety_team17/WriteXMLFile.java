package cellsociety_team17;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class WriteXMLFile {

	private String xD;
	private String yD;
	
	public WriteXMLFile(){
		xD = "20";
		yD = "20";
		writeXML();
	}
	
	public void setX(int xDimension){
		xD = Integer.toString(xDimension);
	}
	
	public void setY(int yDimension){
		yD = Integer.toString(yDimension);
	}
	
	public void changeGrid(HashMap<Coordinate, Cell> gridMap, String cellType){
	    try {

	    	File fXmlFile = new File(".\\src\\cellsociety_team17\\Parameters.xml");
	    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	Document doc = dBuilder.parse(fXmlFile);	    			
	    	doc.getDocumentElement().normalize();
	    	
	    	NodeList simulations = doc.getElementsByTagName(cellType);
	    	for (int i = 0; i < simulations.getLength(); i++){
	    		Node sNode = simulations.item(i);
	    		Element sElement = (Element) sNode;
	    		int xCoord = Integer.parseInt(sElement.getAttribute("xCoordinate"));
	    		int yCoord = Integer.parseInt(sElement.getAttribute("yCoordinate"));
	    		Coordinate newCoord = new Coordinate(xCoord,yCoord);
	    		sElement.setAttribute(cellType, Double.toString(gridMap.get(newCoord).getMyState()));
	    	}
	    }catch (Exception e) {

	    	e.printStackTrace();
	        }
	}
	
	public void writeXML() {		
	  try {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		Element parameter = doc.createElement("Parameter");
		doc.appendChild(parameter);

		// Titles elements
		Element titles = doc.createElement("Titles");
		parameter.appendChild(titles);
		
		// Simulation elements
		addTitles(doc, titles, "Segregation","3");
		addTitles(doc, titles, "PredatorPrey","3");
		addTitles(doc, titles, "Fire","3");
		addTitles(doc, titles, "GameOfLife","2");	
		
		// dimension elements 
		Element dimension = doc.createElement("Dimension");
		parameter.appendChild(dimension);
		
		Element xDimension = doc.createElement("xDimension");
		xDimension.appendChild(doc.createTextNode(xD));
		dimension.appendChild(xDimension);
		
		Element yDimension = doc.createElement("yDimension");
		yDimension.appendChild(doc.createTextNode(yD));
		dimension.appendChild(yDimension);
				
		// cells elements
		Element fireCells = doc.createElement("FireCells");
		parameter.appendChild(fireCells);
		
		Element lifeCells = doc.createElement("lifeCells");
		parameter.appendChild(lifeCells);
		
		Element segregationCells = doc.createElement("segregationCells");
		parameter.appendChild(segregationCells);
		
		Element watorCells = doc.createElement("WaTorCells");
		parameter.appendChild(watorCells);
		
		for (int i = 0; i < Integer.parseInt(xDimension.getTextContent()); i++){
			for (int j = 0; j < Integer.parseInt(yDimension.getTextContent()); j++){
				initFireGrid(doc, fireCells, i, j);
				initLifeGrid(doc, lifeCells, i, j);
				initSegregationGrid(doc, segregationCells, i, j);
				initWatorGrid(doc, watorCells, i, j);				
			}
		}		
		
		// write the content into xml file

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		
		StreamResult result = new StreamResult(new File(".\\src\\cellsociety_team17\\Parameters.xml"));

		// Output to console for testing
		// StreamResult result = new StreamResult(//System.out);

		transformer.transform(source, result);

		//System.out.println("File saved!");

	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	}

	public void initFireGrid(Document doc, Element cells, int i, int j) {
		// each cell element
		if(i == 0 || i == Integer.parseInt(xD)-1 || j == 0 || j == Integer.parseInt(yD) - 1){
			setState(doc, cells, i, j, "0", "myFireCell");
		}
		else{
			setState(doc, cells, i, j, "1", "myFireCell");
		}		
	}
	
	public void initLifeGrid(Document doc, Element cells, int i, int j) {
		// each cell element
		Random randNum = new Random();
		setState(doc, cells, i, j, Integer.toString(randNum.nextInt(2)), "myLifeCell");		
	}
	
	public void initSegregationGrid(Document doc, Element cells, int i, int j) {
		// each cell element
		Random randNum = new Random();
		setState(doc, cells, i, j, Integer.toString(randNum.nextInt(3)), "mySegregationCell");		
	}
	
	public void initWatorGrid(Document doc, Element cells, int i, int j) {
		// each cell element
		Random randNum = new Random();
		setState(doc, cells, i, j, Integer.toString(randNum.nextInt(3)), "myWatorCell");		
	}

	public void setState(Document doc, Element cells, int i, int j, String strState, String cellsName) {
		Element myCell = doc.createElement(cellsName);
		cells.appendChild(myCell);				
		
		myCell.setAttribute("xCoordinate", Integer.toString(i));
		myCell.setAttribute("yCoordinate", Integer.toString(j));
		myCell.setAttribute("state", strState);
	}

	public void addTitles(Document doc, Element titles, String myTitle, String statesNum) {
		Element Simulation = doc.createElement("Simulation");
//		Simulation.appendChild(doc.createTextNode(myTitle));
		titles.appendChild(Simulation);
		
		Element simulationName = doc.createElement("Name");
		simulationName.appendChild(doc.createTextNode(myTitle));
		Simulation.appendChild(simulationName);
		
		Element numberOfStates = doc.createElement("NumberOfStates");
		numberOfStates.appendChild(doc.createTextNode(statesNum));
		Simulation.appendChild(numberOfStates);
		
//		firstSimulation.setAttribute("numberOfStates", statesNum);
	}
	
}