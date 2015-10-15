package cellsociety_team17;

public class SugarPatchCell extends Cell{
  
  private int currentSugar;
  private final int maximumSugar;
  private int sugarGrowBackRate;
  private int sugarGrowBackInterval;
  private int ticks = 0;

  public SugarPatchCell(Coordinate coord, int initMax, int rate, int interval) {
    super(coord);
    this.currentSugar = initMax;
    this.maximumSugar = initMax;
    this.sugarGrowBackRate = rate;
    this.sugarGrowBackInterval = interval;
    this.setMyState(0);
  }
  
  public int returnSugarAmount(){
    return this.currentSugar;
  }
  
  public void depleteSugar(){
    this.currentSugar = 0;
  }
  
  public void growSugar(){
    if(ticks > this.sugarGrowBackInterval){
    if(this.currentSugar < this.maximumSugar){
      if(this.maximumSugar - this.currentSugar < this.sugarGrowBackRate)
      this.currentSugar = this.maximumSugar;
      this.ticks = 0;
    }
    else{
      this.currentSugar += this.sugarGrowBackRate;
      this.ticks = 0;
    }
    }
    else
      this.ticks ++;
  }
  
  
  
  
  

}
