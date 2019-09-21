package com.gze.sort.number;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HigherSortTest {

    private HigherSort hs;
    int[] expecteds = {1,2,3,4,4,5,6,7,8,9};
    int[] actuals = {9,3,2,5,6,8,4,1,7,4};

    @Before
    public void setUp() throws Exception {
        hs = new HigherSort();
        System.out.println("测试开始");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("测试结束");
    }

    @Test
    public void quickSort() {
        hs.quickSort(actuals);
        assertArrayEquals(expecteds,actuals);
    }

    @Test
    public void shellSort() {
        hs.shellSort(actuals);
        assertArrayEquals(expecteds,actuals);
    }

    @Test
    public void heapSort() {
        hs.heapSort(actuals);
        assertArrayEquals(expecteds,actuals);
    }

    @Test
    public void mergeSort(){
        hs.mergeSort(actuals);
        assertArrayEquals(expecteds,actuals);
    }
}