package cellsociety_team17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public abstract class Processor{

	protected Init Parameters;
	protected Grid currentGrid;
	/**
	 * @return the currentGrid
	 */
	public Grid getCurrentGrid() {
		return currentGrid;
	}

	protected Grid nextGrid;
	protected Rules SimulationRules;
	protected HashSet<Coordinate> newlyFilledCellList;
	protected double[] stateCount;

	public Processor(Grid passedGrid, Init params) {
		initProcessor(passedGrid, params);
		// For the three states
		stateCount = new double[3];
		resetStateCount();
	}

	/**
	 * 
	 */
	protected void resetStateCount() {
		for(int i=0;i<stateCount.length;i++){
			stateCount[i]=0;
		}
	}

	/**
	 * @return the stateCount
	 */
	public double[] getStateCount() {
		return stateCount;
	}

	/**
	 * @param passedGrid
	 * @param params
	 */
	public void initProcessor(Grid passedGrid, Init params) {
		setCurrentGrid(passedGrid);
		setSimulationParameters(params);
		setEmptyNextGrid(params);
	}


	public void setCurrentGrid(Grid passedGrid) {
		this.currentGrid = passedGrid;
	}

	protected void setSimulationParameters(Init params) {
		this.Parameters = params;
	}

	public abstract void setEmptyNextGrid(Init params);

	public Grid generateNextGrid() {
		resetStateCount();
		newlyFilledCellList = new HashSet<Coordinate>();
		setSimulationRules();
		editsToNextGrid(Parameters);
		return this.nextGrid;
	}

	public abstract void setSimulationRules();

	public void editsToNextGrid(Init myParam){
		////System.out.println(myParam.returnXDimension());
		////System.out.println("break");
		for (int x = 0; x < myParam.returnXDimension(); x++) {
			for (int y = 0; y < myParam.returnYDimension(); y++) {
				getToMyNextCell(x, y);
			}
		}
	};
	
	public abstract void getToMyNextCell(int x, int y);

	public Grid returnGrid() {
		return nextGrid;
	}
	
	protected void countAllStates(Cell currentCell){
		stateCount[(int) currentCell.getMyState()]++;
	}
}