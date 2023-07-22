package com.example.apate695_assignment1;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;


public class SortingHubController {
    //private fields
    private int [] intArray;
    private SortingStrategy sortingMethod;
    @FXML
    private Label sizeLabel;
    @FXML
    private Slider slider;
    @FXML
    private Pane pane;
    @FXML
    private ComboBox<String> combo;

    //updates the graph by changing the heights and width of rectangle while taking spacing into consideration
    @FXML
    public void updateGraph(int [] data){
        pane.getChildren().clear();
        double w=pane.getPrefWidth()/data.length;
        for(int i = 0; i < data.length; i++) {
            double h= (data[i]*pane.getHeight())/(data.length);
            double x= w*i+1;
            double y= (pane.getPrefHeight()-(h));
            Rectangle rec =new Rectangle(x,y,w-2,h);
            rec.setFill(Color.RED);
            pane.getChildren().add(rec);
        }}
    //creating a random array ranging values from 1-128 but no duplicating numbers, using hashset to not use duplicate #
    //displaying slider value and calling update graph method using the random array generated
    @FXML
    public void createRandomArray() {
        intArray = new int[(int) slider.getValue()];
        Random rand = new Random();
        Set<Integer> usedHeights = new HashSet<>();

        for (int i = 0; i < intArray.length;) {
            int arr = rand.nextInt(intArray.length)+1;
            if (!usedHeights.contains(arr)) {
                usedHeights.add(arr);
                intArray[i] = arr;
                i++;}
        }
        sizeLabel.setText(String.format("%.0f",slider.getValue()));
        updateGraph(intArray);
    }
    //used a switch case to identify what sorting algorithm the user picked in the combo box and then
    //using an instance of sorting class to then run the sorting program
    @FXML
    public void setSortStrategy() {
        String cValue = combo.getValue();
        switch (cValue) {
            case "MergeSort":
                MergeSort merge = new MergeSort(intArray, this);
                merge.run();
            case "InsertionSort":
                InsertionSort insert = new InsertionSort(intArray,this);
                insert.run();
        }
    }
    //initializing the combo box algorithms, the slider value
    @FXML
    public void initialize(){
        combo.getItems().setAll("MergeSort","InsertionSort");
        slider.setValue(64);
        combo.getSelectionModel().selectFirst();
        Platform.runLater(()->{createRandomArray();});

    }
    //reset button to slider 64 and generate random array
    @FXML
    public void rBtn(){
        slider.setValue(64);
        createRandomArray();
    }
    //sort button when click calls the sortstrategy meth
    @FXML
    public void sBtn(){
        setSortStrategy();
    }



}