package cellsociety_team17;
import java.awt.Point;

public class Coordinate extends Point {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * Initialize the coordinates to default
	 */
	public Coordinate(){
		super(0,0);
	}
	
	/**
	 * Initialize the coordinates with given row and column numbers
	 * @param row
	 * @param column
	 */
	public Coordinate(int row, int column){
		super(row, column);
	}
	
	public int getMyRow(){
		return super.x;
	}
	
	public int getMyColumn(){
		return super.y;
	}
}
