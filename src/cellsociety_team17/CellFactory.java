// This entire file is part of my masterpiece.
// Wuming Zhang

package cellsociety_team17;

public class CellFactory {

		public static Cell CreateCell(String cellName, Coordinate currentCoord){
			Cell outCell = null;
			switch (cellName){
			case "mySegregationCell":
				outCell = new SegregationCell(currentCoord);
				break;
				
			case "myWatorCell":
				outCell = new WaTorCell(currentCoord);
				break;
				
			case "myFireCell":
				outCell = new FireCell(currentCoord);
				break;
				
			case "myLifeCell":
				outCell = new LifeCell(currentCoord);
				break;
				
	        default:
	            // throw some exception
	            break;
			}	
			return outCell;
		}
}
