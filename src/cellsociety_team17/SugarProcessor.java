package cellsociety_team17;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SugarProcessor extends Processor{
  
  private SugarAgentRules agentRules;
  private SugarGrid sugarGrid;

  public SugarProcessor(Grid passedGrid, Init params) {
    super(passedGrid, params);
    //System.out.println(params);
    sugarGrid = (SugarGrid) passedGrid;
  }

  @Override
  public void setEmptyNextGrid(Init params) {
    // TODO Auto-generated method stub
    this.nextGrid = new SugarGrid(params.xDimension, params.yDimension);
    
  }

  @Override
  public void setSimulationRules() {
    this.SimulationRules = new SugarPatchRules(this.currentGrid.getMyGrid());
    this.agentRules = new SugarAgentRules(this.currentGrid.getMyGrid(), this.sugarGrid.returnAgentsGrid());
  }

  @Override
  public void editsToNextGrid(Init myParam){
    
    HashMap<Coordinate, SugarAgentCell> agentMap = this.sugarGrid.returnAgentsGrid();
    HashMap<Coordinate, SugarAgentCell> newAgentMap = new HashMap<Coordinate, SugarAgentCell>();
    
    for(Coordinate currcoord: agentMap.keySet()){
      Cell currentSugarAgent = agentMap.get(currcoord);
      if(currentSugarAgent.getMyState() < 0){
        continue;
      }
      Set<Coordinate> occupied = agentMap.keySet();
      this.countAllStates(currentSugarAgent);
      SugarAgentCell newSugarAgent = (SugarAgentCell) this.agentRules.nextState(currentSugarAgent, occupied);
      newAgentMap.put(newSugarAgent.getMyCoordinate(), newSugarAgent);
    }
    
    
    for(int i = 0; i < myParam.xDimension; i++){
      for(int j = 0; j < myParam.yDimension; j++){
        Cell currentSugarPatch = this.currentGrid.getMyGrid().get(new Coordinate(i,j));
        this.countAllStates(currentSugarPatch);
        SugarPatchCell newSugarPatch = (SugarPatchCell) this.SimulationRules.nextState(currentSugarPatch, newAgentMap.keySet());
        this.nextGrid.getMyGrid().put(newSugarPatch.getMyCoordinate(),  newSugarPatch);
      }
    }
    
   SugarGrid returnGrid = (SugarGrid) this.nextGrid;
   returnGrid.setAgentsGrid(newAgentMap);
   this.nextGrid = returnGrid;
    
    
  }
  
  @Override
  public void getToMyNextCell(int x, int y) {
    // TODO Auto-generated method stub
    
  }

}
