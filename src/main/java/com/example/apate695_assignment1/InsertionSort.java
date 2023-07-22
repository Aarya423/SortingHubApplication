package com.example.apate695_assignment1;

import javafx.application.Platform;

public class InsertionSort implements SortingStrategy{
    // private fileds
    private int [] list;
    private SortingHubController controller;
    // constructor
    public InsertionSort(){}
    //constructor with arguments
    public InsertionSort(int [] l,SortingHubController c ){
        this.controller=c;
        this.list=l;
    }
    //starting new thread for animation and calling mergeS to start off sorting
    public void sort(int [] numbers){
        new Thread(()->{
            insertion(numbers);

        }).start();
    }
    //insertion sort method
    //builds a final sorted array one step at a time
    public void insertion(int [] array){
        int k;
        for(int i = 1 ;i < array.length;i++){
            k = array[i];
            for(int j = i-1;j>=0 && array[j]>k;j--) {
                array[j+1] = array[j];
                array[j]=k;
            }
            //updating user interface
            Platform.runLater(()->
            {
                controller.updateGraph(list);
            });
            // using threading along with try and catch to avoid errors when animating
            try{
                Thread.sleep(50);
            }
            catch (InterruptedException ex){
                throw new RuntimeException(ex);
            }
        }}
    //calling sort method with the random array generated from the controller class
    public void run(){
        sort(list);
    }
}
