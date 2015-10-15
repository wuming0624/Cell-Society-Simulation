package gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;

public class SliderGUI {
	private Slider speedSlider;
	private SimModel simModel;

	private final double FRAME_SPEED_MIN = 0.7;
	private final double FRAME_SPEED_MAX = 1.0;

	public SliderGUI(SimModel model) {
		simModel = model;
	}

	public Slider getSpeedSlider() {
		return speedSlider;
	}

	public void setSpeedSlider(Slider speedSlider) {
		this.speedSlider = speedSlider;
	}

	public Slider makeSlider(double min, double max, ChangeListener<Number> changeListener) {
		Slider slider = new Slider();
		slider.setMin(min);
		slider.setMax(max);
		slider.setValue(slider.getMax());
		slider.setShowTickMarks(true);
		slider.valueProperty().addListener(changeListener);
		return slider;
	}

	public Slider makeFrameSpeedSlider() {
		ChangeListener<Number> changeListener = new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				// TODO Auto-generated method stub
				simModel.adjustSpeed((double) arg2);
			}
		};
		return makeSlider(FRAME_SPEED_MIN, FRAME_SPEED_MAX, changeListener);
	}
}
