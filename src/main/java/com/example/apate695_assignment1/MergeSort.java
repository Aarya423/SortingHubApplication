package com.example.apate695_assignment1;

import javafx.application.Platform;

public class MergeSort implements SortingStrategy{
    //private fields
    private int [] list;
    private SortingHubController controller;
    //constructor
    public MergeSort(){}
    //constructor with arguments
    public MergeSort(int [] l,SortingHubController c ){
        this.controller=c;
        this.list=l;
    }
    //starting new thread for animation and calling mergeS to start off sorting
    public void sort(int [] numbers){
        new Thread(()->{
            mergeS(numbers,0,numbers.length-1);
        }).start();

    }
    // main merge method to divide and conquer an array
    // basically divides in half, then sorts, then repeats the same step until sorted properly
    // threading is added to create an animated like action to show how the array is being divided
    public void merge(int []array,int s, int m, int e) {
        int s2=m+1;
        int v, i;
        // if sorted then return
        if (array[m]<=array[s2]){
            return;
        }
        //loop to start merge
        while (s<=m && s2 <=e){
            // if the first element is in the right place then shift the start to the right
            if(array[s]<=array[s2]){
                s++;
            }

            else{
                //creating variable
                v = array[s2];
                i = s2;
                //shifting between element 1 and 2
                while (i!=s){
                    array[i]=array[i-1];
                    i--;
                }
                array[s]=v;
                //updating values
                s++;
                m++;
                s2++;
            }
            //updating user interface
            Platform.runLater(()->
            {
                controller.updateGraph(list);

            });
            // using threading along with try and catch to avoid errors when animating
            try{
                Thread.sleep(5);
            }
            catch (InterruptedException ex){
                throw new RuntimeException(ex);
            }

        }

    }
    // calling merge methods
    public void mergeS(int []array, int left, int right){
        if(left<right){
            int m=left+(right-left)/2;
            //sorting the first and second half
            mergeS(array,left,m);
            mergeS(array,m+1,right);
            merge(array,left,m,right);
        }
    }
    //calling sort method with the random array generated from the controller class
    public void run(){
        sort(list);
    }
}
