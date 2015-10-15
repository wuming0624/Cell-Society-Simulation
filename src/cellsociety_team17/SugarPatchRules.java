package cellsociety_team17;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SugarPatchRules extends Rules{

  public SugarPatchRules(Map<Coordinate, Cell> passedMap) {
    super(passedMap);
    // TODO Auto-generated constructor stub
  }

  @Override
  public Object nextState(Cell currentCell, Set<Coordinate> newCellList) {
    // TODO Auto-generated method stub
    SugarPatchCell curr = (SugarPatchCell) currentCell;
    if(newCellList.contains(curr.getMyCoordinate())){
      curr.depleteSugar();
    }
    curr.growSugar();
    
    return curr;
  }
  
  @Override
  public Object returnNextState() {
    // TODO Auto-generated method stub
    return null;
  }

}
