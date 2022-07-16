package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class PartiesApplication extends Application {

    public static void main(String[] args) {

        launch(PartiesApplication.class);
        System.out.println("Hello world!");


    }

    @Override
    public void start(Stage stage) throws Exception {
        Database dBase = new Database("partiesdata.tsv");

        Scene view = new Scene(makeLineChart(dBase), 640, 480);

        stage.setScene(view);
        stage.show();

    }

    public static LineChart<Number, Number> makeLineChart(Database dBase) {
        
        // create axies & set labels
        NumberAxis xAxis = new NumberAxis(1968, 2008, 4);
        xAxis.setLabel("Year");

        NumberAxis yAxis = new NumberAxis(0, 30, 5);
        yAxis.setLabel("Support");
        
        LineChart<Number,Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Relative support of the parties");


        // Create data set for LineChart
        Party yearInformation = dBase.getParty(0);
        for (int i = 1; i < dBase.getParties().size(); i++) { // Skip index 0 as it contains years
            XYChart.Series dataSet = new XYChart.Series();

            Party currentParty = dBase.getParty(i); 

            String partyName = currentParty.getName();
            dataSet.setName(partyName);

            for (int j = 0; j < currentParty.getSupportData().size(); j++) {

                try { // THIS IS IMPORTANT IF THERE WAS NO DATA FOR A YEAR
                    
                    // THIS IS IMPORTANT
                    Double number = Double.parseDouble(currentParty.getNumber(j)); 
                    
                    Integer year = Integer.valueOf(yearInformation.getNumber(j));

                    dataSet.getData().add(new XYChart.Data(year, number));

                } catch (Exception e){

                }
            }

            lineChart.getData().add(dataSet);
        }

        return lineChart;
    }

}
