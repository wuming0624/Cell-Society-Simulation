package gui;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class GraphGUI {
	private CategoryAxis xAxis;
    private NumberAxis yAxis;
    private LineChart<String,Number> lineChart;
    /**
	 * @return the lineChart
	 */
	public LineChart<String, Number> getLineChart() {
		return lineChart;
	}

	/**
	 * @param lineChart the lineChart to set
	 */
	public void setLineChart(LineChart<String, Number> lineChart) {
		this.lineChart = lineChart;
	}

	private Series<String, Number>[] seriesArray;
    /**
	 * @return the seriesArray
	 */
	public XYChart.Series<String, Number>[] getSeriesArray() {
		return seriesArray;
	}

	/**
	 * @param seriesArray the seriesArray to set
	 */
	public void setSeriesArray(XYChart.Series<String, Number>[] seriesArray) {
		this.seriesArray = seriesArray;
	}

    public GraphGUI(){
    	xAxis = new CategoryAxis();
    	yAxis = new NumberAxis();
    	seriesArray = new XYChart.Series[3];
    	lineChart =  new LineChart<String,Number>(xAxis,yAxis);
    	lineChart.setTitle("Quantities of Each State");
    	for(int i=0;i<seriesArray.length;i++){
    		seriesArray[i] = new XYChart.Series<>();
    	}
    	lineChart.getData().addAll(seriesArray);

    }
    // Need to reset Graph GUI each time
    
   public void addToGraph(double frame, double state,double count){
	   //System.out.println(seriesArray[(int) state]);
	   seriesArray[(int) state].getData().add(new XYChart.Data<String, Number>(Double.toString(frame),count));
	   //System.out.println("Data to be added: "+ Integer.toString((int)frame) + " " + count);
   }
    
}
