package cellsociety_team17;

import java.util.Random;

public class WaTorInit extends Init{
	private String title = "myTitle";
	private String author = "myAuthor";

//	private int xDimension = 10;
//	private int yDimension = 10;

	//private double probCatch = 0.5;
	private WaTorGrid myWaTorGrid;
	private int fishTurns = 6;
	private int sharkTurns = 10;
	private int sharkHp = 5;
	
	public WaTorInit() {
		setGrid();
		this.name = "predatorPrey";
	}

	@Override
	public void setGrid() {
		myWaTorGrid = new WaTorGrid(this.xDimension, this.yDimension);
		Random randNum = new Random();
		for(int i = 0; i < xDimension; i++){
			for(int j = 0; j < yDimension; j++){
				WaTorCell x = (WaTorCell)myWaTorGrid.getMyGrid().get(new Coordinate(i,j));
				x.setMyState(randNum.nextInt(3));
				x.setMyParem(fishTurns, sharkTurns, sharkHp);
			}
		}
		
	}
	
	public WaTorGrid returnGrid(){
		return this.myWaTorGrid;
	}
	public String returnName() {
		return this.name;
	}
//	public double returnProbCatch(){
//		return this.probCatch;
//	}
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
	public int returnMyFishTurns(){
		return this.fishTurns;
	}
	public int returnMySharkTurns(){
		return this.sharkTurns;
	}
	public int returnMySharkHp(){
		return this.sharkHp;
	}
}