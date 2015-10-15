package cellsociety_team17;

public class WaTorGrid extends Grid {
	
//	private int fishTurns;
//	private int sharkTurns;
//	private int sharkHp;

	public WaTorGrid(int xDim, int yDim) {
		super(xDim, yDim);
		// TODO Auto-generated constructor stub
	}
	
//	public WaTorGrid(int xDim, int yDim, int fishturns, int sharkturns, int sharkhp){
//		super(xDim, yDim);
////		fishTurns = fishturns;
////		sharkTurns = sharkturns;
////		sharkHp = sharkhp;
//	}

	@Override
	public void setDimensions(double rowNum, double colNum) {
		
		for (int i = 0; i < rowNum; i++)
		{
			for (int j = 0; j < colNum; j++)
			{
				Coordinate currentCoordinate = new Coordinate(i,j);
				WaTorCell newCell = new WaTorCell(currentCoordinate);
				this.getMyGrid().put(currentCoordinate, newCell);
			}
		}

	}

}