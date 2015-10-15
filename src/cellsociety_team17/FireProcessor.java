package cellsociety_team17;

import java.util.HashSet;

public class FireProcessor extends Processor {

	// Such that returnProbCatch() will work
	private FireInit Parameters;
	private Grid currentGrid;

	public Grid getCurrentGrid() {
		return currentGrid;
	}

	private FireGrid nextGrid;
	private FireRules SimulationRules;
	private HashSet<Coordinate> newlyFilledCellList;

	public FireProcessor(Grid passedGrid, Init params) {
		super(passedGrid, params);
		// getEmptyCells();
	}



	public void setCurrentGrid(Grid passedGrid) {
		this.currentGrid = passedGrid;
	}

	protected void setSimulationParameters(Init params) {
		this.Parameters = (FireInit) params;
	}

	@Override
	public Grid generateNextGrid() {
		this.resetStateCount();
		newlyFilledCellList = new HashSet<Coordinate>();
		setSimulationRules();
		editsToNextGrid(Parameters);
		return this.nextGrid;
	}

	@Override
	public void setSimulationRules() {

		////System.out.println(this.Parameters);
		////System.out.println(this.Parameters.returnProbCatch());
		this.SimulationRules = new FireRules(this.currentGrid.getMyGrid(), this.Parameters.returnProbCatch());
	}

//	@Override
//	public void editsToNextGrid() {
//		// TODO Auto-generated method stub
//
//		for (int x = 0; x < this.currentGrid.returnXDimension(); x++) {
//			for (int y = 0; y < this.currentGrid.returnYDimension(); y++) {
//
//				getToMyNextCell(x, y);
//
//			}
//
//		}
//
//	}
	
	public void getToMyNextCell(int x, int y){
		Cell currentCell = this.currentGrid.getMyGrid().get(new Coordinate(x, y));
		this.countAllStates(currentCell);
		Double newState = new Double(currentCell.getMyState());
		if (currentCell.getMyState() != 0) {

			newState = this.SimulationRules.nextState(currentCell, new HashSet<Coordinate>());
		}
		this.nextGrid.getMyGrid().get(currentCell.getMyCoordinate()).setMyState(newState.doubleValue());
	}

	public void setEmptyNextGrid(Init params) {
		nextGrid = new FireGrid(params.returnXDimension(), params.returnYDimension());
	}

//	@Override
//	public void setEmptyNextGrid(Init params) {
//		// TODO Auto-generated method stub
//		// Temporary for now
//	}



}
