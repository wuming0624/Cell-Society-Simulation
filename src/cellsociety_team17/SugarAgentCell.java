package cellsociety_team17;

public class SugarAgentCell extends Cell {

  private int sugar;
  private final int sugarMetabolism;
  private final int vision;

  public SugarAgentCell(Coordinate coord, int initSugar, int sugarMetab, int sugarVis) {
    super(coord);
    this.sugar = initSugar;
    this.sugarMetabolism = sugarMetab;
    this.vision = sugarVis;
    this.setMyState(1);
  }
  
  public int returnVision(){
    return this.vision;
  }
  
  public void addSugar(int addition){
    this.sugar += addition;
  }
  
  public void metabolizeSugar(){
    this.sugar -= sugarMetabolism;
    if(this.sugar <= 0){
      agentDies();
    }
  }

  private void agentDies() {
    this.setMyState(-1);
  }
  
}
