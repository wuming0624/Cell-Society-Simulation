package gui;

import java.util.ArrayList;
import java.util.List;


import cellsociety_team17.Grid;
import cellsociety_team17.Init;
import cellsociety_team17.SegregationInit;
import cellsociety_team17.LifeInit;
import cellsociety_team17.Main;
import cellsociety_team17.Processor;
import cellsociety_team17.SegregationGrid;
import cellsociety_team17.SegregationProcessor;
import cellsociety_team17.WaTorInit;
import cellsociety_team17.WaTorProcessor;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.chart.XYChart.Series;
import javafx.util.Duration;

public class SimModel {
	private Timeline timeline;
	private SimView simView;
//	private Grid segGrid;
	private Processor[] processorArray;
//	private SegregationInit initArray;
//	
	// temp for fire, until InitClass is extracted
//	private Grid fireGrid;
//	private FireInit initArray;
	private List<CellShape> listOfCellRectangles;
	private int CELLSHAPE_KEY = 0; // 0 is rectangle, 1 is triangle
	// temp for life, until InitClass is extracted
	private Grid grid;
	private Init[] initArray;
	private double[] stateCount;
	/**
	 * @return the stateCount
	 */
	public double[] getStateCount() {
		return stateCount;
	}

	/**
	 * @param stateCount the stateCount to set
	 */
	public void setStateCount(double[] stateCount) {
		this.stateCount = stateCount;
	}

	/**
	 * @return the initArray
	 */
	public Init[] getInitArray() {
		return initArray;
	}
	private int CURRENT_SIM; // Set to Fire

	/**
	 * @return the CURRENT_SIM
	 */
	public int getCurrentSim() {
		return CURRENT_SIM;
	}
    private int FRAMES_PER_SECOND = 1;
    /**
	 * @return the fRAMES_PER_SECOND
	 */
	public int getFPS() {
		return FRAMES_PER_SECOND;
	}

	/**
	 * @param fRAMES_PER_SECOND the fRAMES_PER_SECOND to set
	 */
	public void setFPS(int fps) {
		FRAMES_PER_SECOND = fps;
	}

	private final double DEFAULT_FRAME_DURATION = 1000 / FRAMES_PER_SECOND;
	
	/**
	 * @return the defaultFrameDuration
	 */
	public double getDefaultFrameDuration() {
		return DEFAULT_FRAME_DURATION;
	}

	private double FRAME_DURATION = DEFAULT_FRAME_DURATION;
    /**
	 * @param fRAME_DURATION the fRAME_DURATION to set
	 */
	public void setFrameDuration(double frameDuration) {
		FRAME_DURATION = frameDuration;
	}

	/**
	 * @return the frame duration. 
	 * 	
	 */
	public double getFrameDuration() {
		return FRAME_DURATION;
	}
	public void init(SimView view, Timeline simTime, Processor[] process, Init[] x, int currentSim){
		timeline=simTime;
		simView=view;
		processorArray = process;
		initArray = x;
		CURRENT_SIM = currentSim;
		
	}

	public void iterate(){
		increaseFrameCount(simView);
		// Remove all Cell Rectangles
		stateCount = processorArray[CURRENT_SIM].getStateCount();
		// Print number of states
		//for(int i=0;i<(int)processorArray[CURRENT_SIM].getStateCount().length;i++){
			////System.out.print((int) processorArray[CURRENT_SIM].getStateCount()[i]+ " ");
		//}
		//System.out.println(" ");
		// Checks
		checkWindowWidth();
		checkWindowHeight();		
		grid = processorArray[CURRENT_SIM].generateNextGrid();
		processorArray[CURRENT_SIM].initProcessor(grid, initArray[CURRENT_SIM]);
		processorArray[CURRENT_SIM].setCurrentGrid(grid);
		if(listOfCellRectangles!=null){
		simView.getRoot().getChildren().removeAll(listOfCellRectangles);
		}
		
		if(CELLSHAPE_KEY==0){
		listOfCellRectangles = simView.generateGridRectangles(grid);
		}
		else if(CELLSHAPE_KEY==1){
		listOfCellRectangles = simView.generateGridTriangles(grid);
		}


	}
	/**
	 * @param simView
	 */
	private void increaseFrameCount(SimView simView) {
		// Increase the frame by one for each iteration
		simView.setStepFrame(simView.getStepFrame()+1);
	}
	
	private void resetFrameCount(SimView simView){
		simView.setStepFrame(0);
	}
	
	/**
	 * 
	 */
	private void checkWindowHeight() {
		simView.getScene().heightProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number oldHeight, Number newHeight) {
				simView.setCurrentHeight((double) newHeight);
				//////System.out.println("New Height: "+ simView.getCurrentHeight());
				// Check if window resized beyond screen's current dimension
				// Grid resize?
			}
		});
	}
	/**
	 * 
	 */
	private void checkWindowWidth() {
		simView.getScene().widthProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number oldWidth, Number newWidth) {
				simView.setCurrentWidth((double) newWidth);
				//////System.out.println("New Width: "+ simView.getCurrentWidth());
				// Check if window resized beyond screen's current dimension
				// Grid resize?
			}
		});
	}


	public void play(){
		timeline.play();
	}
	
	public void pause(){
		timeline.pause();
	}
	
	public void step(){
		timeline.stop();
		iterate();
	}
	
	public void rectShape(){
		timeline.stop();
		CELLSHAPE_KEY = 0;
		simView.getRoot().getChildren().remove(simView.getGridBackground());
		simView.getRoot().getChildren().add(simView.getGridBackground());
		reinitLineChart();
		reinitTimeline();
	}
	public void triangleShape(){
		simView.getRoot().getChildren().remove(simView.getGridBackground());
		simView.getRoot().getChildren().add(simView.getGridBackground());
		timeline.stop();
		CELLSHAPE_KEY = 1;
		reinitLineChart();

		reinitTimeline();
	}
	
	public void adjustSpeed(double sliderRatio){
		timeline.stop();
		setFrameDuration(getDefaultFrameDuration()*sliderRatio);
		reinitKeyFrame();
	}
//	
//	public void switchToFire(){
//		timeline.stop();
//		CURRENT_SIM=0;
//		reinitTimeline();
//	}
//	
//	public void switchToLife(){
//		timeline.stop();
//		CURRENT_SIM = 1;
//		reinitTimeline();
//	}
//
//	public void switchToWaTor(){
//		timeline.stop();
//		CURRENT_SIM = 2;
//		reinitTimeline();
//	}
//	public void switchToSeg(){
//		timeline.stop();
//		CURRENT_SIM = 3;
//		reinitTimeline();
//	}
//	
//	public void switchToSugar(){
//	  timeline.stop();
//	  CURRENT_SIM = 4;
//	  reinitTimeline();
//	}
	
	public void switchSim(int currentSim){
		timeline.stop();
		CURRENT_SIM=currentSim;
		reinitLineChart();
		//simView.getGraphGUI().getLineChart().getData().addAll(simView.getGraphGUI().getSeriesArray());
		reinitTimeline();
	}

	/**
	 * 
	 */
	private void reinitLineChart() {
		simView.getGraphGUI().getLineChart().getData().clear();
		simView.getRoot().getChildren().remove(simView.getGraphGUI().getLineChart());
		simView.setGraphGUI(new GraphGUI());
		simView.getRoot().setRight(simView.getGraphGUI().getLineChart());
	}

	/**
	 * 
	 */
	private void reinitTimeline() {
		resetFrameCount(simView);
		timeline = new Timeline();
		initArray = Main.initInitClassArray();
		processorArray = Main.initProcessorArray();
		init(simView, timeline, processorArray, initArray, CURRENT_SIM);
		reinitKeyFrame();
	}

	private void reinitKeyFrame() {
		KeyFrame frame = new KeyFrame(Duration.millis(getFrameDuration()),e->iterate());
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.getKeyFrames().add(frame);
		timeline.play();
	}
	
	void resetToInitial(){
		initArray[CURRENT_SIM].setGrid();
		reinitTimeline();
	}
}
