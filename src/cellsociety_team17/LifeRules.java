package cellsociety_team17;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LifeRules extends Rules {

	private Cell myCell;
	private Coordinate myCoordinate;
	private double nextState;

	public LifeRules(Map<Coordinate, Cell> map) {
		super(map);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Double nextState(Cell currentCell, Set<Coordinate> newCellList) {
		// TODO Auto-generated method stub

		this.myCell = currentCell;
		this.myCoordinate = this.myCell.getMyCoordinate();
		double currentcellstate = currentCell.getMyState();
		double numAlive = 0;

		for (int x = -1; x <= 1; x++) {
			for (int y = -1; y <= 1; y++) {
				Coordinate currentcoord = new Coordinate(this.myCoordinate.getMyRow() + x,
						this.myCoordinate.getMyColumn() + y);
				if (this.returnMap().containsKey(currentcoord) && !currentcoord.equals(this.myCoordinate)) {
					if (this.returnMap().get(currentcoord).getMyState() == 1) {
						numAlive += 1;
					}
				}

			}
		}
		
		// 1=live, 0=dead
		if (currentcellstate == 0 && (numAlive == 3)) {
			return new Double(1);

		} else if (currentcellstate == 1) {
			if (numAlive < 2) {
				return new Double(0);
			}
			if (numAlive == 2 || (numAlive == 3)) {
				return new Double(1);
			}
			if (numAlive > 3) {
				return new Double(0);
			}

		}

		return new Double(0);
	}

	@Override
	public Double returnNextState() {
		// TODO Auto-generated method stub
		return this.nextState;
	}

}
