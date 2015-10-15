package cellsociety_team17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class WaTorProcessor extends Processor {

  private WaTorInit Parameters;
  private HashMap<Coordinate, Coordinate> bufferMap;

  public Grid getCurrentGrid() {
    return currentGrid;
  }

  private WaTorGrid nextGrid;
  private WaTorRules SimulationRules;
  private HashSet<Coordinate> newlyFilledCellList;
  private int fish = 0;

  public WaTorProcessor(Grid passedGrid, Init params) {
    super(passedGrid, params);
    // getEmptyCells();
    this.Parameters = (WaTorInit) params;
  }

  private void createBufferMap() {
    // TODO Auto-generated method stub
    bufferMap = new HashMap<Coordinate, Coordinate>();
    for (int x = 0; x < this.currentGrid.returnXDimension(); x++) {
      bufferMap.put(new Coordinate(x, -1), new Coordinate(x, this.currentGrid.returnYDimension() - 1));
      bufferMap.put(new Coordinate(x, this.currentGrid.returnYDimension()), new Coordinate(x, 0));
    }
    for (int y = 0; y < this.currentGrid.returnYDimension(); y++) {
      bufferMap.put(new Coordinate(-1, y), new Coordinate(this.currentGrid.returnXDimension() - 1, y));
      bufferMap.put(new Coordinate(this.getCurrentGrid().returnXDimension(), y), new Coordinate(0, y));
    }

  }

  @Override
  public void initProcessor(Grid passedGrid, Init params) {

    setCurrentGrid(passedGrid);
    setSimulationParameters(params);
    setEmptyNextGrid(params);
    createBufferMap();
  }



  public void setEmptyNextGrid(Init params) {
    nextGrid = new WaTorGrid(params.returnXDimension(), params.returnYDimension());
  }

  public WaTorGrid generateNextGrid() {
    this.resetStateCount();
	newlyFilledCellList = new HashSet<Coordinate>();
    setSimulationRules();
    editsToNextGrid(this.Parameters);
    return this.nextGrid;
  }

  @Override
  public void setSimulationRules() {
    this.SimulationRules = new WaTorRules(this.currentGrid.getMyGrid(), bufferMap, Parameters);
  }

  @Override
  public void editsToNextGrid(Init z) {
    // TODO Auto-generated method stub
    fish = 0;
    HashSet<Coordinate> fishEaten = new HashSet<Coordinate>();
    newlyFilledCellList.clear();

    for (int x = 0; x < this.currentGrid.returnXDimension(); x++) {
      for (int y = 0; y < this.currentGrid.returnYDimension(); y++) {

        Cell currentCell = this.currentGrid.getMyGrid().get(new Coordinate(x, y));
        this.countAllStates(currentCell);
        if(currentCell.getMyState() == 1){
          fish +=1;
        }

        if ((currentCell.getMyState() == 0) || fishEaten.contains(currentCell.getMyCoordinate())) {
          continue;
        }

        ArrayList<Object> newInformation;
        
        
        newInformation = this.SimulationRules.nextState(currentCell, newlyFilledCellList);
        newlyFilledCellList.addAll(this.SimulationRules.returnCellsFilled());
        
        if(newInformation.size() > 4){
          fishEaten.add((Coordinate)newInformation.get(4));
        }
        
        for(int i = 0; i < newInformation.size(); i+=2){
          if(i >= 4){
            break;
          }
          this.nextGrid.getMyGrid().put((Coordinate )newInformation.get(i), (WaTorCell) newInformation.get(i+1));
        }
        
        
      }
    }

  }

  @Override
  public void getToMyNextCell(int x, int y) {
    // TODO Auto-generated method stub
    
  }



}