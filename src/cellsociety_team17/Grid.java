package cellsociety_team17;

import java.util.HashMap;
import java.util.Map;

public abstract class Grid {
	private HashMap<Coordinate, Cell> myGrid;
	
	private int xDimension;
	private int yDimension;
	
	/**
	 * Create an empty grid
	 */
	public Grid(int xDim, int yDim){
		myGrid = new HashMap<Coordinate,Cell>();
		setDimensions(xDim, yDim);
		xDimension = xDim;
		yDimension = yDim;
	}

	
	public int returnXDimension(){
		return xDimension;
	}
	
	public int returnYDimension(){
		return yDimension;
	}
	
	/**
	 * Create a grid of given dimensions with empty cells
	 * @param rowNum
	 * @param colNum
	 */
	
//	
	public abstract void setDimensions(double rowNum, double colNum);

	public void setMyGrid(HashMap<Coordinate, Cell> newGrid){
		myGrid = newGrid;
	}
	
	public HashMap<Coordinate, Cell> getMyGrid(){
		return myGrid;
	}
	
	/**
	 * Set a cell at given coordinate to a given new state
	 * @param myCoordinate
	 * @param newState
	 */
	public void setCell(Coordinate myCoordinate, double newState){
		myGrid.get(myCoordinate).setMyState(newState);
	}
}
