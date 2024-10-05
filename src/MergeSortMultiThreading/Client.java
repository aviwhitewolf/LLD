package MergeSortMultiThreading;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Client {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> ls = List.of(2,1,6,7,8,21,13,23,1231,3,2,3,2,2,3,4,5,43,2,4,13,10,9);
        ExecutorService ex = Executors.newCachedThreadPool();
        MergeSort sort = new MergeSort(ls, ex);
        Future<List<Integer>> finalFuture = ex.submit(sort);
        System.out.println(finalFuture.get());
    }

}
