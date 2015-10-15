package cellsociety_team17;

public class InitSubset {
  
  private int currentSugar;
  private final int maximumSugar;
  private int sugarGrowBackRate;
  private int sugar;
  private final int sugarMetabolism;
  private final int vision;
  
  public InitSubset(int currsugar, int maxsugar, int sugargrowrate, int sug, int sugarmet, int vis){
    this.currentSugar = currsugar;
    this.maximumSugar = maxsugar;
    this.sugarGrowBackRate = sugargrowrate;
    this.sugar = sug;
    this.sugarMetabolism = sugarmet;
    this.vision = vis;
  }
  
  public int returnCurrentSugar(){
    return this.currentSugar;
  }

  public int returnMaximumSugar(){
    return this.maximumSugar;
  }
  
  public int returnSugarGrowBackRate(){
    return this.sugarGrowBackRate;
  }
  
  public int returnSugar(){
    return this.sugar;
  }
  
  public int returnSugarMetabolism(){
    return this.sugarMetabolism;
  }
  
  public int returnVision(){
    return this.vision;
  }
  
}
