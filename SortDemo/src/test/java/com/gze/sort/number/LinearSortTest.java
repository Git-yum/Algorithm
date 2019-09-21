package com.gze.sort.number;

import org.junit.Test;

import static org.junit.Assert.*;

public class LinearSortTest {
    LinearSort ls = new LinearSort();
    int[] expecteds = {-111,-12,-1,1,2,3,4,5,6,7,8,9,9};
    int[] actuals = {9,3,2,5,6,8,4,1,7,-1,9,-12,-111};

    @Test
    public void countingSort() {
        ls.countingSort(actuals);
        assertArrayEquals(expecteds,actuals);
    }

    @Test
    public void radixSort(){
        ls.radixSort(actuals);
        assertArrayEquals(expecteds,actuals);
    }

    @Test
    public void bucketSort(){
        ls.bucketSort(actuals);
        assertArrayEquals(expecteds,actuals);
    }
}