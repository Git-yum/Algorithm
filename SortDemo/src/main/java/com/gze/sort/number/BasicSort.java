package com.gze.sort.number;

/**
 * 排序方式：升序
 * 基本的数值排序：冒泡排序、插入排序、选择排序
 * 可替换数据类型：byte/short/int/long/float/double
 * 最坏时间复杂度：O(n^2)
 * 平均时间复杂度：O(n^2)
 * 平均空间复杂度：O(1)
 */
public class BasicSort{
    /**
     * 两个数组元素交换
     * 空间复杂度优化建议：可通过异或运算、两数之和与之差去掉临时变量
     * @param array 数组
     * @param i 小的索引
     * @param j 大的索引
     */
    public void swap(int[] array,int i,int j){
        if (0 <= i && i < j && j < array.length){
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    /**
     * 冒泡排序：稳定
     * 时间复杂度优化建议：通过记录交换次数可以减少循环次数，最好时间复杂度O(n)
     * @param array 待排序的数组
     */
    public void bubbleSort(int[] array) {
        for (int i = 0;i < array.length - 1;i++){
            for (int j = 1;j < array.length - i;j++){
                if (array[j] < array[j - 1]){
                    swap(array,j-1,j);
                }
            }
        }
    }

    /**
     * 插入排序：稳定
     * 时间复杂度优化建议：通过记录交换次数可以减少循环次数，最好时间复杂度O(n)
     * @param array 待排序的数组
     */
    public void insertionSort(int[] array){
        for (int i = 1;i < array.length;i++){
            int temp = array[i];
            int j = i - 1;
            while (j >= 0 && temp < array[j]){
                array[j + 1] = array[j];
                j--;
            }
//            for (;j >= 0;j--){
//                if (temp < array[j]){
//                    array[j + 1] = array[j];
//                } else {
//                  break;
//                }
//            }
            array[j + 1] = temp;
        }
    }

    /**
     * 选择排序：不稳定，如：a(2)b(2)c(1)，排序后：c(1)b(2)a(2)
     * @param array 待排序的数组
     */
    public void selectionSort(int[] array){
        for (int i = 0;i < array.length - 1; i++){
            for (int j = i + 1;j < array.length;j++){
                if (array[i] > array[j]){
                    swap(array,i,j);
                }
            }
        }
    }
}
