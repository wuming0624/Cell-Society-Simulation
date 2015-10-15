package cellsociety_team17;

import java.util.HashSet;

public class LifeProcessor extends Processor{

	private Grid currentGrid;

	public Grid getCurrentGrid() {
		return currentGrid;
	}

	private Grid grid;
	private LifeRules SimulationRules;
	private HashSet<Coordinate> newlyFilledCellList;

	
//	public LifeProcessor(Grid passedGrid, SegregationInit params) {
//		super(passedGrid, params);
//		// getEmptyCells();
//	}
	
	
	public LifeProcessor(Grid passedGrid, Init params) {
		super(passedGrid, params);		// getEmptyCells();
	}

	@Override
	public void initProcessor(Grid passedGrid, Init params) {
		setCurrentGrid(passedGrid);
		setSimulationParameters(params);
		setEmptyNextGrid(params);
	}


	public void setCurrentGrid(Grid passedGrid) {
		// TODO Auto-generated method stub
		this.currentGrid = passedGrid;

	}
	
	protected void setSimulationParameters(Init params) {
		this.Parameters = (LifeInit) params;
	}
	
//	private void setSimulationParameters(LifeInit params) {
//		// TODO Auto-generated method stub
//		this.Parameters = params;
//
//	}


	@Override
	public void setSimulationRules() {
		// TODO Auto-generated method stub
		this.SimulationRules = new LifeRules(this.currentGrid.getMyGrid());
	}
//	@Override
//	public void editsToNextGrid() {
//		// TODO Auto-generated method stub
//		for (int x = 0; x < this.currentGrid.returnXDimension(); x++) {
//			for (int y = 0; y < this.currentGrid.returnYDimension(); y++) {
//				
//				
//				Cell currentCell = this.currentGrid.getMyGrid().get(new Coordinate(x, y));
//				Double newState;
//				newState = this.SimulationRules.nextState(currentCell, new HashSet<Coordinate>());
//				////System.out.println(newState);
//				this.nextGrid.getMyGrid().get(currentCell.getMyCoordinate()).setMyState(newState.intValue());
//			}
//
//		}
//
//	}
	
	public void getToMyNextCell(int x, int y) {
		Cell currentCell = this.currentGrid.getMyGrid().get(new Coordinate(x, y));
		countAllStates(currentCell);
		Double newState;
		newState = this.SimulationRules.nextState(currentCell, new HashSet<Coordinate>());
		////System.out.println(newState);
		this.nextGrid.getMyGrid().get(currentCell.getMyCoordinate()).setMyState(newState.intValue());
	}

	public void setEmptyNextGrid(Init params) {
		// TODO Auto-generated method stub
		nextGrid = new LifeGrid(params.returnXDimension(), params.returnYDimension());

	}


//
//	@Override
//	public void setEmptyNextGrid(Init params) {
//		// TODO Auto-generated method stub
//		
//	}

}
