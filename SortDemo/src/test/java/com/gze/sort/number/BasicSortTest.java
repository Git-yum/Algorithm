package com.gze.sort.number;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasicSortTest {
    BasicSort bs;
    int[] expecteds = {1,2,3,4,5,6,7,8,9};
    int[] actuals = {9,3,2,5,6,8,4,1,7};

    @Before
    public void setUp() throws Exception {
        bs = new BasicSort();
        System.out.println("创建对象");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("测试成功");
    }

    @Test
    public void swap() {
        //交换0、1索引元素
        bs.swap(actuals,0,1);
        assertArrayEquals(new int[]{3,9,2,5,6,8,4,1,7},actuals);
    }

    @Test
    public void bubbleSort() {
        bs.bubbleSort(actuals);
        assertArrayEquals(expecteds,actuals);
    }

    @Test
    public void insertionSort() {
        bs.insertionSort(actuals);
        assertArrayEquals(expecteds,actuals);
    }

    @Test
    public void selectionSort() {
        bs.selectionSort(actuals);
        assertArrayEquals(expecteds,actuals);
    }
}