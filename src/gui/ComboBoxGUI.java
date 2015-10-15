package gui;

import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

public abstract class ComboBoxGUI extends ComboBox{
	private ObservableList<String> comboBoxOptions;
	SimModel simModel;
	protected HashMap<String, ResourceBundle> allResourcesBundle;

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


	public ComboBoxGUI(SimModel model, HashMap<String, ResourceBundle> bundle) {
		simModel = model;
		allResourcesBundle = bundle;
	}

	public ObservableList<String> getComboBoxOptions() {
		return comboBoxOptions;
	}

	public void setComboBoxOptions(ObservableList<String> comboBoxOptions) {
		this.comboBoxOptions = comboBoxOptions;
	}
	
	public ComboBox<String> makeComboBox(ObservableList<String> options) {
		ComboBox<String> comboBox = new ComboBox<String>(options);

		// Resolved bug with Windows 10 and touchscreen
		comboBox.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				comboBox.requestFocus();
			}
		});
		return comboBox;
	}
	
	
	public abstract ObservableList<String> initializeComboBoxOptions();
}