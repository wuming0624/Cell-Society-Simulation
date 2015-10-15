package gui;

import java.util.*;
import java.util.HashMap;
import java.util.ResourceBundle;

import cellsociety_team17.Init;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class StatesComboBoxGUI extends ComboBoxGUI {
	private Init[] initArray;
	private String[] statesArray;
	private HashMap<String, Integer> statesMap;
	/**
	 * @return the statesArray
	 */
	public HashMap<String, Integer> getStatesMap() {
		return statesMap;
	}

	public StatesComboBoxGUI(SimModel model, HashMap<String, ResourceBundle> bundle, Init[] array) {
		super(model, bundle);
		initArray = array;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public ObservableList<String> initializeComboBoxOptions() {
		// TODO Auto-generated method stub
		String keyForSim = 	initArray[this.getSimModel().getCurrentSim()].getName();
		// Key to access the resource bundle
		String states = allResourcesBundle.get(keyForSim).getString("states");
		statesArray = states.split(",");
		// Creating statesMap
		statesMap = new HashMap<String,Integer>();
		for(int i=0;i<statesArray.length;i++){
			statesMap.put(statesArray[i],i);
		}
		////System.out.println(FXCollections.observableList(Arrays.asList(statesArray)));
		// Not adding in the list properly
		return FXCollections.observableList(Arrays.asList(statesArray));
	
	
	}

}
