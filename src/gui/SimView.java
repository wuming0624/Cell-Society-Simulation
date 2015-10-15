package gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import cellsociety_team17.Cell;
import cellsociety_team17.Coordinate;
import cellsociety_team17.Grid;
import cellsociety_team17.Main;
import cellsociety_team17.SugarAgentCell;
import cellsociety_team17.SugarGrid;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import locale.Resource;

public class SimView {
	private Scene scene;

	/**
	 * @return the scene
	 */
	public Scene getScene() {
		return scene;
	}

	// Initial dimensions supplied by XML
	private double CURRENT_WIDTH = 1100;
	private double CURRENT_HEIGHT = 700;
	private double TEMP_DIMENSION = 700;


	private ComboBoxGUI simCombo;
	private HashMap<String, ResourceBundle> allResourcesBundle;
	private Label stepFrame;
	private BorderPane root;
	private SimModel simModel;

	/**
	 * @return the root
	 */
	public BorderPane getRoot() {
		return root;
	}

	/**
	 * @param root
	 *            the root to set
	 */
	public void setRoot(BorderPane root) {
		this.root = root;
	}

	private Rectangle gridBackground;

	/**
	 * @return the gridBackground
	 */
	public Rectangle getGridBackground() {
		return gridBackground;
	}

	/**
	 * @param gridBackground the gridBackground to set
	 */
	public void setGridBackground(Rectangle gridBackground) {
		this.gridBackground = gridBackground;
	}

	/**
	 * @param cURRENT_WIDTH
	 *            the cURRENT_WIDTH to set
	 */
	public void setCurrentWidth(double currentWidth) {
		CURRENT_WIDTH = currentWidth;
	}

	/**
	 * @param cURRENT_HEIGHT
	 *            the cURRENT_HEIGHT to set
	 */
	public void setCurrentHeight(double currentHeight) {
		CURRENT_HEIGHT = currentHeight;
	}

	/**
	 * @return the stepFrame
	 */
	public int getStepFrame() {
		//System.out.println(Integer.parseInt(stepFrame.getText()));
		return Integer.parseInt(stepFrame.getText());
	}

	/**
	 * @param stepFrame
	 *            the stepFrame to set
	 */
	public void setStepFrame(int frame) {
		this.stepFrame.setText(String.valueOf(frame));
		
	}

	private ButtonGUI buttonGUI;

	private StatesComboBoxGUI statesCombo;
	private String currentStateComboString;
	private VBox fullCtrlsPanel;

	/**
	 * @return the fullCtrlsPanel
	 */
	public VBox getFullCtrlsPanel() {
		return fullCtrlsPanel;
	}

	/**
	 * @param fullCtrlsPanel
	 *            the fullCtrlsPanel to set
	 */
	public void setFullCtrlsPanel(VBox fullCtrlsPanel) {
		this.fullCtrlsPanel = fullCtrlsPanel;
	}

	private Label speedLabel;
	private Label fastSpeed;
	private Label slowSpeed;
	private SliderGUI sliderGUI;

	/**
	 * @return the speedSlider
	 */
	public Slider getSpeedSlider() {
		return sliderGUI.getSpeedSlider();
	}

	private List<CellShape> listOfCellRect;
	private Rectangle gridBack;
	private VBox fullCtrlsPanelVBox;
	private ComboBox<String> statesComboBox;
	private GraphGUI graphGUI;

	/**
	 * @return the graphGUI
	 */
	public GraphGUI getGraphGUI() {
		return graphGUI;
	}

	/**
	 * @param graphGUI the graphGUI to set
	 */
	public void setGraphGUI(GraphGUI graphGUI) {
		this.graphGUI = graphGUI;
	}

	public SimView(SimModel model, Resource resource, Stage stage) {
		simModel = model;
		allResourcesBundle = resource.getAllResourceBundles();

		simCombo = new SimComboBoxGUI(model, allResourcesBundle);
		
		buttonGUI = new ButtonGUI(model, allResourcesBundle);
		sliderGUI = new SliderGUI(model);


		root = new BorderPane();
		fullCtrlsPanel = (VBox) makeFullCtrlsPanel();
		// fullCtrlsPanel.setPrefHeight(CURRENT_HEIGHT*3/4);
		root.setTop(fullCtrlsPanel);
		gridBackground = makeGridBackground();
		root.setCenter(gridBackground);
		//System.out.println("Frame Step="+getStepFrame());
		graphGUI = new GraphGUI();
		//graphGUI.getLineChart().getData().addAll(graphGUI.getSeriesArray());
		//root.setBottom(graphGUI.getLineChart());
		
		//ScrollPane scrollPane = new ScrollPane();
		//scrollPane.setContent(graphGUI.getLineChart());
		root.setRight(graphGUI.getLineChart());
		scene = new Scene(root, CURRENT_WIDTH, CURRENT_HEIGHT);
		scene.getStylesheets().add("gui/main.css");
	}

	/**
	 * 
	 */


	private Rectangle makeGridBackground() {
		gridBack = new Rectangle();
		// KEEP DIMENSIONS THE SAME
		gridBack.setWidth(CURRENT_HEIGHT * 5 / 7);
		gridBack.setHeight(CURRENT_HEIGHT * 5 / 7);
		// Assuming X starts from the left side of the screen
		gridBack.setY(fullCtrlsPanel.getBoundsInParent().getMaxY());
		gridBack.setFill(Color.WHITE);
		return gridBack;
	}

	public List<CellShape> generateGridTriangles(Grid grid){
		
		Map<Coordinate, Cell> gridMap =  grid.getMyGrid();

		listOfCellRect = new ArrayList<CellShape>();
		
		for (Coordinate key : gridMap.keySet()) {
			CellShape cellShapeObject = new CellTriangle(key.getX(), key.getY(), gridMap.get(key).getMyState(), gridBackground, grid);
			Shape cellShape = cellShapeObject.getCellShape();

			// Bug: ensure the tiles move with the gridBackground when resized
			// cellShapeObject must set width first before setting the position

			// Set polygon coords here
			if(cellShapeObject instanceof CellTriangle){
				((CellTriangle) cellShapeObject).setPolygonCoord();
			}
			
			cellShape.setOnMouseClicked(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent event) {
						if(currentStateComboString!=null){
						gridMap.get(key).setMyState(statesCombo.getStatesMap().get(currentStateComboString));
						}
				}
			});

			if (cellShapeObject.getState() == 1) {
				cellShape.setFill(Color.BLANCHEDALMOND);
			} else if (cellShapeObject.getState() == 2) {
				cellShape.setFill(Color.PINK);
			} else {
				cellShape.setFill(Color.MEDIUMAQUAMARINE);
			}
			graphGUI.addToGraph(getStepFrame(),cellShapeObject.getState(), simModel.getStateCount()[(int) cellShapeObject.getState()]);
			//graphGUI.getLineChart().getData().addAll(graphGUI.getSeriesArray());
			root.getChildren().add(cellShape);
			listOfCellRect.add(cellShapeObject);
		}
		return listOfCellRect;
		
	}
	
	public List<CellShape> generateGridRectangles(Grid grid) {

		Map<Coordinate, Cell> gridMap =  grid.getMyGrid();

		listOfCellRect = new ArrayList<CellShape>();
		
		for (Coordinate key : gridMap.keySet()) {
			CellShape cellShapeObject = new CellRectangle(key.getX(), key.getY(), gridMap.get(key).getMyState(), gridBackground, grid);
			Shape cellShape = cellShapeObject.getCellShape();

			// Bug: ensure the tiles move with the gridBackground when resized
			// cellShapeObject must set width first before setting the position

			if(cellShapeObject instanceof CellRectangle){
				((CellRectangle) cellShapeObject).setRectWidth();
				((CellRectangle) cellShapeObject).setRectHeight();
			}
			
			((CellRectangle) cellShapeObject).setX();
			((CellRectangle) cellShapeObject).setY();
			cellShape.setOnMouseClicked(new EventHandler<MouseEvent>(){
				@Override
				public void handle(MouseEvent event) {
						if(currentStateComboString!=null){
						gridMap.get(key).setMyState(statesCombo.getStatesMap().get(currentStateComboString));
						}
				}
			});

			if (cellShapeObject.getState() == 1) {
				cellShape.setFill(Color.BLANCHEDALMOND);
			} else if (cellShapeObject.getState() == 2) {
				cellShape.setFill(Color.PINK);
			} else {
				cellShape.setFill(Color.MEDIUMAQUAMARINE);
			}
			//System.out.println(cellShapeObject.getState());
			//System.out.println(simModel.getStateCount());
			//System.out.println((int) cellShapeObject.getState());
			graphGUI.addToGraph(getStepFrame(),cellShapeObject.getState(), simModel.getStateCount()[(int) cellShapeObject.getState()]);

			root.getChildren().add(cellShape);
			listOfCellRect.add(cellShapeObject);
		}
		
		if(grid instanceof SugarGrid){
			Map<Coordinate, SugarAgentCell> agentMap = ((SugarGrid)grid).returnAgentsGrid();
			
			for(Coordinate key:agentMap.keySet()){
				CellShape cellShapeObject = new CellCircle(key.getX(), key.getY(), gridMap.get(key).getMyState(), gridBackground, grid);
				Shape cellShape = cellShapeObject.getCellShape();

				// Bug: ensure the tiles move with the gridBackground when resized
				// cellShapeObject must set width first before setting the position
				((CellCircle) cellShapeObject).setCircleCoords();
				
				cellShape.setOnMouseClicked(new EventHandler<MouseEvent>(){
					@Override
					public void handle(MouseEvent event) {
							if(currentStateComboString!=null){
							gridMap.get(key).setMyState(statesCombo.getStatesMap().get(currentStateComboString));
							}
					}
				});


					cellShape.setFill(Color.BLANCHEDALMOND);

				root.getChildren().add(cellShape);
				listOfCellRect.add(cellShapeObject);
				
		
			}
			
		}

		
		return listOfCellRect;
	}

	/**
	 * @return the listOfCellRect
	 */
	public List<CellShape> getListOfCellRect() {
		return listOfCellRect;
	}

	/**
	 * @param listOfCellRect
	 *            the listOfCellRect to set
	 */
	public void setListOfCellRect(List<CellShape> listOfCellRect) {
		this.listOfCellRect = listOfCellRect;
	}


	private Label makeLabel(String text) {
		return new Label(text);
	}

	private Node makeFullCtrlsPanel() {
		fullCtrlsPanelVBox = new VBox();
		
		fullCtrlsPanelVBox.getChildren().addAll(makeCtrlsPanelLine1(), makeCtrlsPanelLine2(),makeCtrlsPanelLine3());
		return fullCtrlsPanelVBox;
	}

	private Node makeCtrlsPanelLine1() {
		HBox hbox = new HBox();
		// Temporary switch to Fire
		hbox.getChildren().addAll(buttonGUI.makeRunButton(), 
								  buttonGUI.makeStopButton(), 
								   buttonGUI.makeResetInitButton(simCombo), 
								  makeSpeedPanel());
		return hbox;
	}

	private Node makeCtrlsPanelLine2() {
		HBox hbox = new HBox();
		stepFrame = makeLabel("0");
		
		hbox.getChildren().addAll(makeChangeSimPanel(), buttonGUI.makeStepButton(), stepFrame, buttonGUI.makeRectShapeButton(), buttonGUI.makeTriangleShapeButton());
		return hbox;
	}

	private Node makeCtrlsPanelLine3(){
		// Options for each state
		// use makeComboBox method
		statesCombo = new StatesComboBoxGUI(simModel,allResourcesBundle,Main.initInitClassArray());
		//System.out.println(statesCombo.initializeComboBoxOptions());
		statesComboBox = statesCombo.makeComboBox(statesCombo.initializeComboBoxOptions());
		
		statesComboBox.valueProperty().addListener(new ChangeListener<String>(){
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				// TODO Auto-generated method stub
				//System.out.println(arg2);
				currentStateComboString = arg2;
			}
			
		});
		return statesComboBox;
	}

	private Node makeSpeedPanel() {
		HBox hbox = new HBox();
		speedLabel = makeLabel(allResourcesBundle.get("common").getString("speed") + ": ");
		fastSpeed = makeLabel(allResourcesBundle.get("common").getString("speed_fast"));
		slowSpeed = makeLabel(allResourcesBundle.get("common").getString("speed_slow"));
		sliderGUI.setSpeedSlider(sliderGUI.makeFrameSpeedSlider());
		hbox.getChildren().addAll(speedLabel, fastSpeed, sliderGUI.getSpeedSlider(), slowSpeed);
		return hbox;
	}

	private Node makeChangeSimPanel() {

		ComboBox<String> comboBox = simCombo.makeComboBox(simCombo.initializeComboBoxOptions());
		 comboBox.valueProperty().addListener(new ChangeListener<String>() {

				@Override public void changed(ObservableValue<? extends String> obVal, String oldString, String newString) {
		            // Uses the Array structure of Init[] and Processor[] to change Sim
		        	simModel.switchSim(comboBox.getItems().indexOf(newString));
		        	statesComboBox.setItems(statesCombo.initializeComboBoxOptions());
				}
		    });

		return comboBox;
	}

	/**
	 * @return the CURRENT_WIDTH
	 */
	public Number getCurrentWidth() {
		return CURRENT_WIDTH;
	}

	/**
<<<<<<< HEAD
	 * @return the CURRENT_HEIGHT
=======
	 * @return the CURRENT_HEIGHT  
>>>>>>> py22_branches
	 */
	public Number getCurrentHeight() {
		return CURRENT_HEIGHT;
	}
}
