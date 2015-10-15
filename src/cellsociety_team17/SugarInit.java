package cellsociety_team17;

import java.util.HashMap;
import java.util.Random;

public class SugarInit extends Init {
  
  private String title = "myTitle";
  private String author = "myAuthor";
  private SugarGrid mySugarGrid;
  
  // For the Patch Cell
  private int maximumsugar = 8;
  private int minsugar = 0;
  private int sugarGrowBackRate = 1;
  private int sugarGrowBackInterval = 1;
  
  // For the agent Cell
  private int agentsugarmax = 25;
  private int agentsugarmin = 5;
  private int sugarMetabolism = 6;
  private int metabmin = 1;
  private int vision = 4;
  private int vismin = 1;
  
  // number of agents
  private int numagents = 51;
  private Random randNum = new Random();
  private int randombounds = xDimension*yDimension/(numagents);
  

  public SugarInit(){
    setGrid();
    this.name = "sugar";
  }

  @Override
  public Grid returnGrid() {
    return this.mySugarGrid;
  }

  private void setAgentsGrid() {
    HashMap<Coordinate, SugarAgentCell> agentsMap = this.mySugarGrid.returnAgentsGrid();
    
    
    for(int i = 0; i < xDimension; i++){
      for(int j = 0; j < yDimension; j++){
        int checktoplaceagent = randNum.nextInt(randombounds);
        if(checktoplaceagent == 0){
          int initialsugarsupply = randNum.nextInt(agentsugarmax - agentsugarmin) + agentsugarmin;
          int initialmetabolism = randNum.nextInt(sugarMetabolism - metabmin) + metabmin;
          int initialvision = randNum.nextInt(vision - vismin) + vismin;
          
          SugarAgentCell x = new SugarAgentCell(new Coordinate(i,j), initialsugarsupply, initialmetabolism, initialvision);
          agentsMap.put(new Coordinate(i,j), x);
        }
      }
    }
    
  }



  @Override
  public void setGrid() {
    mySugarGrid = new SugarGrid(this.xDimension, this.yDimension);
    for(int i = 0; i < xDimension; i++){
      for(int j = 0; j < yDimension; j++){
        // Check for below values to be correct
        
        SugarPatchCell x  = new SugarPatchCell(new Coordinate(i,j), randNum.nextInt(maximumsugar-minsugar) + minsugar, sugarGrowBackRate, sugarGrowBackInterval);
        this.mySugarGrid.getMyGrid().put(new Coordinate(i,j), x);
      }
    }
    
    setAgentsGrid();

  }







}
