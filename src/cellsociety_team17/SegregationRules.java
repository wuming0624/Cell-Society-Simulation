package cellsociety_team17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

public class SegregationRules extends Rules {

	private double myThreshold;
	private Cell myCell;
	private Coordinate myCoordinate;
	private Coordinate nextState = myCoordinate;
	private Coordinate emptyNextState = new Coordinate();

	// This Rules subclass is instantiated with the map from the Grid
	// Instantiated in the Processor
	public SegregationRules(Map<Coordinate, Cell> map, double threshold) {
		super(map);
		this.myThreshold = threshold;
	}
	
//	private void findClosestPoint(){
//		
//		for(int i = 0; i < emptyPoints.size(); i++){
//			Coordinate curr = emptyPoints.get(i);
//			Double dist = Math.sqrt(Math.pow((curr.x) - this.myCoordinate.x, 2) + Math.pow((curr.y) - this.myCoordinate.y, 2));
//			closestPoints.put(dist, curr);
//		}
//		
//		this.nextState = closestPoints.get(closestPoints.firstKey());
//	}

	// Below is the method that is called by the Processor class after the Rules
	// Class is Instantiated
	@Override
	public Coordinate nextState(Cell currentCell, Set<Coordinate> newCellList) {

		this.myCell = currentCell;
		this.myCoordinate = this.myCell.getMyCoordinate();
		this.nextState = this.myCoordinate;
		double totalcellscounted = 0;
		double likecells = 0;

		double currentcellstate = currentCell.getMyState();
		boolean neighborsEmpty = false;
		ArrayList<Coordinate> possibleEmptyStates = new ArrayList<Coordinate>();

		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				Coordinate currentcoord = new Coordinate(this.myCoordinate.getMyRow() + x,
						this.myCoordinate.getMyColumn() + y);
				if (this.returnMap().containsKey(currentcoord) && !currentcoord.equals(this.myCoordinate)) {
					if (this.returnMap().get(currentcoord).getMyState() == currentcellstate) {
						likecells += 1;
					}
					// Execute when currentcoord is empty and not contained in the newCellList
					
					
					if(this.returnMap().get(currentcoord).getMyState() == 0 && !newCellList.contains(currentcoord)){
						neighborsEmpty = true;
						emptyNextState = currentcoord;// this will set the new next state to the first empty cell found
						emptyNextState.setLocation(currentcoord.x, currentcoord.y);
						possibleEmptyStates.add(emptyNextState);
						
					}
					totalcellscounted += 1;
				}
			}
		}

		double ratio = likecells / totalcellscounted;
		if (ratio < this.myThreshold && neighborsEmpty) {
			Random randNum = new Random();
			this.nextState = possibleEmptyStates.get(randNum.nextInt(possibleEmptyStates.size()));
		}
			return this.nextState;
	}

	@Override
	public Coordinate returnNextState() {
		return this.nextState;
	}


}
