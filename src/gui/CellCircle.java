package gui;

import cellsociety_team17.Grid;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class CellCircle extends CellShape {

	public CellCircle(double x, double y, double s, Rectangle gridBackground, Grid grid) {
		super(x, y, s, gridBackground, grid);
		cellShape = new Circle();
		((Circle) cellShape).setRadius(shapeWidth*1/4);
	}
	public void setCircleCoords(){
		((Circle) cellShape).setCenterX(getShapeWidth() * (getxIndex() +0.5)+ gridBackgroundXShift);
		((Circle) cellShape).setCenterY(getShapeHeight() * (getyIndex() + 0.5)+ getyIndex() + gridBackgroundYShift);

	}
	
}
