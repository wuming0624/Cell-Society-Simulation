package cellsociety_team17;

import java.util.ArrayList;
import java.util.Iterator;

public class SegregationProcessor extends Processor {

	/**
	 * @return the currentGrid
	 */
	public Grid getCurrentGrid() {
		return currentGrid;
	}

	public SegregationProcessor(Grid passedGrid, Init params) {
		super(passedGrid, params);
		// getEmptyCells();
	}

	@Override
	public void setSimulationRules() {
		this.SimulationRules = new SegregationRules(this.currentGrid.getMyGrid(), this.Parameters.returnThreshold());
	}

	
	@Override
	public void setEmptyNextGrid(Init params) {
		nextGrid = new SegregationGrid(params.returnXDimension(), params.returnYDimension());
	}

//	@Override
//	public void editsToNextGrid() {
//		for (int x = 0; x < this.currentGrid.returnXDimension(); x++) {
//			for (int y = 0; y < this.currentGrid.returnYDimension(); y++) {
//
//	}
	
	public void getToMyNextCell(int x, int y) {

		Cell currentCell = this.currentGrid.getMyGrid().get(new Coordinate(x, y));
		this.countAllStates(currentCell);
		if (currentCell.getMyState() != 0) {

		Coordinate newLocation = (Coordinate) this.SimulationRules.nextState(currentCell, newlyFilledCellList);
	

		boolean noChange = (currentCell.getMyCoordinate().equals(newLocation));

		// This finds the new cell in the next grid and changes that
		// cells state to the state of the currentCell

		if (!noChange) {
			// Swaps the cell states
			this.nextGrid.getMyGrid().get(newLocation).setMyState(currentCell.getMyState());
			// Adds the newLocation cell to the list
			newlyFilledCellList.add(newLocation);
			this.nextGrid.getMyGrid().get(currentCell.getMyCoordinate()).setMyState(0);
		}
		else{
			this.nextGrid.getMyGrid().get(currentCell.getMyCoordinate()).setMyState(currentCell.getMyState());
		}
	}


	}




}