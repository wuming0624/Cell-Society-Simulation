package gui;

import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ButtonGUI {
	private Button stepButton;
	private Button resetInitButton;
	private Button resetRandomButton;
	private Button stopButton;
	private Button runButton;
	private Button rectShapeButton;
	private Button triangleShapeButton;
	private HashMap<String, ResourceBundle> allResourcesBundle;
	private SimModel simModel;

	public ButtonGUI(SimModel model,HashMap<String, ResourceBundle> bundle) {
		allResourcesBundle = bundle;
		simModel = model;
	}

	public Button makeStepButton(){
		stepButton= makeButton("common", "step", e -> simModel.step());
		return stepButton;
	}
	
	public Button makeRunButton(){
		runButton = makeButton("common", "run", e -> simModel.play());
		return runButton;
	}
	
	public Button makeStopButton(){
		stopButton = makeButton("common", "stop", e -> simModel.pause());
		return stopButton;
	}
	
	public Button makeResetRandomButton(ComboBoxGUI comboBoxGUI){
		resetRandomButton = makeButton("common", "reset_random", null);
		return resetRandomButton;
	}
	
	public Button makeResetInitButton(ComboBoxGUI comboBoxGUI){
		resetInitButton = makeButton("common", "reset_initial", e -> comboBoxGUI.getSimModel().resetToInitial());
		return resetInitButton;
	}
	
	public Button makeRectShapeButton(){
		rectShapeButton = makeButton("common", "rect_shape", e->simModel.rectShape());
		return rectShapeButton;
	}
	
	public Button makeTriangleShapeButton(){
		triangleShapeButton = makeButton("common","triangle_shape",e->simModel.triangleShape());
		return triangleShapeButton;
	}
	
	public Button makeButton(String propertiesFileName, String keyString, EventHandler<ActionEvent> eventHandler) {
		Button button = new Button();
		String buttonLabel = allResourcesBundle.get(propertiesFileName).getString(keyString);
		button.setText(buttonLabel);
		button.setOnAction(eventHandler);
		return button;
	}
}