package cellsociety_team17;

import java.util.HashMap;

public class SugarGrid extends Grid {
  
  private HashMap<Coordinate, SugarAgentCell> agentsGrid = new HashMap<Coordinate, SugarAgentCell>();

  public SugarGrid(int xDim, int yDim) {
    super(xDim, yDim);
  }
  
  public HashMap<Coordinate, SugarAgentCell> returnAgentsGrid(){
    return this.agentsGrid;
  }
  
  public void setAgentsGrid(HashMap<Coordinate, SugarAgentCell> grid){
    this.agentsGrid = grid;
  }


  @Override
  public void setDimensions(double rowNum, double colNum) {
    // TODO Auto-generated method stub
    for (int i = 0; i < rowNum; i++)
    {
      for (int j = 0; j < colNum; j++)
      {
        Coordinate currentCoordinate = new Coordinate(i,j);
        SugarPatchCell newCell = new SugarPatchCell(currentCoordinate, 0, 0, 0);
        this.getMyGrid().put(currentCoordinate, newCell);
      }
    }
    
    
    
    
    
  }

}
