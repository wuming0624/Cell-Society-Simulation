package cellsociety_team17;

import java.util.ArrayList;

public class SegregationGrid extends Grid{
	
	private ArrayList<Double> segregationStates;

	public SegregationGrid(int xDim, int yDim) {
		super(xDim, yDim);
	}
	
	

	@Override
	public void setDimensions(double rowNum, double colNum) {
		
		
		for (int i = 0; i < rowNum; i++)
		{
			for (int j = 0; j < colNum; j++)
			{
				Coordinate currentCoordinate = new Coordinate(i,j);
				SegregationCell newCell = new SegregationCell(currentCoordinate);
				this.getMyGrid().put(currentCoordinate, newCell);
			}
		}
		
	}



}
