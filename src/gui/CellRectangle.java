package gui;

import cellsociety_team17.Grid;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class CellRectangle extends CellShape {

	public void setCellShape(Shape cellShape) {
		this.cellShape = cellShape;
	}

	public CellRectangle(double x, double y, double s, Rectangle gridBackground, Grid grid) {
		// Pull this back up to the superclass
		super(x,y,s,gridBackground,grid);
		cellShape = new Rectangle();

	}

	
	public void setX() {
		((Rectangle) cellShape).setX(
				 (getShapeWidth() * getxIndex() + 0.5 * getxIndex() + gridBackgroundXShift));
	}

	
	public void setY() {
		((Rectangle) cellShape).setY(
				(getShapeHeight() * getyIndex()+ 0.5 * getyIndex() + gridBackgroundYShift));
	}



	public void setRectWidth() {
		((Rectangle) cellShape).setWidth(shapeWidth);
	}
	

	public void setRectHeight() {
		((Rectangle) cellShape).setHeight(shapeHeight);
	}

}
