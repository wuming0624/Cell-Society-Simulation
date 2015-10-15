package cellsociety_team17;

public class LifeGrid extends Grid {

	public LifeGrid(int xDim, int yDim) {
		super(xDim, yDim);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setDimensions(double rowNum, double colNum) {
		// TODO Auto-generated method stub

		
		for (int i = 0; i < rowNum; i++)
		{
			for (int j = 0; j < colNum; j++)
			{
				Coordinate currentCoordinate = new Coordinate(i,j);
				LifeCell newCell = new LifeCell(currentCoordinate);
				this.getMyGrid().put(currentCoordinate, newCell);
			}
		}
		
	}


}
