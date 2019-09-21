package com.gze.sort.number;
/**
 * 排序方式：升序
 * 高级的数值排序：快速排序、希尔排序、堆排序、归并排序
 * 可替换数据类型：byte/short/int/long/float/double
 * 最坏时间复杂度：O(n^2)、O(n^2)、O(n*logn)、O(n*logn)
 * 平均时间复杂度：O(n*logn)、O(n^1.3)、O(n*logn)、O(n*logn)
 * 平均空间复杂度：O(n*logn)、O(1)、O(1)、O(n)
 */
public class HigherSort {
    //成员变量
    private BasicSort bs;
    //构造方法
    public HigherSort(){
        bs = new BasicSort();
    }
    /**
     * 快速排序，和冒泡排序同属于交换排序
     * @param array 待排序的数组
     */
    public void quickSort(int[] array){
        quickSort(array,0,array.length - 1);
    }

    /**
     *  快速排序，单路排序，此版本稳定，但双路排序、三路排序不稳定
     *  可优化为双路排序、三路排序、随机快速排序
     * @param array 待排序的数组
     * @param left  小的索引
     * @param right 大的索引
     */
    public void quickSort(int[] array,int left,int right){
        if (0 <= left && left < right && right < array.length){
            //分区：左侧小于pivot索引对应的值，右侧大于pivot索引对应的值
            int pivot = left;
            for (int i = pivot + 1;i < array.length;i++){
                if (array[i] < array[left]){
                    bs.swap(array,++pivot,i);
                }
            }
            bs.swap(array,left,pivot);
            //递归左右子数组
            quickSort(array,left,pivot - 1);
            quickSort(array,pivot + 1,right);
        }
    }

    /**
     * 希尔排序，插入算法的改进版，不稳定，如：a(3)b(3)c(2),排序后：c(2)b(3)a(3)
     * 可优化调整mod的值，使其时间复杂度达到O(n^1.3)
     * @param array 待排序的数组
     */
    public void shellSort(int[] array){
        int len = array.length;
        int mod = len / 2;
        while (mod > 0){
            for (int i = 0;i < mod;i++){
                modSort(array,mod,i);
            }
            mod /= 2;
        }
    }

    /**
     * 有间隔的数组排序，当mod==1&&startIndex==0时为插入排序
     * @param array 待排序的数组
     * @param mod   子数组的长度
     * @param startIndex 起始索引
     */
    private void modSort(int[] array,int mod,int startIndex){
        for (int i = startIndex + mod;i < array.length;i += mod){
            int temp = array[i];
            int j = i - mod;
            for (;j >= 0;j -= mod){
                if (temp < array[j]){
                    array[j + mod] = array[j];
                } else {
                    break;
                }
            }
            array[j + mod] = temp;
        }
    }

    /**
     * 堆排序，选择排序改进版,不稳定，如：a(3)b(3)c(3),排序后：b(3)a(3)c(3)
     * @param array 待排序的数组
     */
    public void heapSort(int[] array){
        int len = array.length;
        //建立大顶堆
        for (int i = len / 2 - 1;i >= 0;i--){
            maxTopHeap(array,i,len);
        }
        //交换与调整
        for (int j = len - 1;j > 0;j--){
            bs.swap(array,0,j);
            maxTopHeap(array,0,j);
        }
    }

    /**
     * 建立大堆顶
     * @param array 数组
     * @param topIndex 父节点索引
     * @param len  子数组长度
     */
    private void maxTopHeap(int[] array,int topIndex,int len){
        int leftNode = 2 * topIndex + 1;
        int rightNode = 2 * topIndex + 2;
        if (leftNode < len && array[leftNode] > array[topIndex]){
            bs.swap(array,topIndex,leftNode);
            maxTopHeap(array,leftNode,len);
        }
        if (rightNode < len && array[rightNode] > array[topIndex]){
            bs.swap(array,topIndex,rightNode);
            maxTopHeap(array,rightNode,len);
        }
    }

    /**
     * 归并排序，稳定
     * 可优化为多路归并
     * @param array 待排序的数组
     */
    public void mergeSort(int[] array){
        int len = array.length;
        int[] temp = new int[len];
        mergeSort(array,temp,len,1);
        if (halfToZero(len - 1) % 2 == 1){
            for (int i = 0;i < temp.length;i++){
                array[i] = temp[i];
            }
        }
    }

    private void mergeSort(int[] array,int[] temp,int len,int mod){
        if (len > 1 && mod > 0 && mod < len){
            for (int i = 0;i < len;i += 2 * mod){
                int left = i;
                int right = i + mod;
                int index = i;
                //2^n之后的元素直接复制到temp数组中
                while (right >= len && left < len){
                    temp[left] = array[left];
                    left++;
                }
                //正好是2^n个元素
                while (left < i + mod && right < i + 2 * mod && right < len){
                    if (array[left] < array[right]){
                        temp[index++] = array[left++];
                    } else {
                        temp[index++] = array[right++];
                    }

                    //左子数组都已放入临时数组，右子数组仍有剩余时
                    while (left == i + mod && right < i + 2 * mod && right < len){
                        temp[index++] = array[right++];
                    }
                    //右子数组都已放入临时数组，左子数组仍有剩余时
                    while ((right == i + 2 * mod || right == len) && left < i + mod){
                        temp[index++] = array[left++];
                    }
                }
            }
            mergeSort(temp,array,len,2 * mod);
        }
    }

    /**
     * 计算某数折半归零次数
     * @param maxIndex 数组最大索引
     * @return 次数
     */
    private int halfToZero(int maxIndex){
        int count = 0;
        while (maxIndex > 0){
            maxIndex /= 2;
            count++;
        }
        return count;
    }
}
