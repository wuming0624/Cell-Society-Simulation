package cellsociety_team17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class WaTorRules extends Rules {

  private WaTorCell myCell;
  private ArrayList<Object> nextState = new ArrayList<Object>();
  private ArrayList<Object> newInfo;

  private HashMap<Coordinate, Coordinate> myBufferMap;
  private WaTorInit myInitialParam;
  private Random rand = new Random();
  ArrayList<Coordinate> emptyNearby;
  ArrayList<Coordinate> fishNearby;
  HashSet<Coordinate> cellsFilledAlready;

  public WaTorRules(HashMap<Coordinate, Cell> passedMap, HashMap<Coordinate, Coordinate> buffer,
      WaTorInit params) {
    super(passedMap);
    this.myBufferMap = buffer;
    this.myInitialParam = params;

    // TODO Auto-generated constructor stub
  }

  public void fishLogic() {

    if (emptyNearby.size() > 0) {
      // if its ready to give birth
      if (this.myCell.getMyFishTurns() < 1) {
        timeAndSpaceToGiveBirth();

      }

      // or just move and remove a turn
      else {
        fishTimeToMove();

      }
    } else {

      // no where to move
      fishStayPut();
    }
  }

  public ArrayList<Object> sharkLogic() {
    
    if (this.myCell.getMySharkHp() < 1) {
      timeToDie();
    } 
    else if (fishNearby.size() > 0 && this.myCell.getMySharkTurns() < 1) {
      eatAndGiveBirth();
    }

    else if (fishNearby.size() > 0) {
      eatFish();
    } else if (this.myCell.getMySharkTurns() < 1 && emptyNearby.size() > 0) {
      sharkTimeAndSpaceToGiveBirth();
    }

    // if there are empty cells nearby
    else if (emptyNearby.size() > 0) {
      sharkTimeToMove();

    }

    else {
      sharkStayPut();
    }

    return newInfo;
  }

  private void eatAndGiveBirth() {

    this.myCell.resetSharkTurns(this.myInitialParam.returnMySharkTurns());
    this.myCell.resetSharkHp(this.myInitialParam.returnMySharkHp());
    newInfo.add(this.myCell.getMyCoordinate());
    newInfo.add(this.myCell);
    Coordinate childCoord = fishNearby.get(rand.nextInt(fishNearby.size()));
    WaTorCell childCell = new WaTorCell(childCoord, this.myInitialParam);
    childCell.setMyState(2);
    newInfo.add(childCoord);
    newInfo.add(childCell);

  }

  private void sharkTimeAndSpaceToGiveBirth() {
    // TODO Auto-generated method stub
    this.myCell.resetSharkTurns(this.myInitialParam.returnMySharkTurns());
    newInfo.add(0, this.myCell.getMyCoordinate());
    newInfo.add(1, this.myCell);
    Coordinate childCoord = emptyNearby.get(rand.nextInt(emptyNearby.size()));
    cellsFilledAlready.add(childCoord);
    newInfo.add(2, childCoord);
    WaTorCell childcell = new WaTorCell(childCoord, this.myInitialParam);
    childcell.setMyState(2);
    newInfo.add(3, childcell);
  }

  private void eatFish() {
    // TODO Auto-generated method stub
    this.myCell.resetSharkHp(this.myInitialParam.returnMySharkHp());
    this.myCell.minusSharkTurns();
    Coordinate fishcoord = fishNearby.get(rand.nextInt(fishNearby.size()));
    WaTorCell newWater = new WaTorCell(this.myCell.getMyCoordinate(), this.myInitialParam);
    this.myCell.setMyCoordinate(fishcoord);
    newInfo.add(this.myCell.getMyCoordinate());
    cellsFilledAlready.add(this.myCell.getMyCoordinate());
    newInfo.add(this.myCell);
    newInfo.add(newWater.getMyCoordinate());
    newInfo.add(newWater);
    newInfo.add(fishcoord);
  }

  private void setCellAndFishNeighbors(boolean isShark) {

    emptyNearby = new ArrayList<Coordinate>();
    checkNeighbors();
    if (isShark) {
      fishNearby = new ArrayList<Coordinate>();
      
      checkFishToEat();
    }
    newInfo = new ArrayList<Object>();
  }

  @Override
  public ArrayList<Object> nextState(Cell currentCell, Set<Coordinate> newCellList) {
    this.cellsFilledAlready = (HashSet<Coordinate>) newCellList;
    this.myCell = (WaTorCell) currentCell;
    if (this.myCell.getMyState() == 1) {
      setCellAndFishNeighbors(false);
      fishLogic();
    } else if (this.myCell.getMyState() == 2) {
      setCellAndFishNeighbors(true);
      sharkLogic();
    }
    return newInfo;
  }

  private void sharkStayPut() {
    newInfo.add(0, this.myCell.getMyCoordinate());
    this.myCell.minusSharkHp();
    this.myCell.minusSharkTurns();
    newInfo.add(1, this.myCell);
  }

  private void sharkTimeToMove() {
    newInfo.add(0, this.myCell.getMyCoordinate());
    cellsFilledAlready.add(this.myCell.getMyCoordinate());
    WaTorCell newWater = new WaTorCell(this.myCell.getMyCoordinate(), this.myInitialParam);
    newInfo.add(1, newWater);
    Coordinate moveLocation = emptyNearby.get(rand.nextInt(emptyNearby.size()));
    cellsFilledAlready.add(moveLocation);
    newInfo.add(2, moveLocation);
    this.myCell.setMyCoordinate(moveLocation);
    this.myCell.minusSharkTurns();
    this.myCell.minusSharkHp();
    newInfo.add(3, this.myCell);
  }

  private void checkFishToEat() {

    for (int x = -1; x <= 1; x += 2) {
      Coordinate neighbor = new Coordinate(this.myCell.getMyCoordinate().x + x,
          this.myCell.getMyCoordinate().y);
      if (this.returnMap().containsKey(neighbor)) {

        if (this.returnMap().get(neighbor).getMyState() == 1) {
          fishNearby.add(neighbor);
        }
      } else {

        if (this.returnMap().get(this.myBufferMap.get(neighbor)).getMyState() == 1) {
          fishNearby.add(this.myBufferMap.get(neighbor));
        }
      }
    }

    for (int y = -1; y <= 1; y += 2) {
      Coordinate neighbor = new Coordinate(this.myCell.getMyCoordinate().x,
          this.myCell.getMyCoordinate().y + y);
      if (this.returnMap().containsKey(neighbor)) {

        if (this.returnMap().get(neighbor).getMyState() == 1) {
          fishNearby.add(neighbor);
        }
      } else {

        if (this.returnMap().get(this.myBufferMap.get(neighbor)).getMyState() == 1) {
          fishNearby.add(this.myBufferMap.get(neighbor));
        }
      }

    }
  }

  private void timeToDie() {

    newInfo.add(0, this.myCell.getMyCoordinate());
    WaTorCell newWater = new WaTorCell(this.myCell.getMyCoordinate(), this.myInitialParam);
    newInfo.add(1, newWater);
  }

  private void fishStayPut() {
    this.myCell.minusFishTurns();
    newInfo.add(0, this.myCell.getMyCoordinate());
    newInfo.add(1, this.myCell);
  }

  private void fishTimeToMove() {
    newInfo.add(0, this.myCell.getMyCoordinate());
    cellsFilledAlready.add(this.myCell.getMyCoordinate());
    WaTorCell newWater = new WaTorCell(this.myCell.getMyCoordinate(), this.myInitialParam);
    newInfo.add(1, newWater);
    Coordinate moveLocation = emptyNearby.get(rand.nextInt(emptyNearby.size()));
    cellsFilledAlready.add(moveLocation);
    this.myCell.minusFishTurns();
    this.myCell.setMyCoordinate(moveLocation);
    newInfo.add(2, moveLocation);
    newInfo.add(3, this.myCell);
  }

  private void timeAndSpaceToGiveBirth() {
    this.myCell.resetFish(myInitialParam.returnMyFishTurns());
    newInfo.add(0, this.myCell.getMyCoordinate());
    newInfo.add(1, this.myCell);
    Coordinate childCoord = emptyNearby.get(rand.nextInt(emptyNearby.size()));
    newInfo.add(2, childCoord);
    cellsFilledAlready.add(childCoord);
    WaTorCell childcell = new WaTorCell(childCoord, this.myInitialParam);
    childcell.setMyState(1);
    newInfo.add(3, childcell);
  }

  private void checkNeighbors() {

    for (int x = -1; x <= 1; x += 2) {
      Coordinate neighbor = new Coordinate(this.myCell.getMyCoordinate().x + x,
          this.myCell.getMyCoordinate().y);
      if (this.returnMap().containsKey(neighbor)) {

        if (this.returnMap().get(neighbor).getMyState() == 0
            && (!cellsFilledAlready.contains(neighbor))) {
          emptyNearby.add(neighbor);
        }
      } else {

        if (this.returnMap().get(this.myBufferMap.get(neighbor)).getMyState() == 0
            && (!cellsFilledAlready.contains(this.myBufferMap.get(neighbor)))) {
          emptyNearby.add(this.myBufferMap.get(neighbor));
        }
      }
    }

    for (int y = -1; y <= 1; y += 2) {
      Coordinate neighbor = new Coordinate(this.myCell.getMyCoordinate().x,
          this.myCell.getMyCoordinate().y + y);
      if (this.returnMap().containsKey(neighbor)) {

        if (this.returnMap().get(neighbor).getMyState() == 0
            && (!cellsFilledAlready.contains(neighbor))) {
          emptyNearby.add(neighbor);
        }
      } else {

        if (this.returnMap().get(this.myBufferMap.get(neighbor)).getMyState() == 0
            && (!cellsFilledAlready.contains(this.myBufferMap.get(neighbor)))) {
          emptyNearby.add(this.myBufferMap.get(neighbor));
        }
      }

    }
  }

  public HashSet<Coordinate> returnCellsFilled() {
    return this.cellsFilledAlready;
  }

  @Override
  public Cell returnNextState() {
    // TODO Auto-generated method stub
    return null;
  }

}