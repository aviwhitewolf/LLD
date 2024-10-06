package MergeSortMultiThreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class MergeSort implements Callable<List<Integer>> {
    List<Integer> arrayToSort;
    ExecutorService ex;

    public MergeSort(List<Integer> arrayToSort, ExecutorService ex) {
        this.arrayToSort = arrayToSort;
        this.ex = ex;
    }


    @Override
    public List<Integer> call() throws Exception {
        if(arrayToSort.size() <= 1) return arrayToSort;

        //Merge Uses Divide and Concur Strategy

        //First Step to divide the array
        int mid = arrayToSort.size() / 2;
        List<Integer> leftHalf = arrayToSort.subList(0, mid);
        List<Integer> rightHalf = arrayToSort.subList(mid , arrayToSort.size());

        //Now we merge (concur) the array
        //But we will be using multithreading


        Future<List<Integer>> leftSortedFuture = ex.submit(new MergeSort(leftHalf, ex));
        Future<List<Integer>> rightSortedFuture = ex.submit(new MergeSort(rightHalf, ex));

        List<Integer> leftSortedArray = leftSortedFuture.get();
        List<Integer> rightSortedArray = rightSortedFuture.get();

        List<Integer> finalSortedArray = new ArrayList<>();

        int i = 0, j = 0;
        while( i < leftSortedArray.size() && j < rightSortedArray.size()){
            if(leftSortedArray.get(i) < rightSortedArray.get(j)){
                finalSortedArray.add(leftSortedArray.get(i));
                i++;
            }else {
                finalSortedArray.add(rightSortedArray.get(i));
                j++;
            }
        }

        while(i < leftSortedArray.size()){
            finalSortedArray.add(leftSortedArray.get(i));
            i++;
        }

        while(j < rightSortedArray.size()){
            finalSortedArray.add(rightSortedArray.get(j));
            j++;
        }

        return finalSortedArray;
    }
}
