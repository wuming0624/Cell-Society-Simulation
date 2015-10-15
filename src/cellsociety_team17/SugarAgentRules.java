package cellsociety_team17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SugarAgentRules extends Rules {

  HashMap<Coordinate, SugarAgentCell> myAgentMap;

  public SugarAgentRules(Map<Coordinate, Cell> passedMap,
      HashMap<Coordinate, SugarAgentCell> agentMap) {
    super(passedMap);
    myAgentMap = agentMap;
    // TODO Auto-generated constructor stub
  }

  @Override
  public Object nextState(Cell currentCell, Set<Coordinate> occupied) {
    // TODO Auto-generated method stub
    SugarAgentCell current = (SugarAgentCell) currentCell;
    int vision = current.returnVision();

    ArrayList<Coordinate> unoccupiedSpots = createUnoccupiedList(occupied, current, vision,
        new ArrayList<Coordinate>());
    
    if(unoccupiedSpots.size() == 0){
      current.metabolizeSugar();
    }
    else if(unoccupiedSpots.size() == 1){
      SugarPatchCell opencell = (SugarPatchCell) this.returnMap().get(unoccupiedSpots.get(0));
      current.addSugar(opencell.returnSugarAmount());
      current.metabolizeSugar();
      current.setMyCoordinate(opencell.getMyCoordinate());
    }
    else if(unoccupiedSpots.size() > 1){
      Coordinate closestspot = findClosestCoordinate(unoccupiedSpots, current.getMyCoordinate());
      SugarPatchCell opencell = (SugarPatchCell) this.returnMap().get(closestspot);
      current.addSugar(opencell.returnSugarAmount());
      current.metabolizeSugar();
      current.setMyCoordinate(opencell.getMyCoordinate());
    }

    return current;
  }

  private Coordinate findClosestCoordinate(ArrayList<Coordinate> unoccupiedSpots,
      Coordinate myCoordinate) {
    // TODO Auto-generated method stub
    
    int spotindex = -1;
    double shortestdistance = -1;
    
    for(int i = 0; i < unoccupiedSpots.size(); i++){
      Coordinate coord1 = myCoordinate;
      Coordinate coord2 = unoccupiedSpots.get(i);
      double distance = Math.sqrt(Math.pow(coord2.x - coord1.x, 2) + Math.pow(coord2.y - coord1.y, 2));
      
      if(shortestdistance < 0 || shortestdistance > distance){
        shortestdistance = distance;
        spotindex = i;
      }
    }
    
    return unoccupiedSpots.get(spotindex);
  }

  private ArrayList<Coordinate> createUnoccupiedList(Set<Coordinate> occupiedCells,
      SugarAgentCell current, int vision, ArrayList<Coordinate> unoccupiedSpots) {
    for (int x = -1 * vision; x <= vision; x++) {
      for (int y = -1 * vision; y <= vision; y++) {
        Coordinate curr = new Coordinate(current.getMyCoordinate().x + x,
            current.getMyCoordinate().y + y);
        if(this.returnMap().containsKey(curr)){
          addToUnoccupiedList(occupiedCells, unoccupiedSpots, curr);
        }
      }
    }

    return unoccupiedSpots;
  }

  private void addToUnoccupiedList(Set<Coordinate> occupiedCells,
      ArrayList<Coordinate> unoccupiedSpots, Coordinate curr) {

    if (!occupiedCells.contains(curr)) {

      if (unoccupiedSpots.size() == 0) {
        unoccupiedSpots.add(curr);
      }

      else {
        
        
        SugarPatchCell currentPatch = (SugarPatchCell) this.returnMap().get(curr);
        SugarPatchCell topPatch = (SugarPatchCell) this.returnMap().get(unoccupiedSpots.get(0));

        if (currentPatch.returnSugarAmount() > topPatch.returnSugarAmount()) {
          unoccupiedSpots.clear();
          unoccupiedSpots.add(currentPatch.getMyCoordinate());
        } else if (currentPatch.returnSugarAmount() == topPatch.returnSugarAmount()) {
          unoccupiedSpots.add(currentPatch.getMyCoordinate());
        }

      }

    }
  }

  @Override
  public Object returnNextState() {
    // TODO Auto-generated method stub
    return null;
  }

}
