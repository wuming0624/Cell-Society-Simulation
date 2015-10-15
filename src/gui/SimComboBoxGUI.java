package gui;


import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

public class SimComboBoxGUI extends ComboBoxGUI {
	private ObservableList<String> comboBoxOptions;
	private SimModel simModel;
	/**
	 * @return the simModel
	 */
	public SimModel getSimModel() {
		return simModel;
	}

	/**
	 * @param simModel the simModel to set
	 */
	public void setSimModel(SimModel simModel) {
		this.simModel = simModel;
	}


	public SimComboBoxGUI(SimModel model, HashMap<String, ResourceBundle> bundle) {
		super(model,bundle);
		this.setSimModel(model);
		this.allResourcesBundle = bundle;
	}

	@Override

	public ObservableList<String> initializeComboBoxOptions() {
		// initialise comboBoxOptions
		return FXCollections.observableList(Arrays.asList(allResourcesBundle.get("fire").getString("grid_title"),
				allResourcesBundle.get("gameOfLife").getString("grid_title"),
				allResourcesBundle.get("predatorPrey").getString("grid_title"),
				allResourcesBundle.get("segregation").getString("grid_title"),
				allResourcesBundle.get("sugar").getString("grid_title")));
	}
	
	public ComboBox<String> makeComboBox(ObservableList<String> options) {
		ComboBox<String> comboBox = new ComboBox<String>(options);
		comboBox.setPromptText(options.get(simModel.getCurrentSim()));
		
		// Resolved bug with Windows 10 and touchscreen
		comboBox.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				comboBox.requestFocus();
			}
		});
		return comboBox;
	}

}