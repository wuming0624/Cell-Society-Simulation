package cellsociety_team17;

import java.util.Random;

public class LifeInit extends Init {
	private String title = "myTitle";
	private String author = "myAuthor";
//	private int xDimension = 60;
//	private int yDimension = 60;
	private Grid myLifeGrid;

	public LifeInit() {
		setGrid();
		this.name = "gameOfLife";
	}
	@Override
	public void setGrid() {
		// TODO Auto-generated method stub
		myLifeGrid = new LifeGrid(this.xDimension, this.yDimension);
		reader = new ReadXMLFile("myLifeCell");
		myLifeGrid.setMyGrid(reader.returnGrid());
	}
	
	public Grid returnGrid(){
		return this.myLifeGrid;
	}

	public String returnName() {
		return this.name;
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
