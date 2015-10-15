package cellsociety_team17;

public class FireGrid extends Grid {

	public FireGrid(int xDim, int yDim) {
		super(xDim, yDim);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setDimensions(double rowNum, double colNum) {
		
		for (int i = 0; i < rowNum; i++)
		{
			for (int j = 0; j < colNum; j++)
			{
				Coordinate currentCoordinate = new Coordinate(i,j);
				FireCell newCell = new FireCell(currentCoordinate);
				this.getMyGrid().put(currentCoordinate, newCell);
			}
		}

	}



}
