// This entire file is part of my masterpiece.
// Wuming Zhang

package cellsociety_team17;

import java.util.Random;

public class SegregationInit extends Init {
	public SegregationInit() {
		setGrid();
		this.name = "segregation";
	}
	@Override
	public void setGrid() {
		myGrid = new SegregationGrid(this.xDimension, this.yDimension);
		reader = new ReadXMLFile("mySegregationCell");
		myGrid.setMyGrid(reader.returnGrid());
	}
}
