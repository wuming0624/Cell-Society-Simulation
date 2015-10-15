// This entire file is part of my masterpiece.
// Wuming Zhang
package cellsociety_team17;

import java.util.ArrayList;
import java.util.List;


public abstract class Cell {
	private ArrayList<Double> states; 
	private double myState;
	private Coordinate myCoordinate;


	/**
	 * Create an empty cell with default state
	 */

	

	
	/** 
	 * @param statesXML 
	 * Create an empty cell with states info from XML
	 */
	public Cell(Coordinate coord){
		myState = 0; // state 0 is reserved for empty
		myCoordinate = coord;
	}
	
	public List<Double> getMyStates(){
		return states;
	}
	
	/**
	 * Get a cell's state information
	 * @return
	 */
	public double getMyState(){
		return myState;
	}
	
	public Coordinate getMyCoordinate(){
		return myCoordinate;
	}
	
	public void setMyCoordinate(Coordinate newCoordinate){
		myCoordinate = newCoordinate;
	}
	
	/**
	 * Set a cell to a new state
	 * @param newState
	 */
	public void setMyState(double newState){
		myState = newState;
	}
	

}
