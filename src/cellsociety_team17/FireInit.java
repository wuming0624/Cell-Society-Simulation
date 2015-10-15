package cellsociety_team17;

public class FireInit extends Init{
	private String title = "myTitle";
	private String author = "myAuthor";
//	private int xDimension = 30;
//	private int yDimension = 30;
	private double probCatch = 0.75;
	private Grid myFireGrid;
	
	public FireInit() {
		setGrid();
		this.name = "fire";
	}


	@Override
	public void setGrid() {
		myFireGrid = new FireGrid(this.xDimension, this.yDimension);
		reader = new ReadXMLFile("myFireCell"); 
		myFireGrid.setMyGrid(reader.returnGrid());		
		myFireGrid.getMyGrid().get(new Coordinate(xDimension/2, yDimension/2)).setMyState(2);
	}

	public Grid returnGrid(){
		return this.myFireGrid;
	}

	public String returnName() {
		return this.name;
	}
	
	public double returnProbCatch(){
		return this.probCatch;
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
}
