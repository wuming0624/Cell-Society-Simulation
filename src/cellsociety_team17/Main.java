package cellsociety_team17;


import gui.SimModel;
import gui.SimView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import locale.Resource;

public class Main extends Application {
	private SimView simView;
	private SimModel simModel;
	private Resource resource;
	private static Init[] initArray = initInitClassArray();
	private Processor[] processorArray = initProcessorArray();



    private int DEFAULT_SIM = 0; // DEFAULT GAME
    
	public static Init[] initInitClassArray(){
		Init[] initArray = {new FireInit(), 
				new LifeInit(), 
				new WaTorInit(), 
				new SegregationInit(),
				new SugarInit()};
		return initArray;
	}
	
	public static Processor[] initProcessorArray(){
		Processor[] processorArray = { new FireProcessor(initArray[0].returnGrid(),initArray[0]),
				new LifeProcessor(initArray[1].returnGrid(),initArray[1]),
				new WaTorProcessor(initArray[2].returnGrid(),initArray[2]),
				new SegregationProcessor(initArray[3].returnGrid(),initArray[3]),
		    new SugarProcessor(initArray[4].returnGrid(), initArray[4])};
		return processorArray;
	}
	@Override
	public void start(Stage s) {
		simModel = new SimModel();
		resource = new Resource("EN_US");
		simView = new SimView(simModel, resource, s);
		s.setTitle(resource.getAllResourceBundles().get("common").getString("title_bar"));
		s.setScene(simView.getScene());
		s.show();
		
		// Pass in a list of the possible states
		// Put in filename as key for the simInit class
		
		Timeline simulation = new Timeline();
		simModel.init(simView, simulation, processorArray, initArray, DEFAULT_SIM);
		KeyFrame frame = new KeyFrame(Duration.millis(simModel.getFrameDuration()),e->simModel.iterate());
		simulation.setCycleCount(Timeline.INDEFINITE);
		simulation.getKeyFrames().add(frame);
		simulation.play();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
