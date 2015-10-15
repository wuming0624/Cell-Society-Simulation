package cellsociety_team17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class Rules {
	private Map<Coordinate, Cell> myMap;

	public Rules(Map<Coordinate, Cell> passedMap) {
		myMap =  passedMap;
	}

	public Map<Coordinate, Cell> returnMap() {
		return myMap;
	}

	public abstract Object nextState(Cell currentCell, Set<Coordinate> newCellList);

	public abstract Object returnNextState();


}
