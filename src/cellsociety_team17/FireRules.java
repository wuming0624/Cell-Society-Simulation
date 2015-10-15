package cellsociety_team17;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class FireRules extends Rules {

	private double myProbCatch;
	private Cell myCell;
	private Coordinate myCoordinate;
	private double nextState;

	public FireRules(Map<Coordinate, Cell> passedMap, double myPCatch) {
		super(passedMap);
		this.myProbCatch = myPCatch;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Double nextState(Cell currentCell, Set<Coordinate> newCellList) {
		// TODO Auto-generated method stub
		this.myCell = currentCell;
		this.myCoordinate = this.myCell.getMyCoordinate();



		double currentcellstate = currentCell.getMyState();
		boolean neighborBurning = false;
		if(currentcellstate == 2){
			return new Double(0);
		}
		else
		{
			for(int i = -1; i <= 1; i+=2){
				Cell neighborY = this.returnMap().get(new Coordinate(currentCell.getMyCoordinate().x, currentCell.getMyCoordinate().y + i));
				Cell neighborX = this.returnMap().get(new Coordinate(currentCell.getMyCoordinate().x + i, currentCell.getMyCoordinate().y));
				if(neighborY.getMyState() == 2 || neighborX.getMyState() == 2){
					neighborBurning = true;
				}
			}
			if(neighborBurning){
				if(Math.random() < this.myProbCatch){
					return new Double(2);
				}
			}
			return new Double(1);
		}
		
		
		
		

	}

	@Override
	public Double returnNextState() {
		// TODO Auto-generated method stub
		return null;
	}

}
