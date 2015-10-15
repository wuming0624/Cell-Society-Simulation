package cellsociety_team17;

import java.util.ArrayList;
import java.util.List;

public abstract class Init {

	protected String name;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	private String title = "myTitle";
	private String author = "myAuthor";


	protected ReadXMLFile reader = new ReadXMLFile("");
	
	protected int xDimension;
	protected int yDimension;

	private Cell defaultCell;
	private double threshold = 0.5;
	private ArrayList<Double> states = new ArrayList<Double>();
	protected Grid myGrid;

	public Init() {
		super();
		xDimension = reader.returnXDimension();
		yDimension = reader.returnYDimension();
	}
	public abstract void setGrid();

	
	public Grid returnGrid() {
		return this.myGrid;
	}

	public List<Double> returnStates() {
		return this.states;
	}

	public String returnName() {
		return this.name;
	}

	public double returnThreshold() {
		return this.threshold;
	}

	public String returnTitle() {
		return this.title;
	}

	public String returnAuthor() {
		return this.author;
	}

	public int returnXDimension() {
		return this.xDimension;
	}

	public int returnYDimension() {
		return this.yDimension;
	}

	public Cell returnDefaultCell() {
		return this.defaultCell;
	}

}