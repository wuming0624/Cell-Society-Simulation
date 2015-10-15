package gui;

import cellsociety_team17.Grid;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public  class CellShape {
	protected double xIndex;
	protected double yIndex;
	protected double state;
	// Shape needs to be cast in the individual subclasses
	protected Shape cellShape;
	protected Shape gridBackground;
	protected double gridBackgroundXShift;
	protected double gridBackgroundYShift;
	protected double backgroundWidth;
	protected double backgroundHeight;
	protected double xDim;
	protected double yDim;
	/**
	 * @return the shapeWidth
	 */
	public double getShapeWidth() {
		return shapeWidth;
	}

	/**
	 * @return the shapeHeight
	 */
	public double getShapeHeight() {
		return shapeHeight;
	}

	/**
	 * @param shapeWidth the shapeWidth to set
	 */
	public void setShapeWidth(double shapeWidth) {
		this.shapeWidth = shapeWidth;
	}

	/**
	 * @param shapeHeight the shapeHeight to set
	 */
	public void setShapeHeight(double shapeHeight) {
		this.shapeHeight = shapeHeight;
	}

	protected double shapeWidth;
	protected double shapeHeight;

	
	public CellShape(double x, double y, double s, Rectangle gridBackground, Grid grid) {
		// Pull this back up to the superclass

		setGridBackground(gridBackground);
		gridBackgroundXShift = gridBackground.getBoundsInParent().getMinX();
		gridBackgroundYShift = gridBackground.getBoundsInParent().getMinY();
		backgroundWidth = gridBackground.getWidth();
		backgroundHeight = gridBackground.getHeight();
		xDim = grid.returnXDimension();
		yDim = grid.returnYDimension();
		shapeWidth = backgroundWidth/xDim;
		shapeHeight = backgroundHeight/yDim;
		xIndex = x;
		yIndex = y;
		state = s;

	}
	
	/**
	 * @return the gridBackground
	 */
	public Shape getGridBackground() {
		return gridBackground;
	}
	/**
	 * @param gridBackground the gridBackground to set
	 */
	public void setGridBackground(Shape gridBackground) {
		this.gridBackground = gridBackground;
	}
	/**
	 * @return the cellShape
	 */
	public Shape getCellShape() {
		return cellShape;
	}
	/**
	 * @param cellShape the cellShape to set
	 */
	public void setCellShape(Shape cellShape) {
		this.cellShape = cellShape;
	}
	/**
	 * @return the xIndex
	 */
	public double getxIndex() {
		return xIndex;
	}
	/**
	 * @return the yIndex
	 */
	public double getyIndex() {
		return yIndex;
	}
	/**
	 * @return the state
	 */
	public double getState() {
		return state;
	}
	/**
	 * @param xIndex the xIndex to set
	 */
	public void setxIndex(double xIndex) {
		this.xIndex = xIndex;
	}
	/**
	 * @param yIndex the yIndex to set
	 */
	public void setyIndex(double yIndex) {
		this.yIndex = yIndex;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(double state) {
		this.state = state;
	}

}
