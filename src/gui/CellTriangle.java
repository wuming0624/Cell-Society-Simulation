package gui;

import cellsociety_team17.Grid;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

// Not sure of what this does
public class CellTriangle extends CellShape {
	// A - top left, B top right, C bottom
	private double AX, AY, BX, BY, CX, CY;

	public CellTriangle(double x, double y, double s, Rectangle gridBackground, Grid grid) {
		super(x, y, s, gridBackground, grid);
		cellShape = new Polygon();

		// TODO Auto-generated constructor stub
	}
public void setUpTriangleCoord(){
	AX = gridBackgroundXShift + getxIndex()/2 * getShapeWidth();
	AY = gridBackgroundYShift + (getyIndex()+1) * getShapeHeight();
	BX = gridBackgroundXShift + (getxIndex()+2)/2 * getShapeWidth();
	BY = AY;
	CX = gridBackgroundXShift + (getxIndex()+1)/2 * getShapeWidth();
	CY = gridBackgroundYShift + getyIndex() * getShapeHeight(); 
}

public void setDownTriangleCoord(){
	AX = gridBackgroundXShift + getxIndex()/2 * getShapeWidth();
	AY = gridBackgroundYShift + getyIndex() * getShapeHeight();
	BX = gridBackgroundXShift + (getxIndex()+2)/2 * getShapeWidth();
	BY = AY;
	CX = gridBackgroundXShift + (getxIndex()+1)/2 * getShapeWidth();
	CY = gridBackgroundYShift + (getyIndex()+1) * getShapeHeight();
}
	
	public void setPolygonCoord(){
		// There are three points
		if((getxIndex()+getyIndex())%2 ==0){
			setDownTriangleCoord();
		}
		else{
			setUpTriangleCoord();
		}
		((Polygon) cellShape).getPoints().addAll(new Double[]{
			   AX, AY,
			   BX, BY,
			   CX, CY});
	}
	
	
	
	
	


}
