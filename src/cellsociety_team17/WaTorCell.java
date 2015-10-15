package cellsociety_team17;

import java.util.ArrayList;

public class WaTorCell extends Cell {
	private WaTorInit myInitialization;
	private int myFishTurns;
	private int mySharkTurns;
	private int mySharkHp;
	
	public WaTorCell(Coordinate coord) {
		super(coord);
	}
	

	public WaTorCell(Coordinate coord, WaTorInit x){
		super(coord);
		myInitialization = x;
		myFishTurns = x.returnMyFishTurns();
		mySharkTurns = x.returnMySharkTurns();
		mySharkHp = x.returnMySharkHp();
	}
	
	public WaTorCell(Coordinate coord, int fishturns, int sharkturns, int sharkhp){
		super(coord);
		myFishTurns = fishturns;
		mySharkTurns = sharkturns;
		mySharkHp = sharkhp;
	}
	
	public int getMyFishTurns(){
		return myFishTurns;
	}
	
	public int getMySharkTurns(){
		return mySharkTurns;
		
	}
	
	public int getMySharkHp(){
		return mySharkHp;
	}
	
	public void minusFishTurns(){
		myFishTurns--;
	}
	
	public void minusSharkTurns(){
		mySharkTurns--;
	}
	
	public void minusSharkHp(){
		mySharkHp--;
	}
	
	public void resetSharkTurns(int sharkturns){
		mySharkTurns = sharkturns;
		this.setMyState(2);
		myFishTurns = 0;
	}
	
	public void resetSharkHp(int sharkhp){
		mySharkHp = sharkhp;
	}
	
	public void resetWater(){
		mySharkTurns = 0;
		this.setMyState(0);
		myFishTurns = 0;
//		mySharkHp = myInitialization.returnMySharkHp();
	}

	public void resetFish(int fishturns){
		myFishTurns = fishturns;
		this.setMyState(1);
		mySharkTurns = 0;
	}
	

	
	public void setMyParem(int fishturns, int sharkturns, int sharkHp){
		myFishTurns = fishturns;
		mySharkTurns = sharkturns;
		mySharkHp = sharkHp;
	}

}